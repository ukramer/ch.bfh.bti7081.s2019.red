package ch.bfh.red.test.tests.crud;

import ch.bfh.red.backend.models.AcademicTitle;

public class AcademicTitleCrudTest extends CrudTest<AcademicTitle> {

	@Override
	protected AcademicTitle createInstance() {
		return new AcademicTitle("Dr", "");
	}

	@Override
	protected Integer getId(AcademicTitle instance) {
		return instance.getId();
	}

	@Override
	protected void setAnUpdateValue(AcademicTitle instance) {
		instance.setPrefix("Dr. med.");
	}
	
}
