package Reportes;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import Venta.Venta;
import Cliente.Cliente;
import Comercio.Comercio;

public class FiltrarMontoClientePorFecha<T> extends Filtro<Double> {
	 DateTime fecha;
	public FiltrarMontoClientePorFecha(DateTime fecha){
		this.fecha=fecha;
	}

	@Override
	 protected List<Double> filtrar(Comercio comercio) {
		List<Double>resultado= new ArrayList<Double>();
		for(Cliente cAct: comercio.getCliente()){
			
			resultado.add(this.gastoDeCompras(cAct));
		}
		
		return resultado;
	}

	private Double gastoDeCompras(Cliente cliente) {
		Double contador= 0d;
		for(Venta vAct:cliente.getCompras()){
		 if(this.fecha.isEqual(vAct.getFecha()))
			contador+= vAct.getMontoTotal();
		}
		return contador;
	}
}
