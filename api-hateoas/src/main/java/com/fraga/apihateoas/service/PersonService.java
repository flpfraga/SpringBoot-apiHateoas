package com.fraga.apihateoas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fraga.apihateoas.data.vo.PersonVO;
import com.fraga.apihateoas.repository.PersonRepository;
import com.fraga.apihateoasconverter.DozerConverter;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository repository;

	public List<PersonVO> findAll(){
		return DozerConverter.parseList(repository.findAll(), PersonVO.class);
		
	}
	
	
}
