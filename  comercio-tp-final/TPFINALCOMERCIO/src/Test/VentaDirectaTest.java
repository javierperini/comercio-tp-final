package Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import Comercio.Cliente;
import Comercio.Comercio;
import Comercio.OrdenDeCompra;
import Comercio.Producto;
import Comercio.Unidad;
import Comercio.VentaDirecta;
import Excepciones.NoTengoStock;



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
	
	@Before
	
	public void SetUp(){

		///MOCKS
		clienteMock = mock(Cliente.class);
		ordenMock = mock(OrdenDeCompra.class);
		ordenMock2 = mock(OrdenDeCompra.class);
		lecheMock = mock(Producto.class);
		carneMock = mock(Producto.class);
		comercioMock=mock(Comercio.class);
		hoy=new DateTime(2014, 10, 06, 00, 00);
		
		when(this.clienteMock.getNombre()).thenReturn("Oscar");
		
		when(this.ordenMock.getUnProducto()).thenReturn(lecheMock);
		when(this.ordenMock2.getUnProducto()).thenReturn(carneMock);
		
		when(this.ordenMock.getPrecio()).thenReturn(14d);
		when(this.ordenMock2.getPrecio()).thenReturn(40d);
		
		when(this.ordenMock.getCantidad()).thenReturn(1d);
		when(this.ordenMock2.getCantidad()).thenReturn(3d);
		
		
		///Lista de compras
		this.listaDeCompras = new ArrayList<OrdenDeCompra>();
		this.listaDeCompras.add(ordenMock);
		this.listaDeCompras.add(ordenMock2);
		
		////Venta Directa
		ventaD = new VentaDirecta(clienteMock, listaDeCompras, hoy,comercioMock);
	}
	
	@Test
	public void alModificarElStockSeEnviaElMensajeDecrementarStockALosProductosDeLaLista() throws NoTengoStock {
		this.ventaD.modificarStock();
		verify(this.lecheMock,times(1)).decrementarStock(1, litrosMock);
		verify(this.carneMock,times(1)).decrementarStock(3, kilosMock);		
	}
}
