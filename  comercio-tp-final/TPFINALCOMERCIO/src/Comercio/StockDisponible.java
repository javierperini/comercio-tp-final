package Comercio;

public  class StockDisponible extends EstadoStock {

	@Override
	protected void incrementarStock(double cantidad, Presentacion presentacion) {
		presentacion.setStockTotal(presentacion.getStockTotal()+ cantidad);
	}
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
