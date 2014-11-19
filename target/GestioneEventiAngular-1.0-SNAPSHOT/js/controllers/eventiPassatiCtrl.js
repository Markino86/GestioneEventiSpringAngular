gestioneEventi.controller('eventiPassatiCtrl', function($scope,$sessionStorage,$location,dataServices){
    var asyncCallbackPassati = function(risposta) {
                            if(risposta.codice === 0){
                                $scope.mostraFuturi = false;
                                $scope.eventiPassati = risposta.risultato;
                            }
                            else{
                                $scope.mostraPassati = true;
                                $scope.messaggio = risposta.messaggio;
                            }
                        };  
    $scope.logout = function(){
        $sessionStorage.$reset();
        $location.path('/login');    
    };
    
    $scope.mostraAdmin = dataServices.mostraAdmin();
    
    $scope.eventiPassati = dataServices.eventiPassati(asyncCallbackPassati);
                                    
});


