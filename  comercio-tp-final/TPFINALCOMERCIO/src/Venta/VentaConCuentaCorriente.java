package Venta;

import java.util.List;

import org.joda.time.DateTime;

import Cliente.Cliente;
import Comercio.Comercio;
import Comercio.OrdenDeCompra;
import Excepciones.SaldoInsuficienteException;
import Excepciones.SinCuentaCorrienteException;



public class VentaConCuentaCorriente extends Venta{

	public VentaConCuentaCorriente(Cliente unCliente,List<OrdenDeCompra> listadoDeProductos,DateTime fecha,Comercio comercio) {
		super(unCliente, listadoDeProductos, fecha, comercio);
	}
	
	
	public void cobrar(Cliente unCliente) throws SinCuentaCorrienteException, SaldoInsuficienteException {
	      unCliente.descontarSaldo(this.calcularImporte());
	}
	
	public void agregarVentaAlCliente() throws SinCuentaCorrienteException{
			this.getCliente().addCompra(this);
			this.getCliente().getCuentaCorriente().addCompra(this);
		
	}
}

