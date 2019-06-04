package com.example.demo.serv;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Alarmgateway;
import com.example.demo.repo.AlarmgatewayRepository;

@Service
public class AlarmgatewayService {
	
	@Autowired
	private AlarmgatewayRepository repository;
	
	public List<Alarmgateway> findAll(){
		List<Alarmgateway> gateways = new ArrayList<>();
		repository.findAll().forEach(gateways::add);
		return gateways;
	}
	
	public Optional<Alarmgateway> findById(long id) {
		return repository.findById(id);
	}

	public Alarmgateway save(Alarmgateway alarmgateway) {
		return repository.save(alarmgateway);
	}

	public void deleteById(long id) {
		repository.deleteById(id);
	}


	


}
