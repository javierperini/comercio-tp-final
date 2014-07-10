package Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import Venta.VentaConCuentaCorriente;

import Cliente.CuentaCorriente;
import Excepciones.SaldoInsuficienteException;
import Excepciones.SinCuentaCorrienteException;


public class CuentaCorrienteTest {
	
	private CuentaCorriente cuentaCorriente;
	private VentaConCuentaCorriente ventaMock;
	
	@Before
	public void setUp(){
		this.cuentaCorriente = new CuentaCorriente();
		this.ventaMock = mock(VentaConCuentaCorriente.class);
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
	
	@Test
	public void testAgregarCompraConCuentaCorriente(){
		this.cuentaCorriente.agregarCompraConCuentaCorriente(this.ventaMock);
		assertTrue(this.cuentaCorriente.getComprasConCuentacorriente().contains(this.ventaMock));
	}
	
	@Test
	public void testGetComprasConCuentacorriente(){
		this.cuentaCorriente.agregarCompraConCuentaCorriente(this.ventaMock);
		assertTrue(this.cuentaCorriente.getComprasConCuentacorriente().contains(this.ventaMock));
	}
	
	@Test(expected=SinCuentaCorrienteException.class)
    public void testAddCompraSinCuentaCorrienteException() throws SinCuentaCorrienteException{
		this.cuentaCorriente.addCompra(this.ventaMock);
    }
	
	@Test
	public void testAddCompra() throws SinCuentaCorrienteException{
		this.cuentaCorriente.aumentarSaldo(100d);
		this.cuentaCorriente.addCompra(this.ventaMock);
		assertTrue(this.cuentaCorriente.getComprasConCuentacorriente().contains(this.ventaMock));
	}
	
}
