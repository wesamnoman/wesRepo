package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.entities.Person;

public interface PersonServices {

	public Person addPerson(Person porson);

	public List<Person> getList();

	public void deleteById(Long id);

	public Person updatePerson(Person person);

	public Optional<Person> getById(long id);

	public List<Person> findByName(String name);

	List<Person> findByNameAndSpeciality(String name, String speciality);

}
