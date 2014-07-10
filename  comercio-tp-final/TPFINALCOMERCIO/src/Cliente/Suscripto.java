package Cliente;

public class Suscripto extends EstadoSuscripto{
	
	public Suscripto(){}
	
	@Override
	public void enviarMail(String nombreOferta, Cliente unCliente) {
		unCliente.enviarMail(nombreOferta);
	}
}
