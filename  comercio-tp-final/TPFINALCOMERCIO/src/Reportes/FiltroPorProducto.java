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

	@Override
	protected List<Venta> filtrar(Comercio comercio) {
		List<Venta>resultado= new ArrayList<Venta>();
		for(Venta vAct: comercio.getVentas()){
			if(this.seEncuentraProducto(producto,vAct.getOrdenes()))
				 resultado.add(vAct);
		}
		return  resultado;
	}
	
	public void setProducto(Producto producto){
		this.producto=producto;
	}
	
	private boolean seEncuentraProducto(Producto producto,List<OrdenDeCompra> ordenes) {
		boolean  resultado= false; 
		for(OrdenDeCompra oAct:ordenes){
			 	if(producto.equals(oAct.getUnProducto()))
			 		resultado=true;
		 }
		return resultado;
	}

}
