package Comercio;

import org.joda.time.DateTime;



public class Envio {

	private Cliente destinatario;
	private Double montoContrareembolso;
	private EstadoDeEnvio estadoDeEnvio;
	private DateTime fechaDeSalida;
	private Comercio comercio;

	public Envio(Cliente destinatario, Double montoContrareembolso, DateTime unaFecha, Comercio unComercio) {
		this.destinatario = destinatario;
		this.montoContrareembolso = montoContrareembolso;
		this.estadoDeEnvio = new Pendiente();
		this.fechaDeSalida = unaFecha;
		this.comercio = unComercio;
	}
	
	
	
	public void enviar(){
		this.estadoDeEnvio.enviar(this);
	}
	
	public void cobrarContrareembolso(){
		this.estadoDeEnvio.cobrarContrareembolso(this, destinatario, montoContrareembolso, comercio);		
	}
	
	public void reprogramarFechaDeSalida(DateTime unaFecha){
		this.estadoDeEnvio.reprogramarFechaDeSalida(this, unaFecha);		
	}

	public void cancelarEnvio() {
		this.estadoDeEnvio.cancelarEnvio(this);
		
	}
	
	public void elClienteNoSeEncuentra(){
		this.estadoDeEnvio.elClienteNoSeEncuentra(this);
	}
	
	public EstadoDeEnvio getEstadoDeEnvio() {
		return estadoDeEnvio;
	}
	
	public void setEstadoDeEnvio(EstadoDeEnvio estadoDeEnvio) {
		this.estadoDeEnvio = estadoDeEnvio;
	}
	
   //TESTEAR
	public DateTime getFechaDeSalida() {
		return fechaDeSalida;
	}

	public void setFechaDeSalida(DateTime fechaDeSalida) {
		this.fechaDeSalida = fechaDeSalida;
	}
	
}
