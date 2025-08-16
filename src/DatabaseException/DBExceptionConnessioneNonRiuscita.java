package DatabaseException;

public class DBExceptionConnessioneNonRiuscita extends Exception {
	private String messaggioErroreSchermo = "Connessione al DB fallita";

	public String getMessaggioErroreSchermo() {
		return messaggioErroreSchermo;
	}

}
