package com.oraclejava;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;



@Entity
public class Movie { //movie테이블
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_id_seq")
	@SequenceGenerator(name = "movie_id_seq", sequenceName = "movie_seq", allocationSize = 1)//오라클에서는 반드시 allocationSize = 1로 설정해야한다
	private int movieId;
	private String title; //제목
	private int price; //가격
	private String synopsis; //줄거리
	
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	
	
}
