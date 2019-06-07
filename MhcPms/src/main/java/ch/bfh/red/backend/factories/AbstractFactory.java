package ch.bfh.red.backend.factories;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public abstract class AbstractFactory<T> {
	
    static {
        Locale.setDefault(new Locale("de-CH"));
    }
    
	public abstract T create();
	
	public List<T> create(int count) {
    	ArrayList<T> entities = new ArrayList<>();
        for (int i = 0; i < count; i++)
            entities.add(create());
        return entities;
    }
	
}
