package Comercio;

import Excepciones.SaldoInsuficienteException;
import Excepciones.SinCuentaCorrienteException;


public abstract class EstadoCuentaCorriente {
		
	public abstract double getSaldo(CuentaCorriente cc) throws SinCuentaCorrienteException;

	public abstract void setSaldo(double saldo, CuentaCorriente cc);
	
	public abstract void disminuirSaldo(double dinero, CuentaCorriente cc) throws SinCuentaCorrienteException, SaldoInsuficienteException;

	public abstract void addCompra(VentaConCuentaCorriente compra, CuentaCorriente cc) throws SinCuentaCorrienteException;
} 