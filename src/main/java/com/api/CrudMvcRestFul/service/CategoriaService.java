package com.api.CrudMvcRestFul.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

import com.api.CrudMvcRestFul.exception.NotFoundException;
import com.api.CrudMvcRestFul.model.Categoria;
import com.api.CrudMvcRestFul.repository.CategoriaRepository;

@Service
public class CategoriaService {

//	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public CategoriaService(CategoriaRepository categoriaRepository) {
		// TODO Auto-generated constructor stub
		this.categoriaRepository = categoriaRepository;
	}

	public Categoria inserir(Categoria categoria) throws IllegalArgumentException {
		try {
			return categoriaRepository.save(categoria);
		} catch (TransactionSystemException e) {
			// TODO: handle exception
			throw new NotFoundException("Erro ao adicionar noticia");
		} catch (DataIntegrityViolationException e) {
			// TODO: handle exception
			throw new NotFoundException("Erro ao adicionar noticia");
		}
	}

	public Object listar() throws IllegalArgumentException {
		List<Categoria> categorias = (List<Categoria>) categoriaRepository.findAll();
		if (categorias != null) {
			return categorias;
		}
		return null;
	}

	public Categoria listarId(Long id) throws IllegalArgumentException {
		return categoriaRepository.findById(id).orElseThrow(() -> new NotFoundException("Categoria nao encontrada"));
	}

	public void deletar(Long id) throws IllegalArgumentException {
		listarId(id);
		try {
			categoriaRepository.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			// TODO: handle exception
			throw new NotFoundException("Erro");
		}
	}

	public Categoria atualizar(Categoria categoria) throws IllegalArgumentException {
		listarId(categoria.getId());
		try {
			return categoriaRepository.save(categoria);
		} catch (TransactionSystemException e) {
			// TODO: handle exception
			throw new NotFoundException("Erro ao adicionar noticia");
		} catch (DataIntegrityViolationException e) {
			// TODO: handle exception
			throw new NotFoundException("Erro ao adicionar noticia");
		}
	}

}
