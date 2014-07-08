package Comercio;
import java.util.List;

import org.joda.time.DateTime;



public abstract class Movimiento {
		protected DateTime fecha;
		protected List<OrdenDeCompra> listadoDeProductos;
		protected Cliente cliente;
		protected Comercio comercio;
		
		
	 public Movimiento(Cliente unCliente, List<OrdenDeCompra> listadoDeProductos,DateTime fecha,Comercio comercio) {
			this.cliente=unCliente;
			this.listadoDeProductos = listadoDeProductos;
			this.fecha=fecha;
			this.comercio=comercio;
		}
        //TESTEAR
		public double calcularImporte(){
			double monto = 0d;
			for (OrdenDeCompra orden : this.listadoDeProductos) {
				if(this.comercio.estaEnOferta(orden.getUnProducto(),this.fecha)){
					monto+=comercio.getPrecioOfertaDe(orden.getUnProducto());
				}else{
					monto+=orden.getPrecio();
				}
			}
			return monto;
		}
		public Cliente getCliente(){
			return this.cliente;
		}
		public abstract void modificarStock();
		
		public List<OrdenDeCompra> getOrdenes() {
			return this.listadoDeProductos;
		}
		public DateTime getFecha(){
			return fecha;
		}
		
}