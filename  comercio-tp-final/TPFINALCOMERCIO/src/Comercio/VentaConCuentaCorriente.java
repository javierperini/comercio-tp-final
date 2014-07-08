package Comercio;

import java.util.List;

import org.joda.time.DateTime;

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

