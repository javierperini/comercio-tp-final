package Excepciones;

public class SinCuentaCorrienteException extends Exception {
	/**
	 * CONSTRUCTOR
	 */
	public SinCuentaCorrienteException(){
		super("El cliente no tiene una cuenta corriente activa.");
	}
}
