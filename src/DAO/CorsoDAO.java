package DAO;

import java.util.ArrayList;

import DTO.Corso;
import DatabaseException.DBExceptionCorsiNonTrovati;
import DatabaseException.DBExceptionRisultatoIndefinito;

public interface CorsoDAO {
	
	ArrayList<Corso> ottieniCorsi(String usernameChef,String filtroRicavato) throws DBExceptionRisultatoIndefinito, DBExceptionCorsiNonTrovati;

}
