package Movimiento;

import java.util.List;

import org.joda.time.DateTime;

import Cliente.Cliente;
import Comercio.Comercio;
import Comercio.OrdenDeCompra;
import Excepciones.NoTengoStock;

public class Cambio extends Movimiento {
	
	protected List<OrdenDeCompra> listaDeProductosNueva;
	
	/**
	 *CONSTRUCTOR
	 */	
	public Cambio(Cliente unCliente, List<OrdenDeCompra> listadoDeProductos, List<OrdenDeCompra> listaDeProductosNueva,DateTime fecha,Comercio comercio) {
		super(unCliente, listadoDeProductos,fecha,comercio);
		this.listaDeProductosNueva = listaDeProductosNueva;
	}	
	
	/**
	 *-Calcular el importe total de la nueva lista de productos que el cliente quiere a cambio.
	 */
	public Double calcularImporteDeLaListaNueva(){
		Double monto = 0d;
		for (OrdenDeCompra orden : this.listaDeProductosNueva) {
			monto+=orden.getPrecio();
		}
		return monto;
	}
	//TESTEAR
	/**
	 *Retorna true si el monto de la lista que quiere a cambio el cliente supera el monto de la lista que quiere cambiar.
	 */
	public boolean elMontoDeLosProductosSolicitadosExcedeAlDeLosDevueltos(){
		return (this.calcularImporte()<this.calcularImporteDeLaListaNueva());
	}
	//TESTEAR
	/**
	 *Modifica el Stock.
	 */
	@Override
	public void modificarStock() {
		try{
			incrementarStock();
			drecrementarStock();
		}catch(NoTengoStock exepcion){
			System.out.println("El producto no cuenta con stock suficiente");
		}		
	}

	/**
	 *Decrementa el stock de los productos que se encuentran en la lista que el cliente quiere a cambio.
	 */
	private void drecrementarStock() throws NoTengoStock {
		for (OrdenDeCompra orden : this.listaDeProductosNueva) {
			orden.getUnProducto().decrementarStock(orden.getCantidad(), orden.getUnaUnidad());
		}
	}
	
	/**
	 *Incrementa el stock de los productos que el cliente devolvio.
	 */
	private void incrementarStock() {
		for (OrdenDeCompra orden : this.listadoDeProductos) {
			orden.getUnProducto().incrementarStock(orden.getCantidad(), orden.getUnaUnidad());
		}
	}
	
	/**Si el cliente tiene saldo a favor se lo carga en su cuenta corriente**/
	public void cargarDiferenciaEnCuentaCorriente(){
		this.cliente.depositar(this.calcularImporte() - this.calcularImporteDeLaListaNueva());
	}
	
}
