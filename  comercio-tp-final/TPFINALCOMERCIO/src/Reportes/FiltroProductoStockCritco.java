package Reportes;

import java.util.ArrayList;
import java.util.List;

import Comercio.Comercio;
import Producto.Producto;

public class FiltroProductoStockCritco<E> extends Filtro<Producto> {
	
	/**
	 * Devuelve los productos que tiene presentaciones  que superan el stock critico
	 */
	@Override
	protected List<Producto> filtrar(Comercio comercio) {
		List<Producto> resultado= new ArrayList<Producto>();
		for(Producto pAct:comercio.getProductos()){
			if(pAct.presentacionesSuperanStockCritico())
				resultado.add(pAct);
		}
		return resultado;
	}
	

}
