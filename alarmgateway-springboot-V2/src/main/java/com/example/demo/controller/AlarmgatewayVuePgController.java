package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Alarmgateway;
import com.example.demo.serv.PageService;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class AlarmgatewayVuePgController {

	@Autowired
	private PageService service;

	@RequestMapping(value = "/page/get", params = { "search", "desc", "sortBy", "page", "size" }, method = RequestMethod.GET)
	public Page<Alarmgateway> findPaginatedByOrder(@RequestParam("search") String search, @RequestParam("desc") boolean desc, @RequestParam("sortBy") String sortBy, @RequestParam("page") int page,
			@RequestParam("size") int size) throws Exception {

		String[] columns = { "mac", "id" };

		Page<Alarmgateway> resultPage = service.findPaginatedAndSorted(columns, search, desc, sortBy, page - 1, size);
		if (page - 1 > resultPage.getTotalPages()) {
			throw new Exception();
		}
		return resultPage;
	}

}
