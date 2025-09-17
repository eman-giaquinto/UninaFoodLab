package ImplementazioniDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.RicettaDAO;
import DTO.Ricetta;
import DTO.Ricetta.GradoDifficoltà;
import DTO.SessionePratica;
import Database.ComunicazioneDB;
import DatabaseException.DBExceptionOperazioneQueryDML;
import DatabaseException.DBExceptionRicettaGiàAssociata;
import DatabaseException.DBExceptionRicetteNonTrovate;
import DatabaseException.DBExceptionRisultatoIndefinito;

public class ImplementazioneRicettaDAO implements RicettaDAO{
	private ComunicazioneDB comunicaDatabase; 
    
	private ResultSet risultato; 
	
	private static final String VIOLAZIONE_CONSTRAINT_RICETTA_DUPLICATA = "23505"; 

	
	public ImplementazioneRicettaDAO(ComunicazioneDB comunicaDatabase) {
		 this.comunicaDatabase=comunicaDatabase;
	 }
	
	@Override
	public ArrayList<Ricetta> ottieniRicetteSessionePratica(SessionePratica sessionePraticaSelezionata) 
	throws DBExceptionRisultatoIndefinito,DBExceptionRicetteNonTrovate {
		
		ArrayList<Ricetta> ricetteSessionePratica = new ArrayList<>();
		
		int idSessionePraticaRicavata = sessionePraticaSelezionata.getIdSessionePratica();

	    String comando = "SELECT nome,descrizione,gradodiff FROM ricetta "
	    			   + "JOIN detiene ON FKNomeRicetta = nome "
	    			   + "WHERE FKSessionePratica =  "  + idSessionePraticaRicavata + "";
	    	   

	    // Mando il comando e prendo il risultato della query
	    risultato = comunicaDatabase.comunicazioneDBQuery(comando);

	    try {
	        // Controlla se il ResultSet è vuoto prima di iniziare a leggere
	        if (!risultato.isBeforeFirst()) { 
	            // Nessuna riga trovata, la ricerca non ha prodotto risultati.
	            throw new DBExceptionRicetteNonTrovate();
	        }

	        // Itera su tutte le righe restituite dalla query
	        while (comunicaDatabase.prossimaTupla()) {
	            
	        	String nome = risultato.getString("nome");
	        	
	        	String descrizione = risultato.getString("descrizione");
	        	
	        	GradoDifficoltà gradoDifficoltà = GradoDifficoltà.ottieniGradoDiDifficoltàFormattata(risultato.getString("gradodiff"));
	        	
	            // Crea un nuovo oggetto Ricetta con i dati estratti
	        	Ricetta tempRicetta = new Ricetta(nome,descrizione,gradoDifficoltà);
	            
	            // Aggiungi l'oggetto Ricetta alla lista
	            ricetteSessionePratica.add(tempRicetta);
	        }
	    } catch (SQLException e) {
	        // Errore durante l'accesso ai dati del ResultSet
	       throw new DBExceptionRisultatoIndefinito();
	    }
	    
	    return ricetteSessionePratica;
	}

	@Override
	public ArrayList<String> ottieniTutteLeRicette() throws DBExceptionRisultatoIndefinito {
		ArrayList<String> listaRicette = new ArrayList<>();
		
	    String comando = "SELECT nome FROM ricetta;";
	    	   
	    // Mando il comando e prendo il risultato della query
	    risultato = comunicaDatabase.comunicazioneDBQuery(comando);

	    try {
	        // Itera su tutte le righe restituite dalla query
	        while (comunicaDatabase.prossimaTupla()) {
	            
	            String nomeRicetta = risultato.getString("nome");
	            
	            listaRicette.add(nomeRicetta);
	        }
	    } catch (SQLException e) {
	        // Errore durante l'accesso ai dati del ResultSet
	        throw new DBExceptionRisultatoIndefinito();
	    } 

	    
	    return listaRicette;
	}

	@Override
	public void aggiungiRicettaSessionePraticaAssociata(SessionePratica sessionePraticaSelezionata,
			Ricetta ricettaDaAggiungere) throws DBExceptionOperazioneQueryDML, DBExceptionRicettaGiàAssociata {
		
		int idSessionePraticaSelezionata = sessionePraticaSelezionata.getIdSessionePratica();
		
		String nomeRicettaRicavata = ricettaDaAggiungere.getNome();
		
		String comando = "INSERT INTO detiene VALUES ( '"+idSessionePraticaSelezionata+"', "
				+ "'"+nomeRicettaRicavata+"' )";
				  
		try{
			comunicaDatabase.mandaQueryDML(comando);
		} catch (SQLException e) {
			
			String codiceErrore = e.getSQLState();
			
			if(VIOLAZIONE_CONSTRAINT_RICETTA_DUPLICATA.equals(codiceErrore)) {
	 		throw new DBExceptionRicettaGiàAssociata();
			}
			
			throw new DBExceptionOperazioneQueryDML();
			
	 	}
	}		

}
