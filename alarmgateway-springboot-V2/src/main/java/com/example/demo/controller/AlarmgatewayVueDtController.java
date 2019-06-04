package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Alarmgateway;
import com.example.demo.repo.OnzeDataTableRequest;
import com.example.demo.repo.OnzeDataTablesRepository;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class AlarmgatewayVueDtController {
	
	@Autowired
	private OnzeDataTablesRepository<Alarmgateway, Long> repository;
	
	@GetMapping("/datatable/get")
		public Page<Alarmgateway> findPaginatedByOrder(@Valid OnzeDataTableRequest request) throws Exception {
			
			Page<Alarmgateway> resultPage = repository.findAll(request);
			if (request.getPage() - 1 > resultPage.getTotalPages()) {
				throw new Exception();
		}
		return resultPage;
	}
}