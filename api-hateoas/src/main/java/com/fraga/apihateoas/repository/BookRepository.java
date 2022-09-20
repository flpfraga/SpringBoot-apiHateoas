package com.fraga.apihateoas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fraga.apihateoas.data.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Long>{
	
}
