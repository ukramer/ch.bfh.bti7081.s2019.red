package ch.bfh.red.test.tests.crud;

import static org.junit.Assert.assertEquals;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import ch.bfh.red.backend.services.IService;
import ch.bfh.red.test.tests.StartupTest;

import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.DEFAULT)
public abstract class CrudTest<T> extends StartupTest {
	
	@Autowired
	protected CrudRepository<T, Integer> addressRepository;
	
	@Autowired
	protected IService<T> service;
	
	protected abstract T createInstance();
	
	protected abstract Integer getId(T instance);
	
	protected abstract void setAnUpdateValue(T instance);
	
	private T instance;
	
	@Test
    public void create() {
		T instance = createInstance();
		service.add(instance);
    }
	
	@Test
	public void read() {
    	T instance2 = service.getById(getId(instance));
    	assertEquals(instance, instance2);
	}
	
	@Test
	public void update() {
		setAnUpdateValue(instance);
    	service.update(instance);
    	T instance2 = service.getById(getId(instance));
    	assertEquals(instance, instance2);
	}
	
	@Test
	public void delete() {
		Integer id = getId(instance);
		service.delete(id);
		assertEquals(service.existById(id),false);
	}
	
}
