package ch.bfh.red.ui.views.searchBeans;

import java.util.Date;

import ch.bfh.red.ui.views.SearchBean.PatientSearchBean;

public class SingleSessionSearchBean {
	private PatientSearchBean patient;
	private Date startDate;
	private Date endDate;
	
	public SingleSessionSearchBean() {}

	public PatientSearchBean getPatient() {
		return patient;
	}

	public void setPatient(PatientSearchBean patient) {
		this.patient = patient;
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
	
}
