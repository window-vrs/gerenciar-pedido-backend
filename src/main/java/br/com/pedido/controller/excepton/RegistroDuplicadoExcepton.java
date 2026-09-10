package br.com.pedido.controller.excepton;

public class RegistroDuplicadoExcepton extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4992610239069172425L;

	public RegistroDuplicadoExcepton(String mensagem) {
		super(mensagem);
	}

}
