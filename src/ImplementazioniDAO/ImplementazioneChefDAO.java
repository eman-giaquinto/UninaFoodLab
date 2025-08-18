package ImplementazioniDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.ChefDAO;
import DTO.Chef;
import Database.ComunicazioneDB;
import DatabaseException.DBExceptionPasswordErrata;
import DatabaseException.DBExceptionRisultatoIndefinito;
import DatabaseException.DBExceptionUsernameNonTrovato;

public class ImplementazioneChefDAO implements ChefDAO {
	
	private ComunicazioneDB comunicazioneDB;
	
    private ResultSet risultato; 
    
    private Chef chefAutenticato;

	public ImplementazioneChefDAO(ComunicazioneDB comunicazioneDB) {
		this.comunicazioneDB=comunicazioneDB;
	}

	@Override
	public void verificaUsername(String username) throws DBExceptionRisultatoIndefinito, DBExceptionUsernameNonTrovato {
		String comando = "SELECT username, password, nome, cognome, descrizione FROM Chef WHERE ( username = '" + username + "');";
		
		try {
			risultato = comunicazioneDB.comunicazioneDBQuery(comando);
		} catch (DBExceptionRisultatoIndefinito e) {
			throw new DBExceptionRisultatoIndefinito();
		}
		
		if (!comunicazioneDB.prossimaTupla()) {
            throw new DBExceptionUsernameNonTrovato();
		}
				
	}

	@Override
	public void verificaPassword(String password) throws DBExceptionRisultatoIndefinito, DBExceptionPasswordErrata {
		String passwordChefDB;
		try {
			passwordChefDB = risultato.getString("password");
		} catch (SQLException e) {
			throw new DBExceptionRisultatoIndefinito();
		}
		
		if(!password.equals(passwordChefDB)) {
			throw new DBExceptionPasswordErrata();
		}
				
	}

	@Override
	public void recuperoDati() throws DBExceptionRisultatoIndefinito {
		String nomeChef;
		String cognomeChef;
		String descrizioneChef;
		
		try {
			nomeChef = risultato.getString("nome");
			cognomeChef = risultato.getString("cognome");
			descrizioneChef = risultato.getString("descrizione");
		} catch (SQLException e) {
			throw new DBExceptionRisultatoIndefinito();
		}
		
		chefAutenticato.setNome(nomeChef);
		chefAutenticato.setCognome(cognomeChef);
		chefAutenticato.setDescrizione(descrizioneChef);
		
	}

	@Override
	public Chef verificaAccesso(String username, String password)
			throws DBExceptionRisultatoIndefinito, DBExceptionUsernameNonTrovato, DBExceptionPasswordErrata {

		chefAutenticato = new Chef(null,null,null,null,null);

		verificaUsername(username);
		
		chefAutenticato.setUsername(username);
		
		verificaPassword(password);
		
		chefAutenticato.setPassword(password);
		
		recuperoDati();
		
		return chefAutenticato;
	}

}
