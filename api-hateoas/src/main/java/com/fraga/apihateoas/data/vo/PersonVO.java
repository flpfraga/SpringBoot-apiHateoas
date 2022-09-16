package com.fraga.apihateoas.data.vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "firstName", "lastName", "bithday", "gender"})
public class PersonVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@JsonProperty("Firt Name")
	private String firstName;
	@JsonProperty("Last Name")
	private String lastName;
	@JsonIgnore
	private LocalDate bithday;
	private String gender;
	
	public PersonVO() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bithday, firstName, gender, id, lastName);
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
				&& Objects.equals(gender, other.gender) && id == other.id && Objects.equals(lastName, other.lastName);
	}

	@Override
	public String toString() {
		return "PersonVO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", bithday=" + bithday
				+ ", gender=" + gender + "]";
	}

	
}
