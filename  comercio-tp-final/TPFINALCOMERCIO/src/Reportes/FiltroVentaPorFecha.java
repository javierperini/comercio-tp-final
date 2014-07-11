package Reportes;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import Comercio.Comercio;
import Venta.Venta;

public class FiltroVentaPorFecha<E> extends Filtro<Venta> {
	private DateTime fechaMin;
	private DateTime fechaMax;
	
	public FiltroVentaPorFecha(DateTime fechaMin,DateTime fechaMax){
		this.fechaMin=fechaMin;
		this.fechaMax=fechaMax;
	}
	/**
	 * Setea una fecha
	 * @param fechaMin una fecha
	 */
	public void setFechaMin(DateTime fechaMin){
		this.fechaMin=fechaMin;
	}
	
	/**
	 * Setea una fecha
	 * @param fechaMax una fecha
	 */
	public void setFechaMax(DateTime fechaMax){
		this.fechaMax=fechaMax;
	}
	
	/**
	 *  filtra ventas entre fechas
	 */
	@Override
	protected List<Venta> filtrar(Comercio comercio) {
		List<Venta>resultado= new ArrayList<Venta>();
		for(Venta vAct: comercio.getVentas()){
				if(this.estasEntreEstaFechas(vAct.getFecha(),this. fechaMin,this. fechaMax))
					 resultado.add(vAct);
		}
		
		return resultado;
	}
	/**
	 *  
	 * @param fecha1  fecha a buscar
	 * @param fechaMin fecha minima del rango
	 * @param fechaMax fecha maxima del rango
	 * @return si una fecha esta entre dos fecha
	 */
	private boolean estasEntreEstaFechas(DateTime fecha1,DateTime fechaMin,DateTime fechaMax){
		return fecha1.isAfter(fechaMax) && fecha1.isBefore(fechaMin) || fecha1.isEqual(fechaMin) || fecha1.isEqual(fechaMax); 
	}


}
