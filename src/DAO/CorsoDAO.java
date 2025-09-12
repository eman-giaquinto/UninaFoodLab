package DAO;

import java.util.ArrayList;

import DTO.Chef;
import DTO.Corso;
import DatabaseException.DBExceptionCorsiNonTrovati;
import DatabaseException.DBExceptionDataInizioMaggioreDataFine;
import DatabaseException.DBExceptionOperazioneQueryDML;
import DatabaseException.DBExceptionRisultatoIndefinito;

public interface CorsoDAO {
	
	/* LOGICA TASTO VISUALIZZA */
	ArrayList<Corso> ottieniCorsiChef(Chef chefAssociato,String filtroRicavato) throws DBExceptionRisultatoIndefinito,
	DBExceptionCorsiNonTrovati;
	
	/* LOGICA TASTO AGGIUNGI */
	void creaCorso(Corso corsoDaCreare) throws DBExceptionOperazioneQueryDML,DBExceptionDataInizioMaggioreDataFine;

}
