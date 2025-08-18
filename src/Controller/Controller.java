package Controller;

import javax.swing.JFrame;

import DAO.ChefDAO;
import DTO.Chef;
import Database.ComunicazioneDB;
import DatabaseException.DBExceptionConnessioneNonRiuscita;
import DatabaseException.DBExceptionCreazioneStatementFallita;
import DatabaseException.DBExceptionPasswordErrata;
import DatabaseException.DBExceptionRisultatoIndefinito;
import DatabaseException.DBExceptionUsernameNonTrovato;
import GUI.FinestraLogin;
import GUI.FinestraMenuPrincipale;
import ImplementazioniDAO.ImplementazioneChefDAO;

public class Controller {
	// Database
	private ComunicazioneDB comunicazioneDB;
	
	// Finestre
	private FinestraLogin finestraLogin;
	private FinestraMenuPrincipale finestraMenuPrincipale;
	
	//DAO
	private ChefDAO chefDAO;
	
	//DTO
	private Chef chefLoggato;
	

	public static void main(String[] args) {
		Controller c = new Controller();
	}
	
	public Controller() {
		
		// Inizializzo le finestre
		finestraLogin = new FinestraLogin(this);
		finestraMenuPrincipale = new FinestraMenuPrincipale(this);

		finestraLogin.setVisible(true);
		
		// Apro la comunicazione con il database
		try {
		comunicazioneDB = new ComunicazioneDB();
		} catch (DBExceptionCreazioneStatementFallita e) {
			finestraLogin.messaggioErrorPopUp(e.getMessaggioErroreSchermo());
		} catch (DBExceptionConnessioneNonRiuscita e) {
			finestraLogin.messaggioErrorPopUp(e.getMessaggioErroreSchermo());
		}
		
		// Inizializzo i DTO		
		chefLoggato = new Chef(null,null,null,null,null);
		
		// Inizializzo i DAO
		chefDAO = new ImplementazioneChefDAO(comunicazioneDB);

	}
	
	public void accesso(String username, String password) throws DBExceptionRisultatoIndefinito,
					DBExceptionUsernameNonTrovato, DBExceptionPasswordErrata {
		
		// Richiamo il metodo per provare l' accesso con i dati inseriti
		chefLoggato = chefDAO.verificaAccesso(username, password);
		
		/* Inizializzazione schermata principale*/
		String nomecognomeChef = chefLoggato.getPresentazione();
		
		finestraMenuPrincipale.impostaChef(nomecognomeChef);
		
		gotoMenuPrincipale(finestraLogin);
	}
	
	public void gotoMenuPrincipale(JFrame finestra) {
		finestraMenuPrincipale.setVisible(true);
		finestra.setVisible(false);
	}
	
}
