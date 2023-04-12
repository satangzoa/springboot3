package com.oraclejava;

import java.util.*;

import org.springframework.data.domain.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer>{//id타입은 Integer Movie.java의 @Id private int movieId
	//정렬
	List<Movie> findAll(Sort sort);
	//페이징
	Page<Movie> findAll(Pageable pageable);
	
	
}
