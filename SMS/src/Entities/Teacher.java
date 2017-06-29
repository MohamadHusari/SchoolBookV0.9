package Entities;

public class Teacher extends User {

	
	private int TeacherUnit_ID;
	private float maxLearningHours;
	private float CurrentLearningHours;
	
	public Teacher(int ID, String Name, String Password, String Email, int Status, AccessProfiles Access,int TeacherUnit_ID,float maxLearningHours, float CurrentLearningHours)
	{
		super(ID, Name, Password, Email, Status, Access);
		setCurrentLearningHours(CurrentLearningHours);
		setTeacherUnit_ID(TeacherUnit_ID);
		setMaxLearningHours(maxLearningHours);
	}
	
	public float getCurrentLearningHours() {
		return CurrentLearningHours;
	}
	public void setCurrentLearningHours(float currentLearningHours) {
		CurrentLearningHours = currentLearningHours;
	}
	public int getTeacherUnit_ID() {
		return TeacherUnit_ID;
	}
	public void setTeacherUnit_ID(int teacherUnit_ID) {
		TeacherUnit_ID = teacherUnit_ID;
	}
	public float getMaxLearningHours() {
		return maxLearningHours;
	}
	public void setMaxLearningHours(float maxLearningHours) {
		this.maxLearningHours = maxLearningHours;
	}
	
	
}
