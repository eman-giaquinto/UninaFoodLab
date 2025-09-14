package DatabaseException;

public class DBExceptionDataSessionePraticaDiversaMensile extends Exception{
	private String messaggioErroreSchermo = "Inserimento fallito. Riprova!\n"
			+ "Hai già inserito una sessione pratica in questo mese.\n"
			+ "Non puoi utilizzare un giorno diverso,inserisci lo stesso giorno.";

	public String getMessaggioErroreSchermo() {
		return messaggioErroreSchermo;
	}

	
}
