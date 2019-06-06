package com.example.demo.repo;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.example.demo.constraints.BproSpecification;
import com.example.demo.constraints.SearchCriteria;
import com.example.demo.constraints.SpecificationsBuilder;

public class OnzeDataTablesRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
		implements OnzeDataTablesRepository<T, ID> {

	public OnzeDataTablesRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
	}
	@Override
	
	public Page<T> findAll(OnzeDataTableRequest input) {
		return findAll(input, null);
	}

	@Override
	public Page<T> findAll(OnzeDataTableRequest input, Specification<T> additionalSpecification) {
		SpecificationsBuilder builder = new SpecificationsBuilder(input.getColumns(), input.getSearch());
		Specification<T> result = builder
				.build(searchCriteria -> new BproSpecification<T>((SearchCriteria) searchCriteria));

		Pageable page = null;
		if (input.getDirection() == null) {
			page = PageRequest.of(input.getPage() - 1, input.getSize());
		} else {
			Direction dir = input.getDirection();
			Sort sort = Sort.by(new Sort.Order(dir, input.getSortBy()));
			page = PageRequest.of(input.getPage() - 1, input.getSize(), sort);
		}
		return findAll(result.and(additionalSpecification), page);
	}
}
