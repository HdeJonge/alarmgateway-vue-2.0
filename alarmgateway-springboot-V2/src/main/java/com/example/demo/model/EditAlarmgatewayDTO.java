package com.example.demo.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class EditAlarmgatewayDTO {

	private Long id;
	@NotNull
	@NotEmpty
	private String mac;
	
	public EditAlarmgatewayDTO() {
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
}
