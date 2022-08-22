package com.devsuperior.movieflix.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.movieflix.dto.MovieByGenreDTO;
import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.services.MovieService;
import com.devsuperior.movieflix.services.ReviewService;


@RestController
@RequestMapping("/movies")
public class MovieResource {
	
	@Autowired
	private MovieService service;
	@Autowired
	private ReviewService reviewService;
	
	@GetMapping("/{id}")
	public ResponseEntity<MovieDTO> findbyId(@PathVariable Long id){
		MovieDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping("/{id}/reviews")
	public ResponseEntity<List<ReviewDTO>> findReviews(@PathVariable Long id){
		List<ReviewDTO> list = reviewService.findByMovie(id);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping
	public ResponseEntity<Page<MovieByGenreDTO>> findReviews(@RequestParam(defaultValue = "0",name = "genreId") Long genreId,Pageable pageable){
		PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("title"));
		Page<MovieByGenreDTO> page = service.findByGenre(genreId,pageRequest);
		return ResponseEntity.ok(page);
	}

}
