package br.com.wellmmjr.services.v2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wellmmjr.converter.DozerConverter;
import br.com.wellmmjr.converter.custom.PersonConverter;
import br.com.wellmmjr.data.model.Person;
import br.com.wellmmjr.data.vo.v2.PersonVOV2;
import br.com.wellmmjr.exception.ResourceNotFoundException;
import br.com.wellmmjr.repository.PersonRepository;

@Service
public class PersonServicesV2 {

	
	@Autowired
	PersonRepository repository;
	
	@Autowired
	private PersonConverter personConverter;
	
	public PersonVOV2 createPerson(PersonVOV2 person) {
		
		var entity = personConverter.convertVOV2ToEntity(person);
		repository.save(entity);
		var entityVO = personConverter.convertEntityToVOV2(entity);
		
		return entityVO;
	}
	
	public PersonVOV2 updatePerson(PersonVOV2 person) {
		var entity = repository.findById(person.getId()). 
				orElseThrow(() -> new ResourceNotFoundException("no records found for this id")) ;

		entity.setAddress(person.getAddress());
		entity.setFirstName(person.getFirstName());
		entity.setSecondName(person.getSecondName());
		entity.setGender(person.getGender());
		
		return DozerConverter.parseObject(repository.save(entity), PersonVOV2.class);
	}
	
	public void deletePerson(Long id) {
		Person entity = repository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("no records found for this id")) ;
		repository.delete(entity);
	}
	
	public PersonVOV2 findById(Long id) {

		var entity = repository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("no records found for this id")) ;
		return DozerConverter.parseObject(entity, PersonVOV2.class);
	}
	
	
	public List<PersonVOV2> findAll() {
		
		return DozerConverter.parseListObjects(repository.findAll(), PersonVOV2.class);
	}
}
