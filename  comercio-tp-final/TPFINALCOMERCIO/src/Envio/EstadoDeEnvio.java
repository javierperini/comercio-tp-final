package Envio;

import org.joda.time.DateTime;

import Cliente.Cliente;
import Comercio.Comercio;




public abstract class EstadoDeEnvio {
	
	public String informarEstado(){
		return null;		
	}
	
	public void enviar(Envio unEnvio) {
		System.out.println("No puedo realizar esta accion");
	}
	
	public void cobrarContrareembolso(Envio unEnvio, Cliente unDestinatario, Double unMonto, Comercio unComercio){
		System.out.println("No puedo realizar esta accion");
	}

	public void reprogramarFechaDeSalida(Envio unEnvio, DateTime unaFecha){
			System.out.println("No puedo realizar esta accion");
		}

	public void cancelarEnvio(Envio unEnvio) {
			unEnvio.setEstadoDeEnvio(new Cancelado());
	}
	public void elClienteNoSeEncuentra(Envio unEnvio){
		System.out.println("No puedo realizar esta accion");
	}
	
}