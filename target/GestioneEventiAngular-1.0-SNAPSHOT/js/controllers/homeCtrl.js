gestioneEventi.controller('homeCtrl', function($scope,$sessionStorage,$location,dataServices){
    
    $scope.logout = function(){
        $sessionStorage.$reset();
        $location.path('/login');    
    };
    
    
    $scope.mostraAdmin = dataServices.mostraAdmin();                                
});



