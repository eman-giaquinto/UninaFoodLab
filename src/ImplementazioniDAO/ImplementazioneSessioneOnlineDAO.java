package ImplementazioniDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.SessioneOnlineDAO;
import DTO.SessioneOnline;
import Database.ComunicazioneDB;
import DatabaseException.DBExceptionRisultatoIndefinito;
import DatabaseException.DBExceptionSessioniOnlineNonTrovate;

public class ImplementazioneSessioneOnlineDAO implements SessioneOnlineDAO {
	 private ComunicazioneDB comunicazioneDB; 
	 private ResultSet risultato; 
	
	 public ImplementazioneSessioneOnlineDAO(ComunicazioneDB comunicazioneDB) {
		 this.comunicazioneDB=comunicazioneDB;
	 }

	@Override
	public ArrayList<SessioneOnline> ottieniSessioniOnline(int idCorsoRicavato)
			throws DBExceptionRisultatoIndefinito, DBExceptionSessioniOnlineNonTrovate {
		
		ArrayList<SessioneOnline> sessioniOnline = new ArrayList<>();

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
	            
	            // Crea un nuovo oggetto SessioneOnline con i dati estratti
	        	SessioneOnline tempSessioneOnline = new SessioneOnline(
	            		risultato.getInt("idsessione_online"),
	            		SessioneOnline.Piattaforma.fromDescrizione(risultato.getString("piattaforma")),
	            		risultato.getDate("datasessione").toLocalDate(),
	            		risultato.getTime("orarioinizio"),
	            		risultato.getTime("orariofine"),
	            		risultato.getString("link"),
	            		risultato.getInt("fkcorso"));
	            
	            // Aggiungi l'oggetto Sessione online alla lista
	        	sessioniOnline.add(tempSessioneOnline);
	        }
	    } catch (SQLException e) {
	        // Errore durante l'accesso ai dati del ResultSet
	        throw new DBExceptionRisultatoIndefinito();
	    } 
	    
	    return sessioniOnline;
	}

}
