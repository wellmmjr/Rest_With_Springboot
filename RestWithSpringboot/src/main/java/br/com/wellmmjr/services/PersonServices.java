package br.com.wellmmjr.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.wellmmjr.converter.DozerConverter;
import br.com.wellmmjr.data.model.Person;
import br.com.wellmmjr.data.vo.v1.PersonVO;
import br.com.wellmmjr.exception.ResourceNotFoundException;
import br.com.wellmmjr.repository.PersonRepository;

@Service
public class PersonServices {

	@Autowired
	PersonRepository repository;
	
	public PersonVO createPerson(PersonVO person) {
		
		var entity = DozerConverter.parseObject(person, Person.class);
		repository.save(entity);
		var entityVO = DozerConverter.parseObject(entity, PersonVO.class);
		
//		var entityVO = DozerConverter.parseObject(repository.save(entity), PersonVO.class); <-- maneira alternativa de realizar a conversão já salvando
		
		return entityVO;
	}
	
	public PersonVO updatePerson(PersonVO person) {
		var entity = repository.findById(person.getKey()). // atenção: este findById retorna um Person, visto que é da classe PersonRepository e ñ metodo desta propria
				orElseThrow(() -> new ResourceNotFoundException("no records found for this id")) ;

		entity.setAddress(person.getAddress());
		entity.setFirstName(person.getFirstName());
		entity.setSecondName(person.getSecondName());
		entity.setGender(person.getGender());
		
		return DozerConverter.parseObject(repository.save(entity), PersonVO.class);
	}

	@Transactional //notação para garantir qualidade ACID dos dados
	public void disablePerson(Long id) {
		repository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("no records found for this id")) ;
		
		repository.disablePersons(id);
	}
	
	public void deletePerson(Long id) {
		Person entity = repository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("no records found for this id")) ;
		repository.delete(entity);
	}
	
	public PersonVO findById(Long id) {

		var entity = repository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("no records found for this id")) ;
		return DozerConverter.parseObject(entity, PersonVO.class);
	}
	
	
	public List<PersonVO> findAll() {
		
		return DozerConverter.parseListObjects(repository.findAll(), PersonVO.class);
	}
	
}
