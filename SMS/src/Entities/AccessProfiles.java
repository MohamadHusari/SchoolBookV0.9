package Entities;

import java.io.Serializable;

public class AccessProfiles implements Serializable {

	private int Access;
	
	public static int student=0;
	public static int teacher=1;
	public static int schoolAdmin=2;
	public static int systemAdmin=3;
	public static int secretry=4;
	public static int parent=5;
	
	
	public AccessProfiles(int Acc){
		setAccess(Acc);
	}
	public int getAccess() {
		return Access;
	}

	public void setAccess(int access) {
		Access = access;
	}
	
}
