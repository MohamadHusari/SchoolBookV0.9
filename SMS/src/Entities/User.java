package Entities;

import java.io.Serializable;

public class User implements Serializable {

	private int ID;
	private String Name;
	private String Password;
	private String Email;
	private int Status; // 2 if it was blocked (parent blocking)
	private AccessProfiles Access;
	//Constructor
	
	public User(int ID,String Name,String Password,String Email,int Status,AccessProfiles Access)
	{
		setID(ID);
		setName(Name);
		setPassword(Password);
		setEmail(Email);
		setStatus(Status);
		setAccess(Access);
	}
	
	//ID Getters & Setters
	public int getID(){return this.ID;}
	public void setID(int id){this.ID=id;}
	//Name Getters & Setters
	public String getName(){return this.Name;}
	public void setName(String name){this.Name=name;}
	//Password Getters & Setters
	public String getPassword(){return this.Password;}
	public void setPassword(String password){this.Password=password;}
	//Email Getters & Setters
	public String getEmail(){return this.Email;}
	public void setEmail(String email){this.Email=email;}
	//Status Getters & Setters
	public int getStatus(){return this.Status;}
	public void setStatus(int Status){this.Status=Status;}
	//Access Getters & Setters
	public AccessProfiles getAccess() {return Access;}
	public void setAccess(AccessProfiles access) {Access = access;}
}
