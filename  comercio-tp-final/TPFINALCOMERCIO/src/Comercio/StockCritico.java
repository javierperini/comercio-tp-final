package Comercio;

public class StockCritico extends EstadoStock {

	//TESTEAR
	@Override
	protected void decrementarStock(double cantidad, Presentacion presentacion){
	   if(presentacion.getStockTotal()-cantidad<=presentacion.getStockMin()){
			presentacion.setStockTotal(presentacion.getStockTotal()-cantidad);
			presentacion.setEstado(new StockMinimo());
		}
		Double d=presentacion.getStockTotal()-cantidad;
		presentacion.setStockTotal(d);
	}

}
