package Entities;

import java.util.Date;

public class AssignmentFile extends File {
		private int AssignmentNumber;
		private Date FinalDate;
		
		public AssignmentFile(String filename,int userid,int courseid,int assignmentnumber,Date finaldate){
			super(filename,userid,courseid);
			setAssignmentNumber(assignmentnumber);
			setFinalDate(finaldate);
		}
		
		public int getAssignmentNumber() {
			return AssignmentNumber;
		}
		public void setAssignmentNumber(int assignmentNumber) {
			AssignmentNumber = assignmentNumber;
		}
		public Date getFinalDate() {
			return FinalDate;
		}
		public void setFinalDate(Date finalDate) {
			FinalDate = finalDate;
		}
}
