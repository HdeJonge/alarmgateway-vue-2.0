package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Alarmgateway;
import com.example.demo.serv.AlarmgatewayService;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class RestAlarmgatewayController {
	
	@Autowired
	private AlarmgatewayService service;
	
	public RestAlarmgatewayController() {
		
	}
	
	@GetMapping("/alarm")
	public List<Alarmgateway> getAll() {
		List<Alarmgateway> alarmgatewaysDto = service.findAll();
		return alarmgatewaysDto;
	}
	
	@GetMapping("/alarm/{id}")
	public Alarmgateway getOne(@PathVariable("id") long id) {
		Alarmgateway alarmgateway = service.findById(id).get();
		return alarmgateway;
	}
	
	@PostMapping("/alarm")
	public Alarmgateway postAlarmgateway(@Valid @RequestBody Alarmgateway Alarmgateway) {
		Alarmgateway _Alarmgateway = service.save(Alarmgateway);
		return _Alarmgateway;
	}

	@DeleteMapping("/alarm/{id}")
	public ResponseEntity<String> deleteAlarmgateway(@PathVariable("id") long id) {
		System.out.println("Delete Alarmgateway with ID = " + id + "...");
		service.deleteById(id);
		return new ResponseEntity<>("Alarmgateway has been deleted!", HttpStatus.OK);
	}

	@PutMapping("/alarm/{id}")
	public ResponseEntity<Alarmgateway> updateAlarmgateway(@PathVariable("id") long id, @RequestBody Alarmgateway Alarmgateway) {
		System.out.println("Update Alarmgateway with ID = " + id + "...");
		return new ResponseEntity<>(service.save(Alarmgateway), HttpStatus.OK);

	}
	
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        return errors;
//    }
	
}
