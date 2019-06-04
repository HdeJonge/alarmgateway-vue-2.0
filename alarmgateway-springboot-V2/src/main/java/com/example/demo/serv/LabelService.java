package com.example.demo.serv;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Label;
import com.example.demo.repo.LabelRepository;

@Service
public class LabelService {
	
	@Autowired
	private LabelRepository repository;
	
	public List<Label> findAll(){
		List<Label> gateways = new ArrayList<>();
		repository.findAll().forEach(gateways::add);
		return gateways;
	}
	
	public Optional<Label> findById(long id) {
		return repository.findById(id);
	}

	public Label save(Label alarmgateway) {
		return repository.save(alarmgateway);
	}

	public void deleteById(long id) {
		repository.deleteById(id);
	}


	


}
