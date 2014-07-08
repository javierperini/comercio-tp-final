package Comercio;

import org.joda.time.DateTime;

public abstract class Oferta {
	
	private int idOferta;// ver si hay que sacar
	private double descuento;
	private String nombre;
	private DateTime validoDesde;
	private DateTime validoHasta;
	
   public Oferta(double descuento, String nombre, int id, DateTime validoDesde, DateTime  validoHasta){
		this.setDescuento(descuento);
		this.setNombre(nombre);
		this.setId(id);
		this.setValidoDesde(new DateTime(validoDesde));
		this.setValidoHasta(new DateTime(validoHasta));
	}
	
	
	
	public void setId(int id){
		this.idOferta = id;
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
	/*
	public boolean ofertaValida(DateTime fecha){
		return fecha.isAfter(this.getValidoDesde()) && fecha.isBefore(this.getValidoHasta());
	}*/
	
	public abstract double getPrecioOferta();

	public abstract boolean ofertaValida(Producto producto, DateTime fecha);

	public abstract boolean perteneceA(Producto producto);
	
}
