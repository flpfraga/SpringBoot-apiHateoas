package com.fraga.apihateoas.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fraga.apihateoas.data.vo.PersonVO;
import com.fraga.apihateoas.service.PersonService;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

	@Autowired
	private PersonService service;
	
	@GetMapping
	public List<PersonVO> findAll(){
		return service.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public PersonVO findById(@PathVariable ("id") Long id){
		return service.findById(id);
	}
	
	@PostMapping
	public PersonVO create(@RequestBody PersonVO person){
		return service.create(person);
	}
	
	@PutMapping("/{id}")
	public PersonVO update(@PathVariable ("id") Long id,@RequestBody PersonVO person){
		return service.update(id, person);
	}
	
	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable ("id") Long id){
		service.delete(id);
	}
	
}
