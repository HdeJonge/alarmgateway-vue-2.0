package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.demo.constraints.BproSpecification;
import com.example.demo.constraints.SearchCriteria;
import com.example.demo.model.Label;
import com.example.demo.model.User;
import com.example.demo.repo.OnzeDataTableRequest;
import com.example.demo.serv.UserService;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("/user")
	public Page<User> findAllUsers(@Valid OnzeDataTableRequest request, @RequestParam String from,
			@RequestParam String to) throws Exception {
		System.out.println(from + "-" + to);
		Page<User> resultPage;
		if ((from != "") || (to != "")) {
			resultPage = service.findAll(request, from, to);
		} else {
			resultPage = service.findAll(request);
		}
		if (request.getPage() - 1 > resultPage.getTotalPages()) {
			throw new Exception();
		}
		return resultPage;
	}

	@PostMapping("/user")
	public User postAlarmgateway(@Valid @RequestBody User user) {
		User _user = service.save(user);
		return _user;
	}
}