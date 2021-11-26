package br.com.wellmmjr.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="Person Endpoints", description = "Accepts and provides entity Person's xml, x-yaml, json contents", 
tags= {"Person Endpoints", "Person"})
@RestController
@RequestMapping("/api/person/v1")
public class PersonController {
	
	@Autowired
	private PersonServices services;
	

	@ApiOperation(value="Allows create Person by this endpoint")
	@PostMapping(value = "/create", produces = {"application/json", "application/xml", "application/x-yaml"}, 
			consumes = {"application/json", "application/xml", "application/x-yaml"})
	public PersonVO create(@RequestBody PersonVO person){
		
		PersonVO personVO = services.createPerson(person);
		personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());
		return personVO;
		
	}
	
	@ApiOperation(value="Allows update Person by id through endpoint")
	@PutMapping(value = "/update", produces = {"application/json", "application/xml", "application/x-yaml"}, 
			consumes = {"application/json", "application/xml", "application/x-yaml"})
	public PersonVO update(@RequestBody PersonVO person){
		
		PersonVO personVO = services.updatePerson(person);
		personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());
		return personVO;
		
	}
	
	@ApiOperation(value="Delete Person's info by its id")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id" ) Long id){
		
		services.deletePerson(id);
		return ResponseEntity.ok().build();
		
	}
	
	@ApiOperation(value="Deliver a list with all Person in database")
	@GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
	public List<PersonVO> findAll(){
		List<PersonVO> personVO = services.findAll();
		
		personVO.stream().forEach(p ->p.add (
				linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel())
		);
		
		return personVO;
		
	}

	@ApiOperation(value="Loads Person's info by its id")
	@GetMapping(value="/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
	public PersonVO findById(@PathVariable("id" ) Long id){
		
		PersonVO personVO = services.findById(id);
		personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return personVO;
		
	}

}
