package com.api.CrudMvcRestFul.exception;

//@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Entidade não encontrada")
public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public NotFoundException(String msg) {
		super(msg);
	}

}
