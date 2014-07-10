package Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import Venta.VentaDirecta;

import Cliente.Cliente;
import Excepciones.SaldoInsuficienteException;
import Excepciones.SinCuentaCorrienteException;

public class ClienteTest {
	
	public Cliente cliente;
	public VentaDirecta ventaMock;
	
	@Before
	public void setUp(){
		this.cliente = new Cliente("pepe");
		this.ventaMock = mock(VentaDirecta.class);
		}
		
	@Test
	public void testDepositarPlata() throws SinCuentaCorrienteException{		
		this.cliente.depositar(100d);		
		assertEquals(this.cliente.getCuentaCorriente().saldo(), 100d, 0.00);
	}
			
	
	@Test
	public void testGetSaldo() throws SinCuentaCorrienteException {		
		this.cliente.depositar(100d);		
		assertEquals(this.cliente.getSaldo(), 100d, 0.00);
	}
	

	@Test(expected=SinCuentaCorrienteException.class)
    public void testGetSaldoException() throws SinCuentaCorrienteException{
		this.cliente.getSaldo();
    }
	
	@Test
	public void testdescontarSaldo() throws SinCuentaCorrienteException, SaldoInsuficienteException{
		this.cliente.depositar(100d);	
		assertEquals(cliente.getSaldo(), 100d, 0.00);		
		this.cliente.descontarSaldo(50d);
		assertEquals(cliente.getSaldo(), 50d, 0.00);
	}
		
	@Test(expected=SinCuentaCorrienteException.class)
    public void testdescontarSaldoSinCuentaCorriente() throws SinCuentaCorrienteException, SaldoInsuficienteException{
		this.cliente.descontarSaldo(100d);
    }
	
	@Test(expected=SaldoInsuficienteException.class)
    public void testdescontarSaldoInsuficienteException() throws SinCuentaCorrienteException, SaldoInsuficienteException{
		this.cliente.depositar(50d);
		this.cliente.descontarSaldo(100d);
    }
	
	@Test
	public void testGetCompras(){
		assertTrue(this.cliente.getCompras().isEmpty());
	}
	
	@Test
	public void testGetNombre(){
		assertTrue(this.cliente.getNombre().equals("pepe"));
	}
	
}