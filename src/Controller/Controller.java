package Controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JFrame;

import DAO.ChefDAO;
import DAO.CorsoDAO;
import DAO.RicettaDAO;
import DAO.SessioneOnlineDAO;
import DAO.SessionePraticaDAO;
import DTO.Chef;
import DTO.Corso;
import DTO.Corso.FrequenzaSessione;
import DTO.Corso.TipoCorso;
import DTO.Ricetta;
import DTO.SessioneOnline;
import DTO.SessioneOnline.Piattaforma;
import DTO.SessionePratica;
import Database.ComunicazioneDB;
import DatabaseException.DBExceptionConnessioneNonRiuscita;
import DatabaseException.DBExceptionCorsiNonTrovati;
import DatabaseException.DBExceptionCreazioneStatementFallita;
import DatabaseException.DBExceptionDataInizioMaggioreDataFine;
import DatabaseException.DBExceptionDataSessioneFuoriRange;
import DatabaseException.DBExceptionDataSessioneOnlineDiversaMensile;
import DatabaseException.DBExceptionDataSessioneOnlineDiversaSettimanale;
import DatabaseException.DBExceptionDataSessionePraticaDiversaMensile;
import DatabaseException.DBExceptionOperazioneQueryDML;
import DatabaseException.DBExceptionPasswordErrata;
import DatabaseException.DBExceptionRicettaGiàAssociata;
import DatabaseException.DBExceptionRicetteNonTrovate;
import DatabaseException.DBExceptionRisultatoIndefinito;
import DatabaseException.DBExceptionSessioneOnlineDuplicata;
import DatabaseException.DBExceptionSessioneOnlineLinkDuplicato;
import DatabaseException.DBExceptionSessionePraticaDuplicata;
import DatabaseException.DBExceptionSessioniOnlineNonTrovate;
import DatabaseException.DBExceptionSessioniPraticheNonTrovate;
import DatabaseException.DBExceptionUsernameNonTrovato;
import GUI.FinestraAggiungiCorso;
import GUI.FinestraAggiungiRicettaSessionePratica;
import GUI.FinestraAggiungiSessioneOnline;
import GUI.FinestraAggiungiSessionePratica;
import GUI.FinestraLogin;
import GUI.FinestraMenuPrincipale;
import GUI.FinestraSceltaAggiungi;
import GUI.FinestraSceltaTipoDiSessione;
import GUI.FinestraSelezionaCorso;
import GUI.FinestraSelezionaSessionePratica;
import GUI.FinestraVisualizzaCorsi;
import GUI.FinestraVisualizzaRicetteSessionePratica;
import GUI.FinestraVisualizzaSessioniOnline;
import GUI.FinestraVisualizzaSessioniPratiche;
import ImplementazioniDAO.ImplementazioneChefDAO;
import ImplementazioniDAO.ImplementazioneCorsoDAO;
import ImplementazioniDAO.ImplementazioneRicettaDAO;
import ImplementazioniDAO.ImplementazioneSessioneOnlineDAO;
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
	private FinestraVisualizzaRicetteSessionePratica finestraVisualizzaRicetteSessionePratica;
	private FinestraSceltaAggiungi finestraSceltaAggiungi;
	private FinestraVisualizzaSessioniOnline finestraVisualizzaSessioniOnline;
	private FinestraAggiungiCorso finestraAggiungiCorso;
	private FinestraSelezionaCorso finestraSelezionaCorso;
	private FinestraAggiungiSessionePratica finestraAggiungiSessionePratica;
	private FinestraAggiungiSessioneOnline finestraAggiungiSessioneOnline;
	private FinestraSelezionaSessionePratica finestraSelezionaSessionePratica;
	private FinestraAggiungiRicettaSessionePratica finestraAggiungiRicettaSessionePratica;




	
	//DAO
	private ChefDAO chefDAO;
    private CorsoDAO corsoDAO;
    private SessionePraticaDAO sessionePraticaDAO;
    private RicettaDAO ricettaDAO;
    private SessioneOnlineDAO sessioneOnlineDAO;



	
	//DTO
	private Chef chefAutenticato;
	private Corso corsoScelto;
	private SessionePratica sessionePraticaScelta;


	
	//Oggetti
    private DateTimeFormatter formatoDataItaliana = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private DateTimeFormatter formatoOraItaliana = DateTimeFormatter.ofPattern("HH:mm");
    private ArrayList<String> listaRicette  = new ArrayList<String>();







	public static void main(String[] args) {
		Controller c = new Controller();
		
		// aggiungere un metodo per far si che la java swing giri su un thread 
		
		// aggiungere un metodo per chiudere la connessione al db
		
	}
	
	public Controller() {
		
		// Inizializzo le finestre
		finestraLogin = new FinestraLogin(this);
		finestraMenuPrincipale = new FinestraMenuPrincipale(this);
		finestraVisualizzaCorsi = new FinestraVisualizzaCorsi(this);
		finestraSceltaTipoDiSessione = new FinestraSceltaTipoDiSessione(this);
		finestraVisualizzaSessioniPratiche = new FinestraVisualizzaSessioniPratiche(this);
		finestraVisualizzaRicetteSessionePratica = new FinestraVisualizzaRicetteSessionePratica(this);
		finestraSceltaAggiungi = new FinestraSceltaAggiungi(this);
		finestraVisualizzaSessioniOnline = new FinestraVisualizzaSessioniOnline(this);
		finestraAggiungiCorso = new FinestraAggiungiCorso(this);
		finestraSelezionaCorso = new FinestraSelezionaCorso(this);
		finestraAggiungiSessionePratica = new FinestraAggiungiSessionePratica(this);
		finestraAggiungiSessioneOnline = new FinestraAggiungiSessioneOnline(this);
		finestraSelezionaSessionePratica = new FinestraSelezionaSessionePratica(this);
		finestraAggiungiRicettaSessionePratica = new FinestraAggiungiRicettaSessionePratica(this);




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
		chefAutenticato = new Chef(null,null,null,null);
		corsoScelto = new Corso(0,null,null,null,null,null);
		sessionePraticaScelta = new SessionePratica(0,0,null,null,null,null);
		
		// Inizializzo i DAO
		chefDAO = new ImplementazioneChefDAO(comunicazioneDB);
    	corsoDAO = new ImplementazioneCorsoDAO(comunicazioneDB); 
        sessionePraticaDAO = new ImplementazioneSessionePraticaDAO(comunicazioneDB);
        ricettaDAO = new ImplementazioneRicettaDAO(comunicazioneDB);
        sessioneOnlineDAO = new ImplementazioneSessioneOnlineDAO(comunicazioneDB);



	}
	
	public void accesso(String username, String password) throws DBExceptionRisultatoIndefinito,
					DBExceptionUsernameNonTrovato, DBExceptionPasswordErrata {
		
		Chef chefDaVerificare = new Chef(username,password);
		
		// Richiamo il metodo per provare l' accesso con i dati inseriti
		chefAutenticato = chefDAO.verificaAccesso(chefDaVerificare);
		
		/* Inizializzazione schermata principale*/
		String nomecognomeChef = chefAutenticato.getPresentazione();
		
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
	
	public void richiestaVisualizzaCorsiSchermo(String filtroScelto) throws DBExceptionRisultatoIndefinito,
	DBExceptionCorsiNonTrovati{
		
	    ArrayList<Corso> corsiVisualizzati = corsoDAO.ottieniCorsiChef(chefAutenticato,filtroScelto);
	    chefAutenticato.setCorsiAssociati(corsiVisualizzati);
		// Se non vengono rilanciate eccezioni allora procedo a stampare a schermo i corsi ricavati
		stampaTabellaCorsi();
	}
	
	private void stampaTabellaCorsi() {
		finestraVisualizzaCorsi.svuotaTabella();
			    
		int numeroCorso=1;
		
		// Per ogni corso aggiungo una tupla alla tabella dei corsi visualizzati a schermo 
		for (Corso corso : chefAutenticato.getCorsiAssociati()) {
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
		//Imposta l'elenco dei tipi di corsi per filtrare i corsi dalla schermata di visualizzazione
		String[] tipiDiCorso = Corso.ottieniDescrizioniTipiDiCorsi("Tutti");
	    return tipiDiCorso;
	}
	
	/* VISUALIZZA SCELTA TIPO DI SESSIONE */
	
	public void richiestaMostraSessioniCorsoSelezionato(int idCorsoRichiesto) {
		corsoScelto.setIdCorso(idCorsoRichiesto);
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
	
	public void richiestaVisualizzaSessioniPraticheSchermo() throws DBExceptionRisultatoIndefinito, 
	DBExceptionSessioniPraticheNonTrovate{
		
	    ArrayList<SessionePratica> sessioniPraticheVisualizzate = sessionePraticaDAO.ottieniSessioniPratiche(corsoScelto);
	    corsoScelto.setSessioniPratiche(sessioniPraticheVisualizzate);
		// Se non vengono rilanciate eccezioni allora procedo a stampare a schermo le sessioni pratiche ricavate
		stampaTabellaSessioniPratiche();
	}
	
	private void stampaTabellaSessioniPratiche() {
		finestraVisualizzaSessioniPratiche.svuotaTabella();
	    
		int numeroSessionePratica=1;
		// Per ogni sessione pratica aggiungo una tupla alla tabella visualizzata a schermo 
		for (SessionePratica sessionepratica : corsoScelto.getSessioniPratiche()) {
			finestraVisualizzaSessioniPratiche.aggiungiTupla(
					sessionepratica.getIdSessionePratica(),
					numeroSessionePratica++,
	                sessionepratica.getNumeroAdesioni(),
	                sessionepratica.getDataSessione().format(formatoDataItaliana),
	                sessionepratica.getOrarioInizio().format(formatoOraItaliana),
	                sessionepratica.getOrarioFine().format(formatoOraItaliana)
	                );
		}
	}
	
	/* VISUALIZZA RICETTE DELLE SESSIONI PRATICHE */
	
	public void richiestaMostraRicetteSessioneSelezionata(int idSessioneRichiesta) {
		sessionePraticaScelta.setIdSessionePratica(idSessioneRichiesta);
	}
	
	public void showFinestraVisualizzaRicetteSessionePratica() {
		finestraVisualizzaRicetteSessionePratica.setVisible(true);
		finestraVisualizzaSessioniPratiche.setVisible(false);
		finestraVisualizzaRicetteSessionePratica.richiestaVisualizzaRicette();
	}
	
	public void richiestaVisualizzaRicetteSchermo() throws DBExceptionRisultatoIndefinito, DBExceptionRicetteNonTrovate{
		
		ArrayList<Ricetta> ricetteSessionePraticaVisualizzate; 
		ricetteSessionePraticaVisualizzate = ricettaDAO.ottieniRicetteSessionePratica(sessionePraticaScelta);
		sessionePraticaScelta.setRicetteAssociate(ricetteSessionePraticaVisualizzate);
		// Se non vengono rilanciate eccezioni allora procedo a stampare a schermo le sessioni pratiche ricavate
		stampaTabellaRicetteSessionePratica();
	}
	
	private void stampaTabellaRicetteSessionePratica() {
		finestraVisualizzaRicetteSessionePratica.svuotaTabella();
	    
		int numeroRicettaSessionePratica=1;
		// Per ogni ricetta aggiungo una tupla alla tabella visualizzata a schermo 
		for (Ricetta ricette : sessionePraticaScelta.getRicetteAssociate()) {
			finestraVisualizzaRicetteSessionePratica.aggiungiTupla(
					numeroRicettaSessionePratica++,
					ricette.getNome(),
					ricette.getDescrizione(),
					ricette.getGradoDifficoltà().getDescrizione()
	            );
		}
	}
	
	public void backToFinestraVisualizzaSessioniPratiche() {
		finestraVisualizzaSessioniPratiche.setVisible(true);
		finestraVisualizzaRicetteSessionePratica.setVisible(false);
	}
	
	/* VISUALIZZA SESSIONI ONLINE  */
	
	public void showFinestraVisualizzaSessioniOnline() {
		finestraVisualizzaSessioniOnline.setVisible(true);
		finestraVisualizzaCorsi.setVisible(false);
		finestraSceltaTipoDiSessione.setVisible(false);
		finestraVisualizzaSessioniOnline.richiestaVisualizzaSessioniOnline();
	}
	
	public void richiestaVisualizzaSessioniOnlineSchermo() throws DBExceptionRisultatoIndefinito,
	DBExceptionSessioniOnlineNonTrovate  {
	    ArrayList<SessioneOnline> sessioniOnlineVisualizzate = sessioneOnlineDAO.ottieniSessioniOnline(corsoScelto);
	    corsoScelto.setSessioniOnline(sessioniOnlineVisualizzate);
		// Se non vengono rilanciate eccezioni allora procedo a stampare a schermo le sessioni pratiche ricavate
		stampaTabellaSessioniOnline();
	}
	
	private void stampaTabellaSessioniOnline() {
		finestraVisualizzaSessioniOnline.svuotaTabella();
		
		int numeroSessioneOnline=1;
		// Per ogni sessione online aggiungo una tupla alla tabella visualizzata a schermo 
		for (SessioneOnline sessioneonline : corsoScelto.getSessioniOnline()) {
			finestraVisualizzaSessioniOnline.aggiungiTupla(
					sessioneonline.getIdSessioneOnline(),
					numeroSessioneOnline++,
					sessioneonline.getPiattaforma().getDescrizione(),
					sessioneonline.getDataSessione().format(formatoDataItaliana),
					sessioneonline.getOrarioInizio().format(formatoOraItaliana),
					sessioneonline.getOrarioFine().format(formatoOraItaliana),
					sessioneonline.getLink()
	            );
		}
	}
	
	/* LOGICA TASTO AGGIUNGI */
	
	/* AGGIUNGI CORSI */
	public void showFinestraSceltaAggiungi() {
		finestraSceltaAggiungi.setVisible(true);
		finestraMenuPrincipale.setVisible(false);
	}

	public void showFinestraAggiungiCorso() {
		// Prepara la schermata con i relativi campi di inserimento 
		finestraAggiungiCorso.setCampiInserimento();
		finestraAggiungiCorso.setVisible(true);
		finestraSceltaAggiungi.setVisible(false);
	}
	
	public void backToFinestraSceltaAggiungi(JFrame finestra) {
		finestraSceltaAggiungi.setVisible(true);
		finestra.setVisible(false);
	}
	
	public String[] getDescrizioniTipiDiCorso() {
		//Imposta l'elenco dei tipi di corsi per la schermata di inserimento
		String[] tipiDiCorso = Corso.ottieniDescrizioniTipiDiCorsi("Tutte");
	    return tipiDiCorso;
	}
	
	public String[] getDescrizioniFrequenzeSessioni() {
		//Imposta l'elenco delle frequenze delle sessioni per la schermata di inserimento
		String[] frquenzeSessioni = Corso.ottieniDescrizioniFrequenzeSessioni();
	    return frquenzeSessioni;
	}
	
	public void richiestaCreaCorso(String tipoDiCorsoInserito,LocalDate dataDiInizioInserita,LocalDate dataDiFineInserita,
	String frequenzaSessioneInserita) throws DBExceptionOperazioneQueryDML,DBExceptionDataInizioMaggioreDataFine{
		
		TipoCorso tipoDiCorso = TipoCorso.ottieniTipoDiCorsoFormattato(tipoDiCorsoInserito);        
        
        FrequenzaSessione frequenzaSessione = FrequenzaSessione.ottieniFrequenzaSessioneFormattata(frequenzaSessioneInserita);        

		Corso corsotemp = new Corso(tipoDiCorso, dataDiInizioInserita, frequenzaSessione, dataDiFineInserita, chefAutenticato);
		
		corsoDAO.creaCorso(corsotemp);
		
	}
	
	/* AGGIUNGI SESSIONE PRATICA - ONLINE E AGGIUNGI RICETTE ALLA SESSIONE PRATICA */
	
	public void showFinestraSelezionaCorso(String modalitàAccesso){
		
		switch(modalitàAccesso){
			case "pratica":
			finestraSelezionaCorso.setModalitàAccesso(modalitàAccesso);
			break;
			case "online":
			finestraSelezionaCorso.setModalitàAccesso(modalitàAccesso);
			break;
			case "ricetta":
			finestraSelezionaCorso.setModalitàAccesso(modalitàAccesso);
			break;
		}
		
		finestraSelezionaCorso.setFiltriTipiDiCorso();
		finestraSelezionaCorso.setVisible(true);
		finestraSceltaAggiungi.setVisible(false);
		finestraSelezionaCorso.richiestaSelezionaCorso("Tutti");
	}
	
	public void richiestaSelezionaCorsoAggiungi(String filtroScelto) throws DBExceptionRisultatoIndefinito,
	DBExceptionCorsiNonTrovati{
		
	    ArrayList<Corso> corsiVisualizzati = corsoDAO.ottieniCorsiChef(chefAutenticato,filtroScelto);
	    chefAutenticato.setCorsiAssociati(corsiVisualizzati);
		// Se non vengono rilanciate eccezioni allora procedo a stampare a schermo i corsi ricavati
		stampaTabellaSelezionaCorso();
	}
	
	private void stampaTabellaSelezionaCorso() {
		finestraSelezionaCorso.svuotaTabella();
			    
		int numeroCorso=1;
		
		// Per ogni corso aggiungo una tupla alla tabella dei corsi visualizzati a schermo 
		for (Corso corso : chefAutenticato.getCorsiAssociati()) {
			finestraSelezionaCorso.aggiungiTupla(
					corso.getIdCorso(),
	                numeroCorso++,
	                corso.getTipoDiCorso().getDescrizione(), 
	                corso.getDataInizio().format(formatoDataItaliana), 
	                corso.getFrequenzaSessione().getDescrizione(), 
	                corso.getDataFine().format(formatoDataItaliana)
	            );
		}
	}
	
	public void impostaCorsoSelezionato(int idCorsoRichiesto) {
		corsoScelto.setIdCorso(idCorsoRichiesto);
	}
	
	public void showFinestraAggiungiSessionePratica(){
		getRangeDateCorsoScelto();
		
	    LocalDate dataInizioCorsoSelezionato = corsoScelto.getDataInizio();
	    LocalDate dataFineCorsoSelezionato = corsoScelto.getDataFine();
	    String frequenzaSessioneCorsoSelezionato = corsoScelto.getFrequenzaSessione().getDescrizione();
		
		
		finestraAggiungiSessionePratica.setRangeDataSessionePratica(dataInizioCorsoSelezionato,dataFineCorsoSelezionato,
																	frequenzaSessioneCorsoSelezionato);
		finestraAggiungiSessionePratica.setCampiInserimento();
		finestraAggiungiSessionePratica.setVisible(true);
		finestraSelezionaCorso.setVisible(false);
	}
	
	private void getRangeDateCorsoScelto() {
		for (Corso corso : chefAutenticato.getCorsiAssociati()) {
			if(corso.getIdCorso()==corsoScelto.getIdCorso()) {
				corsoScelto.setDataInizio(corso.getDataInizio());
				corsoScelto.setDataFine(corso.getDataFine());
				corsoScelto.setFrequenzaSessione(corso.getFrequenzaSessione());
			}
		}
	}
	
	public void richiestaCreaSessionePratica(LocalDate dataSessioneInserita,LocalTime orarioInizioInserito,
	LocalTime orarioFineInserito,int numeroAdesioniInserito) throws DBExceptionOperazioneQueryDML, 
	DBExceptionDataSessioneFuoriRange,DBExceptionSessionePraticaDuplicata, DBExceptionDataSessionePraticaDiversaMensile{

		SessionePratica sessionePraticaTemp = new SessionePratica(
				numeroAdesioniInserito, dataSessioneInserita, orarioInizioInserito, orarioFineInserito, corsoScelto);
				
		sessionePraticaDAO.creaSessionePratica(sessionePraticaTemp);
		
	}
	
	public void backToSelezionaCorso(JFrame finestra) {
		finestraSelezionaCorso.setVisible(true);
		finestra.setVisible(false);
	}
	
	/* AGGIUNGI SESSIONE ONLINE PARTE AGGIUNTIVA */
	public void showFinestraAggiungiSessioneOnline(){
		getRangeDateCorsoScelto();
		
		LocalDate dataInizioCorsoSelezionato = corsoScelto.getDataInizio();
	    LocalDate dataFineCorsoSelezionato = corsoScelto.getDataFine();
	    String frequenzaSessioneCorsoSelezionato = corsoScelto.getFrequenzaSessione().getDescrizione();
	    
		finestraAggiungiSessioneOnline.setRangeDataSessionePratica(dataInizioCorsoSelezionato,dataFineCorsoSelezionato,
																	frequenzaSessioneCorsoSelezionato);
		finestraAggiungiSessioneOnline.setCampiInserimento();
		finestraAggiungiSessioneOnline.setVisible(true);
		finestraSelezionaCorso.setVisible(false);
	}
	
	public String[] getDescrizioniTipiDiPiattaforma() {
		String[] tipiDiPiattaforma = SessioneOnline.ottieniDescrizioniPiattaforme();
	    return tipiDiPiattaforma;
	}
	
	public void richiestaCreaSessioneOnline(LocalDate dataSessioneInserita,LocalTime orarioInizioInserito,
	LocalTime orarioFineInserito, String piattaformaInserita,String linkInserito) throws DBExceptionOperazioneQueryDML,
	DBExceptionSessioneOnlineDuplicata, DBExceptionDataSessioneOnlineDiversaSettimanale,
	DBExceptionDataSessioneOnlineDiversaMensile,DBExceptionSessioneOnlineLinkDuplicato{
		
		Piattaforma piattaformaRicavata = Piattaforma.ottieniPiattaformaFormattata(piattaformaInserita);        
		
		SessioneOnline sessioneOnlineDaAggiungere = new SessioneOnline(
				piattaformaRicavata, dataSessioneInserita, orarioInizioInserito, orarioFineInserito, linkInserito, corsoScelto);
		
		sessioneOnlineDAO.creaSessioneOnline(sessioneOnlineDaAggiungere);
	}
	
	/* AGGIUNGI RICETTA ALLA SESSIONE PRATICA PARTE AGGIUNTIVA */
	public void showFinestraSelezionaSessionePratica() {
		finestraSelezionaSessionePratica.setVisible(true);
		finestraSelezionaCorso.setVisible(false);
		finestraSelezionaSessionePratica.richiestaVisualizzaSessioniPratiche();	
	}
	
	public void richiestaSelezionaSessionePraticaSchermo() throws DBExceptionRisultatoIndefinito, DBExceptionSessioniPraticheNonTrovate{
	    ArrayList<SessionePratica> sessioniPraticheVisualizzate = sessionePraticaDAO.ottieniSessioniPratiche(corsoScelto);
	    corsoScelto.setSessioniPratiche(sessioniPraticheVisualizzate);
		// Se non vengono rilanciate eccezioni allora procedo a stampare a schermo le sessioni pratiche ricavate
		stampaTabellaSelezionaSessioniPratiche();
	}
	
	private void stampaTabellaSelezionaSessioniPratiche() {
		finestraSelezionaSessionePratica.svuotaTabella();
		
		int numeroSessionePratica=1;
		// Per ogni sessione pratica aggiungo una tupla alla tabella visualizzata a schermo 
		for (SessionePratica sessionepratica : corsoScelto.getSessioniPratiche()) {
			finestraSelezionaSessionePratica.aggiungiTupla(
					sessionepratica.getIdSessionePratica(),
					numeroSessionePratica++,
	                sessionepratica.getNumeroAdesioni(),
	                sessionepratica.getDataSessione().format(formatoDataItaliana),
	                sessionepratica.getOrarioInizio().format(formatoOraItaliana),
	                sessionepratica.getOrarioFine().format(formatoOraItaliana)
	                );
		}
	}
	
	public void impostaSessionePraticaSelezionata(int idSessionePraticaRichiesta) {
		sessionePraticaScelta.setIdSessionePratica(idSessionePraticaRichiesta);
	}
	
	public void showFinestraAggiungiRicettaSessionePratica () {
		finestraAggiungiRicettaSessionePratica.setVisible(true);
		finestraSelezionaSessionePratica.setVisible(false);
		finestraAggiungiRicettaSessionePratica.setListaRicette();
		finestraAggiungiRicettaSessionePratica.setCampoRicetta();
	}
	
	public ArrayList<String> impostaElencoRicette() throws DBExceptionRisultatoIndefinito {
		// Se è la prima volta che recupero tutte le ricette dal DB l' array sarà vuoto
		if(listaRicette.isEmpty()) {
			listaRicette=ricettaDAO.ottieniTutteLeRicette();
		}
		return listaRicette;
	}
	
	public void backToFinestraSelezionaSessionePratica() {
		finestraSelezionaSessionePratica.setVisible(true);
		finestraAggiungiRicettaSessionePratica.setVisible(false);
	}
	
	public void aggiungiRicettaSessionePratica(String nomeRicettaRicavata) throws DBExceptionOperazioneQueryDML, 
	DBExceptionRicettaGiàAssociata {
		
		Ricetta tempRicetta = new Ricetta(nomeRicettaRicavata);
		
		ricettaDAO.aggiungiRicettaSessionePraticaAssociata(sessionePraticaScelta, tempRicetta);
	}
	
}
