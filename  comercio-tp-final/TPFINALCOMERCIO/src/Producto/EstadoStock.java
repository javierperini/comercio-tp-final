package Producto;


public abstract class EstadoStock {
	
	/**
	 *  Decrementar stock
	 * @param cantidad numero a decrementar
	 * @param presentacion presentacion a decrementar
	 */
	protected void decrementarStock(double cantidad,Presentacion presentacion){
		System.out.println("No puedo realizar la accion");
	}

	/**
	 *  Incrementar stock
	 * @param cantidad numero a incrementar
	 * @param presentacion presentacion a incrementar
	 */
	protected void incrementarStock(double cantidad, Presentacion presentacion) {
		presentacion.setStockTotal(presentacion.getStockTotal()+cantidad);
		if(presentacion.getStockTotal()>(presentacion.getStockCri()+10d)){
			presentacion.setEstado(new StockDisponible());
		}
	}

}
