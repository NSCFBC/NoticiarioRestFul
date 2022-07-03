package com.api.CrudMvcRestFul.exception;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.api.CrudMvcRestFul.model.Erro;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler{
		
	@org.springframework.web.bind.annotation.ExceptionHandler({NotFoundException.class,Exception.class, RuntimeException.class, Throwable.class})
	public ResponseEntity<?> entidadeNaoEncontrada(NotFoundException e){
		
		ErroType erroType = ErroType.BAD_REQUEST;
		String detalhe = "Não concluímos a requisição, dados informados estão em conflito com o sistema !";
		
		Erro erro = createErroBuilder(HttpStatus.BAD_REQUEST, erroType, detalhe).build();
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErroType erroType = ErroType.METODO_INVALIDO;
		String detalhe = "Operacao invalida, tente novamente";
		//metodo errado put get post
		Erro erro = createErroBuilder(HttpStatus.BAD_REQUEST, erroType, detalhe).build();
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ErroType erroType = ErroType.PARAMETROS_INVALIDOS;
		String detalhe = "Parametros para busca invalidos";
		//parametros a mais invalidos /1234232
		Erro erro = createErroBuilder(HttpStatus.BAD_REQUEST, erroType, detalhe).build();
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		ErroType erroType = ErroType.FORMATO_INVALIDO;
		String detalhe = "Dados no formato inválido";
		
		Erro erro = createErroBuilder(status, erroType, detalhe).build();
		// TODO Auto-generated method stub
		return handleExceptionInternal(ex, erro, new HttpHeaders(), status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		ErroType erroType = ErroType.ENTIDADE_NAO_ENCONTRADA;
		String detalhe = "Não foi encontrado nenhum dados";
		
		Erro erro = createErroBuilder(status, erroType, detalhe).build();
		// TODO Auto-generated method stub
		return handleExceptionInternal(ex, erro, new HttpHeaders(), status, request);
	}
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErroType erroType = ErroType.ENTIDADE_NAO_ENCONTRADA;
		String detalhe = "Não foi encontrado nenhum dados";
		
		Erro erro = createErroBuilder(status, erroType, detalhe).build();
		// TODO Auto-generated method stub
		return handleExceptionInternal(ex, erro, new HttpHeaders(), status, request);
	}
	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		ErroType erroType = ErroType.ENTIDADE_NAO_ENCONTRADA;
		String detalhe = "Não foi encontrado nenhum dados";
		
		Erro erro = createErroBuilder(status, erroType, detalhe).build();
		// TODO Auto-generated method stub
		return handleExceptionInternal(ex, erro, new HttpHeaders(), status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		ErroType erroType = ErroType.ENTIDADE_NAO_ENCONTRADA;
		String detalhe = "Não foi encontrado nenhum dados";
		
		Erro erro = createErroBuilder(status, erroType, detalhe).build();
		// TODO Auto-generated method stub
		return handleExceptionInternal(ex, erro, new HttpHeaders(), status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		ErroType erroType = ErroType.ENTIDADE_NAO_ENCONTRADA;
		String detalhe = "Não foi encontrado nenhum dados";
		
		Erro erro = createErroBuilder(status, erroType, detalhe).build();
		// TODO Auto-generated method stub
		return handleExceptionInternal(ex, erro, new HttpHeaders(), status, request);
	}
	
	
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErroType erroType = ErroType.FORMATO_INVALIDO;
		String detalhe = "Dados no formato inválido";
		
		Erro erro = createErroBuilder(status, erroType, detalhe).build();
		// TODO Auto-generated method stub
		return handleExceptionInternal(ex, erro, new HttpHeaders(), status, request);
	}
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		ErroType erroType = ErroType.ENTIDADE_NAO_ENCONTRADA;
		String detalhe = "Não foi encontrado nenhum dadoss";
		
		Erro erro = createErroBuilder(status, erroType, detalhe).build();
		// TODO Auto-generated method stub
		return handleExceptionInternal(ex, erro, new HttpHeaders(), status, request);
	}
	
	
	
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		if (body == null) {
			body = Erro.builder()
					.title(status.getReasonPhrase())
					.status(status.value())
					.build();
		}else if(body instanceof String) {
			body = Erro.builder()
					.title((String)body)
					.build();
		}
		
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	private Erro.ErroBuilder createErroBuilder(HttpStatus status,
			ErroType erroType,String details){
		return Erro.builder()
				.status(status.value())
				.type(erroType.getUrl())
				.title(erroType.getTitle())
				.detail(details);
	}

}
