package Excepciones;

public class SaldoInsuficienteException extends Exception{
	/**
	 * CONSTRUCTOR
	 */
	public SaldoInsuficienteException(){
		super("El cliente no tiene saldo en su cuenta corriente.");
	}
	
}
