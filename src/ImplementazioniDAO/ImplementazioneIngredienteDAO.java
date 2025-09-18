package ImplementazioniDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.IngredienteDAO;
import DTO.Ingrediente;
import DTO.Ricetta;
import Database.ComunicazioneDB;
import DatabaseException.DBExceptionRisultatoIndefinito;

public class ImplementazioneIngredienteDAO implements IngredienteDAO  {

	private ComunicazioneDB comunicaDatabase; 
    
	private ResultSet risultato; 
	
	public ImplementazioneIngredienteDAO(ComunicazioneDB comunicaDatabase) {
		 this.comunicaDatabase=comunicaDatabase;
	}
	
	@Override
	public ArrayList<Ingrediente> ottieniIngredientiRicetta(Ricetta ricettaDiRiferimento)
			throws DBExceptionRisultatoIndefinito {
		
		String nomeRicetta = ricettaDiRiferimento.getNome();
		
		ArrayList<Ingrediente> ingredientiRicetta = new ArrayList<>();

	    String comando = "SELECT fkNomeIngrediente FROM appartiene "
	    			   + "WHERE fknomericetta =  '"  + nomeRicetta + "' ";
	    	   

	    // Mando il comando e prendo il risultato della query
	    risultato = comunicaDatabase.comunicazioneDBQuery(comando);

	    try {
	       
	        // Itera su tutte le righe restituite dalla query
	        while (comunicaDatabase.prossimaTupla()) {
	            
	            // Crea un nuovo oggetto Ingrediente con i dati estratti
	        	Ingrediente tempIngrediente = new Ingrediente(
	            		risultato.getString("fkNomeIngrediente"));
	            
	            // Aggiungi l'oggetto Ingrediente alla lista
	        	ingredientiRicetta.add(tempIngrediente);
	        }
	    } catch (SQLException e) {
	        // Errore durante l'accesso ai dati del ResultSet
	        throw new DBExceptionRisultatoIndefinito();
	    }
	    
	    return ingredientiRicetta;	
	}
	
}
