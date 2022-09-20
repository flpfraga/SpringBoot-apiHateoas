package com.fraga.apihateoas.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fraga.apihateoas.controller.BookController;
import com.fraga.apihateoas.controller.PersonController;
import com.fraga.apihateoas.converter.DozerConverter;
import com.fraga.apihateoas.data.model.Book;
import com.fraga.apihateoas.data.model.Person;
import com.fraga.apihateoas.data.vo.BookVO;
import com.fraga.apihateoas.data.vo.PersonVO;
import com.fraga.apihateoas.exception.ResourceNotFoundException;
import com.fraga.apihateoas.repository.BookRepository;
import com.fraga.apihateoas.repository.PersonRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository repository;

	public List<BookVO> findAll() {
		List<BookVO> books = DozerConverter.parseList(repository.findAll(), BookVO.class);
		books.stream()
				.forEach(p -> p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()));
		return books;
	}

	public BookVO findById(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records matches for this ID!"));

		BookVO vo = DozerConverter.parseObject(entity, BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		return vo;

	}

	public BookVO create(BookVO book) {
		var entity = DozerConverter.parseObject(book, Book.class);
		BookVO vo = DozerConverter.parseObject(repository.save(entity), BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(book.getKey())).withSelfRel());
		return vo;
	}

	public void delete(Long id) {
		var vo = findById(id);
		repository.delete(DozerConverter.parseObject(vo, Book.class));
	}

	public BookVO update(Long id, BookVO book) {
		var vo = findById(id);
		vo.setAuthor(book.getAuthor());
		vo.setLaunchDate(book.getLaunchDate());
		vo.setPrice(book.getPrice());
		vo.setTitle(book.getTitle());
		var entity = DozerConverter.parseObject(vo, Book.class);
		vo = DozerConverter.parseObject(repository.save(entity), BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(book.getKey())).withSelfRel());
		return vo;
	}

}
