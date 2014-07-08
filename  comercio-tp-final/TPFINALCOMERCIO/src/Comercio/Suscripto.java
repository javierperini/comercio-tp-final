package Comercio;


public class Suscripto extends EstadoSuscripto{
	
	private Cliente cliente;
	
	public Suscripto(Cliente cliente){
		this.cliente = cliente;
	}
	//TESTEAR
	@Override
	public void enviarMail(String nombreOferta) {
		System.out.println("Se genero una nueva oferta!" + nombreOferta);
	}
	//TESTEAR
	public Cliente getCliente() {
		return this.cliente;
	}
	//TESTEAR
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
