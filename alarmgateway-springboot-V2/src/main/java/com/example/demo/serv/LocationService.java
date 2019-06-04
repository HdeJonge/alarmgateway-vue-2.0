package com.example.demo.serv;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Location;
import com.example.demo.repo.LabelRepository;
import com.example.demo.repo.LocationRepository;

@Service
public class LocationService {
	
	@Autowired
	private LocationRepository repository;
	
	public List<Location> findAll(){
		List<Location> gateways = new ArrayList<>();
		repository.findAll().forEach(gateways::add);
		return gateways;
	}
	
	public Optional<Location> findById(long id) {
		return repository.findById(id);
	}

	public Location save(Location alarmgateway) {
		return repository.save(alarmgateway);
	}

	public void deleteById(long id) {
		repository.deleteById(id);
	}
}
