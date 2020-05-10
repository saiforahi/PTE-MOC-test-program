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
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;

public class Set1listening7 {

	public JFrame frame;
	Player p;
	private JTextField textField3;
	private JTextField textField1;
	private JTextField textField5;
	private JTextField textField4;
	private JTextField textField2;
	private JTextField textField6;
	public JLabel statusLabel;
	JProgressBar progressBar;
	public int val=4;
	Thread player1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Set1listening7 window = new Set1listening7();
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
	public Set1listening7() {
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
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1370, 56);
		frame.getContentPane().add(panel);
		
		JLabel lblListeningTest = new JLabel("Listening Test");
		lblListeningTest.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblListeningTest.setBounds(10, 0, 704, 56);
		panel.add(lblListeningTest);
		
		JLabel label_1 = new JLabel("<html><p align='CENTER'>You will hear a recording. Type the missing words.</p></html>");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_1.setBounds(65, 93, 1188, 28);
		frame.getContentPane().add(label_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new MatteBorder(2, 1, 2, 1, (Color) new Color(51, 0, 153)));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(529, 152, 277, 147);
		frame.getContentPane().add(panel_1);
		
		JLabel label_2 = new JLabel("Status");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_2.setBounds(92, 11, 85, 30);
		panel_1.add(label_2);
		
		 statusLabel = new JLabel("beginning in 4 seconds");
		statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		statusLabel.setBounds(66, 52, 152, 27);
		panel_1.add(statusLabel);
		
		 progressBar = new JProgressBar();
		progressBar.setBackground(new Color(224, 255, 255));
		progressBar.setForeground(new Color(0, 51, 102));
		progressBar.setBorder(new LineBorder(new Color(30, 144, 255), 2, true));
		progressBar.setBorderPainted(true);
		progressBar.setStringPainted(true);
		progressBar.setString("");
		progressBar.setBounds(21, 104, 234, 26);
		panel_1.add(progressBar);
		
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
				Set1listening8 go= new Set1listening8();
				go.frame.setVisible(true);
			}
		});
		button_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_1.setBackground(Color.WHITE);
		button_1.setBounds(1165, 615, 89, 28);
		frame.getContentPane().add(button_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new LineBorder(Color.DARK_GRAY, 2));
		panel_2.setBackground(new Color(224, 255, 255));
		panel_2.setBounds(134, 615, 154, 33);
		frame.getContentPane().add(panel_2);
		
		JLabel lblItemNo = new JLabel("Item no 6 of 17");
		lblItemNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblItemNo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblItemNo.setBounds(10, 0, 144, 33);
		panel_2.add(lblItemNo);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBackground(new Color(255, 140, 0));
		panel_3.setBounds(0, 54, 1370, 33);
		frame.getContentPane().add(panel_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBackground(Color.WHITE);
		panel_4.setBounds(134, 318, 1120, 286);
		frame.getContentPane().add(panel_4);
		
		JLabel lblWeCantBelieve = new JLabel("We can't believe how prevalent lying is. We're essentially against lying. but if you look more closely, the plot actually thickens. We lie more to strangers than we");
		lblWeCantBelieve.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblWeCantBelieve.setBounds(10, 11, 1100, 20);
		panel_4.add(lblWeCantBelieve);
		
		JLabel lblLieToCoworkers = new JLabel("lie to co-workers. Extroverts lie more than ");
		lblLieToCoworkers.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblLieToCoworkers.setBounds(10, 38, 295, 20);
		panel_4.add(lblLieToCoworkers);
		
		JLabel lblMenLie = new JLabel(". Men lie eight times more about themselves than they do other people. Women lie more to protect");
		lblMenLie.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblMenLie.setBounds(438, 38, 673, 20);
		panel_4.add(lblMenLie);
		
		JLabel lblOtherPeopleIf = new JLabel(" other people. If you're in an average married couple, you're gonna lie to your spouse in 1 out of every 10 ");
		lblOtherPeopleIf.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblOtherPeopleIf.setBounds(10, 66, 715, 20);
		panel_4.add(lblOtherPeopleIf);
		
		JLabel lblUnmarriedThatNumber = new JLabel(" unmarried, that number drops to 3. ");
		lblUnmarriedThatNumber.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblUnmarriedThatNumber.setBounds(10, 92, 702, 20);
		panel_4.add(lblUnmarriedThatNumber);
		
		JLabel lblLyingsComplexIts = new JLabel("Lying's complex. It's woven into the ");
		lblLyingsComplexIts.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblLyingsComplexIts.setBounds(10, 140, 247, 20);
		panel_4.add(lblLyingsComplexIts);
		
		textField3 = new JTextField();
		textField3.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField3.setHorizontalAlignment(SwingConstants.CENTER);
		textField3.setColumns(10);
		textField3.setBounds(267, 142, 98, 20);
		panel_4.add(textField3);
		
		JLabel lblOfOur = new JLabel("of our daily and our business lives. We're deeply ambivalent about the truth. We parse it out on an as-needed");
		lblOfOur.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblOfOur.setBounds(375, 140, 735, 20);
		panel_4.add(lblOfOur);
		
		textField1 = new JTextField();
		textField1.setHorizontalAlignment(SwingConstants.CENTER);
		textField1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField1.setColumns(10);
		textField1.setBounds(315, 38, 113, 20);
		panel_4.add(textField1);
		
		textField5 = new JTextField();
		textField5.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField5.setHorizontalAlignment(SwingConstants.CENTER);
		textField5.setColumns(10);
		textField5.setBounds(714, 224, 106, 20);
		panel_4.add(textField5);
		
		JLabel lblBasisSometimesFor = new JLabel("basis, sometimes for very very good reasons and other times just because we don't understand the gaps in our lives. That's truth number two about lying. We're ");
		lblBasisSometimesFor.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblBasisSometimesFor.setBounds(10, 169, 1100, 20);
		panel_4.add(lblBasisSometimesFor);
		
		textField4 = new JTextField();
		textField4.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField4.setHorizontalAlignment(SwingConstants.CENTER);
		textField4.setColumns(10);
		textField4.setBounds(174, 200, 98, 20);
		panel_4.add(textField4);
		
		JLabel lblWithEachOther = new JLabel("against lying, but we're ");
		lblWithEachOther.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblWithEachOther.setBounds(10, 197, 158, 20);
		panel_4.add(lblWithEachOther);
		
		textField2 = new JTextField();
		textField2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField2.setHorizontalAlignment(SwingConstants.CENTER);
		textField2.setColumns(10);
		textField2.setBounds(728, 66, 106, 20);
		panel_4.add(textField2);
		
		JLabel lblNowYouMay = new JLabel("Now, you may think that's bad. If you're");
		lblNowYouMay.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNowYouMay.setBounds(841, 66, 269, 20);
		panel_4.add(lblNowYouMay);
		
		JLabel lblForItIn = new JLabel("for it in ways that our society has sanctioned for centuries and centuries and centuries. It's as old as breathing. It's part of");
		lblForItIn.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblForItIn.setBounds(282, 197, 828, 20);
		panel_4.add(lblForItIn);
		
		JLabel lblOurCultureIts = new JLabel("our culture, it's part of our history. Think Dante, Shakespeare, the Bible, News of the World. Lying has ");
		lblOurCultureIts.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblOurCultureIts.setBounds(10, 224, 702, 20);
		panel_4.add(lblOurCultureIts);
		
		JLabel lblValueToUs = new JLabel("value to us as a species. Researchers");
		lblValueToUs.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblValueToUs.setBounds(830, 224, 280, 20);
		panel_4.add(lblValueToUs);
		
		JLabel lblHaveLongKnown = new JLabel("have long known that the more intelligent the species, that larger the neocortex, the more likely it is to be");
		lblHaveLongKnown.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblHaveLongKnown.setBounds(10, 253, 722, 20);
		panel_4.add(lblHaveLongKnown);
		
		textField6 = new JTextField();
		textField6.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField6.setHorizontalAlignment(SwingConstants.CENTER);
		textField6.setColumns(10);
		textField6.setBounds(734, 251, 106, 20);
		panel_4.add(textField6);
		
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
				progressBar.setMaximum(162);
				do{
					progressBar.setValue(progressBar.getValue()+1);
					try {
						TimeUnit.MILLISECONDS.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}while(progressBar.getValue()<162);
			}
		};
		Runnable run=new Runnable(){
			public void run(){
				try {
					Connection conn= sqlConnection.dbConnection();
					PreparedStatement pstmt=conn.prepareStatement("SELECT listeningFillblank FROM test1question WHERE rowid=?;");
					pstmt.setInt(1, 2);  // 1st listening mcq
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
					la.fillBlankAnswer2[0]=textField1.getText().trim();
					//la.fillBlankAnswer.add(textField1.getText().trim());
				}
				if(!textField2.getText().equals("") && !textField2.getText().equals(null) )
				{
					la.fillBlankAnswer2[1]=textField2.getText().trim();
					//la.fillBlankAnswer.add(textField2.getText().trim());
				}
				if(!textField3.getText().equals("") && !textField3.getText().equals(null) )
				{
					la.fillBlankAnswer2[2]=textField3.getText().trim();
					//la.fillBlankAnswer.add(textField3.getText().trim());
				}
				if(!textField4.getText().equals("") && !textField4.getText().equals(null) )
				{
					la.fillBlankAnswer2[3]=textField4.getText().trim();
					//la.fillBlankAnswer.add(textField4.getText().trim());
				}
				if(!textField5.getText().equals("") && !textField5.getText().equals(null) )
				{
					la.fillBlankAnswer2[4]=textField5.getText().trim();
					//la.fillBlankAnswer.add(textField4.getText().trim());
				}
				if(!textField6.getText().equals("") && !textField6.getText().equals(null) )
				{
					la.fillBlankAnswer2[5]=textField6.getText().trim();
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
