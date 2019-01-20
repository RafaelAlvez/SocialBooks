package com.algaworks.socialbooks.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.socialbooks.domain.Comentarios;
import com.algaworks.socialbooks.domain.Livro;
import com.algaworks.socialbooks.repository.ComentariosRepository;
import com.algaworks.socialbooks.repository.LivrosRepository;
import com.algaworks.socialbooks.services.exception.LivroServiceException;

@Service		
public class LivroServiceImpl implements LivroService {
	
	private static final Logger logger = LoggerFactory.getLogger(LivroServiceImpl.class);
	
	@Autowired
	private LivrosRepository livrosRepository;
	
	@Autowired
	private ComentariosRepository comentariosRepository;

	@Override
	public List<Livro> listarTodos() {
		logger.info("listando todos os livros!");
		List<Livro> livro = livrosRepository.findAll();
		return livro;
	}

	@Override
	public Optional<Livro> Buscar(Long id) {
		logger.info("Buscar livro por id: " + id);
		Optional<Livro> livro = livrosRepository.findById(id);
		
		if(!livro.isPresent())
			throw new LivroServiceException("livro não encontrado!");
		
		return livro;
	}

	@Override
	public Livro salvar(Livro livro) {
		livro.setId(null);
		Livro livroSalvo = livrosRepository.save(livro);
		logger.info("Novo livro salvo");
		return livroSalvo;
	}

	@Override
	public void atualizar(Livro livro, Long id) {
		Buscar(id);
		livrosRepository.save(livro);
		logger.info("Aluaizado livro");
	}

	@Override
	public void deletar(Long id) {
		try {
			logger.info("Deletar livro: " + id);
			livrosRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new LivroServiceException("O livro não pode ser encontrado!");
		}
	}
	
	public Comentarios salvarComentario(final Long idLivro, final Comentarios comentario) {
		logger.info("Adicionario comentario ao livro: " + idLivro);
		Optional<Livro> livro = Buscar(idLivro);
		
		comentario.setLivro(livro.get());
		comentario.setUsuario("Rafael.Alvez");
		comentario.setAutor(livro.get().getAutor());
		comentario.setData(new Date());
		
		return comentariosRepository.save(comentario);
	}
	
	public List<Comentarios> listarComentarios(final Long idLivro) {
		Optional<Livro> buscar = Buscar(idLivro);
		return buscar.get().getComentarios();
	}
	
	

}
