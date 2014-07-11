package Reportes;


import java.util.List;
import Comercio.Comercio;

public class Reporte<T> {

	private Comercio comercio;
	private Filtro<T> filtro;

	public Reporte(Comercio comercio) {
	  this.comercio= comercio;
	}
	
	public void setStrategy(Filtro<T> strategy) {
		this.filtro=strategy;
	}

	public List<T> filtrar() {
		return this.filtro.filtrar(this.comercio);
	}
	

}
