package com.api.CrudMvcRestFul.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Erro {		
	
	private int status;
	private String type;
	private String title;
	private String detail;
}
