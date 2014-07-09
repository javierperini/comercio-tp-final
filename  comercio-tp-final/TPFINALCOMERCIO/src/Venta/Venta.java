package Venta;
import java.util.List;

import org.joda.time.DateTime;

import Cliente.Cliente;
import Comercio.Comercio;
import Comercio.OrdenDeCompra;
import Excepciones.NoTengoStock;
import Excepciones.SaldoInsuficienteException;
import Excepciones.SinCuentaCorrienteException;
import Movimiento.Movimiento;




public abstract class Venta extends Movimiento{
	
	
	/**
	 *CONSTRUCTOR
	 */
	public Venta(Cliente unCliente, List<OrdenDeCompra> listadoDeProductos,DateTime fecha,Comercio comercio) {
		super(unCliente, listadoDeProductos,fecha,comercio);
	}

	
	//TESTEAR
	/**
	 *Decrementa el stock de los productos que estan en la lista dada por el cliente.
	 */
	public void modificarStock(){
		try{
			for (OrdenDeCompra orden : this.listadoDeProductos) {
				orden.getUnProducto().decrementarStock(orden.getCantidad(), orden.getUnaUnidad());
			}
		}catch(NoTengoStock exepcion){
			System.out.println("El producto no cuenta con stock suficiente");
		}
	}
	
	/**
	 *Registra la venta en la lista de compras hechas del cliente.
	 */
	public void agregarVentaAlCliente() throws SinCuentaCorrienteException{
		this.getCliente().addCompra(this);
	}
	
	/**
	 *Determina si la venta pertenece a tal cliente y si se hizo en una fecha determinada.
     * @param cliente
	 * @param ordenCompras
	 * @param fecha
	 */
	public boolean perteneceA(Cliente cliente,List<OrdenDeCompra> ordenCompras, DateTime fecha) {
		
		return this.getFecha().isEqual(fecha) && this.getCliente().equals(cliente) ;
	}

}
