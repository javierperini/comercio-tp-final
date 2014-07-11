package Reportes;

import java.util.List;

import Comercio.Comercio;
import Envio.Envio;

public class FiltroEnvioPendiente<E> extends Filtro<Envio> {

	@Override
	protected List<Envio> filtrar(Comercio comercio) {
		
		return comercio.getEnviosPendientes();
	}

}
