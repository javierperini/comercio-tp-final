package Comercio;

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
	//TESTEAR
	public Presentacion getPresentacion(Unidad unidad) {
		for(Presentacion preAct:this.ListPresentacion){
			if(preAct.getUnidad()==unidad)
				return  preAct;
			}
		return null;
	}
	//TESTEAR
	public List<Presentacion> presentaciones() {
		
		return this.ListPresentacion;
	}

	public Ubicacion getUbicacion(Unidad unidad) {
		
		return  this.getPresentacion(unidad).getUbicacion();
	}

	public void cambiarPrecioDeVenta(Unidad unidad,double precio) {
	
		 this.getPresentacion(unidad).cambiarPrecio(precio);
	}

	public void decrementarStock(double cantidad, Unidad unidad) throws NoTengoStock {
		
		this.getPresentacion(unidad).decrementarStock(cantidad);
	}

	public void incrementarStock(double cantidad, Unidad unidad) {
		
		this.getPresentacion(unidad).incrementarStock(cantidad);
	}

	public double stockTotal(Unidad unidad) {
		
		return this.getPresentacion(unidad).getStockTotal();
	}

	public double getPrecioVenta(Unidad unidad) {
		
		return this.getPresentacion(unidad).getPrecioVenta();
	}

}
