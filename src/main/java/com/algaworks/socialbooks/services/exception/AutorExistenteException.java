package com.algaworks.socialbooks.services.exception;

public class AutorExistenteException extends RuntimeException {
 
	private static final long serialVersionUID = -3365074602709939343L;

	public AutorExistenteException(String mensagem) {
		super(mensagem);
	}
	
	public AutorExistenteException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}