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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.MatteBorder;

public class Set1listening18 {

	public JFrame frame;
	public int val=4;
	JLabel statusLabel;
	JProgressBar progressBar;
	JTextArea textArea;
	JLabel countLabel;
	Player p;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Set1listening18 window = new Set1listening18();
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
	public Set1listening18() {
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
		
		JLabel lblListeningTestwrite = new JLabel("Listening Test");
		lblListeningTestwrite.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblListeningTestwrite.setBounds(10, 0, 506, 56);
		panel.add(lblListeningTestwrite);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(255, 140, 0));
		panel_1.setBounds(0, 55, 1370, 33);
		frame.getContentPane().add(panel_1);
		
		JLabel lblYouWillHear = new JLabel("You will hear a sentence. Type the sentence in the box below exactly as you hear it. Write as much of the sentence as you can. You will hear the sentence only once.");
		lblYouWillHear.setHorizontalAlignment(SwingConstants.CENTER);
		lblYouWillHear.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblYouWillHear.setBounds(86, 111, 1188, 33);
		frame.getContentPane().add(lblYouWillHear);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new LineBorder(Color.DARK_GRAY, 2));
		panel_2.setBackground(new Color(224, 255, 255));
		panel_2.setBounds(134, 615, 154, 33);
		frame.getContentPane().add(panel_2);
		
		JLabel lblItemNo = new JLabel("Item no 15 of 17");
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
				Set1listening19 go= new Set1listening19();
				go.frame.setVisible(true);
			}
		});
		button_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_2.setEnabled(true);
		button_2.setBounds(1165, 615, 89, 28);
		frame.getContentPane().add(button_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setLayout(null);
		panel_3.setBorder(new MatteBorder(2, 1, 2, 1, (Color) new Color(51, 0, 153)));
		panel_3.setBounds(587, 183, 277, 148);
		frame.getContentPane().add(panel_3);
		
		JLabel label = new JLabel("Status");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label.setBounds(93, 11, 85, 30);
		panel_3.add(label);
		
		statusLabel = new JLabel("beginning in 4 seconds");
		statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		statusLabel.setBounds(67, 52, 152, 27);
		panel_3.add(statusLabel);
		
		progressBar = new JProgressBar();
		progressBar.setBackground(new Color(224, 255, 255));
		progressBar.setForeground(new Color(0, 51, 102));
		progressBar.setBorder(new LineBorder(new Color(30, 144, 255), 2, true));
		progressBar.setBorderPainted(true);
		progressBar.setStringPainted(true);
		progressBar.setString("");
		progressBar.setBounds(22, 104, 234, 26);
		panel_3.add(progressBar);
		
		countLabel = new JLabel("Total word count: 0");
		countLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		countLabel.setBounds(134, 571, 153, 24);
		frame.getContentPane().add(countLabel);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(new MatteBorder(2, 1, 2, 1, (Color) new Color(51, 0, 153)));
		panel_4.setBackground(Color.WHITE);
		panel_4.setBounds(134, 399, 1120, 146);
		frame.getContentPane().add(panel_4);
		
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
		textArea.setBounds(10, 11, 1100, 124);
		panel_4.add(textArea);
		
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
					progressBar.setMaximum(46);
					do{
						progressBar.setValue(progressBar.getValue()+1);
						try {
							TimeUnit.MILLISECONDS.sleep(250);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}while(progressBar.getValue()<46);
				}
			};
			Runnable run=new Runnable(){
				public void run(){
					try {
						Connection conn= sqlConnection.dbConnection();
						PreparedStatement pstmt=conn.prepareStatement("SELECT listeningDictation FROM test1question WHERE rowid=?;");
						pstmt.setInt(1, 1);  // 2nd listening mcq
						ResultSet rs=pstmt.executeQuery();
						InputStream input = rs.getBinaryStream("listeningDictation");
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
					la.dictation1=textArea.getText().trim();
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
				la.dictation1=textArea.getText().trim();
				FileOutputStream fos=new FileOutputStream(answer,true);
				ObjectOutputStream oos=new ObjectOutputStream(new BufferedOutputStream (fos));
				oos.writeObject(la);
				oos.close();
				fos.close();
				}
			}
		public static int countWordsUsingStringTokenizer(String sentence) {
			if (sentence == null || sentence.isEmpty()||sentence==""||sentence==" ") {
				return 0; }
			StringTokenizer tokens = new StringTokenizer(sentence);
			return tokens.countTokens(); 
		}
}
