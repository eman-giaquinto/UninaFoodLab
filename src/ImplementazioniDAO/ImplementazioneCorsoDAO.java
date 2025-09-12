package ImplementazioniDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import DAO.CorsoDAO;
import DTO.Chef;
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
	public ArrayList<Corso> ottieniCorsiChef(Chef chefAssociato, String filtroRicavato)
			throws DBExceptionRisultatoIndefinito, DBExceptionCorsiNonTrovati {

		ArrayList<Corso> corsi = new ArrayList<>();
		
		String usernameChef = chefAssociato.getUsername();
		
	    String comando = "SELECT IDCorso, TipoDiCorso, DataInizio, Freqsessione, DataFine FROM Corso "  
				   	   + "WHERE FKChef = '" + usernameChef + "'";
	    
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
	            		Corso.FrequenzaSessione.ottieniTipoDaDescrizione(risultato.getString("Freqsessione")),
	            		risultato.getDate("DataFine").toLocalDate(),
	            		chefAssociato);
	            
	            // Aggiungi l'oggetto Corso all arrayList
	            corsi.add(tempCorso);
	    	}
	    } catch (SQLException e) {
	        //throw new DBExceptionRisultatoIndefinito();
	    	e.printStackTrace();
	    }
	    
		return corsi;
	}
	
	@Override
	public void creaCorso(Corso corsoDaCreare) throws DBExceptionOperazioneQueryDML, DBExceptionDataInizioMaggioreDataFine {
		
		String tipoDiCorsoRicavato = corsoDaCreare.getTipoDiCorso().getDescrizione();
		
		LocalDate dataDiInizioRicavato = corsoDaCreare.getDataInizio();
		
		String frequenzaSessioneRicavato = corsoDaCreare.getFrequenzaSessione().getDescrizione();
		
		LocalDate dataDiFineRicavato = corsoDaCreare.getDataFine();
		
		String usernameChef = corsoDaCreare.getChefDiRiferimento().getUsername();
	    
		String comando = "INSERT INTO Corso (TipoDiCorso, DataInizio, FreqSessione, DataFine, FKChef) VALUES "  
 			   + "( '"+tipoDiCorsoRicavato+"' , '"+dataDiInizioRicavato+"' , "
 			   + "  '"+frequenzaSessioneRicavato+"' , '"+dataDiFineRicavato+"', '"+usernameChef+"' ); ";
	    
	    try{
		    comunicazioneDB.mandaQueryDML(comando);
		    
	    } catch (SQLException e){
	    	if (e.getMessage().contains(CONSTRAINT_DATA_INIZIO_MINORE_O_UGUALE_DATA_FINE.toLowerCase())) {
	    		throw new DBExceptionDataInizioMaggioreDataFine();
	    	}
	    	throw new DBExceptionOperazioneQueryDML();
	    }
		
	}
	

}
