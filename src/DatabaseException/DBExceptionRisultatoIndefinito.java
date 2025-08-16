package DatabaseException;

public class DBExceptionRisultatoIndefinito extends Exception {
	private String messaggioErroreSchermo= "Errore durante la query";

	public String getMessaggioErroreSchermo() {
		return messaggioErroreSchermo;
	};
	
}
