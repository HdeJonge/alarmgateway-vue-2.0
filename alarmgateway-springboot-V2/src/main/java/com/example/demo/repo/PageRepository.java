package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.model.Alarmgateway;

public interface PageRepository extends JpaRepository<Alarmgateway,Long>, JpaSpecificationExecutor<Alarmgateway>{

}
