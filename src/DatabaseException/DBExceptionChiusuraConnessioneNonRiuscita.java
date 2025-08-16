package DatabaseException;

public class DBExceptionChiusuraConnessioneNonRiuscita extends Exception{
	private String messaggioErroreSchermo= "Chiusura connessione DB fallita";

	public String getMessaggioErroreSchermo() {
		return messaggioErroreSchermo;
	};
}
