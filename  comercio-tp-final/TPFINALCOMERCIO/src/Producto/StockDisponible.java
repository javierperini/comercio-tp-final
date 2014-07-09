package Producto;


public  class StockDisponible extends EstadoStock {
	
	/**
	 *  Incrementar stock
	 * @param cantidad numero a incrementar
	 * @param presentacion presentacion a incrementar
	 */
	@Override
	protected void incrementarStock(double cantidad, Presentacion presentacion) {
		presentacion.setStockTotal(presentacion.getStockTotal()+ cantidad);
	}
	
	/**
	 *  Decrementar stock
	 * @param cantidad numero a decrementar
	 * @param presentacion presentacion a decrementar
	 */
	@Override
	protected void decrementarStock(double cantidad, Presentacion presentacion){
		
		presentacion.setStockTotal(presentacion.getStockTotal()-cantidad);
		if((presentacion.getStockTotal()>presentacion.getStockMin()) && (presentacion.getStockTotal()<=presentacion.getStockCri())){
			presentacion.setEstado(new StockCritico());
		}else{ if(presentacion.getStockTotal()<=presentacion.getStockMin())
					presentacion.setEstado(new StockMinimo());
				
		}
		
	}

}
