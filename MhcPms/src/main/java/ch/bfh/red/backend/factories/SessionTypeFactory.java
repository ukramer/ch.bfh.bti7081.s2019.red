package ch.bfh.red.backend.factories;

import java.util.Random;

import ch.bfh.red.backend.models.SessionType;

public class SessionTypeFactory extends AbstractFactory<SessionType> {
	Random random = new Random();
	
	@Override
	public SessionType create() {
		return SessionType.values()[random.nextInt(SessionType.values().length)];
	}
	
}
