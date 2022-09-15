package com.fraga.apihateoas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fraga.apihateoas.data.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

}
