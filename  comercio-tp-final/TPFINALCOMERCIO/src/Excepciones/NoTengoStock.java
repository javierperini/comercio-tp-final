package Excepciones;

public class NoTengoStock  extends Exception {
	public NoTengoStock(){
		super("No hay stock para esa compra");
	}

}
