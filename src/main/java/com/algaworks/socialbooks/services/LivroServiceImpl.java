package com.algaworks.socialbooks.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.socialbooks.domain.Livro;
import com.algaworks.socialbooks.repository.LivrosRepository;
import com.algaworks.socialbooks.services.exception.LivroServiceException;

@Service		
public class LivroServiceImpl implements LivroService {
	
	private static final Logger loger = LoggerFactory.getLogger(LivroServiceImpl.class);
	
	@Autowired
	private LivrosRepository livrosRepository;

	@Override
	public List<Livro> listarTodos() {
		List<Livro> livro = livrosRepository.findAll();
		return livro;
	}

	@Override
	public Optional<Livro> Buscar(Long id) {
		Optional<Livro> livro = livrosRepository.findById(id);
		
		if(!livro.isPresent())
			throw new LivroServiceException("livro não encontrado!");
		
		return livro;
	}

	@Override
	public Livro salvar(Livro livro) {
		livro.setId(null);
		Livro livroSalvo = livrosRepository.save(livro);
		return livroSalvo;
	}

	@Override
	public void atualizar(Livro livro, Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletar(Long id) {
		try {
			livrosRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new LivroServiceException("O livro não pode ser encontrado!");
		}
	}
	
	
	

}
