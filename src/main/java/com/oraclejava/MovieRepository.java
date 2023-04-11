package com.oraclejava;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer>{//id타입은 Integer Movie.java의 @Id private int movieId
	

}
