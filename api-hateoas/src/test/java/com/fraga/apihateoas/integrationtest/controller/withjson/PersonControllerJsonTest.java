package com.fraga.apihateoas.integrationtest.controller.withjson;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fraga.apihateoas.integrationtest.testcontainers.AbstractIntegrationTest;
import com.fraga.apihateoas.integrationtest.vo.PersonVO;

import configs.TestConfigs;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class PersonControllerJsonTest extends AbstractIntegrationTest {

	private static RequestSpecification specification;
	private static ObjectMapper objectMapper;
	private static PersonVO person;
	
	@BeforeAll
	public static void setup() {
		objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		person = new PersonVO();
	}

	@Test
	@Order(1)
	public void testCreate() throws JsonMappingException, JsonProcessingException {
		mockPerson();
		specification = new RequestSpecBuilder()
		.addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_VALID)
		.setBasePath("/api/person/v1")
		.setPort(TestConfigs.SERVER_PORT)
		.addFilter(new RequestLoggingFilter(LogDetail.ALL))
		.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
		.build();
		
		var content = given().spec(specification)
				.contentType(TestConfigs.CONTENT_TYPE_JSON)
					.body(person)
					.when()
					.post()
				.then()
					.statusCode(200)
				.extract()
					.body()
						.asString();
			PersonVO createdPerson = objectMapper.readValue(content, PersonVO.class);
			person = createdPerson;
			
			assertNotNull(createdPerson.getFirstName());
			assertNotNull(createdPerson.getLastName());
			assertNotNull(createdPerson.getGender());
			
			assertTrue(createdPerson.getKey() > 0);
			
			assertEquals("Richard", createdPerson.getFirstName());
			assertEquals("Stallman",createdPerson.getLastName());
			assertEquals("Male",createdPerson.getGender());
	}
	@Test
	@Order(2)
	public void testCreateWithInvalidOrigin() throws JsonMappingException, JsonProcessingException {
		mockPerson();
		specification = new RequestSpecBuilder()
				.addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_INVALID)
				.setBasePath("/api/person/v1")
				.setPort(TestConfigs.SERVER_PORT)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
				.build();
		
		var content = given().spec(specification)
				.contentType(TestConfigs.CONTENT_TYPE_JSON)
					.body(person)
				.when()
					.post()
				.then()
					.statusCode(403)
				.extract()
					.body()
						.asString();
		
		assertNotNull(content);
		assertEquals("Invalid CORS request", content);
	}
	@Test
	@Order(3)
	public void findById() throws JsonMappingException, JsonProcessingException {
		mockPerson();
		specification = new RequestSpecBuilder()
				.addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_VALID)
				.setBasePath("/api/person/v1")
				.setPort(TestConfigs.SERVER_PORT)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
				.build();
		
		var content = given().spec(specification)
				.contentType(TestConfigs.CONTENT_TYPE_JSON)
					.pathParam("id", person.getKey() )
				.when()
					.get("{id}")
				.then()
					.statusCode(200)
				.extract()
					.body()
						.asString();
		PersonVO persistedPerson = objectMapper.readValue(content, PersonVO.class);
		person = persistedPerson;
		
		assertNotNull(persistedPerson.getFirstName());
		assertNotNull(persistedPerson.getLastName());
		assertNotNull(persistedPerson.getGender());
		
		assertEquals("Richard", persistedPerson.getFirstName());
		assertEquals("Stallman",persistedPerson.getLastName());
		assertEquals("Male",persistedPerson.getGender());
	}
	@Test
	@Order(4)
	public void findByIdInvalidOrigin() throws JsonMappingException, JsonProcessingException {
		mockPerson();
		specification = new RequestSpecBuilder()
				.addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_INVALID)
				.setBasePath("/api/person/v1")
				.setPort(TestConfigs.SERVER_PORT)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
				.build();
		
		var content = given().spec(specification)
				.contentType(TestConfigs.CONTENT_TYPE_JSON)
					.pathParam("id", person.getKey() )
				.when()
					.get("{id}")
				.then()
					.statusCode(403)
				.extract()
					.body()
						.asString();
		assertNotNull(content);
		assertEquals("Invalid CORS request", content);
	}

	private void mockPerson() {
		
		person.setFirstName("Richard");
		person.setLastName("Stallman");
		person.setGender("Male");
	}
}
