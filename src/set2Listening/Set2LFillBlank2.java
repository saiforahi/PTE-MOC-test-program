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
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Set2LFillBlank2 {

	public JFrame frame;
	Player p;
	private JTextField textField3;
	private JTextField textField1;
	private JTextField textField5;
	private JTextField textField4;
	private JTextField textField2;
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
					Set2LFillBlank2 window = new Set2LFillBlank2();
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
	public Set2LFillBlank2() {
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
		
		JLabel label = new JLabel("Listening Test (Fill in the blanks)");
		label.setFont(new Font("Tahoma", Font.BOLD, 24));
		label.setBounds(10, 0, 704, 56);
		panel.add(label);
		
		JLabel label_1 = new JLabel("<html><p align='CENTER'>You will hear a recording. Type the missing words.</p></html>");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_1.setBounds(65, 93, 1188, 28);
		frame.getContentPane().add(label_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(112, 128, 144)));
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
				Set2HighCorrectSummary1 go= new Set2HighCorrectSummary1();
				go.frame.setVisible(true);
			}
		});
		button_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_1.setBackground(Color.WHITE);
		button_1.setBounds(1165, 615, 89, 28);
		frame.getContentPane().add(button_1);
		
		JLabel label_4 = new JLabel("Lab Symbiotic");
		label_4.setHorizontalAlignment(SwingConstants.TRAILING);
		label_4.setForeground(Color.DARK_GRAY);
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_4.setBounds(1188, 667, 156, 20);
		frame.getContentPane().add(label_4);
		
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
		panel_4.setBounds(134, 378, 1120, 156);
		frame.getContentPane().add(panel_4);
		
		JLabel lblWeCantBelieve = new JLabel("After 13.8 billion years of cosmic history, our universe has woken up and become aware of itself. From a small blue planet, tiny, ");
		lblWeCantBelieve.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblWeCantBelieve.setBounds(10, 11, 875, 20);
		panel_4.add(lblWeCantBelieve);
		
		JLabel lblLieToCoworkers = new JLabel("universe have begun gazing out into the cosmos with telescopes, discovering something humbling. We've discovered that our universe is vastly grander than our");
		lblLieToCoworkers.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblLieToCoworkers.setBounds(10, 38, 1100, 20);
		panel_4.add(lblLieToCoworkers);
		
		JLabel lblUnmarriedThatNumber = new JLabel("something");
		lblUnmarriedThatNumber.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblUnmarriedThatNumber.setBounds(10, 92, 71, 20);
		panel_4.add(lblUnmarriedThatNumber);
		
		JLabel lblLyingsComplexIts = new JLabel(", which is that the technology we're developing has the potential to help life flourish like never before, not just for ");
		lblLyingsComplexIts.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblLyingsComplexIts.setBounds(199, 94, 776, 20);
		panel_4.add(lblLyingsComplexIts);
		
		textField3 = new JTextField();
		textField3.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textField3.setColumns(10);
		textField3.setBounds(91, 94, 98, 20);
		panel_4.add(textField3);
		
		JLabel lblOfOur = new JLabel("but for billions of years, and not just on earth but throughout much of this");
		lblOfOur.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblOfOur.setBounds(10, 123, 503, 20);
		panel_4.add(lblOfOur);
		
		textField1 = new JTextField();
		textField1.setHorizontalAlignment(SwingConstants.CENTER);
		textField1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textField1.setColumns(10);
		textField1.setBounds(887, 11, 113, 20);
		panel_4.add(textField1);
		
		textField5 = new JTextField();
		textField5.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textField5.setColumns(10);
		textField5.setBounds(517, 123, 106, 20);
		panel_4.add(textField5);
		
		textField4 = new JTextField();
		textField4.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textField4.setColumns(10);
		textField4.setBounds(991, 94, 98, 20);
		panel_4.add(textField4);
		
		textField2 = new JTextField();
		textField2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textField2.setColumns(10);
		textField2.setBounds(10, 66, 106, 20);
		panel_4.add(textField2);
		
		JLabel lblNowYouMay = new JLabel("imagined and that life seems to be an almost imperceptibly small perturbation on an otherwise dead universe. But we've also discovered");
		lblNowYouMay.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNowYouMay.setBounds(122, 66, 988, 20);
		panel_4.add(lblNowYouMay);
		
		JLabel lblPartsOfOur = new JLabel("parts of our");
		lblPartsOfOur.setBounds(1010, 11, 100, 20);
		panel_4.add(lblPartsOfOur);
		lblPartsOfOur.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		
		JLabel lblCosmos = new JLabel("cosmos.");
		lblCosmos.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblCosmos.setBounds(630, 123, 158, 20);
		panel_4.add(lblCosmos);
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
					PreparedStatement pstmt=conn.prepareStatement("SELECT listeningFillblank FROM test2question WHERE rowid=?;");
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
			answer.delete();
			FileOutputStream fos=new FileOutputStream(new File("Answer//ListeningAns.ser"),true);
			ObjectOutputStream oos=new ObjectOutputStream(new BufferedOutputStream (fos));
			
			oos.writeObject(la);
			oos.close();
			fos.close();
			
		}
	}
}
