package com.api.CrudMvcRestFul.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

import com.api.CrudMvcRestFul.exception.NotFoundException;
import com.api.CrudMvcRestFul.model.Noticia;
import com.api.CrudMvcRestFul.repository.NoticiaRepository;

@Service
public class NoticaService {

//	@Autowired
	private NoticiaRepository noticiaRepository;

//	@Autowired
	private CategoriaService categoriaService;

//	@Autowired
	private UsuarioService usuarioService;
//	buscarPeloId
	
	public NoticaService(NoticiaRepository noticiaRepository, 
			CategoriaService categoriaService,
			UsuarioService usuarioService) {
		// TODO Auto-generated constructor stub
		this.noticiaRepository = noticiaRepository;
		this.categoriaService = categoriaService;
		this.usuarioService = usuarioService;
	}

	public List<Noticia> bottomNoticias() {
		List<Noticia> noticias = noticiaRepository.buscarTop(PageRequest.of(0, 2));
		if (noticias != null) {
			return noticias;
		}
		return null;
	}

	public List<Noticia> listarTipoNoticia(String tipo) {
		List<Noticia> noticias = noticiaRepository.listarTipoNoticia(tipo);
		if (noticias != null) {
			return noticias;
		}
		return null;
	}

	public List<Noticia> buscarTopDezNoticia() {
		List<Noticia> noticias = noticiaRepository.buscarTopDezNoticia(PageRequest.of(0, 10));
		if (noticias != null) {
			return noticias;
		}
		return null;
	}

	public List<Noticia> topDezNoticias() {
		List<Noticia> noticias = noticiaRepository.buscarTopDezNoticias(PageRequest.of(0, 10));
		if (noticias != null) {
			return noticias;
		}
		return null;
	}

	public List<Noticia> topNoticias() {
		List<Noticia> noticias = noticiaRepository.buscarTopDestaques(PageRequest.of(0, 3));
		if (noticias != null) {
			return noticias;
		}
		return null;
	}

	public List<Noticia> topNoticiasEconomia() {
		List<Noticia> noticias = noticiaRepository.buscarTopEconomia(PageRequest.of(0, 3));
		if (noticias != null) {
			return noticias;
		}
		return null;
	}

	public Noticia atualizar(Noticia noticia) {
		try {
			Noticia n1 = noticiaRepository.save(noticia);
			return n1;
		} catch (TransactionSystemException e) {
			// TODO: handle exception
			throw new NotFoundException("Erro ao adicionar noticia");
		} catch (DataIntegrityViolationException e) {
			// TODO: handle exception
			throw new NotFoundException("Erro ao adicionar noticia");
		}
	}

	public void deletar(Long id) {
		noticiaRepository.findById(id);
		// se nao existir noticia é gerado uma exception aqui
		// campo ja validado
		existeOuNaoNoticia(id);
		noticiaRepository.deleteById(id);
	}

	public Noticia inserirNoticia(Noticia noticia) {
		// logica para inserir: --
		// verifica o id do usuario e da categoria da mensagem
		// se ambos existirem, é realizado o cadastro
		Long idUser = noticia.getUsuario().getId();
		Long idCat = noticia.getCategoria().getId();

		// campo categoria ja validado no service Categoria
		// se tiver erro ja esta tratado na categoria
		categoriaService.listarId(idCat);
		// campo ja validado no service Usuario
		usuarioService.buscarPeloId(idUser);
		// falta validar categoria

		// pode ter erro nesse campo
		// validando
		try {
			Noticia n1 = noticiaRepository.save(noticia);
			return n1;
		} catch (TransactionSystemException e) {
			// TODO: handle exception
			throw new NotFoundException("Erro ao adicionar noticia");
		} catch (DataIntegrityViolationException e) {
			// TODO: handle exception
			throw new NotFoundException("Erro ao adicionar noticia");
		}

	}

	public List<?> listarTodos() {
		List<Noticia> noticias = (List<Noticia>) noticiaRepository.findAll();
		if (noticias != null) {
			return noticias;
		}
		return null;
	}

	public Noticia buscarId(Long id) {
		return existeOuNaoNoticia(id);
//			return noticia.isPresent() ? noticia : null;
//		System.out.println(noticia.get().getNoticia());
	}

	public Noticia existeOuNaoNoticia(Long id) {
		return noticiaRepository.findById(id).orElseThrow(() -> new NotFoundException("Não foi encontrado informação"));
//		return noticia.isPresent() ? noticia : null;
//	System.out.println(noticia.get().getNoticia());
	}
}
