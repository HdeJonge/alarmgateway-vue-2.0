package com.example.demo.repo;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

//https://www.baeldung.com/spring-data-repositories

@NoRepositoryBean
public interface OnzeDataTablesRepository<T, ID extends Serializable>
		extends PagingAndSortingRepository<T, ID>, JpaSpecificationExecutor<T> {

	Page<T> findAll(OnzeDataTableRequest input);


//	/**
//	 * Returns the filtered list for the given {@link DataTablesInput}.
//	 * 
//	 * @param input                   the {@link DataTablesInput} mapped from the
//	 *                                Ajax request
//	 * @param additionalSpecification an additional {@link Specification} to apply
//	 *                                to the query (with an "AND" clause)
//	 * @return a {@link DataTablesOutput}
//	 */
//	Page<T> findAll(OnzeDataTableRequest input, Specification<T> additionalSpecification);
//
//	/**
//	 * Returns the filtered list for the given {@link DataTablesInput}.
//	 * 
//	 * @param input                     the {@link DataTablesInput} mapped from the
//	 *                                  Ajax request
//	 * @param additionalSpecification   an additional {@link Specification} to apply
//	 *                                  to the query (with an "AND" clause)
//	 * @param preFilteringSpecification a pre-filtering {@link Specification} to
//	 *                                  apply to the query (with an "AND" clause)
//	 * @return a {@link DataTablesOutput}
//	 */
//	Page<T> findAll(OnzeDataTableRequest input, Specification<T> additionalSpecification,
//			Specification<T> preFilteringSpecification);
//
//	/**
//	 * Returns the filtered list for the given {@link DataTablesInput}.
//	 *
//	 * @param input     the {@link DataTablesInput} mapped from the Ajax request
//	 * @param converter the {@link Function} to apply to the results of the query
//	 * @return a {@link DataTablesOutput}
//	 */
//	<R> Page<R> findAll(OnzeDataTableRequest input, Function<T, R> converter);
//
//	/**
//	 * Returns the filtered list for the given {@link DataTablesInput}.
//	 *
//	 * @param input                     the {@link DataTablesInput} mapped from the
//	 *                                  Ajax request
//	 * @param additionalSpecification   an additional {@link Specification} to apply
//	 *                                  to the query (with an "AND" clause)
//	 * @param preFilteringSpecification a pre-filtering {@link Specification} to
//	 *                                  apply to the query (with an "AND" clause)
//	 * @param converter                 the {@link Function} to apply to the results
//	 *                                  of the query
//	 * @return a {@link DataTablesOutput}
//	 */
//	<R> Page<R> findAll(OnzeDataTableRequest input, Specification<T> additionalSpecification,
//			Specification<T> preFilteringSpecification, Function<T, R> converter);

}