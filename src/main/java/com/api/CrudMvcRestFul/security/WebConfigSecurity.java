package com.api.CrudMvcRestFul.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.api.CrudMvcRestFul.service.ImplementacaoUserDetailsSercice;





/*Mapeaia URL, enderecos, autoriza ou bloqueia acessoa a URL*/
//classe de configuração securirity
//nessa classe que é definido quais protocolos 
//de autenticação, autorização, proteção e armazanamento



@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private ImplementacaoUserDetailsSercice implementacaoUserDetailsSercice;
	
	
	/*Configura as solicitações de acesso por Http*/
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		/*Ativando a proteção contra usuário que não estão validados por TOKEN*/
		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
		
		/*Ativando a permissão para acesso a página incial do sistema EX: sistema.com.br/index*/
		.disable().authorizeRequests()
//		.antMatchers("/noticias/listar").hasRole("USER")
		.antMatchers("/**").permitAll()
		.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
		.antMatchers(HttpMethod.GET, "/**").permitAll()
		.antMatchers(HttpMethod.POST, "/**").permitAll()
		.antMatchers(HttpMethod.PUT, "/**").permitAll()
//2
		
		/*URL de Logout - Redireciona após o user deslogar do sistema*/
		.anyRequest().authenticated().and().cors().and().logout().logoutSuccessUrl("/index")
		
		/*Maperia URL de Logout e insvalida o usuário*/
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		
		/*Filtra requisições de login para autenticação*/
		.and().addFilterBefore(new JWTLoginFilter("/login", authenticationManager()), 
									UsernamePasswordAuthenticationFilter.class)
		
		/*Filtra demais requisições paa verificar a presenção do TOKEN JWT no HEADER HTTP*/
		.addFilterBefore(new JwtApiAutenticacaoFilter(), UsernamePasswordAuthenticationFilter.class);
	
//		2
//		.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//		.antMatchers(HttpMethod.GET, "/**").permitAll()
//		.antMatchers(HttpMethod.POST, "/**").permitAll()
//		.antMatchers(HttpMethod.PUT, "/**").permitAll()
		
//		http.csrf().disable().
//		authorizeRequests()
//		.antMatchers("/noticias/listar").hasRole("USER")
//		.antMatchers("/login", "/error**", "/public/**", "/resources/**", "/webjars/**").permitAll().and().
//		formLogin().loginPage("/login").failureUrl("/login").defaultSuccessUrl("/home").and().logout().permitAll();
//		
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
	

	/*Service que irá consultar o usuário no banco de dados*/	
	auth.userDetailsService(implementacaoUserDetailsSercice)
//	new BCryptPasswordEncoder()
	/*Padrão de codigição de senha*/
	.passwordEncoder(NoOpPasswordEncoder.getInstance());
	
	}

}
