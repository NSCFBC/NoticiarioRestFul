package com.api.CrudMvcRestFul.service;

import java.util.List;

import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.api.CrudMvcRestFul.exception.NotFoundException;
import com.api.CrudMvcRestFul.model.Usuario;
import com.api.CrudMvcRestFul.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
//	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public UsuarioService(UsuarioRepository usuarioRepository) {
		// TODO Auto-generated constructor stub
		this.usuarioRepository = usuarioRepository;
	}
	
	public List<?> listarAll() {
		List<Usuario> usuarios = (List<Usuario>) usuarioRepository.findAll();
		if (usuarios == null) {
			return null;
		}
		return usuarios;
	}
	
	public void delete(Long id) {
		try {
			usuarioRepository.deleteById(id);
//			EmptyResultDataAccessException
		}catch (DataIntegrityViolationException e) {
			// TODO: handle exception
			throw new NotFoundException("nao encontrado");
		}
		catch (EmptyResultDataAccessException e) {
			throw new NotFoundException("nao encontrado");
		}
	}
	
	public Usuario atualizaUser(Usuario usuario) {
		try {
			return usuarioRepository.save(usuario);
			
		} catch (IllegalArgumentException e) {
			throw new NotFoundException("Dados inválidos");
		}
	}
	public Usuario buscarPeloEmail(String email) {
		return usuarioRepository.findUserByLogin(email);
	}
	
	public Usuario buscarPeloId(Long id) {
		return usuarioRepository.findById(id).orElseThrow(() -> new NotFoundException("erro"));
	}
	
	public List<String> buscarPermissao(String email) {
		try {
		return usuarioRepository.buscarPermissao(email);
		}catch (DataIntegrityViolationException e) {
			// TODO: handle exception
			throw new NotFoundException("Problema ao buscar Permissão");
		}
		catch (EmptyResultDataAccessException e) {
			throw new NotFoundException("Problema ao buscar Permissão");
		}
	}

	public Usuario salvar(Usuario usuario) {
		try {
			return usuarioRepository.save(usuario);
//			EmptyResultDataAccessException
		}catch (DataIntegrityViolationException e) {
			// TODO: handle exception
			throw new NotFoundException("Problema ao salvar usuário");
		}
		catch (EmptyResultDataAccessException e) {
			throw new NotFoundException("Problema ao salvar usuário");
		}
	}
	
}
