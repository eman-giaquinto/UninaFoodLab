package ImplementazioniDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import DAO.SessionePraticaDAO;
import DTO.Corso;
import DTO.SessionePratica;
import Database.ComunicazioneDB;
import DatabaseException.DBExceptionDataSessioneFuoriRange;
import DatabaseException.DBExceptionDataSessionePraticaDiversaMensile;
import DatabaseException.DBExceptionOperazioneQueryDML;
import DatabaseException.DBExceptionRisultatoIndefinito;
import DatabaseException.DBExceptionSessionePraticaDuplicata;
import DatabaseException.DBExceptionSessioniPraticheNonTrovate;

public class ImplementazioneSessionePraticaDAO implements SessionePraticaDAO{
	 private ComunicazioneDB comunicazioneDB; 
	 private ResultSet risultato; 
	 
	private static final String VIOLAZIONE_VINCOLO_DATASESSIONE_FUORI_RANGE = "SP001";
	private static final String VIOLAZIONE_VINCOLO_SESSIONE_DUPLICATA = "SP002"; 
	private static final String VIOLAZIONE_VINCOLO_DATASESSIONE_DIVERSA_MENSILE = "SP003"; 

	
	 public ImplementazioneSessionePraticaDAO(ComunicazioneDB comunicazioneDB) {
		 this.comunicazioneDB=comunicazioneDB;
	 }

	@Override
	public ArrayList<SessionePratica> ottieniSessioniPratiche(Corso corsoSelezionato)
			throws DBExceptionRisultatoIndefinito, DBExceptionSessioniPraticheNonTrovate {
		
		ArrayList<SessionePratica> sessioniPratiche = new ArrayList<>();
		
		int idCorsoRicavato = corsoSelezionato.getIdCorso();

	    String comando = "SELECT * FROM sessione_pratica "
	    			   + "WHERE fkcorso =  "  + idCorsoRicavato + "";
	    	   
	    // Mando il comando e prendo il risultato della query
	    risultato = comunicazioneDB.comunicazioneDBQuery(comando);

	    try {
	        // Controlla se il ResultSet è vuoto prima di iniziare a leggere
	        if (!risultato.isBeforeFirst()) { 
	            // Nessuna riga trovata, la ricerca non ha prodotto risultati.
	            throw new DBExceptionSessioniPraticheNonTrovate();
	        }

	        // Itera su tutte le righe restituite dalla query
	        while (comunicazioneDB.prossimaTupla()) {
	        	
	        	int idSessionePratica = risultato.getInt("idsessionepratica");
	        	
	        	int numeroAdesioni = risultato.getInt("numeroadesioni");
	        	
	            LocalDate dataSessione = risultato.getDate("datasessione").toLocalDate();

	        	LocalTime orarioInizio = risultato.getTime("orarioinizio").toLocalTime();
	        	
	        	LocalTime orarioFine = risultato.getTime("orariofine").toLocalTime();

	            // Crea un nuovo oggetto SessionePratica con i dati estratti
	            SessionePratica tempSessionePratica = new SessionePratica(
	            		idSessionePratica,numeroAdesioni,dataSessione,orarioInizio,orarioFine,corsoSelezionato);
	            
	            // Aggiungi l'oggetto Sessione pratica alla lista
	            sessioniPratiche.add(tempSessionePratica);
	        }
	    } catch (SQLException e) {
	        // Errore durante l'accesso ai dati del ResultSet
	        throw new DBExceptionRisultatoIndefinito();
	    } 
	    
	   
	    return sessioniPratiche;
	}

	@Override
	public void creaSessionePratica(SessionePratica sessionePraticaDaAggiungere) throws DBExceptionOperazioneQueryDML,
	DBExceptionDataSessioneFuoriRange,DBExceptionSessionePraticaDuplicata, DBExceptionDataSessionePraticaDiversaMensile {
		
		int numeroAdesioniRicavato = sessionePraticaDaAggiungere.getNumeroAdesioni();
		
		LocalDate dataSessioneRicavata = sessionePraticaDaAggiungere.getDataSessione();
		
		LocalTime orarioInizioRicavato = sessionePraticaDaAggiungere.getOrarioInizio();
		
		LocalTime orarioFineRicavato = sessionePraticaDaAggiungere.getOrarioFine();

		int idCorsoRicavato = sessionePraticaDaAggiungere.getCorsoDiRiferimento().getIdCorso();
		
		String comando = "INSERT INTO sessione_pratica (numeroadesioni, datasessione, orarioinizio, orariofine, fkcorso) VALUES "
				   + "( '"+numeroAdesioniRicavato+"' , '"+dataSessioneRicavata+"' , '"+orarioInizioRicavato+"', "
				   		+ " '"+orarioFineRicavato+"' , '"+idCorsoRicavato+"') ";
	
		try{
			comunicazioneDB.mandaQueryDML(comando);
		} catch (SQLException e) {			
			String codiceErrore = e.getSQLState();
			
			if(VIOLAZIONE_VINCOLO_DATASESSIONE_FUORI_RANGE.equals(codiceErrore)) {
				throw new DBExceptionDataSessioneFuoriRange();
				}
			
			if(VIOLAZIONE_VINCOLO_SESSIONE_DUPLICATA.equals(codiceErrore)) {
				throw new DBExceptionSessionePraticaDuplicata();
				}
			
			if(VIOLAZIONE_VINCOLO_DATASESSIONE_DIVERSA_MENSILE.equals(codiceErrore)) {
				throw new DBExceptionDataSessionePraticaDiversaMensile();
				}
			
			throw new DBExceptionOperazioneQueryDML();
			}		
	}
	 
}
