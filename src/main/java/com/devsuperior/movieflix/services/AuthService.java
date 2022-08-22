package com.devsuperior.movieflix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.UserRepository;
import com.devsuperior.movieflix.services.exceptions.UnauthorizedException;

@Service
public class AuthService {

	@Autowired
	private UserRepository repository;
	
	@Transactional(readOnly = true)
	public User authenticated() {
		try {
		//Busca username do usuário logado
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = repository.findByEmail(username);
		return user;
		//Caso usuário não exista 
		}catch(Exception e) {
			throw new UnauthorizedException("Invalid user");
		}
	}
}
