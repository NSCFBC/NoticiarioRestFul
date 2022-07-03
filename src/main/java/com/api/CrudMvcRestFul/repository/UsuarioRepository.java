package com.api.CrudMvcRestFul.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.api.CrudMvcRestFul.model.Usuario;

@Repository
@Transactional
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
	
	@Query("select u from Usuario u where u.email = ?1")
	Usuario findUserByLogin(String login);
	
//	@Query("select u from Usuario u where u.email = ?1")
//	Usuario findPermissao(String login);

	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "update bd_user set token = ?1 where email = ?2")
	void atualizaTokenUser(String token, String user);
	
	@Query(nativeQuery = true, value = "SELECT r.nome_role from role r join usuarios_role ur on ur.role_id = r.id join bd_user u on u.id = ur.usuario_id where u.email = ?1")
	List<String> buscarPermissao(String email);
}
