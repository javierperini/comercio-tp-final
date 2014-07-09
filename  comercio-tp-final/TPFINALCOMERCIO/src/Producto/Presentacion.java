package Producto;

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
	
	/**
	 * 
	 * @return  el stock total de la presentacion
	 */
	public double getStockTotal(){
		return this.stockTotal;
	}
	
	/**
	 * 
	 * @return la unidad de la presentacion
	 */
	public Unidad getUnidad() {
		return unidad;
	}

	/**
	 * 
	 * @return la ubicacion de una presentacion
	 */
	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	/**
	 * 
	 * @return el precio de venta de una presentacion
	 */
	public double getPrecioVenta() {
		return precioVenta;
	}
	
	/**
	 * 
	 * @param precioVenta  nuevo precio de la presentacion
	 */
	private void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}

	/**
	 * 
	 * @return el stock minimo de una presentacion
	 */
	public double getStockMin() {
		return stockMin;
	}

	/**
	 * 
	 * @return el stock critico de una presentacion
	 */
	public double getStockCri() {
		return stockCri;
	}

	/**
	 * 
	 * @param precio  de venta nuevo
	 */
	public void cambiarPrecio(double precio) {
		this.preciosAnteriores.add(this.precioVenta);
		this.setPrecioVenta(precio);
	}
	
	/**
	 * 
	 * @param cantidad   numero a decrementar
	 * @throws NoTengoStock  excepcion que  se activa cuando no hay stock para ese pedido
	 */
	public void decrementarStock(double cantidad)throws NoTengoStock {
		if(this.getStockTotal()<cantidad){
			System.out.println("No tengo stock para esta accion");
			throw new NoTengoStock();
		}
		this.estado.decrementarStock(cantidad,this);
	}
	
	/**
	 * 
	 * @param cantidad numero a  incrementar
	 */
	public void incrementarStock(double cantidad) {
		this.estado.incrementarStock(cantidad, this);
	}
	/**
	 * 
	 * @return la lista de precios por los que fue pasando una presentacion
	 */
	public List<Double> getPrecionAnteriores() {
		
		return this.preciosAnteriores;
	}
	
	/**
	 * 
	 * @param cantidad nuevo stock total
	 */
	public void setStockTotal(double cantidad) {
		this.stockTotal=cantidad;
		
	}
	
	/**
	 *  setea un nuevo estado
	 * @param newEstado nuevo estado de la presentacion
	 */
	public void setEstado(EstadoStock newEstado) {
		this.estado=newEstado;
		
	}
	/**
	 * 
	 * @return el estado que tiene actualmente la presentacion
	 */
	public EstadoStock getEstado() {
		
		return this.estado;
	}
	

}
