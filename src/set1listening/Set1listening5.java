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
import javax.sound.sampled.LineUnavailableException;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JProgressBar;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;

public class Set1listening5 {

	public JFrame frame;
	Player p;
	JLabel statusLabel;
	JProgressBar progressBar;
	public int val=4;
	Thread player1;
	JCheckBox check1,check2,check3,check4,check5,check6;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Set1listening5 window = new Set1listening5();
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
	public Set1listening5() {
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
					player1=null;
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
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1370, 56);
		frame.getContentPane().add(panel);
		
		JLabel lblListeningTest = new JLabel("Listening Test");
		lblListeningTest.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblListeningTest.setBounds(10, 0, 704, 56);
		panel.add(lblListeningTest);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(255, 140, 0));
		panel_1.setBounds(0, 55, 1370, 33);
		frame.getContentPane().add(panel_1);
		
		JLabel label_1 = new JLabel("<html><p align='CENTER'>Listen to the recording and answer the question by selecting all the correct responses. You will need to select more than one response.</p></html>");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_1.setBounds(85, 99, 1188, 39);
		frame.getContentPane().add(label_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new MatteBorder(2, 1, 2, 1, (Color) new Color(51, 0, 153)));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(526, 165, 277, 147);
		frame.getContentPane().add(panel_2);
		
		JLabel label_2 = new JLabel("Status");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_2.setBounds(92, 11, 85, 30);
		panel_2.add(label_2);
		
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
		progressBar.setBounds(21, 104, 234, 26);
		panel_2.add(progressBar);
		
		 check1 = new JCheckBox("<html><p>The speaker joined a group that fiercely opposed bringing controversial speakers to campus.</p></html>");
		check1.setHorizontalAlignment(SwingConstants.LEFT);
		check1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		check1.setBackground(Color.WHITE);
		check1.setBounds(176, 367, 598, 20);
		frame.getContentPane().add(check1);
		
		 check2 = new JCheckBox("<html><p>It was difficult for the speaker to see how bringing controversial speakers to campus could be valuable when they caused harm.</p></html>");
		check2.setHorizontalAlignment(SwingConstants.LEFT);
		check2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		check2.setBackground(Color.WHITE);
		check2.setBounds(176, 400, 807, 20);
		frame.getContentPane().add(check2);
		
		JLabel lblWhichOfThe = new JLabel("Which of the following statements are true?");
		lblWhichOfThe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblWhichOfThe.setBounds(134, 332, 709, 28);
		frame.getContentPane().add(lblWhichOfThe);
		
		 check3 = new JCheckBox("<html><p>The speaker was disappointed for being attacked personally, the administration canceling speakers and others misinterpreting his intentions.</p></html>");
		check3.setHorizontalAlignment(SwingConstants.LEFT);
		check3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		check3.setBackground(Color.WHITE);
		check3.setBounds(176, 433, 873, 20);
		frame.getContentPane().add(check3);
		
		 check4 = new JCheckBox("<html><p>The speaker understands that for some people who have had traumatic experiences, listening to controversial speakers can be like reliving that trauma.</p></html>");
		check4.setHorizontalAlignment(SwingConstants.LEFT);
		check4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		check4.setBackground(Color.WHITE);
		check4.setBounds(176, 466, 956, 20);
		frame.getContentPane().add(check4);
		
		 check5 = new JCheckBox("<html><p>It makes the speaker\u2019s stomach turn when people argue that giving controversial speakers a platform does more harm than good.</p></html>");
		check5.setHorizontalAlignment(SwingConstants.LEFT);
		check5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		check5.setBackground(Color.WHITE);
		check5.setBounds(176, 501, 826, 20);
		frame.getContentPane().add(check5);
		
		 check6 = new JCheckBox("<html><p>The speaker says that we need to understand what opposes society to progress in order to move forward.</p></html>");
		check6.setHorizontalAlignment(SwingConstants.LEFT);
		check6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		check6.setBackground(Color.WHITE);
		check6.setBounds(176, 534, 956, 20);
		frame.getContentPane().add(check6);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new LineBorder(Color.DARK_GRAY, 2));
		panel_3.setBackground(new Color(224, 255, 255));
		panel_3.setBounds(134, 615, 154, 33);
		frame.getContentPane().add(panel_3);
		
		JLabel lblItemNo = new JLabel("Item no 4 of 17");
		lblItemNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblItemNo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblItemNo.setBounds(10, 0, 144, 33);
		panel_3.add(lblItemNo);
		
		JButton button_1 = new JButton("Next");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(p!=null)
				{
					p.close();
					p=null;
				}
				try {
					fileSaver();
				} catch (ClassNotFoundException | IOException e) {
					JOptionPane.showMessageDialog(frame, e.getMessage());
					e.printStackTrace();
				}
				frame.dispose();
				Set1listening6 go= new Set1listening6();
				go.frame.setVisible(true);
			}
		});
		button_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_1.setBackground(Color.WHITE);
		button_1.setBounds(1165, 615, 89, 28);
		frame.getContentPane().add(button_1);
		
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
						progressBar.setMaximum(198);
						do{
							progressBar.setValue(progressBar.getValue()+1);
							try {
								TimeUnit.MILLISECONDS.sleep(500);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}while(progressBar.getValue()<198);
					}
				};
				Runnable run=new Runnable(){
					public void run(){
						try {
							Connection conn= sqlConnection.dbConnection();
							PreparedStatement pstmt=conn.prepareStatement("SELECT listeningMcqMulti FROM test1question WHERE rowid=?;");
							pstmt.setInt(1, 2);  // 1st listening mcq
							ResultSet rs=pstmt.executeQuery();
							InputStream input = rs.getBinaryStream("listeningMcqMulti");
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
				player1=new Thread(run);
				tr1.start();
				player1.start();
				
				//Thread.sleep(5000);
				//recorder();
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
						
						if(check1.isSelected())
						{
							la.mcqMulitiAnswer2[0]=check1.getText().trim();
							System.out.println(la.mcqMulitiAnswer2[0]);
							//la.mcqMulitiAnswer.add(chckbox1.getText().trim());
						}
						else
						{
							la.mcqMulitiAnswer2[0]="";
						}
						if(check2.isSelected())
						{
							la.mcqMulitiAnswer2[1]=check2.getText().trim();
							System.out.println(la.mcqMulitiAnswer2[1]);
							//la.mcqMulitiAnswer.add(chckbox2.getText().trim());
						}
						else
						{
							la.mcqMulitiAnswer2[1]="";
						}
						
						if(check3.isSelected())
						{
							la.mcqMulitiAnswer2[2]=check3.getText().trim();
							System.out.println(la.mcqMulitiAnswer2[2]);
						}
						else
						{
							la.mcqMulitiAnswer2[2]="";
						}
						if(check4.isSelected())
						{
							la.mcqMulitiAnswer2[3]=check4.getText().trim();
							System.out.println(la.mcqMulitiAnswer2[3]);
						}
						else
						{
							la.mcqMulitiAnswer2[3]="";
						}
						if(check5.isSelected())
						{
							la.mcqMulitiAnswer2[4]=check5.getText().trim();
							System.out.println(la.mcqMulitiAnswer2[4]);
						}
						else
						{
							la.mcqMulitiAnswer2[4]="";
						}
						if(check6.isSelected())
						{
							la.mcqMulitiAnswer2[5]=check6.getText().trim();
							System.out.println(la.mcqMulitiAnswer2[5]);
						}
						else
						{
							la.mcqMulitiAnswer2[5]="";
						}
						
						ois.close();
						fis.close();
						answer.delete();
						FileOutputStream fos=new FileOutputStream(new File("Answer//ListeningAns.ser"),true);
						ObjectOutputStream oos=new ObjectOutputStream(new BufferedOutputStream (fos));
						System.out.println(la.mcqMulitiAnswer2);
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
