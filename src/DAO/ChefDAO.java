package DAO;

import DTO.Chef;
import DatabaseException.DBExceptionPasswordErrata;
import DatabaseException.DBExceptionRisultatoIndefinito;
import DatabaseException.DBExceptionUsernameNonTrovato;

public interface ChefDAO {
	void verificaUsername (String username) throws DBExceptionUsernameNonTrovato, DBExceptionRisultatoIndefinito;
	
	void verificaPassword(String password) throws DBExceptionRisultatoIndefinito, DBExceptionPasswordErrata;
	
	void recuperoDati() throws DBExceptionRisultatoIndefinito;
	
	Chef verificaAccesso(String username, String password) throws DBExceptionRisultatoIndefinito, DBExceptionUsernameNonTrovato, DBExceptionPasswordErrata;
}
