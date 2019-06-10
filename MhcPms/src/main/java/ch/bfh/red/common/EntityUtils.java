package ch.bfh.red.common;

import java.util.ArrayList;
import java.util.Collection;

import ch.bfh.red.backend.models.ExpositionNote;
import ch.bfh.red.backend.models.GroupSession;
import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.models.PatientNote;
import ch.bfh.red.backend.models.SingleSession;
import ch.bfh.red.backend.models.Therapist;
import ch.bfh.red.backend.models.TherapistNote;
import ch.bfh.red.backend.models.Therapy;

public class EntityUtils {
	
	public static Collection<Class<?>> getEntityClasses() {
		Collection<Class<?>> entityClasses = new ArrayList<>();
		entityClasses.add(Patient.class);
		entityClasses.add(Therapist.class);
		entityClasses.add(PatientNote.class);
		entityClasses.add(TherapistNote.class);
		entityClasses.add(ExpositionNote.class);
		entityClasses.add(SingleSession.class);
		entityClasses.add(GroupSession.class);
		entityClasses.add(Therapy.class);
		return entityClasses;
	}
	
}
