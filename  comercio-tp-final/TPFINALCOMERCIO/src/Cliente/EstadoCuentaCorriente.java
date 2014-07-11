package Cliente;

import Excepciones.SaldoInsuficienteException;
import Excepciones.SinCuentaCorrienteException;
import Venta.VentaConCuentaCorriente;

public abstract class EstadoCuentaCorriente {
	/**
	 * 
	 * @param cc este parametro refiere a la cuenta corriente de la cual se retornara el saldo.
	 * @return retorna el saldo de la cuenta corriente.
	 * @throws SinCuentaCorrienteException si la cuenta corriente pasada por parametro no
	 * estuviece activa saltaria esta excepcion.
	 */
	public abstract double getSaldo(CuentaCorriente cc) throws SinCuentaCorrienteException;
	/**
	 * Setea el saldo en la cuenta corriente, de estar en estado NoActiva la pasa a Activa.
	 * @param saldo este parametro refiere al saldo a setear en la cuenta corriente.
	 * @param cc este parametro refiere a la cuenta corriente a la cual se le seteara el saldo.
	 */
	public abstract void setSaldo(double saldo, CuentaCorriente cc);
	/**
	 * Disminuye el saldo de la cuenta corriente.
	 * @param dinero este parametro refiere al monto a disminuir de la cuenta corriente.
	 * @param cc este parametro refiere a la cuenta corriente a la cual se le descontara el saldo.
	 * @throws SinCuentaCorrienteException si no huviece una cuenta corriente a la cual descontarle
	 * algun saldo saltaria esta excepcion.
	 * @throws SaldoInsuficienteException si la cuenta corriente no tuviece saldo suficiente 
	 * saltaria esta excepcion.
	 */
	public abstract void disminuirSaldo(double dinero, CuentaCorriente cc) throws SinCuentaCorrienteException, SaldoInsuficienteException;
	/**
	 * Agrega una venta a la cuenta corriente
	 * @param compra este parametro refiere a la venta a agregar
	 * @param cc este parametro refiere a la cuenta corriente a la cual se le agregara la venta
	 * @throws SinCuentaCorrienteException Si no huviece una cuenta corriente a la cual 
	 * agregarle una venta saltaria esta excepcion 
	 */
	public abstract void addCompra(VentaConCuentaCorriente compra, CuentaCorriente cc) throws SinCuentaCorrienteException;
} 