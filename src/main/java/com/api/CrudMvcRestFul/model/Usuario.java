package com.api.CrudMvcRestFul.model;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "bd_user")
public class Usuario implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@EqualsAndHashCode.Include
	private Long id;

//	@Column(unique = true)
//	@SuppressWarnings("deprecation")
	@javax.validation.constraints.Email(message = "Email no fomato incorreto")
	@javax.validation.constraints.NotNull(message = "Email com valor nulo")
	@NotBlank(message = "Dado informado está incorreto ou em branco")
	@NotEmpty(message = "Dado informado está incorreto ou vazio")
	@Column(unique = true)
	private String email;

	@javax.validation.constraints.NotNull(message = "Senha com valor nulo")
	@NotBlank(message = "Dado informado está incorreto ou em branco")
	@NotEmpty(message = "Dado informado está incorreto ou vazio")
	@Length(min = 6, max = 20)
	@Column(nullable = false)
	private String senha;

	@javax.validation.constraints.NotNull(message = "Nome com valor nulo")
	@NotBlank(message = "Dado informado está incorreto ou em branco")
	@NotEmpty(message = "Dado informado está incorreto ou vazio")
	@Length(min = 3, max = 30)
	@Column(nullable = false)
	private String nome;

	private String token = "";

	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuarios_role", uniqueConstraints = @UniqueConstraint(columnNames = { "usuario_id",
			"role_id" }, name = "unique_role_user"), joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id", table = "Usuario", unique = false, foreignKey = @ForeignKey(name = "usuario_fk", value = ConstraintMode.CONSTRAINT)),

			inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id", table = "Role", unique = false, updatable = false, foreignKey = @ForeignKey(name = "role_fk", value = ConstraintMode.CONSTRAINT)))
	private List<Role> roles; /* Os papeis ou acessos */

	@JsonIgnore
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
	private List<Noticia> noticias;

	/* São os acessos do usuário ROLE_ADMIN OU ROLE_VISITANTE */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

//	@JsonIgnore
	@Override
	public String getPassword() {
		return this.senha;
	}

//	@JsonIgnore
	@Override
	public String getUsername() {
		return this.email;
	}

//	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return true;
	}

}