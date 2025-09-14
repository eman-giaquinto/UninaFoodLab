package Exception;

public class ExceptionCampoOrarioInizioVuoto extends Exception{
	private String messaggioWarningSchermo = "Campo ORARIO INIZIO vuoto, inserisci un valore valido";

	public String getmessaggioWarningSchermo() {
		return messaggioWarningSchermo;
	}
}
