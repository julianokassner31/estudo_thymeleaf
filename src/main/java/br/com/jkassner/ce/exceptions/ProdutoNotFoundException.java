package br.com.jkassner.ce.exceptions;

public class ProdutoNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProdutoNotFoundException() {
		super("Produto não encontrado.");
	}
}
