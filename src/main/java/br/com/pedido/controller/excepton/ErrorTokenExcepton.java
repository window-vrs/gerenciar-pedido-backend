package br.com.pedido.controller.excepton;

public class ErrorTokenExcepton extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4992610239069172425L;

	public ErrorTokenExcepton(String mensagem) {
		super(mensagem);
	}

}
