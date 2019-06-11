package ch.bfh.red.common;

import java.util.Collection;
import java.util.function.Function;

public class DTOutils {
	
	public static <T, U> boolean equalsOrNull(T t1, T t2, Function<T, U> mapper) {
		if (t1 == null)
			throw new NullPointerException("t1 is null");
		if (t2 == null)
			throw new NullPointerException("t2 is null");
		U u1 = mapper.apply(t1);
		U u2 = mapper.apply(t2);
		if (u1 != null && u2 != null && !u1.equals(u2))
			return false;
		return true;
	}
	
	public static <T> boolean equalsCollection(Collection<T> coll1, Collection<T> coll2) {
		if (coll1.size() != coll2.size())
			return false;
		for (T t: coll1)
			if (!coll2.contains(t))
				return false;
		return true;
	}
	
	public static <T, U extends Collection<V>, V> boolean equalsOrNullCollection(T t1, T t2, Function<T, U> mapper) {
		if (t1 == null)
			throw new NullPointerException("t1 is null");
		if (t2 == null)
			throw new NullPointerException("t2 is null");
		U u1 = mapper.apply(t1);
		U u2 = mapper.apply(t2);
		if (u1 != null && u2 != null && !equalsCollection(u1, u2))
			return false;
		return true;
	}
	
}
