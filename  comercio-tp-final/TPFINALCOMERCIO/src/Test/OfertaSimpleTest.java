package Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import Oferta.OfertaSimple;
import Producto.Presentacion;
import Producto.Producto;
import Producto.Unidad;

public class OfertaSimpleTest {

	Producto unProductoMock;
	Unidad unaUnidadMock;
	Presentacion unaPresentacionMock;
	int id;
	double descuento = 10/*%*/;
	OfertaSimple oferta;
	DateTime validoDesde;
	DateTime validoHasta;
	String nombre;
	
	@Before
	public void setUp(){
		this.unProductoMock = mock(Producto.class);
		this.unaUnidadMock = mock(Unidad.class);
		this.unaPresentacionMock = mock(Presentacion.class);
		this.id = 1;
		this.validoDesde = new DateTime("2014-04-10"); 
		this.validoHasta = new DateTime("2014-05-10");
		this.nombre = "Oferta de Dulce de Leche Sancor de 500 g";
		this.oferta = new OfertaSimple(this.nombre, this.unProductoMock, 
				this.descuento, this.unaUnidadMock, this.id, this.validoDesde, this.validoHasta);

		when(this.unProductoMock.getPresentacion(this.unaUnidadMock)).thenReturn(this.unaPresentacionMock);
		when(this.unaPresentacionMock.getPrecioVenta()).thenReturn(10d);
	}
	
	@Test
	public void testGetPrecioOferta() {		
		double precioOferta = this.oferta.getPrecioOferta();
		assertEquals(9, precioOferta, 0.00);
	}

}
