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
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;

public class Set1listening9 {

	public JFrame frame;
	JRadioButton radio1,radio2,radio3,radio4;
	public int val=4;
	Player p;
	JLabel statusLabel;
	JProgressBar progressBar;
	String summary;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Set1listening9 window = new Set1listening9();
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
	public Set1listening9() {
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
		
		JLabel lblListeningTest = new JLabel("Listening Test ");
		lblListeningTest.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblListeningTest.setBounds(10, 0, 704, 56);
		panel.add(lblListeningTest);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(255, 140, 0));
		panel_1.setBounds(0, 55, 1370, 33);
		frame.getContentPane().add(panel_1);
		
		JLabel label_1 = new JLabel("<html><p align='CENTER'>You will hear a recording. Click on the paragraph that best relates to the recording.</p></html>");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_1.setBounds(65, 93, 1188, 28);
		frame.getContentPane().add(label_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new MatteBorder(2, 1, 2, 1, (Color) new Color(51, 0, 153)));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(525, 149, 277, 147);
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
		
		 radio1 = new JRadioButton("<html><p>The speaker had a complicated and challenging relationship with his mother who was diagnosed with Schizophrenia because of which it was decided that he needed to live apart from her.</p></html>");
		 radio1.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		if(radio1.isSelected())
				{
					summary=radio1.getText();
					radio2.setSelected(false);
					radio3.setSelected(false);
					radio4.setSelected(false);
				}
				else
				{
					summary=radio1.getText();
					radio1.setSelected(true);
					radio2.setSelected(false);
					radio3.setSelected(false);
					radio4.setSelected(false);
				}
		 	}
		 });
		radio1.setVerticalAlignment(SwingConstants.TOP);
		radio1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		radio1.setBackground(Color.WHITE);
		radio1.setBounds(248, 329, 923, 47);
		frame.getContentPane().add(radio1);
		
		 radio2 = new JRadioButton("<html><p >Despite of being fearful of his mother due to her suffering from Schizophrenia, the speaker appreciates some of the life lesson she taught him, including seeing the world and the issues faced in it as dynamic and complex and that there is value in listening to opinions he may not like to hear.</p></html>");
		 radio2.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		if(radio2.isSelected())
				{
					summary=radio2.getText();
					radio1.setSelected(false);
					radio3.setSelected(false);
					radio4.setSelected(false);
				}
				else
				{
					summary=radio2.getText();
					radio2.setSelected(true);
					radio1.setSelected(false);
					radio3.setSelected(false);
					radio4.setSelected(false);
				}
		 	}
		 });
		radio2.setVerticalAlignment(SwingConstants.TOP);
		radio2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		radio2.setBackground(Color.WHITE);
		radio2.setBounds(248, 390, 1022, 69);
		frame.getContentPane().add(radio2);
		
		 radio3 = new JRadioButton("<html><p>At the age of 14, it was decided that the speaker should live apart from his mother who had been suffering from Schizophrenia, and he agreed as his mother taught him not to write off opinions that he disagreed with or disliked because there is always something to learn from the perspective of others, even when doing so might be difficult.</p></html>");
		 radio3.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		if(radio3.isSelected())
				{
					summary=radio3.getText();
					radio2.setSelected(false);
					radio3.setSelected(false);
					radio4.setSelected(false);
				}
				else
				{
					summary=radio3.getText();
					radio3.setSelected(true);
					radio2.setSelected(false);
					radio1.setSelected(false);
					radio4.setSelected(false);
				}
		 	}
		 });
		radio3.setVerticalAlignment(SwingConstants.TOP);
		radio3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		radio3.setBackground(Color.WHITE);
		radio3.setBounds(248, 475, 923, 47);
		frame.getContentPane().add(radio3);
		
		 radio4 = new JRadioButton("<html><p>Whenever his mother would get angry, the speaker would remind himself of the important lessons she taught him about life like the idea that there was something to be learned from listening to opposing views, even if it was hard for the speaker.</p></html>");
		 radio4.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		if(radio4.isSelected())
				{
					summary=radio4.getText();
					radio1.setSelected(false);
					radio2.setSelected(false);
					radio3.setSelected(false);
				}
				else
				{
					summary=radio4.getText();
					radio4.setSelected(true);
					radio2.setSelected(false);
					radio1.setSelected(false);
					radio3.setSelected(false);
				}
		 	}
		 });
		radio4.setVerticalAlignment(SwingConstants.TOP);
		radio4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		radio4.setBackground(Color.WHITE);
		radio4.setBounds(248, 541, 923, 47);
		frame.getContentPane().add(radio4);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new LineBorder(Color.DARK_GRAY, 2));
		panel_3.setBackground(new Color(224, 255, 255));
		panel_3.setBounds(134, 615, 154, 33);
		frame.getContentPane().add(panel_3);
		
		JLabel lblItemNo = new JLabel("Item no 8 of 17");
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				frame.dispose();
				Set1listening10 go= new Set1listening10();
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
					progressBar.setMaximum(300);
					do{
						progressBar.setValue(progressBar.getValue()+1);
						try {
							TimeUnit.MILLISECONDS.sleep(250);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}while(progressBar.getValue()<300);
				}
			};
			Runnable run=new Runnable(){
				public void run(){
					try {
						Connection conn= sqlConnection.dbConnection();
						PreparedStatement pstmt=conn.prepareStatement("SELECT listeningHighSummary FROM test1question WHERE rowid=?;");
						pstmt.setInt(1, 2);  // 1st listening Highlinght Summary
						ResultSet rs=pstmt.executeQuery();
						InputStream input = rs.getBinaryStream("listeningHighSummary");
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
					la.highlightCorrectSummary2=summary;
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
