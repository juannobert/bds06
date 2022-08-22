package com.devsuperior.movieflix.dto;

import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;

public class MovieDTO extends MovieByGenreDTO {
	private static final long serialVersionUID = 1L;

	
	private Genre genre;
	
	public MovieDTO() {
	}

	public MovieDTO(Movie entity) {
		super(entity);
		this.genre = entity.getGenre();
		
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	
	
}
