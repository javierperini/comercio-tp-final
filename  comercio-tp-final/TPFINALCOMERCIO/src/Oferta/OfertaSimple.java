package Oferta;

import org.joda.time.DateTime;

import Producto.Producto;
import Producto.Unidad;

public class OfertaSimple extends Oferta{

	private Producto unProducto;
	private Unidad unaUnidad;
	/**
	 * CONSTRUCTOR
	 * @param nombre este parametro refiere al nombre de la oferta.
	 * @param unProducto este parametro refiere al producto al que se le aplicara la oferta.
	 * @param descuento este parametro refiere al descuento que se le aplicara la oferta.
	 * @param unaUnidad este parametro refiere a la unidad del producto que se quiere ofertar
	 * @param validoDesde este parametro refiere a la fecha de inicio de la oferta.
	 * @param validoHasta este parametro refiere a la fecha de fin de la oferta.
	 */
	public OfertaSimple(String nombre, Producto unProducto, 
			double descuento, Unidad unaUnidad, DateTime validoDesde, DateTime  validoHasta) {
		
		super(descuento, nombre, validoDesde, validoHasta);
		this.unProducto = unProducto;
		this.unaUnidad = unaUnidad;
	}
	/**
	 * 
	 * @return Retorna el producto que se quiere ofertar
	 */
	public Producto getUnProducto() {
		return unProducto;
	}
	/**
	 * 
	 * @return Retorna la unidad del producto que se quiere ofertar.
	 */
	public Unidad getUnaUnidad() {
		return unaUnidad;
	}
	/**
	 * @return Retorna el precio de la oferta.
	 */
	@Override
	public double getPrecioOferta() {
		
		double precioDeVenta = this.getUnProducto().getPresentacion(this.getUnaUnidad()).getPrecioVenta();		
		double descuento = this.getDescuento();
		double precioDeOferta = (precioDeVenta - (precioDeVenta*descuento)/100);
		return precioDeOferta;
	}
	/**
	 * @param producto este parametro refiere al producto que se quiere saber si esta dentro de la oferta.
	 * @return Retorna un booleano indicando si la oferta incluye un producto.
	 */
	@Override
	public boolean perteneceA(Producto producto) {
		return this.getUnProducto().equals(producto);
	}
	
}
