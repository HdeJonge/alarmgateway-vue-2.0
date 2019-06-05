package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Alarmgateway;
import com.example.demo.model.User;
import com.example.demo.repo.OnzeDataTableRequest;
import com.example.demo.repo.OnzeDataTablesRepository;
import com.example.demo.repo.UserRepository;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserRepository repository;
	
	@GetMapping("/user")
		public Page<User> findAllUsers(@Valid OnzeDataTableRequest request) throws Exception {

			
			Page<User> resultPage = repository.findAll(request);
			if (request.getPage() - 1 > resultPage.getTotalPages()) {
				throw new Exception();
		}
		return resultPage;
	}
}