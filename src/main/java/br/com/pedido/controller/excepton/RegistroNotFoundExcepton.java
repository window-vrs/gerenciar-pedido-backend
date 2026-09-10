package br.com.pedido.controller.excepton;

public class RegistroNotFoundExcepton extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4992610239069172425L;

	public RegistroNotFoundExcepton(String mensagem) {
		super(mensagem);
	}

}
