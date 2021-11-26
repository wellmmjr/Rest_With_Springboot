package br.com.wellmmjr.controller;

import java.util.List;

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

import br.com.wellmmjr.data.vo.v1.PersonVO;
import br.com.wellmmjr.services.PersonServices;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {
	
	@Autowired
	private PersonServices services;
	
//	@RequestMapping(value="/create", method = RequestMethod.POST, 
//			produces= MediaType.APPLICATION_JSON_VALUE, 
//			consumes= MediaType.APPLICATION_JSON_VALUE) <--- anotações utilizadas em versões anteriores do springboot para com seus verbos
	@PostMapping(value = "/create")
	public PersonVO create(@RequestBody PersonVO person){
		
		return services.createPerson(person);
		
	}
	
//	@RequestMapping(value="/update", method = RequestMethod.PUT, 
//			produces= MediaType.APPLICATION_JSON_VALUE, 
//			consumes= MediaType.APPLICATION_JSON_VALUE)
	@PutMapping(value = "/update")
	public PersonVO update(@RequestBody PersonVO person){
		
		return services.updatePerson(person);
		
	}
	
//	@RequestMapping(value="/delete/{id}", 
//			method = RequestMethod.DELETE) 
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id" ) Long id){
		
		services.deletePerson(id);
		return ResponseEntity.ok().build();
		
	}
	
//	@RequestMapping(method = RequestMethod.GET, 
//			produces= MediaType.APPLICATION_JSON_VALUE)
	@GetMapping
	public List<PersonVO> findAll(){
		
		return services.findAll();
		
	}

//	@RequestMapping(value="/{id}", method = RequestMethod.GET, 
//			produces= MediaType.APPLICATION_JSON_VALUE)
	@GetMapping("/{id}")
	public PersonVO findById(@PathVariable("id" ) Long id){
		
		return services.findById(id);
		
	}

}
