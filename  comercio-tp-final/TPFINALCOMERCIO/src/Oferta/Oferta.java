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
	 * @param descuento Este parametro refiere al descuento que se le agrega a los productos que entren en la oferta. 
	 * @param nombre este parametro refiere al nombre de la oferta.
	 * @param validoDesde este parametro refiere a la fecha desde cuando es valida la oferta.
	 * @param validoHasta este parametro refiere a la fecha desde cuando deja de ser valida la oferta.
	 */
   public Oferta(double descuento, String nombre, DateTime validoDesde, DateTime  validoHasta){
		this.descuento = descuento;
		this.nombre = nombre;
		this.validoDesde = new DateTime(validoDesde);
		this.validoHasta = new DateTime(validoHasta);
	}
	/**
	 * 
	 * @return retorna el porcentaje de descuento de la oferta.
	 */
	public double getDescuento() {
		return descuento;
	}
	/**
	 * 
	 * @return Retorna el nombre de la oferta.
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * 
	 * @return Retorna la fecha de inicio oferta.
	 */
	public DateTime getValidoDesde() {
		return this.validoDesde;
	}
	/**
	 * 
	 * @return Retorna la fecha de vencimiento de la oferta.
	 */
	public DateTime getValidoHasta() {
		return this.validoHasta;
	}

	/**
	 * 
	 * @param fecha este parametro refiere a la fecha en la que se quiere saber si la oferta estara vigente.
	 * @return Retorna un booleano indicando si la oferta sigue vigente en la fecha pasada por parametro
	 */
	public boolean ofertaValida(DateTime fecha){
		return fecha.isAfter(this.getValidoDesde()) && fecha.isBefore(this.getValidoHasta());
	}
	/**
	 * 
	 * @return retorna el precio de la oferta.
	 */
	public abstract double getPrecioOferta();
	/**
	 * 
	 * @param producto este parametro refiere al producto que se quiere saber si esta dentro de la oferta.
	 * @return retorna un booleano indicando si una oferta es sobre un producto en particular.
	 */
	public abstract boolean perteneceA(Producto producto);
	
}
