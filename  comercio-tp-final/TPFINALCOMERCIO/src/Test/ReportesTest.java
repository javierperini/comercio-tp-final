package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import Cliente.Cliente;
import Comercio.Comercio;
import Comercio.OrdenDeCompra;
import Comercio.Reporte;
import Excepciones.SinCuentaCorrienteException;
import Producto.Presentacion;
import Producto.Producto;
import Producto.Unidad;



public class ReportesTest {
	Reporte reporte;
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
	DateTime fecha1;
	DateTime fecha2;
	DateTime fecha3;
	DateTime fecha4;
	List<OrdenDeCompra> listaOrden;
	ArrayList<Producto> listaProducto;
	
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
		this.fecha1= new DateTime(2014, 12, 06, 00, 00);
		this.fecha2=new  DateTime(2014, 11, 03, 00, 00);
		this.fecha3= new DateTime(2014, 10, 16, 00, 00);
		this.fecha4= new DateTime(2014, 10, 12, 00, 00);
		Mockito.when(this.pan.getPresentacion(unidad1)).thenReturn(this.presentacion2K);
		Mockito.when(this.leche.getPresentacion(unidad2)).thenReturn(this.presentacion1L);
		Mockito.when(this.presentacion1L.getPrecioVenta()).thenReturn(30d);
		Mockito.when(this.presentacion2K.getPrecioVenta()).thenReturn(20d);
		this.orden1= new OrdenDeCompra(this.pan,unidad1,2d, fecha1);
		this.orden2= new OrdenDeCompra(this.leche,unidad2,1d, fecha2);
		this.listaOrden= new ArrayList<OrdenDeCompra>();
		this.listaOrden.add(this.orden1);
		this.listaOrden.add(this.orden2);
		this.listaProducto= new ArrayList<Producto>();
		this.listaProducto.add(this.leche);
		this.listaProducto.add(this.pan);
		
		
		this.comercio= new Comercio("Nombre", listaProducto);
		this.reporte= new Reporte(this.comercio);
		
	}

	@Test
	public void filtrarVentasporFecha() throws SinCuentaCorrienteException {
		this.comercio.generarVentaDirecta(this.cliente, this.listaOrden, this.fecha1);
		this.comercio.generarVentaDirecta(this.cliente, this.listaOrden, this.fecha2);
		this.comercio.generarVentaDirecta(this.cliente, this.listaOrden, this.fecha3);
		this.comercio.generarVentaDirecta(this.cliente, this.listaOrden, this.fecha4);
		assertEquals(3,this.reporte.filtrarVentaPorFecha(this.fecha1,this.fecha3).size(),0);
		assertEquals(4,this.reporte.filtrarVentaPorFecha(this.fecha1,this.fecha4).size(),0);
		assertEquals(1,this.reporte.filtrarVentaPorFecha(this.fecha4,this.fecha4).size(),0);
	}
	@Test
	public void filtrarVentasporProducto() throws SinCuentaCorrienteException {
		this.comercio.generarVentaDirecta(this.cliente, this.listaOrden, this.fecha1);
		this.comercio.generarVentaDirecta(this.cliente, this.listaOrden, this.fecha2);
		this.comercio.generarVentaDirecta(this.cliente, this.listaOrden, this.fecha3);
		this.comercio.generarVentaDirecta(this.cliente, this.listaOrden, this.fecha4);
		assertEquals(4,this.reporte.filtrarVentaPorProducto(this.leche).size(),0);
		assertEquals(4,this.reporte.filtrarVentaPorProducto(this.pan).size(),0);
		assertEquals(0,this.reporte.filtrarVentaPorProducto(this.queso).size(),0);
	}
	@Test
	public void filtrarVentasporMonto() throws SinCuentaCorrienteException {
		this.comercio.generarVentaDirecta(this.cliente, this.listaOrden, this.fecha1);
		this.comercio.generarVentaDirecta(this.cliente, this.listaOrden, this.fecha2);
		this.comercio.generarVentaDirecta(this.cliente, this.listaOrden, this.fecha3);
		this.comercio.generarVentaDirecta(this.cliente, this.listaOrden, this.fecha4);
		assertEquals(4,this.reporte.filtrarVentaPorMonto(11d,500d).size(),0);
		assertEquals(4,this.reporte.filtrarVentaPorMonto(0d,10d).size(),0);
		assertEquals(0,this.reporte.filtrarVentaPorMonto(1000d,5000d).size(),0);
	
	}
}
