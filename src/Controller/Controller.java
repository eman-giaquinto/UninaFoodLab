package Controller;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JFrame;

import DAO.ChefDAO;
import DAO.CorsoDAO;
import DAO.SessionePraticaDAO;
import DTO.Chef;
import DTO.Corso;
import DTO.SessionePratica;
import Database.ComunicazioneDB;
import DatabaseException.DBExceptionConnessioneNonRiuscita;
import DatabaseException.DBExceptionCorsiNonTrovati;
import DatabaseException.DBExceptionCreazioneStatementFallita;
import DatabaseException.DBExceptionPasswordErrata;
import DatabaseException.DBExceptionRisultatoIndefinito;
import DatabaseException.DBExceptionSessioniPraticheNonTrovate;
import DatabaseException.DBExceptionUsernameNonTrovato;
import GUI.FinestraLogin;
import GUI.FinestraMenuPrincipale;
import GUI.FinestraSceltaTipoDiSessione;
import GUI.FinestraVisualizzaCorsi;
import GUI.FinestraVisualizzaSessioniPratiche;
import ImplementazioniDAO.ImplementazioneChefDAO;
import ImplementazioniDAO.ImplementazioneCorsoDAO;
import ImplementazioniDAO.ImplementazioneSessionePraticaDAO;

public class Controller {
	// Database
	private ComunicazioneDB comunicazioneDB;
	
	// Finestre
	private FinestraLogin finestraLogin;
	private FinestraMenuPrincipale finestraMenuPrincipale;
	private FinestraVisualizzaCorsi finestraVisualizzaCorsi;
	private FinestraSceltaTipoDiSessione finestraSceltaTipoDiSessione;
	private FinestraVisualizzaSessioniPratiche finestraVisualizzaSessioniPratiche;

	
	//DAO
	private ChefDAO chefDAO;
    private CorsoDAO corsoDAO;
    private SessionePraticaDAO sessionePraticaDAO;


	
	//DTO
	private Chef chefLoggato;
	
	//Oggetti
    private ArrayList<Corso> corsiVisualizzati;
    private DateTimeFormatter formatoDataItaliana = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private int idCorsoSelezionato;
    private ArrayList<SessionePratica> sessioniPraticheVisualizzate;
    private DateTimeFormatter formatoOraItaliana = DateTimeFormatter.ofPattern("HH:mm");





	

	public static void main(String[] args) {
		Controller c = new Controller();
	}
	
	public Controller() {
		
		// Inizializzo le finestre
		finestraLogin = new FinestraLogin(this);
		finestraMenuPrincipale = new FinestraMenuPrincipale(this);
		finestraVisualizzaCorsi = new FinestraVisualizzaCorsi(this);
		finestraSceltaTipoDiSessione = new FinestraSceltaTipoDiSessione(this);
		finestraVisualizzaSessioniPratiche = new FinestraVisualizzaSessioniPratiche(this);

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
        sessionePraticaDAO = new ImplementazioneSessionePraticaDAO(comunicazioneDB);


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
		finestraVisualizzaCorsi.setFiltriTipiDiCorso();
		finestraVisualizzaCorsi.setVisible(true);
		finestraMenuPrincipale.setVisible(false);
		finestraVisualizzaCorsi.richiestaVisualizzaCorsi("Tutti");
	}
	
	public void richiestaConfermataVisualizzaCorsi(String filtroScelto) throws DBExceptionRisultatoIndefinito,
					DBExceptionCorsiNonTrovati{
		
		String usernameChef = chefLoggato.getUsername();
		corsiVisualizzati = corsoDAO.ottieniCorsi(usernameChef,filtroScelto);
		// Se non vengono rilanciate eccezioni allora procedo a stampare a schermo i corsi ricavati
		stampaTabellaCorsi();
	}
	
	private void stampaTabellaCorsi() {
		finestraVisualizzaCorsi.svuotaTabella();
			    
		int numeroCorso=1;
		
		// Per ogni corso aggiungo una tupla alla tabella dei corsi visualizzati a schermo 
		for (Corso corso : corsiVisualizzati) {
			finestraVisualizzaCorsi.aggiungiTupla(
					corso.getIdCorso(),
	                numeroCorso++,
	                corso.getTipoDiCorso().getDescrizione(), 
	                corso.getDataInizio().format(formatoDataItaliana), 
	                corso.getFrequenzaSessione().getDescrizione(), 
	                corso.getDataFine().format(formatoDataItaliana)
	            );
		}
	}
	
	public String[] impostaDescrizioniTipiDiCorso() {
		//Imposta l'elenco dei tipi di corsi per filtrare i corsi
		String[] tipiDiCorso = Corso.ottieniDescrizioniTipiDiCorso();
	    return tipiDiCorso;
	}
	
	/* VISUALIZZA SCELTA TIPO DI SESSIONE */
	
	public void richiestaMostraSessioniCorsoSelezionato(int idCorsoRichiesto) {
		idCorsoSelezionato=idCorsoRichiesto;
	}
	
	public void showFinestraSceltaTipoDiSessione() {
		finestraSceltaTipoDiSessione.setVisible(true);
	}
	
	/* VISUALIZZA SESSIONI PRATICHE  */

	public void backToCorsiVisualizzati(JFrame finestra) {
		finestraVisualizzaCorsi.setVisible(true);
		finestra.setVisible(false);
	}
	
	public void showFinestraVisualizzaSessioniPratiche() {
		finestraVisualizzaSessioniPratiche.setVisible(true);
		finestraVisualizzaCorsi.setVisible(false);
		finestraSceltaTipoDiSessione.setVisible(false);
		finestraVisualizzaSessioniPratiche.richiestaVisualizzaSessioniPratiche();
	}
	
	public void richiestaConfermataVisualizzaSessioniPratiche() throws DBExceptionRisultatoIndefinito, 
					DBExceptionSessioniPraticheNonTrovate{
		
		sessioniPraticheVisualizzate = sessionePraticaDAO.ottieniSessioniPratiche(idCorsoSelezionato);
		// Se non vengono rilanciate eccezioni allora procedo a stampare a schermo le sessioni pratiche ricavate
		stampaTabellaSessioniPratiche();
	}
	
	private void stampaTabellaSessioniPratiche() {
		finestraVisualizzaSessioniPratiche.svuotaTabella();
	    
		int numeroSessionePratica=1;
		// Per ogni sessione pratica aggiungo una tupla alla tabella visualizzata a schermo 
		for (SessionePratica sessionepratica : sessioniPraticheVisualizzate) {
			finestraVisualizzaSessioniPratiche.aggiungiTupla(
					sessionepratica.getIdSessionePratica(),
					numeroSessionePratica++,
	                sessionepratica.getNumeroAdesioni(),
	                sessionepratica.getDataSessione().format(formatoDataItaliana),
	                sessionepratica.getOrarioInizio().toLocalTime().format(formatoOraItaliana),
	                sessionepratica.getOrarioFine().toLocalTime().format(formatoOraItaliana),
					sessionepratica.getFkCorso()
	            );
		}
	}
	
	/* VISUALIZZA RICETTE DELLE SESSIONI PRATICHE */
	
	
	
	
}
