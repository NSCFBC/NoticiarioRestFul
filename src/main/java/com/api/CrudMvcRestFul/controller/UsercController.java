package com.api.CrudMvcRestFul.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.CrudMvcRestFul.model.Usuario;
import com.api.CrudMvcRestFul.service.UsuarioService;

@RestController
@RequestMapping(value = "/usuario")
@CrossOrigin(origins = "http://localhost:4200")
public class UsercController {
	
	private UsuarioService usuarioService;
	
	public UsercController(UsuarioService usuarioService) {
		// TODO Auto-generated constructor stub
		this.usuarioService = usuarioService;
	}

//	@Autowired
	
	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<?> listarAll() throws IOException {
		@SuppressWarnings("unchecked")
		List<Usuario> usuarios = (List<Usuario>) usuarioService.listarAll();
		if (usuarios != null) {
//			FileToBase64StringConversionUnitTest fileToBase64StringConversion = new FileToBase64StringConversionUnitTest();
//			fileToBase64StringConversion.fileToBase64StringConversion();
			return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping(value = "/")
	public Usuario insert(@Valid @RequestBody Usuario usuario) {
		return usuarioService.salvar(usuario);
	}

	@PutMapping(value = "/")
	public ResponseEntity<Usuario> atualizaUser(@Valid @RequestBody Usuario usuario) {
		Usuario usuarioAtualizado = usuarioService.atualizaUser(usuario);
		return new ResponseEntity<Usuario>(usuarioAtualizado, HttpStatus.OK);
	}
	@GetMapping(value = "/e")
	public ResponseEntity<List<String>> buscarEmail(@RequestParam(name = "email") String email) {
//		System.out.println("okkkkkk");
//		System.out.println(email);
		List<String> permissoes = usuarioService.buscarPermissao(email);
		return new ResponseEntity<List<String>>(permissoes, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Usuario> buscarPeloId(@PathVariable(value = "id") Long id) {
		Usuario usuario = usuarioService.buscarPeloId(id);
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	@DeleteMapping(value = "/{id}")
//	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteId(@PathVariable(value = "id") Long id) {
		usuarioService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
