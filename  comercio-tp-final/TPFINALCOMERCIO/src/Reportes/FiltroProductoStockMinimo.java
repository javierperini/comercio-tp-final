package Reportes;

import java.util.ArrayList;
import java.util.List;

import Comercio.Comercio;
import Producto.Producto;
import Producto.Unidad;

public class FiltroProductoStockMinimo<T> extends Filtro<Producto> {
	



	@Override
	protected List<Producto> filtrar(Comercio comercio) {
		List<Producto> resultado= new ArrayList<Producto>();
		for(Producto pAct:comercio.getProductos()){
			if(pAct.presentacionesSuperanStockMinimo())
				resultado.add(pAct);
		}
		return resultado;
	}


}