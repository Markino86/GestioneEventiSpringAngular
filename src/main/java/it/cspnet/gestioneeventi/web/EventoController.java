package it.cspnet.gestioneeventi.web;

import it.cspnet.gestioneeventi.exception.IscrizioneGiaEffettuataException;
import it.cspnet.gestioneeventi.model.Evento;
import it.cspnet.gestioneeventi.model.JsonResult;
import it.cspnet.gestioneeventi.model.Utente;
import it.cspnet.gestioneeventi.servizi.ServiziEventi;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EventoController {

    @Autowired
    private ServiziEventi servizi;
    
    private static Logger logger = Logger.getLogger(EventoController.class);

    public void setServizi(ServiziEventi servizi) {
        this.servizi = servizi;
    }

    @RequestMapping(value = "/eventiPassati", method = RequestMethod.GET)
    public @ResponseBody JsonResult getEventiPassati() {
        JsonResult jR = new JsonResult();
        Collection<Evento> eF = null;
        try {
            eF = servizi.getEventiPassati();
            jR.setRisultato(eF);            
            jR.setCodice(0);
            if(eF.isEmpty()){
                jR.setCodice(1);
                jR.setMessaggio("Non ci sono eventi passati!");
                logger.debug("Non ci sono eventi passati!");
            }
        } catch (Exception ex) {
            logger.error("Eccezione: " + ex.getMessage());
            jR.setCodice(1);
            jR.setMessaggio("Sito in manutenzione!");
        }
        return jR;
    }

    @RequestMapping(value = "/eventiFuturi", method = RequestMethod.GET)
    public @ResponseBody JsonResult getEventifuturi() {
        JsonResult jR = new JsonResult();
        Collection<Evento> eF = null;
        try {
            eF = servizi.getListaEventiFuturi();
            jR.setRisultato(eF);            
            jR.setCodice(0);
            if(eF.isEmpty()){
                jR.setCodice(1);
                jR.setMessaggio("Non ci sono eventi futuri!");
                logger.debug("Non ci sono eventi futuri!");
            }
        } catch (Exception ex) {
            logger.error("Eccezione: " + ex.getMessage());
            jR.setCodice(1);
            jR.setMessaggio("Sito in manutenzione!");
        }
        return jR;
    }

    @RequestMapping(value = "/iscrizione", method = RequestMethod.GET)
    public @ResponseBody JsonResult iscrizioneAdEvento(HttpServletRequest req) {
        long idEvento = Long.parseLong(req.getParameter("id"));
        String username = req.getParameter("username");
        JsonResult jR = new JsonResult();
        try {
            servizi.iscrizioneAdEvento(idEvento, username);
            jR.setCodice(0);
            jR.setMessaggio("Iscrizione avvenuta con successo!!");
            logger.debug(username + " iscritto correttamento all'evento con id: " + idEvento);
        } catch (IscrizioneGiaEffettuataException ex) {
            logger.error("Eccezione: " + ex.getMessage());
            jR.setCodice(1);
            jR.setMessaggio("Già iscritto all'evento!!");
        }
        return jR;
    }

    @RequestMapping(value = "/visualizzaPartecipanti", method = RequestMethod.GET)
    public @ResponseBody JsonResult listaPartecipanti(HttpServletRequest req) {
        long idEvento = Long.parseLong(req.getParameter("id"));
        JsonResult jR = new JsonResult();
        Collection<Utente> uP = null;
        try {
            uP = servizi.listaPartecipanti(idEvento).getUtentiPartecipanti();
            jR.setRisultato(uP);            
            jR.setCodice(0);
            if(uP.isEmpty()){
                jR.setCodice(1);
                jR.setMessaggio("Non ci sono partecipanti all'evento!");
                logger.debug("Non ci sono partecipanti all'evento con id: " + idEvento);
            }
        } catch (Exception ex) {
            logger.error("Eccezione: " + ex.getMessage());
            jR.setCodice(1);
            jR.setMessaggio("Sito in manutenzione!");
        }
        return jR;
    }

    @RequestMapping(value = "/listaPrenotazioni", method = RequestMethod.POST)
    public @ResponseBody JsonResult listaPrenotazioni(@RequestBody Utente utente) {
        JsonResult jR = new JsonResult();
        Collection<Evento> eP = null;
        try {
            eP = servizi.listaPrenotazioni(utente.getUsername());
            jR.setRisultato(eP);            
            jR.setCodice(0);
            if(eP.isEmpty()){
                jR.setCodice(1);
                jR.setMessaggio("Non sei iscritto a nessun evento!");
                logger.debug(utente.getUsername() + "non iscritto ad alcun evento");
            }
        } catch (Exception ex) {
            logger.error("Eccezione: " + ex.getMessage());
            jR.setCodice(1);
            jR.setMessaggio("Sito in manutenzione!");
        }
        return jR;
    }

    @RequestMapping(value = "/annullaIscrizione", method = RequestMethod.DELETE)
    public @ResponseBody JsonResult annullaIscrizione(HttpServletRequest req) {
        long idEvento = Long.parseLong(req.getParameter("id"));
        String username = req.getParameter("username");
        JsonResult jR = new JsonResult();
        try {
            servizi.annullaIscrizione(idEvento, username);
            logger.debug(username + "ha annullato correttamente l'iscrizione all'evento con id: " + idEvento);
        } catch (Exception ex) {
            logger.error("Eccezione: " + ex.getMessage());
            jR.setCodice(1);
            jR.setMessaggio("Sito in manutenzione!");
        }
        return jR;
    }

}
