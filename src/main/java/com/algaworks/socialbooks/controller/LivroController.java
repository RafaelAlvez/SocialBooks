package com.algaworks.socialbooks.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.algaworks.socialbooks.domain.Comentario;
import com.algaworks.socialbooks.domain.Livro;
import com.algaworks.socialbooks.services.LivroService;

@RestController
@RequestMapping("/livros")
public class LivroController {

	@Autowired
	private LivroService livroService;

	@GetMapping
	public ResponseEntity<List<Livro>> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(livroService.listarTodos());
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> buscar(@PathVariable("id") Long id) {
		Optional<Livro> livro = livroService.Buscar(id);
		CacheControl cacheControl = CacheControl.maxAge(30, TimeUnit.SECONDS);
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(livro);
	}

	@PostMapping
	public ResponseEntity<Void> salvar(@Valid @RequestBody Livro livro) {
		livro = livroService.salvar(livro);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(livro.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		livroService.deletar(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> atualizar(@Valid @RequestBody Livro livro, @PathVariable("id") Long id) {
		livro.setId(id);
		livroService.atualizar(livro, id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PostMapping(value = "/comentarios/{id}")
	public ResponseEntity<Void> adicionarComentario(@PathVariable("id") Long idLivro, @Valid @RequestBody Comentario comentario) {
		comentario = livroService.salvarComentario(idLivro, comentario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping(value = "/comentarios/{id}")
	public ResponseEntity<List<Comentario>> listarComentarios(@PathVariable("id") Long idLivro) {
		List<Comentario> listarComentarios = livroService.listarComentarios(idLivro);
		return ResponseEntity.status(HttpStatus.OK).body(listarComentarios);
	}
	
	@PutMapping(value = "/comentarios/{id}")
	public ResponseEntity<Void> atualizarComentario(@PathVariable("id") Long idLivro, @Valid @RequestBody Comentario comentario) {
			livroService.atualizarComentario(idLivro, comentario);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@DeleteMapping(value = "/comentarios/{id}")
	public ResponseEntity<Void> deletarComentario(@PathVariable("id") Long idComentario) {
		livroService.deletarComentario(idComentario);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
}