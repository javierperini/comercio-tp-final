package Comercio;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

public class OfertaCompuesta extends Oferta{

	private List<Oferta> ofertas;
	
	public OfertaCompuesta(String nombre, List<Oferta> unasOfertas, 
			double descuento, int id, DateTime validoDesde, DateTime  validoHasta){		
		
		super(descuento, nombre, id, validoDesde, validoHasta);
		this.ofertas = new ArrayList<Oferta>();
		this.setOfertas(unasOfertas);
	}
	
	
	public List<Oferta> getOfertas() {
		return ofertas;
	}

	public void setOfertas(List<Oferta> ofertas) {
		this.ofertas = ofertas;
	}
	
	public double getPrecioOferta() {
		
		double descuento = this.getDescuento();
		double precioDeOferta = 0;
		
		for(Oferta o : this.getOfertas()){
			precioDeOferta += o.getPrecioOferta();
		}
		
		return precioDeOferta - ((precioDeOferta*descuento)/100);
	}

	//TESTEAR
	@Override
	public boolean ofertaValida(Producto producto, DateTime fecha) {
     	    for(Oferta o: this.getOfertas()){
		    	if(o.ofertaValida(producto, fecha))
		    		return true;
		    }
		return false;
	}

	//TESTEAR
	@Override
	public boolean perteneceA(Producto producto) {
		
		    for(Oferta o: this.getOfertas()){
		    	if(o.perteneceA(producto))
		    		return true;
		    }
		return false;
	}
	
}
