package Excepciones;

public class NoTengoStock  extends Exception {
	/**
	 * CONSTRUCTOR
	 */
	public NoTengoStock(){
		super("No hay stock para esa compra");
	}

}
