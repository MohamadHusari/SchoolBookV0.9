package Entities;

import java.util.Date;

public class Request extends Messages{
	
	private boolean Accepted;//True if been Approved False otherwise

	public Request(int Message_ID, int User_ID, String Message_Subject, String Message_Context, Date Message_date, boolean Status)
	{
		super( Message_ID,  User_ID,  Message_Subject,  Message_Context,  Message_date,  Status);
	}
	public boolean isAccepted() {
		return Accepted;
	}

	public void setAccepted(boolean accepted) {
		Accepted = accepted;
	}

}
