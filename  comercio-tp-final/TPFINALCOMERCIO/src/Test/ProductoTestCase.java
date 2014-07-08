package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import Comercio.Presentacion;
import Comercio.Producto;
import Comercio.Ubicacion;
import Comercio.Unidad;
import Excepciones.NoTengoStock;

public class ProductoTestCase {
	Producto lecheSancor;
	Presentacion leche1l;
	Presentacion leche2l;
	Presentacion leche3l;
	List<Presentacion>presentaciones;
	Unidad unLitro;
	Unidad dosLitro;
	Ubicacion rfc2;
	@Before
	public void setUp(){
		
		//Creo los Mocks
		this.unLitro=new Mockito().mock(Unidad.class);
		this.dosLitro=new Mockito().mock(Unidad.class);
		this.leche1l=new Mockito().mock(Presentacion.class);
		this.leche2l=new Mockito().mock(Presentacion.class);
		this.leche3l=new Mockito().mock(Presentacion.class);
		this.rfc2=new Mockito().mock(Ubicacion.class);
		//Comportamientos de los Mocks
		Mockito.when(this.leche1l.getUnidad()).thenReturn(this.unLitro);
		Mockito.when(this.leche1l.getUbicacion()).thenReturn(this.rfc2);
		Mockito.when(this.leche1l.getPrecioVenta()).thenReturn(14d);
		Mockito.when(this.leche1l.getStockTotal()).thenReturn(19d);
		Mockito.when(this.leche2l.getUnidad()).thenReturn(this.dosLitro);
		Mockito.when(this.leche2l.getStockTotal()).thenReturn(21d);
		Mockito.when(this.leche2l.getPrecioVenta()).thenReturn(14d);
		//Creo las listas
		this.presentaciones=new ArrayList<Presentacion>();
		//Agrego a las listas
		this.presentaciones.add(leche1l);
		this.presentaciones.add(leche2l);
		this.presentaciones.add(leche3l);
		
		//Creo objetos concretos
		this.lecheSancor=new Producto("leche", "Sancor", "descripcion", "comestible", presentaciones);
		
	}
	@Test
	public void testGetPresentacion() {
		assertEquals(this.leche1l,this.lecheSancor.getPresentacion(this.unLitro));
	}
	@Test
	public void testGetUbicacion(){
		assertEquals(this.rfc2,this.lecheSancor.getUbicacion(this.unLitro));
	}
	@Test
	public void testPrecioDeVenta(){
		this.lecheSancor.cambiarPrecioDeVenta(this.unLitro,14d);
		assertEquals(14d,this.lecheSancor.getPrecioVenta(this.unLitro),0);
	}
	@Test
	public void testDecrementarStock() throws NoTengoStock{
		this.lecheSancor.decrementarStock(1d,this.unLitro);
		assertEquals(19d,this.lecheSancor.stockTotal(this.unLitro),0);
	}
	@Test
	public void testIncrementarStock(){
		this.lecheSancor.incrementarStock(1d,this.dosLitro);
		assertEquals(21d,this.lecheSancor.stockTotal(this.dosLitro),0);
	}
	
}
