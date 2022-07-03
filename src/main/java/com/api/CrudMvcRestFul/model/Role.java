package com.api.CrudMvcRestFul.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Role")
@SequenceGenerator(name = "seq_role", sequenceName = "seq_role", allocationSize = 1, initialValue = 1)
public class Role implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_role")
	private Long id;

	private String nomeRole; /* Papel, exemplo ROLE_SECRETARIO OU ROLE_GERENTE... */

	@Override
	public String getAuthority() { /* Retorna o nome no papel, acesso ou atorização exemplo ROLE_GERENTE */
		return this.nomeRole;
	}

	

}