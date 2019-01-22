package com.algaworks.socialbooks.services.exception;

public class NaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = -695266613435056904L;
	
	public NaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public NaoEncontradoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}