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

public class Set2LMCQSingle2 {

	public JFrame frame;
	
	public int val=4;
	JLabel statusLabel;
	JProgressBar progressBar;
	JRadioButton radio1,radio2,radio3,radio4;
	Player p;
	String summary;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Set2LMCQSingle2 window = new Set2LMCQSingle2();
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
	public Set2LMCQSingle2() {
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
		panel.setBorder(new LineBorder(Color.DARK_GRAY, 2));
		panel.setBackground(new Color(224, 255, 255));
		panel.setBounds(134, 615, 154, 33);
		frame.getContentPane().add(panel);
		
		JLabel lblItemNo = new JLabel("Item no 10 of 17");
		lblItemNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblItemNo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblItemNo.setBounds(10, 0, 144, 33);
		panel.add(lblItemNo);
		
		 radio4 = new JRadioButton("He claims that it is the person who is nice to your face and will stab you in the back.");
		radio4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		radio4.setBounds(213, 529, 573, 33);
		frame.getContentPane().add(radio4);
		
		 radio3 = new JRadioButton("<html><p >By asking whose careers they have fundamentally improved and seeing if they mention people above or below them in the hierarchy.</p></html>");
		 radio3.setVerticalAlignment(SwingConstants.TOP);
		radio3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		radio3.setBounds(213, 478, 699, 41);
		frame.getContentPane().add(radio3);
		
		 radio2 = new JRadioButton("He notes that Fakers are more likely to name people who are below them in a hierarchy.");
		radio2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		radio2.setBounds(213, 429, 699, 33);
		frame.getContentPane().add(radio2);
		
		 radio1 = new JRadioButton("By watching how someone treats their restaurant server or their Uber driver.");
		radio1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		radio1.setBounds(213, 381, 699, 33);
		frame.getContentPane().add(radio1);
		
		JLabel lblWhatAreThe = new JLabel("What does the speaker say is his favourite way to catch an Agreeable Taker or a Faker?");
		lblWhatAreThe.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblWhatAreThe.setBounds(134, 331, 654, 33);
		frame.getContentPane().add(lblWhatAreThe);
		
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
				Set2HighMissWord1 go= new Set2HighMissWord1();
				go.frame.setVisible(true);
			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 12));
		button.setBackground(Color.WHITE);
		button.setBounds(1165, 615, 89, 28);
		frame.getContentPane().add(button);
		
		JLabel label_2 = new JLabel("Lab Symbiotic");
		label_2.setHorizontalAlignment(SwingConstants.TRAILING);
		label_2.setForeground(Color.DARK_GRAY);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_2.setBounds(1188, 667, 156, 20);
		frame.getContentPane().add(label_2);
		
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
		panel_2.setBorder(new LineBorder(new Color(112, 128, 144)));
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
		progressBar.setBounds(21, 94, 234, 26);
		panel_2.add(progressBar);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(0, 0, 1370, 56);
		frame.getContentPane().add(panel_3);
		
		JButton button_1 = new JButton("Submit");
		button_1.setForeground(Color.DARK_GRAY);
		button_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_1.setEnabled(false);
		button_1.setBackground(Color.WHITE);
		button_1.setBounds(1203, 14, 134, 30);
		panel_3.add(button_1);
		
		JLabel label_6 = new JLabel("Listening Test (Multiple choice - choose single answer)");
		label_6.setFont(new Font("Tahoma", Font.BOLD, 24));
		label_6.setBounds(10, 0, 704, 56);
		panel_3.add(label_6);
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
							PreparedStatement pstmt=conn.prepareStatement("SELECT listeningMcqSingle FROM test2question WHERE rowid=?;");
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
				}
				else
				{
					answerFolder.mkdirs();
					File answer=new File("Answer//ListeningAns.ser");
					ListeningAnswer la=new ListeningAnswer();
					/*if(radiobtn3.isSelected())
					{
						la.highlightCorrectSummary=radiobtn3.getText();
					}*/
					la.mcqSingleAnswer2=summary;
					FileOutputStream fos=new FileOutputStream(answer,true);
					ObjectOutputStream oos=new ObjectOutputStream(new BufferedOutputStream (fos));
					oos.writeObject(la);
					oos.close();
					fos.close();
				}
			}

}
