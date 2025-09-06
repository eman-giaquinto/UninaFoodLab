package Exception;

public class ExceptionFrequenzaSessioneMensile extends Exception {
	private String messaggioWarningSchermo = "Hai scelto una frequenza delle sessioni mensile"
			+ "\nIl corso non può iniziare e terminare nello stesso mese";

	public String getmessaggioWarningSchermo() {
		return messaggioWarningSchermo;
	}
}
