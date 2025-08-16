package Controller;

import Database.ComunicazioneDB;
import DatabaseException.DBExceptionConnessioneNonRiuscita;
import DatabaseException.DBExceptionCreazioneStatementFallita;
import GUI.FinestraLogin;

public class Controller {
	// Database
	private ComunicazioneDB comunicazioneDB;
	
	// Finestre
	private FinestraLogin finestraLogin;
	

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
		

	}

}
