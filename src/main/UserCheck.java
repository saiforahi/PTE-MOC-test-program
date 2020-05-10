package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class UserCheck {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		resumeMethod();
		try {
			File answer=new File("Answer//ReadingAns.ser");
			FileInputStream fis=new FileInputStream(answer);
			ObjectInputStream ois=new ObjectInputStream(fis);
			ReadingAnswer ui=(ReadingAnswer)ois.readObject();
			ois.close();
			fis.close();
			
			System.out.println(ui.mcq1);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}
	
	public static void resumeMethod()
	{
		try {
			File answer=new File("Answer//UserInfo.ser");
			if(answer.exists())
			{
				FileInputStream fis=new FileInputStream(answer);
				ObjectInputStream ois=new ObjectInputStream(fis);
				UserInfo ui=(UserInfo)ois.readObject();
				
				
				System.out.println(ui.name);
				System.out.println(ui.cell);
				
				ois.close();
				fis.close();
				answer.delete();
				FileOutputStream fos=new FileOutputStream(new File("Answer//UserInfo.ser"),true);
				ObjectOutputStream oos=new ObjectOutputStream(fos);
				oos.writeObject(ui);
				oos.close();
				fos.close();
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
