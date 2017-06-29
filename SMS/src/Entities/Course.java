package Entities;

import java.io.Serializable;

public class Course implements Serializable {

	private String Course_ID;
	private String Course_Name;
	private String TeachUnit_ID;
	private float CourseStudyHours;
	private String PreCourses[];
	
	public Course(String Course_ID,String Course_Name,String TeachUnit_ID,float CourseStudyHours,String PreCourses[])
	{	
		setCourse_ID(Course_ID);
		setCourse_Name(Course_Name);
		setTeachUnit_ID(TeachUnit_ID);
		setCourseStudyHours(CourseStudyHours);
		setPreCourses(PreCourses);
	}
	public String getCourse_ID() {
		return Course_ID;
	}
	public void setCourse_ID(String course_ID) {
		Course_ID = course_ID;
	}
	public String getCourse_Name() {
		return Course_Name;
	}
	public void setCourse_Name(String course_Name) {
		Course_Name = course_Name;
	}
	public String getTeachUnit_ID() {
		return TeachUnit_ID;
	}
	public void setTeachUnit_ID(String teachUnit_ID) {
		TeachUnit_ID = teachUnit_ID;
	}
	public float getCourseStudyHours() {
		return CourseStudyHours;
	}
	public void setCourseStudyHours(float courseStudyHours) {
		CourseStudyHours = courseStudyHours;
	}
	public String[] getPreCourses() {
		return PreCourses;
	}
	public void setPreCourses(String preCourses[]) {
		PreCourses = preCourses;
	}
}
