package Reportes;

import java.util.ArrayList;
import java.util.List;

import Comercio.Comercio;
import Comercio.OrdenDeCompra;
import Producto.Producto;
import Venta.Venta;

public class FiltroPorProducto<E> extends Filtro<Venta> {
	private Producto producto;
	
	public FiltroPorProducto(Producto producto){
		this.producto=producto;
	}
	/**
	 *  Devuelve las ventas que tiene cierto producto
	 */
	@Override
	protected List<Venta> filtrar(Comercio comercio) {
		List<Venta>resultado= new ArrayList<Venta>();
		for(Venta vAct: comercio.getVentas()){
			if(this.seEncuentraProducto(producto,vAct.getOrdenes()))
				 resultado.add(vAct);
		}
		return  resultado;
	}
	/**
	 *  Setea un producto a buscar
	 * @param producto producto a buscar
	 */
	public void setProducto(Producto producto){
		this.producto=producto;
	}
	
	/**
	 * Devuelve si un producto esta en esas ordenes de compras
	 * @param producto a buscar
	 * @param ordenes lista de ordenes de compra
	 * @return
	 */
	private boolean seEncuentraProducto(Producto producto,List<OrdenDeCompra> ordenes) {
		boolean  resultado= false; 
		for(OrdenDeCompra oAct:ordenes){
			 	if(producto.equals(oAct.getUnProducto()))
			 		resultado=true;
		 }
		return resultado;
	}

}
