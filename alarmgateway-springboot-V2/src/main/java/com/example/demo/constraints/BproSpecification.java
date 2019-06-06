package com.example.demo.constraints;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.example.demo.model.Alarmgateway;

public class BproSpecification<T> implements Specification<T> {
	/**
	 * https://www.baeldung.com/rest-api-search-language-spring-data-specifications
	 * 
	 * BproSpecification implements the Specification interface and we’re going to
	 * pass in our own constraint to construct the actual query:
	 */
	private SearchCriteria criteria;

	public BproSpecification(SearchCriteria criteria) {
		super();
		this.criteria = criteria;
	}

	public BproSpecification() {
	}

	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> cq, CriteriaBuilder builder) {
		
		if (criteria.getOperation().equalsIgnoreCase(">")) {
			return builder.greaterThanOrEqualTo(root.<String>get(criteria.getKey()), criteria.getValue().toString());
		} else if (criteria.getOperation().equalsIgnoreCase("<")) {
			return builder.lessThanOrEqualTo(root.<String>get(criteria.getKey()), criteria.getValue().toString());
		} else if (criteria.getOperation().equalsIgnoreCase(":")) {
			/**/
			if (root.get(criteria.getKey()).getJavaType() == String.class) {
				return builder.like(root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
			} else {
				try {
					Long.parseLong(criteria.getValue().toString());
				} catch (NumberFormatException e) {
					return null;
				}
				return builder.equal(root.get(criteria.getKey()), criteria.getValue());
			}
		}
		return null;
	}

}
