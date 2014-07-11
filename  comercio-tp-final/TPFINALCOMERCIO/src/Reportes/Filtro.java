package Reportes;

import java.util.List;

import Comercio.Comercio;

public abstract class  Filtro<E> {
	protected abstract List<E> filtrar(Comercio comercio);

}
