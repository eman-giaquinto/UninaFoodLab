package DatabaseException;

public class DBExceptionDataSessioneOnlineDiversaSettimanale extends Exception{
	private String messaggioErroreSchermo = "Inserimento fallito. Riprova!\n"
			+ "Hai già inserito una sessione online in questa settimana.\n"
			+ "Non puoi utilizzare un giorno diverso,inserisci lo stesso giorno.";

	public String getMessaggioErroreSchermo() {
		return messaggioErroreSchermo;
	}

	
}
