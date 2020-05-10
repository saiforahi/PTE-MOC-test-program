package set1listening;

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
import javax.swing.border.MatteBorder;

public class Set1listening6 {

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
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Set1listening6 window = new Set1listening6();
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
	public Set1listening6() {
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
						conn.createStatement().executeUpdate("DELETE FROM set1speaking;");
						conn.createStatement().executeUpdate("DELETE FROM serFiles;");
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
		frame.setSize(new Dimension(screenSize.width, screenSize.height));
		frame.getContentPane().setPreferredSize(new Dimension(screenSize.width,screenSize.height));
		frame.setAlwaysOnTop(true);
		frame.setResizable(false);
		frame.setLocation(0, 0);
		
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1370, 56);
		frame.getContentPane().add(panel);
		
		JLabel lblListeningTestfill = new JLabel("Listening Test");
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
				Set1listening7 go= new Set1listening7();
				go.frame.setVisible(true);
			}
		});
		button_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_2.setBounds(1165, 615, 89, 28);
		frame.getContentPane().add(button_2);
		
		JLabel lblyouWillHear = new JLabel("<html><p align='CENTER'>You will hear a recording. Type the missing words.</p></html>");
		lblyouWillHear.setHorizontalAlignment(SwingConstants.CENTER);
		lblyouWillHear.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblyouWillHear.setBounds(65, 93, 1188, 28);
		frame.getContentPane().add(lblyouWillHear);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setLayout(null);
		panel_3.setBorder(new MatteBorder(2, 1, 2, 1, (Color) new Color(51, 0, 153)));
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
		progressBar.setForeground(new Color(0, 51, 102));
		progressBar.setBorder(new LineBorder(new Color(30, 144, 255), 2, true));
		progressBar.setBorderPainted(true);
		progressBar.setStringPainted(true);
		progressBar.setString("");
		progressBar.setBounds(21, 104, 234, 26);
		panel_3.add(progressBar);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setBounds(134, 376, 1120, 183);
		frame.getContentPane().add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("We physicists believe, for example, that there is really a multiverse that exists even inside our living room. We are waves, vibrating waves, and these waves");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNewLabel.setBounds(10, 11, 1100, 20);
		panel_4.add(lblNewLabel);
		
		JLabel lblSteveWeinbergWinner = new JLabel(" vibrate and then split apart with time. Steve Weinberg, winner of the Nobel Prize compares it to the following. Think of ");
		lblSteveWeinbergWinner.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblSteveWeinbergWinner.setBounds(10, 40, 821, 20);
		panel_4.add(lblSteveWeinbergWinner);
		
		JLabel lblIfYoureInside = new JLabel("if you're inside your");
		lblIfYoureInside.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblIfYoureInside.setBounds(957, 40, 154, 20);
		panel_4.add(lblIfYoureInside);
		
		JLabel lblFrequencyButIn = new JLabel("living room listening to BBC Radio, that radio is tuned to one frequency. But in your living room there are all frequencies; Radio Cuba, Radio Moscow, the top ");
		lblFrequencyButIn.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblFrequencyButIn.setBounds(10, 68, 1100, 20);
		panel_4.add(lblFrequencyButIn);
		
		JLabel lblRadioIsOnly = new JLabel("40 rock stations. All these radio frequencies are vibrating inside your living room but your radio is only");
		lblRadioIsOnly.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblRadioIsOnly.setBounds(10, 95, 702, 20);
		panel_4.add(lblRadioIsOnly);
		
		JLabel lblButAsTime = new JLabel("to one frequency. Now in other words,");
		lblButAsTime.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblButAsTime.setBounds(841, 95, 269, 20);
		panel_4.add(lblButAsTime);
		
		JLabel lblToEvolveThese = new JLabel("when two universes are in phase they are coherent and you can move back and");
		lblToEvolveThese.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblToEvolveThese.setBounds(10, 122, 524, 20);
		panel_4.add(lblToEvolveThese);
		
		textField3 = new JTextField();
		textField3.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField3.setHorizontalAlignment(SwingConstants.CENTER);
		textField3.setColumns(10);
		textField3.setBounds(544, 122, 98, 20);
		panel_4.add(textField3);
		
		JLabel lblWithEachOther = new JLabel("But as time starts to evolve, these two universes decouple.");
		lblWithEachOther.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblWithEachOther.setBounds(652, 122, 458, 20);
		panel_4.add(lblWithEachOther);
		
		textField1 = new JTextField();
		textField1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField1.setHorizontalAlignment(SwingConstants.CENTER);
		textField1.setColumns(10);
		textField1.setBounds(834, 40, 113, 20);
		panel_4.add(textField1);
		
		textField2 = new JTextField();
		textField2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField2.setHorizontalAlignment(SwingConstants.CENTER);
		textField2.setColumns(10);
		textField2.setBounds(725, 96, 106, 20);
		panel_4.add(textField2);
		
		JLabel lblTheyStartTo = new JLabel("They start to vibrate at different frequencies, they can no longer");
		lblTheyStartTo.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblTheyStartTo.setBounds(10, 151, 432, 20);
		panel_4.add(lblTheyStartTo);
		
		textField4 = new JTextField();
		textField4.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField4.setHorizontalAlignment(SwingConstants.CENTER);
		textField4.setColumns(10);
		textField4.setBounds(452, 151, 98, 20);
		panel_4.add(textField4);
		
		JLabel lblWithEachOther_1 = new JLabel("with each other.");
		lblWithEachOther_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblWithEachOther_1.setBounds(562, 151, 269, 20);
		panel_4.add(lblWithEachOther_1);
		
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
							PreparedStatement pstmt=conn.prepareStatement("SELECT listeningFillblank FROM test1question WHERE rowid=?;");
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
					else
					{
						JOptionPane.showMessageDialog(frame,"listeningAns.ser file is missing!");
					}
					
				}
			}
}
