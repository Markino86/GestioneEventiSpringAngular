var gestioneEventi = angular.module('gestioneEventi', ['ngRoute','ngStorage']);

gestioneEventi.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/login', {
        templateUrl: 'partials/login-partial.html',
        controller: 'loginCtrl'
      }).
      when('/home', {
        templateUrl: 'partials/home-partial.html',
        controller: 'homeCtrl'
      }).
      when('/registrazione', {
        templateUrl: 'partials/registrazione-partial.html',
        controller: 'registrazioneCtrl'
      }).
      when('/creaRelatore', {
        templateUrl: 'partials/creaRelatore-partial.html',
        controller: 'creaRelatoreCtrl'
      }).
      when('/creaEvento', {
        templateUrl: 'partials/creaEvento-partial.html',
        controller: 'creaEventoCtrl'
      }).
      when('/eventiFuturi', {
        templateUrl: 'partials/eventiFuturi-partial.html',
        controller: 'eventiFuturiCtrl'
      }).
      when('/eventiPassati', {
        templateUrl: 'partials/eventiPassati-partial.html',
        controller: 'eventiPassatiCtrl'
      }).
      when('/listaPrenotazioni', {
        templateUrl: 'partials/listaPrenotazioni-partial.html',
        controller: 'listaPrenotazioniCtrl'
      }).         
      otherwise({
        redirectTo: '/login'
      });
  }]);

