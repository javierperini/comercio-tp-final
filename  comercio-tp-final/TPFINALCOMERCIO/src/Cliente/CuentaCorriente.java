package Cliente;

import java.util.List;

import Excepciones.SaldoInsuficienteException;
import Excepciones.SinCuentaCorrienteException;
import Movimiento.Venta;
import Movimiento.VentaConCuentaCorriente;



public class CuentaCorriente {
	
	private EstadoCuentaCorriente estadoCC;
	private double saldo;
	private List<Venta> comprasConCuentacorriente;
	
	public CuentaCorriente(){
		this.estadoCC = new NoActiva();
		this.setSaldo(0d);
	}
	
	public EstadoCuentaCorriente getEstadoCC() {
		return this.estadoCC;
	}

	public void setEstadoCC(EstadoCuentaCorriente estadoCC) {
		this.estadoCC = estadoCC;
	}
	/*
	 * El siguiente mensaje debe ser usado con cuidado, por eso se puso en protected 
	 * para que sea bien usado.
	 */
	protected double getSaldo(){
		return this.saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	//TESTEAR
	public List<Venta> getComprasConCuentacorriente() {
		return this.comprasConCuentacorriente;
	}
	//TESTEAR
	public void agregarCompraConCuentaCorriente(VentaConCuentaCorriente compra){
		this.comprasConCuentacorriente.add(compra);
	}
	
	public void aumentarSaldo(double unSaldo){
		this.getEstadoCC().setSaldo(unSaldo, this);
	}

	public void descontarSaldo(double unSaldo) throws SinCuentaCorrienteException, SaldoInsuficienteException {
		this.getEstadoCC().disminuirSaldo(unSaldo, this);
	}
	
	public double saldo() throws SinCuentaCorrienteException{
		return this.getEstadoCC().getSaldo(this);
	}
	//TESTEAR
	public void addCompra(VentaConCuentaCorriente compra) throws SinCuentaCorrienteException {
		this.getEstadoCC().addCompra(compra, this);
	}
	
	
}




