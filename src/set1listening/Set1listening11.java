package set1listening;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import main.Functions;
import main.ListeningAnswer;
import main.Menu;
import main.sqlConnection;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;

public class Set1listening11 {

	public JFrame frame;
	
	public int val=4;
	JLabel statusLabel;
	JProgressBar progressBar;
	JRadioButton radiobtn1,radiobtn2,radiobtn3,radiobtn4;
	Player p;
	String summary;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Set1listening11 window = new Set1listening11();
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
	public Set1listening11() {
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
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				
				String[] options = new String[2];
				options[0] = new String("Yes");
				options[1] = new String("No!");
				int choice=JOptionPane.showOptionDialog(frame.getContentPane(),"Are you sure to exit?!","Confirmation to proceed", JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,null,options,null);
				if(choice==0)
				{
					
					if(p!=null)
					{
						p.close();
						p=null;
					}
					File answer=new File("Answer//ListeningAns.ser");
					answer.delete();
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
		//frame.setBounds(100, 100, 450, 300);
		frame.setIconImage(Functions.setIcon());
		frame.getContentPane().setBackground(Color.WHITE);
		frame.pack();
		frame.setBackground(Color.WHITE);
		frame.setIconImage(Functions.setIcon());
		frame.setTitle("Listening Test");
		frame.setFocusable(true);
		frame.setSize(new Dimension(screenSize.width, screenSize.height));
		frame.getContentPane().setPreferredSize(new Dimension(screenSize.width, screenSize.height));
		frame.setAlwaysOnTop(true);
		frame.setResizable(false);
		frame.setLocation(0, 0);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new LineBorder(Color.DARK_GRAY, 2));
		panel.setBackground(new Color(224, 255, 255));
		panel.setBounds(134, 615, 154, 33);
		frame.getContentPane().add(panel);
		
		JLabel lblItemNo = new JLabel("Item no 10 of 17");
		lblItemNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblItemNo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblItemNo.setBounds(10, 0, 144, 33);
		panel.add(lblItemNo);
		
		 radiobtn4 = new JRadioButton("Extroverts lie less than introverts.");
		 radiobtn4.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		if(radiobtn2.isSelected())
				{
					summary=radiobtn2.getText();
					radiobtn1.setSelected(false);
					radiobtn3.setSelected(false);
					radiobtn4.setSelected(false);
				}
				else
				{
					summary=radiobtn2.getText();
					radiobtn2.setSelected(true);
					radiobtn1.setSelected(false);
					radiobtn3.setSelected(false);
					radiobtn4.setSelected(false);
				}
		 	}
		 });
		 radiobtn4.setBackground(Color.WHITE);
		radiobtn4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		radiobtn4.setBounds(213, 529, 699, 33);
		frame.getContentPane().add(radiobtn4);
		
		 radiobtn3 = new JRadioButton("A person can be taken advantage of by knowing what they are hungry for.");
		 radiobtn3.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		if(radiobtn3.isSelected())
				{
					summary=radiobtn3.getText();
					radiobtn1.setSelected(false);
					radiobtn2.setSelected(false);
					radiobtn4.setSelected(false);
				}
				else
				{
					summary=radiobtn3.getText();
					radiobtn3.setSelected(true);
					radiobtn2.setSelected(false);
					radiobtn1.setSelected(false);
					radiobtn4.setSelected(false);
				}
		 	}
		 });
		 radiobtn3.setBackground(Color.WHITE);
		radiobtn3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		radiobtn3.setBounds(213, 478, 699, 33);
		frame.getContentPane().add(radiobtn3);
		
		 radiobtn2 = new JRadioButton("Studies show that you may be lied to anywhere between 10 to 200 times by strangers.");
		 radiobtn2.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		if(radiobtn2.isSelected())
				{
					summary=radiobtn2.getText();
					radiobtn1.setSelected(false);
					radiobtn3.setSelected(false);
					radiobtn4.setSelected(false);
				}
				else
				{
					summary=radiobtn2.getText();
					radiobtn2.setSelected(true);
					radiobtn1.setSelected(false);
					radiobtn3.setSelected(false);
					radiobtn4.setSelected(false);
				}
		 	}
		 });
		 radiobtn2.setBackground(Color.WHITE);
		radiobtn2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		radiobtn2.setBounds(213, 429, 699, 33);
		frame.getContentPane().add(radiobtn2);
		
		 radiobtn1 = new JRadioButton("Strangers are three times more likely to lie to each other.");
		 radiobtn1.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		if(radiobtn1.isSelected())
				{
					summary=radiobtn1.getText();
					radiobtn2.setSelected(false);
					radiobtn3.setSelected(false);
					radiobtn4.setSelected(false);
				}
				else
				{
					summary=radiobtn1.getText();
					radiobtn1.setSelected(true);
					radiobtn2.setSelected(false);
					radiobtn3.setSelected(false);
					radiobtn4.setSelected(false);
				}
		 	}
		 });
		 radiobtn1.setBackground(Color.WHITE);
		radiobtn1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		radiobtn1.setBounds(213, 381, 699, 33);
		frame.getContentPane().add(radiobtn1);
		
		JLabel label_1 = new JLabel("Choose the statement that matches the ones made by the speaker.");
		label_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		label_1.setBounds(134, 331, 654, 33);
		frame.getContentPane().add(label_1);
		
		JButton button = new JButton("Next");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(p!=null)
				{
					p.close();
					p=null;
				}
				try {
					fileSaver();
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				frame.dispose();
				Set1listening12 go= new Set1listening12();
				go.frame.setVisible(true);
			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 12));
		button.setBackground(Color.WHITE);
		button.setBounds(1165, 615, 89, 28);
		frame.getContentPane().add(button);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(255, 140, 0));
		panel_1.setBounds(0, 55, 1370, 33);
		frame.getContentPane().add(panel_1);
		
		JLabel label_3 = new JLabel("<html><p align='CENTER'>Listen to the recording and answer the multiple-choice question by selecting the correct response. Only one response is correct.</p></html>");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_3.setBounds(65, 93, 1188, 28);
		frame.getContentPane().add(label_3);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new MatteBorder(2, 1, 2, 1, (Color) new Color(51, 0, 153)));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(531, 154, 277, 141);
		frame.getContentPane().add(panel_2);
		
		JLabel label_4 = new JLabel("Status");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_4.setBounds(92, 11, 85, 30);
		panel_2.add(label_4);
		
		 statusLabel = new JLabel("beginning in 4 seconds");
		statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		statusLabel.setBounds(66, 52, 152, 27);
		panel_2.add(statusLabel);
		
		 progressBar = new JProgressBar();
		progressBar.setBackground(new Color(224, 255, 255));
		progressBar.setForeground(new Color(0, 51, 102));
		progressBar.setBorder(new LineBorder(new Color(30, 144, 255), 2, true));
		progressBar.setBorderPainted(true);
		progressBar.setStringPainted(true);
		progressBar.setString("");
		progressBar.setBounds(21, 94, 234, 26);
		panel_2.add(progressBar);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(0, 0, 1370, 56);
		frame.getContentPane().add(panel_3);
		
		JLabel lblListeningTest = new JLabel("Listening Test");
		lblListeningTest.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblListeningTest.setBounds(10, 0, 704, 56);
		panel_3.add(lblListeningTest);
		
		JLabel label = new JLabel("\u00A9Lab Symbiotic");
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.DARK_GRAY);
		label.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		label.setBounds(0, 704, 1370, 24);
		frame.getContentPane().add(label);
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
						progressBar.setMaximum(347);
						do{
							progressBar.setValue(progressBar.getValue()+1);
							try {
								TimeUnit.MILLISECONDS.sleep(250);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}while(progressBar.getValue()<347);
					}
				};
				Runnable run=new Runnable(){
					public void run(){
						try {
							Connection conn= sqlConnection.dbConnection();
							PreparedStatement pstmt=conn.prepareStatement("SELECT listeningMcqSingle FROM test1question WHERE rowid=?;");
							pstmt.setInt(1, 2);  // 1st listening mcq
							ResultSet rs=pstmt.executeQuery();
							InputStream input = rs.getBinaryStream("listeningMcqSingle");
							//FileInputStream fis=new FileInputStream("sounds\\S1.mp3");
							p=new Player(input);
							
							p.play();
							rs.close();
							pstmt.close();
							conn.close();
						} catch (JavaLayerException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						statusLabel.setText("playback stopped");
						
						frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					}
				};
				Thread tr1=new Thread(run1);
				Thread tr=new Thread(run);
				tr1.start();
				tr.start();
			}
			
			
			public void fileSaver() throws IOException, ClassNotFoundException{
				File answerFolder = new File("Answer");
				if(answerFolder.exists())
				{
					File answer=new File("Answer//ListeningAns.ser");
					if(answer.exists())
					{
						FileInputStream fis=new FileInputStream(answer);
						ObjectInputStream ois=new ObjectInputStream(fis);
						ListeningAnswer la=(ListeningAnswer)ois.readObject();
						/*if(radiobtn3.isSelected())
						{
							la.highlightCorrectSummary=radiobtn3.getText();
						}*/
						la.mcqSingleAnswer2=summary;
						ois.close();
						fis.close();
						answer.delete();
						FileOutputStream fos=new FileOutputStream(new File("Answer//ListeningAns.ser"),true);
						ObjectOutputStream oos=new ObjectOutputStream(new BufferedOutputStream (fos));
						oos.writeObject(la);
						oos.close();
						fos.close();
					}
					else
					{
						JOptionPane.showMessageDialog(frame,"listeningAns.ser file is missing!");
					}
				}
				
			}

}
