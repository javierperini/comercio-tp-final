package Envio;

import org.joda.time.DateTime;

import Cliente.Cliente;
import Comercio.Comercio;

public abstract class EstadoDeEnvio {
	
	/**
	 *Envia un pedido.
	 */
	public void enviar(Envio unEnvio) {
		System.out.println("No puedo realizar esta accion");
	}
	
	/**
	 *Cobra al cliente el contrareembolso.
     * @param unEnvio
	 * @param unDestinatario
	 * @param unMonto
	 * @param unComercio
	 */
	public void cobrarContrareembolso(Envio unEnvio, Cliente unDestinatario, Double unMonto, Comercio unComercio){
		System.out.println("No puedo realizar esta accion");
	}

	/**
	 *Asigna al envio una nueva fecha de salida
     * @param unEnvio
	 * @param unaFecha
	 */
	public void reprogramarFechaDeSalida(Envio unEnvio, DateTime unaFecha){
			System.out.println("No puedo realizar esta accion");
		}

	/**
	 *Cambia el estado del envio a Cancelado.
     * @param unEnvio
	 */
	public void cancelarEnvio(Envio unEnvio) {
			unEnvio.setEstadoDeEnvio(new Cancelado());
	}
	
	/**
	 *Si el cliente no se encuentra. Reprograma el envio.
     * @param unEnvio
	 */
	public void elClienteNoSeEncuentra(Envio unEnvio){
		System.out.println("No puedo realizar esta accion");
	}
	
}