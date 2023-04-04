package br.com.lutfi.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lutfi.controller.PersonController;
import br.com.lutfi.data.vo.v1.PersonVO;
import br.com.lutfi.data.vo.v2.PersonVOV2;
import br.com.lutfi.exceptions.RequiredObjectIsNullException;
import br.com.lutfi.exceptions.ResourceNotFoundException;
import br.com.lutfi.mapper.DozerMapper;
import br.com.lutfi.mapper.PersonMapper;
import br.com.lutfi.model.Person;
import br.com.lutfi.repository.PersonRepository;

@Service
public class PersonServices {

	@Autowired
	PersonRepository personRepository;

	@Autowired
	PersonMapper mapper;

	private Logger logger = Logger.getLogger(PersonServices.class.getName());

	public List<PersonVO> findAll() {

		logger.info("Finding all people!");

		var persons = DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);
		persons.stream()
				.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
		return persons;
	}

	public PersonVO findById(Long id) {

		logger.info("Finding one person!");

		PersonVO person = new PersonVO();

		person.setFirstName("Leandro");
		person.setLastName("Costa");
		person.setAddress("Uberlândia - Minas Gerais - Brasil");
		person.setGender("Male");
		var entity = personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("no record found for this ID"));
		var vo = DozerMapper.parseObject(entity, PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return vo;
	}

	public PersonVO create(PersonVO person) {
		if (person == null)
			throw new RequiredObjectIsNullException();

		logger.info("Creating one person!");
		var entity = DozerMapper.parseObject(person, Person.class);
		var vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}

	public PersonVOV2 createV2(PersonVOV2 person) {

		logger.info("Creating one person with V2!");
		var entity = mapper.convertVoTOEntity(person);
		var vo = mapper.convertEntityToVo(personRepository.save(entity));
		return vo;
	}

	public PersonVO update(PersonVO person) {
		if (person == null)
			throw new RequiredObjectIsNullException();
		logger.info("Updating one person!");

		var entity = personRepository.findById(person.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());

		var vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}

	public void delete(Long id) {

		logger.info("Deleting one person!");

		var entity = personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		personRepository.delete(entity);
	}

}
