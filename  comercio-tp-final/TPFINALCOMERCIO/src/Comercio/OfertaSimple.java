package Comercio;

import org.joda.time.DateTime;



public class OfertaSimple extends Oferta{

	private Producto unProducto;
	private Unidad unaUnidad;

	public OfertaSimple(String nombre, Producto unProducto, 
			double descuento, Unidad unaUnidad, int id, DateTime validoDesde, DateTime  validoHasta) {
		
		super(descuento, nombre, id, validoDesde, validoHasta);
		this.unProducto = unProducto;
		this.unaUnidad = unaUnidad;
	}

	public Producto getUnProducto() {
		return unProducto;
	}



	public Unidad getUnaUnidad() {
		return unaUnidad;
	}
	
	@Override
	public double getPrecioOferta() {
		
		double precioDeVenta = this.getUnProducto().getPresentacion(this.getUnaUnidad()).getPrecioVenta();		
		double descuento = this.getDescuento();
		double precioDeOferta = (precioDeVenta - (precioDeVenta*descuento)/100);
		return precioDeOferta;
	}
	//TESTEAR
	@Override
	public boolean ofertaValida(Producto producto, DateTime fecha) {
		
       return  this.getUnProducto().equals(producto) && fecha.isAfter(this.getValidoDesde()) && fecha.isBefore(this.getValidoHasta());
	}
	//TESTEAR
	@Override
	public boolean perteneceA(Producto producto) {
		
		return this.getUnProducto().equals(producto);
	}
	
}
