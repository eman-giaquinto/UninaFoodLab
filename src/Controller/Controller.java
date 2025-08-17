package Controller;

import DAO.ChefDAO;
import DTO.Chef;
import Database.ComunicazioneDB;
import DatabaseException.DBExceptionConnessioneNonRiuscita;
import DatabaseException.DBExceptionCreazioneStatementFallita;
import DatabaseException.DBExceptionPasswordErrata;
import DatabaseException.DBExceptionRisultatoIndefinito;
import DatabaseException.DBExceptionUsernameNonTrovato;
import GUI.FinestraLogin;
import ImplementazioniDAO.ImplementazioneChefDAO;

public class Controller {
	// Database
	private ComunicazioneDB comunicazioneDB;
	
	// Finestre
	private FinestraLogin finestraLogin;
	
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
		
	chefLoggato = chefDAO.verificaAccesso(username, password);
	
	}
}
