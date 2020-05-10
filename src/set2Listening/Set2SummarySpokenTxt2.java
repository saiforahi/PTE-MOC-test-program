package set2Listening;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import main.Functions;
import main.ListeningAnswer;
import main.Menu;
import main.sqlConnection;
import javax.swing.JPanel;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Set2SummarySpokenTxt2 {

	public JFrame frame;
	JTextArea textArea;
	JLabel countLabel, secLabel, minLabel,statusLabel;
	Thread timer;
	JProgressBar progressBar;
	Player p;
	int val=4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Set2SummarySpokenTxt2 window = new Set2SummarySpokenTxt2();
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
	public Set2SummarySpokenTxt2() {
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
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1370, 56);
		frame.getContentPane().add(panel);
		
		JButton button = new JButton("Submit");
		button.setOpaque(true);
		button.setForeground(Color.DARK_GRAY);
		button.setFont(new Font("Tahoma", Font.BOLD, 12));
		button.setEnabled(false);
		button.setBackground(Color.WHITE);
		button.setBounds(1203, 14, 134, 30);
		panel.add(button);
		
		JLabel label = new JLabel("Listening Test (Summarize spoken text)");
		label.setFont(new Font("Tahoma", Font.BOLD, 24));
		label.setBounds(10, 0, 497, 56);
		panel.add(label);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(255, 140, 0));
		panel_1.setBounds(0, 55, 1370, 33);
		frame.getContentPane().add(panel_1);
		
		JLabel label_1 = new JLabel("<html><p align='CENTER'>You will hear a short lecture. Write a summary for a felow student who was not present at the lecture. You should write 50-70 words. You have 10 minutes to finish this task. Your response will be judged on the quality of your writing and on how well your response presents the key points presented in the lecture.</p></html>");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_1.setBounds(86, 111, 1188, 48);
		frame.getContentPane().add(label_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new LineBorder(new Color(112, 128, 144)));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(528, 182, 277, 147);
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
		
		minLabel = new JLabel("9");
		minLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		minLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		minLabel.setBounds(1193, 568, 24, 24);
		frame.getContentPane().add(minLabel);
		
		JLabel label_5 = new JLabel("Timer");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_5.setBounds(1152, 568, 42, 24);
		frame.getContentPane().add(label_5);
		
		secLabel = new JLabel("60");
		secLabel.setHorizontalAlignment(SwingConstants.LEFT);
		secLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		secLabel.setBounds(1230, 568, 24, 24);
		frame.getContentPane().add(secLabel);
		
		JLabel label_7 = new JLabel(":");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_7.setBounds(1218, 568, 10, 24);
		frame.getContentPane().add(label_7);
		
		JButton button_1 = new JButton("Next");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] options = new String[2];
				options[0] = new String("Agree");
				options[1] = new String("Disagree");
				int choice=JOptionPane.showOptionDialog(frame.getContentPane(),"Are you sure to save and step ahead?!","Confirmation to proceed", JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,null,options,null);
				if(choice==0)
				{
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
					Set2LMCQMulti1 go= new Set2LMCQMulti1();
					go.frame.setVisible(true);
				}
			}
		});
		button_1.setOpaque(true);
		button_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_1.setBounds(1165, 615, 89, 28);
		frame.getContentPane().add(button_1);
		
		JLabel label_8 = new JLabel("Lab Symbiotic");
		label_8.setHorizontalAlignment(SwingConstants.TRAILING);
		label_8.setForeground(Color.DARK_GRAY);
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_8.setBounds(1188, 667, 156, 20);
		frame.getContentPane().add(label_8);
		
		textArea = new JTextArea();
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
		textArea.setLineWrap(true);
		textArea.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textArea.setEditable(false);
		textArea.setBounds(134, 393, 1118, 162);
		frame.getContentPane().add(textArea);
		
		countLabel = new JLabel("Total word count: 0");
		countLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		countLabel.setBounds(134, 568, 153, 24);
		frame.getContentPane().add(countLabel);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new LineBorder(Color.DARK_GRAY, 2));
		panel_3.setBackground(new Color(224, 255, 255));
		panel_3.setBounds(134, 615, 154, 33);
		frame.getContentPane().add(panel_3);
		
		JLabel lblItemNo = new JLabel("Item no 2 of 17");
		lblItemNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblItemNo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblItemNo.setBounds(10, 0, 144, 33);
		panel_3.add(lblItemNo);
		
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
	
	public static int countWordsUsingStringTokenizer(String sentence) {
		if (sentence == null || sentence.isEmpty()||sentence==""||sentence==" ") {
			return 0; }
		StringTokenizer tokens = new StringTokenizer(sentence);
		return tokens.countTokens(); 
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
			Thread timeCounter=new Thread(run);
			timeCounter.start();
			
		}
		
		/************************************************** player **************************************************************/
		
		public void player() throws InterruptedException, LineUnavailableException{
			Runnable run=new Runnable(){
				public void run(){
					statusLabel.setText("Playing");
					textArea.setEditable(true);
					progressBar.setMaximum(93);
					do{
						progressBar.setValue(progressBar.getValue()+1);
						try {
							TimeUnit.MILLISECONDS.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}while(progressBar.getValue()<93);
					
					timer.start();
				}
			};
			Runnable run1=new Runnable(){
				public void run(){
					try {
						Connection conn= sqlConnection.dbConnection();
						PreparedStatement pstmt=conn.prepareStatement("SELECT listeningSummary FROM test2question WHERE rowid=?;");
						pstmt.setInt(1, 2);  // 1st summary
						ResultSet rs=pstmt.executeQuery();
						InputStream input = rs.getBinaryStream("listeningSummary");
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
			Thread player1=new Thread(run);
			Thread player2=new Thread(run1);
			player2.start();
			player1.start();
		
		}
		
		/******************************************** file saver  * @throws IOException * @throws ClassNotFoundException ******************************************************/
		
		public void fileSaver() throws IOException, ClassNotFoundException{
			File answerFolder = new File("Answer");
			if(answerFolder.exists())
			{
				File answer=new File("Answer//ListeningAns.ser");
				ListeningAnswer la=new ListeningAnswer();
				la.summaryAnswer2=textArea.getText().trim();
				FileOutputStream fos=new FileOutputStream(answer,true);
				ObjectOutputStream oos=new ObjectOutputStream(new BufferedOutputStream (fos));
				System.out.println(la.summaryAnswer2);
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
			else
			{
				answerFolder.mkdirs();
				File answer=new File("Answer//ListeningAns.ser");
				ListeningAnswer la=new ListeningAnswer();
				la.summaryAnswer2=textArea.getText().trim();
				FileOutputStream fos=new FileOutputStream(answer,true);
				ObjectOutputStream oos=new ObjectOutputStream(new BufferedOutputStream (fos));
				System.out.println(la.summaryAnswer);
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
