package com.example.demo.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "locations")
public class Location {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@NotEmpty
	private String street;
	@NotNull
	@NotEmpty
	private String streetNumber;
	@NotNull
	@NotEmpty
	private String town;
	@NotNull
	@NotEmpty
	private String postalCode;
	@NotNull
	@NotEmpty
	private String phoneNumber;
	
    @ManyToMany(cascade = {
            CascadeType.MERGE,
            CascadeType.DETACH
        })
	    @JoinTable(
	            name = "location_label", 
	            joinColumns = { @JoinColumn(name = "location_id") }, 
	            inverseJoinColumns = { @JoinColumn(name = "label_id") }
	        )
	private List<Label> labelList;

	public List<Label> getLabelList() {
		return labelList;
	}

	public void setLabelList(List<Label> labels) {
		this.labelList = labels;
	}

	public void addLabel(Label label) {
		this.labelList.add(label);
	}
	public void removeLabel(Label label) {
		this.labelList.remove(label);
	}
	public Location() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getLocationDescription() {
		return 
				street + " " 
				+ streetNumber +  ", "
				+ town;

	}
}
