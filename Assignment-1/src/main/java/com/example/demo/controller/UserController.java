package com.example.demo.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.model.UserPage;
import com.example.demo.model.UserSearchCriteria;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	private UserService service;

	public UserController(UserService service) {
		super();
		this.service = service;
	}
	@GetMapping
	public ResponseEntity<Page<User>>getUser(UserPage userpage,UserSearchCriteria searchcriteria){
	return new ResponseEntity<>(service.getUser(userpage, searchcriteria),HttpStatus.OK);
	}
	@PostMapping
	public ResponseEntity<User>adduser(User user){
		return new ResponseEntity<>(service.adduser(user),HttpStatus.OK);
	}

}
