package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "person")
public class Person {
	/**
	 * ID of Person
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idPerson;
	/**
	 * Name of person
	 */
	private String name;
	private String speciality;
	private double salary;
	
	public Person() {
		super();
	}

	public Person(String name, String speciality, double salary) {
		super();
		this.name = name;
		this.speciality = speciality;
		this.salary = salary;
	}

	public long getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(long idPerson) {
		this.idPerson = idPerson;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	
	

	

}
