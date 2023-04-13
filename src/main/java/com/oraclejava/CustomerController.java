package com.oraclejava;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
public class CustomerController {

	
//	   @InitBinder
//	    protected void initBinder(WebDataBinder binder){
//	        DateFormat  dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//	        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,true));
//	    }
	
	private static final int PAGE_SIZE = 10;
	
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

	@RequestMapping(value = "/customers/create", method = RequestMethod.GET)
	public String create(Model model) {
		
		
		return "customerCreate";
	}
	
	
	@RequestMapping(value = "/customers/create",method = RequestMethod.POST)
	public String create(Customer customer, Model model) {
		
		customerRepository.save(customer);
		
		return "redirect:/customers";
	}
	
}
















