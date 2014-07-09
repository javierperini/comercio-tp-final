package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import Excepciones.NoTengoStock;
import Producto.EstadoStock;
import Producto.Presentacion;
import Producto.StockCritico;
import Producto.StockDisponible;
import Producto.StockMinimo;
import Producto.Ubicacion;
import Producto.Unidad;


public class PresentacionTestCase {
	Presentacion presentacion;
	Ubicacion ubicacion;
	Unidad unLitro;
	NoTengoStock sinStock;
	@Before
	public void setUp(){
		this.sinStock= new NoTengoStock(); 
		this.ubicacion=new Mockito().mock(Ubicacion.class);
		this.unLitro=new Mockito().mock(Unidad.class);
		this.presentacion= new Presentacion(this.unLitro,this.ubicacion,10d,2d,"222",15d,1d,4d);
		}
	
	@Test
	public void testDecrementarStock() throws NoTengoStock {
		this.presentacion.decrementarStock(1d);
		assertEquals(14d,this.presentacion.getStockTotal(),0);
		}
	
	@Test(expected=NoTengoStock.class)
	public void testDecrementarStockExepcion()throws NoTengoStock {
		this.presentacion.decrementarStock(26d);
		assertEquals(15,this.presentacion.getStockTotal(),0);
	}
	
	@Test
	public void testIncrementar(){
		this.presentacion.incrementarStock(10d);
		assertEquals(25d,this.presentacion.getStockTotal(),0);
	}
	
	@Test
	public void mePaseDelStockCritico() throws NoTengoStock{
		EstadoStock estado= new StockCritico();
		this.presentacion.decrementarStock(12d);
		assertEquals(estado.getClass(),this.presentacion.getEstado().getClass());
		
	}
	
	@Test
	public void mePaseDelStockMinimo() throws NoTengoStock{
		EstadoStock estado= new StockCritico();
		EstadoStock estado2= new StockMinimo();
		this.presentacion.decrementarStock(12d);
		assertEquals(estado.getClass(),this.presentacion.getEstado().getClass());
		this.presentacion.decrementarStock(3d);
		assertEquals(estado2.getClass(),this.presentacion.getEstado().getClass());
		this.presentacion.incrementarStock(15d);
		this.presentacion.decrementarStock(12d);
		assertEquals(estado2.getClass(),this.presentacion.getEstado().getClass());
	}
	
	@Test
	public void estoyEnStockDisponible() throws NoTengoStock{
		EstadoStock estado= new StockDisponible();
		this.presentacion.decrementarStock(11d);
		this.presentacion.incrementarStock(20d);
		assertEquals(estado.getClass(),this.presentacion.getEstado().getClass());
	}
	
	@Test
	public void cambiarPrecioVenta(){
		this.presentacion.cambiarPrecio(12d);
		assertEquals(12d,this.presentacion.getPrecioVenta(),0);
		assertEquals(1,this.presentacion.getPrecionAnteriores().size(),0);
	}
	
	@Test
	public void getUnidadYUbicacion(){
		assertEquals(this.ubicacion,this.presentacion.getUbicacion());
		assertEquals(this.unLitro,this.presentacion.getUnidad());
	}
	

}
