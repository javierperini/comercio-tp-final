package Reportes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Venta.Venta;
import Cliente.Cliente;
import Comercio.Comercio;

public class FiltrarMontoCliente<T> extends Filtro<Double> {

	/**
	 * Devuelve los gastos de los clientes
	 */
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
			contador+= vAct.getMontoTotal();
		}
		return contador;
	}

}
