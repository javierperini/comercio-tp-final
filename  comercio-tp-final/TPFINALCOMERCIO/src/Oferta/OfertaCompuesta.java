package Oferta;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import Producto.Producto;

public class OfertaCompuesta extends Oferta{

	private List<Oferta> ofertas;
	/**
	 * CONSTRUCTOR
	 * @param nombre este parametro refiere al nombre de la oferta.
	 * @param unasOfertas este parametro refiere a las ofertas que entran dentro de la oferta compuesta.
	 * @param descuento este parametro refiere al porcentaje de descuento que se le va a aplicar a la oferta compuesta.
	 * @param validoDesde este parametro refiere a la fecha de inicio de la oferta.
	 * @param validoHasta este parametro refiere a la fecha de fin de la oferta.
	 */
	public OfertaCompuesta(String nombre, List<Oferta> unasOfertas, 
			double descuento, DateTime validoDesde, DateTime  validoHasta){		
		
		super(descuento, nombre, validoDesde, validoHasta);
		this.ofertas = new ArrayList<Oferta>();
		this.setOfertas(unasOfertas);
	}
	/**
	 * 
	 * @return Retorna las ofertas incluidas dentro de la oferta compuesta.
	 */
	public List<Oferta> getOfertas() {
		return ofertas;
	}
	/**
	 * Setea la lista de ofertas.
	 * @param ofertas este parametro refiere a las ofertas que se quieren setear.
	 */
	public void setOfertas(List<Oferta> ofertas) {
		this.ofertas = ofertas;
	}
	/**
	 * @return retorna el precio de la oferta
	 */
	@Override
	public double getPrecioOferta() {
		
		double descuento = this.getDescuento();
		double precioDeOferta = 0;
		
		for(Oferta o : this.getOfertas()){
			precioDeOferta += o.getPrecioOferta();
		}
		
		return precioDeOferta - ((precioDeOferta*descuento)/100);
	}
	/**
	 * @param producto este parametro refiere al producto que se quiere saber si esta dentro de la oferta.
	 * @return Retorna un booleano indicando si el producto pertenece a la oferta. 
	 */
	@Override
	public boolean perteneceA(Producto producto) {
		boolean ret = false;
		    for(Oferta o: this.getOfertas()){
		    	if(o.perteneceA(producto))
		    		ret = true;
		    }
		return ret;
	}	
}
