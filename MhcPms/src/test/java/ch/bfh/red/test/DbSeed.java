package ch.bfh.red.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ch.bfh.red.backend.seeders.DbSeeder;

public class DbSeed extends StartupTest {
	
	@Autowired
	private DbSeeder dbSeeder;
	
	@Test
	public void seed() {
		dbSeeder.seed(150);
	}
	
}
