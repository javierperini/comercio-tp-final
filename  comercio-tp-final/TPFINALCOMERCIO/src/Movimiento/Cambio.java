package Movimiento;

import java.util.List;

import org.joda.time.DateTime;

import Cliente.Cliente;
import Comercio.Comercio;
import Comercio.OrdenDeCompra;
import Excepciones.NoTengoStock;




public class Cambio extends Movimiento {
	
	protected List<OrdenDeCompra> listaDeProductosNueva;
	
	public Cambio(Cliente unCliente, List<OrdenDeCompra> listadoDeProductos, List<OrdenDeCompra> listaDeProductosNueva,DateTime fecha,Comercio comercio) {
		super(unCliente, listadoDeProductos,fecha,comercio);
		this.listaDeProductosNueva = listaDeProductosNueva;
	}	
	
	public Double calcularImporteDeLaListaNueva(){
		Double monto = 0d;
		for (OrdenDeCompra orden : this.listaDeProductosNueva) {
			monto+=orden.getPrecio();
		}
		return monto;
	}
	//TESTEAR
	public boolean elMontoDeLosProductosSolicitadosExcedeAlDeLosDevueltos(){
		return (this.calcularImporte()<this.calcularImporteDeLaListaNueva());
	}
	//TESTEAR
	@Override
	public void modificarStock() {
		try{
			incrementarStock();
			drecrementarStock();
		}catch(NoTengoStock exepcion){
			System.out.println("El producto no cuenta con stock suficiente");
		}		
	}

	private void drecrementarStock() throws NoTengoStock {
		for (OrdenDeCompra orden : this.listaDeProductosNueva) {
			orden.getUnProducto().decrementarStock(orden.getCantidad(), orden.getUnaUnidad());
		}
	}

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
