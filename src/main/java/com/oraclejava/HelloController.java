package com.oraclejava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.*;


@Controller
@RequestMapping(value = "/cgv")//http://localhost:8090/cgv/ 해줘야 화면이 뜬다
public class HelloController {
	//http://localhost:8084/?page=1 페이지화면 띄우기
	private static final int PAGE_SIZE= 10;//private static final상수와 같다 값이 정해지면 변경불가 페이징처리하기
	
	@Autowired
	private MovieRepository movieRepository;
	
	@RequestMapping(value = {"/", ""}, method = RequestMethod.GET)// {"/", ""} /cgv로 가게 만들었다 /cgv/로 uri에 적어도 실행된다
	public ModelAndView index(@RequestParam(required = false, value = "page") Integer pageNumber) {//페이징 예외처리 required = false 이게 없으면 required = true가 된다
		pageNumber = (pageNumber == null) ?  1 : pageNumber; //삼항연상자
 		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");//index.html을 가리킨다
		mav.addObject("msg","안녕하시지요?😊😊");
		//mav.addObject("movieList",movieRepository.findAll(Sort.by("title")));//제목을 가나다순으로 정렬한다.영어가 먼저 위에 있다.descending은 내림차순
		//가격이 높은 순으로 정렬
		//mav.addObject("movieList",movieRepository.findAll(Sort.by("price").descending().and(Sort.by("title")))); //가격은 높은순 제목은 가나다순
		//mav.addObject("movieList",movieRepository.findAll(Sort.by("movieId")));
		Page<Movie> movies = movieRepository.findAll(PageRequest.of(pageNumber -1 , PAGE_SIZE, Sort.by("movieId")));
		
		
		int current = movies.getNumber() +1; ////현재 페이지 번호 /기본이 0이 된다 그래서 1부터 시작해야하니까 +1을한다.
		int begin = 1;
		int end = movies.getTotalPages();
		
		mav.addObject("movieList", movies);
		mav.addObject("beginIndex", begin);
		mav.addObject("endIndex", end);
		mav.addObject("currentIndex", current);
		
		return mav;
	}
	
	//영화 추가 화면
	@RequestMapping(value = "/create", method = RequestMethod.GET)//링크로 들어가니까 get이 온다
	public String create(Model model) {
		
		
		return "movieCreate";
	}
	
	//영화 추가 화면
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(Movie movie, Model model) { //
		
		movieRepository.save(movie);
		
		
		return "redirect:/cgv/";
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)//{id}패스베리어블이다 id는 아무단어나 대체될 수 있다.
	public String update(@PathVariable Integer id, Model model) {
		Movie movie = movieRepository.findById(id).get();
		model.addAttribute("movie", movie);
		return "movieUpdate";
	}
	
	//수정
	@RequestMapping(params ="update", value = "/update/{movieId}", method = RequestMethod.POST)//Movie.java의 movieId를 가리킨다
	public String update(Movie movie, Model model) {
		// smovie: 찾은 영화(searched movie)
		Movie smovie = movieRepository.findById(movie.getMovieId()).get();
		smovie.setTitle(movie.getTitle());
		smovie.setPrice(movie.getPrice());
		smovie.setSynopsis(movie.getSynopsis());
		movieRepository.save(smovie);
		//jpa에서는 movieId가 업데이트 안된다.
		return "redirect:/cgv/";
	}
	
	//삭제
	@RequestMapping(params ="delete", value = "/update/{movieId}", method = RequestMethod.POST)
	public String delete(@PathVariable Integer movieId, Model model) {
		Movie smovie = movieRepository.findById(movieId).get();
		movieRepository.delete(smovie);
		
		return "redirect:/cgv/";
	}
	
	
	
	
}















