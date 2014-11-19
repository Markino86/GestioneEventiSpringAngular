gestioneEventi.controller('creaRelatoreCtrl',function($scope,$sessionStorage,$location,dataServices){
    var asyncCallback = function(risposta) {
                            if(risposta.codice === 0){
                                $scope.mostraRelatore = false;
                                $scope.relatore = risposta.risultato;
                            }
                            else{
                                $scope.mostraRelatore = true;
                                $scope.messaggio = risposta.messaggio;
                            }
                        },
        isNotEmpty = function () {
            toastr.options = {
                "closeButton": true,
                "debug": false,
                "positionClass": "toast-top-right",
                "onclick": null,
                "showDuration": "300",
                "hideDuration": "1000",
                "timeOut": "5000",
                "extendedTimeOut": "1000",
                "showEasing": "swing",
                "hideEasing": "linear",
                "showMethod": "fadeIn",
                "hideMethod": "fadeOut"
            };
        if ($('#nome').val().length === 0) {
            toastr.error('Il campo Nome non può essere vuoto!');
            return false;
        }
        if ($('#cognome').val().length === 0) {
            toastr.error('Il campo Cognome non può essere vuoto!');
            return false;
        }
        if ($('#titoloStudio').val().length === 0) {
            toastr.error('Il campo Titolo di studio non può essere vuoto!');
            return false;
        }
        if ($('#email').val().length === 0) {
            toastr.error('Il campo E-mail non può essere vuoto!');
            return false;
        }
        
        return true;
    };                              
    $scope.creaRelatore = function(relatore){
        if(isNotEmpty())
            dataServices.creaRelatore(relatore,asyncCallback);
    };
    $scope.logout = function(){
        $sessionStorage.$reset();
        $location.path('/login');    
    };
    
    return {
        isNotEmpty : isNotEmpty 
    };
});


