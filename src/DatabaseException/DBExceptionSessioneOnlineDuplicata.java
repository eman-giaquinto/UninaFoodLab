package DatabaseException;

public class DBExceptionSessioneOnlineDuplicata extends Exception{
	private String messaggioErroreSchermo = "Inserimento fallito. Riprova!\n"
			+ "Hai già inserito una sessione online con gli stessi orari o sovrapposti";

	public String getMessaggioErroreSchermo() {
		return messaggioErroreSchermo;
	}

	
}
