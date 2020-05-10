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
import javax.swing.JTextField;
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

public class Set2LFillBlank1 {

	public JFrame frame;
	public JTextField textField3;
	public JTextField textField1;
	public JTextField textField2;
	public JTextField textField4;
	JLabel statusLabel;
	JProgressBar progressBar;
	Player p;
	public static int val=4;
	Thread player1;
	private JTextField textField5;
	private JTextField textField6;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Set2LFillBlank1 window = new Set2LFillBlank1();
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
	public Set2LFillBlank1() {
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
		frame.setForeground(Color.WHITE);
		frame.pack();
		frame.setBackground(Color.WHITE);
		frame.setIconImage(Functions.setIcon());
		frame.setTitle("Listening Test");
		frame.setFocusable(true);
		frame.setSize(new Dimension(screenSize.width+4, 730));
		frame.getContentPane().setPreferredSize(new Dimension(screenSize.width+4, 730));
		frame.setAlwaysOnTop(true);
		frame.setResizable(false);
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
		
		JLabel lblListeningTestfill = new JLabel("Listening Test (Fill in the blanks)");
		lblListeningTestfill.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblListeningTestfill.setBounds(10, 0, 704, 56);
		panel.add(lblListeningTestfill);
		
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
		
		JLabel lblItemNo = new JLabel("Item no 5 of 17");
		lblItemNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblItemNo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblItemNo.setBounds(10, 0, 144, 33);
		panel_2.add(lblItemNo);
		
		JButton button_2 = new JButton("Next");
		button_2.setBackground(Color.WHITE);
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
				Set2LFillBlank2 go= new Set2LFillBlank2();
				go.frame.setVisible(true);
			}
		});
		button_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_2.setBounds(1165, 615, 89, 28);
		frame.getContentPane().add(button_2);
		
		JLabel label_2 = new JLabel("Lab Symbiotic");
		label_2.setHorizontalAlignment(SwingConstants.TRAILING);
		label_2.setForeground(Color.DARK_GRAY);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_2.setBounds(1188, 667, 156, 20);
		frame.getContentPane().add(label_2);
		
		JLabel lblyouWillHear = new JLabel("<html><p align='CENTER'>You will hear a recording. Type the missing words.</p></html>");
		lblyouWillHear.setHorizontalAlignment(SwingConstants.CENTER);
		lblyouWillHear.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblyouWillHear.setBounds(65, 93, 1188, 28);
		frame.getContentPane().add(lblyouWillHear);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setLayout(null);
		panel_3.setBorder(new LineBorder(new Color(112, 128, 144)));
		panel_3.setBounds(530, 171, 277, 147);
		frame.getContentPane().add(panel_3);
		
		JLabel label = new JLabel("Status");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label.setBounds(92, 11, 85, 30);
		panel_3.add(label);
		
		statusLabel = new JLabel("beginning in 4 seconds");
		statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		statusLabel.setBounds(66, 52, 152, 27);
		panel_3.add(statusLabel);
		
		progressBar = new JProgressBar();
		progressBar.setBackground(new Color(224, 255, 255));
		progressBar.setBounds(21, 104, 234, 26);
		panel_3.add(progressBar);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setBounds(134, 324, 1120, 288);
		frame.getContentPane().add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("We can also use a \u201CYes, and\u201D mindset to have more fun. Because the reality is that the average person will work 90 thousand hours in their");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNewLabel.setBounds(10, 11, 937, 20);
		panel_4.add(lblNewLabel);
		
		JLabel lblSteveWeinbergWinner = new JLabel("90 thousand hours. That's the entire length, the entire discography of Netflix. That is a lot of time and we can say, \u201CYes, I'm going to work 90 thousand hours and");
		lblSteveWeinbergWinner.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblSteveWeinbergWinner.setBounds(10, 40, 1100, 20);
		panel_4.add(lblSteveWeinbergWinner);
		
		JLabel lblFrequencyButIn = new JLabel(" I might as well");
		lblFrequencyButIn.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblFrequencyButIn.setBounds(10, 68, 106, 20);
		panel_4.add(lblFrequencyButIn);
		
		JLabel lblRadioIsOnly = new JLabel("Between my junior and senior year of high school I worked in a factory and I will tell you what was not a very");
		lblRadioIsOnly.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblRadioIsOnly.setBounds(10, 110, 738, 20);
		panel_4.add(lblRadioIsOnly);
		
		JLabel lblToEvolveThese = new JLabel("that I might, in the future, want to become an international hip-hop superstar. So to the past a time I would think of");
		lblToEvolveThese.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblToEvolveThese.setBounds(10, 137, 780, 20);
		panel_4.add(lblToEvolveThese);
		
		textField3 = new JTextField();
		textField3.setColumns(10);
		textField3.setBounds(758, 110, 98, 20);
		panel_4.add(textField3);
		
		textField1 = new JTextField();
		textField1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField1.setHorizontalAlignment(SwingConstants.CENTER);
		textField1.setColumns(10);
		textField1.setBounds(957, 11, 113, 20);
		panel_4.add(textField1);
		
		textField2 = new JTextField();
		textField2.setColumns(10);
		textField2.setBounds(118, 68, 106, 20);
		panel_4.add(textField2);
		
		JLabel lblTheyStartTo = new JLabel("them down in a notebook a little bit later. ");
		lblTheyStartTo.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblTheyStartTo.setBounds(10, 166, 432, 20);
		panel_4.add(lblTheyStartTo);
		
		textField4 = new JTextField();
		textField4.setColumns(10);
		textField4.setBounds(800, 137, 98, 20);
		panel_4.add(textField4);
		
		JLabel lblIt = new JLabel("it.");
		lblIt.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblIt.setBounds(234, 68, 20, 20);
		panel_4.add(lblIt);
		
		JLabel lblJobAndAt = new JLabel("job. And at the time, I thought at");
		lblJobAndAt.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblJobAndAt.setBounds(866, 110, 244, 20);
		panel_4.add(lblJobAndAt);
		
		JLabel lblInMyHead = new JLabel("in my head and then I'd write");
		lblInMyHead.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblInMyHead.setBounds(903, 137, 207, 20);
		panel_4.add(lblInMyHead);
		
		JLabel lblOxygenTooBonded = new JLabel("Oxygen too, bonded together with covalent");
		lblOxygenTooBonded.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblOxygenTooBonded.setBounds(10, 233, 297, 20);
		panel_4.add(lblOxygenTooBonded);
		
		JLabel lblAndIRecently = new JLabel("And I recently found one of those notebooks and discovered why I never became a hip-hop superstar. Because one of the rhymes was, \u201CHydrogen plus Hydrogen, ");
		lblAndIRecently.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblAndIRecently.setBounds(10, 206, 1100, 20);
		panel_4.add(lblAndIRecently);
		
		textField5 = new JTextField();
		textField5.setColumns(10);
		textField5.setBounds(310, 233, 106, 20);
		panel_4.add(textField5);
		
		JLabel lblWhatDoYou = new JLabel("What do you get just a thing called water. Yeah, it's");
		lblWhatDoYou.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblWhatDoYou.setBounds(426, 233, 345, 20);
		panel_4.add(lblWhatDoYou);
		
		textField6 = new JTextField();
		textField6.setColumns(10);
		textField6.setBounds(781, 233, 106, 20);
		panel_4.add(textField6);
		
		JLabel lblUpAndIts = new JLabel("up and it's only getting hotter.\u201D");
		lblUpAndIts.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblUpAndIts.setBounds(892, 233, 218, 20);
		panel_4.add(lblUpAndIts);
		
		JLabel lblItsTheReason = new JLabel("It\u2019s the reason why I never became hip-hop superstar. Right? But it still helped me to pass the time.");
		lblItsTheReason.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblItsTheReason.setBounds(10, 260, 1100, 20);
		panel_4.add(lblItsTheReason);
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
						progressBar.setMaximum(480);
						do{
							progressBar.setValue(progressBar.getValue()+1);
							try {
								TimeUnit.MICROSECONDS.sleep(125000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}while(progressBar.getValue()<480);
					}
				};
				Runnable run=new Runnable(){
					public void run(){
						try {
							Connection conn= sqlConnection.dbConnection();
							PreparedStatement pstmt=conn.prepareStatement("SELECT listeningFillblank FROM test2question WHERE rowid=?;");
							pstmt.setInt(1, 1);  // 1st listening mcq
							ResultSet rs=pstmt.executeQuery();
							InputStream input = rs.getBinaryStream("listeningFillblank");
							//FileInputStream fis=new FileInputStream("sounds\\S1.mp3");
							p=new Player(input);
							
							p.play();
							rs.close();
							pstmt.close();
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
			
			/******************************************** file saver *****************************************/
			
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
						ois.close();
						fis.close();
						if(!textField1.getText().equals(null) )
						{
							la.fillBlankAnswer[0]=textField1.getText().trim();
							//la.fillBlankAnswer.add(textField1.getText().trim());
						}
						if(!textField2.getText().equals("") && !textField2.getText().equals(null) )
						{
							la.fillBlankAnswer[1]=textField2.getText().trim();
							//la.fillBlankAnswer.add(textField2.getText().trim());
						}
						if(!textField3.getText().equals("") && !textField3.getText().equals(null) )
						{
							la.fillBlankAnswer[2]=textField3.getText().trim();
							//la.fillBlankAnswer.add(textField3.getText().trim());
						}
						if(!textField4.getText().equals("") && !textField4.getText().equals(null) )
						{
							la.fillBlankAnswer[3]=textField4.getText().trim();
							//la.fillBlankAnswer.add(textField4.getText().trim());
						}
						if(!textField5.getText().equals("") && !textField5.getText().equals(null) )
						{
							la.fillBlankAnswer[4]=textField5.getText().trim();
							//la.fillBlankAnswer.add(textField4.getText().trim());
						}
						if(!textField6.getText().equals("") && !textField6.getText().equals(null) )
						{
							la.fillBlankAnswer[5]=textField6.getText().trim();
							//la.fillBlankAnswer.add(textField4.getText().trim());
						}
						
						answer.delete();
						FileOutputStream fos=new FileOutputStream(new File("Answer//ListeningAns.ser"),true);
						ObjectOutputStream oos=new ObjectOutputStream(new BufferedOutputStream (fos));
						System.out.println(la.summaryAnswer);
						System.out.println(la.fillBlankAnswer.length);
						System.out.println(la.fillBlankAnswer[0]);
						System.out.println(la.fillBlankAnswer[1]);
						System.out.println(la.fillBlankAnswer[2]);
						System.out.println(la.fillBlankAnswer[3]);
						
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
					if(!textField1.getText().equals(null) )
					{
						la.fillBlankAnswer[0]=textField1.getText().trim();
						//la.fillBlankAnswer.add(textField1.getText().trim());
					}
					if(!textField2.getText().equals("") && !textField2.getText().equals(null) )
					{
						la.fillBlankAnswer[1]=textField2.getText().trim();
						//la.fillBlankAnswer.add(textField2.getText().trim());
					}
					if(!textField3.getText().equals("") && !textField3.getText().equals(null) )
					{
						la.fillBlankAnswer[2]=textField3.getText().trim();
						//la.fillBlankAnswer.add(textField3.getText().trim());
					}
					if(!textField4.getText().equals("") && !textField4.getText().equals(null) )
					{
						la.fillBlankAnswer[3]=textField4.getText().trim();
						//la.fillBlankAnswer.add(textField4.getText().trim());
					}
					if(!textField5.getText().equals("") && !textField5.getText().equals(null) )
					{
						la.fillBlankAnswer[4]=textField5.getText().trim();
						//la.fillBlankAnswer.add(textField4.getText().trim());
					}
					if(!textField6.getText().equals("") && !textField6.getText().equals(null) )
					{
						la.fillBlankAnswer[5]=textField6.getText().trim();
						//la.fillBlankAnswer.add(textField4.getText().trim());
					}
					answer.delete();
					FileOutputStream fos=new FileOutputStream(new File("Answer//ListeningAns.ser"),true);
					ObjectOutputStream oos=new ObjectOutputStream(new BufferedOutputStream (fos));
					
					oos.writeObject(la);
					oos.close();
					fos.close();
					
				}
			}
}
