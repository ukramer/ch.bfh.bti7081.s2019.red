package ch.bfh.red.test.tests.factory;

import java.util.List;

import org.junit.Test;

import ch.bfh.red.backend.factories.AbstractFactory;

public abstract class FactoryTest<T> {
	private final AbstractFactory<T> factory;
	
	public FactoryTest(AbstractFactory<T> factory) {
		this.factory = factory;
	}
	
	@Test
	public void test() {
		List<T> items = factory.create(100);
		
		for (T item: items)
			System.out.println(item);
	}
	
}
