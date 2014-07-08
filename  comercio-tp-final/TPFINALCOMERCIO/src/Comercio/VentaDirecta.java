package Comercio;
import java.util.List;

import org.joda.time.DateTime;

import Excepciones.SaldoInsuficienteException;
import Excepciones.SinCuentaCorrienteException;



public class VentaDirecta extends Venta{
	
	
	public VentaDirecta(Cliente unCliente, List<OrdenDeCompra> listadoDeProductos, DateTime unaFecha,Comercio comercio) {
		super(unCliente, listadoDeProductos,unaFecha,comercio);
		
	}

	


	
}