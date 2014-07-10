package Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import Oferta.Oferta;
import Oferta.OfertaCompuesta;
import Oferta.OfertaSimple;
import Producto.Producto;


public class OfertaCompuestaTest {

	OfertaSimple oferta1Mock;
	OfertaSimple oferta2Mock;
	double descuento;
	OfertaCompuesta oferta;
	DateTime validoDesde;
	DateTime validoHasta;
	String nombre;
	Producto unProductoMock;
	
	@Before
	public void setUp(){
		this.unProductoMock = mock(Producto.class);
		this.oferta1Mock = mock(OfertaSimple.class);
		this.oferta2Mock = mock(OfertaSimple.class);
		this.descuento = 0.5;
		this.validoDesde = new DateTime("2014-04-10"); 
		this.validoHasta = new DateTime("2014-05-10");
		this.nombre = "Oferta de Fernet + 2 cocas";
		
		List<Oferta> ofertas = new ArrayList<Oferta>();
		ofertas.add(oferta1Mock);
		ofertas.add(oferta1Mock);
		ofertas.add(oferta2Mock);
		
		this.oferta = new OfertaCompuesta(this.nombre,ofertas,this.descuento, 
				this.validoDesde, this.validoHasta);
		
		when(this.oferta1Mock.getPrecioOferta()).thenReturn(10d);
		when(this.oferta1Mock.perteneceA(this.unProductoMock)).thenReturn(true);
		when(this.oferta2Mock.getPrecioOferta()).thenReturn(20d);
		
	}
	
	@Test
	public void testGetPrecioOferta() {		 
		assertEquals(39.8, this.oferta.getPrecioOferta(), 0.000);
	}
	
	@Test
	public void testPerteneceA(){
		assertTrue(this.oferta.perteneceA(this.unProductoMock));
	}

}
