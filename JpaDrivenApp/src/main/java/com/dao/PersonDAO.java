package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.model.Person;

@Repository
@Transactional
public class PersonDAO {

	@PersistenceContext
	private EntityManager em;
	
	public Long add(Person person){
		em.persist(person);
		return person.getId();
	}
	
	public void delete(Long id){
		Person p = em.find(Person.class,id);
		if(p == null)
			throw new IllegalArgumentException("Person Not Found");
		em.remove(p);
	}
	
	public void update(Person p){
		em.merge(p);
	}
	
	public List<Person> fetchAll(){
	 CriteriaQuery<Person> qry = em.getCriteriaBuilder().createQuery(Person.class);
	 @SuppressWarnings("unused")
	 Root<Person> personList = qry.from(Person.class);
	 return em.createQuery(qry).getResultList();
	}
}
