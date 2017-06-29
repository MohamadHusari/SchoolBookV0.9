package Entities;

import java.io.Serializable;

public class File implements Serializable {
	private String FileName;
	private int UserID;
	private int CourseID;
	
	public File(String filename,int userid,int courseid){
		setFileName(filename);
		setUserID(userid);
		setCourseID(courseid);
	}
	
	public String getFileName() {
		return FileName;
	}
	public void setFileName(String fileName) {
		FileName = fileName;
	}
	public int getUserID() {
		return UserID;
	}
	public void setUserID(int userID) {
		UserID = userID;
	}
	public int getCourseID() {
		return CourseID;
	}
	public void setCourseID(int courseID) {
		CourseID = courseID;
	}
}
