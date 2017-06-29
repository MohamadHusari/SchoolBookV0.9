package OurMessage;

import java.io.Serializable;

public class Message implements Serializable {

	/*
	 * Message Guide:
	 * Qtype: 1 -> INSERT QUERY
	 * 		  2 -> SELECT QUERY
	 * 		  3 -> Display QUERY Result in Server
	 * 		  4 -> UPDATE QUERY
	 * =============================
	 * Query: The Query String which needed to be communicate with the DateBase
	 */
	private String Query;
	private int Qtype;
	
	public Message (String Q,int QT)
	{
		 SetQuery(Q);
		 SetQType(QT);
	}
	
	public void SetQuery(String query)
	{this.Query=query;}
	
	public String GetQuery()
	{return this.Query;}
	
	public void SetQType(int qtype)
	{this.Qtype=qtype;}
	
	public  int GetQType()
	{return this.Qtype;}
}
