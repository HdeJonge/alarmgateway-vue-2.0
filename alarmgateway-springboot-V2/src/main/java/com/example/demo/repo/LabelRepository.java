package com.example.demo.repo;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Alarmgateway;
import com.example.demo.model.Label;

public interface LabelRepository extends CrudRepository<Label,Long>{

}
