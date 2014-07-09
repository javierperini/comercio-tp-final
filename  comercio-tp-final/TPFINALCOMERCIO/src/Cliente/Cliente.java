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
import Movimiento.Venta;
import Producto.Producto;
import Producto.Unidad;



public class Cliente implements Observer{
	
	private List<Venta> compras;
	private String nombre;
	private EstadoSuscripto estadoS;
	private CuentaCorriente cuentaCorriente;
	private OrdenDeCompra ordenPedida;
	
	public Cliente(String nombre){
		this.compras = new ArrayList<Venta>();
		this.nombre=nombre;
		this.setEstadoS(new NoSuscripto());
		this.cuentaCorriente = new CuentaCorriente();
	}
	
	
	//TESTEAR
	public List<Venta> getCompras() {
		return this.compras;
	}
	//TESTEAR
	public String getNombre() {
		return this.nombre;
	}
	//TESTEAR 
	public EstadoSuscripto getEstadoS() {
		return this.estadoS;
	}
	
	public void setEstadoS(EstadoSuscripto estadoS) {
		this.estadoS = estadoS;
	}

	public CuentaCorriente getCuentaCorriente() {
		return this.cuentaCorriente;
	}
	//TESTEAR
	public void setCuentaCorriente(CuentaCorriente cuentaCorriente) {
		this.cuentaCorriente = cuentaCorriente;
	}

	
	public void depositar(double dinero){
		this.getCuentaCorriente().aumentarSaldo(dinero);		
	}
	
	public double getSaldo() throws SinCuentaCorrienteException {
		return this.getCuentaCorriente().saldo();
	}
	
	public void descontarSaldo(double unSaldo) throws SinCuentaCorrienteException, SaldoInsuficienteException{
		this.getCuentaCorriente().descontarSaldo(unSaldo);
	}
	
	public void addCompra(Venta venta){
		this.compras.add(venta);
	}

	//TESTEAR
	public void update(Observable o, Object arg) {
		
	    String nombreOferta = (String)arg;
	    this.getEstadoS().enviarMail(nombreOferta);
		
		
	}
	
	public void recibirCambio(List<OrdenDeCompra> listaDeProductosNueva) {
		// TODO Auto-generated method stub
		// lo pide victor
	}
	public void pagarAlContado(double calcularImporte) {
		// TODO Auto-generated method stub
		//los pide victor
	}
	//TESTEAR de la extencion
	public void pedido(Producto producto, Unidad unidad, double cantidad,Comercio comercio){
		comercio.agregarAListaPedidos(this);
		DateTime today=new DateTime(2014, 10, 06, 00, 00);
		this.ordenPedida= new OrdenDeCompra(producto,unidad,cantidad,today);
	}
	//TESTEAR de la extencion
	public void avisoDePedido(OrdenDeCompra pedida) {
	   if(this.ordenPedida.getCantidad()==pedida.getCantidad() && this.ordenPedida.getUnProducto().equals(pedida.getUnProducto()) && this.ordenPedida.getUnaUnidad()== pedida.getUnaUnidad()){
		    System.out.println("Esta en stock tu pedido");
	   }
		
	}



}
