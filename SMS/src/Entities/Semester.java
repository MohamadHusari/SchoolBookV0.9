package Entities;

import java.io.Serializable;
import java.util.Date;

public class Semester implements Serializable {
	private Date startDate;
	private Date endDate;
	private String semesterLetter;
	private int semester_id;
	
	public Semester(Date startDate,Date endDate,String semesterLetter,int semester_id)
	{
		setEndDate(endDate);
		setStartDate(startDate);
		setSemesterLetter(semesterLetter);
		setSemester_id(semester_id);
	}
	
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getSemesterLetter() {
		return semesterLetter;
	}
	public void setSemesterLetter(String semesterLetter) {
		this.semesterLetter = semesterLetter;
	}
	public int getSemester_id() {
		return semester_id;
	}
	public void setSemester_id(int semester_id) {
		this.semester_id = semester_id;
	}
}
