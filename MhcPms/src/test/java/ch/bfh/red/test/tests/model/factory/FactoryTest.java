package ch.bfh.red.test.tests.model.factory;

import java.util.List;

import ch.bfh.red.test.tests.StartupTest;
import org.junit.Test;

import ch.bfh.red.backend.factories.AbstractFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class FactoryTest<T>  extends StartupTest {

    protected AbstractFactory<T> factory;



    @Test
    public void test() {
        List<T> items = factory.create(100);

        for (T item : items)
            System.out.println(item);
    }

}
