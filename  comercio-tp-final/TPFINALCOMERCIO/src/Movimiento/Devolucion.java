package Movimiento;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import Cliente.Cliente;
import Comercio.Comercio;
import Comercio.OrdenDeCompra;

public class Devolucion extends Movimiento {

	private List<OrdenDeCompra>ordenes = new ArrayList<OrdenDeCompra>();
	/**
	 * CONSTRUCTOR
	 * @param unCliente 
	 * @param listadoDeProductos
	 * @param fecha
	 * @param comercio
	 */
	public Devolucion(Cliente unCliente, List<OrdenDeCompra> listadoDeProductos,DateTime fecha,Comercio comercio){		
		super(unCliente,listadoDeProductos,fecha,comercio);
	}

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
