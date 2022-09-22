package com.fraga.apihateoas.integrationtest.vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.github.dozermapper.core.Mapping;

public class PersonVO 	implements Serializable {
private static final long serialVersionUID = 1L;
	
	@Mapping("id")
	private Long key;
	private String firstName;
	private String lastName;
	private String gender;
	
	public PersonVO() {
		// TODO Auto-generated constructor stub
	}
	
	public Long getKey() {
		return key;
	}


	public void setKey(Long key) {
		this.key = key;
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

	

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, gender, key, lastName);
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
		return Objects.equals(firstName, other.firstName) && Objects.equals(gender, other.gender)
				&& Objects.equals(key, other.key) && Objects.equals(lastName, other.lastName);
	}


}
