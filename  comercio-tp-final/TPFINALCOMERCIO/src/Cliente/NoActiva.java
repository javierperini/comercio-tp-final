package Cliente;

import Excepciones.SinCuentaCorrienteException;
import Venta.VentaConCuentaCorriente;


public class NoActiva extends EstadoCuentaCorriente {
	/**
	 * CONSTRUCTOR
	 */
	public NoActiva(){}
	
	/**
	 * @throws SinCuentaCorrienteException Como el estado es NoActiva al pedir el saldo salta esta excepcion
	 */
	@Override
	public double getSaldo(CuentaCorriente cc) throws SinCuentaCorrienteException{
		throw new SinCuentaCorrienteException();
	}
	/**
	 * Pasa la cuenta corriente a estado activa y setea el saldo.
	 * @param saldo este parametro refiere al saldo a setear.
	 * @param cc este parametro refiere a la cuenta corriente a la cual setearle el saldo.
	 */
	@Override
	public void setSaldo(double saldo, CuentaCorriente cc) {
		cc.setEstadoCC(new Activa());
		cc.setSaldo(saldo);
	}
	/**
	 * @throws SinCuentaCorrienteException Como el estado es NoActiva al tratar de disminuir 
	 * el saldo salta esta excepcion.
	 */
	@Override
	public void disminuirSaldo(double dinero, CuentaCorriente cc) throws SinCuentaCorrienteException{
		throw new SinCuentaCorrienteException();			
	}
	/**
	 * @throws SinCuentaCorrienteException Como el estado es NoActiva al tratar de agregar una
	 * compra a la cuenta corriente salta esta excepcion. 
	 */
	@Override
	public void addCompra(VentaConCuentaCorriente compra, CuentaCorriente cc)
		throws SinCuentaCorrienteException {
		throw new SinCuentaCorrienteException();
	}		
}