package com.oraclejava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.*;

@Controller
public class HelloController {
	//http://localhost:8084/?page=1 페이지화면 띄우기
	private static final int PAGE_SIZE= 5;//private static final상수와 같다 값이 정해지면 변경불가 페이징처리하기
	
	@Autowired
	private MovieRepository movieRepository;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(@RequestParam(required = false, value = "page") Integer pageNumber) {//페이징 예외처리 required = false 이게 없으면 required = true가 된다
		pageNumber = (pageNumber == null) ?  1 : pageNumber; //삼항연상자
 		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		mav.addObject("msg","안녕하시지요?😊😊");
		//mav.addObject("movieList",movieRepository.findAll(Sort.by("title")));//제목을 가나다순으로 정렬한다.영어가 먼저 위에 있다.descending은 내림차순
		//가격이 높은 순으로 정렬
		//mav.addObject("movieList",movieRepository.findAll(Sort.by("price").descending().and(Sort.by("title")))); //가격은 높은순 제목은 가나다순
		//mav.addObject("movieList",movieRepository.findAll(Sort.by("movieId")));
		Page<Movie> movies = movieRepository.findAll(PageRequest.of(pageNumber -1 , PAGE_SIZE, Sort.by("movieId")));
		
		int current = movies.getNumber() +1; //기본이 0이 된다 그래서 1부터 시작해야하니까 +1을한다.
		int begin = 1;
		int end = movies.getTotalPages();
		
		mav.addObject("movieList", movies);
		mav.addObject("beginIndex", begin);
		mav.addObject("endIndex", end);
		mav.addObject("currentIndex", current);
		
		return mav;
	}
	
}















