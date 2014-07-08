package Comercio;

public abstract class EstadoStock {
	
	protected void decrementarStock(double cantidad,Presentacion presentacion){
		System.out.println("No puedo realizar la accion");
	}
	protected void incrementarStock(double cantidad, Presentacion presentacion) {
		presentacion.setStockTotal(presentacion.getStockTotal()+cantidad);
		if(presentacion.getStockTotal()>(presentacion.getStockCri()+10d)){
			presentacion.setEstado(new StockDisponible());
		}
	}

}
