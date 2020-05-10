package main;

import java.io.Serializable;

public class License implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;
	String cell;
	String code;
	String set1voucher;
	String set2voucher;
	int times;
	
	public License(){
		name="LabSymbiotic";
		cell="01737552558";
		code="1133";
		times=0;
		set1voucher=new String();
		set2voucher=new String();
	}
}
