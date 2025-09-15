package DatabaseException;

public class DBExceptionSessioneOnlineLinkDuplicato extends Exception{
	private String messaggioWarningSchermo = "Hai inserito un link già registrato"
			+ "\nProva ad inserire un altro link";

	public String getmessaggioWarningSchermo() {
		return messaggioWarningSchermo;
	}
}
