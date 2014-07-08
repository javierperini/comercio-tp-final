package Comercio;

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
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	//TESTEAR
	public String getUnidad() {
		return unidad;
	}
	//TESTEAR
	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}


}
