package DatabaseException;

public class DBExceptionCorsiNonTrovati extends Exception{
	private String messaggioWarningSchermo = "Non sono presenti corsi registrati";

	public String getMessaggioWarningSchermo() {
		return messaggioWarningSchermo;
	}
}
