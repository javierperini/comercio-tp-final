package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import Comercio.EstadoStock;
import Comercio.Presentacion;
import Comercio.StockCritico;
import Comercio.StockDisponible;
import Comercio.StockMinimo;
import Comercio.Ubicacion;
import Comercio.Unidad;
import Excepciones.NoTengoStock;


public class PresentacionTestCase {
	Presentacion leche1L;
	Ubicacion ubicacion;
	Unidad unLitro;
	NoTengoStock sinStock;
	@Before
	public void setUp(){
		this.sinStock= new NoTengoStock(); 
		this.ubicacion=new Mockito().mock(Ubicacion.class);
		this.unLitro=new Mockito().mock(Unidad.class);
		this.leche1L= new Presentacion(this.unLitro,this.ubicacion,10d,2d,"222",15d,1d,4d);
		}
	@Test
	public void testDecrementarStock() throws NoTengoStock {
		this.leche1L.decrementarStock(1d);
		assertEquals(14d,this.leche1L.getStockTotal(),0);
		}
	// DUDAS CON LA EXEPCION porque no uso TRY Y CATCH
	@Test(expected=NoTengoStock.class)
	public void testDecrementarStockExepcion()throws NoTengoStock {
		this.leche1L.decrementarStock(26d);
		assertEquals(15,this.leche1L.getStockTotal(),0);
	}
	@Test
	public void testIncrementar(){
		this.leche1L.incrementarStock(10d);
		assertEquals(25d,this.leche1L.getStockTotal(),0);
	}
	@Test
	public void mePaseDelStockCritico() throws NoTengoStock{
		EstadoStock estado= new StockCritico();
		this.leche1L.decrementarStock(12d);
		assertEquals(estado.getClass(),this.leche1L.getEstado().getClass());
		
	}
	@Test
	public void mePaseDelStockMinimo() throws NoTengoStock{
		EstadoStock estado= new StockMinimo();
		this.leche1L.decrementarStock(14d);
		assertEquals(estado.getClass(),this.leche1L.getEstado().getClass());
	}
	@Test
	public void estoyEnStockDisponible() throws NoTengoStock{
		EstadoStock estado= new StockDisponible();
		this.leche1L.decrementarStock(11d);
		this.leche1L.incrementarStock(20d);
		assertEquals(estado.getClass(),this.leche1L.getEstado().getClass());
	}
	@Test
	public void cambiarPrecioVenta(){
		this.leche1L.cambiarPrecio(12d);
		assertEquals(12d,this.leche1L.getPrecioVenta(),0);
		assertEquals(1,this.leche1L.getPrecionAnteriores().size(),0);
	}
	

}
