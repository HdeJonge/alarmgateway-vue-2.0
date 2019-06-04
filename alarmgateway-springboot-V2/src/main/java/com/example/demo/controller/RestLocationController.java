package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Label;
import com.example.demo.model.Location;
import com.example.demo.serv.LabelService;
import com.example.demo.serv.LocationService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class RestLocationController {
	
	@Autowired
	private LocationService service;
	
	public RestLocationController() {
	}
	
	@GetMapping("/location")
	public List<Location> getAll() {
		List<Location> location = service.findAll();
		return location;
	}
	@GetMapping("/location/{id}")
	public Location getOne(@PathVariable("id") long id) {
		Location location = service.findById(id).get();
		return location;
	}
	
	@PostMapping("/location")
	public Location postAlarmgateway(@Valid @RequestBody Location location) {

		Location _location = service.save(location);
		return _location;
	}

	@DeleteMapping("/location/{id}")
	public ResponseEntity<String> deleteAlarmgateway(@PathVariable("id") long id) {
		System.out.println("Delete label with ID = " + id + "...");
		service.deleteById(id);
		return new ResponseEntity<>("label has been deleted!", HttpStatus.OK);
	}

	@PutMapping("/location/{id}")
	public ResponseEntity<Location> updateAlarmgateway(@PathVariable("id") long id, @RequestBody Location location) {
		System.out.println("Update location with ID = " + id + "...");
		Optional<Location> LocationData = service.findById(id);
		if (LocationData.isPresent()) {
			return new ResponseEntity<>(service.save(location), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
