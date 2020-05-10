package main;

import java.io.Serializable;
import java.util.ArrayList;

public class ListeningAnswer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String summaryAnswer;
	public String summaryAnswer2;
	public String[] mcqMulitiAnswer;
	public String[] mcqMulitiAnswer2;
	//public String[] mcqMulitiAnswer2;
	public String[] fillBlankAnswer;
	public String[] fillBlankAnswer2;
	public String highlightCorrectSummary;
	public String highlightCorrectSummary2;
	public String mcqSingleAnswer;
	public String mcqSingleAnswer2;
	public String missingWord1;
	public String missingWord2;
	public ArrayList<String> highlightCorrectWord;
	public ArrayList<String> highlightCorrectWord2;
	public String dictation1;
	public String dictation2;
	public String dictation3;
	
	public ListeningAnswer(){
		summaryAnswer=new String();
		summaryAnswer2=new String();
		highlightCorrectSummary=new String();
		highlightCorrectSummary2=new String();
		mcqMulitiAnswer =  new String[7];
		mcqMulitiAnswer2 =  new String[6];
		fillBlankAnswer = new String[4];
		fillBlankAnswer2 = new String[6];
		mcqSingleAnswer = new String();
		mcqSingleAnswer2 = new String();
		missingWord1=new String();
		missingWord2=new String();
		highlightCorrectWord=new ArrayList<String>();
		highlightCorrectWord2=new ArrayList<String>();
		dictation1=new String();
		dictation2=new String();
		dictation3=new String();
	}
}
