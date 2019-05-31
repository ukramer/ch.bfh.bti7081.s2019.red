package ch.bfh.red.common;

import java.lang.reflect.Constructor;

import org.springframework.beans.BeanInstantiationException;

public class BeanUtils {
	
	public static void checkBeanInstantiation(StackTraceElement[] stackTrace, Class<?> cls) {
		boolean springInstantiation = false;
		for (StackTraceElement element: stackTrace) {
			try {
				if (Constructor.class.equals(Class.forName(element.getClassName())))
					springInstantiation = true;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		if (!springInstantiation)
			throw new BeanInstantiationException(cls, "Public bean constructor was called. The class has to be instatiated with @Autowired");
		
	}
	
}
