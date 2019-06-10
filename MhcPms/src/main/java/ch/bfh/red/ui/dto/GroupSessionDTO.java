package ch.bfh.red.ui.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

import ch.bfh.red.backend.models.GroupSession;
import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.models.SessionType;
import ch.bfh.red.backend.models.Therapist;

public class GroupSessionDTO {
	private Integer id;
	private Date startDate;
	private Date endDate;
	private List<Patient> patients;
	private List<Therapist> therapists;
	private SessionType sessionType;
	
	public GroupSessionDTO() {}

	public static GroupSessionDTO toDTO(GroupSession session) {
		GroupSessionDTO dto = new GroupSessionDTO();
		try {
			dto.id = session.getId();
		} catch (Exception e) {
			dto.id = null;
		}
		dto.startDate = session.getStartDate();
		dto.endDate = session.getEndDate();
		dto.patients = new ArrayList<>(session.getPatients());
		dto.therapists = new ArrayList<>(session.getTherapists());
		dto.sessionType = session.getSessionType();
		return dto;
	}
	
	public static GroupSession toModel(GroupSessionDTO dto) {
		GroupSession session = new GroupSession();
		if (dto.getId() != null)
			session.setId(dto.getId());
		session.setStartDate(dto.getStartDate());
		session.setEndDate(dto.getEndDate());
		session.setPatients(dto.getPatients());
		session.setTherapists(dto.getTherapists());
		session.setSessionType(dto.getSessionType());
		return session;
	}
	
	@Override
	public GroupSessionDTO clone() {
		GroupSessionDTO clone = new GroupSessionDTO();
		clone.setId(this.getId());
		clone.setStartDate(this.getStartDate());
		clone.setEndDate(this.getEndDate());
		clone.setPatients(this.getPatients());
		clone.setTherapists(this.getTherapists());
		clone.setSessionType(this.getSessionType());
		return clone;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

	public List<Therapist> getTherapists() {
		return therapists;
	}

	public void setTherapists(List<Therapist> therapists) {
		this.therapists = therapists;
	}

	public SessionType getSessionType() {
		return sessionType;
	}

	public void setSessionType(SessionType sessionType) {
		this.sessionType = sessionType;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((sessionType == null) ? 0 : sessionType.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GroupSessionDTO other = (GroupSessionDTO) obj;
		if (equalsOrNull(other, o -> o.getId()))
			return false;
		if (equalsOrNull(other, o -> o.getStartDate()))
			return false;
		if (equalsOrNull(other, o -> o.getEndDate()))
			return false;
		if (equalsOrNull(other, o -> o.getSessionType()))
			return false;
		if (equalsOrNullCollection(other, o -> o.getPatients()))
			return false;
		if (equalsOrNullCollection(other, o -> o.getTherapists()))
			return false;
		return true;
	}
	
	private <U extends Collection<V>, V> boolean equalsOrNullCollection(GroupSessionDTO other, Function<GroupSessionDTO, U> mapper) {
		return equalsOrNullCollection(this, other, mapper);
	}
	
	private static <T, U extends Collection<V>, V> boolean equalsOrNullCollection(T t1, T t2, Function<T, U> mapper) {
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
	
	private static <T> boolean equalsCollection(Collection<T> coll1, Collection<T> coll2) {
		if (coll1.size() != coll2.size())
			return false;
		for (T t: coll1)
			if (!coll2.contains(t))
				return false;
		return true;
	}
	
	private <T> boolean equalsOrNull(GroupSessionDTO other,
										Function<GroupSessionDTO, T> mapper) {
			return equalsOrNull(this, other, mapper);
		}
	
	private static <T, U> boolean equalsOrNull(T t1, T t2, Function<T, U> mapper) {
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
	
}
