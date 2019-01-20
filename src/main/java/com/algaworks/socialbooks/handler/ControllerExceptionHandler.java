package com.algaworks.socialbooks.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.algaworks.socialbooks.domain.DetalheErro;
import com.algaworks.socialbooks.services.exception.LivroServiceException;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(LivroServiceException.class)
	public ResponseEntity<DetalheErro> handlerLivroServiceException(LivroServiceException e, HttpServletRequest request) {
		
		DetalheErro erro = new DetalheErro();
		erro.setStatus(404l);
		erro.setTitulo("livro não pode ser encontrado");
		erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/404");
		erro.setTimeStamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	

}	