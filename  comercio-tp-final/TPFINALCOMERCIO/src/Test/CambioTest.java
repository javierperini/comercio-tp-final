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

import Cliente.Cliente;
import Comercio.Comercio;
import Comercio.OrdenDeCompra;
import Excepciones.NoTengoStock;
import Movimiento.Cambio;
import Producto.Producto;
import Producto.Unidad;


public class CambioTest {
	
	public Cambio cambio;
	public OrdenDeCompra ordenMock;
	public OrdenDeCompra ordenMock2;
	public OrdenDeCompra ordenMockNueva;
	public Producto lecheMock;
	public Producto carneMock;
	public Producto zapatillasMock;
	public ArrayList<OrdenDeCompra> listaDeCompras;
	public ArrayList<OrdenDeCompra> listaDeComprasNueva;
	public Cliente clienteMock;
	public Unidad litrosMock;
	public Unidad kilosMock;
	public Unidad talle;
	public DateTime hoy;
	public Comercio coto;
	
	@Before
	
	public void SetUp(){

		///MOCKS
		clienteMock = mock(Cliente.class);
		ordenMock = mock(OrdenDeCompra.class);
		ordenMock2 = mock(OrdenDeCompra.class);
		ordenMockNueva = mock(OrdenDeCompra.class);
		lecheMock = mock(Producto.class);
		carneMock = mock(Producto.class);
		zapatillasMock = mock(Producto.class);
		coto= mock(Comercio.class);
		hoy=new DateTime(2014, 10, 06, 00, 00);
		
		when(this.clienteMock.getNombre()).thenReturn("Oscar");
		
		when(this.ordenMock.getUnProducto()).thenReturn(lecheMock);
		when(this.ordenMock2.getUnProducto()).thenReturn(carneMock);
		when(this.ordenMockNueva.getUnProducto()).thenReturn(zapatillasMock);
		
		when(this.ordenMock.getPrecio()).thenReturn(14d);
		when(this.ordenMock2.getPrecio()).thenReturn(40d);
		when(this.ordenMockNueva.getPrecio()).thenReturn(100d);
		
		when(this.ordenMock.getCantidad()).thenReturn(1d);
		when(this.ordenMock2.getCantidad()).thenReturn(3d);
		when(this.ordenMockNueva.getCantidad()).thenReturn(1d);
		
		
		///Lista de compras
		listaDeCompras = new ArrayList<OrdenDeCompra>();
		listaDeComprasNueva = new ArrayList<OrdenDeCompra>();
		this.listaDeCompras.add(ordenMock);
		this.listaDeCompras.add(ordenMock2);
		this.listaDeComprasNueva.add(ordenMockNueva);
		
		
		////Venta Directa
		cambio = new Cambio(clienteMock, listaDeComprasNueva, listaDeCompras,hoy,coto);
	}

	@Test
	public void elMontoDeLaListaNuevaSuperaElMontoDeLaListaDevuelta(){
		assertFalse(this.cambio.elMontoDeLosProductosSolicitadosExcedeAlDeLosDevueltos());
	}
	
	@Test
	public void alModificarElStockLosProductosEnLaListaDeDevueltosRecibiranElMensajeIncrementarStock() throws NoTengoStock{
		this.cambio.modificarStock();
		verify(lecheMock,times(1)).decrementarStock(1, litrosMock);
		verify(carneMock,times(1)).decrementarStock(3, kilosMock);		
	}
	
	@Test
	public void alModificarElStockLosProductosEnLaListaNuevaRecibiranElMensajeDecrementarStock(){
		this.cambio.modificarStock();
		verify(zapatillasMock,times(1)).incrementarStock(1, talle);	
	}
	
	@Test
	public void siElClienteTieneSaldoAFavorAlRealizarElCambioSeLeAcreditaEnSuCuentaCorriente(){
		this.cambio.cargarDiferenciaEnCuentaCorriente();
		verify(clienteMock,times(1)).depositar(46d);	
	}
}