package Reportes;


import java.util.List;
import Comercio.Comercio;

public class Reporte<E> {

	private Comercio comercio;
	private Filtro<E> filtro;

	public Reporte(Comercio comercio) {
	  this.comercio= comercio;
	}
	
	public void setStrategy(Filtro<E> strategy) {
		this.filtro=strategy;
	}

	public List<E> filtrar() {
		return this.filtro.filtrar(this.comercio);
	}
	

}
