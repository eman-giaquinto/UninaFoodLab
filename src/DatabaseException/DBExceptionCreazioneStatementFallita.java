package DatabaseException;

public class DBExceptionCreazioneStatementFallita extends Exception{
	private String messaggioErroreSchermo = "Creazione statement fallita";

	public String getMessaggioErroreSchermo() {
		return messaggioErroreSchermo;
	}
}
