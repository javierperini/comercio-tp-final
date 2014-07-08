package Comercio;

import org.joda.time.DateTime;


public class Reprogramado extends EstadoDeEnvio {

	
	@Override
	public void reprogramarFechaDeSalida(Envio unEnvio, DateTime unaFecha){
		unEnvio.setFechaDeSalida(unaFecha);
		unEnvio.setEstadoDeEnvio(new Pendiente());
	}
}
