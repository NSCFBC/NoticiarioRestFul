package com.api.CrudMvcRestFul.exception;

import lombok.Getter;

@Getter
public enum ErroType {
	ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada","Entidade não encontrada"),
	PARAMETROS_INVALIDOS("/parametros-invalidos","Erro ao solicitar dado"),
	METODO_INVALIDO("/metodo-invalido","Erro ao solicitar recurso"),
	BAD_REQUEST("/dados-invalidos","Erro ao solicitar recurso"),
	FORMATO_INVALIDO("/formato-invalido","Erro ao atualizar informação"); 
	
	private String title;
	
	private String url;
	
	
	private ErroType(String path, String title) {
		this.url = "http://localhost:4200/home" + path;
		this.title = title;
	}
}
