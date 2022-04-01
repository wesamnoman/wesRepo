package com.example.demo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entities.Person;
import com.example.demo.repositories.PersonRepository;



@Configuration
public class ConfigurationClass {
	
	@Value("${my.profile}")
	public String profileState;
	
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	public RestTemplate restTamplate() {
		return new RestTemplate();
	}
	
	@Bean
	public void readEnvironment () {
		
		System.out.println("Profile status is "+this.profileState);
		
	}
	
	

}
