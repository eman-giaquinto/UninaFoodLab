package ImplementazioniDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.SessionePraticaDAO;
import DTO.SessionePratica;
import Database.ComunicazioneDB;
import DatabaseException.DBExceptionRisultatoIndefinito;
import DatabaseException.DBExceptionSessioniPraticheNonTrovate;

public class ImplementazioneSessionePraticaDAO implements SessionePraticaDAO{
	 private ComunicazioneDB comunicazioneDB; 
	 private ResultSet risultato; 
	
	 public ImplementazioneSessionePraticaDAO(ComunicazioneDB comunicazioneDB) {
		 this.comunicazioneDB=comunicazioneDB;
	 }

	@Override
	public ArrayList<SessionePratica> ottieniSessioniPratiche(int idCorsoRicavato)
			throws DBExceptionRisultatoIndefinito, DBExceptionSessioniPraticheNonTrovate {
		
		ArrayList<SessionePratica> sessioniPratiche = new ArrayList<>();

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
	            
	            // Crea un nuovo oggetto SessionePratica con i dati estratti
	            SessionePratica tempSessionePratica = new SessionePratica(
	            		risultato.getInt("idsessione_pratica"),
	            		risultato.getInt("numeroadesioni"),
	            		risultato.getDate("datasessione").toLocalDate(),
	            		risultato.getTime("orarioinizio"),
	            		risultato.getTime("orariofine"),
	            		risultato.getInt("fkcorso"));
	            
	            // Aggiungi l'oggetto Sessione pratica alla lista
	            sessioniPratiche.add(tempSessionePratica);
	        }
	    } catch (SQLException e) {
	        // Errore durante l'accesso ai dati del ResultSet
	        throw new DBExceptionRisultatoIndefinito();
	    } 
	    
	    return sessioniPratiche;
	}
	 
}
