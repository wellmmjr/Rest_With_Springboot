package br.com.wellmmjr.controller.v2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wellmmjr.data.vo.v2.PersonVOV2;
import br.com.wellmmjr.services.v2.PersonServicesV2;

@RestController
@RequestMapping("/person/v2")
public class PersonControllerV2{
	
	@Autowired
	private PersonServicesV2 services;
	
	public PersonVOV2 create(@RequestBody PersonVOV2 person){
		
		return services.createPerson(person);
		
	}
	
	@PutMapping(value = "/update")
	public PersonVOV2 update(@RequestBody PersonVOV2 person){
		
		return services.updatePerson(person);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id" ) Long id){
		
		services.deletePerson(id);
		return ResponseEntity.ok().build();
		
	}
	
	@GetMapping
	public List<PersonVOV2> findAll(){
		
		return services.findAll();
		
	}

	@GetMapping("/{id}")
	public PersonVOV2 findById(@PathVariable("id" ) Long id){
		
		return services.findById(id);
		
	}

}
