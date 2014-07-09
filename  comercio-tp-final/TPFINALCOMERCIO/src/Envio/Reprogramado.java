package Envio;

import org.joda.time.DateTime;


public class Reprogramado extends EstadoDeEnvio {

	/**
	 *1-Asigna una nueva fecha de salida al envio.
	 *2-Cambia el estado del envio a Pendiente.
     * @param unEnvio
	 * @param unaFecha
	 */
	@Override
	public void reprogramarFechaDeSalida(Envio unEnvio, DateTime unaFecha){
		unEnvio.setFechaDeSalida(unaFecha);
		unEnvio.setEstadoDeEnvio(new Pendiente());
	}
}
