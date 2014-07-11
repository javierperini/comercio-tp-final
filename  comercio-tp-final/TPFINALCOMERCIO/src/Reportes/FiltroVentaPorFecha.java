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
	
	public void setFechaMin(DateTime fechaMin){
		this.fechaMin=fechaMin;
	}
	public void setFechaMax(DateTime fechaMax){
		this.fechaMax=fechaMax;
	}

	@Override
	protected List<Venta> filtrar(Comercio comercio) {
		List<Venta>resultado= new ArrayList<Venta>();
		for(Venta vAct: comercio.getVentas()){
				if(this.estasEntreEstaFechas(vAct.getFecha(),this. fechaMin,this. fechaMax))
					 resultado.add(vAct);
		}
		
		return resultado;
	}
	private boolean estasEntreEstaFechas(DateTime fecha1,DateTime fechaMin,DateTime fechaMax){
		return fecha1.isAfter(fechaMax) && fecha1.isBefore(fechaMin) || fecha1.isEqual(fechaMin) || fecha1.isEqual(fechaMax); 
	}


}
