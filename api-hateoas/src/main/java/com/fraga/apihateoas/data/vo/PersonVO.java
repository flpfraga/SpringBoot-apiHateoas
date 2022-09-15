package com.fraga.apihateoas.data.vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;

public class PersonVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String firstName;
	private String lastName;
	private LocalDate bithday;
	private String gernder;
	
	public PersonVO() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getBithday() {
		return bithday;
	}

	public void setBithday(LocalDate bithday) {
		this.bithday = bithday;
	}

	public String getGernder() {
		return gernder;
	}

	public void setGernder(String gernder) {
		this.gernder = gernder;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bithday, firstName, gernder, id, lastName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonVO other = (PersonVO) obj;
		return Objects.equals(bithday, other.bithday) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(gernder, other.gernder) && id == other.id && Objects.equals(lastName, other.lastName);
	} 

}
