package ch.bfh.red.backend.seeders;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.bfh.red.backend.factories.ExpositionNoteFactory;
import ch.bfh.red.backend.factories.GroupSessionFactory;
import ch.bfh.red.backend.factories.PatientFactory;
import ch.bfh.red.backend.factories.PatientNoteFactory;
import ch.bfh.red.backend.factories.SingleSessionFactory;
import ch.bfh.red.backend.factories.TherapistFactory;
import ch.bfh.red.backend.factories.TherapistNoteFactory;
import ch.bfh.red.backend.models.ExpositionNote;
import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.models.PatientNote;
import ch.bfh.red.backend.models.SingleSession;
import ch.bfh.red.backend.models.Therapist;
import ch.bfh.red.backend.models.TherapistNote;
import ch.bfh.red.backend.models.Therapy;
import ch.bfh.red.backend.models.TherapyType;
import ch.bfh.red.backend.persistence.TherapyPersistenceManager;

@Component
public class DbSeeder {
	private final PatientFactory patientFactory = new PatientFactory();
	private final TherapistFactory therapistFactory = new TherapistFactory();
	private final PatientNoteFactory patientNoteFactory = new PatientNoteFactory();
	private final TherapistNoteFactory therapistNoteFactory = new TherapistNoteFactory();
	private final ExpositionNoteFactory expositionNoteFactory = new ExpositionNoteFactory();
	private final SingleSessionFactory singleSessionFactory = new SingleSessionFactory();
	private final GroupSessionFactory groupSessionFactory = new GroupSessionFactory();
	
	@Autowired
	private TherapyPersistenceManager therapyManager;
	
	public void seed(int therapyCount) {
		final Collection<Patient> patients = patientFactory.create(therapyCount*2);
		final Collection<Therapist> therapists = therapistFactory.create(20);
		final List<Entry<Patient, Therapist>> pairs = createPairs(therapyCount, patients, therapists, 3);
		final Collection<Therapy> therapies = new ArrayList<>();
		
		for (int i=0; i<pairs.size(); i++) {
			Patient patient = pairs.get(i).getKey();
			Therapist therapist = pairs.get(i).getValue();
			
			Range<PatientNote> patientNoteRange = new Range<>(5, 20, PatientNote.class);
			Range<TherapistNote> therapistNoteRange = new Range<>(5, 20, TherapistNote.class);
			Range<ExpositionNote> expositionNoteRange = new Range<>(5, 20, ExpositionNote.class);
			Range<SingleSession> singleSessionRange = new Range<>(5, 20, SingleSession.class);
			
			Therapy therapy = createTherapy(new Date(), 
											TherapyType.PSYCHO, 
											patient, 
											therapist,
											patientNoteRange,
											therapistNoteRange,
											expositionNoteRange,
											singleSessionRange); 
			therapies.add(therapy);
		}
		
		therapyManager.persistAll(therapies);
	}
	
	private Therapy createTherapy(  Date startDate,
									TherapyType therapyType,
									Patient patient,
									Therapist therapist,
									Range<PatientNote> patientNoteRange,
									Range<TherapistNote> therapistNoteRange,
									Range<ExpositionNote> expositionNoteRange,
									Range<SingleSession> singleSessionRange) {
		Therapy therapy = new Therapy(startDate, therapyType, patient, therapist);
		List<PatientNote> patientNotes = createRanged(
				() -> patientNoteFactory.create(patient), patientNoteRange);
		List<TherapistNote> therapistNotes = createRanged(
				() -> therapistNoteFactory.create(therapist), therapistNoteRange);
		List<ExpositionNote> expositionNotes = createRanged(
				() -> expositionNoteFactory.create(patient), expositionNoteRange);
		List<SingleSession> singleSessions = createRanged(
				() -> singleSessionFactory.create(patient, therapist),
				singleSessionRange);
		therapy.setPatientNotes(patientNotes);
		therapy.setTherapistNotes(therapistNotes);
		therapy.setExpositionNotes(expositionNotes);
		therapy.setSingleSessions(singleSessions);
		return therapy;
	}
	
	private static List<Entry<Patient, Therapist>> createPairs(int pairCount,
	                                                                 Collection<Patient> patients,
	                                                                 Collection<Therapist> therapists,
	                                                                 int maxPatientCount) {
		List<Entry<Patient, Therapist>> pairs = new ArrayList<>();
		while (pairs.size() < pairCount) {
			Patient patient = getRandom(patients);
			Therapist therapist = getRandom(therapists);
			int patientCount = (int)pairs.stream().filter(entry -> entry.getKey().equals(patient)).count();
			if (patientCount < maxPatientCount)
				pairs.add(new AbstractMap.SimpleEntry<>(patient, therapist));
		}
		return pairs;
	}
	
	private static <T> T getRandom(Collection<T> collection) {
		int size = collection.size();
		int n = (int)(Math.random()*((double)size-1));
		Iterator<T> iterator = collection.iterator();
		T t = null;
		for (int i=0; i<=n; i++) {
			t = iterator.next();
		}
		return t;
	}
	
	private static <T> List<T> createRanged(Supplier<T> factory,
											Range<T> range) {
		double sub = range.getMax() - range.getMin();
		int n = (int) (Math.random() * sub) + range.getMin();
		List<T> items = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			items.add(factory.get());
		}
		return items;
	}
	
	private class Range<T> {
		private final Class<T> entityClass;
		private final int min;
		private final int max;
		
		public Range(int min, int max, Class<T> entityClass) {
			this.min = min;
			this.max = max;
			this.entityClass = entityClass;
		}
		
		public Class<T> getEntityClass() {
			return entityClass;
		}
		
		public int getMin() {
			return min;
		}
		
		public int getMax() {
			return max;
		}
		
	}
	
}
