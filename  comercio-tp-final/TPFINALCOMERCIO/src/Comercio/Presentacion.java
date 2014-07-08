package Comercio;

import java.util.ArrayList;
import java.util.List;

import Excepciones.NoTengoStock;

public class Presentacion {

	private Unidad unidad;
	private Ubicacion ubicacion;
	private double precioVenta;
	private double precioCompra;
	private String codBarra;
	private double stockMin;
	private double stockCri;
	private List<Double> preciosAnteriores;
	private double stockTotal;
	private EstadoStock estado;

	public Presentacion(Unidad unidad, Ubicacion ubicacion, double pVenta, double pCompra,String codBarra,double stockTotal, double stockMin, double stockCri) {
		//(unidad, ubicacion,precioVenta,precioCompra,codigoDeBarra,stockTotal,StockMin,StockCri)
		this.preciosAnteriores= new ArrayList<Double>();
		this.unidad=unidad;
		this.ubicacion=ubicacion;
		this.precioVenta=pVenta;
		this.precioCompra=pCompra;
		this.codBarra=codBarra;
		this.stockMin=stockMin;
		this.stockCri=stockCri;
		this.stockTotal=stockTotal;
		this.estado=new StockDisponible();
		
	}
	public double getStockTotal(){
		return this.stockTotal;
	}
	
	public Unidad getUnidad() {
		return unidad;
	}


	//TESTEAR
	public Ubicacion getUbicacion() {
		return ubicacion;
	}



	public double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}



	public double getStockMin() {
		return stockMin;
	}



	public double getStockCri() {
		return stockCri;
	}




	public void cambiarPrecio(double precio) {
		this.preciosAnteriores.add(this.precioVenta);
		this.setPrecioVenta(precio);
	}
	public void decrementarStock(double cantidad)throws NoTengoStock {
		if(this.getStockTotal()<cantidad){
			System.out.println("No tengo stock para esta accion");
			throw new NoTengoStock();
		}
		this.estado.decrementarStock(cantidad,this);
	}
	
	public void incrementarStock(double cantidad) {
		this.estado.incrementarStock(cantidad, this);
	}
	public List<Double> getPrecionAnteriores() {
		
		return this.preciosAnteriores;
	}

	public void setStockTotal(double d) {
		this.stockTotal=d;
		
	}
	public void setEstado(EstadoStock newEstado) {
		this.estado=newEstado;
		
	}
	public EstadoStock getEstado() {
		
		return this.estado;
	}
	

}
