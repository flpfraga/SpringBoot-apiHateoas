package com.fraga.apihateoas.unittests.mapper.mocks;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fraga.apihateoas.data.model.Person;
import com.fraga.apihateoas.data.vo.PersonVO;

public class MockPerson {
	
	public Person mockEntity() {
		return mockEntity(0);
	}
	
	public PersonVO mockVO() {
		return mockVO(0);
	}
	
	public List<Person> mockEntityList(){
		List<Person> persons = new ArrayList<Person>();
		for (int i = 0; i < 14; i++) {
			persons.add(mockEntity(i));
		}
		return persons;
	}
 
	public List<PersonVO> mockVOList(){
		List<PersonVO> persons = new ArrayList<PersonVO>();
		for (int i = 0; i < 14; i++) {
			persons.add(mockVO(i));
		}
		return persons;
	}
	
	public Person mockEntity(Integer number) {
		Person person = new Person();
		person.setId(number.longValue());
		person.setFirstName("First Name Teste" + number);
		person.setLastName("Last Name Teste" + number);
		person.setGender(((number % 2)==0)? "Male":"Female");
		person.setBithday(LocalDate.now().plusDays(number));
		return person;
	}
	
	public PersonVO mockVO(Integer number) {
		PersonVO person = new PersonVO();
		person.setKey(number.longValue());
		person.setFirstName("First Name Teste" + number);
		person.setLastName("Last Name Teste" + number);
		person.setGender(((number % 2)==0)? "Male":"Female");
		person.setBithday(LocalDate.now().plusDays(number));
		return person;
	}
	
	
}
