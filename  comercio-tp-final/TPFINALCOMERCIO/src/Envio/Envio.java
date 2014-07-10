package Envio;

import org.joda.time.DateTime;

import Cliente.Cliente;
import Comercio.Comercio;



public class Envio {

	private Cliente destinatario;
	private Double montoContrareembolso;
	private EstadoDeEnvio estadoDeEnvio;
	private DateTime fechaDeSalida;
	private Comercio comercio;

	/**
	 *CONSTRUCTOR
	 */
	public Envio(Cliente destinatario, Double montoContrareembolso, DateTime unaFecha, Comercio unComercio) {
		this.destinatario = destinatario;
		this.montoContrareembolso = montoContrareembolso;
		this.estadoDeEnvio = new Pendiente();
		this.fechaDeSalida = unaFecha;
		this.comercio = unComercio;
	}
	
	
	/**
	 *Salida del envio.
	 */
	public void enviar(){
		this.estadoDeEnvio.enviar(this);
	}
	
	/**
	 *Cobra el contrareembolso.
	 */
	public void cobrarContrareembolso(){
		this.estadoDeEnvio.cobrarContrareembolso(this, destinatario, montoContrareembolso, comercio);		
	}
	
	/**
	 *Reprograma la fecha.
	 */
	public void reprogramarFechaDeSalida(DateTime unaFecha){
		this.estadoDeEnvio.reprogramarFechaDeSalida(this, unaFecha);		
	}
	
	/**
	 *Cancela el envio.
	 */
	public void cancelarEnvio() {
		this.estadoDeEnvio.cancelarEnvio(this);
		
	}
	/**
	 *Se utiliza en caso de que el cliente no se encuentre.
	 */
	public void elClienteNoSeEncuentra(){
		this.estadoDeEnvio.elClienteNoSeEncuentra(this);
	}
	
	/**
	 *El estado actual del envio.
	 */
	public EstadoDeEnvio getEstadoDeEnvio() {
		return estadoDeEnvio;
	}
	
	/**
	 *Modifica el estado del envio.
     * @param estadoDeEnvio nuevo estado de envio.
	 */
	public void setEstadoDeEnvio(EstadoDeEnvio estadoDeEnvio) {
		this.estadoDeEnvio = estadoDeEnvio;
	}
	
	/**
	 *Fecha de salida del envio.
	 */
	public DateTime getFechaDeSalida() {
		return fechaDeSalida;
	}
	
	/**
	 *Modifica la fecha de salida del envio.
     * @param fechaDeSalida nueva fecha.
	 */
	 
	public void setFechaDeSalida(DateTime fechaDeSalida) {
		this.fechaDeSalida = fechaDeSalida;
	}
	
}
