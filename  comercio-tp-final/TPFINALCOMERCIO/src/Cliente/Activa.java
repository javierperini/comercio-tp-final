package Cliente;

import Excepciones.SaldoInsuficienteException;
import Venta.VentaConCuentaCorriente;


public class Activa extends EstadoCuentaCorriente{
	/**
	 * CONSTRUCTOR
	 */
	public Activa(){}
	
	/**
	 * Setea el saldo de la cuenta corriente.
	 * @param este parametro refiere al saldo a depositar en la cuenta corriente.
	 * @param este parametro refiere a la cuenta corriente donde se depositara el saldo.
	 */
	public void setSaldo(double saldo, CuentaCorriente cc){
		cc.setSaldo(saldo + cc.getSaldo());
	}
	
	/**
	 * Retorna el saldo disponible en la cuenta corriente.
	 * @param cc este parametro refiere a la cuenta corriente de la que se quiere saber el saldo disponible.
	 */
	@Override
	public double getSaldo(CuentaCorriente cc){
		return cc.getSaldo();
	}

	/**
	 * Disminuye el saldo de la cuenta corriente
	 * @param dinero este parametro refiere a la cantidad de dinero que se disminuira de la cuenta corriente.
	 * @param cc este parametro refiere a la cuenta corriente a la cual se le disminuira el saldo.
	 */
	@Override
	public void disminuirSaldo(double dinero, CuentaCorriente cc) throws SaldoInsuficienteException {
		if((cc.getSaldo() - dinero) < 0d){
			throw new SaldoInsuficienteException();
		}
		else{	
			cc.setSaldo(cc.getSaldo() - dinero);
		}
	}
	
	/**
	 * Agrega una compra a la cuenta corriente
	 * @param compra Este parametro refiere a la compra a agregar en la cuenta corriente.
	 * @param cc Este parametro refiere a la cuenta corriente a la que se le agregara la compra.
	 */
	@Override
	public void addCompra(VentaConCuentaCorriente compra, CuentaCorriente cc){
		cc.agregarCompraConCuentaCorriente(compra);
	}

}
