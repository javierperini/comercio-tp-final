package Cliente;

import Excepciones.SinCuentaCorrienteException;
import Venta.VentaConCuentaCorriente;


public class NoActiva extends EstadoCuentaCorriente {

		public NoActiva(){}

		@Override
		public double getSaldo(CuentaCorriente cc) throws SinCuentaCorrienteException{
			throw new SinCuentaCorrienteException();
		}
		
		@Override
		public void setSaldo(double saldo, CuentaCorriente cc) {
			cc.setEstadoCC(new Activa());
			cc.setSaldo(saldo);
		}

		@Override
		public void disminuirSaldo(double dinero, CuentaCorriente cc) throws SinCuentaCorrienteException{
			throw new SinCuentaCorrienteException();			
		}

		@Override
		public void addCompra(VentaConCuentaCorriente compra, CuentaCorriente cc)
				throws SinCuentaCorrienteException {
			throw new SinCuentaCorrienteException();
		}
		
		
}