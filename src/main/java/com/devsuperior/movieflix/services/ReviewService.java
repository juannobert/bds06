package com.devsuperior.movieflix.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewRepository repository;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private AuthService authService;
	
	@Transactional
	public ReviewDTO save(ReviewDTO dto) {
		Review entity = new Review();
		BeanUtils.copyProperties(dto, entity,"id","user");
		entity.setUser(authService.authenticated());
		entity.setMovie(movieRepository.getReferenceById(dto.getMovieId()));
		entity = repository.save(entity);
		return new ReviewDTO(entity);
	}

	@Transactional(readOnly = true)
	public List<ReviewDTO> findByMovie(Long id) {
		List<Review> list = repository.find(id);
		return list.stream()
				.map(x -> new ReviewDTO(x))
				.collect(Collectors.toList());
		
	}
}
