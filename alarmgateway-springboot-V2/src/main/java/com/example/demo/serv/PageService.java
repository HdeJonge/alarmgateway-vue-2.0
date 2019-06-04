package com.example.demo.serv;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.demo.constraints.BproSpecification;
import com.example.demo.constraints.SearchCriteria;
import com.example.demo.constraints.SpecificationsBuilder;
import com.example.demo.model.Alarmgateway;
import com.example.demo.repo.PageRepository;


//https://www.baeldung.com/spring-data-repositories

@Service
public class PageService {
 
    @Autowired
    private PageRepository dao;
 
    public Page<Alarmgateway> findPaginated(int page, int size) {
        return dao.findAll(PageRequest.of(page, size));
    }

    public Page<Alarmgateway> findPaginatedAndSorted(String[] columns, String search, boolean desc, String sortBy, int page, int size) {

    	/*with builder*/
        SpecificationsBuilder builder = new SpecificationsBuilder(columns,search);
        Specification<Alarmgateway> result = builder.build(
        	    searchCriteria -> new  BproSpecification<Alarmgateway>((SearchCriteria) searchCriteria)
        );
        
        /*no builder*/
        Specification<Alarmgateway> res = null;
        for (int i = 1; i < columns.length; i++) {
        	BproSpecification<Alarmgateway> spec = new BproSpecification<Alarmgateway>(new SearchCriteria(columns[i], ":", search));;
        	res = Specification.where(res).or(spec);
        }
        
    	Direction dir = desc ? Direction.DESC : Direction.ASC;
    	Sort sort = Sort.by(new Sort.Order(dir, sortBy));
    	Page<Alarmgateway> findAll = dao.findAll(result,PageRequest.of(page, size,sort));
    	
    	return findAll;
    }
}