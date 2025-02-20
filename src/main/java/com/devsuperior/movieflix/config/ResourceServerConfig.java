package com.devsuperior.movieflix.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	// Endpoints publicos
	private static final String[] PUBLIC = { "/oauth/token", "/h2-console/**" };
	
	private static final String[] VISITOR = { "/genres/**","/movies/**","/reviews/**"};
	
	
	
	@Autowired
	private Environment env;
	
	@Autowired
	private JwtTokenStore tokenStore;

	// Verifica se o token é válido
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		if(Arrays.asList(env.getActiveProfiles()).contains("test")) { // Se for perfil de teste
			http.headers().frameOptions().disable(); // Liberar H2 console
		}
		
		http.authorizeRequests()
		.antMatchers(PUBLIC).permitAll()// Acessar aos endpoints públicos não exigiram login
		.antMatchers(HttpMethod.GET,VISITOR).hasAnyRole("MEMBER","VISITOR")
		
		.anyRequest().hasAnyRole("MEMBER"); //Para acessar qualquer outra rota o usuário precisa estar logado

	}

}
