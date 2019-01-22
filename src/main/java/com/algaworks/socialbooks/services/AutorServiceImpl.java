package com.algaworks.socialbooks.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.socialbooks.domain.Autor;
import com.algaworks.socialbooks.repository.AutorRepository;

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
	
}