package DatabaseException;

public class DBExceptionSessionePraticaDuplicata extends Exception{
	private String messaggioErroreSchermo = "Inserimento fallito. Riprova!\n"
			+ "Hai già inserito una sessione pratica con gli stessi orari o sovrapposti";

	public String getMessaggioErroreSchermo() {
		return messaggioErroreSchermo;
	}

	
}
