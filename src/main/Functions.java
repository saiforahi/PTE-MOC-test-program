package main;

import java.awt.Image;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;

public class Functions {
	
	public static Image setIcon(){
		try {
			Connection con=sqlConnection.dbConnection();
			//PreparedStatement pstmt= con.prepareStatement("SELECT describeImage FROM test1question WHERE rowid=?;");
			//pstmt.setInt(1, 1);
			ResultSet rs=con.createStatement().executeQuery("SELECT image FROM image_table WHERE rowid=5;");
			if (rs.next())
			{
				byte[] blob=rs.getBytes("image");
				ImageIcon image= new ImageIcon(blob);
				Image im=image.getImage();
				rs.close();
				con.close();
				return im;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}

}
