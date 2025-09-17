package Exception;

public class ExceptionCampoRicettaNonSelezionato extends Exception{
	private String messaggioWarningSchermo = "Campo RICETTA vuoto, inserisci un valore valido";

	public String getmessaggioWarningSchermo() {
		return messaggioWarningSchermo;
	}
}
