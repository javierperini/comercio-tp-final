package Reportes;

import java.util.ArrayList;
import java.util.List;

import Venta.Venta;
import Cliente.Cliente;
import Comercio.Comercio;

public class FiltrarMontoPorCliente<T> extends Filtro<Double> {
	private Cliente cliente;

	public FiltrarMontoPorCliente(Cliente cliente){
		this.cliente=cliente;
		
	}

	@Override
	 protected List<Double> filtrar(Comercio comercio) {
		List<Double>resultado= new ArrayList<Double>();
		for(Cliente cAct: comercio.getCliente()){
			if(this.cliente.equals(cliente))
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
