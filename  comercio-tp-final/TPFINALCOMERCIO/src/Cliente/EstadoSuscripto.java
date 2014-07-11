package Cliente;


public abstract class EstadoSuscripto{
	
	/**
	 * Este metodo envia un mail al cliente si se encuentra 
	 * suscripto al sistema de ofertas.  
	 * @param nombreOferta este parametro refiere al nombre de la oferta que se enviara por mail.
	 * @param unCliente este parametro refiere al cliente al que se le enviara el mail.
	 */
	public abstract void enviarMail(String nombreOferta, Cliente unCliente) ;
	
}
