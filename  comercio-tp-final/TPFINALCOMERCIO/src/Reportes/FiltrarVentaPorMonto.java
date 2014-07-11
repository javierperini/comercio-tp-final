package Reportes;

import java.util.ArrayList;
import java.util.List;

import Comercio.Comercio;
import Venta.Venta;

public class FiltrarVentaPorMonto<E> extends Filtro<Venta> {
	private double min;
	private double max;

	public FiltrarVentaPorMonto(double min,double max){
		this.min=min;
		this.max=max;
	}
	
	/**
	 * Setea un precio
	 * @param min precio
	 */
	public void setMin(double min){
		this.min=min;
	}
	 
	/**
	  *  Setea un precio
	  * @param max precio
	  */
	public void setMax(double max){
		this.max=max;
	}
	
	/**
	 *  Devuelve las venta que se encuentran entre dos montos
	 */
	@Override
	protected List<Venta> filtrar(Comercio comercio) {
		List<Venta>resultado= new ArrayList<Venta>();
		for(Venta vAct: comercio.getVentas()){
				if(this.entreEsteMonto(vAct.calcularImporte(),this.min,this.max))
					 resultado.add(vAct);
		}
		
		return resultado;
	}
	/**
	 * Devuelve si un numero esta entre otros dos numeros
	 * @param n numero  a buscar
	 * @param min numero minimo del rango
	 * @param max numero maximio del rango
	 * @return
	 */
	private boolean entreEsteMonto(double n, double min,double max) {
		
		return  n>= min && n<=max ;
	}



}
