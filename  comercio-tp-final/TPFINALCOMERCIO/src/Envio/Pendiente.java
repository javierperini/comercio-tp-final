package Envio;




public class Pendiente extends EstadoDeEnvio {


	@Override
	public void enviar(Envio unEnvio){
		unEnvio.setEstadoDeEnvio(new EnProceso());
	}
}