package com.example.demo.serv;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.demo.constraints.BproSpecification;
import com.example.demo.constraints.DateSpecification;
import com.example.demo.constraints.SearchCriteria;
import com.example.demo.model.User;
import com.example.demo.repo.OnzeDataTableRequest;
import com.example.demo.repo.UserDTRepository;
import com.example.demo.repo.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserDTRepository dtRepository;
	
	@Autowired
	private UserRepository repository;

	public Page<User> findAll(OnzeDataTableRequest req) throws ParseException {
		Page<User> result = dtRepository.findAll(req);
		return result;
	}
	public Page<User> findAll(OnzeDataTableRequest req,String from,String to) throws ParseException {
		DateSpecification<User> spec = new DateSpecification<>(from, to);
		Page<User> result = dtRepository.findAll(req,spec);
		return result;
	}

	public Page<User> findAll(OnzeDataTableRequest req,String query) throws ParseException {

		Page<User> result = dtRepository.findAll(req);
		return result;
	}
	
	public User save(User user) {
		return repository.save(user);
	}
}
