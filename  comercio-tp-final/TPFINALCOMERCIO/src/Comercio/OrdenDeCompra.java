package Comercio;

import org.joda.time.DateTime;

import Producto.Producto;
import Producto.Unidad;



public class OrdenDeCompra {
	
	private Producto unProducto;
	private Unidad unaUnidad;
	private double cantidad;
	private DateTime fecha;
	
	public OrdenDeCompra(Producto unProducto, Unidad unaUnidad, double cantidad, DateTime fecha) {
		this.unProducto = unProducto;
		this.unaUnidad = unaUnidad;
		this.cantidad = cantidad;
		this.fecha = fecha;
	}

	public Producto getUnProducto() {
		return unProducto;
	}


	public Unidad getUnaUnidad() {
		return unaUnidad;
	}

	

	public double getCantidad() {
		return this.cantidad;
	}
	
	public double getPrecio(){
		return (this.getCantidad())*
		(this.getUnProducto().getPresentacion(this.getUnaUnidad()).getPrecioVenta(/*this.getFecha()*/));
	}
	
	public DateTime getFecha() {
		return this.fecha;
	}

	
}
