package com.algaworks.socialbooks.services;

import java.util.List;
import java.util.Optional;

import com.algaworks.socialbooks.domain.Livro;

public interface LivroService {
	
	public List<Livro> listarTodos();
	
	public Optional<Livro> Buscar(final Long id);
	
	public Livro salvar(Livro livro);
	
	public void atualizar(Livro livro, final Long id);
	
	public void deletar(final Long id);
	
}