package Exception;

public class ExceptionCampoTipoCorsoVuoto extends Exception{
	private String messaggioWarningSchermo = "Campo TIPO DI CORSO vuoto, inserisci un valore valido";

	public String getmessaggioWarningSchermo() {
		return messaggioWarningSchermo;
	}
}
