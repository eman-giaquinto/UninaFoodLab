package DAO;

import DTO.Chef;
import DatabaseException.DBExceptionPasswordErrata;
import DatabaseException.DBExceptionRisultatoIndefinito;
import DatabaseException.DBExceptionUsernameNonTrovato;

public interface ChefDAO {
	
	/* LOGICA LOGIN */
	
	Chef verificaAccesso(Chef chefDaVerificare) throws DBExceptionRisultatoIndefinito, 
	DBExceptionUsernameNonTrovato, DBExceptionPasswordErrata;
	
	void verificaUsername (String username) throws DBExceptionUsernameNonTrovato, DBExceptionRisultatoIndefinito;
	
	void verificaPassword(String password) throws DBExceptionRisultatoIndefinito, DBExceptionPasswordErrata;
	
	String recuperoNomeChef() throws DBExceptionRisultatoIndefinito;
	
	String recuperoCognomeChef() throws DBExceptionRisultatoIndefinito;


}
