package Test;

//import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import Cliente.Cliente;
import Cliente.CuentaCorriente;
import Comercio.Comercio;
import Comercio.OrdenDeCompra;
import Excepciones.SaldoInsuficienteException;
import Excepciones.SinCuentaCorrienteException;
import Movimiento.VentaConCuentaCorriente;

public class VentaConCuentaCorrienteTest {

	Cliente unCliente;
	Cliente unClienteMock;
	VentaConCuentaCorriente venta;
	VentaConCuentaCorriente ventaMock;
	OrdenDeCompra unaOrdenMock;
	OrdenDeCompra otraOrdenMock;
	DateTime hoy;
	Comercio coto;
	CuentaCorriente cc;
	
	@Before
	public void setUp(){
	    this.unaOrdenMock = mock(OrdenDeCompra.class);
		this.otraOrdenMock = mock(OrdenDeCompra.class);
		this.unClienteMock = mock(Cliente.class);
		this.cc=mock(CuentaCorriente.class);
		List<OrdenDeCompra>listadoDeProductos = new ArrayList<OrdenDeCompra>();
		listadoDeProductos.add(this.unaOrdenMock);
		listadoDeProductos.add(this.otraOrdenMock);
		coto= mock(Comercio.class);
		hoy=new DateTime(2014, 10, 06, 00, 00);
		unCliente = new Cliente("pepe"); 
		this.venta = new VentaConCuentaCorriente(this.unCliente,listadoDeProductos,this.hoy,this.coto);
		this.ventaMock = new VentaConCuentaCorriente(unClienteMock, listadoDeProductos,this.hoy,this.coto);
		when(unClienteMock.getCuentaCorriente()).thenReturn(cc);
		when(unaOrdenMock.getPrecio()).thenReturn(100d);
		when(otraOrdenMock.getPrecio()).thenReturn(100d);
	}
	
	@Test
	public void testCobrar() throws SinCuentaCorrienteException, SaldoInsuficienteException{
		
		this.venta.cobrar(unClienteMock);
		
		verify(this.unClienteMock, atLeastOnce()).descontarSaldo(200d);
	}
	
	@Test(expected=SinCuentaCorrienteException.class)
    public void testCobrarSinCuentaCorriente() throws SinCuentaCorrienteException, SaldoInsuficienteException{
		this.venta.cobrar(unCliente);
    }
	
	@Test(expected=SaldoInsuficienteException.class)
    public void testCobrarConSaldoInsuficienteExeption() throws SinCuentaCorrienteException, SaldoInsuficienteException{
		this.unCliente.depositar(100d);
		this.venta.cobrar(unCliente);
    }
	
	@Test
	public void testAgregarVentaAlCliente() throws SinCuentaCorrienteException{
		this.ventaMock.agregarVentaAlCliente();
	   verify(this.unClienteMock, atLeastOnce()).addCompra(ventaMock);
    }
	
	@Test(expected=SinCuentaCorrienteException.class)
    public void testAgregarVentaAlClienteSinCuentaCorriente() throws SinCuentaCorrienteException{
		this.venta.agregarVentaAlCliente();
    }

}
