package main;

import java.io.Serializable;

public class UserInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String name,cell,email,date,comment;
	public int setNumber;
	
	public UserInfo(){
		name=new String();
		cell=new String();
		email=new String();
		date=new String();
		comment=new String();
	}
}
