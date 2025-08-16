package DatabaseException;

public class DBExceptionOperazioneQueryDML extends Exception {
	private String messaggioErroreSchermo= "Operazione DML fallita. Controlla i valori inseriti.";
	
	public String getMessaggioErroreSchermo() {
		return messaggioErroreSchermo;
	};
}
