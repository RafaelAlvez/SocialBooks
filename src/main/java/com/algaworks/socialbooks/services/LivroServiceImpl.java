package com.algaworks.socialbooks.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.algaworks.socialbooks.domain.Comentario;
import com.algaworks.socialbooks.domain.Livro;
import com.algaworks.socialbooks.repository.ComentariosRepository;
import com.algaworks.socialbooks.repository.LivrosRepository;
import com.algaworks.socialbooks.services.exception.NaoEncontradoException;

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
			throw new NaoEncontradoException("livro não encontrado!");
		
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
			throw new NaoEncontradoException("O livro não pode ser encontrado!");
		}
	}
	
	@Override
	public Comentario adicionarComentario(final Long idLivro, final Comentario comentario) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		logger.info("Adicionario comentario ao livro: " + idLivro);
		Optional<Livro> livro = Buscar(idLivro);
		
		comentario.setLivro(livro.get());
		comentario.setUsuario(auth.getName());
		comentario.setData(new Date());
		
		return comentariosRepository.save(comentario);
	}
	
	@Override
	public List<Comentario> listarComentarios(final Long idLivro) {
		Optional<Livro> buscar = Buscar(idLivro);
		return buscar.get().getComentarios();
	}

	@Override
	public void atualizarComentario(final Long idLivro, Comentario comentario) {
		Buscar(idLivro);
		comentariosRepository.save(comentario);
	}

	@Override
	public void deletarComentario(final Long idComentario) {
		logger.info("Deletar comentario");
		try {
			comentariosRepository.deleteById(idComentario);
		} catch (EmptyResultDataAccessException e) {
			throw new NaoEncontradoException("Comentário inexistente!");
		}
	}

}
