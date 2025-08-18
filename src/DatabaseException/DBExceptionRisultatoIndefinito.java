package DatabaseException;

public class DBExceptionRisultatoIndefinito extends Exception {
	private String messaggioErrorSchermo= "Errore durante la query";

	public String getMessaggioErrorSchermo() {
		return messaggioErrorSchermo;
	};
	
}
