package Movimiento;
import java.util.List;

import org.joda.time.DateTime;

import Cliente.Cliente;
import Comercio.Comercio;
import Comercio.OrdenDeCompra;
import Excepciones.NoTengoStock;
import Excepciones.SaldoInsuficienteException;
import Excepciones.SinCuentaCorrienteException;




public abstract class Venta extends Movimiento{

	protected int codigoDeVenta;
	
	
	
	public Venta(Cliente unCliente, List<OrdenDeCompra> listadoDeProductos,DateTime fecha,Comercio comercio) {
		super(unCliente, listadoDeProductos,fecha,comercio);
		
	   codigoDeVenta=(int)Math.random();
	}

	
	//TESTEAR
	public void modificarStock(){
		try{
			for (OrdenDeCompra orden : this.listadoDeProductos) {
				orden.getUnProducto().decrementarStock(orden.getCantidad(), orden.getUnaUnidad());
			}
		}catch(NoTengoStock exepcion){
			System.out.println("El producto no cuenta con stock suficiente");
		}
	}
	

	public boolean perteneceA(Cliente cliente,List<OrdenDeCompra> ordenCompras, DateTime fecha) {
		
		return this.getFecha().isEqual(fecha) && this.getCliente().equals(cliente) ;
	}

}
