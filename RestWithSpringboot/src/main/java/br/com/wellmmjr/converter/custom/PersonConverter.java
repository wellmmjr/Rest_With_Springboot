package br.com.wellmmjr.converter.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.wellmmjr.data.model.Person;
import br.com.wellmmjr.data.vo.v2.PersonVOV2;

@Service
public class PersonConverter {

	public PersonVOV2 convertEntityToVOV2(Person person) {
		
		PersonVOV2 personVO = new PersonVOV2();
		
		personVO.setId(person.getId());
		personVO.setFirstName(person.getFirstName());
		personVO.setSecondName(person.getSecondName());
		personVO.setGender(person.getGender());
		personVO.setAddress(person.getAddress());
		personVO.setBirthDate(new Date()); // esta conversão se dá por ex. numa busca que viesse do banco(entity) para o client, como a alteção não ocorreu no banco, deixemos new Date() (pois não virá esse campo do banco para fazermos person.getBithDate())
		
		return personVO;
		
	}
	
	public Person convertVOV2ToEntity(PersonVOV2 person) {
			
			Person entity = new Person();
			
			entity.setId(person.getId());
			entity.setFirstName(person.getFirstName());
			entity.setSecondName(person.getSecondName());
			entity.setGender(person.getGender());
			entity.setAddress(person.getAddress());

			return entity;
			
		}
}
