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
public class CustomerController {

	private static final int PAGE_SIZE = 5;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	
		
	@RequestMapping(value = "/customers", method = RequestMethod.GET)
	public ModelAndView customers(@RequestParam(required = false, value = "page") Integer pageNumber) {
		pageNumber = (pageNumber == null) ?  1 : pageNumber; 
 		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("customers");
		
		//Page<Customer> customers = CustomerRepository.findAll(PageRequest.of(pageNumber -1 , PAGE_SIZE, Sort.by("customer_code")));
		Page<Customer> customers = customerRepository.findAll(PageRequest.of(pageNumber -1 , PAGE_SIZE, Sort.by("customerCode")));
		
		int current = customers.getNumber() + 1; //현재 페이지 번호
		int begin = 1;
		int end = customers.getTotalPages();
		
		mav.addObject("customerList", customers);
		mav.addObject("beginIndex", begin);
		mav.addObject("endIndex", end);
		mav.addObject("currentIndex", current);
		
		return mav;
	}
	
}
















