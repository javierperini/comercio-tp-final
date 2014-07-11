package Comercio;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import org.joda.time.DateTime;

import Cliente.Cliente;
import Envio.Envio;
import Excepciones.SinCuentaCorrienteException;
import Movimiento.Cambio;
import Movimiento.Devolucion;
import Oferta.Oferta;
import Oferta.OfertaCompuesta;
import Oferta.OfertaSimple;
import Producto.Producto;
import Producto.Unidad;
import Venta.Venta;
import Venta.VentaConCuentaCorriente;
import Venta.VentaConEntrega;
import Venta.VentaDirecta;


public class Comercio extends Observable {
	private String nombre;
	private List<Producto> productos;
	private List<Comercio> comercios;
	private List<Cliente> clientePedidos;
	private List<Oferta> ofertas;
	private double montoRecaudado;
	private List<Venta> ventas;
	private List<Devolucion> devoluciones;
	private List <Cambio>cambios;
	private List<Envio> enviosPendientes;
	
	
	public Comercio(String nombre, List<Producto> productos) {
	
		this.nombre=nombre;
     	this.productos=productos;
		this.comercios= new ArrayList<Comercio>();
		this.montoRecaudado=0d;
		this.ventas=new ArrayList<Venta>();
		this.cambios=new ArrayList<Cambio>();
		this.devoluciones=new ArrayList<Devolucion>();
		this.ofertas=new ArrayList<Oferta>();
		this.clientePedidos= new ArrayList<Cliente>();
		this.enviosPendientes=new ArrayList<Envio>();
		
	}
	
	public List<Producto> getProductos(){
		return this.productos;
	}
	
	/**
	 * 
	 * @param envio hecho algun cliente
	 */
	public void agregarEnvioAListaDePendientes(Envio envio){
		this.enviosPendientes.add(envio);
	}
	
	/**
	 * Precondicon: el envio tiene que estar en la lista;
	 * @param envio hecho algun cliente
	 */
	public void quitarEnvioAListaDePendientes(Envio envio){
		this.enviosPendientes.remove(envio);
	}
	/**
	 * 
	 * @return la lista de envios pendientes
	 */
	public List<Envio> getEnviosPendientes(){
		return this.enviosPendientes;
	}
	
	/**
	 * Agrega un producto a la lista de productos
	 * Notifica a sus clientes
	 * @param producto 
	 * @param unidad  
	 * @param cantidad
	 */
	public void agregarProducto(Producto producto,Unidad unidad,int cantidad,DateTime fecha) {
		
		this.productos.add(producto);
		this.notificarClientes(producto, unidad,cantidad,fecha);
	}
    
	/**
     * Cambia y notifica a sus observadores
     * @param producto
     * @param unidad
     * @param cantidad
     */
   public void notificarClientes(Producto producto, Unidad unidad,double cantidad,DateTime fecha) {
		
		OrdenDeCompra pedida=  new OrdenDeCompra(producto,unidad,cantidad,fecha);
		for(Cliente cliente: this.clientePedidos){
			 cliente.avisoDePedido(pedida);
		 }
	}
	
	/**
	 * 
	 * @return Cantidad de productos en el sistema
	 */
	public int cantidadDeProductos() {
		return productos.size();
	}
	
	/**
	 * Agrega un comercio a una red de comercios
	 * @param comercio
	 */
	public void agregarComercio(Comercio comercio) {
		this.comercios.add(comercio);
	}
	
	/**
	 * 
	 * @return Cantidad de comercios en la red de comercios
	 */
	public int cantComerciosAhderidos() {
		return comercios.size();
	}
	
	public List<Devolucion> devolucionesRealizadas(){
		return this.devoluciones;
	}
	
	/**
	 *1- Crea una VentaDirecta
	 *2- Incrementa el monto del local, dependiendo de lo que alla gastado
	 *3- Se Agrega a la lista de ventas realizadas
	 *4- Modifica el stock
	 * @param cliente
	 * @param ordenCompras
	 * @param fecha
	 * @throws SinCuentaCorrienteException 
	 */
	public void generarVentaDirecta(Cliente cliente,List<OrdenDeCompra> ordenCompras, DateTime fecha) throws SinCuentaCorrienteException {
		VentaDirecta ventaD=new VentaDirecta(cliente,ordenCompras,fecha,this);
		registrarVenta(ventaD);
	}
	
	private void registrarVenta(Venta ventaD) throws SinCuentaCorrienteException {
		this.ventas.add(ventaD);
		ventaD.modificarStock();
		double gastado=ventaD.calcularImporte();
		ventaD.agregarVentaAlCliente();
		this.incrementarMonto(gastado);
	}
	
	/**
	 *1- Crea una VentaConEntrega 
	 *2- Se Agrega a la lista de ventas realizadas
	 * @param cliente
	 * @param ordenCompras
	 * @param fecha
	 */
	public void generarVentaConEntrega(Cliente cliente, List<OrdenDeCompra> ordenCompras, DateTime fecha) {
		VentaConEntrega ventaE=new VentaConEntrega(cliente,ordenCompras,fecha,this);
		this.ventas.add(ventaE);
		ventaE.modificarStock();
		ventaE.enviar(fecha);
		ventaE.getEnvio().enviar();
		ventaE.getEnvio().cobrarContrareembolso();
	}
	
	/**
	 *1- Crea una VentaConCuentaCorriente  
	 *2- Incrementa el monto del local, dependiendo de lo que alla gastado
	 *3- Se Agrega a la lista de ventas realizadas
	 * @param cliente
	 * @param ordenCompras
	 * @param fecha
	 * @throws SinCuentaCorrienteException 
	 */
	public void generarVentaCC(Cliente cliente, List<OrdenDeCompra> ordenCompras,DateTime fecha) throws SinCuentaCorrienteException {
			
			VentaConCuentaCorriente ventaCC=new VentaConCuentaCorriente(cliente,ordenCompras,fecha,this);
			this.registrarVenta(ventaCC);
	}
	
	/**
	 * 1- Crear un objeto OfertaSimple y lo agrega a las lista de ofertas
	 * 2- Notifica a los clientes subcriptos que hay ofertas	
	 * @param nombre
	 * @param producto
	 * @param unidad    
	 * @param descuento  el descuento aplicado
	 * @param idOferta identificador de la oferta
	 * @param fechaInicio inicio de la oferta
	 * @param fechaFin    fin de la oferta
	 */
	public void generarOferta(String nombre,Producto producto,Unidad unidad,int descuento,DateTime fechaInicio,DateTime fechaFin) {
		
		Oferta oferta=new OfertaSimple(nombre,producto,descuento,unidad,fechaInicio,fechaFin);
		this.ofertas.add(oferta);
		this.enviarEmail(nombre);
	}
	/**
	 * Cambia su estado 
	 * Notifica a los observadores que cambio
	 * @param nombreOferta
	 */
	private void enviarEmail(String nombreOferta) {
		
		this.setChanged();
		this.notifyObservers(nombreOferta);
	}
	/**
	 * 
	 * @return Todas las ofertas en el sistema
	 */
	public List<Oferta> ofertasRealizadas(){
		
		return this.ofertas;
	}
	/**
	 *1-Crea un objeto OfertaCompuesta  , lo agrega a las lista ofertas compuestas
	 *2-Envia email a los clientes avisando de las ofertas
     * @param nombre
	 * @param producto
	 * @param unidad    
	 * @param descuento  el descuento aplicado
	 * @param idOferta identificador de la oferta
	 * @param fechaInicio inicio de la oferta
	 * @param fechaFin    fin de la oferta
	 */
	public void generarOfertas(String nombre,List<Oferta> ofertas,int descuento,DateTime fechaInicio,DateTime fechaFin) {
	
		Oferta oferta= new OfertaCompuesta(nombre,ofertas,descuento,fechaInicio,fechaFin);
		this.ofertas.add(oferta);
		this.enviarEmail(nombre);
	}
	/**
	 * Incrementa el monto del Comercio
	 * @param numero
	 */
	public void incrementarMonto(double numero) {
		this.montoRecaudado += numero;
	}
    /**
     * 
     * @return Monto en el sistema
     */
	public double getMontoRecaudado() {
		
		return this.montoRecaudado;
	}
	/**
	 * 
	 * @return todas al ventas en el sistema
	 */
	public List<Venta> getVentas() {
		
		return this.ventas;
	}
	/**
	 *1- Creo el objeto devolucion , lo agrego a la lista
	 *2- Descuento al comercio la cantidad de objetos devueltos
	 *3-Quito la venta de la lista de ventas realizadas
	 * @param cliente
	 * @param ordenCompras
	 * @param fecha
	 */
	public void generarUnaDevolcion(Cliente cliente,List<OrdenDeCompra> ordenCompras, DateTime fecha) {
		
		Devolucion devolucion= new Devolucion(cliente,ordenCompras,fecha,this);
		this.devoluciones.add(devolucion);
		double perdida= devolucion.calcularImporte();
		this.descontarMonto(perdida);
		devolucion.modificarStock();
		devolucion.devolverPlata();
		this.quitarVenta(cliente,ordenCompras,fecha);
		
	}
	/**
	 * Remuevo una venta realizada anteriormente
	 * @param cliente
	 * @param ordenCompras
	 * @param fecha
	 */
	private void quitarVenta(Cliente cliente, List<OrdenDeCompra> ordenCompras,DateTime fecha) {
		
		List<Venta> ventaAEliminar= new ArrayList<Venta>();
		
		for(Venta vAct:this.getVentas()){
			if(vAct.perteneceA(cliente,ordenCompras,fecha)){
				 ventaAEliminar.add(vAct);
			}
		}
		this.getVentas().remove(ventaAEliminar.get(0));
	}
	/**
	 * Descuenta un monto  al monton total del comercio
	 * @param perdida
	 */
	private void descontarMonto(double perdida) {
		
		this.montoRecaudado-=perdida;
		
	}
	/**
	 * 
	 * @return Retorna todos los cambios
	 */
	public List<Cambio> cambiosRealizados() {
		
		return this.cambios;
	}
	
	/**
	 * Creo un objeto cambio y lo agrego a la lista de cambios
	 * @param cliente
	 * @param ordenCompras
	 * @param ordenComprasNueva
	 * @param fecha
	 */
	public void generarCambio(Cliente cliente, List<OrdenDeCompra> ordenCompras, List<OrdenDeCompra> ordenComprasNueva, DateTime fecha) {
		
		Cambio cambio= new Cambio(cliente,ordenCompras,ordenComprasNueva,fecha,this);
		cambio.modificarStock();
		this.cambios.add(cambio);
	} 
	
	/**
	 * 
	 * @param producto 
	 * @param fecha 
	 * @return si un producto esta en oferta o no
	 */
	public boolean estaEnOferta(Producto producto, DateTime fecha) {
		boolean ret = false;
		    for(Oferta oAct:this.ofertas){
		    	if(oAct.perteneceA(producto) && oAct.ofertaValida(fecha))
				    	ret = true;
		    }
		return ret;
	}
	/**
	 * 
	 * @param producto
	 * @return el precio de una oferta si el producto esta en oferta
	 */
	public double getPrecioOfertaDe(Producto producto) {
		double var= 0d;
		for (Oferta of:this.ofertas){
			   if(of.perteneceA(producto))
				   var= of.getPrecioOferta();
		 }
		return var;
	} 
	
	/**
	 *  agregar un cliente a la lista de pedidos a enviar
	 * @param cliente 
	 */
	public void agregarAListaPedidos(Cliente cliente) {
		this.clientePedidos.add(cliente);
		
	}
	/**
	 * 
	 * @return la cantidad de clientes que tiene envios pendientes
	 */
	public int cantClientePedidos (){
		return this.clientePedidos.size();
	}
}
