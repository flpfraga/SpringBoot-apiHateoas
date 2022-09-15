package com.fraga.apihateoas.data.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "firt-name", length = 40, nullable = false)
	private String firstName;
	@Column(name = "last-name", length = 40, nullable = false)
	private String lastName;
	@Column(length = 20)
	private LocalDate bithday;
	@Column(length=10)
	private String gernder;
	
	public Person() {
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
		Person other = (Person) obj;
		return Objects.equals(bithday, other.bithday) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(gernder, other.gernder) && id == other.id && Objects.equals(lastName, other.lastName);
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", bithday=" + bithday
				+ ", gernder=" + gernder + "]";
	}
	
	

}
