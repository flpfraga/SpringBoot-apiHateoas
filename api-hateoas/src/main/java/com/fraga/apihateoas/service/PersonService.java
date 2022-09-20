package com.fraga.apihateoas.service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fraga.apihateoas.controller.PersonController;
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
		List<PersonVO> persons = DozerConverter.parseList(repository.findAll(), PersonVO.class);
		persons.stream()
				.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
		return persons;
	}

	public PersonVO findById(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records matches for this ID!"));

		PersonVO vo = DozerConverter.parseObject(entity, PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return vo;

	}

	public PersonVO create(PersonVO person) {
		var entity = DozerConverter.parseObject(person, Person.class);
		PersonVO vo = DozerConverter.parseObject(repository.save(entity), PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(person.getKey())).withSelfRel());
		return vo;
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
		vo = DozerConverter.parseObject(repository.save(entity), PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(person.getKey())).withSelfRel());
		return vo;
	}

}
