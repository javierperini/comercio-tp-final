package Envio;

public class Pendiente extends EstadoDeEnvio {
	/**
	 *Cambia el estado del envio a EnProceso.
     * @param unEnvio
	 */
	@Override
	public void enviar(Envio unEnvio){
		unEnvio.setEstadoDeEnvio(new EnProceso());
	}
}