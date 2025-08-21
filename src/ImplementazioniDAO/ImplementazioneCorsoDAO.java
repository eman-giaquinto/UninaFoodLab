package ImplementazioniDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.CorsoDAO;
import DTO.Corso;
import Database.ComunicazioneDB;
import DatabaseException.DBExceptionCorsiNonTrovati;
import DatabaseException.DBExceptionRisultatoIndefinito;

public class ImplementazioneCorsoDAO implements CorsoDAO{

	 private ComunicazioneDB comunicazioneDB; 
	 private ResultSet risultato; 
	
	 public ImplementazioneCorsoDAO(ComunicazioneDB comunicazioneDB) {
		 this.comunicazioneDB=comunicazioneDB;
	 }
	 
	@Override
	public ArrayList<Corso> ottieniCorsi(String usernameChef, String filtroRicavato)
			throws DBExceptionRisultatoIndefinito, DBExceptionCorsiNonTrovati {

		ArrayList<Corso> corsi = new ArrayList<>();
		
	    String comando = "SELECT IDCorso, TipoDiCorso, DataInizio, Freq_sessione, DataFine FROM Corso "  
    				   + "JOIN Registra ON IDCorso = FKCorso "  
    				   + "WHERE FKUsername = '" + usernameChef + "'";
	    
	    if(!filtroRicavato.equals("Tutti")) {
	    	comando = comando + " AND TipoDiCorso = '" + filtroRicavato + "'"; 
	    }
	    
	    risultato = comunicazioneDB.comunicazioneDBQuery(comando);
	    
	    try {
	    	 // Controlla se il ResultSet è vuoto prima di iniziare a leggere
	        if (!risultato.isBeforeFirst()) { 
	            throw new DBExceptionCorsiNonTrovati();
	        }
	        
	    	while (comunicazioneDB.prossimaTupla()) {
	    		// Crea un nuovo oggetto Corso con i dati estratti
	            Corso tempCorso = new Corso(
	            		risultato.getInt("IDCorso"),
	            		Corso.TipoCorso.ottieniTipoDaDescrizione(risultato.getString("TipoDiCorso")), 
	            		risultato.getDate("DataInizio").toLocalDate(),
	            		Corso.FrequenzaSessione.ottieniTipoDaDescrizione(risultato.getString("Freq_sessione")),
	            		risultato.getDate("DataFine").toLocalDate());
	            
	            // Aggiungi l'oggetto Corso all arrayList
	            corsi.add(tempCorso);
	    	}
	    } catch (SQLException e) {
	        throw new DBExceptionRisultatoIndefinito();
	    }
	    
		return corsi;
	}

}
