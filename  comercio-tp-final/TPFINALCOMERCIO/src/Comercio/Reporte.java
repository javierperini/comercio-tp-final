package Comercio;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import Producto.Producto;
import Venta.Venta;

public class Reporte {

	private Comercio comercio;

	public Reporte(Comercio comercio) {
	  this.comercio= comercio;
	}

	public List<Venta> filtrarVentaPorFecha(DateTime fechaMin,DateTime fechaMax) {
		List<Venta>resultado= new ArrayList<Venta>();
		for(Venta vAct: this.comercio.getVentas()){
				if(this.estasEntreEstaFechas(vAct.getFecha(), fechaMin, fechaMax))
					 resultado.add(vAct);
		}
		
		return resultado;
	}
	private boolean estasEntreEstaFechas(DateTime fecha1,DateTime fechaMin,DateTime fechaMax){
		return fecha1.isAfter(fechaMax) && fecha1.isBefore(fechaMin) || fecha1.isEqual(fechaMin) || fecha1.isEqual(fechaMax); 
	}

	public List<Venta> filtrarVentaPorProducto(Producto producto) {
		List<Venta>resultado= new ArrayList<Venta>();
		for(Venta vAct: this.comercio.getVentas()){
			if(this.seEncuentraProducto(producto,vAct.getOrdenes()))
				 resultado.add(vAct);
		}
		return resultado;
	}

	private boolean seEncuentraProducto(Producto producto,List<OrdenDeCompra> ordenes) {
		boolean  resultado= false; 
		for(OrdenDeCompra oAct:ordenes){
			 	if(producto.equals(oAct.getUnProducto()))
			 		resultado=true;
		 }
		return resultado;
	}

	public List<Venta> filtrarVentaPorMonto(double min, double max) {
		List<Venta>resultado= new ArrayList<Venta>();
		for(Venta vAct: this.comercio.getVentas()){
				if(this.entreEsteMonto(vAct.calcularImporte(),min,max))
					 resultado.add(vAct);
		}
		
		return resultado;
	}

	private boolean entreEsteMonto(double n, double min,double max) {
		
		return  n>= min && n<=max ;
	}
	

}
