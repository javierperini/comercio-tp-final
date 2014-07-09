package Envio;

import Cliente.Cliente;
import Comercio.Comercio;




public class EnProceso extends EstadoDeEnvio {

	EstadoDeEnvio posibleEstado;
	

	@Override
	public void cobrarContrareembolso(Envio unEnvio, Cliente unDestinatario, Double unMonto, Comercio unComercio){
			unComercio.incrementarMonto(unMonto);
			unEnvio.setEstadoDeEnvio(new Entregado());
	}
	
	@Override
	public void elClienteNoSeEncuentra(Envio unEnvio){
		unEnvio.setEstadoDeEnvio(new Reprogramado());;
	}
}