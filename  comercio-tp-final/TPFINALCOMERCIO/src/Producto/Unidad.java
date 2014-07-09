package Producto;

public class Unidad {

	private double cantidad;
	private String unidad;
	//TESTEAR
	public Unidad(double d, String string) {
		this.cantidad=d;
		this.unidad=string;
	}
	//TESTEAR
	public double getCantidad() {
		return cantidad;
	}
	
	//TESTEAR
	public String getUnidad() {
		return unidad;
	}
	

}
