package set2;

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
import java.io.InputStream;
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
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import main.Functions;
import main.Menu;
import main.sqlConnection;

public class Set2RetellStory2 {

	public JFrame frame;
	JLabel statusLabel;
	JProgressBar progressBar;
	JProgressBar progressBar1;
	JLabel statusLabel1;
	JButton nextButton;
	TargetDataLine target;
	AudioInputStream audioStream;
	AudioFormat af;
	public static int val=4;
	Player p;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Set2RetellStory2 window = new Set2RetellStory2();
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
	public Set2RetellStory2() {
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
					if(p!=null)
					{
						p.close();
						p=null;
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
		
		JLabel lblSpeakingTestretell = new JLabel("Speaking Test");
		lblSpeakingTestretell.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblSpeakingTestretell.setBounds(10, 0, 497, 56);
		panel.add(lblSpeakingTestretell);
		
		
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
		
		JLabel lblItemNo = new JLabel("Item no 24 of 35");
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
				
				save("retellStory1.mp3");
				frame.dispose();
				Set2RetellStory3 go=new Set2RetellStory3();
				go.frame.setVisible(true);
			}
		});
		nextButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		nextButton.setEnabled(false);
		nextButton.setBounds(1165, 615, 89, 28);
		frame.getContentPane().add(nextButton);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setLayout(null);
		panel_3.setBorder(new LineBorder(new Color(51, 0, 102), 3, true));
		panel_3.setBounds(919, 196, 335, 173);
		frame.getContentPane().add(panel_3);
		
		JLabel label = new JLabel("Status");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label.setBounds(124, 30, 85, 30);
		panel_3.add(label);
		
		statusLabel = new JLabel("beginning in 4 seconds");
		statusLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		statusLabel.setBounds(63, 71, 220, 27);
		panel_3.add(statusLabel);
		
		progressBar = new JProgressBar();
		progressBar.setBackground(new Color(224, 255, 255));
		progressBar.setForeground(new Color(0, 51, 102));
		progressBar.setBorder(new LineBorder(new Color(30, 144, 255), 2, true));
		progressBar.setBorderPainted(true);
		progressBar.setStringPainted(true);
		progressBar.setString("");
		progressBar.setBounds(21, 123, 293, 26);
		panel_3.add(progressBar);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setLayout(null);
		panel_4.setBorder(new LineBorder(new Color(51, 0, 102), 3, true));
		panel_4.setBounds(919, 399, 335, 183);
		frame.getContentPane().add(panel_4);
		
		JLabel label_3 = new JLabel("Recorded Answer");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_3.setBounds(10, 11, 315, 36);
		panel_4.add(label_3);
		
		JLabel label_4 = new JLabel("Current Status:");
		label_4.setHorizontalAlignment(SwingConstants.LEFT);
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_4.setBounds(23, 77, 89, 30);
		panel_4.add(label_4);
		
		progressBar1 = new JProgressBar();
		progressBar1.setBackground(new Color(224, 255, 255));
		progressBar1.setForeground(new Color(0, 51, 102));
		progressBar1.setBorder(new LineBorder(new Color(30, 144, 255), 2, true));
		progressBar1.setBorderPainted(true);
		progressBar1.setStringPainted(true);
		progressBar1.setString("");
		progressBar1.setBounds(23, 137, 291, 26);
		panel_4.add(progressBar1);
		
		statusLabel1 = new JLabel("");
		statusLabel1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		statusLabel1.setBounds(122, 77, 203, 30);
		panel_4.add(statusLabel1);
		
		JLabel label_6 = new JLabel("New label");
		label_6.setBounds(212, 196, 637, 393);
		frame.getContentPane().add(label_6);
		
		JLabel lblNewLabel = new JLabel("<html>You will hear a lecture. After listening to the lecture, in 10 seconds, Please speak into the microphone and retell what you have just heard from the lecture in your own words. You will have 40 seconds to give your response.</html>");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(212, 99, 1042, 46);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel label_1 = new JLabel("\u00A9Lab Symbiotic");
		label_1.setVerticalAlignment(SwingConstants.TOP);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(Color.DARK_GRAY);
		label_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		label_1.setBounds(0, 704, 1370, 24);
		frame.getContentPane().add(label_1);
	}
	
	//************************************************************** Method of countdown*********************************************************
	
		public void timeCount(){
			Runnable run=new Runnable(){
				public void run(){
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
						player();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (LineUnavailableException e) {
						e.printStackTrace();
					}
				}
			};
			Thread tr=new Thread(run);
			tr.start();
			
		}
		
	/************************************************** player **************************************************************/
		
		public void player() throws InterruptedException, LineUnavailableException{
			Runnable run1=new Runnable(){
				public void run(){
					statusLabel.setText("Playing");
					progressBar.setMaximum(88);
					do{
						progressBar.setValue(progressBar.getValue()+1);
						try {
							TimeUnit.SECONDS.sleep(1);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}while(progressBar.getValue()<=88);
				}
			};
			Runnable run2=new Runnable(){
				public void run(){
					int num=100;
					while(num>0)
					{
						num--;
						statusLabel1.setText("Beginning in "+num+" seconds");
						try {
							TimeUnit.SECONDS.sleep(1);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			};
			Runnable run3=new Runnable(){
				public void run(){
					try {
						Connection conn= sqlConnection.dbConnection();
						PreparedStatement pstmt=conn.prepareStatement("SELECT retellStory FROM test2question WHERE rowid=?;");
						pstmt.setInt(1, 2);  // 1st retell lecture 
						ResultSet rs=pstmt.executeQuery();
						InputStream input = rs.getBinaryStream("retellStory");
						//FileInputStream fis=new FileInputStream("sounds\\S1.mp3");
						p=new Player(input);
						p.play();
						rs.close();
						pstmt.close();
						input.close();
					} catch (JavaLayerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					statusLabel.setText("playback stopped");
					frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				}
			};
			Thread tr1=new Thread(run1);
			Thread tr2=new Thread(run2);
			Thread tr3=new Thread(run3);
			tr1.start();
			tr2.start();
			tr3.start();
			TimeUnit.SECONDS.sleep(100);
			recorder();
		}
		
	/************************************************** STOP! **************************************************************/
		
		public void recorder() throws LineUnavailableException{
			Runnable run=new Runnable(){
				public void run(){
					statusLabel1.setText("Recording.....");
					progressBar1.setValue(0);
					progressBar1.setMaximum(80);
					nextButton.setEnabled(true);
					while(progressBar1.getValue()<80)
			    	{
						progressBar1.setValue(progressBar1.getValue()+2);
			    		try {
							TimeUnit.SECONDS.sleep(1);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			    	}
					statusLabel1.setText("Completed");
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
		
		// ****************************************************************class of recording*********************************************************
		class CaptureThread extends Thread{
			  public void run(){
			    File f=new File("retellStory1.mp3");
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
				String updateSQL = "UPDATE set2speaking SET retellStory = ? WHERE rowid = ?;";
				Connection conn=sqlConnection.answerDBConnection();
				PreparedStatement pstmt = conn.prepareStatement(updateSQL);

				// set parameters
				/*pstmt.setBytes(1, readFile(filename));
				pstmt.setInt(2, materialId);*/
				File audio = new File(filename);
				FileInputStream   fis = new FileInputStream(audio);
				pstmt.setBinaryStream(1, fis,(int) audio.length());
				pstmt.setInt(2,2);
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
