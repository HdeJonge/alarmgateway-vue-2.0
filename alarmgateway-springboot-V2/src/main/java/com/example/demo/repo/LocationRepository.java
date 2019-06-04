package com.example.demo.repo;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Alarmgateway;
import com.example.demo.model.Label;
import com.example.demo.model.Location;

public interface LocationRepository extends CrudRepository<Location,Long>{

}
