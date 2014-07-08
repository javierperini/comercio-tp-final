package Excepciones;

public class SinCuentaCorrienteException extends Exception {
	
	/**
	 *  Me lo pide java y yo se lo doy.
	 */
	private static final long serialVersionUID = 1L;

	public SinCuentaCorrienteException(){
		super("El cliente no tiene una cuenta corriente activa.");
	}
	
	public void mensaje(){
		System.out.println("El cliente no tiene una cuenta corriente activa.");
	}
}
