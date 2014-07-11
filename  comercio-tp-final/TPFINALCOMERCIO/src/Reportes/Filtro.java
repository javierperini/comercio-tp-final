package Reportes;

import java.util.List;

import Comercio.Comercio;

public abstract class  Filtro<T> {
	protected abstract List<T> filtrar(Comercio comercio);

}
