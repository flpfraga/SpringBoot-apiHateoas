package com.fraga.apihateoas.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fraga.apihateoas.data.model.Person;
import com.fraga.apihateoas.data.vo.PersonVO;
import com.fraga.apihateoas.unittests.mapper.mocks.MockPerson;

public class DozerConverterTest {

	MockPerson inputObject;
	
	@BeforeEach
	public void setUp() {
		inputObject = new MockPerson();
	}
	
	@Test
	public void parseEntityToVoTest() {
		PersonVO output = DozerConverter.parseObject(inputObject.mockEntity(), PersonVO.class);
		assertEquals(Long.valueOf(0L), output.getKey());
        assertEquals("First Name Teste0", output.getFirstName());
        assertEquals("Last Name Teste0", output.getLastName());
        assertEquals("Male", output.getGender());
        assertEquals(LocalDate.now(), output.getBithday());
	}

	@Test
	public void parseVoToEntityTest() {
		Person output = DozerConverter.parseObject(inputObject.mockVO(), Person.class);
		assertEquals(Long.valueOf(0L), output.getId());
		assertEquals("First Name Teste0", output.getFirstName());
		assertEquals("Last Name Teste0", output.getLastName());
		assertEquals("Male", output.getGender());
		assertEquals(LocalDate.now(), output.getBithday());
	}
	
	@Test
	public void parseListEntityToListVoTest() {
		List<PersonVO> outputList= DozerConverter.parseList(inputObject.mockEntityList(), PersonVO.class);
		
		PersonVO outputZero = outputList.get(0);
		assertEquals(Long.valueOf(0L), outputZero.getKey());
		assertEquals("First Name Teste0", outputZero.getFirstName());
		assertEquals("Last Name Teste0", outputZero.getLastName());
		assertEquals("Male", outputZero.getGender());
		assertEquals(LocalDate.now(), outputZero.getBithday());

		PersonVO outputSix = outputList.get(6);
		assertEquals(Long.valueOf(6L), outputSix.getKey());
		assertEquals("First Name Teste6", outputSix.getFirstName());
		assertEquals("Last Name Teste6", outputSix.getLastName());
		assertEquals("Male", outputSix.getGender());
		assertEquals(LocalDate.now().plusDays(6), outputSix.getBithday());

		PersonVO outputThirtenn = outputList.get(13);
		assertEquals(Long.valueOf(13L), outputThirtenn.getKey());
		assertEquals("First Name Teste13", outputThirtenn.getFirstName());
		assertEquals("Last Name Teste13", outputThirtenn.getLastName());
		assertEquals("Female", outputThirtenn.getGender());
		assertEquals(LocalDate.now().plusDays(13), outputThirtenn.getBithday());

	}

	@Test
	public void parseListVOToListEntityTest() {
		List<Person> outputList = DozerConverter.parseList(inputObject.mockVOList(), Person.class);

		Person outputZero = outputList.get(0);
		assertEquals(Long.valueOf(0L), outputZero.getId());
		assertEquals("First Name Teste0", outputZero.getFirstName());
		assertEquals("Last Name Teste0", outputZero.getLastName());
		assertEquals("Male", outputZero.getGender());
		assertEquals(LocalDate.now(), outputZero.getBithday());

		Person outputSix = outputList.get(6);
		assertEquals(Long.valueOf(6L), outputSix.getId());
		assertEquals("First Name Teste6", outputSix.getFirstName());
		assertEquals("Last Name Teste6", outputSix.getLastName());
		assertEquals("Male", outputSix.getGender());
		assertEquals(LocalDate.now().plusDays(6), outputSix.getBithday());

		Person outputThirtenn = outputList.get(13);
		assertEquals(Long.valueOf(13L), outputThirtenn.getId());
		assertEquals("First Name Teste13", outputThirtenn.getFirstName());
		assertEquals("Last Name Teste13", outputThirtenn.getLastName());
		assertEquals("Female", outputThirtenn.getGender());
		assertEquals(LocalDate.now().plusDays(13), outputThirtenn.getBithday());
		
	}
	
}
