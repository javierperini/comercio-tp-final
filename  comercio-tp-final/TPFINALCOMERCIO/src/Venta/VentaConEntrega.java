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

	/**
	 *CONSTRUCTOR
	 */
	public VentaConEntrega(Cliente unCliente, List<OrdenDeCompra> listadoDeProductos, DateTime unaFecha, Comercio unComercio){
		super(unCliente, listadoDeProductos,unaFecha,unComercio);
		
	}
	
	/**
	 *Instancia un envio, que correspondera a la venta, y lo envia.
     * @param unaFechaDeSalida la fecha de salida asignada al envio.
	 */
	public void enviar(DateTime unaFechaDeSalida) {
		envio=new Envio(cliente, this.calcularImporte(), unaFechaDeSalida, comercio);
		envio.enviar();
	}
	
	/**
	 *El envio correspondiente.
	 */
	public Envio getEnvio() {
		return envio;
	}

	public void cobrarContrareembolso() {
		this.envio.cobrarContrareembolso();
	}



	
}
