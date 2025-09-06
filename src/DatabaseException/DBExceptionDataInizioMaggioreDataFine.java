package DatabaseException;

public class DBExceptionDataInizioMaggioreDataFine extends Exception{
	private String messaggioWarningSchermo = "La data di inizio non può essere successiva alla data di fine";

	public String getmessaggioWarningSchermo() {
		return messaggioWarningSchermo;
	}
}
