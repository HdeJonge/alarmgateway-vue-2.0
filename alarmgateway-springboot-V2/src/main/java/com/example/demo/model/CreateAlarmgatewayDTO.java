package com.example.demo.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CreateAlarmgatewayDTO {
	@NotNull
	@NotEmpty
	private String mac;

	public CreateAlarmgatewayDTO() {
		
	}

	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}

}
