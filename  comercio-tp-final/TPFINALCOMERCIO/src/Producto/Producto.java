package Producto;

import java.util.List;

import Excepciones.NoTengoStock;

public class Producto {

	private String nombre;
	private String marca;
	private String descripcion;
	private String tipo;
	private List<Presentacion> ListPresentacion;
	
	public Producto(String nombre, String marca, String descripcion,String tipo, List<Presentacion> listPrese) {
		//(nombre,marca,descripcion,tipo,List<presentacion>)
		this.nombre=nombre;
		this.marca= marca;
		this.descripcion=descripcion;
		this.tipo=tipo;
		this.ListPresentacion= listPrese;
	}
	
	/**
	 * 
	 * @param unidad  de una presentacion especifica
	 * @return una presentacion si concuerda con la unidad pasada
	 */
     public Presentacion getPresentacion(Unidad unidad) {
		for(Presentacion preAct:this.ListPresentacion){
			if(preAct.getUnidad()==unidad)
				return  preAct;
			}
		return null;
	}
	
     /**
	 * 
	 * @return todas las presentaciones del producto
	 */
	public List<Presentacion> presentaciones() {
		
		return this.ListPresentacion;
	}
	
	/**
	 *  
	 * @param unidad  unidad  de una presentacion especifica
	 * @return una ubicacion de una presentacion especifica
	 */
	public Ubicacion getUbicacion(Unidad unidad) {
		
		return  this.getPresentacion(unidad).getUbicacion();
	}
	
	/**
	 *  Proposito: Cambiar el precio de una  presentacion
	 * @param unidad   de una presentacion especifica
	 * @param precio de una presentacion especifica
	 */
	public void cambiarPrecioDeVenta(Unidad unidad,double precio) {
	
		 this.getPresentacion(unidad).cambiarPrecio(precio);
	}
	/**
	 *  Proposito: Decrementar el stock de una presentacion especifica
	 * @param cantidad  numero  a decrementar
	 * @param unidad de una presentacion especifica
	 * @throws NoTengoStock exepcion que se ejecuta cuando no tengo stock para esta accion
	 */
	public void decrementarStock(double cantidad, Unidad unidad) throws NoTengoStock {
		
		this.getPresentacion(unidad).decrementarStock(cantidad);
	}
	
	/**
	 *  Proposito: Incrementar el stock de una presentacion especifica
	 * @param cantidad  numero  a decrementar
	 * @param unidad de una presentacion especifica
	 *
	 */
	public void incrementarStock(double cantidad, Unidad unidad) {
		
		this.getPresentacion(unidad).incrementarStock(cantidad);
	}
	
	/**
	 * 
	 * @param unidad de una presentacion especifica
	 * @return el stock total de una presentacion especifica
	 */
	public double stockTotal(Unidad unidad) {
		
		return this.getPresentacion(unidad).getStockTotal();
	}
	
	/**
	 * 
	 * @param unidad de una presentacion especifica
	 * @return el precio de venta de una presentacion especifica
	 */
	public double getPrecioVenta(Unidad unidad) {
		
		return this.getPresentacion(unidad).getPrecioVenta();
	}

}
