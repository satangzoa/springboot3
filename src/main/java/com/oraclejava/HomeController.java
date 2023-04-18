package com.oraclejava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("homeLayout");
		mav.addObject("contents", "home :: home_contents");
//		contents 파일명 home :: home_contents프래그넌트명
		return mav;
	}

}
