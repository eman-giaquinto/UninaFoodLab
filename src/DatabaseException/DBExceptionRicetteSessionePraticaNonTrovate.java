package DatabaseException;

public class DBExceptionRicetteSessionePraticaNonTrovate extends Exception {
	private String messaggioWarningSchermo = "Non ci sono ricette associate alla sessione\n"
										   + "pratica selezionata";

	public String getMessaggioWarningSchermo() {
		return messaggioWarningSchermo;
	};
	
}
