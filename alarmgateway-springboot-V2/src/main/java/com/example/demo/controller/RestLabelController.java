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
import com.example.demo.serv.LabelService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class RestLabelController {
	
	@Autowired
	private LabelService service;
	
	public RestLabelController() {
	}
	
	@GetMapping("/label")
	public List<Label> getAll() {
		List<Label> label = service.findAll();
		return label;
	}
	@GetMapping("/label/{id}")
	public Label getOne(@PathVariable("id") long id) {
		Label label = service.findById(id).get();
		return label;
	}
	
	@PostMapping("/label")
	public Label postAlarmgateway(@Valid @RequestBody Label label) {

		Label _label = service.save(label);
		return _label;
	}

	@DeleteMapping("/label/{id}")
	public ResponseEntity<String> deleteAlarmgateway(@PathVariable("id") long id) {
		System.out.println("Delete label with ID = " + id + "...");
		service.deleteById(id);
		return new ResponseEntity<>("label has been deleted!", HttpStatus.OK);
	}

	@PutMapping("/label/{id}")
	public ResponseEntity<Label> updateAlarmgateway(@PathVariable("id") long id, @RequestBody Label label) {
		System.out.println("Update label with ID = " + id + "...");
		Optional<Label> LabelData = service.findById(id);
		if (LabelData.isPresent()) {
			Label _label = LabelData.get();
			_label.setDescription(label.getDescription());
			return new ResponseEntity<>(service.save(_label), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
