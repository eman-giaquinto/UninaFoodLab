package ImplementazioniDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.CorsoDAO;
import DTO.Corso;
import Database.ComunicazioneDB;
import DatabaseException.DBExceptionCorsiNonTrovati;
import DatabaseException.DBExceptionDataInizioMaggioreDataFine;
import DatabaseException.DBExceptionOperazioneQueryDML;
import DatabaseException.DBExceptionRisultatoIndefinito;

public class ImplementazioneCorsoDAO implements CorsoDAO{

	 private ComunicazioneDB comunicazioneDB; 
	 private ResultSet risultato; 
	 
	 /* Lista dei nomi dei vincoli */
	 private static final String CONSTRAINT_DATA_INIZIO_MINORE_O_UGUALE_DATA_FINE = "DataI_MinEqual_DataF";
	
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
	    
	    // Ricavo tutti i corsi di qualsiasi tipo
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
	
	@Override
	public int creaCorso(String tipoDiCorsoRicavato, String dataDiInizioRicavato, String dataDiFineRicavato,
		String frequenzaSessioneRicavato) throws DBExceptionOperazioneQueryDML, DBExceptionDataInizioMaggioreDataFine,
		DBExceptionRisultatoIndefinito {
		
		// Inizializzo con un valore sentinella/sporco per individuare un idcorso non valido
		int idNuovoCorsoAggiunto=-1;
	    
		String comando = "INSERT INTO Corso (TipoDiCorso, DataInizio, Freq_Sessione, DataFine) VALUES "  
	    			   + "( '"+tipoDiCorsoRicavato+"' , '"+dataDiInizioRicavato+"' , "
	    			   + "  '"+frequenzaSessioneRicavato+"' , '"+dataDiFineRicavato+"' ); ";
	    
	    try{
		    comunicazioneDB.mandaQueryDML(comando);
		    risultato = comunicazioneDB.numeroTuplaAggiunta();
		    
		    // Se non vengono rilanciate eccezioni, recupero il numero di tupla aggiunto
		    if (comunicazioneDB.prossimaTupla()) {
		    	idNuovoCorsoAggiunto = risultato.getInt(1);
		    }
		    
	    } catch (SQLException e){
	    	if (e.getMessage().contains(CONSTRAINT_DATA_INIZIO_MINORE_O_UGUALE_DATA_FINE.toLowerCase())) {
	    		throw new DBExceptionDataInizioMaggioreDataFine();
	    	}
	    	throw new DBExceptionOperazioneQueryDML();
	    }
    	return idNuovoCorsoAggiunto;
		
	}
	
	@Override
	public void registraCorso(String usernameChef, int idCorsoAggiunto ) throws DBExceptionOperazioneQueryDML {
		
		String comando = "INSERT INTO Registra (fkUsername, fkCorso) VALUES "
					   + " ('"+usernameChef+"' , "+idCorsoAggiunto+" ) " ;
 
		try{
			comunicazioneDB.mandaQueryDML(comando);
		} catch (SQLException e) {
			throw new DBExceptionOperazioneQueryDML();
		}
	}

}
