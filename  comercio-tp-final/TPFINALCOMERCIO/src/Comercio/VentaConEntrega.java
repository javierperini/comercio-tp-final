package Venta;
import java.util.List;

import org.joda.time.DateTime;

import Cliente.Cliente;
import Comercio.Comercio;
import Comercio.OrdenDeCompra;
import Envio.Envio;
import Excepciones.SaldoInsuficienteException;
import Excepciones.SinCuentaCorrienteException;




public class VentaConEntrega extends Venta{
	
	protected Envio envio;


	public VentaConEntrega(Cliente unCliente, List<OrdenDeCompra> listadoDeProductos, DateTime unaFecha, Comercio unComercio) {
		super(unCliente, listadoDeProductos,unaFecha,unComercio);
		
	}

	public void enviar(DateTime unaFechaDeSalida) {
		envio=new Envio(cliente, this.calcularImporte(), unaFechaDeSalida, comercio);
	}
	
	public Envio getEnvio() {
		return envio;
	}



	
}
