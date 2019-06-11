package ch.bfh.red.backend.seeders;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.bfh.red.backend.factories.ExpositionNoteFactory;
import ch.bfh.red.backend.factories.GroupSessionFactory;
import ch.bfh.red.backend.factories.PatientFactory;
import ch.bfh.red.backend.factories.PatientNoteFactory;
import ch.bfh.red.backend.factories.SessionTypeFactory;
import ch.bfh.red.backend.factories.SingleSessionFactory;
import ch.bfh.red.backend.factories.TherapistFactory;
import ch.bfh.red.backend.factories.TherapistNoteFactory;
import ch.bfh.red.backend.factories.TherapyFactory;
import ch.bfh.red.backend.models.ExpositionNote;
import ch.bfh.red.backend.models.GroupSession;
import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.models.PatientNote;
import ch.bfh.red.backend.models.SessionType;
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
	private final TherapyFactory therapyFactory = new TherapyFactory();
	private final SessionTypeFactory sessionTypeFactory = new SessionTypeFactory();
	
	@Autowired
	private TherapyPersistenceManager therapyManager;
	
	public void seed(int therapyCount) {
		final Collection<Patient> patients = patientFactory.create(therapyCount * 2);
		final Collection<Therapist> therapists = therapistFactory.create(20);
		final Collection<Therapy> therapies = createTherapies(therapyCount, patients, therapists);
		final Collection<GroupSession> groupSessions = createGroupSessions(patients, therapists);
		
		therapyManager.persistAll(therapies);
		
		connect(therapies, groupSessions);
		
		therapyManager.persistAll(therapies);
	}
	
	private void connect(Collection<Therapy> therapies, Collection<GroupSession> groupSessions) {
		for (GroupSession groupSession: groupSessions)
			connect(therapies, groupSession);
	}
	
	private void connect(Collection<Therapy> therapies, GroupSession groupSession) {
		Therapy therapy = getTherapy(groupSession, therapies);
		if (therapy != null) {
			Collection<GroupSession> groupSessions  = therapy.getGroupSessions();
			if (groupSessions == null) {
				groupSessions = new ArrayList<>();
				therapy.setGroupSessions(new ArrayList<>(groupSessions));
			}
			groupSessions.add(groupSession);
			
			List<Therapy> groupSessionTherapies = groupSession.getTherapies();
			if (groupSessionTherapies == null) {
				groupSessionTherapies = new ArrayList<>();
				groupSession.setTherapies(groupSessionTherapies);
			}
			groupSessionTherapies.add(therapy);
		}
		
	}
	
	private Therapy getTherapy(GroupSession groupSession, Collection<Therapy> therapies) {
		Collection<Patient> patients = groupSession.getPatients();
		Collection<Therapy> patientTherapies = therapies.stream()
				.filter(therapy -> patients.contains(therapy.getPatient()))
				.collect(Collectors.toList());
		if (patientTherapies.isEmpty())
			return null;
		return patientTherapies.iterator().next();
	}
	
	private Collection<GroupSession> createGroupSessions(Collection<Patient> patients, Collection<Therapist> therapists) {
		final Collection<GroupSession> groupSessions = new ArrayList<>();
		Range<Patient> patientRange = new Range<>(5, 20);
		Range<Therapist> therapistRange = new Range<>(1, 3);
		final List<Entry<Collection<Patient>,
				Collection<Therapist>>> groupSessionPairs = createGroupSessionPairs(10,
						patients, therapists, patientRange, therapistRange);
		for (int i = 0; i < groupSessionPairs.size(); i++) {
			Collection<Patient> sessionPatients = groupSessionPairs.get(i).getKey();
			Collection<Therapist> sessionTherapists = groupSessionPairs.get(i).getValue();
			groupSessions.add(createGroupSession(sessionTypeFactory.create(),
					sessionTherapists,
					sessionPatients));
		}
		return groupSessions;
	}
	
	private Collection<Therapy> createTherapies(int therapyCount, Collection<Patient> patients, Collection<Therapist> therapists) {
		final Collection<Therapy> therapies = new ArrayList<>();
		final List<Entry<Patient, Therapist>> therapyPairs = createTherapyPairs(
				therapyCount, patients, therapists, 3);
		for (int i = 0; i < therapyPairs.size(); i++) {
			Patient patient = therapyPairs.get(i).getKey();
			Therapist therapist = therapyPairs.get(i).getValue();
			
			Range<PatientNote> patientNoteRange = new Range<>(5, 20);
			Range<TherapistNote> therapistNoteRange = new Range<>(5, 20);
			Range<ExpositionNote> expositionNoteRange = new Range<>(5, 20);
			Range<SingleSession> singleSessionRange = new Range<>(5, 20);
			
			Therapy therapy = createTherapy(
					therapyFactory.createTherapyType(),
					patient,
					therapist,
					patientNoteRange,
					therapistNoteRange,
					expositionNoteRange,
					singleSessionRange);
			therapies.add(therapy);
		}
		return therapies;
	}
	
	private Therapy createTherapy(
									TherapyType therapyType,
									Patient patient,
									Therapist therapist,
									Range<PatientNote> patientNoteRange,
									Range<TherapistNote> therapistNoteRange,
									Range<ExpositionNote> expositionNoteRange,
									Range<SingleSession> singleSessionRange) {
		Therapy therapy = therapyFactory.create(patient, therapist);
		therapy.setTherapyType(therapyType);
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
	
	private GroupSession createGroupSession(SessionType sessionType,
											Collection<Therapist> therapists,
											Collection<Patient> patients) {
		GroupSession groupSession = groupSessionFactory
				.create(new ArrayList<>(therapists), new ArrayList<>(patients));
		groupSession.setSessionType(sessionType);
		return groupSession;
	}
	
	private static List<Entry<Collection<Patient>, Collection<Therapist>>>
			createGroupSessionPairs(int pairCount,
									Collection<Patient> patients,
									Collection<Therapist> therapists,
									Range<Patient> patientRange,
									Range<Therapist> therapistRange) {
		List<Entry<Collection<Patient>, Collection<Therapist>>> pairs = new ArrayList<>();
		while (pairs.size() < pairCount) {
			Collection<Patient> sessionPatients = getRandom(
					patientRange.getRandomRangedValue(), patients, true);
			Collection<Therapist> sessionTherapists = getRandom(
					therapistRange.getRandomRangedValue(), therapists, true);
			pairs.add(new AbstractMap.SimpleEntry<>(sessionPatients, sessionTherapists));
		}
		return pairs;
	}
	
	private static List<Entry<Patient, Therapist>> createTherapyPairs(  int pairCount,
																		Collection<Patient> patients,
																		Collection<Therapist> therapists,
																		int maxPatientCount) {
		List<Entry<Patient, Therapist>> pairs = new ArrayList<>();
		while (pairs.size() < pairCount) {
			Patient patient = getRandom(patients);
			Therapist therapist = getRandom(therapists);
			int patientCount = (int) pairs.stream()
					.filter(entry -> entry.getKey().equals(patient)).count();
			if (patientCount < maxPatientCount)
				pairs.add(new AbstractMap.SimpleEntry<>(patient, therapist));
		}
		return pairs;
	}
	
	private static <T> Collection<T> getRandom( int count, Collection<T> collection,
												boolean distinct) {
		Collection<T> randomSelectedElements;
		if (distinct) {
			randomSelectedElements = new HashSet<>();
			Collection<T> distinctCollection = new HashSet<>();
			for (T t : collection)
				distinctCollection.add(t);
			collection = distinctCollection;
			if (count >= collection.size()) {
				System.out.println(
						"INFO DbSeeder.getRandom not enough input parameters for creating collection of "
								+ count + "elements.");
				count = collection.size();
			}
		} else
			randomSelectedElements = new ArrayList<>();
		while (randomSelectedElements.size() < count) {
			randomSelectedElements.add(getRandom(collection));
		}
		return randomSelectedElements;
	}
	
	private static <T> T getRandom(Collection<T> collection) {
		int size = collection.size();
		int n = (int) (Math.random() * ((double) size - 1));
		Iterator<T> iterator = collection.iterator();
		T t = null;
		for (int i = 0; i <= n; i++) {
			t = iterator.next();
		}
		return t;
	}
	
	private static <T> List<T> createRanged(Supplier<T> factory,
											Range<T> range) {
		int n = range.getRandomRangedValue();
		List<T> items = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			items.add(factory.get());
		}
		return items;
	}
	
	private class Range<T> {
		private final int min;
		private final int bound;
		private final Random random = new Random();
		
		public Range(int min, int max) {
			if (min < 0 || max < 0 || max < min)
				throw new IllegalArgumentException();
			this.min = min;
			this.bound = max - min + 1;
		}
		
		public int getRandomRangedValue() {
			return min + random.nextInt(bound);
		}
		
	}
	
}
