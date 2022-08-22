package com.devsuperior.movieflix.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.UserDTO;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.UserRepository;
import com.devsuperior.movieflix.services.exceptions.UnauthorizedException;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	private UserRepository repository;

	@Autowired
	private static Logger logger = LoggerFactory.getLogger(UserService.class);
	@Transactional(readOnly = true)
	public UserDTO profileActive() {
		try {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			User user = repository.findByEmail(username);
			return new UserDTO(user);
		} catch (Exception e) {
			throw new UnauthorizedException("Invalid user");
		}

	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByEmail(username);
		if(user == null) {
			logger.error("Email not found: " + username );
			throw new UsernameNotFoundException("Email not found");
		}
		logger.info("Email found: " + username);
		return user;
	}
}
