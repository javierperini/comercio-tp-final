package Cliente;

import Excepciones.SaldoInsuficienteException;
import Excepciones.SinCuentaCorrienteException;
import Venta.VentaConCuentaCorriente;


public class Activa extends EstadoCuentaCorriente{
	
	public Activa(){}
	//TESTEAR
	public void setSaldo(double saldo, CuentaCorriente cc){
		cc.setSaldo(saldo + cc.getSaldo());
	}
	
	@Override
	public double getSaldo(CuentaCorriente cc){
		return cc.getSaldo();
	}

	@Override
	public void disminuirSaldo(double dinero, CuentaCorriente cc) throws SaldoInsuficienteException {
		if((cc.getSaldo() - dinero) < 0d){
			throw new SaldoInsuficienteException();
		}
		else{	
			cc.setSaldo(cc.getSaldo() - dinero);
		}
	}
	//TESTEAR
	@Override
	public void addCompra(VentaConCuentaCorriente compra, CuentaCorriente cc)
			throws SinCuentaCorrienteException {
		cc.agregarCompraConCuentaCorriente(compra);
	}

}
