package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Cliente.CuentaCorriente;
import Excepciones.SaldoInsuficienteException;
import Excepciones.SinCuentaCorrienteException;


public class CuentaCorrienteTest {
	
	private CuentaCorriente cuentaCorriente;
	
	@Before
	public void setUp(){
		this.cuentaCorriente = new CuentaCorriente();
	}
	
	@Test
	public void testAumentarSaldo() throws SinCuentaCorrienteException{
		this.cuentaCorriente.aumentarSaldo(100d);
		
		assertEquals(this.cuentaCorriente.saldo(), 100d, 0.00);
	}
	
	@Test(expected=SinCuentaCorrienteException.class)
    public void testDescontarSaldoSinCuentaCorriente() throws SinCuentaCorrienteException, SaldoInsuficienteException{
		this.cuentaCorriente.descontarSaldo(100d);
    }
	
	@Test(expected=SaldoInsuficienteException.class)
    public void testDescontarSaldoConSaldoInsuficiente() throws SinCuentaCorrienteException, SaldoInsuficienteException{
		this.cuentaCorriente.aumentarSaldo(50d);
		
		this.cuentaCorriente.descontarSaldo(100d);
    }
	
	@Test
	public void testSaldo() throws SinCuentaCorrienteException{		
		this.cuentaCorriente.aumentarSaldo(50d);
		
		assertEquals(this.cuentaCorriente.saldo(), 50d, 0.00);
    }
	
	@Test(expected=SinCuentaCorrienteException.class)
    public void testSaldoSinCuentaCorriente() throws SinCuentaCorrienteException{		
		this.cuentaCorriente.saldo();
    }
}
