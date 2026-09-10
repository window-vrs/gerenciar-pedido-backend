package br.com.pedido;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "br.com.pedido.model")
public class GerenciarPedidoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GerenciarPedidoApplication.class, args);
	}

}
