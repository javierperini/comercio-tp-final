package Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import Cliente.Activa;
import Cliente.CuentaCorriente;
import Venta.VentaConCuentaCorriente;

public class ActivaTest {

	VentaConCuentaCorriente ventaMock;
	CuentaCorriente cuentaMock;
	Activa cuentaCorriente;
	
	@Before
	public void setUp(){
		this.ventaMock = mock(VentaConCuentaCorriente.class);
		this.cuentaMock = mock(CuentaCorriente.class);
		this.cuentaCorriente = new Activa();
	}
	
	@Test
	public void testAddCompra(){
		this.cuentaCorriente.addCompra(this.ventaMock, this.cuentaMock);
		verify(this.cuentaMock,times(1)).agregarCompraConCuentaCorriente(this.ventaMock);
	}

}
