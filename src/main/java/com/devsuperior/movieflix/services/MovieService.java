package com.devsuperior.movieflix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.MovieByGenreDTO;
import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class MovieService {
	
	@Autowired
	private MovieRepository repository;
	
	@Autowired
	private GenreRepository genreRepository;
	
	@Transactional(readOnly = true)
	public MovieDTO findById(Long id) {
		Movie entity = repository.findById(id)
				.orElseThrow(() ->  new ResourceNotFoundException("Movie not found"));
		return new MovieDTO(entity);
	}
	
	@Transactional(readOnly = true)
	public Page<MovieByGenreDTO> findByGenre(Long genreId,Pageable pageable) {
		
		Genre genre = (genreId == 0 ? null :genreRepository.getReferenceById(genreId) );
		Page<Movie> list = repository.findByGenre(genre, pageable);
		return list.map(x -> new MovieByGenreDTO(x));
		
	}
	

}
