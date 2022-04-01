package com.example.demo.servicesImpls;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entities.Person;
import com.example.demo.repositories.PersonRepository;
import com.example.demo.services.PersonServices;

@Service
public class PersonServiceImp implements PersonServices {
	@Autowired
	PersonRepository personRepository;

	@Override
	public Person addPerson(Person porson) {

		return personRepository.save(porson);
	}

	@Override
	public List<Person> getList() {
		return (List<Person>) personRepository.findAll();
	}

	@Override
	public void deleteById(Long id) {
		 personRepository.deleteById(id);
	}

	@Override
	public Person updatePerson(Person personRequest) {
		Person p = personRepository.getById(personRequest.getIdPerson());
		p.setName(personRequest.getName());
		p.setSalary(personRequest.getSalary());
		p.setSpeciality(personRequest.getSpeciality());

		return personRepository.save(p);
	}

	@Override
	public Optional<Person> getById(long id) {
		Optional<Person> p = personRepository.findById(id);
		return p;
		
	}

	@Override
	public List<Person> findByName(String name) {
		List<Person> list = personRepository.findByName(name);
		return list;
	}

	@Override
	public List<Person> findByNameAndSpeciality(String name, String speciality) {
		
		return personRepository.findByNameAndSpeciality(name, speciality);
	}
	

}
