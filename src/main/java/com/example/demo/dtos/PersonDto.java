package com.example.demo.dtos;

public class PersonDto {

	private long idPerson;
	private String name;
	private String speciality;
	// private double salary;

	public PersonDto() {
		super();
	}

	public PersonDto(String name, String speciality) {
		super();
		this.name = name;
		this.speciality = speciality;
		// this.salary = salary;
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
	/*
	 * public double getSalary() { return salary; } public void setSalary(double
	 * salary) { this.salary = salary; }
	 */

}
