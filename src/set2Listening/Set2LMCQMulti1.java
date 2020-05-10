package set2Listening;

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

import javax.sound.sampled.LineUnavailableException;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import main.ListeningAnswer;
import main.Menu;
import main.sqlConnection;

public class Set2LMCQMulti1 {

	public JFrame frame;
	Player p;
	JLabel statusLabel;
	public static int val=4;
	JProgressBar progressBar;
	JCheckBox chckbox1;
	JCheckBox chckbox2;
	JCheckBox chckbox3;
	JCheckBox chckbox4;
	JCheckBox chckbox5;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Set2LMCQMulti1 window = new Set2LMCQMulti1();
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
	public Set2LMCQMulti1() {
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
		frame.pack();
		frame.setBackground(Color.WHITE);
		frame.setIconImage(Functions.setIcon());
		frame.setTitle("Listening Test");
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
		
		JButton button = new JButton("Submit");
		button.setForeground(Color.DARK_GRAY);
		button.setFont(new Font("Tahoma", Font.BOLD, 12));
		button.setEnabled(false);
		button.setBackground(Color.WHITE);
		button.setBounds(1203, 14, 134, 30);
		panel.add(button);
		
		JLabel lblListeningTestmultiple = new JLabel("Listening Test (Multiple choice - choose multiple answers)");
		lblListeningTestmultiple.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblListeningTestmultiple.setBounds(10, 0, 704, 56);
		panel.add(lblListeningTestmultiple);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(255, 140, 0));
		panel_1.setBounds(0, 55, 1370, 33);
		frame.getContentPane().add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setLayout(null);
		panel_2.setBorder(new LineBorder(new Color(112, 128, 144)));
		panel_2.setBounds(526, 165, 277, 147);
		frame.getContentPane().add(panel_2);
		
		JLabel label_1 = new JLabel("Status");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_1.setBounds(92, 11, 85, 30);
		panel_2.add(label_1);
		
		statusLabel = new JLabel("beginning in 4 seconds");
		statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		statusLabel.setBounds(66, 52, 152, 27);
		panel_2.add(statusLabel);
		
		progressBar = new JProgressBar();
		progressBar.setBackground(new Color(224, 255, 255));
		progressBar.setBounds(21, 104, 234, 26);
		panel_2.add(progressBar);
		
		JLabel lbllistenToThe = new JLabel("<html><p align='CENTER'>Listen to the recording and answer the question by selecting all the correct responses. You will need to select more than one response.</p></html>");
		lbllistenToThe.setHorizontalAlignment(SwingConstants.CENTER);
		lbllistenToThe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbllistenToThe.setBounds(85, 99, 1188, 39);
		frame.getContentPane().add(lbllistenToThe);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new LineBorder(Color.DARK_GRAY, 2));
		panel_3.setBackground(new Color(224, 255, 255));
		panel_3.setBounds(134, 615, 154, 33);
		frame.getContentPane().add(panel_3);
		
		JLabel lblItemNo = new JLabel("Item no 3 of 17");
		lblItemNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblItemNo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblItemNo.setBounds(10, 0, 144, 33);
		panel_3.add(lblItemNo);
		
		JButton button_2 = new JButton("Next");
		button_2.setBackground(Color.WHITE);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(p!=null)
				{
					p=null;
				}
				try {
					fileSaver();
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				frame.dispose();
				Set2LMCQMulti2 go= new Set2LMCQMulti2();
				go.frame.setVisible(true);
			}
		});
		button_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_2.setBounds(1165, 615, 89, 28);
		frame.getContentPane().add(button_2);
		
		JLabel label_5 = new JLabel("Lab Symbiotic");
		label_5.setHorizontalAlignment(SwingConstants.TRAILING);
		label_5.setForeground(Color.DARK_GRAY);
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_5.setBounds(1188, 667, 156, 20);
		frame.getContentPane().add(label_5);
		
		JLabel lblNewLabel = new JLabel("According to the speaker, which of the following are NOT necessarily a sign of deception despite most people\u2019s beliefs?");
		lblNewLabel.setForeground(new Color(0, 0, 128));
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel.setBounds(134, 332, 950, 28);
		frame.getContentPane().add(lblNewLabel);
		
		chckbox1 = new JCheckBox("A lot of eye contact");
		chckbox1.setBackground(Color.WHITE);
		chckbox1.setHorizontalAlignment(SwingConstants.LEFT);
		chckbox1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbox1.setBounds(204, 404, 584, 20);
		frame.getContentPane().add(chckbox1);
		
		chckbox2 = new JCheckBox("Upper body freezing up.");
		chckbox2.setBackground(Color.WHITE);
		chckbox2.setHorizontalAlignment(SwingConstants.LEFT);
		chckbox2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbox2.setBounds(204, 437, 474, 20);
		frame.getContentPane().add(chckbox2);
		
		chckbox4 = new JCheckBox("Very little eye contact.");
		chckbox4.setBackground(Color.WHITE);
		chckbox4.setHorizontalAlignment(SwingConstants.LEFT);
		chckbox4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbox4.setBounds(204, 503, 467, 20);
		frame.getContentPane().add(chckbox4);
		
		chckbox3 = new JCheckBox("Smiling with crow\u2019s feet around the eyes.");
		chckbox3.setBackground(Color.WHITE);
		chckbox3.setHorizontalAlignment(SwingConstants.LEFT);
		chckbox3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbox3.setBounds(204, 470, 487, 20);
		frame.getContentPane().add(chckbox3);
		
		chckbox5 = new JCheckBox("Fidgeting.");
		chckbox5.setBackground(Color.WHITE);
		chckbox5.setHorizontalAlignment(SwingConstants.LEFT);
		chckbox5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbox5.setBounds(204, 538, 513, 20);
		frame.getContentPane().add(chckbox5);
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
					progressBar.setMaximum(70);
					do{
						progressBar.setValue(progressBar.getValue()+1);
						try {
							TimeUnit.MILLISECONDS.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}while(progressBar.getValue()<70);
				}
			};
			Runnable run=new Runnable(){
				public void run(){
					try {
						Connection conn= sqlConnection.dbConnection();
						PreparedStatement pstmt=conn.prepareStatement("SELECT listeningMcqMulti FROM test2question WHERE rowid=?;");
						pstmt.setInt(1, 1);  // 1st listening mcq
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
			Thread tr=new Thread(run);
			tr1.start();
			tr.start();
			
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
					
					if(chckbox1.isSelected())
					{
						la.mcqMulitiAnswer[0]=chckbox1.getText().trim();
						System.out.println(la.mcqMulitiAnswer[0]);
						//la.mcqMulitiAnswer.add(chckbox1.getText().trim());
					}
					else if(chckbox2.isSelected())
					{
						la.mcqMulitiAnswer[1]=chckbox2.getText().trim();
						System.out.println(chckbox2.getText().trim());
						//la.mcqMulitiAnswer.add(chckbox2.getText().trim());
					}
					else if(chckbox3.isSelected())
					{
						la.mcqMulitiAnswer[2]=chckbox3.getText().trim();
						System.out.println(chckbox3.getText().trim());
						//la.mcqMulitiAnswer.add(chckbox3.getText().trim());
					}
					else if(chckbox4.isSelected())
					{
						la.mcqMulitiAnswer[3]=chckbox4.getText().trim();
						System.out.println(chckbox4.getText().trim());
						//la.mcqMulitiAnswer.add(chckbox4.getText().trim());
					}
					else if(chckbox5.isSelected())
					{
						la.mcqMulitiAnswer[4]=chckbox5.getText().trim();
						System.out.println(chckbox5.getText().trim());
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
					
					/************** after writing ******/
					
					
				}
				
			}
			else
			{
				answerFolder.mkdirs();
				File answer=new File("Answer//ListeningAns.ser");
				ListeningAnswer la=new ListeningAnswer();
				if(chckbox1.isSelected())
				{
					la.mcqMulitiAnswer[0]=chckbox1.getText().trim();
					//la.mcqMulitiAnswer.add(chckbox1.getText().trim());
				}
				if(chckbox2.isSelected())
				{
					la.mcqMulitiAnswer[1]=chckbox2.getText().trim();
					//la.mcqMulitiAnswer.add(chckbox2.getText().trim());
				}
				if(chckbox3.isSelected())
				{
					la.mcqMulitiAnswer[2]=chckbox3.getText().trim();
					//la.mcqMulitiAnswer.add(chckbox3.getText().trim());
				}
				if(chckbox4.isSelected())
				{
					la.mcqMulitiAnswer[3]=chckbox4.getText().trim();
					//la.mcqMulitiAnswer.add(chckbox4.getText().trim());
				}
				if(chckbox5.isSelected())
				{
					la.mcqMulitiAnswer[4]=chckbox5.getText().trim();
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
