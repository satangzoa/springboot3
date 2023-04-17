package com.oraclejava;

import java.util.Date;
import javax.persistence.SequenceGenerator;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

@Entity
public class Customer { //Customer 테이블
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_Code_seq")
	@SequenceGenerator(name = "customer_Code_seq", sequenceName = "customer_seq", allocationSize = 1)
	private int customerCode; //customer_Code는 인식이 안되므로 customerCode로 바꿔야한다
	private String customerName; //이름
	private String customerPass; //비밀번호
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date customerBirth; //생년월일
	private String customerJob; //직업
	
	@Column(unique = true)
	private String customerMail; //이메일
	private String customerTel; //전화번호
	private String customerPost; //우편번호
	private String customerAdd; //주소
	
	//권한
	private String role;
	
	
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getCustomerPass() {
		return customerPass;
	}
	public void setCustomerPass(String customerPass) {
		this.customerPass = customerPass;
	}
	public int getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(int customerCode) {
		this.customerCode = customerCode;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Date getCustomerBirth() {
		return customerBirth;
	}
	public void setCustomerBirth(Date customerBirth) {
		this.customerBirth = customerBirth;
	}
	public String getCustomerJob() {
		return customerJob;
	}
	public void setCustomerJob(String customerJob) {
		this.customerJob = customerJob;
	}
	public String getCustomerMail() {
		return customerMail;
	}
	public void setCustomerMail(String customerMail) {
		this.customerMail = customerMail;
	}
	public String getCustomerTel() {
		return customerTel;
	}
	public void setCustomerTel(String customerTel) {
		this.customerTel = customerTel;
	}
	public String getCustomerPost() {
		return customerPost;
	}
	public void setCustomerPost(String customerPost) {
		this.customerPost = customerPost;
	}
	public String getCustomerAdd() {
		return customerAdd;
	}
	public void setCustomerAdd(String customerAdd) {
		this.customerAdd = customerAdd;
	}
	
	
	
	
}
