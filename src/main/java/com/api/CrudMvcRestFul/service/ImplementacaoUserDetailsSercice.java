package com.api.CrudMvcRestFul.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.CrudMvcRestFul.model.Usuario;
import com.api.CrudMvcRestFul.repository.UsuarioRepository;


@Service
public class ImplementacaoUserDetailsSercice implements UserDetailsService {
	
//	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public ImplementacaoUserDetailsSercice(UsuarioRepository usuarioRepository) {
		// TODO Auto-generated constructor stub
		this.usuarioRepository = usuarioRepository;
	}
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		/*Consulta no banco o usuario*/
		
		Usuario usuario = usuarioRepository.findUserByLogin(username);
		
		if (usuario == null) {
			throw new UsernameNotFoundException("Usuário não foi encontrado");
		}
		
		return new User(usuario.getEmail(),
				usuario.getPassword(),
				usuario.getAuthorities());
	}


}
