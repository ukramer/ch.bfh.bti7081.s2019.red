package ch.bfh.red.ui.dto;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

import ch.bfh.red.backend.models.SessionType;
import ch.bfh.red.common.DTOutils;

public class GroupSessionDTO {
	private Integer id;
	private Date startDate;
	private Date endDate;
	private List<PatientDTO> patients;
	private List<TherapistDTO> therapists;
	private SessionType sessionType;
	
	public GroupSessionDTO() {}
	
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

	public List<PatientDTO> getPatients() {
		return patients;
	}

	public void setPatients(List<PatientDTO> patients) {
		this.patients = patients;
	}

	public List<TherapistDTO> getTherapists() {
		return therapists;
	}

	public void setTherapists(List<TherapistDTO> therapists) {
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
		return DTOutils.equalsOrNullCollection(this, other, mapper);
	}
	
	private <T> boolean equalsOrNull(GroupSessionDTO other,
										Function<GroupSessionDTO, T> mapper) {
			return DTOutils.equalsOrNull(this, other, mapper);
		}
	
}
