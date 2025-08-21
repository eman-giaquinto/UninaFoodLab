package Controller;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JFrame;

import DAO.ChefDAO;
import DAO.CorsoDAO;
import DTO.Chef;
import DTO.Corso;
import Database.ComunicazioneDB;
import DatabaseException.DBExceptionConnessioneNonRiuscita;
import DatabaseException.DBExceptionCorsiNonTrovati;
import DatabaseException.DBExceptionCreazioneStatementFallita;
import DatabaseException.DBExceptionPasswordErrata;
import DatabaseException.DBExceptionRisultatoIndefinito;
import DatabaseException.DBExceptionUsernameNonTrovato;
import GUI.FinestraLogin;
import GUI.FinestraMenuPrincipale;
import GUI.FinestraVisualizzaCorsi;
import ImplementazioniDAO.ImplementazioneChefDAO;
import ImplementazioniDAO.ImplementazioneCorsoDAO;

public class Controller {
	// Database
	private ComunicazioneDB comunicazioneDB;
	
	// Finestre
	private FinestraLogin finestraLogin;
	private FinestraMenuPrincipale finestraMenuPrincipale;
	private FinestraVisualizzaCorsi finestraVisualizzaCorsi;

	
	//DAO
	private ChefDAO chefDAO;
    private CorsoDAO corsoDAO;

	
	//DTO
	private Chef chefLoggato;
	
	//Oggetti
    private ArrayList<Corso> corsiVisualizzati;

	

	public static void main(String[] args) {
		Controller c = new Controller();
	}
	
	public Controller() {
		
		// Inizializzo le finestre
		finestraLogin = new FinestraLogin(this);
		finestraMenuPrincipale = new FinestraMenuPrincipale(this);
		finestraVisualizzaCorsi = new FinestraVisualizzaCorsi(this);

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
    	corsoDAO = new ImplementazioneCorsoDAO(comunicazioneDB); 


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
	
	/* LOGICA TASTO VISUALIZZA */
	
	/* VISUALIZZA CORSI */
	
	public void showFinestraVisualizzaCorsi() {
		finestraVisualizzaCorsi.setFiltri();
		finestraVisualizzaCorsi.setVisible(true);
		finestraMenuPrincipale.setVisible(false);
		finestraVisualizzaCorsi.richiestaVisualizzaCorsi("Tutti");
		
	}
	
	public void richiestaConfermataVisualizzaCorsi(String filtroScelto) throws DBExceptionRisultatoIndefinito, DBExceptionCorsiNonTrovati{
		String usernameChef = chefLoggato.getUsername();
		corsiVisualizzati = corsoDAO.ottieniCorsi(usernameChef,filtroScelto);
		stampaTabellaCorsi();
	}
	
	private void stampaTabellaCorsi() {
		finestraVisualizzaCorsi.svuotaTabella();
		
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	    
		int numeroCorso=1;
		
		// Per ogni corso aggiungo una tupla alla tabella dei corsi visualizzati a schermo 
		for (Corso corso : corsiVisualizzati) {
			finestraVisualizzaCorsi.aggiungiTupla(
					corso.getIdCorso(),
	                numeroCorso++,
	                corso.getTipoDiCorso().getDescrizione(), 
	                corso.getDataInizio().format(formatter), 
	                corso.getFrequenzaSessione().getDescrizione(), 
	                corso.getDataFine().format(formatter)
	            );
		}
	}
	
	public String[] impostaDescrizioniTipiDiCorso() {
		String[] tipiDiCorso = Corso.ottieniDescrizioniTipiDiCorso();
	    return tipiDiCorso;
	}
	
	
}
