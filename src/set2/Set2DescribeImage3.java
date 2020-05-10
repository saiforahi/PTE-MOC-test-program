package set2;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import main.Functions;
import main.Menu;
import main.sqlConnection;

public class Set2DescribeImage3 {

	public JFrame frame;
	JLabel statusLabel;
	JProgressBar progressBar;
	JLabel label_1;
	JLabel imageLabel;
	JButton nextButton;
	public static int val=25;
	TargetDataLine target;
	AudioInputStream audioStream;
	AudioFormat af;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Set2DescribeImage3 window = new Set2DescribeImage3();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Set2DescribeImage3() {
		initialize();
		timeCount();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				String[] options = new String[2];
				options[0] = new String("Yes");
				options[1] = new String("No!");
				int choice=JOptionPane.showOptionDialog(frame.getContentPane(),"Are you sure to exit?!","Confirmation to proceed", JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,null,options,null);
				if(choice==0)
				{
					Connection conn=sqlConnection.answerDBConnection();
					try {
						conn.createStatement().executeUpdate("DELETE FROM set2speaking;");
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					frame.dispose();
					Menu go=new Menu();
					go.frame.setVisible(true);
					go.frame.setLocationRelativeTo(null);
				}
				else frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			}
		});
		frame.pack();
		frame.setBackground(Color.WHITE);
		frame.setIconImage(Functions.setIcon());
		frame.setTitle("Speaking Test");
		frame.setFocusable(true);
		frame.setSize(new Dimension(screenSize.width, screenSize.height));
		frame.getContentPane().setPreferredSize(new Dimension(screenSize.width, screenSize.height));
		frame.setAlwaysOnTop(true);
		frame.setResizable(false);
		frame.setLocation(0,0);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1370, 56);
		frame.getContentPane().add(panel);
		
		JLabel lblSpeakingTestdescribe = new JLabel("Speaking Test");
		lblSpeakingTestdescribe.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblSpeakingTestdescribe.setBounds(10, 0, 497, 56);
		panel.add(lblSpeakingTestdescribe);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(255, 140, 0));
		panel_1.setBounds(0, 55, 1370, 33);
		frame.getContentPane().add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new LineBorder(Color.DARK_GRAY, 2));
		panel_2.setBackground(new Color(224, 255, 255));
		panel_2.setBounds(134, 615, 154, 33);
		frame.getContentPane().add(panel_2);
		
		JLabel lblItemNo = new JLabel("Item no 19 of 35");
		lblItemNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblItemNo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblItemNo.setBounds(10, 0, 144, 33);
		panel_2.add(lblItemNo);
		
		nextButton = new JButton("Next");
		nextButton.setBackground(Color.WHITE);
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(target.isOpen())
				{
					target.stop();
					target.close();
				}
				
				save("describeImage1.mp3");
				frame.dispose();
				Set2DescribeImage4 go=new Set2DescribeImage4();
				go.frame.setVisible(true);
			}
		});
		nextButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		nextButton.setEnabled(false);
		nextButton.setBounds(1165, 615, 89, 28);
		frame.getContentPane().add(nextButton);
		
		JLabel label = new JLabel("Look at the image below. In 25 seconds, please speak into the microphone and describe in detail what the image is showing. You will have 40 seconds to give your response.");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label.setBounds(0, 93, 1370, 28);
		frame.getContentPane().add(label);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new BevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, null, null, null));
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(134, 167, 674, 410);
		frame.getContentPane().add(panel_3);
		
		imageLabel = new JLabel("");
		imageLabel.setBounds(10, 11, 654, 388);
		panel_3.add(imageLabel);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(51, 0, 102), 3, true));
		panel_4.setLayout(null);
		panel_4.setBackground(Color.WHITE);
		panel_4.setBounds(853, 251, 384, 218);
		frame.getContentPane().add(panel_4);
		
		JLabel label_4 = new JLabel("Recorded Answer");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("Tahoma", Font.BOLD, 18));
		label_4.setBounds(50, 11, 290, 46);
		panel_4.add(label_4);
		
		JLabel label_5 = new JLabel("Current Status:");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_5.setBounds(22, 68, 273, 31);
		panel_4.add(label_5);
		
		statusLabel = new JLabel("Completed!");
		statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		statusLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		statusLabel.setBounds(10, 110, 364, 31);
		panel_4.add(statusLabel);
		
		progressBar = new JProgressBar();
		progressBar.setValue(0);
		progressBar.setOpaque(true);
		progressBar.setForeground(new Color(0, 51, 102));
		progressBar.setBorder(new LineBorder(new Color(30, 144, 255), 2, true));
		progressBar.setString("");
		progressBar.setBorderPainted(true);
		progressBar.setStringPainted(true);
		progressBar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		progressBar.setEnabled(false);
		progressBar.setBounds(10, 162, 364, 33);
		panel_4.add(progressBar);
		
		JLabel label_2 = new JLabel("\u00A9Lab Symbiotic");
		label_2.setVerticalAlignment(SwingConstants.TOP);
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(Color.DARK_GRAY);
		label_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		label_2.setBounds(0, 704, 1370, 24);
		frame.getContentPane().add(label_2);
	}
	
	//************************************************************** Method of countdown*********************************************************
	
			public void timeCount(){
				Runnable run=new Runnable(){
					public void run(){
						view();
						frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
						while(val>=0)
				    	{
							statusLabel.setText("starting in "+val+" seconds");
							val--;
				    		try {
								TimeUnit.SECONDS.sleep(1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
				    	}
						try {
							recorder();
						} catch (LineUnavailableException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				};
				Thread tr=new Thread(run);
				tr.start();
				
			}
	//********************************************************** view method ************************************************************
			
			public void view(){
				try {
					Connection con=sqlConnection.dbConnection();
					//PreparedStatement pstmt= con.prepareStatement("SELECT describeImage FROM test1question WHERE rowid=?;");
					//pstmt.setInt(1, 1);
					ResultSet rs=con.createStatement().executeQuery("SELECT describeImage FROM test2question WHERE rowid=3;");
					if (rs.next())
					{
						byte[] blob=rs.getBytes("describeImage");
						ImageIcon image= new ImageIcon(blob);
						Image im=image.getImage();
						Image myimg=im.getScaledInstance(imageLabel.getWidth(),imageLabel.getHeight(),Image.SCALE_SMOOTH);
						ImageIcon icon= new ImageIcon(myimg);
						imageLabel.setIcon(icon);
					}
					rs.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
			
			/************************************************** recorder! **************************************************************/
			
			public void recorder() throws LineUnavailableException{
				Runnable run=new Runnable(){
					public void run(){
						statusLabel.setText("Recording.....");
						nextButton.setEnabled(true);
						progressBar.setValue(0);
						while(progressBar.getValue()<100)
				    	{
							progressBar.setValue(progressBar.getValue()+5);
				    		try {
								TimeUnit.SECONDS.sleep(2);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
				    	}
						statusLabel.setText("Completed");
						frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
						target.close();
						target.stop();
					}
				};
				af=new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,16000,16, 2, 4, 16000, false);
				DataLine.Info info= new DataLine.Info(TargetDataLine.class, af);
				if(!AudioSystem.isLineSupported(info)){
					System.err.println("Line isn't supported!");
				}
				target= (TargetDataLine)AudioSystem.getLine(info);
				audioStream=new AudioInputStream(target);
				Thread tr1=new Thread(run);
				tr1.start();
				new CaptureThread().start();
			}
			
			class CaptureThread extends Thread{
				  public void run(){
				    File f=new File("describeImage1.mp3");
				    try{
				      target.open(af);
				      target.start();
				      AudioSystem.write(audioStream, AudioFileFormat.Type.WAVE, f);
				    }catch (Exception e){
				      e.printStackTrace();
				    }//end catch
				  }//end run
			}
			
			// ****************************************************************        Save       *********************************************************
			
			public void save(String filename) {
				try {
					String updateSQL = "UPDATE set2speaking SET describeImage = ? WHERE rowid = ?;";
					Connection conn=sqlConnection.answerDBConnection();
					PreparedStatement pstmt = conn.prepareStatement(updateSQL);

					// set parameters
					/*pstmt.setBytes(1, readFile(filename));
					pstmt.setInt(2, materialId);*/
					File audio = new File(filename);
					FileInputStream   fis = new FileInputStream(audio);
					pstmt.setBinaryStream(1, fis,(int) audio.length());
					pstmt.setInt(2,3);
					pstmt.executeUpdate();
					System.out.println("Stored the file in the BLOB column.");
					pstmt.close();
					conn.close();
					try {
						fis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					audio.delete();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
}	
