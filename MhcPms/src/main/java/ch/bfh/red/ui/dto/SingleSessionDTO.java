package ch.bfh.red.ui.dto;

import java.util.Date;
import java.util.function.Function;

import ch.bfh.red.backend.models.Patient;
import ch.bfh.red.backend.models.SessionType;
import ch.bfh.red.backend.models.SingleSession;
import ch.bfh.red.backend.models.Therapist;
import ch.bfh.red.common.DTOutils;

public class SingleSessionDTO {
	private Integer id;
	private Date startDate;
	private Date endDate;
	private Patient patient;
	private Therapist therapist;
	private SessionType sessionType;
	
	public SingleSessionDTO() {}
	
	public static SingleSessionDTO toDTO(SingleSession singleSession) {
		SingleSessionDTO dto = new SingleSessionDTO();
		try {
			dto.id = singleSession.getId();
		} catch (Exception e) {
			dto.id = null;
		}
		dto.startDate = singleSession.getStartDate();
		dto.endDate = singleSession.getEndDate();
		dto.patient = singleSession.getPatient();
		dto.therapist = singleSession.getTherapist();
		dto.sessionType = singleSession.getSessionType();
		return dto;
	}
	
	public static SingleSession toModel(SingleSessionDTO dto) {
		SingleSession session = new SingleSession();
		if (dto.getId() != null)
			session.setId(dto.getId());
		session.setStartDate(dto.getStartDate());
		session.setEndDate(dto.getEndDate());
		session.setPatient(dto.getPatient());
		session.setTherapist(dto.getTherapist());
		session.setSessionType(dto.getSessionType());
		return session;
	}
	
	@Override
	public SingleSessionDTO clone() {
		SingleSessionDTO clone = new SingleSessionDTO();
		clone.setId(this.getId());
		clone.setStartDate(this.getStartDate());
		clone.setEndDate(this.getEndDate());
		clone.setPatient(this.getPatient());
		clone.setTherapist(this.getTherapist());
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
	
	public Patient getPatient() {
		return patient;
	}
	
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	public Therapist getTherapist() {
		return therapist;
	}
	
	public void setTherapist(Therapist therapist) {
		this.therapist = therapist;
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
		result = prime * result + ((patient == null) ? 0 : patient.hashCode());
		result = prime * result + ((sessionType == null) ? 0 : sessionType.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((therapist == null) ? 0 : therapist.hashCode());
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
		SingleSessionDTO other = (SingleSessionDTO) obj;
		if (equalsOrNull(other, o -> o.getId()))
			return false;
		if (equalsOrNull(other, o -> o.getStartDate()))
			return false;
		if (equalsOrNull(other, o -> o.getEndDate()))
			return false;
		if (equalsOrNull(other, o -> o.getSessionType()))
			return false;
		if (equalsOrNull(other, o -> o.getPatient()))
			return false;
		if (equalsOrNull(other, o -> o.getTherapist()))
			return false;
		return true;
	}
	
	private <T> boolean equalsOrNull(SingleSessionDTO other,
									Function<SingleSessionDTO, T> mapper) {
		return DTOutils.equalsOrNull(this, other, mapper);
	}
	
}
