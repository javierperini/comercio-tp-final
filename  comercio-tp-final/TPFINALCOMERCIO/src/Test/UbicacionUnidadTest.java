package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Producto.Ubicacion;
import Producto.Unidad;

public class UbicacionUnidadTest {
	Ubicacion ubicacion;
	Unidad unidad;
	
	@Before
	public void setUp(){
		this.unidad= new Unidad(10d,"litros");
		this.ubicacion= new Ubicacion("RFC234");
	}

	@Test
	public void testUnidad() {
		assertEquals(10d,this.unidad.getCantidad(),0);
		assertEquals("litros",this.unidad.getUnidad());
		
	}
	@Test
	public void testUbicacion() {
		assertEquals("RFC234",this.ubicacion.getNombre());
		
	}

}
