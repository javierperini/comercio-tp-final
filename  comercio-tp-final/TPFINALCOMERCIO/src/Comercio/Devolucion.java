package Comercio;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

public class Devolucion extends Movimiento {
	

	private List<OrdenDeCompra>ordenes = new ArrayList<OrdenDeCompra>();
	
	public Devolucion(Cliente unCliente, List<OrdenDeCompra> listadoDeProductos,DateTime fecha,Comercio comercio){		
		super(unCliente,listadoDeProductos,fecha,comercio);
	}

	//Quien le devuelve la plata al comercio? Xq si lo hace devolucion debe conocer al comercio.

	public Cliente getCliente() {
		return cliente;
	}

	public void devolverPlata() {
		this.getCliente().depositar(this.calcularImporte());
	}

	@Override
	public void modificarStock() {
		for(OrdenDeCompra o: this.getOrdenes()){
			o.getUnProducto().getPresentacion(o.getUnaUnidad()).incrementarStock(o.getCantidad());
		}
		
	}

	

}
