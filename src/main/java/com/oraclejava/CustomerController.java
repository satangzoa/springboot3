package com.oraclejava;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
@RequestMapping(value = "/admin/customers") //주소자체가 권한이 된다
public class CustomerController {

	
//	   @InitBinder
//	    protected void initBinder(WebDataBinder binder){
//	        DateFormat  dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//	        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,true));
//	    }
	
	private static final int PAGE_SIZE = 10;
	
	@Autowired //레포지토리 자동으로 불러온다
	private CustomerRepository customerRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@RequestMapping(value = {"","/"}, method = RequestMethod.GET)
	public ModelAndView customers(@RequestParam(required = false, value = "page") Integer pageNumber) {
		pageNumber = (pageNumber == null) ?  1 : pageNumber; 
 		
		ModelAndView mav = new ModelAndView();
//		mav.setViewName("customers");
		mav.setViewName("homeLayout");
		mav.addObject("contents", "adminHome :: admin_contents");
		mav.addObject("admin_contents", "customers :: admin_contents");
		
		
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

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(Model model) {
		
		
		return "customerCreate";
	}
	
	
	@RequestMapping(value = "/create",method = RequestMethod.POST)
	public String create(Customer customer, Model model) {
		
		String pass = customer.getCustomerPass();
		
		customer.setCustomerPass(passwordEncoder.encode(pass));
		
		customer.setRole("ROLE_USER"); // 기본 권한 부여
		
		
		customerRepository.save(customer);
		
		return "redirect:/admin/customers";
	}


	@RequestMapping(value = "/update/{customerCode}", method = RequestMethod.GET)
	public String update(@PathVariable Integer customerCode, Model model) {
		Customer customer = customerRepository.findById(customerCode).get();
		model.addAttribute("customer", customer);
		return "customerUpdate";
	}
	
	
	@RequestMapping(params = "update",value = "/update/{customerCode}", method = RequestMethod.POST)
	public String update(Customer customer, Model model) {
		
		Customer customers = customerRepository.findById(customer.getCustomerCode()).get();
		customers.setCustomerName(customer.getCustomerName());

//		String pass = customer.getCustomerPass();
//		customer.setCustomerPass(passwordEncoder.encode(pass));와
//		customers.setCustomerPass(customer.getCustomerPass()); 는 같다. 
		
		String oldPassword= customers.getCustomerPass();
		String newPassword = customer.getCustomerPass();
		
		if (!newPassword.equals(oldPassword)) { // != 안됨 /사용하면 안됨
			customer.setCustomerPass(passwordEncoder.encode(newPassword));
			
		}
		
		
		String pass = customer.getCustomerPass();
		customer.setCustomerPass(passwordEncoder.encode(pass));
//		customers.setCustomerPass(customer.getCustomerPass());
		customers.setCustomerBirth(customer.getCustomerBirth());
		customers.setCustomerJob(customer.getCustomerJob());
		customers.setCustomerMail(customer.getCustomerMail());
		customers.setCustomerTel(customer.getCustomerTel());
		customers.setCustomerPost(customer.getCustomerPost());
		customers.setCustomerAdd(customer.getCustomerAdd());
		
		customerRepository.save(customers);
		
		return "redirect:/admin/customers/";
	}
	
	@RequestMapping(params="delete", value = "/update/{customerCode}", method = RequestMethod.POST)
	public String delete(@PathVariable Integer customerCode, Model model) {
		Customer customers = customerRepository.findById(customerCode).get();
		customerRepository.delete(customers);
		
		
		return "redirect:/admin/customers/";
	}
	
	
}
	
	
	
















