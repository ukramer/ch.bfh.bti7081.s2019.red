package ch.bfh.red.test.beans;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import ch.bfh.red.backend.services.IService;
import ch.bfh.red.test.StartupTest;

public abstract class BeanTest<T> extends StartupTest {

	@Autowired
	protected CrudRepository<T, Integer> repository;

	@Autowired
	protected IService<T> service;
	
	@Test
	public void testAutowiringProcess() {
		assertNotNull(repository);
		assertNotNull(service);
	}

}