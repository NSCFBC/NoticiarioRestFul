package com.api.CrudMvcRestFul.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "bd_noticia")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Noticia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) //auto ja é o padrao..
	@EqualsAndHashCode.Include
	private Long id;
	
	@NotBlank
	@Column(nullable = false)
	@Length(min = 8, max = 60)
	private String titulo;
	
	@NotBlank
	@Column(nullable = false)
	@Length(min = 10, max = 50)
	private String subtitulo;
	
	
	@Column(columnDefinition = "text", nullable = false)
	@Length(min = 1, message = "Noticia tem que ter no minimo 150 caracteres")
	private String conteudo;
	
	@Temporal(TemporalType.DATE)
	private Date dataNoticia;
//	private LocalDate data; 
	
	//futuramente converter imagem para base 64
//	@NotNull
	private String caminhoImagem;
	
	@ManyToOne
	private Usuario usuario;
	
	@ManyToOne
	private Categoria categoria;
}
