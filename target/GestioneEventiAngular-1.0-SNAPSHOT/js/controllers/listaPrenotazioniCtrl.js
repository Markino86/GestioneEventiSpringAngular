gestioneEventi.controller('listaPrenotazioniCtrl', function($scope,$sessionStorage,$route,$location,dataServices){
    var asyncCallbackPrenotazioni = function(risposta) {
                            if(risposta.codice === 0){
                                $scope.mostraPrenotazioni = false;
                                $scope.eventiPrenotati = risposta.risultato;
                                
                            }
                            else{
                                $scope.mostraPrenotazioni = true;
                                $scope.messaggio = risposta.messaggio;
                            }
    },
    asyncCallbackAnnulla = function(){
        $route.reload();
    };

    $scope.logout = function(){
        $sessionStorage.$reset();
        $location.path('/login');    
    };
    
    dataServices.eventiPrenotati(asyncCallbackPrenotazioni);
    
    $scope.annullaIscrizione = function(idEvento){
        dataServices.annullaIscrizione(idEvento,asyncCallbackAnnulla);
    };
});


