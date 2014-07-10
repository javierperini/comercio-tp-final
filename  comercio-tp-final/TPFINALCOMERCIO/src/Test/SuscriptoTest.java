package Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import Cliente.Cliente;
import Cliente.Suscripto;

public class SuscriptoTest {

	Suscripto estadoSuscripto;
	Cliente clienteMock;
	
	@Before
	public void setUp(){
		this.clienteMock = mock(Cliente.class);
		this.estadoSuscripto = new Suscripto();
		this.estadoSuscripto.enviarMail("oferta", this.clienteMock);
	}
	
	@Test
	public void testEnviarMail(){
		verify(this.clienteMock,times(1)).enviarMail("oferta");
	}

}
