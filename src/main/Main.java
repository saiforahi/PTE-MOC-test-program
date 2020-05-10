package main;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {

  public static void main(String args[]) throws Exception {
    Connection conn = sqlConnection.answerDBConnection();
    /*FileInputStream fis=new FileInputStream(new File("Answer\\ListeningAns.ser"));
	ObjectInputStream ois=new ObjectInputStream(fis);
	ListeningAnswer l=(ListeningAnswer)ois.readObject();
	ois.close();
	fis.close();
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(baos);
    oos.writeObject(l);
    
    PreparedStatement pstmt = dbConn.prepareStatement("INSERT INTO serFiles (files) VALUES(?)");
    
    byte[] employeeAsBytes = baos.toByteArray();
    ByteArrayInputStream bais = new ByteArrayInputStream(employeeAsBytes);
    pstmt.setBinaryStream(1, bais, employeeAsBytes.length);
    pstmt.executeUpdate();
    pstmt.close();*/
    
	PreparedStatement pstmt=conn.prepareStatement("SELECT files FROM serFiles WHERE rowid=?;");
	pstmt.setInt(1, 1);  // 1st repeat sentence 
	ResultSet rs=pstmt.executeQuery();
	byte[] st = (byte[]) rs.getObject(1);
    ByteArrayInputStream baip = new ByteArrayInputStream(st);
    ObjectInputStream ois1 = new ObjectInputStream(baip);
    ListeningAnswer l1=(ListeningAnswer)ois1.readObject();
    System.out.println(l1.summaryAnswer);
    System.out.println(l1.dictation3);
    rs.close();
    ois1.close();
    baip.close();
    
	pstmt.setInt(1, 2);  // 1st repeat sentence 
	ResultSet rs1=pstmt.executeQuery();
	byte[] st1 = (byte[]) rs1.getObject(1);
    ByteArrayInputStream baip1 = new ByteArrayInputStream(st1);
    ObjectInputStream ois2 = new ObjectInputStream(baip1);
    ReadingAnswer l2=(ReadingAnswer)ois2.readObject();
    System.out.println(l2.mcq1);
   rs1.close();
    ois2.close();
    baip1.close();
    /*
    Statement stmt = dbConn.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT files FROM serFiles");
    while (rs.next()) {
      byte[] st = (byte[]) rs.getObject(1);
      ByteArrayInputStream baip = new ByteArrayInputStream(st);
      ObjectInputStream ois1 = new ObjectInputStream(baip);
      ListeningAnswer l1=(ListeningAnswer)ois1.readObject();
      System.out.println(l1.summaryAnswer);
      System.out.println(l1.dictation3);
      ois1.close();
      baip.close();
    }
    
    stmt.close();
    rs.close();
    dbConn.close();*/
  }
  
}
