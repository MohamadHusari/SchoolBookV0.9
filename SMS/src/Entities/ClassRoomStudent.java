package Entities;

import java.io.Serializable;

public class ClassRoomStudent implements Serializable {
	
		private String Class_id;
		private int studentid;
		//Check with DataBase Serialization Numbering
		public ClassRoomStudent(String class_id,int studentId){
			setClass_id(class_id);
			setStudentID(studentId);
		}
		
		public String getClass_id() {
			return Class_id;
		}
		public void setClass_id(String class_id) {
			Class_id = class_id;
		}
		public int getStudentID() {
			return studentid;
		}
		public void setStudentID(int studentId) {
			studentid = studentId;
		}
		
}
