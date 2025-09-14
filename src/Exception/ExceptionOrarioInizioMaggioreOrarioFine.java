package Exception;

public class ExceptionOrarioInizioMaggioreOrarioFine extends Exception{
	private String messaggioWarningSchermo = "L' orario d' inizio non può essere uguale o successivo all' orario di fine";

	public String getmessaggioWarningSchermo() {
		return messaggioWarningSchermo;
	}
}
