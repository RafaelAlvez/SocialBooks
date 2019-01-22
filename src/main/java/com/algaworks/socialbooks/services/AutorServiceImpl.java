package com.algaworks.socialbooks.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.socialbooks.domain.Autor;
import com.algaworks.socialbooks.repository.AutorRepository;
import com.algaworks.socialbooks.services.exception.NaoEncontradoException;

@Service
public class AutorServiceImpl implements AutorService {
	
	private static final Logger logger = LoggerFactory.getLogger(AutorServiceImpl.class);
	
	@Autowired
	private AutorRepository autorRepository;

	@Override
	public List<Autor> listar() {
		logger.info("Bucando todos os autores");
		List<Autor> autores = autorRepository.findAll();
		return autores;
	}
	
	@Override
	public Autor salvar(Autor autor) {
		if (autor.getId() != null) {
			Optional<Autor> a = autorRepository.findById(autor.getId());
			if (a.isPresent()) {
				throw new NaoEncontradoException("Autor ja existe!"); 
			}
		}
		return autorRepository.save(autor);
	}
	
	@Override
	public Autor buscar(Long id) {
		Optional<Autor> findById = autorRepository.findById(id);
		if (!findById.isPresent()) {
			throw new NaoEncontradoException("Autor não encontrado!");
		}
		return findById.get();
	}
	
}