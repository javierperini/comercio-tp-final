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

import Cliente.Cliente;
import Cliente.CuentaCorriente;
import Comercio.Comercio;
import Comercio.OrdenDeCompra;
import Excepciones.SinCuentaCorrienteException;
import Oferta.Oferta;
import Producto.Presentacion;
import Producto.Producto;
import Producto.Ubicacion;
import Producto.Unidad;
import Venta.Venta;
import Venta.VentaConCuentaCorriente;
public class ComercioTestCase {
	Comercio comercio;
	Comercio sucursal;
	Producto producto;
	Presentacion presentancion;
	Unidad unidad;
	Ubicacion ubicacion;
	List<Producto> productos;
	List<Presentacion> presentaciones;
	Cliente clienteMock;
	List<OrdenDeCompra>ordenCompras;
	OrdenDeCompra orden;
	List<Oferta> ofertas;
	Oferta oferta;
	Venta venta;
	DateTime fechaInicio;
	DateTime fechaFin;
	DateTime fechaIntermedia;
	CuentaCorriente ccMock;

	@Before
	public void setUp(){
		//Creo objetos Concreto
		this.presentancion=new Presentacion(this.unidad,this.ubicacion,12d,8d,"123",10d,6d,2d);// (unidad, ubicacion,precioVenta,precioCompra,codigoDeBarra,stockTotal,StockMin,StockCri)
		this.presentaciones= new ArrayList<Presentacion>();
		this.presentaciones.add(this.presentancion);
		this.producto= new Producto("producto","serenisima","de vaca","comestible",this.presentaciones);//(nombre,marca,descripcion,tipo,List<presentacion>)
		this.fechaInicio= new DateTime(2013,06,12, 0, 0);
		this.fechaFin= new DateTime(2013,06,20, 0, 0);
		this.fechaIntermedia= new DateTime(2013,06,15, 0, 0);
		this.productos=new ArrayList<Producto>();
		this.comercio=new Comercio("coto",productos);
		//Creo diferentes tipos de lista

		this.ordenCompras=new ArrayList<OrdenDeCompra>();
		this.ofertas=new ArrayList<Oferta>();
		//Objetos Mock
		this.sucursal=new  Mockito().mock(Comercio.class);
		this.clienteMock=new  Mockito().mock(Cliente.class);
		this.orden= new Mockito().mock(OrdenDeCompra.class);
		this.oferta=new Mockito().mock(Oferta.class);
		this.venta= new Mockito().mock(VentaConCuentaCorriente.class);
		this.ccMock=new Mockito().mock(CuentaCorriente.class);
		
		
		
		
		//Comportamiento a los mocks
		Mockito.when(this.venta.calcularImporte()).thenReturn(12d);
		Mockito.when(this.orden.getUnProducto()).thenReturn(this.producto);
		Mockito.when(this.orden.getUnaUnidad()).thenReturn(this.unidad);
		Mockito.when(this.orden.getPrecio()).thenReturn(12d);
		Mockito.when(this.orden.getCantidad()).thenReturn(1d);
		Mockito.when(this.venta.calcularImporte()).thenReturn(12d);
		Mockito.when(this.clienteMock.getCuentaCorriente()).thenReturn(this.ccMock);
		//Agrego a las listas
				this.productos.add(producto);
				this.ordenCompras.add(this.orden);
				this.ofertas.add(oferta);
	}
	@Test
	public void testAgregarProducto() {
		this.comercio.agregarProducto(this.producto,unidad,3,fechaInicio);
		assertEquals(2,this.comercio.cantidadDeProductos(),0);
		
	}
	
	@Test
	public void testAgregarComercio(){
		this.comercio.agregarComercio(this.sucursal);
		assertEquals(1,this.comercio.cantComerciosAhderidos(),0);
	}
	@Test
	public void testGenerarOferta(){
		this.comercio.generarOferta("Ofertaproducto1L",producto,this.unidad,23,1,fechaInicio,fechaInicio);
		this.comercio.generarOfertas("MuchasOfertas",this.ofertas,20,2,fechaInicio,fechaInicio);
		assertEquals(2,this.comercio.ofertasRealizadas().size(),0);
	}
	@Test
	public void testGenerarVentaCC() throws SinCuentaCorrienteException{
		this.comercio.generarVentaCC(this.clienteMock,this.ordenCompras,this.fechaInicio);
		assertEquals(12d,this.comercio.getMontoRecaudado(),0);
		assertEquals(1d,this.comercio.getVentas().size(),0);
		
	}
	@Test
	public void testGenerarVentaDirecta() throws SinCuentaCorrienteException{
		this.comercio.generarVentaDirecta(this.clienteMock,this.ordenCompras,this.fechaInicio);
		assertEquals(12d,this.comercio.getMontoRecaudado(),0);
		assertEquals(1d,this.comercio.getVentas().size(),0);
		
	}
	@Test
	public void testGenerarVentaConEntrega(){
		this.comercio.generarVentaConEntrega(this.clienteMock,this.ordenCompras,this.fechaInicio);
		assertEquals(12d,this.comercio.getMontoRecaudado(),0d);
		assertEquals(1d,this.comercio.getVentas().size(),0d);
		
	}
	@Test
	public void generarDevolucion() throws SinCuentaCorrienteException{
		this.comercio.generarVentaDirecta(this.clienteMock,this.ordenCompras,this.fechaInicio);
		this.comercio.generarUnaDevolcion(this.clienteMock,this.ordenCompras,this.fechaInicio);
		assertEquals(0d,this.comercio.getMontoRecaudado(),0);
		assertEquals(1,this.comercio.devolucionesRealizadas().size(),0);
		assertEquals(0d,this.comercio.getVentas().size(),0d); 
		
	}
	@Test
	public void generaCambio(){
         this.comercio.generarCambio(this.clienteMock,this.ordenCompras,this.ordenCompras, this.fechaInicio);
		 assertEquals(1,this.comercio.cambiosRealizados().size(),0);
	}
	@Test
	public  void notificarClientes(){
		this.comercio.agregarAListaPedidos(this.clienteMock);
		this.comercio.agregarProducto(this.producto,unidad,3,fechaInicio);
		assertEquals(1,this.comercio.cantClientePedidos());
		Mockito.verify(clienteMock,Mockito.atLeastOnce()).avisoDePedido((OrdenDeCompra) Mockito.anyObject());
	}
	@Test
	public void estaEnOfertaYPrecioOferta(){
		this.comercio.generarOferta("Oferta", this.producto, this.unidad, 12, 1212, this.fechaInicio, this.fechaFin);
		assertTrue(this.comercio.estaEnOferta(this.producto, this.fechaIntermedia));
		assertEquals(10.56d,this.comercio.getPrecioOfertaDe(this.producto),0);
	}


}
