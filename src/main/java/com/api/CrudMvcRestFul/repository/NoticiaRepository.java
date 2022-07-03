package com.api.CrudMvcRestFul.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.api.CrudMvcRestFul.model.Noticia;

@Repository
@Transactional
public interface NoticiaRepository extends CrudRepository<Noticia, Long>{

	
	@Query(value = "select n from Noticia n join Categoria c on n.categoria.id = c.id where n.categoria.nome like '%Leitura%'")
	List<Noticia> buscarTopDestaques(PageRequest pageRequest);
	
	@Query(value = "select n from Noticia n join Categoria c on n.categoria.id = c.id where n.categoria.nome like '%Musica%'")
	List<Noticia> buscarTopEconomia(PageRequest pageRequest);
	
	@Query(value = "select n from Noticia n join Categoria c on n.categoria.id = c.id")
	List<Noticia> buscarTopDezNoticias(PageRequest pageRequest);
	
	@Query(value = "select n from Noticia n order by dataNoticia desc")
	List<Noticia> buscarTopDezNoticia(PageRequest pageRequest);
	
	@Query(value = "select n from Noticia n join Categoria c on n.categoria.id = c.id where n.categoria.nome like '%Musica%'")
	List<Noticia> buscarTop(PageRequest pageRequest);
	
	@Query(value = "select n from Noticia n join Categoria c on c.id = n.categoria.id where n.categoria.nome = ?1 ")
	List<Noticia> listarTipoNoticia(String Detalhe);
}
