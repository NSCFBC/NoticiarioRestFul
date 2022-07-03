package com.api.CrudMvcRestFul.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.api.CrudMvcRestFul.ApplicationContextLoad;
import com.api.CrudMvcRestFul.model.Role;
import com.api.CrudMvcRestFul.model.Usuario;
import com.api.CrudMvcRestFul.repository.UsuarioRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
@Component
public class JWTTokenAutenticacaoService {
	
	
	/*Tem de validade do Token 2 dias*/
	private static final long EXPIRATION_TIME = 172800000;
	
	/*Uma senha unica para compor a autenticacao e ajudar na segurança*/
	private static final String SECRET = "SenhaExtremamenteSecreta";
	
	/*Prefixo padrão de Token*/
	private static final String TOKEN_PREFIX = "Bearer";
	
	private static final String HEADER_STRING = "Authorization";
	
	/*Gerando token de autenticado e adiconando ao cabeçalho e resposta Http*/
	public void addAuthentication(HttpServletResponse response , String username,Collection<? extends GrantedAuthority> collection) throws IOException {
	String result = collection.stream()
		 .map(GrantedAuthority::getAuthority)
		 .collect(Collectors.joining(","));
//	System.out.println("okk");
//	System.out.println(result);
		/*Montagem do Token*/
		String JWT = Jwts.builder() /*Chama o gerador de Token*/
				        .setSubject(username) /*Adicona o usuario*/
				        .setAudience(result)
				        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) /*Tempo de expiração*/
				        .signWith(SignatureAlgorithm.HS512, SECRET).compact(); /*Compactação e algoritmos de geração de senha*/
		
		/*Junta token com o prefixo*/
		String token = TOKEN_PREFIX + " " + JWT; /*Bearer 87878we8we787w8e78w78e78w7e87w*/
		
		/*Adiciona no cabeçalho http*/
		response.addHeader(HEADER_STRING, token); /*Authorization: Bearer 87878we8we787w8e78w78e78w7e87w*/
		
		
		ApplicationContextLoad.getApplicationContext()
        .getBean(UsuarioRepository.class).atualizaTokenUser(JWT, username);
		
		/*Liberando resposta para portas diferentes que usam a API ou caso clientes web*/
		 liberacaoCors(response);
		 
		
		/*Escreve token como responsta no corpo http*/
		response.getWriter().write("{\"Authorization\": \""+token+"\"}");
		
	}
	
	
	/*Retorna o usuário validado com token ou caso não sejá valido retorna null*/
	public Authentication getAuhentication(HttpServletRequest request, HttpServletResponse response) {
		 liberacaoCors(response);
		/*Pega o token enviado no cabeçalho http*/
		
		String token = request.getHeader(HEADER_STRING);
		
		try {
		if (token != null) {
			
			String tokenLimpo = token.replace(TOKEN_PREFIX, "").trim();
			
			/*Faz a validação do token do usuário na requisição*/
			String user = Jwts.parser().setSigningKey(SECRET) /*Bearer 87878we8we787w8e78w78e78w7e87w*/
								.parseClaimsJws(tokenLimpo) /*87878we8we787w8e78w78e78w7e87w*/
								.getBody().getSubject(); /*João Silva*/
			if (user != null) {
				
				Usuario usuario = ApplicationContextLoad.getApplicationContext()
						        .getBean(UsuarioRepository.class).findUserByLogin(user);
				
				if (usuario != null) {
					
					if (tokenLimpo.equalsIgnoreCase(usuario.getToken())) {
					
						return new UsernamePasswordAuthenticationToken(
								usuario.getEmail(), 
								usuario.getSenha(),
								usuario.getAuthorities());
				  }
				}
			}
			
		}//fim da condicao
		}catch (io.jsonwebtoken.ExpiredJwtException e) {
			// TODO: handle exception
			try {
				response.getOutputStream().println("seu token espirado");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
			
			}
		}
	
		liberacaoCors(response);
		return null; /*Não autorizado*/
		
	}


	private void liberacaoCors(HttpServletResponse response) {

		if (response.getHeader("Access-Control-Allow-Origin") == null) {
			response.addHeader("Access-Control-Allow-Origin", "*");
		}
		
		if (response.getHeader("Access-Control-Allow-Headers") == null) {
			response.addHeader("Access-Control-Allow-Headers", "*");
		}
		
		
		if (response.getHeader("Access-Control-Request-Headers") == null) {
			response.addHeader("Access-Control-Request-Headers", "*");
		}
		
		if(response.getHeader("Access-Control-Allow-Methods") == null) {
			response.addHeader("Access-Control-Allow-Methods", "*");
		}
	}
	

}