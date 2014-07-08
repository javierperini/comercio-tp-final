package Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import Comercio.OfertaSimple;
import Comercio.Presentacion;
import Comercio.Producto;
import Comercio.Unidad;

public class OfertaTest {

	OfertaSimple oferta;
	DateTime fechaVigente;
	DateTime fechaNoVigente;
	DateTime validoDesde;
	DateTime validoHasta;
	Producto productoMock;
	Unidad unidadMock;
	Presentacion presentacionMock;
	int id;
	String nombre;
	
	@Before
	public void setUp() throws Exception {
		this.productoMock = mock(Producto.class);
		this.unidadMock = mock(Unidad.class);
		this.presentacionMock = mock(Presentacion.class);
		this.id = 1;		
		this.nombre = "Oferta de pollo.";
		
		when(productoMock.getPresentacion(unidadMock)).thenReturn(presentacionMock);
		when(presentacionMock.getPrecioVenta()).thenReturn(10d);
		
		this.validoDesde = new DateTime("2014-04-10"); 
		this.validoHasta = new DateTime("2014-05-10");
		this.fechaVigente = new DateTime("2014-04-20");
		this.fechaNoVigente = new DateTime("2014-05-11");
		this.oferta = new OfertaSimple(this.nombre, this.productoMock, 10d/*%*/, 
				this.unidadMock ,this.id, this.validoDesde, this.validoHasta);
		
	}

	@Test
	public void testUnaOfertaEstaVigente() {
		//assertTrue(this.oferta.ofertaValida(fechaVigente));
	}
	
	@Test
	public void testUnaOfertaNoEstaVigente() {
		//assertFalse(this.oferta.ofertaValida(fechaNoVigente));
	}

}
