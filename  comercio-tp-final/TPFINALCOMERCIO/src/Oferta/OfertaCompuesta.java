package Oferta;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import Producto.Producto;

public class OfertaCompuesta extends Oferta{

	private List<Oferta> ofertas;
	
	public OfertaCompuesta(String nombre, List<Oferta> unasOfertas, 
			double descuento, DateTime validoDesde, DateTime  validoHasta){		
		
		super(descuento, nombre, validoDesde, validoHasta);
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
