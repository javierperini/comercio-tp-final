package Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import Comercio.OrdenDeCompra;
import Producto.Presentacion;
import Producto.Producto;
import Producto.Unidad;

public class OrdenDeCompraTest {

	int cantidad;
	Producto unProductoMock;
	Presentacion unaPresentacionMock;
	Unidad unaUnidadMock;
	OrdenDeCompra orden;
	DateTime fecha;
	
	@Before
	public void setUp(){
		this.cantidad = 10;
		this.unProductoMock = mock(Producto.class);
		this.unaPresentacionMock = mock(Presentacion.class);
		this.unaUnidadMock = mock(Unidad.class);
		this.fecha = new DateTime("2014-06-03");
		
		when(this.unProductoMock.getPresentacion(this.unaUnidadMock)).thenReturn(this.unaPresentacionMock);
		when(this.unaPresentacionMock.getPrecioVenta()).thenReturn(10d);  
		
		this.orden = new OrdenDeCompra(this.unProductoMock, this.unaUnidadMock, this.cantidad, this.fecha);
		
	}
	
	@Test
	public void testUnaOrdenDeCompraPertenecienteAUnClienteYDeUnProductoY10UnidadesCuesta100() {
		assertEquals(this.orden.getPrecio(), 100, 0.000);
	}

}
