package com.algaworks.socialbooks.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.algaworks.socialbooks.domain.DetalheErro;
import com.algaworks.socialbooks.services.exception.AutorExistenteException;
import com.algaworks.socialbooks.services.exception.NaoEncontradoException;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(NaoEncontradoException.class)
	public ResponseEntity<DetalheErro> handleLivroServiceException(final NaoEncontradoException e, final HttpServletRequest request) {
		
		DetalheErro erro = new DetalheErro();
		erro.setStatus(404L);
		erro.setTitulo("livro não pode ser encontrado");
		erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/404");
		erro.setTimeStamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(AutorExistenteException.class)
	public ResponseEntity<DetalheErro> handleAutorExistenteException(final AutorExistenteException e, final HttpServletRequest request) {
		
		DetalheErro erro = new DetalheErro();
		erro.setStatus(409L);
		erro.setTitulo("Autor ja existe!");
		erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/409");
		erro.setTimeStamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<DetalheErro> handleDataIntegrityViolationException(final DataIntegrityViolationException e, final HttpServletRequest request) {
		
		DetalheErro erro = new DetalheErro();
		erro.setStatus(400L);
		erro.setTitulo("Requisicao invalida");
		erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/409");
		erro.setTimeStamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
}	