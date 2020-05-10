package set1listening;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
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

public class Set1listening1 {

	public JFrame frame;
	JProgressBar progressBar;
	Player p;
	public static int val=4;
	JTextArea textArea;
	JLabel statusLabel;
	Thread timer;
	JLabel secLabel;
	JLabel minLabel;
	JLabel countLabel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Set1listening1 window = new Set1listening1();
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
	public Set1listening1() {
		initialize();
		timeCount();
	}
	
	public static int countWordsUsingStringTokenizer(String sentence) {
		if (sentence == null || sentence.isEmpty()||sentence==""||sentence==" ") {
			return 0; }
		StringTokenizer tokens = new StringTokenizer(sentence);
		return tokens.countTokens(); 
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
		frame.pack();
		frame.setBackground(Color.WHITE);
		frame.setIconImage(Functions.setIcon());
		frame.setTitle("Listening Test");
		frame.setFocusable(true);
		frame.setSize(new Dimension(screenSize.width, screenSize.height));
		frame.getContentPane().setPreferredSize(new Dimension(screenSize.width,screenSize.height));
		frame.setAlwaysOnTop(true);
		frame.setResizable(false);
		
		frame.setLocation(-2, -1);
		frame.getContentPane().setLayout(null);
		
		JButton button = new JButton("Next");
		button.setOpaque(true);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] options = new String[2];
				options[0] = new String("Agree");
				options[1] = new String("Disagree");
				int choice=JOptionPane.showOptionDialog(frame.getContentPane(),"Are you sure to save and step ahead?!","Confirmation to proceed", JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,null,options,null);
				if(choice==0)
				{
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
					Set1listening2 go= new Set1listening2();
					go.frame.setVisible(true);
				}
				
			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 12));
		button.setBounds(1165, 615, 89, 28);
		frame.getContentPane().add(button);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new LineBorder(Color.DARK_GRAY, 2));
		panel.setBackground(new Color(224, 255, 255));
		panel.setBounds(134, 615, 154, 33);
		frame.getContentPane().add(panel);
		
		JLabel lblItemNo = new JLabel("Item no 1 of 17");
		lblItemNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblItemNo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblItemNo.setBounds(10, 0, 144, 33);
		panel.add(lblItemNo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 0, 1370, 56);
		frame.getContentPane().add(panel_1);
		
		JLabel lblListeningTestsummarize = new JLabel("Listening Test");
		lblListeningTestsummarize.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblListeningTestsummarize.setBounds(10, 0, 497, 56);
		panel_1.add(lblListeningTestsummarize);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(255, 140, 0));
		panel_2.setBounds(0, 55, 1370, 33);
		frame.getContentPane().add(panel_2);
		
		
		JLabel lblYouWillHear = new JLabel("<html><p align='CENTER'>You will hear a short lecture. Write a summary for a felow student who was not present at the lecture. You should write 50-70 words. You have 10 minutes to finish this task. Your response will be judged on the quality of your writing and on how well your response presents the key points presented in the lecture.</p></html>");
		lblYouWillHear.setHorizontalAlignment(SwingConstants.CENTER);
		lblYouWillHear.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblYouWillHear.setBounds(86, 111, 1188, 48);
		frame.getContentPane().add(lblYouWillHear);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setLayout(null);
		panel_3.setBorder(new MatteBorder(2, 1, 2, 1, (Color) new Color(51, 0, 153)));
		panel_3.setBounds(528, 182, 277, 147);
		frame.getContentPane().add(panel_3);
		
		JLabel label_2 = new JLabel("Status");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_2.setBounds(92, 11, 85, 30);
		panel_3.add(label_2);
		
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
		panel_4.setBorder(new MatteBorder(2, 1, 2, 1, (Color) new Color(51, 0, 153)));
		panel_4.setBackground(Color.WHITE);
		panel_4.setBounds(134, 393, 1118, 162);
		frame.getContentPane().add(panel_4);
		panel_4.setLayout(null);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 11, 1098, 140);
		panel_4.add(textArea);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_BACK_SPACE||arg0.getKeyCode()==KeyEvent.VK_A || arg0.getKeyCode()==KeyEvent.VK_B || arg0.getKeyCode()==KeyEvent.VK_C || arg0.getKeyCode()==KeyEvent.VK_D||arg0.getKeyCode()==KeyEvent.VK_E||arg0.getKeyCode()==KeyEvent.VK_F||arg0.getKeyCode()==KeyEvent.VK_G||arg0.getKeyCode()==KeyEvent.VK_H||arg0.getKeyCode()==KeyEvent.VK_I||arg0.getKeyCode()==KeyEvent.VK_J||arg0.getKeyCode()==KeyEvent.VK_K||arg0.getKeyCode()==KeyEvent.VK_L||arg0.getKeyCode()==KeyEvent.VK_M||arg0.getKeyCode()==KeyEvent.VK_N||arg0.getKeyCode()==KeyEvent.VK_O||arg0.getKeyCode()==KeyEvent.VK_P||arg0.getKeyCode()==KeyEvent.VK_Q||arg0.getKeyCode()==KeyEvent.VK_R||arg0.getKeyCode()==KeyEvent.VK_S||arg0.getKeyCode()==KeyEvent.VK_T||arg0.getKeyCode()==KeyEvent.VK_U||arg0.getKeyCode()==KeyEvent.VK_V||arg0.getKeyCode()==KeyEvent.VK_W||arg0.getKeyCode()==KeyEvent.VK_X||arg0.getKeyCode()==KeyEvent.VK_Y||arg0.getKeyCode()==KeyEvent.VK_Z){
					//String s= textArea.getText();
					//countWordsUsingStringTokenizer(s);
					countLabel.setText("Total word count: "+countWordsUsingStringTokenizer(textArea.getText()));
				}
			}
		});
		textArea.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		
		countLabel = new JLabel("Total word count: 0");
		countLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		countLabel.setBounds(134, 568, 153, 24);
		frame.getContentPane().add(countLabel);
		
		JLabel label_5 = new JLabel("Timer");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_5.setBounds(1152, 568, 42, 24);
		frame.getContentPane().add(label_5);
		
		minLabel = new JLabel("9");
		minLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		minLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		minLabel.setBounds(1193, 568, 24, 24);
		frame.getContentPane().add(minLabel);
		
		JLabel label_7 = new JLabel(":");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_7.setBounds(1218, 568, 10, 24);
		frame.getContentPane().add(label_7);
		
		secLabel = new JLabel("60");
		secLabel.setHorizontalAlignment(SwingConstants.LEFT);
		secLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		secLabel.setBounds(1230, 568, 24, 24);
		frame.getContentPane().add(secLabel);
		
		JLabel label = new JLabel("\u00A9Lab Symbiotic");
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.DARK_GRAY);
		label.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		label.setBounds(0, 704, 1370, 24);
		frame.getContentPane().add(label);
		
		timer=new Thread()
		{
			@Override public void run(){
				int sec=60;
				int min=9;
				while(min>0)
				{
					sec--;
					if(sec<10)
					{
						secLabel.setText("0"+Integer.toString(sec));
					}
					else{secLabel.setText(Integer.toString(sec));}
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(sec==0)
					{
						min--;
						minLabel.setText(Integer.toString(min));
						sec=60;
					}
				}
				textArea.setEditable(false);
				textArea.setFocusable(false);
				try {
					fileSaver();
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
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
				textArea.setEditable(true);
				progressBar.setMaximum(76);
				do{
					progressBar.setValue(progressBar.getValue()+1);
					try {
						TimeUnit.MILLISECONDS.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}while(progressBar.getValue()<76);
				
				timer.start();
			}
		};
		Runnable run=new Runnable(){
			public void run(){
				try {
					Connection conn= sqlConnection.dbConnection();
					PreparedStatement pstmt=conn.prepareStatement("SELECT listeningSummary FROM test1question WHERE rowid=?;");
					pstmt.setInt(1, 1);  // 1st summary
					ResultSet rs=pstmt.executeQuery();
					InputStream input = rs.getBinaryStream("listeningSummary");
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
		Thread tr=new Thread(run);
		tr1.start();
		tr.start();
	}
	
	/******************************************** file saver  * @throws IOException * @throws ClassNotFoundException ******************************************************/
	
	public void fileSaver() throws IOException, ClassNotFoundException{
		File answerFolder = new File("Answer");
		if(answerFolder.exists())
		{
			File answer=new File("Answer//ListeningAns.ser");
			ListeningAnswer la=new ListeningAnswer();
			la.summaryAnswer=textArea.getText().trim();
			FileOutputStream fos=new FileOutputStream(answer,true);
			ObjectOutputStream oos=new ObjectOutputStream(new BufferedOutputStream (fos));
			oos.writeObject(la);
			oos.close();
			fos.close();
			
			FileInputStream fis=new FileInputStream(answer);
			ObjectInputStream ois=new ObjectInputStream(fis);
			ListeningAnswer la1=(ListeningAnswer)ois.readObject();
			System.out.println(la1.summaryAnswer);
			ois.close();
			fis.close();
		}
	}
}
