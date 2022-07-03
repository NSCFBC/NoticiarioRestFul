package com.api.CrudMvcRestFul.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.CrudMvcRestFul.model.Noticia;
import com.api.CrudMvcRestFul.service.NoticaService;

@RestController
@RequestMapping(value = "/noticias")
@CrossOrigin(origins = "http://localhost:4200")
public class NoticiaController {

//	@Autowired
	private NoticaService noticaService;
	
	public NoticiaController(NoticaService noticaService) {
		this.noticaService = noticaService;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/topDownNoticia")
	public ResponseEntity<?> dezNoticia(){
		List<Noticia> noticias = noticaService.buscarTopDezNoticia();
		if (noticias != null) {
			return new ResponseEntity<List<Noticia>>(noticias, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
//	buscarTopDezNoticia
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/buscarBottom")
	public ResponseEntity<?> bottomNoticias(){
		List<Noticia> noticias = noticaService.bottomNoticias();
		if (noticias != null) {
			return new ResponseEntity<List<Noticia>>(noticias, HttpStatus.OK);			
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/buscarDezUltimas")
	public ResponseEntity<?> topDezNoticias(){
		List<Noticia> noticias = noticaService.topDezNoticias();
		if (noticias != null) {
			return new ResponseEntity<List<Noticia>>(noticias, HttpStatus.OK);			
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/buscaEconomia")
	public ResponseEntity<?> buscarTopEconomia(){
		List<Noticia> noticias = noticaService.topNoticiasEconomia();
		if (noticias != null) {
			return new ResponseEntity<List<Noticia>>(noticias, HttpStatus.OK);			
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/buscaDestaques")
	public ResponseEntity<?> buscarTopDestaques(){
		List<Noticia> noticias = noticaService.topNoticias();
		if (noticias != null) {
			return new ResponseEntity<List<Noticia>>(noticias, HttpStatus.OK);			
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@PutMapping("/atualizar")
	public Noticia atualizar(@Valid @RequestBody Noticia noticia){
		return noticaService.atualizar(noticia);
	}
	
	//oks
	@CrossOrigin(origins = "http://localhost:4200")
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/inserir")
	public Noticia inserirNoticia(@Valid @RequestBody Noticia noticia) {
		return noticaService.inserirNoticia(noticia);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/listar")
	public ResponseEntity<?> listar(){
		@SuppressWarnings("unchecked")
		List<Noticia> noticias = (List<Noticia>) noticaService.listarTodos();
		if (noticias != null) {
			return new ResponseEntity<List<Noticia>>(noticias, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	
	
//oks	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deletarId(@PathVariable(value = "id") Long id){
		
		noticaService.deletar(id);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
//	listarTipoNoticia ok pode ser melhorado
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/listarTipoDeNoticia/{tipo}")
	public ResponseEntity<?> buscarTipoNoticia(@PathVariable(value = "tipo") String tipo){
		List<Noticia> noticias = noticaService.listarTipoNoticia(tipo);
		//existe lista com dados
		if (noticias != null) {
			return new ResponseEntity<List<Noticia>>(noticias,HttpStatus.OK);
		}
		return  new ResponseEntity<Void>(HttpStatus.NO_CONTENT); 
	}

	
	//validado
	@GetMapping("/findId/{id}")
	public ResponseEntity<?> buscarId(@PathVariable(value = "id") Long id) {
			Noticia noticia = noticaService.buscarId(id);
			return new ResponseEntity<Noticia>(noticia, HttpStatus.ACCEPTED);	
	}

}
