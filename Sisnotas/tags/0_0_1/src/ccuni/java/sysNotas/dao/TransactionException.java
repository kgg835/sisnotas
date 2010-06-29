package ccuni.java.sysNotas.dao;

public class TransactionException extends Exception {
	

	private static String msgError = "La transacción ha fallado";

	public TransactionException(String string) {
		super(msgError + " : " + string);
	}

	public TransactionException(String string, Exception e) {
		super(msgError, e);
	}

	public TransactionException(Throwable cause) {
		super(msgError + " : " + cause.getMessage());

	}
	
	public String toString(){
		return getMessage();
	}

}
