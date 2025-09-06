package DAO;

import java.util.ArrayList;

import DTO.Corso;
import DatabaseException.DBExceptionCorsiNonTrovati;
import DatabaseException.DBExceptionDataInizioMaggioreDataFine;
import DatabaseException.DBExceptionOperazioneQueryDML;
import DatabaseException.DBExceptionRisultatoIndefinito;

public interface CorsoDAO {
	
	/* LOGICA TASTO VISUALIZZA */
	ArrayList<Corso> ottieniCorsi(String usernameChef,String filtroRicavato) throws DBExceptionRisultatoIndefinito,
	DBExceptionCorsiNonTrovati;
	
	/* LOGICA TASTO AGGIUNGI */
	int creaCorso(String tipoDiCorsoRicavato,String dataDiInizioRicavata,String dataDiFineRicavata,
	String frequenzaSessioneRicavata) throws DBExceptionOperazioneQueryDML,DBExceptionDataInizioMaggioreDataFine, 
	DBExceptionRisultatoIndefinito;
	
	void registraCorso(String usernameChef, int idCorsoAggiunto) throws DBExceptionOperazioneQueryDML;

}
