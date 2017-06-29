package OurMessage;

import java.io.Serializable;

public class Request implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object Request;
	private int Rtype;
	
	public Request(Object request,int rtype){
		setRequest(request);
		setRtype(rtype);
	}
	public Object getRequest() {
		return Request;
	}
	public void setRequest(Object request) {
		Request = request;
	}
	public int getRtype() {
		return Rtype;
	}
	public void setRtype(int rtype) {
		Rtype = rtype;
	}
}
