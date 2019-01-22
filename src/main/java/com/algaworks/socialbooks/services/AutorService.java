package com.algaworks.socialbooks.services;

import java.util.List;

import com.algaworks.socialbooks.domain.Autor;

public interface AutorService {
	
	public List<Autor> listar();
	
	public Autor salvar(Autor autor);
	
	public Autor buscar(Long id);

}