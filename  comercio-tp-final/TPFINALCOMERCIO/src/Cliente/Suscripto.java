package Cliente;

public class Suscripto extends EstadoSuscripto{
	/**
	 * CONSTRUCTOR
	 */
	public Suscripto(){}
	/**
	 * Envia un mail al cliente
	 * @param nombreOferta este parametro refiere al nombre de la oferta que se enviara por mail.
	 * @param unCliente este parametro refiere al cliente al que se le enviara el mail
	 */
	@Override
	public void enviarMail(String nombreOferta, Cliente unCliente) {
		unCliente.enviarMail(nombreOferta);
	}
}
