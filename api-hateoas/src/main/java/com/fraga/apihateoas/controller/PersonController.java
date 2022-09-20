package com.fraga.apihateoas.controller;

import java.util.List;

import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.fraga.apihateoas.util.MediaType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/person/v1")
@Tag(name="People", description = "EndPoints for Managing Peoples")
public class PersonController {

	@Autowired
	private PersonService service;

	@GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
	@Operation(summary = "Finds all people", description = "Finds all people",
	tags = {"People"},
	responses = {
			@ApiResponse(description = "Success", responseCode = "200",
					content = {
							@Content(
									mediaType = "application/json",
									array = @ArraySchema(schema = @Schema(implementation = PersonVO.class))
									),
							@Content(
									mediaType = "application/x-yaml",
									array = @ArraySchema(schema = @Schema(implementation = PersonVO.class))
									),
							@Content(
									mediaType = "application/xml",
									array = @ArraySchema(schema = @Schema(implementation = PersonVO.class))
									)
					}),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
	})
	public List<PersonVO> findAll() {
		return service.findAll();
	}
	
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
	@Operation(summary = "Finds a person", description = "Finds a person",
			tags = {"People"},
			responses = {
					@ApiResponse(description = "Success", responseCode = "200",
							content = {
									@Content(
											mediaType = "application/json",
											schema = @Schema(implementation = PersonVO.class)
											),
									@Content(
											mediaType = "application/x-yaml",
											schema = @Schema(implementation = PersonVO.class)
											),
									@Content(
											mediaType = "application/xml",
											schema = @Schema(implementation = PersonVO.class)
											)
							}),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
			})
	public PersonVO findById(@PathVariable("id") Long id) {
		return service.findById(id);
	}
	
	

	@PostMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML }, 
			consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
	@Operation(summary = "Create a person", description = "Create a person",
	tags = {"People"},
	responses = {
			@ApiResponse(description = "Success", responseCode = "200",
					content = {
							@Content(
									mediaType = "application/json",
									schema = @Schema(implementation = PersonVO.class)
									),
							@Content(
									mediaType = "application/x-yaml",
									schema = @Schema(implementation = PersonVO.class)
									),
							@Content(
									mediaType = "application/xml",
									schema = @Schema(implementation = PersonVO.class)
									)
					}),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
	})
	public PersonVO create(@RequestBody PersonVO person) {
		return service.create(person);
	}

	@PutMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML }, 
			consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
	@Operation(summary = "Update a person", description = "Update a person",
	tags = {"People"},
	responses = {
			@ApiResponse(description = "Success", responseCode = "200",
					content = {
							@Content(
									mediaType = "application/json",
									schema = @Schema(implementation = PersonVO.class)
									),
							@Content(
									mediaType = "application/x-yaml",
									schema = @Schema(implementation = PersonVO.class)
									),
							@Content(
									mediaType = "application/xml",
									schema = @Schema(implementation = PersonVO.class)
									)
					}),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
	})
	public PersonVO update(@PathVariable("id") Long id, @RequestBody PersonVO person) {
		return service.update(id, person);
	}

	@DeleteMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
	@Operation(summary = "Delete a person", description = "Deletea person",
	tags = {"People"},
	responses = {
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
	})
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}

}
