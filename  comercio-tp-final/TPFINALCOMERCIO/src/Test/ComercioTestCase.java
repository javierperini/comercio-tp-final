package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;

import Comercio.Cliente;
import Comercio.Comercio;
import Comercio.Oferta;
import Comercio.OrdenDeCompra;
import Comercio.Presentacion;
import Comercio.Producto;
import Comercio.Ubicacion;
import Comercio.Unidad;
import Comercio.Venta;
import Comercio.VentaConCuentaCorriente;
public class ComercioTestCase {
	Comercio coto;
	Comercio cotoQuil;
	Producto leche;
	Presentacion leche2L;
	Unidad unLitro;
	Ubicacion rfc2;
	List<Producto> productos;
	List<Presentacion> presentaciones;
	Cliente pepe;
	List<OrdenDeCompra>ordenCompras;
	OrdenDeCompra orden;
	List<Oferta> ofertas;
	Oferta oferta;
	DateTime fecha10;
	Venta venta;

	@Before
	public void setUp(){
		//Creo objetos Concreto
		this.leche2L=new Presentacion(this.unLitro,this.rfc2,12d,8d,"123",10d,6d,2d);// (unidad, ubicacion,precioVenta,precioCompra,codigoDeBarra,stockTotal,StockMin,StockCri)
		this.presentaciones= new ArrayList<Presentacion>();
		this.presentaciones.add(this.leche2L);
		this.leche= new Producto("leche","serenisima","de vaca","comestible",this.presentaciones);//(nombre,marca,descripcion,tipo,List<presentacion>)
		this.fecha10= new DateTime(2013,06,12, 0, 0);
		this.productos=new ArrayList<Producto>();
		this.coto=new Comercio("coto",productos);
		//Creo diferentes tipos de lista

		this.ordenCompras=new ArrayList<OrdenDeCompra>();
		this.ofertas=new ArrayList<Oferta>();
		//Objetos Mock
		this.cotoQuil=new  Mockito().mock(Comercio.class);
		this.pepe=new  Mockito().mock(Cliente.class);
		this.orden= new Mockito().mock(OrdenDeCompra.class);
		this.oferta=new Mockito().mock(Oferta.class);
		this.venta= new Mockito().mock(VentaConCuentaCorriente.class);
		
		//this.venta= Mockito.mock(VentaConCuentaCorriente.class);
		
		
		//Comportamiento a los mocks
		Mockito.when(this.venta.calcularImporte()).thenReturn(12d);
		Mockito.when(this.orden.getUnProducto()).thenReturn(this.leche);
		Mockito.when(this.orden.getUnaUnidad()).thenReturn(this.unLitro);
		Mockito.when(this.orden.getPrecio()).thenReturn(12d);
		Mockito.when(this.orden.getCantidad()).thenReturn(1d);
		Mockito.when(this.venta.calcularImporte()).thenReturn(12d);
		//Agrego a las listas
				this.productos.add(leche);
				this.ordenCompras.add(this.orden);
				this.ofertas.add(oferta);
	}
	@Test
	public void testAgregarProducto() {
		this.coto.agregarProducto(this.leche,unLitro,3);
		assertEquals(2,this.coto.cantidadDeProductos(),0);
		//COMO VERIFICAR QUE DESDE COMERCIO SE ENVIO UN MSJ TAN VECES (COMERCIO NO ES MOCK)
	}
	
	@Test
	public void testAgregarComercio(){
		this.coto.agregarComercio(this.cotoQuil);
		assertEquals(1,this.coto.cantComerciosAhderidos(),0);
	}
	@Test
	public void testGenerarOferta(){
		this.coto.generarOferta("OfertaLeche1L",leche,this.unLitro,23,1,fecha10,fecha10);
		this.coto.generarOfertas("MuchasOfertas",this.ofertas,20,2,fecha10,fecha10);
		assertEquals(2,this.coto.ofertasRealizadas().size(),0);
	}
	@Test
	public void testGenerarVentaCC(){
		this.coto.generarVentaCC(this.pepe,this.ordenCompras,this.fecha10);
		assertEquals(12d,this.coto.getMontoRecaudado(),0);
		assertEquals(1d,this.coto.getVentas().size(),0);
		
	}
	@Test
	public void testGenerarVentaDirecta(){
		this.coto.generarVentaDirecta(this.pepe,this.ordenCompras,this.fecha10);
		assertEquals(12d,this.coto.getMontoRecaudado(),0);
		assertEquals(1d,this.coto.getVentas().size(),0);
		
	}
	@Test
	public void testGenerarVentaConEntrega(){
		this.coto.generarVentaConEntrega(this.pepe,this.ordenCompras,this.fecha10);
		assertEquals(12d,this.coto.getMontoRecaudado(),0d);
		assertEquals(1d,this.coto.getVentas().size(),0d);
		
	}
	@Test
	public void generarDevolucion(){
		this.coto.generarVentaCC(this.pepe,this.ordenCompras,this.fecha10);
		this.coto.generarUnaDevolcion(this.pepe,this.ordenCompras,this.fecha10);
		assertEquals(0d,this.coto.getMontoRecaudado(),0);
		assertEquals(1,this.coto.devolucionesRealizadas().size(),0);
		assertEquals(0d,this.coto.getVentas().size(),0d); 
		
	}
	@Test
	public void generaCambio(){
         this.coto.generarCambio(this.pepe,this.ordenCompras,this.ordenCompras, this.fecha10);
		 assertEquals(1,this.coto.cambiosRealizados().size(),0);
	}


}
