package set2Listening;

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

public class Set2LMCQMulti2 {

	public JFrame frame;
	Player p;
	JLabel statusLabel;
	JProgressBar progressBar;
	public int val=4;
	Thread player1;
	JCheckBox check1,check2,check3,check4,check5;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Set2LMCQMulti2 window = new Set2LMCQMulti2();
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
	public Set2LMCQMulti2() {
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
						conn.createStatement().executeUpdate("DELETE FROM set2speaking;");
						conn.createStatement().executeUpdate("DELETE FROM set2SerFiles;");
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
		
		JButton button = new JButton("Submit");
		button.setForeground(Color.DARK_GRAY);
		button.setFont(new Font("Tahoma", Font.BOLD, 12));
		button.setEnabled(false);
		button.setBackground(Color.WHITE);
		button.setBounds(1203, 14, 134, 30);
		panel.add(button);
		
		JLabel label = new JLabel("Listening Test (Multiple choice - choose multiple answers)");
		label.setFont(new Font("Tahoma", Font.BOLD, 24));
		label.setBounds(10, 0, 704, 56);
		panel.add(label);
		
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
		panel_2.setBorder(new LineBorder(new Color(112, 128, 144)));
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
		progressBar.setBounds(21, 104, 234, 26);
		panel_2.add(progressBar);
		
		 check1 = new JCheckBox("The audience have seen him being interviewed on the Daily Show with John Stuart.");
		check1.setHorizontalAlignment(SwingConstants.LEFT);
		check1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		check1.setBackground(Color.WHITE);
		check1.setBounds(178, 407, 598, 20);
		frame.getContentPane().add(check1);
		
		 check2 = new JCheckBox("He was surprised that the person known as \u201CThe Lion Whisperer\u201D said that he found doing stand-up comedy to be too scary despite him living with lions.");
		check2.setHorizontalAlignment(SwingConstants.LEFT);
		check2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		check2.setBackground(Color.WHITE);
		check2.setBounds(178, 440, 956, 20);
		frame.getContentPane().add(check2);
		
		JLabel lblWhichOfThe = new JLabel("Which of the following statements correctly describe the speaker\u2019s experiences related to humour and comedy?");
		lblWhichOfThe.setForeground(new Color(0, 0, 128));
		lblWhichOfThe.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblWhichOfThe.setBounds(134, 332, 967, 28);
		frame.getContentPane().add(lblWhichOfThe);
		
		 check3 = new JCheckBox("He discussed humour and comedy with \u201CThe Lion Whisperer\u201D at an event in South Africa.");
		check3.setHorizontalAlignment(SwingConstants.LEFT);
		check3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		check3.setBackground(Color.WHITE);
		check3.setBounds(178, 473, 873, 20);
		frame.getContentPane().add(check3);
		
		 check4 = new JCheckBox("The speaker feels intimidated when talking to people about humour and comedy.");
		check4.setHorizontalAlignment(SwingConstants.LEFT);
		check4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		check4.setBackground(Color.WHITE);
		check4.setBounds(178, 506, 956, 20);
		frame.getContentPane().add(check4);
		
		 check5 = new JCheckBox("The speaker wants everyone to know that humour is a skill and it can be learned, like he has done.");
		check5.setHorizontalAlignment(SwingConstants.LEFT);
		check5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		check5.setBackground(Color.WHITE);
		check5.setBounds(178, 541, 826, 20);
		frame.getContentPane().add(check5);
		
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
					p=null;
				}
				try {
					fileSaver();
				} catch (ClassNotFoundException | IOException e) {
					JOptionPane.showMessageDialog(frame, e.getMessage());
					e.printStackTrace();
				}
				frame.dispose();
				Set2LFillBlank1 go= new Set2LFillBlank1();
				go.frame.setVisible(true);
			}
		});
		button_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_1.setBackground(Color.WHITE);
		button_1.setBounds(1165, 615, 89, 28);
		frame.getContentPane().add(button_1);
		
		JLabel label_6 = new JLabel("Lab Symbiotic");
		label_6.setHorizontalAlignment(SwingConstants.TRAILING);
		label_6.setForeground(Color.DARK_GRAY);
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_6.setBounds(1188, 667, 156, 20);
		frame.getContentPane().add(label_6);
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
							PreparedStatement pstmt=conn.prepareStatement("SELECT listeningMcqMulti FROM test2question WHERE rowid=?;");
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
							System.out.println(la.mcqMulitiAnswer[0]);
							//la.mcqMulitiAnswer.add(chckbox1.getText().trim());
						}
						else if(check2.isSelected())
						{
							la.mcqMulitiAnswer2[1]=check2.getText().trim();
							System.out.println(check2.getText().trim());
							//la.mcqMulitiAnswer.add(chckbox2.getText().trim());
						}
						else if(check3.isSelected())
						{
							la.mcqMulitiAnswer2[2]=check3.getText().trim();
							
						}
						else if(check4.isSelected())
						{
							la.mcqMulitiAnswer2[3]=check4.getText().trim();
							
							//la.mcqMulitiAnswer.add(chckbox4.getText().trim());
						}
						else if(check5.isSelected())
						{
							la.mcqMulitiAnswer2[4]=check5.getText().trim();
							
							//la.mcqMulitiAnswer.add(chckbox5.getText().trim());
						}
						
						ois.close();
						fis.close();
						answer.delete();
						FileOutputStream fos=new FileOutputStream(new File("Answer//ListeningAns.ser"),true);
						ObjectOutputStream oos=new ObjectOutputStream(new BufferedOutputStream (fos));
						oos.writeObject(la);
						oos.close();
						fos.close();
					}
					
				}
				else
				{
					answerFolder.mkdirs();
					File answer=new File("Answer//ListeningAns.ser");
					ListeningAnswer la=new ListeningAnswer();
					if(check1.isSelected())
					{
						la.mcqMulitiAnswer2[0]=check1.getText().trim();
						System.out.println(la.mcqMulitiAnswer[0]);
						//la.mcqMulitiAnswer.add(chckbox1.getText().trim());
					}
					else if(check2.isSelected())
					{
						la.mcqMulitiAnswer2[1]=check2.getText().trim();
						System.out.println(check2.getText().trim());
						//la.mcqMulitiAnswer.add(chckbox2.getText().trim());
					}
					else if(check3.isSelected())
					{
						la.mcqMulitiAnswer2[2]=check3.getText().trim();
						
					}
					else if(check4.isSelected())
					{
						la.mcqMulitiAnswer2[3]=check4.getText().trim();
						
						//la.mcqMulitiAnswer.add(chckbox4.getText().trim());
					}
					else if(check5.isSelected())
					{
						la.mcqMulitiAnswer2[4]=check5.getText().trim();
						
						//la.mcqMulitiAnswer.add(chckbox5.getText().trim());
					}
					
					FileOutputStream fos=new FileOutputStream(answer,true);
					ObjectOutputStream oos=new ObjectOutputStream(new BufferedOutputStream (fos));
					oos.writeObject(la);
					oos.close();
					fos.close();
				}
			}
}
