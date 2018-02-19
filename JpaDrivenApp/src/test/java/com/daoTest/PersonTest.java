package com.daoTest;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import junit.framework.Assert;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.config.AppConfig;
import com.model.Person;
import com.service.PersonService;

@RunWith(value=SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=AppConfig.class)
public class PersonTest {

	@Autowired
	private PersonService personService;
	
	@Test
	public void saveaPerson(){
		Person p = new Person();
		p.setFullName("s");
		p.setName("gsj");
		p.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
		personService.save(p);
		System.out.println("id => "+p.getId());
		//Assert.assertEquals(1, p.getId());
	}
	
	@Test
	public void testFetchAll(){
		List<Person> pList = personService.fetchAll();
		System.out.println("pList =>"+pList.size());
		Assert.assertNotNull(pList);
	}
	
	@Test
	public void deletePerson(){
		personService.delete(2L);
	}
}
