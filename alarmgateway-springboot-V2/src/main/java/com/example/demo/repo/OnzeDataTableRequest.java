package com.example.demo.repo;

import org.springframework.data.domain.Sort.Direction;

public class OnzeDataTableRequest {

	public OnzeDataTableRequest() {
		
	}
	
	public OnzeDataTableRequest(String search, String[] columns, Direction direction, String sortBy, int page, int size) {
		super();
		this.search = search;
		this.columns = columns;
		this.direction = direction;
		this.sortBy = sortBy;
		this.page = page;
		this.size = size;
	}

	private String search;
	private String[] columns;
	private Direction direction;
	private String sortBy;
	private int page;
	private int size;

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String[] getColumns() {
		return columns;
	}

	public void setColumns(String[] columns) {
		this.columns = columns;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(String value) {
		if(value !=null) {
			this.direction = Boolean.parseBoolean(value) ? Direction.DESC : Direction.ASC;
		}
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
