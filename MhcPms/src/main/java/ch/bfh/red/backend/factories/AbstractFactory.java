package ch.bfh.red.backend.factories;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFactory<T> {
	
	public abstract T create();
	
	public List<T> create(int count) {
    	ArrayList<T> entities = new ArrayList<>();
        for (int i = 0; i < count; i++)
            entities.add(create());
        return entities;
    }
	
}
