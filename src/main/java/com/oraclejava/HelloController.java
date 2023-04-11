package com.oraclejava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.*;

@Controller
public class HelloController {
	
	@Autowired
	private MovieRepository movieRepository;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		mav.addObject("msg","안녕하시지요?😊😊");
		mav.addObject("movieList",movieRepository.findAll());
		return mav;
	}
	
}
