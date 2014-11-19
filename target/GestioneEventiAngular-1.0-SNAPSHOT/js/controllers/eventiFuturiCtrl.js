gestioneEventi.controller('eventiFuturiCtrl', function($scope,$sessionStorage,$location,$route,dataServices){
    var asyncCallbackFuturi = function(risposta) {
                            if(risposta.codice === 0){
                                $scope.mostraFuturi = false;
                                $scope.eventiFuturi = risposta.risultato;
                            }
                            else{
                                $scope.mostraFuturi = true;
                                $scope.messaggio = risposta.messaggio;
                            }
                        },
        asyncCallbackPartecipanti = function(risposta) {
                            $scope.mostraTable = true;
                            if(risposta.codice === 0){
                                $scope.mostraPartecipanti= false;
                                $scope.listaPartecipanti = risposta.risultato;
                                
                            }
                            else{
                                $scope.mostraPartecipanti= true;
                                $scope.messaggio = risposta.messaggio;
                            }
                        },
        asyncCallbackIscriviti = function(risposta) {
                            if(risposta.codice === 0){
                                $scope.mostraIscritto = true;
                                $scope.mostraErrIscritto = false;
                                $scope.messaggio = risposta.messaggio;
                            }
                            else{
                                $scope.mostraIscritto= false;
                                $scope.mostraErrIscritto = true;
                                $scope.messaggio = risposta.messaggio;  
                            }
        };
        
    $scope.logout = function(){
        $sessionStorage.$reset();
        $location.path('/login');    
    };
    
    $scope.mostraAdmin = dataServices.mostraAdmin();
    
    $scope.eventiFuturi = dataServices.eventiFuturi(asyncCallbackFuturi);
    
    $scope.listaPartecipanti = function(idEvento){
        dataServices.listaPartecipanti(asyncCallbackPartecipanti,idEvento);
    };
    
    $scope.iscriviti = function (idEvento){
        dataServices.iscriviti(asyncCallbackIscriviti,idEvento);  
    };
    
});


