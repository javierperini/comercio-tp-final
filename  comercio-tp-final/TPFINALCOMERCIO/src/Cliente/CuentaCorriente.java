package Cliente;

import java.util.ArrayList;
import java.util.List;

import Excepciones.SaldoInsuficienteException;
import Excepciones.SinCuentaCorrienteException;
import Venta.Venta;
import Venta.VentaConCuentaCorriente;

public class CuentaCorriente {
	
	private EstadoCuentaCorriente estadoCC;
	private double saldo;
	private List<Venta> comprasConCuentacorriente;
	
	/**
	 * CONSTRUCTOR
	 */
	public CuentaCorriente(){
		this.comprasConCuentacorriente = new ArrayList<Venta>();
		this.estadoCC = new NoActiva();
		this.setSaldo(0d);
	}
	
	/**
	 * 
	 * @return retorna el estado de la cuenta corriente
	 */
	public EstadoCuentaCorriente getEstadoCC() {
		return this.estadoCC;
	}
	
	/**
	 * Setea el estado de la cuenta corriente.
	 * @param estadoCC este parametro refiere al estado que se le seteara a la cuenta corriente.
	 */
	public void setEstadoCC(EstadoCuentaCorriente estadoCC) {
		this.estadoCC = estadoCC;
	}
	
	/**
	 * Para ser usado solo por las sublcases.
	 * @return el saldo de la cuenta corriente. 
	 */
	protected double getSaldo(){
		return this.saldo;
	}

	/**
	 * Setea el saldo en la cuenta corriente.
	 * @param saldo este parametro refiere al saldo que se seteara en la cuenta corriente.
	 */
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	/**
	 * 
	 * @return retorna la lista de compras realizadas con cuenta corriente.
	 */
	public List<Venta> getComprasConCuentacorriente() {
		return this.comprasConCuentacorriente;
	}
	
	/**
	 * Agrega una compra a la cuenta corriente.
	 * Para ser usado solo por el estado.
	 * @param compra este parametro refiere a la compra que se agregara
	 */
	public void agregarCompraConCuentaCorriente(VentaConCuentaCorriente compra){
		this.comprasConCuentacorriente.add(compra);
	}
	
	/**
	 * Aumenta el saldo de la cuenta corriente
	 * @param unSaldo este parametro refiere al saldo que se le sumara a la cuenta corriente
	 */
	public void aumentarSaldo(double unSaldo){
		this.getEstadoCC().setSaldo(unSaldo, this);
	}

	/**
	 * Descuenta saldo de la cuenta corriente.
	 * @param unSaldo este parametro refiere al saldo que se descontara.
	 * @throws SinCuentaCorrienteException Si no hubiece una cuenta corriente activa saltaría ésta excepcion.
	 * @throws SaldoInsuficienteException Si no tuviece suficiente saldo para descontar  saltaroa esta excepcion.
	 */
	public void descontarSaldo(double unSaldo) throws SinCuentaCorrienteException, SaldoInsuficienteException {
		this.getEstadoCC().disminuirSaldo(unSaldo, this);
	}
	
	/**
	 * 
	 * @return retorna el saldo de la cuenta corriente.
	 * @throws SinCuentaCorrienteException Si no huviece una cuenta corriente activa saltaria esta excepcion.
	 */
	public double saldo() throws SinCuentaCorrienteException{
		return this.getEstadoCC().getSaldo(this);
	}
	
	/**
	 * Agrega una compra a la cuenta corriente.
	 * @param compra este parametro refiere a la compra a agregar
	 * @throws SinCuentaCorrienteException Si nohuviece una cuenta corriente activa saltaria esta excepcion
	 */
	public void addCompra(VentaConCuentaCorriente compra) throws SinCuentaCorrienteException {
		this.getEstadoCC().addCompra(compra, this);
	}
	
	
}




