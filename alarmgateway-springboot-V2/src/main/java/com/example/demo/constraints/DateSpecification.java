package com.example.demo.constraints;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class DateSpecification<T> implements Specification<T> {

	/**
	 * https://stackoverflow.com/questions/38424516/date-comparison-using-the-jpa-criteria-api
	 */
	private static final long serialVersionUID = 1L;

	private String key;
	private Date startDate;
	private Date endDate;

	public DateSpecification(String startDate, String endDate) throws ParseException {
		super();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		this.key = "birthDay";
		if (startDate != "") {
			this.startDate = dateFormat.parse(startDate);
		}
		if (endDate != "") {
			this.endDate = dateFormat.parse(endDate);
		}
	}

	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> cq, CriteriaBuilder criteriaBuilder) {
		Predicate startDatePredicate = criteriaBuilder.greaterThanOrEqualTo(root.get(key).as(java.sql.Date.class),
				startDate);
		Predicate endDatePredicate = criteriaBuilder.lessThanOrEqualTo(root.get(key).as(java.sql.Date.class), endDate);
		Predicate and = criteriaBuilder.and(startDatePredicate, endDatePredicate);
		
		if(startDate!=null && endDate==null) {
			return startDatePredicate;
		}else if(startDate==null && endDate!=null) {
			return endDatePredicate;
		}
		else {
			return and;
		}
//		Predicate startDateOrPredicate = criteriaBuilder.or(startDatePredicate, root.get(key).isNull());
//		Predicate endDateOrPredicate = criteriaBuilder.or(endDatePredicate, root.get(key).isNull());
	}

}
