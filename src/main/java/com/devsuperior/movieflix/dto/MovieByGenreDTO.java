package com.devsuperior.movieflix.dto;

import java.io.Serializable;

import com.devsuperior.movieflix.entities.Movie;

public class MovieByGenreDTO implements Serializable {
	private static final long serialVersionUID = 1L;


	private Long id;

	private String title;

	private String subTitle;

	private Integer date;

	private String imgUrl;

	private String synopsis;

	public MovieByGenreDTO() {
	}

	public MovieByGenreDTO(Long id, String title, String subTitle, Integer date, String imgUrl, String synopsis) {
		super();
		this.id = id;
		this.title = title;
		this.subTitle = subTitle;
		this.date = date;
		this.imgUrl = imgUrl;
		this.synopsis = synopsis;
	}
	
	public MovieByGenreDTO(Movie entity) {
			super();
			this.id = entity.getId();
			this.title = entity.getTitle();
			this.subTitle = entity.getSubTitle();
			this.imgUrl = entity.getImgUrl();
			this.synopsis = entity.getSynopsis();
			this.date = entity.getDate();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	

	public Integer getDate() {
		return date;
	}

	public void setDate(Integer date) {
		this.date = date;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	
	
}
	