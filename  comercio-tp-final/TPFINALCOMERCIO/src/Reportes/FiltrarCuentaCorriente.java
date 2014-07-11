package Reportes;

import java.util.ArrayList;
import java.util.List;

import Cliente.Cliente;
import Comercio.Comercio;
import Venta.Venta;

public class FiltrarCuentaCorriente<T> extends Filtro<Venta>{

	@Override
	protected List<Venta> filtrar(Comercio comercio) {
		List<Venta>resultado= new ArrayList<Venta>();
		for(Cliente cAct: comercio.getCliente()){
			
			resultado=this.agregarALaLista(resultado,cAct.getCuentaCorriente().getComprasConCuentacorriente());
		}
		
		return resultado;
	}

	private List<Venta> agregarALaLista(List<Venta> resultado,List<Venta> agregar) {
		for(Venta vAct: agregar){
			resultado.add(vAct);
		}
		return resultado;
	}

}
