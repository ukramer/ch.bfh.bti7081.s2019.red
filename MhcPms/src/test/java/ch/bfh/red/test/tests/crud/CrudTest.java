package ch.bfh.red.test.tests.crud;

import org.junit.Before;
import org.junit.Test;
import ch.bfh.red.backend.services.IService;
import ch.bfh.red.test.tests.StartupTest;

public abstract class CrudTest<T> extends StartupTest {
	private Exception createCheck = null;
	private Exception readCheck = null;
	private Exception updateCheck = null;
	private Exception deleteCheck = null;
	
	protected abstract T createInstance();
	
	protected abstract Integer getId(T instance);
	
	protected abstract void setAnUpdateValue(T instance);
	
	protected abstract IService<T> getService();
	
	@Before
	public void before() {
		T instance = null;
		T instance2;
		
		// Create check
		try {
			instance = createInstance();
			instance2 = getService().persist(instance);
		} catch (Exception e) {
			createCheck = e;
		}
		
		// Read check
		try {
			instance2 = getService().getById(getId(instance));
			instance = instance2;
			final T readInstance1 = instance;
			final T readInstance2 = instance2;
			if (!readInstance1.equals(readInstance2)) {
				String instances = String.format("memoryInstance=%s, dbInstance=%s",
						readInstance1.toString(), readInstance2.toString());
				readCheck = new Exception(
						"Read instance is not equal to the instance in memory: "
								+ instances);
			}
		} catch (Exception e) {
			readCheck = e;
		}
		
		// Update check
		try {
			setAnUpdateValue(instance);
			instance2 = getService().persist(instance);
			final T updateInstance1 = instance;
			final T updateInstance2 = instance2;
			if (!updateInstance1.equals(updateInstance2)) {
				String instances = String.format("memoryInstance=%s, dbInstance=%s",
						updateInstance1.toString(), updateInstance2.toString());
				updateCheck = new Exception(
						"Updated value could not persisted: " + instances);
			}
		} catch (Exception e) {
			updateCheck = e;
		}
		
		// Delete check
		try {
			final Integer id = getId(instance);
			getService().delete(id);
			if (getService().existById(id)) {
				deleteCheck = new Exception("Item is still persisted: memoryInstance"
						+ instance.toString());
			}
		} catch (Exception e) {
			deleteCheck = e;
		}
		
	}
	
	@Test
	public void create() throws Exception {
		assertException(createCheck);
	}
	
	@Test
	public void read() throws Exception {
		assertException(readCheck);
	}
	
	@Test
	public void update() throws Exception {
		assertException(updateCheck);
	}
	
	@Test
	public void delete() throws Exception {
		assertException(deleteCheck);
	}
	
	private void assertException(Exception e) throws Exception {
		if (e != null)
			throw e;
	}
	
}
