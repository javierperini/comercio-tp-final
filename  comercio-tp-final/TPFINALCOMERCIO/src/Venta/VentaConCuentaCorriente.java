package Venta;

import java.util.List;

import org.joda.time.DateTime;

import Cliente.Cliente;
import Comercio.Comercio;
import Comercio.OrdenDeCompra;
import Excepciones.SaldoInsuficienteException;
import Excepciones.SinCuentaCorrienteException;

public class VentaConCuentaCorriente extends Venta{
	/**
	 * CONSTRUCTOR
	 * @param unCliente este parametro refiere al cliente asociado a la venta.
	 * @param listadoDeProductos este parametro refiere a los productos asociados a la venta.
	 * @param fecha este parametro refiere a la fecha de creacion de la venta.
	 * @param comercio este parametro refiere al comercio donde se genero dicha venta.
	 */
	public VentaConCuentaCorriente(Cliente unCliente,List<OrdenDeCompra> listadoDeProductos,DateTime fecha,Comercio comercio) {
		super(unCliente, listadoDeProductos, fecha, comercio);
	}
	/**
	 * Descuenta saldo de la cuenta corriente del cliente, si es que tiene una.
	 * @param unCliente este parametro refiere al cliente al cual se le quiere cobrar la venta.
	 * @throws SinCuentaCorrienteException Esta excepcion salta si el cliente no tiene una cuenta corriente.
	 * @throws SaldoInsuficienteException Esta excepcion salta si el cliente no tiene saldo suficiente para pagar.
	 */
	public void cobrar(Cliente unCliente) throws SinCuentaCorrienteException, SaldoInsuficienteException {
	      unCliente.descontarSaldo(this.calcularImporte());
	}
	/**
	 * Le agrega la venta al cliente y a su cuenta corriente, si es que tiene.
	 * @throws SinCuentaCorrienteException Esta excepcion salta si el cliente no tiene una cuenta corriente.
	 */
	@Override
	public void agregarVentaAlCliente() throws SinCuentaCorrienteException{
		if(! this.comercio.estaRegistrado(this.getCliente()))
			this.comercio.agregarCliente(this.getCliente());
		this.getCliente().addCompra(this);
		this.getCliente().getCuentaCorriente().addCompra(this);
	}
}

