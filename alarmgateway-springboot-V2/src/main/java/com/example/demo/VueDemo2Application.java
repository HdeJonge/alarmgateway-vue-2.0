package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.datatables.repository.DataTablesRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.demo.repo.OnzeDataTablesFactoryBean;

@SpringBootApplication
@EnableJpaRepositories(repositoryFactoryBeanClass = OnzeDataTablesFactoryBean.class)
public class VueDemo2Application {

	public static void main(String[] args) {
		SpringApplication.run(VueDemo2Application.class, args);
	}

}
