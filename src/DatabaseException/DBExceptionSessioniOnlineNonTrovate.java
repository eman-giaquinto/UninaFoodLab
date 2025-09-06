package DatabaseException;

public class DBExceptionSessioniOnlineNonTrovate extends Exception {
	private String messaggioWarningSchermo= "Non ci sono sessioni pratiche associate al corso selezionato";

	public String getMessaggioWarningSchermo() {
		return messaggioWarningSchermo;
	};
	
}
