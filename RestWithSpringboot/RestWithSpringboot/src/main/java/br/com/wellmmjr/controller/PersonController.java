package br.com.wellmmjr.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@Autowired
	private PagedResourcesAssembler<PersonVO> avengeeers_assemble_yeeeee;
	
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
	
	@CrossOrigin(origins= {"https://www.google.com"}) 
	@ApiOperation(value="Deliver a list with all Person in database")
	@GetMapping(value="/findAllByName/{name}",
			produces = {"application/json", "application/xml", "application/x-yaml"})
	public ResponseEntity<?> findAllByName(
			@PathVariable(value="name") String name,
			@RequestParam(value="page", defaultValue="0") int page, 
			@RequestParam(value="limit", defaultValue="5") int limit, 
			@RequestParam(value="sort", defaultValue="asc") String sort){
		
		var sortDirection = "desc".equalsIgnoreCase(sort) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName"));
		Page<PersonVO> persons = services.findAllByName(name, pageable);
		
		persons.stream().forEach(p ->p.add (
				linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel())
				);
		
		PagedResources<?> resources =  avengeeers_assemble_yeeeee.toResource(persons);
		
		return new ResponseEntity<>(resources, HttpStatus.OK);
		
	}
	
	@CrossOrigin(origins= {"https://www.google.com"}) 
	@ApiOperation(value="Deliver a list with all Person in database")
	@GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
	public ResponseEntity<?> findAll(
			@RequestParam(value="page", defaultValue="0") int page, 
			@RequestParam(value="limit", defaultValue="5") int limit, 
			@RequestParam(value="sort", defaultValue="asc") String sort){
		
		var sortDirection = "desc".equalsIgnoreCase(sort) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName"));
		
		Page<PersonVO> persons = services.findAll(pageable);
		
		persons.stream().forEach(p ->p.add (
				linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel())
		);
		
		PagedResources<?> resources =  avengeeers_assemble_yeeeee.toResource(persons);
		
		return new ResponseEntity<>(resources, HttpStatus.OK);
		
	}

	@ApiOperation(value="Loads Person's info by its id")
	@GetMapping(value="/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
	public PersonVO findById(@PathVariable("id" ) Long id){
		
		PersonVO personVO = services.findById(id);
		personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return personVO;
		
	}
	
	@ApiOperation(value="Disables Persons by its id")
	@PatchMapping(value="/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
	public ResponseEntity<?> disabledPerson(@PathVariable("id" ) Long id){
		
		services.disablePerson(id);
		return ResponseEntity.ok().build();
		
	}

}
