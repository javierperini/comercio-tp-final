package Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import Cliente.Cliente;
import Comercio.Comercio;
import Comercio.OrdenDeCompra;
import Movimiento.Devolucion;
import Producto.Presentacion;
import Producto.Producto;
import Producto.Unidad;



public class DevolucionTest {
	
	public Devolucion devolucion;
	public Cliente clienteMock;
	public OrdenDeCompra ordenMock;
	public Producto productoMock;
	public Unidad unidadMock;
	public Presentacion presentacionMock;  
	public Comercio coto;
	public DateTime hoy;
	
	@Before
	public void setUp(){ 
		this.clienteMock = mock(Cliente.class);
		this.ordenMock = mock(OrdenDeCompra.class);
		this.productoMock = mock(Producto.class);
		this.unidadMock = mock(Unidad.class);
		this.presentacionMock = mock(Presentacion.class);
		this.coto=mock(Comercio.class);
		this.hoy=new DateTime(2014, 10, 06, 00, 00);
		when(this.ordenMock.getPrecio()).thenReturn(100d);
		when(this.ordenMock.getUnProducto()).thenReturn(this.productoMock);
		when(this.ordenMock.getUnaUnidad()).thenReturn(this.unidadMock);
		when(this.ordenMock.getCantidad()).thenReturn(1d);
		when(this.productoMock.getPresentacion(this.unidadMock)).thenReturn(this.presentacionMock);
		
		List<OrdenDeCompra> ordenes = new ArrayList<OrdenDeCompra>();
		ordenes.add(ordenMock);
		this.devolucion = new Devolucion(this.clienteMock,ordenes,this.hoy,this.coto);
	
	}
	
	@Test
	public void testCalcularImporte() {
		assertEquals(this.ordenMock.getPrecio(), this.devolucion.calcularImporte(), 0.000);
	}
	
	@Test
	public void testDevolverPlata(){		
		this.devolucion.devolverPlata();
		
		verify(this.clienteMock, atLeastOnce()).depositar(devolucion.calcularImporte());
	}
	
	@Test
	public void testIncrementarStock(){
		
		this.devolucion.modificarStock();
		verify(this.productoMock.getPresentacion(this.unidadMock), atLeastOnce()).incrementarStock(this.ordenMock.getCantidad());
	}
	
	
}