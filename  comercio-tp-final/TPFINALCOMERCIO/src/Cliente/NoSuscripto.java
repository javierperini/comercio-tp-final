package Cliente;

public class NoSuscripto extends EstadoSuscripto {
	
	/**
	 * Este metodo no hace nada porque si el cliente 
	 * no esta suscripto al sistema de ofertas no se le envia ningun mail.
	 */
	@Override
	public void enviarMail(String nombreOferta, Cliente unCliente){}

}
