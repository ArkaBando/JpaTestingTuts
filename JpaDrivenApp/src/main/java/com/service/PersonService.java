package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.PersonDAO;
import com.model.Person;

@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Service
public class PersonService {
	
	@Autowired
	PersonDAO personDAO;
	
	@Transactional
	public Long save(Person p){
		return personDAO.add(p);
	}
	
	@Transactional(readOnly=true)
	public List<Person> fetchAll(){
		return personDAO.fetchAll();
	}
	
	@Transactional
	public void update(Person p){
		personDAO.update(p);
	}
	
	@Transactional
	public void delete(Long id){
		personDAO.delete(id);
	}
}
