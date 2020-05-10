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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
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

public class Set2HighMissWord2 {

	public JFrame frame;
	JLabel statusLabel;
	JProgressBar progressBar;
	public static int val=4;
	public String summary;
	JRadioButton radiobtn1;
	JRadioButton radiobtn2;
	JRadioButton radiobtn3;
	JRadioButton radiobtn4;
	Player p;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Set2HighMissWord2 window = new Set2HighMissWord2();
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
	public Set2HighMissWord2() {
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
					File answer=new File("Answer//ListeningAns.ser");
					answer.delete();
					Connection conn=sqlConnection.answerDBConnection();
					try {
						conn.createStatement().executeUpdate("DELETE FROM set1speaking;");
						conn.createStatement().executeUpdate("DELETE FROM set2SerFiles;");
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
		frame.setBackground(Color.DARK_GRAY);
		frame.setIconImage(Functions.setIcon());
		frame.setTitle("Listening Test");
		frame.setFocusable(true);
		frame.setSize(new Dimension(screenSize.width+4, 730));
		frame.getContentPane().setPreferredSize(new Dimension(screenSize.width+4, 730));
		frame.setAlwaysOnTop(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(-2, -1);
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
		
		JLabel label = new JLabel("Listening Test (Multiple choice - Select missing word)");
		label.setFont(new Font("Tahoma", Font.BOLD, 24));
		label.setBounds(10, 0, 704, 56);
		panel.add(label);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(255, 140, 0));
		panel_1.setBounds(0, 55, 1370, 33);
		frame.getContentPane().add(panel_1);
		
		JLabel label_1 = new JLabel("<html><p align='CENTER'>You will hear a recording. At the end of the recording the last word or group of words has been replaced by a beep. Select the correct option to complete the recording.</p></html>");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_1.setBounds(65, 93, 1188, 28);
		frame.getContentPane().add(label_1);
		
		JButton button_2 = new JButton("Next");
		button_2.addActionListener(new ActionListener() {
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
				Set2HighIncrrctWord1 go= new Set2HighIncrrctWord1();
				go.frame.setVisible(true);
			}
		});
		button_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_2.setBounds(1165, 615, 89, 28);
		frame.getContentPane().add(button_2);
		
		JLabel label_4 = new JLabel("Lab Symbiotic");
		label_4.setHorizontalAlignment(SwingConstants.TRAILING);
		label_4.setForeground(Color.DARK_GRAY);
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_4.setBounds(1188, 667, 156, 20);
		frame.getContentPane().add(label_4);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new LineBorder(Color.DARK_GRAY, 2));
		panel_3.setBackground(new Color(224, 255, 255));
		panel_3.setBounds(134, 615, 154, 33);
		frame.getContentPane().add(panel_3);
		
		JLabel lblItemNo = new JLabel("Item no 12 of 17");
		lblItemNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblItemNo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblItemNo.setBounds(10, 0, 144, 33);
		panel_3.add(lblItemNo);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setLayout(null);
		panel_2.setBorder(new LineBorder(new Color(112, 128, 144)));
		panel_2.setBounds(483, 166, 277, 138);
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
		progressBar.setForeground(new Color(244, 164, 96));
		progressBar.setStringPainted(true);
		progressBar.setBackground(new Color(224, 255, 255));
		progressBar.setString("");
		progressBar.setBounds(21, 90, 234, 26);
		panel_2.add(progressBar);
		
		JLabel lblWhichOfThe = new JLabel("Which of the following options would be the most appropriate final words of the speaker?");
		lblWhichOfThe.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblWhichOfThe.setBounds(279, 329, 654, 33);
		frame.getContentPane().add(lblWhichOfThe);
		
		radiobtn1 = new JRadioButton("Trustworthy environment.");
		radiobtn1.setBackground(Color.WHITE);
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
		radiobtn1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		radiobtn1.setBounds(309, 380, 451, 33);
		frame.getContentPane().add(radiobtn1);
		
		radiobtn2 = new JRadioButton("Lawless community.");
		radiobtn2.setBackground(Color.WHITE);
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
		radiobtn2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		radiobtn2.setBounds(309, 428, 232, 33);
		frame.getContentPane().add(radiobtn2);
		
		radiobtn3 = new JRadioButton("Post-truth society.");
		radiobtn3.setBackground(Color.WHITE);
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
		radiobtn3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		radiobtn3.setBounds(309, 477, 232, 33);
		frame.getContentPane().add(radiobtn3);
		
		radiobtn4 = new JRadioButton("Truth seeking world.");
		radiobtn4.setBackground(Color.WHITE);
		radiobtn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(radiobtn4.isSelected())
				{
					summary=radiobtn4.getText();
					radiobtn2.setSelected(false);
					radiobtn3.setSelected(false);
					radiobtn1.setSelected(false);
				}
				else
				{
					summary=radiobtn4.getText();
					radiobtn4.setSelected(true);
					radiobtn2.setSelected(false);
					radiobtn3.setSelected(false);
					radiobtn1.setSelected(false);
				}
			}
		});
		radiobtn4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		radiobtn4.setBounds(309, 528, 232, 33);
		frame.getContentPane().add(radiobtn4);
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
				progressBar.setMaximum(152);
				do{
					progressBar.setValue(progressBar.getValue()+1);
					try {
						TimeUnit.MILLISECONDS.sleep(250);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}while(progressBar.getValue()<152);
			}
		};
		Runnable run=new Runnable(){
			public void run(){
				try {
					Connection conn= sqlConnection.dbConnection();
					PreparedStatement pstmt=conn.prepareStatement("SELECT listeningMissingWord FROM test1question WHERE rowid=?;");
					pstmt.setInt(1, 2);  // 2nd listening mcq
					ResultSet rs=pstmt.executeQuery();
					InputStream input = rs.getBinaryStream("listeningMissingWord");
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
				/*if(radiobtn3.isSelected())
				{
					la.highlightCorrectSummary=radiobtn3.getText();
				}*/
				la.missingWord2=summary;
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
			la.missingWord2=summary;
			FileOutputStream fos=new FileOutputStream(answer,true);
			ObjectOutputStream oos=new ObjectOutputStream(new BufferedOutputStream (fos));
			oos.writeObject(la);
			oos.close();
			fos.close();
		}
	}

}
