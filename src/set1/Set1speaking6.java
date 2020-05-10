package set1;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
import main.Functions;
import main.Menu;
import main.sqlConnection;
import javax.swing.border.MatteBorder;

public class Set1speaking6 {

	public JFrame frame;
	JLabel statusLabel;
	JProgressBar progressBar;
	JButton nextButton;
	JTextPane textPane;
	TargetDataLine target;
	AudioInputStream audioStream;
	AudioFormat af;
	public static int val=25;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Set1speaking6 window = new Set1speaking6();
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
	public Set1speaking6() {
		initialize();
		textPane.setText(getText());
		
		JLabel label = new JLabel("\u00A9Lab Symbiotic");
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.DARK_GRAY);
		label.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		label.setBounds(0, 704, 1370, 24);
		frame.getContentPane().add(label);
		timeCount();
	}
	
	public String getText(){
		try {
			Connection conn=sqlConnection.dbConnection();
			ResultSet rs = conn.createStatement().executeQuery("SELECT readAloud FROM test1question;");
			int count=0;
			String s=null;
			while (rs.next())
			{
				count++;
				if(count==6)
				{
					s=rs.getString("readAloud");
					break;
				}
				
			}
			rs.close();
			conn.close();
			return s;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
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
						conn.createStatement().executeUpdate("DELETE FROM set1speaking;");
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
		
		nextButton = new JButton("Next");
		nextButton.setBackground(Color.WHITE);
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(target.isOpen())
				{
					
					target.close();
				}
				
				save("ReadAloud6.mp3");
				frame.dispose();
				Set1speaking7 go=new Set1speaking7();
				go.frame.setVisible(true);
			}
		});
		nextButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		nextButton.setEnabled(false);
		nextButton.setBounds(1165, 615, 89, 28);
		frame.getContentPane().add(nextButton);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1370, 56);
		frame.getContentPane().add(panel);
		
		JLabel lblSpeakingTest = new JLabel("Speaking Test");
		lblSpeakingTest.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblSpeakingTest.setBounds(10, 0, 497, 56);
		panel.add(lblSpeakingTest);
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(255, 140, 0));
		panel_1.setBounds(0, 55, 1370, 33);
		frame.getContentPane().add(panel_1);
		
		JLabel label_1 = new JLabel("Look at the text below. In 35 seconds, you must read this test aloud as naturally and clearly as possible. Remember you have 35 seconds to read aloud.");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_1.setBounds(86, 111, 1188, 33);
		frame.getContentPane().add(label_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(2, 0, 2, 0, (Color) new Color(0, 51, 102)));
		panel_2.setLayout(null);
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(508, 203, 384, 210);
		frame.getContentPane().add(panel_2);
		
		JLabel label_2 = new JLabel("Recorded Answer");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		label_2.setBounds(50, 11, 290, 46);
		panel_2.add(label_2);
		
		JLabel label_3 = new JLabel("Current Status:");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_3.setBounds(22, 68, 273, 31);
		panel_2.add(label_3);
		
		statusLabel = new JLabel("Completed!");
		statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		statusLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		statusLabel.setBounds(10, 110, 364, 31);
		panel_2.add(statusLabel);
		
		progressBar = new JProgressBar();
		progressBar.setForeground(new Color(0, 51, 102));
		progressBar.setOpaque(true);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		progressBar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		progressBar.setBorder(new MatteBorder(2, 1, 2, 1, (Color) new Color(0, 51, 102)));
		progressBar.setString("");
		progressBar.setEnabled(false);
		progressBar.setBounds(10, 162, 364, 33);
		panel_2.add(progressBar);
		
		textPane = new JTextPane();
		textPane.setText((String) null);
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textPane.setEditable(false);
		textPane.setBounds(134, 456, 1120, 114);
		frame.getContentPane().add(textPane);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new LineBorder(Color.DARK_GRAY, 2));
		panel_3.setBackground(new Color(224, 255, 255));
		panel_3.setBounds(134, 615, 154, 33);
		frame.getContentPane().add(panel_3);
		
		JLabel lblItemNo = new JLabel("Item no 6 of 37");
		lblItemNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblItemNo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblItemNo.setBounds(10, 0, 144, 33);
		panel_3.add(lblItemNo);
	}
	
	//************************************************************** Method of countdown*********************************************************
	
			public void timeCount(){
				Runnable run=new Runnable(){
					public void run(){
						frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
						while(progressBar.getValue()<100)
				    	{
							val--;
							statusLabel.setText("starting in "+val+" seconds");
				    		progressBar.setValue(progressBar.getValue()+4);
				    		try {
								TimeUnit.SECONDS.sleep(1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
				    	}
						try {
							record();
						} catch (LineUnavailableException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				};
				Thread tr=new Thread(run);
				tr.start();
				
			}
			
			// ****************************************************************Method of recording*********************************************************
			
			public void record() throws LineUnavailableException{
				Runnable run=new Runnable(){
					public void run(){
						statusLabel.setText("Recording.....");
						nextButton.setEnabled(true);
						frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
						progressBar.setValue(0);
						while(progressBar.getValue()<100)
				    	{
				    		progressBar.setValue(progressBar.getValue()+3);
				    		try {
								TimeUnit.SECONDS.sleep(1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
				    	}
						statusLabel.setText("Completed");
						if(target.isOpen())
						{
							target.close();
							target.stop();
						}
					}
				};
				af=new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,88200,16, 2, 4, 88200, false);
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
			
			// ****************************************************************class of recording*********************************************************
			class CaptureThread extends Thread{
				  public void run(){
				    File f=new File("ReadAloud6.mp3");
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
					Connection conn=sqlConnection.answerDBConnection();
					String updateSQL = "INSERT INTO set1speaking (readAloud) VALUES(?)";
					PreparedStatement pstmt = conn.prepareStatement(updateSQL);
						
					// set parameters
						/*pstmt.setBytes(1, readFile(filename));
						pstmt.setInt(2, materialId);*/
					File audio = new File(filename);
					FileInputStream   fis = new FileInputStream(audio);
					pstmt.setBinaryStream(1, fis,(int) audio.length());
					pstmt.executeUpdate();
					System.out.println("inserted the file in the BLOB column.");
					pstmt.close();
					try {
						fis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					audio.delete();
					conn.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
}
