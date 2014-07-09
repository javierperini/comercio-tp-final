package Envio;

import Cliente.Cliente;
import Comercio.Comercio;




public class EnProceso extends EstadoDeEnvio {

	EstadoDeEnvio posibleEstado;
	
	/**
	 *1-Suma el contrareembolso al monto del comercio.
	 *2-Cambia el estado del envio a Entregado.
     * @param unEnvio
	 * @param unDestinatario
	 * @param unMonto
	 * @param unComercio
	 */
	@Override
	public void cobrarContrareembolso(Envio unEnvio, Cliente unDestinatario, Double unMonto, Comercio unComercio){
			unComercio.incrementarMonto(unMonto);
			unEnvio.setEstadoDeEnvio(new Entregado());
	}
	
	/**
	 *Si el cliente no se encuentra. Cambia el estado del envio a Reprogramado.
     * @param unEnvio
	 */
	@Override
	public void elClienteNoSeEncuentra(Envio unEnvio){
		unEnvio.setEstadoDeEnvio(new Reprogramado());;
	}
}