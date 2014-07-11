package Movimiento;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import Cliente.Cliente;
import Comercio.Comercio;
import Comercio.OrdenDeCompra;

public class Devolucion extends Movimiento {

	
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
	/**
	 * @return retorna el cliente asociado a la devolucion.
	 */
	public Cliente getCliente() {
		return cliente;
	}
	/**
	 * Deposita el importe de las ordenes de compra que esta devolviendo el cliente en su cuenta corriente.
	 */
	public void devolverPlata() {
		this.getCliente().depositar(this.calcularImporte());
	}
	/**
	 * Incrementa el stock del comercio dependiendo de la cantidad de productos que se esten devolviendo.
	 */
	@Override
	public void modificarStock() {
		for(OrdenDeCompra o: this.getOrdenes()){
			o.getUnProducto().getPresentacion(o.getUnaUnidad()).incrementarStock(o.getCantidad());
		}
		
	}

	

}
