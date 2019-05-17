package ch.bfh.red.test.tests.crud;

import ch.bfh.red.backend.models.Visibility;

public class VisibilityCrudTest extends CrudTest<Visibility> {

	@Override
	protected Visibility createInstance() {
		return new Visibility("Private", "Private");
	}

	@Override
	protected Integer getId(Visibility instance) {
		throw new RuntimeException();
	}

	@Override
	protected void setAnUpdateValue(Visibility instance) {
		instance.setName("Protected");
	}
	
}
