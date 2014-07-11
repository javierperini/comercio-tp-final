package Movimiento;
import java.util.List;

import org.joda.time.DateTime;

import Cliente.Cliente;
import Comercio.Comercio;
import Comercio.OrdenDeCompra;



public abstract class Movimiento {
		protected DateTime fecha;
		protected List<OrdenDeCompra> listadoDeProductos;
		protected Cliente cliente;
		protected Comercio comercio;
		protected double montoTotal;
		
		/**
		 *-CONSTRUCTOR
		 */
	 public Movimiento(Cliente unCliente, List<OrdenDeCompra> listadoDeProductos,DateTime fecha,Comercio comercio) {
			this.cliente=unCliente;
			this.listadoDeProductos = listadoDeProductos;
			this.fecha=fecha;
			this.comercio=comercio;
			this.montoTotal= 0d;
		}
        
	 	/**
		 *-Calcula el importe de la lista de ordenes de compra, osea la suma de: cada producto por su cantidad.
		 */
	 	public double calcularImporte(){
			double monto= 0d;
			for (OrdenDeCompra orden : this.listadoDeProductos) {
				if(this.comercio.estaEnOferta(orden.getUnProducto(),this.fecha)){
					monto+=comercio.getPrecioOfertaDe(orden.getUnProducto());
				}else{
					monto+=orden.getPrecio();
				}
			}
			this.montoTotal=monto;
			return monto;
		}
	 	/**
		 *-Cliente al que corresponde la venta.
		 */
		public Cliente getCliente(){
			return this.cliente;
		}
		/**
		 *-Metodo abstracto que deberan implementar: Venta, Devolucion y Cambio.
		 */
		public abstract void modificarStock();
		
		/**
		 *-La lista de ordenes de compra del cliente.
		 */
		public List<OrdenDeCompra> getOrdenes() {
			return this.listadoDeProductos;
		}
		/**
		 *-Fecha en que se realiza la venta.
		 */
		public DateTime getFecha(){
			return fecha;
		}
		public double getMontoTotal(){
			return this.montoTotal;
		}
		
}