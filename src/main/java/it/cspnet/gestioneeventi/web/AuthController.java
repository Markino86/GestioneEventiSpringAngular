package it.cspnet.gestioneeventi.web;

import it.cspnet.gestioneeventi.exception.ExistingUserException;
import it.cspnet.gestioneeventi.exception.NonConfermatoException;
import it.cspnet.gestioneeventi.exception.UserNotFoundException;
import it.cspnet.gestioneeventi.exception.WrongPasswordException;
import it.cspnet.gestioneeventi.model.JsonResult;
import it.cspnet.gestioneeventi.model.Utente;
import it.cspnet.gestioneeventi.servizi.ServiziEventi;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class AuthController {

    @Autowired
    private ServiziEventi servizi;
    
    private static Logger logger = Logger.getLogger(AuthController.class);

    public void setServizi(ServiziEventi servizi) {
        this.servizi = servizi;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody JsonResult login(@RequestBody Utente logInUtente, HttpServletRequest req) {
        JsonResult jR = new JsonResult();
        Utente utente = null;
        try {
            utente =  servizi.login(logInUtente.getUsername(), logInUtente.getPassword());
            jR.setRisultato(utente);
            logger.debug(utente.getUsername() + " loggato correttamente!");
            jR.setCodice(0);
        } catch (UserNotFoundException ex) {
            logger.error("Eccezione: " + ex.getMessage());
            jR.setCodice(1);
            jR.setMessaggio("Utente '" + logInUtente.getUsername() + "' non trovato!");
        } catch (WrongPasswordException ex) {
            logger.error("Eccezione: " + ex.getMessage());
            jR.setCodice(1);
            jR.setMessaggio("Password errata!");
        } catch (NonConfermatoException ex) {
            logger.error("Eccezione: " + ex.getMessage());
            jR.setCodice(1);
            jR.setMessaggio("mail Utente non confermata");
        } catch (Exception ex) {
            logger.error("Eccezione: " + ex.getMessage());
            jR.setCodice(1);
            jR.setMessaggio("Sito in manutenzione: riprovare più tardi");
        }
        return jR;
    }
    @RequestMapping(value = "/confermaReg", method = RequestMethod.GET)
    public String confermaUtente(HttpServletRequest req){
        String username = req.getParameter("username");
        servizi.setConfermato(username);
        return "redirect:/#/login";
    }
    
    @RequestMapping(value = "/registrati", method = RequestMethod.POST)
    public @ResponseBody JsonResult registrazioneUtente(@RequestBody Utente utente,HttpServletRequest req) {
        JsonResult jR = new JsonResult();
        Utente u = null;
        try {
            u = servizi.creaUtente(utente);
            jR.setRisultato(u);
            jR.setCodice(0);
        } catch (ExistingUserException ex) {
            logger.error("Eccezione: " + ex.getMessage());
            jR.setCodice(1);
            jR.setMessaggio("Utente già esistente");
        } catch (Exception ex) {
            logger.error("Eccezione: " + ex.getMessage());
            jR.setCodice(1);
            jR.setMessaggio("Sito in manutenzione!!");
        }
        return jR;
    }

}
