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

import com.api.CrudMvcRestFul.model.Categoria;
import com.api.CrudMvcRestFul.service.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoriaController {

//	@Autowired
	private CategoriaService categoriaService;
	
	public CategoriaController(CategoriaService categoriaService) {
		// TODO Auto-generated constructor stub
		this.categoriaService = categoriaService;
	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/")
	public Categoria salva(@Valid @RequestBody Categoria categoria) {
		return categoriaService.inserir(categoria);
	}

	@ResponseStatus(value = HttpStatus.OK)
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Long id) {
		categoriaService.deletar(id);
	}

	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping("/{id}")
	public Categoria findId(@PathVariable Long id) {
		return categoriaService.listarId(id);
	}

	@ResponseStatus(code = HttpStatus.OK)
	@PutMapping("/")
	public Categoria atualizar(@Valid @RequestBody Categoria categoria) {
		return categoriaService.atualizar(categoria);
	}

	@GetMapping("/")
	public ResponseEntity<?> buscarTodos() {
		@SuppressWarnings("unchecked")
		List<Categoria> categorias = (List<Categoria>) categoriaService.listar();
		if (!categorias.isEmpty()) {
			return new ResponseEntity<List<Categoria>>(categorias, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
