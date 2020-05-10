package main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ReadingAnswer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String mcq1;
	public String mcq2;
	public List <String> mcq3;
	public List <String> mcq4;
	public List <String> reArrange1;
	public List <String> reArrange2;
	public List <String> fillBlank;
	public List <String> fillBlank2;
	public List <String> fillBlank3;
	public List <String> fillBlank4;
	public List <String> fillBlankCombo;
	public List <String> fillBlankCombo2;
	public List <String> fillBlankCombo3;
	public List <String> fillBlankCombo4;
	public List <String> fillBlankCombo5;
	public ReadingAnswer()
	{
		mcq1=new String();
		mcq2=new String();
		mcq3=new ArrayList <String>();
		mcq4=new ArrayList <String>();
		reArrange1=new ArrayList <String>();
		reArrange2=new ArrayList <String>();
		fillBlank=new ArrayList <String>();
		fillBlank2=new ArrayList <String>();
		fillBlank3=new ArrayList <String>();
		fillBlank4=new ArrayList <String>();
		fillBlankCombo=new ArrayList <String>();
		fillBlankCombo2=new ArrayList <String>();
		fillBlankCombo3=new ArrayList <String>();
		fillBlankCombo4=new ArrayList <String>();
		fillBlankCombo5=new ArrayList <String>();
	}
	
}
