package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import Cliente.Cliente;
import Cliente.CuentaCorriente;
import Comercio.Comercio;
import Comercio.OrdenDeCompra;
import Excepciones.SinCuentaCorrienteException;
import Producto.Presentacion;
import Producto.Producto;
import Producto.Unidad;
import Reportes.FiltrarCuentaCorriente;
import Reportes.FiltrarMontoCliente;
import Reportes.FiltrarMontoClientePorFecha;
import Reportes.FiltrarMontoPorCliente;
import Reportes.FiltrarVentaPorMonto;
import Reportes.FiltroEnvioPendiente;
import Reportes.FiltroPorProducto;
import Reportes.FiltroProductoStockCritco;
import Reportes.FiltroProductoStockMinimo;
import Reportes.FiltroVentaPorFecha;
import Reportes.Reporte;
import Venta.Venta;
import Venta.VentaDirecta;



public class ReportesTest {
	Reporte<Venta> reporte;
	Comercio comercio;
	OrdenDeCompra orden1;
	OrdenDeCompra orden2;
	Cliente cliente;
	Producto leche;
	Producto pan;
	Producto queso;
	Presentacion presentacion2K;
	Presentacion presentacion1L;
	Unidad unidad1;
	Unidad unidad2;
	CuentaCorriente cuentaCorriente;
	DateTime fecha1;
	DateTime fecha2;
	DateTime fecha3;
	DateTime fecha4;
	Venta venta;
	List<OrdenDeCompra> listaOrden;
	ArrayList<Producto> listaProducto;
	List<Venta> compras;
	
	
	
	@Before
	public void setUp(){
		
		this.presentacion1L= Mockito.mock(Presentacion.class);
		this.presentacion2K=Mockito.mock(Presentacion.class);
		this.leche=Mockito.mock(Producto.class);
		this.pan=Mockito.mock(Producto.class);
		this.queso=Mockito.mock(Producto.class);
		this.unidad1=Mockito.mock(Unidad.class);
		this.unidad2=Mockito.mock(Unidad.class);
		this.cliente=Mockito.mock(Cliente.class);
		this.cuentaCorriente=Mockito.mock(CuentaCorriente.class);
		this.fecha1= new DateTime(2014, 12, 06, 00, 00);
		this.fecha2=new  DateTime(2014, 11, 03, 00, 00);
		this.fecha3= new DateTime(2014, 10, 16, 00, 00);
		this.fecha4= new DateTime(2014, 10, 12, 00, 00);

		this.compras= new ArrayList<Venta>();
		this.venta=Mockito.mock(VentaDirecta.class);
		this.compras.add(venta);
		Mockito.when(this.cuentaCorriente.getComprasConCuentacorriente()).thenReturn(this.compras);
		Mockito.when(this.cliente.getCuentaCorriente()).thenReturn(this.cuentaCorriente);
		Mockito.when(this.pan.getPresentacion(unidad1)).thenReturn(this.presentacion2K);
		Mockito.when(this.leche.getPresentacion(unidad2)).thenReturn(this.presentacion1L);
		Mockito.when(this.pan.presentacionesSuperanStockMinimo()).thenReturn(true);
		Mockito.when(this.leche.presentacionesSuperanStockMinimo()).thenReturn(false);
		Mockito.when(this.pan.presentacionesSuperanStockCritico()).thenReturn(false);
		Mockito.when(this.leche.presentacionesSuperanStockCritico()).thenReturn(true);
		Mockito.when(this.presentacion1L.getPrecioVenta()).thenReturn(30d);
		Mockito.when(this.presentacion2K.getPrecioVenta()).thenReturn(20d);
		Mockito.when(this.presentacion1L.getStockMin()).thenReturn(2d);
		Mockito.when(this.presentacion2K.getStockMin()).thenReturn(2d);
		Mockito.when(this.presentacion1L.getStockCri()).thenReturn(4d);
		Mockito.when(this.presentacion2K.getStockCri()).thenReturn(3d);
		Mockito.when(this.presentacion2K.getStockTotal()).thenReturn(1d);
		Mockito.when(this.presentacion1L.getStockTotal()).thenReturn(1d);

		
		this.orden1= new OrdenDeCompra(this.pan,unidad1,2d, fecha1);
		this.orden2= new OrdenDeCompra(this.leche,unidad2,1d, fecha2);
		
		this.listaOrden= new ArrayList<OrdenDeCompra>();
		this.listaOrden.add(this.orden1);
		this.listaOrden.add(this.orden2);
		
		this.listaProducto= new ArrayList<Producto>();
		this.listaProducto.add(this.leche);
		this.listaProducto.add(this.pan);
		
		
		this.comercio= new Comercio("Nombre", listaProducto);
		this.reporte= new Reporte<Venta>(this.comercio);
		
	}

	@Test
	public void filtrarVentasporFecha() throws SinCuentaCorrienteException {
		this.comercio.generarVentaDirecta(this.cliente, this.listaOrden, this.fecha1);
		this.comercio.generarVentaDirecta(this.cliente, this.listaOrden, this.fecha2);
		this.comercio.generarVentaDirecta(this.cliente, this.listaOrden, this.fecha3);
		this.comercio.generarVentaDirecta(this.cliente, this.listaOrden, this.fecha4);
		FiltroVentaPorFecha<Venta> filtro= new FiltroVentaPorFecha<Venta>(this.fecha2,this.fecha1); 
		this.reporte.setStrategy(filtro);
		assertEquals(2,this.reporte.filtrar().size(),0);
		filtro.setFechaMax(fecha4);
		filtro.setFechaMin(fecha3);
		assertEquals(2,this.reporte.filtrar().size(),0);
		filtro.setFechaMax(fecha4);
		filtro.setFechaMin(fecha1);
		assertEquals(4,this.reporte.filtrar().size(),0);
	}
	@Test
	public void filtrarVentasporProducto() throws SinCuentaCorrienteException {
		this.comercio.generarVentaDirecta(this.cliente, this.listaOrden, this.fecha1);
		this.comercio.generarVentaDirecta(this.cliente, this.listaOrden, this.fecha2);
		this.comercio.generarVentaDirecta(this.cliente, this.listaOrden, this.fecha3);
		this.comercio.generarVentaDirecta(this.cliente, this.listaOrden, this.fecha4);
		FiltroPorProducto<Venta> porProducto= new FiltroPorProducto<Venta>(this.leche);
		this.reporte.setStrategy(porProducto);
		assertEquals(4,this.reporte.filtrar().size(),0);
		porProducto.setProducto(this.pan);
		assertEquals(4,this.reporte.filtrar().size(),0);
		porProducto.setProducto(this.queso);
		assertEquals(0,this.reporte.filtrar().size(),0);
		}
	@Test
	public void filtrarVentasporMonto() throws SinCuentaCorrienteException {
		this.comercio.generarVentaDirecta(this.cliente, this.listaOrden, this.fecha1);
		this.comercio.generarVentaDirecta(this.cliente, this.listaOrden, this.fecha2);
		this.comercio.generarVentaDirecta(this.cliente, this.listaOrden, this.fecha3);
		this.comercio.generarVentaDirecta(this.cliente, this.listaOrden, this.fecha4);
		FiltrarVentaPorMonto<Venta> filtro= new FiltrarVentaPorMonto<Venta>(11d,500d);
		this.reporte.setStrategy(filtro);
		assertEquals(4,this.reporte.filtrar().size(),0);
		filtro.setMax(10d);
		filtro.setMin(0d);
		assertEquals(0,this.reporte.filtrar().size(),0);
		filtro.setMax(1000d);
		filtro.setMin(20d);
		assertEquals(4,this.reporte.filtrar().size(),0);
	}
	
	@Test
	public void filtrarProductoDebajoStockMinimo(){
		FiltroProductoStockMinimo filtro= new FiltroProductoStockMinimo();
		this.reporte.setStrategy(filtro);
		assertEquals(1,this.reporte.filtrar().size(),0);
	 }
	
	@Test
	public void filtrarProductoDebajoStockCritico(){
		FiltroProductoStockCritco filtro= new FiltroProductoStockCritco();
		this.reporte.setStrategy(filtro);
		assertEquals(1,this.reporte.filtrar().size(),0);
	 }
	
	@Test
	public void filtrarEnviosPendientes(){
		this.comercio.generarVentaConEntrega(this.cliente, this.listaOrden, this.fecha1);
		this.comercio.generarVentaConEntrega(this.cliente, this.listaOrden, this.fecha2);
		FiltroEnvioPendiente filtro= new FiltroEnvioPendiente();
		this.reporte.setStrategy(filtro);
		assertEquals(0,this.reporte.filtrar().size(),0);
	}
	
	@Test
	public void filtrarPorMontoCliente() throws SinCuentaCorrienteException{
		this.comercio.generarVentaDirecta(this.cliente, this.listaOrden, this.fecha1);
		this.comercio.generarVentaDirecta(this.cliente, this.listaOrden, this.fecha2);
		FiltrarMontoCliente filtro= new FiltrarMontoCliente();
		this.reporte.setStrategy(filtro);
		assertEquals(1,this.reporte.filtrar().size(),0);
	}
	
	@Test
	public void filtrarMontoPorCliente() throws SinCuentaCorrienteException{
		this.comercio.generarVentaDirecta(this.cliente, this.listaOrden, this.fecha1);
		this.comercio.generarVentaDirecta(this.cliente, this.listaOrden, this.fecha2);
		FiltrarMontoPorCliente filtro= new FiltrarMontoPorCliente(this.cliente);
		this.reporte.setStrategy(filtro);
		assertEquals(1,this.reporte.filtrar().size(),0);
	}
	
	@Test 
 	public void filtrarMontoPorFecha() throws SinCuentaCorrienteException {
		this.comercio.generarVentaDirecta(this.cliente, this.listaOrden, this.fecha1);
		this.comercio.generarVentaDirecta(this.cliente, this.listaOrden, this.fecha2);
		FiltrarMontoClientePorFecha filtro= new FiltrarMontoClientePorFecha(fecha1);
		this.reporte.setStrategy(filtro);
		assertEquals(1,this.reporte.filtrar().size(),0);
	}
	
	@Test
	public void filtrarPorCuentaCorriente() throws SinCuentaCorrienteException{
		this.comercio.generarVentaCC(this.cliente, this.listaOrden, this.fecha1);
		this.comercio.generarVentaCC(this.cliente, this.listaOrden, this.fecha2);
		FiltrarCuentaCorriente filtro= new FiltrarCuentaCorriente();
		this.reporte.setStrategy(filtro);
		assertEquals(1,this.reporte.filtrar().size(),0);
	}
}
