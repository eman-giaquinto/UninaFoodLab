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
    
	public ImplementazioneChefDAO(ComunicazioneDB comunicazioneDB) {
		this.comunicazioneDB=comunicazioneDB;
	}

	@Override
	public Chef verificaAccesso(Chef chefDaVerificare)
			throws DBExceptionRisultatoIndefinito, DBExceptionUsernameNonTrovato, DBExceptionPasswordErrata {

		String usernameChef = chefDaVerificare.getUsername();
		
		String passwordChef = chefDaVerificare.getPassword();

		verificaUsername(usernameChef);
				
		verificaPassword(passwordChef);
		
		String nomeChef = recuperoNomeChef();

		String cognomeChef = recuperoCognomeChef();

	    Chef chefVerificato = new Chef(usernameChef,passwordChef,nomeChef,cognomeChef);

		return chefVerificato;
	}
	
	@Override
	public void verificaUsername(String username) throws DBExceptionRisultatoIndefinito, DBExceptionUsernameNonTrovato {
		String comando = "SELECT username, password, nome, cognome FROM Chef "
					   + "WHERE ( username = '" + username + "');";
		
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
	public String recuperoNomeChef() throws DBExceptionRisultatoIndefinito {
		String nomeChefRecuperato;
		
		try {
			nomeChefRecuperato = risultato.getString("nome");
		} catch (SQLException e) {
			throw new DBExceptionRisultatoIndefinito();
		}
		
		return nomeChefRecuperato;
	}

	@Override
	public String recuperoCognomeChef() throws DBExceptionRisultatoIndefinito {
		String cognomeChefRecuperato;
		
		try {
			cognomeChefRecuperato = risultato.getString("cognome");
		} catch (SQLException e) {
			throw new DBExceptionRisultatoIndefinito();
		}
		
		return cognomeChefRecuperato;
	}

}
