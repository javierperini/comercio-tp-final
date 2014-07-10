package Test;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import Cliente.Cliente;
import Comercio.Comercio;
import Envio.Cancelado;
import Envio.EnProceso;
import Envio.Entregado;
import Envio.Envio;
import Envio.Pendiente;
import Envio.Reprogramado;



public class EnvioTest {

	public Envio envio;
	public Cliente clienteMock;
	public Comercio comercioMock;
	
	@Before
	public void SetUp(){
		
		///MOCKS
		clienteMock = mock(Cliente.class);
		comercioMock = mock(Comercio.class);
				
		////Envio
		envio = new Envio(clienteMock, 1000d, new DateTime(2014, 10, 6, 00, 00), comercioMock);
		
	}
	
	@Test
	public void envioIniciaConEstadoPendienteYConElMontoDeLaVentaComoMontoContrareembolso(){
		assertTrue(this.envio.getEstadoDeEnvio().getClass().equals(Pendiente.class));
	}
	
	@Test
	public void cuandoLlegaLaFechaDeSalidaElEnvioCambiaSuEstadoAEnProceso(){
		envio.enviar();
		assertTrue(this.envio.getEstadoDeEnvio().getClass().equals(EnProceso.class));
	}

	@Test
	public void cuandoElClienteNoEstaEnCasaSeCambiaElEstadoAReprogramadoYSeCambiaLaFecha(){
		this.envio.enviar();
		this.envio.elClienteNoSeEncuentra();
		this.envio.reprogramarFechaDeSalida(new DateTime(2014, 10, 7, 00, 00));
		assertTrue(this.envio.getEstadoDeEnvio().getClass().equals(Pendiente.class));
	}
	
	@Test
	public void cuandoSeEntregaElPedidoSeCambiaElEstadoAEntregado(){
		this.envio.setEstadoDeEnvio(new EnProceso());
		this.envio.cobrarContrareembolso();
		assertTrue(envio.getEstadoDeEnvio().getClass() == Entregado.class);
		verify(this.comercioMock,times(1)).incrementarMonto(1000d);
	}
	
	@Test
	public void cuandoSeCancelaElEnvioSeCambiaElEstadoACancelado(){
		this.envio.cancelarEnvio();
		assertTrue(envio.getEstadoDeEnvio().getClass() == Cancelado.class);
	}
	
	@Test
	public void getFechaTest(){
		this.envio.setEstadoDeEnvio(new Reprogramado());
		this.envio.setFechaDeSalida(2014, 10, 7, 00, 00);
		assertTrue(this.envio.getFecha().isEquals(2014, 10, 7, 00, 00));
	}
}
