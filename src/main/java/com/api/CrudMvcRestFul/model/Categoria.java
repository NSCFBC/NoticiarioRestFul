package com.api.CrudMvcRestFul.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Categoria {

	@Id
	// anotação opxional quando a estrategia é auto
	@GeneratedValue(strategy = GenerationType.AUTO)
	@EqualsAndHashCode.Include
	private Long id;
	

	// sera o nome da categoria de noticia p/ filtro ou algo do tipo
	@Column(name = "nm_cat_noticia")
	private String nome;

	@JsonIgnore
	@OneToMany(mappedBy = "categoria")
	private List<Noticia> noticias;

}
