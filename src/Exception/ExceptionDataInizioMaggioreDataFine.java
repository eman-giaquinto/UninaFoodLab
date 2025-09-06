package Exception;

public class ExceptionDataInizioMaggioreDataFine extends Exception{
	private String messaggioWarningSchermo = "La data d' inizio non può essere successiva alla data di fine";

	public String getmessaggioWarningSchermo() {
		return messaggioWarningSchermo;
	}
}
