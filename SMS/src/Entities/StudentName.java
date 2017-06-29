package Entities;

public class StudentName {

	private int id;
	private String name;
	public StudentName(int ID,String Name){
		setId(ID);
		setName(Name);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
