package com.algaworks.socialbooks.services.exception;

public class LivroServiceException extends RuntimeException {

	private static final long serialVersionUID = -5938425129623209665L;
	
	public LivroServiceException(String mensagem) {
		super(mensagem);
	}
	
	public LivroServiceException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}