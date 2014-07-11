package Reportes;


import java.util.List;
import Comercio.Comercio;

public class Reporte<E> {

	private Comercio comercio;
	private Filtro<E> filtro;

	public Reporte(Comercio comercio) {
	  this.comercio= comercio;
	}
	/**
	 * 	setea la strategia nueva
	 * @param strategy la strategia que se va usar
	 */
	public void setStrategy(Filtro<E> strategy) {
		this.filtro=strategy;
	}
	/**
	 * filtra datos  dependiendo la stragegia usada
	 * @return lista de elementos filtrados
	 */
	public List<E> filtrar() {
		return this.filtro.filtrar(this.comercio);
	}
	

}
