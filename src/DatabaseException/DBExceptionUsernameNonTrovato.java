package DatabaseException;

public class DBExceptionUsernameNonTrovato extends Exception{
	private String messaggioWarningSchermo = "Username non trovato";

	public String getmessaggioWarningSchermo() {
		return messaggioWarningSchermo;
	}
}
