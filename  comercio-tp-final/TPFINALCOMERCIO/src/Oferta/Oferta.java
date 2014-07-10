package Oferta;

import org.joda.time.DateTime;

import Producto.Producto;

public abstract class Oferta {
	
	private double descuento;
	private String nombre;
	private DateTime validoDesde;
	private DateTime validoHasta;
	/**
	 * CONSTRUCTOR
	 * @param descuento Este parametro refiere al descuento que se le 
	 * @param nombre
	 * @param validoDesde
	 * @param validoHasta
	 */
   public Oferta(double descuento, String nombre, DateTime validoDesde, DateTime  validoHasta){
		this.setDescuento(descuento);
		this.setNombre(nombre);
		this.setValidoDesde(new DateTime(validoDesde));
		this.setValidoHasta(new DateTime(validoHasta));
	}
	
	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}
	//TESTEAR
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	//TESTEAR
	public DateTime getValidoDesde() {
		return this.validoDesde;
	}

	public void setValidoDesde(DateTime validoDesde) {
		this.validoDesde = validoDesde;
	}
	//TESTEAR
	public DateTime getValidoHasta() {
		return this.validoHasta;
	}

	public void setValidoHasta(DateTime validoHasta) {
		this.validoHasta = validoHasta;
	}

	public boolean ofertaValida(DateTime fecha){
		return fecha.isAfter(this.getValidoDesde()) && fecha.isBefore(this.getValidoHasta());
	}
	
	public abstract double getPrecioOferta();

	public abstract boolean perteneceA(Producto producto);
	
}
