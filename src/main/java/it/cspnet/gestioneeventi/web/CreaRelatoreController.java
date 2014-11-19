package it.cspnet.gestioneeventi.web;

import it.cspnet.gestioneeventi.model.JsonResult;
import it.cspnet.gestioneeventi.model.Relatore;
import it.cspnet.gestioneeventi.servizi.ServiziEventi;
import java.util.Collection;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CreaRelatoreController {

    @Autowired
    private ServiziEventi servizi;
    
    private static Logger logger = Logger.getLogger(CreaRelatoreController.class);

    public void setServizi(ServiziEventi servizi) {
        this.servizi = servizi;
    }

    @RequestMapping(value = "/creaRelatore", method = RequestMethod.POST)
    public @ResponseBody JsonResult registrazioneRelatore(@RequestBody Relatore relatore) {
        JsonResult jR = new JsonResult();
        Relatore r = null;
        try {
            if (servizi.verificaRelatore(relatore) == null) {
                r = servizi.creaRelatore(relatore);
                jR.setRisultato(r);            
                jR.setCodice(0); 
                logger.debug(relatore.getNome() + relatore.getCognome() + " censito correttamente!");
            }
            else {
                
                jR.setCodice(1);
                jR.setMessaggio("Relatore già esistente!");
            }
        } catch (Exception ex) {
            logger.error("Eccezione: " + ex.getMessage());
            jR.setCodice(1);
            jR.setMessaggio("Sito in manutenzione!");
        }
        return jR;
    }
    
    @RequestMapping(value = "/getAllRelatori", method = RequestMethod.GET)
    public @ResponseBody JsonResult getAllRelatori() {
        JsonResult jR = new JsonResult();
        Collection<Relatore> relatori = null;
        try {
            relatori = servizi.listaRelatori();
            jR.setRisultato(relatori);            
            jR.setCodice(0);
        } catch (Exception ex) {
            logger.error("Eccezione: " + ex.getMessage());
            jR.setCodice(1);
            jR.setMessaggio("Nessun relatore censito!");
        }
        return jR;
    }
}
