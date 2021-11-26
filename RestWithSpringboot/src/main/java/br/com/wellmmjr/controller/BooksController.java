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

import br.com.wellmmjr.data.vo.v1.BooksVO;
import br.com.wellmmjr.services.BooksServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="Books Endpoints", description = "Accepts and provides entity Books' xml, x-yaml, json contents", 
tags= {"Books Endpoints", "Book"})
@RestController
@RequestMapping("/api/book/v1")
public class BooksController {
	
	@Autowired
	private BooksServices services;
	

	@ApiOperation(value="Allows create Book by this endpoint")
	@PostMapping(value = "/create", produces = {"application/json", "application/xml", "application/x-yaml"}, 
			consumes = {"application/json", "application/xml", "application/x-yaml"})
	public BooksVO create(@RequestBody BooksVO book){
		
		BooksVO bookVO = services.createBooks(book);
		bookVO.add(linkTo(methodOn(BooksController.class).findById(bookVO.getKey())).withSelfRel());
		return bookVO;
		
	}
	
	@ApiOperation(value="Allows update Book by id through endpoint")
	@PutMapping(value = "/update", produces = {"application/json", "application/xml", "application/x-yaml"}, 
			consumes = {"application/json", "application/xml", "application/x-yaml"})
	public BooksVO update(@RequestBody BooksVO book){
		
		BooksVO bookVO = services.updateBooks(book);
		bookVO.add(linkTo(methodOn(BooksController.class).findById(bookVO.getKey())).withSelfRel());
		return bookVO;
		
	}
	
	@ApiOperation(value="Deletes Books by id")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id" ) Long id){
		
		services.deleteBooks(id);
		return ResponseEntity.ok().build();
		
	}
	
	@ApiOperation(value="Deliver a list containing all Books created")
	@GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
	public List<BooksVO> findAll(){
		List<BooksVO> bookVO = services.findAll();
		
		bookVO.stream().forEach(p ->p.add (
				linkTo(methodOn(BooksController.class).findById(p.getKey())).withSelfRel())
		);
		
		return bookVO;
		
	}

	@ApiOperation(value="Loads Books' details info by id")
	@GetMapping(value="/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
	public BooksVO findById(@PathVariable("id" ) Long id){
		
		BooksVO bookVO = services.findById(id);
		bookVO.add(linkTo(methodOn(BooksController.class).findById(id)).withSelfRel());
		return bookVO;
		
	}

}
