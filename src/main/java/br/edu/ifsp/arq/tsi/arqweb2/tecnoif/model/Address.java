package br.edu.ifsp.arq.tsi.arqweb2.tecnoif.model;

import java.io.Serializable;
import java.util.Objects;

public class Address implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String streetName; 
	private String houseNumber; 
	private String addressComplement; 
	private String neighborhood; 
	private String postalCode; 
	private String city; 
	private String state;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	public String getAddressComplement() {
		return addressComplement;
	}
	public void setAddressComplement(String addressComplement) {
		this.addressComplement = addressComplement;
	}
	public String getNeighborhood() {
		return neighborhood;
	}
	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	} 
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Address{" +
				"id=" + id +
				", streetName='" + streetName + '\'' +
				", houseNumber='" + houseNumber + '\'' +
				", addressComplement='" + addressComplement + '\'' +
				", neighborhood='" + neighborhood + '\'' +
				", postalCode='" + postalCode + '\'' +
				", city='" + city + '\'' +
				", state='" + state + '\'' +
				'}';
	}

}
