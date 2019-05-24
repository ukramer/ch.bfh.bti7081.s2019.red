package ch.bfh.red.test.tests.crud;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import ch.bfh.red.backend.services.IService;
import ch.bfh.red.test.tests.StartupTest;

@FixMethodOrder(MethodSorters.DEFAULT)
public abstract class CrudTest<T> extends StartupTest {
    
    @Autowired
    protected CrudRepository<T, Integer> repository;
    
    @Autowired
    protected IService<T> service;
    
    private Runnable initCheck = createInitAssertion();
    private Runnable createCheck = createInitAssertion();
    private Runnable readCheck = createInitAssertion();
    private Runnable updateCheck = createInitAssertion();
    private Runnable deleteCheck = createInitAssertion();
    
    protected abstract T createInstance();
    
    protected abstract Integer getId(T instance);
    
    protected abstract void setAnUpdateValue(T instance);
    
    @Before
    public void before() {
        try {
            // Create check
            T instance = createInstance();
            instance = service.add(instance);
            final T createInstance = instance;
            createCheck = () -> assertNotNull(createInstance);
            
            // Read check
            T instance2 = service.getById(getId(instance));
            final T readInstance1 = instance;
            final T readInstance2 = instance2;
            readCheck = () -> assertEquals(readInstance1, readInstance2);
            instance = instance2;
            
            // Update check
            setAnUpdateValue(instance);
            instance2 = service.update(instance);
            final T updateInstance1 = instance;
            final T updateInstance2 = instance2;
            updateCheck = () -> assertEquals(updateInstance1, updateInstance2);
            
            // Delete check
            final Integer id = getId(instance);
            service.delete(id);
            deleteCheck = () -> assertEquals(service.existById(id), false);
            initCheck = () -> {};
        } catch (Exception e) {
            initCheck = () -> {
                throw e;
            };
        }
        
    }
    
    @Test
    public void init() {
        initCheck.run();
    }
    
    @Test
    public void create() {
        createCheck.run();
    }
    
    @Test
    public void read() {
        readCheck.run();
    }
    
    @Test
    public void update() {
        updateCheck.run();
    }
    
    @Test
    public void delete() {
        deleteCheck.run();
    }
    
    private Runnable createInitAssertion() {
        return () -> {
            throw new NullPointerException("Test case could not executed.");
        };
    }
    
}
