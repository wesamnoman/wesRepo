package com.example.demo.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dtos.PersonDto;
import com.example.demo.entities.Person;
import com.example.demo.services.PersonServices;

@RestController
@RequestMapping("person")
@CrossOrigin(origins ="[localhost:8080]")
public class PersonController {
	
	@Autowired
	PersonServices personService;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	RestTemplate restTemplate;
	@Value("${api.getPerson}")
	String url;

	@PostMapping("/addPerson")
	public ResponseEntity<PersonDto> addPerson(@RequestBody PersonDto p) {
		// convert to entity
		Person personRequest = modelMapper.map(p, Person.class);
		// body de response
		Person person = personService.addPerson(personRequest);
		// convert to dto
		PersonDto personResponse = modelMapper.map(person, PersonDto.class);
		// headers de response
		HttpHeaders headers = new HttpHeaders();
		headers.add("Descriptions", "adding Person");
		return new ResponseEntity<PersonDto>(personResponse, headers, HttpStatus.CREATED);
	}

	@GetMapping("/getPerson")
	public ResponseEntity<Stream<PersonDto>> getList() {
		// headers of response
		HttpHeaders headers = new HttpHeaders();
		headers.add("Descriptaions", "list of person");
		// body of response
		Stream<PersonDto> list = personService.getList().stream()
				.map(person -> modelMapper.map(person, PersonDto.class));
		return new ResponseEntity<Stream<PersonDto>>(list, headers, HttpStatus.FOUND);
	}

	@DeleteMapping("/deletePerson/{id}")
	public void deletePerson(@PathVariable long id) {
		personService.deleteById(id);
	}

	@PutMapping("/updatePerson")
	public ResponseEntity<PersonDto> updatePerson(@RequestBody PersonDto personDto) {
		// convert to Entity
		Person personRequest = modelMapper.map(personDto, Person.class);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Descriptaions", "Update a person");
		Person person = personService.updatePerson(personRequest);
		// convert to dto
		PersonDto personResponse = modelMapper.map(person, PersonDto.class);

		return new ResponseEntity<PersonDto>(personResponse, headers, HttpStatus.CREATED);

	}

	@GetMapping("search/{keyword}")
	public Stream<PersonDto> searchPerson(@PathVariable String keyword) {
		List<Person> s = personService.findByName(keyword);
		if (s.isEmpty()) {
			System.out.println("Name is not existed");

		}

		return s.stream().map(person -> modelMapper.map(person, PersonDto.class));

	}

	@GetMapping("/getById/{id}")
	public Optional<PersonDto> getById(@PathVariable long id) {
		Optional<Person> p = personService.getById(id);
		if (p.isEmpty()) {

			System.out.println("Id is not correct");
		}
		return p.map(person -> modelMapper.map(person, PersonDto.class));

	}

	@GetMapping("findAll/{name}/{speciality}")
	public Stream<PersonDto> findAll(@PathVariable String name, @PathVariable String speciality) {
		List<Person> s = personService.findByNameAndSpeciality(name, speciality);
		if (s.isEmpty()) {
			System.out.println("Name is not existed");

		}

		return s.stream().map(person -> modelMapper.map(person, PersonDto.class));

	}

	/**
	 * call to another api and get by Id
	 */

	@GetMapping("/getApi/{id}")
	public ResponseEntity<String> getApi(@PathVariable long id) {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
		ResponseEntity<String> s = restTemplate.exchange(url + "/" + id, HttpMethod.GET, entity, String.class);
		return s;

	}

	/**
	 * call to another api and get by Id
	 */

	@GetMapping("/getApi")
	public ResponseEntity<String> getApi() {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
		ResponseEntity<String> s = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		return s;

	}

	@GetMapping(value = "/download")
	public ResponseEntity<Object> downloadFile() throws IOException {
		String filename = "http://www.africau.edu/images/default/sample.pdf";
		File file = new File(filename);
		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
		HttpHeaders headers = new HttpHeaders();

		headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");

		ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers).contentLength(file.length())
				.contentType(MediaType.parseMediaType("application/txt")).body(resource);

		return responseEntity;
	}

}
