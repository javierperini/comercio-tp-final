package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import Excepciones.NoTengoStock;
import Producto.Presentacion;
import Producto.Producto;
import Producto.Ubicacion;
import Producto.Unidad;

public class ProductoTestCase {
	Producto producto;
	Presentacion presentacion1L;
	Presentacion presentacion2L;
	Presentacion presentacion3L;
	List<Presentacion>presentaciones;
	Unidad unidad1L;
	Unidad unidad2L;
	Unidad unidad3L;
	Ubicacion ubicacion;
	@Before
	public void setUp(){
		
		//Creo los Mocks
		this.unidad1L=new Mockito().mock(Unidad.class);
		this.unidad2L=new Mockito().mock(Unidad.class);
		this.unidad3L=new Mockito().mock(Unidad.class);
		this.presentacion1L=new Mockito().mock(Presentacion.class);
		this.presentacion2L=new Mockito().mock(Presentacion.class);
		this.presentacion3L=new Mockito().mock(Presentacion.class);
		this.ubicacion=new Mockito().mock(Ubicacion.class);
		//Comportamientos de los Mocks
		Mockito.when(this.presentacion1L.getUnidad()).thenReturn(this.unidad1L);
		Mockito.when(this.presentacion1L.getUbicacion()).thenReturn(this.ubicacion);
		Mockito.when(this.presentacion1L.getPrecioVenta()).thenReturn(14d);
		Mockito.when(this.presentacion1L.getStockTotal()).thenReturn(19d);
		Mockito.when(this.presentacion2L.getUnidad()).thenReturn(this.unidad2L);
		Mockito.when(this.presentacion2L.getStockTotal()).thenReturn(21d);
		Mockito.when(this.presentacion2L.getPrecioVenta()).thenReturn(14d);
		//Creo las listas
		this.presentaciones=new ArrayList<Presentacion>();
		//Agrego a las listas
		this.presentaciones.add(presentacion1L);
		this.presentaciones.add(presentacion2L);
		this.presentaciones.add(presentacion3L);
		
		//Creo objetos concretos
		this.producto=new Producto("leche", "Sancor", "descripcion", "comestible", presentaciones);
		
	}
	
	@Test
	public void testGetPresentacion() {
		assertEquals(this.presentacion1L,this.producto.getPresentacion(this.unidad1L));
		assertEquals(3,this.producto.presentaciones().size(),0);
		assertEquals(null,this.producto.getPresentacion(this.unidad3L));
	}
	
	@Test
	public void testGetUbicacion(){
		assertEquals(this.ubicacion,this.producto.getUbicacion(this.unidad1L));
	}
	
	@Test
	public void testPrecioDeVenta(){
		this.producto.cambiarPrecioDeVenta(this.unidad1L,14d);
		assertEquals(14d,this.producto.getPrecioVenta(this.unidad1L),0);
	}
	
	@Test
	public void testDecrementarStock() throws NoTengoStock{
		this.producto.decrementarStock(1d,this.unidad1L);
		assertEquals(19d,this.producto.stockTotal(this.unidad1L),0);
	}
	
	@Test
	public void testIncrementarStock(){
		this.producto.incrementarStock(1d,this.unidad2L);
		assertEquals(21d,this.producto.stockTotal(this.unidad2L),0);
	}
	
	
}
