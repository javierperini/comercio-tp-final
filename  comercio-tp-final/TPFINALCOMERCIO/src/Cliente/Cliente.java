package Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.joda.time.DateTime;

import Comercio.Comercio;
import Comercio.OrdenDeCompra;
import Excepciones.SaldoInsuficienteException;
import Excepciones.SinCuentaCorrienteException;
import Producto.Producto;
import Producto.Unidad;
import Venta.Venta;



public class Cliente implements Observer{
	
	private List<Venta> compras;
	private String nombre;
	private EstadoSuscripto estadoS;
	private CuentaCorriente cuentaCorriente;
	private OrdenDeCompra ordenPedida;
	
	/**
	 * CONSTRUCTOR
	 * @param nombre este parametro refiere al nombre del cliente
	 */
	public Cliente(String nombre){
		this.compras = new ArrayList<Venta>();
		this.nombre=nombre;
		this.setEstadoS(new NoSuscripto());
		this.cuentaCorriente = new CuentaCorriente();
	}
	
	/**
	 *
	 * @return Retorna las compras realizadas por el cliente.
	 */
	public List<Venta> getCompras() {
		return this.compras;
	}
	
	/**
	 * 
	 * @return retorna el nombre del cliente.
	 */
	public String getNombre() {
		return this.nombre;
	}
	 
	/**
	 * 
	 * @return retorna el estado suscripto del cliente. 
	 */
	public EstadoSuscripto getEstadoS() {
		return this.estadoS;
	}
	
	/**
	 * Setea el estado suscripto del cliente
	 * @param estadoS este parametro refiere al estado suscripto a setear del cliente.
	 */
	public void setEstadoS(EstadoSuscripto estadoS) {
		this.estadoS = estadoS;
	}

	/**
	 * 
	 * @return retorna la cuenta corriente del cliente.
	 */
	public CuentaCorriente getCuentaCorriente() {
		return this.cuentaCorriente;
	}
	
	/**
	 * Setea la cuenta corriente del cliente
	 * @param cuentaCorriente este parametro refiere a la cuenta corriente del cliente.
	 */
	public void setCuentaCorriente(CuentaCorriente cuentaCorriente) {
		this.cuentaCorriente = cuentaCorriente;
	}

	/**
	 * Deposita dinero en la cuenta corriente del cliente.
	 * @param dinero este parametro refiere al dinero a depositar en la cuenta corriente del cliente.
	 */
	public void depositar(double dinero){
		this.getCuentaCorriente().aumentarSaldo(dinero);		
	}
	
	/**
	 * 
	 * @return retorna el saldo que tiene en la cuenta corriente el cliente.
	 * @throws SinCuentaCorrienteException si el cliente no tiene cuenta corriente activa larga esta excepcion.
	 */
	public double getSaldo() throws SinCuentaCorrienteException {
		return this.getCuentaCorriente().saldo();
	}
	
	/**
	 * Descuenta saldo de la cuenta corriente del cliente
	 * @param unSaldo este parametro refiere al saldo a descontar de la cuenta corriente del cliente.
	 * @throws SinCuentaCorrienteException si el cliente no tiene una cuenta corriente no se le puede 
	 * descontar ningun saldo y salta esta excepcion.
	 * @throws SaldoInsuficienteException si el cliente no tiene saldo suficiente para descontar de la cuenta corriente
	 * se larga esta excepcion. 
	 */
	public void descontarSaldo(double unSaldo) throws SinCuentaCorrienteException, SaldoInsuficienteException{
		this.getCuentaCorriente().descontarSaldo(unSaldo);
	}
	
	/**
	 * Agrega una venta a la lista de compras del cliente.
	 * @param venta este parametro refiere a la venta a agregar a la lista de compras del cliente.
	 */
	public void addCompra(Venta venta){
		this.compras.add(venta);
	}

	public void update(Observable o, Object arg) {
	    String nombreOferta = (String)arg;
	    this.getEstadoS().enviarMail(nombreOferta, this);
	}
	
	/**
	 * Simula el envio de un mail al cliente.
	 * @param nombreDeOferta este parametro refiere al nombre de la oferta que se le enviara por mail al cliente.
	 */
	public void enviarMail(String nombreDeOferta){
		System.out.println(nombreDeOferta);
	}
	
	//TESTEAR de la extencion
	public void pedido(Producto producto, Unidad unidad, double cantidad,Comercio comercio){
		comercio.agregarAListaPedidos(this);
		DateTime today=new DateTime(2014, 10, 06, 00, 00);
		this.ordenPedida= new OrdenDeCompra(producto,unidad,cantidad,today);
	}
	//TESTEAR de la extencion
	public void avisoDePedido(OrdenDeCompra pedida) {
	   if(this.ordenPedida.getCantidad()==pedida.getCantidad() && 
			   this.ordenPedida.getUnProducto().equals(pedida.getUnProducto()) && 
			   this.ordenPedida.getUnaUnidad()== pedida.getUnaUnidad())
		    System.out.println("Esta en stock tu pedido");
	}

}
