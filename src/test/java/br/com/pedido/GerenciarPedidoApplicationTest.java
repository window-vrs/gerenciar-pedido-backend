package br.com.pedido;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import lombok.AllArgsConstructor;

@SpringBootTest
@AllArgsConstructor
public class GerenciarPedidoApplicationTest {

	private final ApplicationContext context;
	
	@Test
	public void contextLoads() {
		assertNotNull(context);
	}

}
