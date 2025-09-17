package DAO;

import java.util.ArrayList;

import DTO.Ricetta;
import DTO.SessionePratica;
import DatabaseException.DBExceptionOperazioneQueryDML;
import DatabaseException.DBExceptionRicettaGiàAssociata;
import DatabaseException.DBExceptionRicetteNonTrovate;
import DatabaseException.DBExceptionRisultatoIndefinito;

public interface RicettaDAO {
	/* LOGICA TASTO VISUALIZZA */
	ArrayList<Ricetta> ottieniRicetteSessionePratica(SessionePratica sessionePraticaSelezionata) 
	throws DBExceptionRisultatoIndefinito,DBExceptionRicetteNonTrovate;

	/* LOGICA TASTO AGGIUNGI */
	ArrayList<String> ottieniTutteLeRicette() throws DBExceptionRisultatoIndefinito;

	void aggiungiRicettaSessionePraticaAssociata(SessionePratica sessionePraticaSelezionata,Ricetta ricettaDaAggiungere) 
	throws DBExceptionOperazioneQueryDML, DBExceptionRicettaGiàAssociata;
}
