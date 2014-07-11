package Comercio;

import org.joda.time.DateTime;

import Producto.Producto;
import Producto.Unidad;

public class OrdenDeCompra {
	
	private Producto unProducto;
	private Unidad unaUnidad;
	private double cantidad;
	private DateTime fecha;
	/**
	 * CONSTRUCTOR
	 * @param unProducto este parametro refiere al producto que se cargara en la orden de compra.
	 * @param unaUnidad este parametro refiere a la unidad del producto que se cargara en la orden de compra.
	 * @param cantidad este parametro refiere a la cantidad de la presentacion en 
	 * cuestion que se cargara en la orden de compra. 
	 * @param fecha este parametro refiere a la fecha en la que se creo la orden de compra.
	 */
	public OrdenDeCompra(Producto unProducto, Unidad unaUnidad, double cantidad, DateTime fecha) {
		this.unProducto = unProducto;
		this.unaUnidad = unaUnidad;
		this.cantidad = cantidad;
		this.fecha = fecha;
	}
	/**
	 * 
	 * @return retorna el producto al que refiere la orden de compra.
	 */
	public Producto getUnProducto() {
		return unProducto;
	}
	/**
	 * 
	 * @return retorna la unidad del producto al que refiere la orden de compra.
	 */
	public Unidad getUnaUnidad() {
		return unaUnidad;
	}
	/**
	 * 
	 * @return retorna la cantidad de unidades de esa presentacion en particular a la que refiere la orden de compra.
	 */
	public double getCantidad() {
		return this.cantidad;
	}
	/**
	 * 
	 * @return retorna el precio de la orden de compra.
	 */
	public double getPrecio(){
		return (this.getCantidad())*(this.getUnProducto().getPresentacion(this.getUnaUnidad()).getPrecioVenta());
	}
	/**
	 * 
	 * @return retorna la fecha en la que se creo la orden de compra.
	 */
	public DateTime getFecha() {
		return this.fecha;
	}
	
}
