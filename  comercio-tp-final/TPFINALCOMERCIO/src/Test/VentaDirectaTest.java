package Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import Cliente.Cliente;
import Comercio.Comercio;
import Comercio.OrdenDeCompra;
import Excepciones.NoTengoStock;
import Producto.Presentacion;
import Producto.Producto;
import Producto.Unidad;
import Venta.VentaDirecta;



public class VentaDirectaTest {

	public VentaDirecta ventaD;
	public OrdenDeCompra ordenMock;
	public OrdenDeCompra ordenMock2;
	public Producto lecheMock;
	public Producto carneMock;
	public ArrayList<OrdenDeCompra> listaDeCompras;
	public Cliente clienteMock;
	public Unidad litrosMock;
	public Unidad kilosMock;
	public DateTime hoy;
	public Comercio comercioMock;
	public Presentacion presentacion;
	
	@Before
	
	public void SetUp(){

		///MOCKS
		clienteMock = mock(Cliente.class);
		lecheMock = mock(Producto.class);
		carneMock = mock(Producto.class);
		comercioMock=mock(Comercio.class);
		kilosMock= mock(Unidad.class);
		litrosMock= mock(Unidad.class);
		presentacion= mock(Presentacion.class);
		hoy=new DateTime(2014, 10, 06, 00, 00);
		ordenMock = new OrdenDeCompra(carneMock, kilosMock, 1d, hoy);
		ordenMock2 = new OrdenDeCompra(lecheMock, litrosMock, 3d, hoy);
		when(lecheMock.getPresentacion(litrosMock)).thenReturn(presentacion);
		when(carneMock.getPresentacion(kilosMock)).thenReturn(presentacion);
		when(presentacion.getPrecioVenta()).thenReturn(27d);
		
		///Lista de compras
		this.listaDeCompras = new ArrayList<OrdenDeCompra>();
		this.listaDeCompras.add(ordenMock);
		this.listaDeCompras.add(ordenMock2);
		
		////Venta Directa
		ventaD = new VentaDirecta(clienteMock, listaDeCompras, hoy, comercioMock);
	}
	
	@Test
	public void alModificarElStockSeEnviaElMensajeDecrementarStockALosProductosDeLaLista() throws NoTengoStock {
		this.ventaD.modificarStock();
		verify(this.lecheMock,times(1)).decrementarStock(3d, litrosMock);
		verify(this.carneMock,times(1)).decrementarStock(1d, kilosMock);		
	}
	
	@Test
	public void calcularImporteTest(){
		assertEquals(108d,this.ventaD.calcularImporte(),0);
	}
}
