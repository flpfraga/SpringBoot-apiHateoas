package com.fraga.apihateoas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fraga.apihateoas.converter.DozerConverter;
import com.fraga.apihateoas.data.model.Person;
import com.fraga.apihateoas.data.vo.PersonVO;
import com.fraga.apihateoas.exception.ResourceNotFoundException;
import com.fraga.apihateoas.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository repository;

	public List<PersonVO> findAll() {
		return DozerConverter.parseList(repository.findAll(), PersonVO.class);
	}

	public PersonVO findById(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records matches for this ID!"));
		return DozerConverter.parseObject(entity, PersonVO.class);

	}

	public PersonVO create(PersonVO person) {
		var entity = DozerConverter.parseObject(person, Person.class);
		return DozerConverter.parseObject(repository.save(entity), PersonVO.class);
	}
	
	public void delete(Long id) {
		var vo = findById(id);
		repository.delete(DozerConverter.parseObject(vo, Person.class));
	}
	
	public PersonVO update(Long id, PersonVO person) {
		var vo = findById(id);
		vo.setFirstName(person.getFirstName());
		vo.setLastName(person.getLastName());
		vo.setGender(person.getGender());
		vo.setBithday(person.getBithday());
		var entity = DozerConverter.parseObject(vo, Person.class);
		return DozerConverter.parseObject(repository.save(entity), PersonVO.class);
	}
	

}
