package Excepciones;

public class SaldoInsuficienteException extends Exception{
	/**
	 * Se lo pongo porque lo pide java.
	 */
	private static final long serialVersionUID = 1L;

	public SaldoInsuficienteException(){
		super("El cliente no tiene saldo en su cuenta corriente.");
	}
	
}
