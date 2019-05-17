package ch.bfh.red.test.tests.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import ch.bfh.red.backend.services.IService;
import ch.bfh.red.test.tests.StartupTest;

public class BeanTest<T> extends StartupTest {
	
	@Autowired
	protected CrudRepository<T, Integer> repository;
	
	@Autowired
	protected IService<T> service;
	
}