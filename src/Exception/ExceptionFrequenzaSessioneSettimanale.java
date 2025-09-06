package Exception;

public class ExceptionFrequenzaSessioneSettimanale extends Exception{
	private String messaggioWarningSchermo = "Hai scelto una frequenza delle sessioni settimanale"
			+ "\nScegli una data di fine coerente alla data d' inizio";

	public String getmessaggioWarningSchermo() {
		return messaggioWarningSchermo;
	}
}
