package Venta;
import java.util.List;

import org.joda.time.DateTime;

import Cliente.Cliente;
import Comercio.Comercio;
import Comercio.OrdenDeCompra;
import Excepciones.SaldoInsuficienteException;
import Excepciones.SinCuentaCorrienteException;

public class VentaDirecta extends Venta{
	
	/**
	 *CONSTRUCTOR
	 */
	public VentaDirecta(Cliente unCliente, List<OrdenDeCompra> listadoDeProductos, DateTime unaFecha,Comercio comercio){
		super(unCliente, listadoDeProductos,unaFecha,comercio);
	}	
}