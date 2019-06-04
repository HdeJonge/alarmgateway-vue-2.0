package com.example.demo.constraints;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;

import com.example.demo.model.Alarmgateway;

public class SpecificationsBuilder {
    private final List<SearchCriteria> params;
	 
    public SpecificationsBuilder() {
        params = new ArrayList<SearchCriteria>();
    }
    public SpecificationsBuilder(String[] columns, String search) {
    	params = new ArrayList<SearchCriteria>();
        for (int i = 0; i < columns.length; i++) {
		    params.add(new SearchCriteria(columns[i], ":", search));
	    }
    }
    public SpecificationsBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }
    
    public <T> Specification<T> build(Function<SearchCriteria, BproSpecification<T>> mappingToSpecification) {
        if (params.size() == 0) {
            return null;
        }
        List<Specification<T>> specs = new ArrayList<>();
        for (SearchCriteria param : params) {
            specs.add(mappingToSpecification.apply(param));  //how to make here generic
        }

        Specification<T> result = specs.get(0);
        for (int i = 1; i < specs.size(); i++) {
            result = Specification.where(result).or(specs.get(i)); //warning 1
        }
        return result;
    }
}
