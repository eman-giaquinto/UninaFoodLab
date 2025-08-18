package DatabaseException;

public class DBExceptionUsernameNonTrovato extends Exception{
	private String messaggioErrorSchermo = "Username non trovato";

	public String getmessaggioErrorSchermo() {
		return messaggioErrorSchermo;
	}
}
