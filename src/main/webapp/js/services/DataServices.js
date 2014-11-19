gestioneEventi.factory('dataServices',function($http,$sessionStorage) {
    var login = function(utente,asyncCallback){
            $http.post('login.do',utente).
                success(function(data, status, headers, config){
                    asyncCallback(data);
                });
        },
        creaUtente = function(utente, asyncCallback){
            $http.post('registrati.do',utente).
                success(function(data, status, headers, config){
                    asyncCallback(data);
                });
        },
        creaRelatore = function(relatore, asyncCallback){
            $http.post('creaRelatore.do',relatore).
                success(function(data, status, headers, config){
                    asyncCallback(data);
                });
        },
        mostraAdmin = function(){
            return $sessionStorage.utente.profilo === 'admin';
        },
        getAllRelatori = function(asyncCallbackRelatori){
            $http.get('getAllRelatori.do').
                success(function(data, status, headers, config){
                    asyncCallbackRelatori(data);
                });
        },
        creaEvento = function(evento,asyncCallbackEvento){
            $http.post('registraEvento.do',evento).
                success(function(data, status, headers, config){
                    asyncCallbackEvento(data);
                });
        },
        eventiFuturi = function(asyncCallbackFuturi){
            $http.get('eventiFuturi.do').
                success(function(data, status, headers, config){
                    asyncCallbackFuturi(data);
                });
        },
        eventiPassati = function(asyncCallbackPassati){
            $http.get('eventiPassati.do').
                success(function(data, status, headers, config){
                    asyncCallbackPassati(data);
                });
        },
        listaPartecipanti = function(asyncCallbackPartecipanti,idEvento){
            $http.get('visualizzaPartecipanti.do',{params:{id:idEvento}}).
                    success(function(data, status, headers, config){
                    asyncCallbackPartecipanti(data);
                });
        },
        iscriviti = function(asyncCallbackIscriviti,idEvento){
            $http.get('iscrizione.do',{params:{id:idEvento, username:$sessionStorage.utente.username}}).
                    success(function(data, status, headers, config){
                    asyncCallbackIscriviti(data);   
                });
        },
        eventiPrenotati = function(asyncCallbackEvento){
            $http.post('listaPrenotazioni.do',$sessionStorage.utente).
                success(function(data, status, headers, config){
                    asyncCallbackEvento(data);
                });
        },
        annullaIscrizione = function(idEvento,asyncCallbackAnnulla){
            $http.delete('annullaIscrizione.do',{params:{id:idEvento, username:$sessionStorage.utente.username}}).
                    success(function(data, status, headers, config){
                        asyncCallbackAnnulla();
                });
        };

    return {
        login : login,
        creaUtente : creaUtente,
        mostraAdmin : mostraAdmin,
        creaRelatore : creaRelatore,
        getAllRelatori: getAllRelatori,
        creaEvento : creaEvento,
        eventiFuturi : eventiFuturi,
        eventiPassati : eventiPassati,
        listaPartecipanti : listaPartecipanti,
        iscriviti : iscriviti,
        eventiPrenotati : eventiPrenotati,
        annullaIscrizione : annullaIscrizione
    };
});


