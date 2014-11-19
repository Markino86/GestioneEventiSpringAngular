gestioneEventi.controller('creaEventoCtrl',function($scope,$sessionStorage,$location,dataServices){
    var asyncCallbackRelatori = function(risposta) {
                            if(risposta.codice === 0){
                                $scope.mostraRelatore = false;
                                $scope.relatori = risposta.risultato; 
                            }
                            else{
                                $scope.mostraRelatore = true;
                                $scope.messaggio = risposta.messaggio;
                            }
        },
        asyncCallbackEvento = function(risposta) {
                            if(risposta.codice === 0){
                                $scope.mostraRelatore = false;
                                //$scope.evento = risposta.risultato; 
                            }
                            else{
                                $scope.mostraRelatore = true;
                                $scope.messaggio = risposta.messaggio;
                            }
        },
        verificaCreazione = function() {

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

        if ($('#titolo').val().length === 0) {
            toastr.error('Il titolo non può essere vuoto!');
            return false;
        }
        if ($('#descrizione').val().length === 0) {
            toastr.error('La descrizione non può essere vuota!');
            return false;
        }
        if ($('#location').val().length === 0) {
            toastr.error('Location non può essere vuota!');
            return false;
        }
        return true;
    };
    
    $scope.logout = function(){
        $sessionStorage.$reset();
        $location.path('/login');    
    };
    
    dataServices.getAllRelatori(asyncCallbackRelatori);
    
    $scope.creaEvento = function(evento){
        if(verificaCreazione())
            dataServices.creaEvento(evento,asyncCallbackEvento);
    };
    
    return {
        verificaEvento: verificaCreazione
    };
});


