package it.cspnet.gestioneeventi.web;

import it.cspnet.gestioneeventi.model.Evento;
import it.cspnet.gestioneeventi.model.JsonResult;
import it.cspnet.gestioneeventi.servizi.ServiziEventi;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CreaEventoController {

    @Autowired
    private ServiziEventi servizi;
    
    private static Logger logger = Logger.getLogger(CreaEventoController.class);

    public void setServizi(ServiziEventi servizi) {
        this.servizi = servizi;
    }
    
    @RequestMapping(value = "/registraEvento", method = RequestMethod.POST)
    public @ResponseBody JsonResult registrazioneEvento(@RequestBody Evento evento) {
        JsonResult jR = new JsonResult();
        try{
            jR.setCodice(0);   
            jR.setRisultato(servizi.creaEvento(evento));
            logger.debug(evento.toString() + " creato correttamente!");
        } catch(Exception ex) {
            logger.error("Eccezione: " + ex.getMessage());
            jR.setCodice(1);
            jR.setMessaggio("Errore nella creazione dell'evento!!");
        } 
        return jR;
    }
}
