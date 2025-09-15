package ImplementazioniDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import DAO.SessioneOnlineDAO;
import DTO.Corso;
import DTO.SessioneOnline;
import DTO.SessioneOnline.Piattaforma;
import Database.ComunicazioneDB;
import DatabaseException.DBExceptionDataSessioneOnlineDiversaMensile;
import DatabaseException.DBExceptionDataSessioneOnlineDiversaSettimanale;
import DatabaseException.DBExceptionOperazioneQueryDML;
import DatabaseException.DBExceptionRisultatoIndefinito;
import DatabaseException.DBExceptionSessioneOnlineDuplicata;
import DatabaseException.DBExceptionSessioneOnlineLinkDuplicato;
import DatabaseException.DBExceptionSessioniOnlineNonTrovate;

public class ImplementazioneSessioneOnlineDAO implements SessioneOnlineDAO {
	 private ComunicazioneDB comunicazioneDB; 
	 private ResultSet risultato; 
	
	private static final String VIOLAZIONE_VINCOLO_SESSIONE_ONLINE_DUPLICATA = "SO001"; 
	private static final String VIOLAZIONE_VINCOLO_DATASESSIONE_ONLINE_DIVERSA_SETTIMANALE = "SO002"; 
	private static final String VIOLAZIONE_VINCOLO_DATASESSIONE_ONLINE_DIVERSA_MENSILE = "SO003"; 
	private static final String VIOLAZIONE_CONSTRAINT_LINK_UNIVOCO = "23505"; 
	 
	 public ImplementazioneSessioneOnlineDAO(ComunicazioneDB comunicazioneDB) {
		 this.comunicazioneDB=comunicazioneDB;
	 }

	@Override
	public ArrayList<SessioneOnline> ottieniSessioniOnline(Corso corsoSelezionato)
			throws DBExceptionRisultatoIndefinito, DBExceptionSessioniOnlineNonTrovate {
		
		ArrayList<SessioneOnline> sessioniOnline = new ArrayList<>();
		
		int idCorsoRicavato = corsoSelezionato.getIdCorso(); 

	    String comando = "SELECT * FROM sessione_online "
	    			   + "WHERE fkcorso =  "  + idCorsoRicavato + "";
	    	   
	    // Mando il comando e prendo il risultato della query
	    risultato = comunicazioneDB.comunicazioneDBQuery(comando);

	    try {
	        // Controlla se il ResultSet è vuoto prima di iniziare a leggere
	        if (!risultato.isBeforeFirst()) { 
	            // Nessuna riga trovata, la ricerca non ha prodotto risultati.
	            throw new DBExceptionSessioniOnlineNonTrovate();
	        }

	        // Itera su tutte le righe restituite dalla query
	        while (comunicazioneDB.prossimaTupla()) {
	            
	        	int idSessioneOnline = risultato.getInt("idsessionepratica");
	        	
	        	Piattaforma piattaforma = Piattaforma.ottieniPiattaformaFormattata(risultato.getString("piattaforma"));
	        	
	            LocalDate dataSessione = risultato.getDate("datasessione").toLocalDate();

	        	LocalTime orarioInizio = risultato.getTime("orarioinizio").toLocalTime();
	        	
	        	LocalTime orarioFine = risultato.getTime("orariofine").toLocalTime();
	        	
	        	String link = risultato.getString("link");
	        	
	            // Crea un nuovo oggetto SessioneOnline con i dati estratti
	        	SessioneOnline tempSessioneOnline = new SessioneOnline(idSessioneOnline,piattaforma,dataSessione,
	        			orarioInizio,orarioFine,link,corsoSelezionato);
	            
	            // Aggiungi l'oggetto Sessione online alla lista
	        	sessioniOnline.add(tempSessioneOnline);
	        }
	    } catch (SQLException e) {
	        // Errore durante l'accesso ai dati del ResultSet
	        throw new DBExceptionRisultatoIndefinito();
	    } 
	    
	    return sessioniOnline;
	}

	@Override
	public void creaSessioneOnline(SessioneOnline sessioneOnlineDaAggiungere) throws DBExceptionOperazioneQueryDML,
			DBExceptionSessioneOnlineDuplicata, DBExceptionDataSessioneOnlineDiversaSettimanale,
			DBExceptionDataSessioneOnlineDiversaMensile, DBExceptionSessioneOnlineLinkDuplicato {
		
		String piattaformaRicavata = sessioneOnlineDaAggiungere.getPiattaforma().getDescrizione();
		
		LocalDate dataSessioneRicavata = sessioneOnlineDaAggiungere.getDataSessione();
		
		LocalTime orarioInizioRicavato = sessioneOnlineDaAggiungere.getOrarioInizio();
		
		LocalTime orarioFineRicavato = sessioneOnlineDaAggiungere.getOrarioFine();
		
		String linkRicavato = sessioneOnlineDaAggiungere.getLink();

		int idCorsoRicavato = sessioneOnlineDaAggiungere.getCorsoDiRiferimento().getIdCorso();
		
		
		String comando = "INSERT INTO sessione_online (piattaforma, datasessione, orarioinizio, orariofine, link, fkcorso) "
			+ "VALUES ( '"+piattaformaRicavata+"' , '"+dataSessioneRicavata+"' , '"+orarioInizioRicavato+"', "
			+ " '"+orarioFineRicavato+"' , '"+linkRicavato+"' , '"+idCorsoRicavato+"') ";
	
		try{
			comunicazioneDB.mandaQueryDML(comando);
		} catch (SQLException e) {
			
			String codiceErrore = e.getSQLState();
					
			if(VIOLAZIONE_VINCOLO_SESSIONE_ONLINE_DUPLICATA.equals(codiceErrore)) {
			throw new DBExceptionSessioneOnlineDuplicata();
			}
			
			if(VIOLAZIONE_VINCOLO_DATASESSIONE_ONLINE_DIVERSA_SETTIMANALE.equals(codiceErrore)) {
			throw new DBExceptionDataSessioneOnlineDiversaSettimanale();
			}
			
			if(VIOLAZIONE_VINCOLO_DATASESSIONE_ONLINE_DIVERSA_MENSILE.equals(codiceErrore)) {
			throw new DBExceptionDataSessioneOnlineDiversaMensile();
			}
			
			if(VIOLAZIONE_CONSTRAINT_LINK_UNIVOCO.equals(codiceErrore)) {
		 		throw new DBExceptionSessioneOnlineLinkDuplicato();
			}
			
			throw new DBExceptionOperazioneQueryDML();
		}		
	}

}
