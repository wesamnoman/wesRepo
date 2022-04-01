package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entities.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
	
	List<Person> findByName(String name);
	List<Person>findByNameAndSpeciality(String name, String speciality);
	

}
