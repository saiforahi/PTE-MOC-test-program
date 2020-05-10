package set1Writing;

import java.awt.Color;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
import main.Functions;
import main.Menu;
import main.sqlConnection;
import javax.swing.border.MatteBorder;

public class Set1writing2 {

	public JFrame frame;
	JLabel minLabel;
	Thread timer;
	JLabel secLabel;
	JTextArea textArea;
	JLabel countLabel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Set1writing2 window = new Set1writing2();
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
	public Set1writing2() {
		initialize();
		timer.start();
	}
	public static int countWordsUsingStringTokenizer(String sentence) {
		if (sentence == null || sentence.isEmpty()||sentence==""||sentence==" ") {
			return 0; }
		StringTokenizer tokens = new StringTokenizer(sentence);
		return tokens.countTokens(); 
	}
	public String getText()
	{
		try {
			String query= "SELECT writing FROM test1question WHERE rowid=?;";
			Connection conn=sqlConnection.dbConnection();
			PreparedStatement pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, 2);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{
				query=rs.getString("writing");
			}
			rs.close();
			pstmt.close();
			return query;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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
		frame.setTitle("Speaking Test");
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
		
		JLabel lblWritingTest = new JLabel("Writing test");
		lblWritingTest.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblWritingTest.setBounds(10, 0, 671, 56);
		panel.add(lblWritingTest);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(255, 140, 0));
		panel_1.setBounds(0, 55, 1370, 33);
		frame.getContentPane().add(panel_1);
		
		JTextPane textPane = new JTextPane();
		textPane.setText("Read the passage bellow and summarize it using one sentence. Type your response in the box at the bottom of the screen. You have 10 minutes to finish the task. Your response will be judged on the quality of your writing and on how well your response presents the key points in the passage.");
		textPane.setFont(new Font("Sitka Text", Font.PLAIN, 14));
		textPane.setEditable(false);
		textPane.setBackground(Color.WHITE);
		textPane.setBounds(92, 99, 1172, 52);
		frame.getContentPane().add(textPane);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setText((String) null);
		textPane_1.setFont(new Font("Serif", Font.PLAIN, 17));
		textPane_1.setEditable(false);
		textPane_1.setBackground(Color.WHITE);
		textPane_1.setBounds(94, 164, 1168, 275);
		textPane_1.setText(getText());
		frame.getContentPane().add(textPane_1);
		
		countLabel = new JLabel("Total word count: 0");
		countLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		countLabel.setBounds(92, 592, 153, 24);
		frame.getContentPane().add(countLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new LineBorder(Color.DARK_GRAY, 2));
		panel_2.setBackground(new Color(224, 255, 255));
		panel_2.setBounds(92, 627, 154, 33);
		frame.getContentPane().add(panel_2);
		
		JLabel lblItemNo = new JLabel("Item no 2 of 3");
		lblItemNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemNo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblItemNo.setBounds(0, 0, 154, 33);
		panel_2.add(lblItemNo);
		
		JLabel label_3 = new JLabel("Timer");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_3.setBounds(1162, 592, 42, 24);
		frame.getContentPane().add(label_3);
		
		minLabel = new JLabel("9");
		minLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		minLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		minLabel.setBounds(1203, 592, 24, 24);
		frame.getContentPane().add(minLabel);
		
		JLabel label_5 = new JLabel(":");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_5.setBounds(1228, 592, 10, 24);
		frame.getContentPane().add(label_5);
		
		secLabel = new JLabel("60");
		secLabel.setHorizontalAlignment(SwingConstants.LEFT);
		secLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		secLabel.setBounds(1240, 592, 24, 24);
		frame.getContentPane().add(secLabel);
		
		JButton button_2 = new JButton("Next");
		button_2.setBackground(Color.WHITE);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					 Connection conn=sqlConnection.answerDBConnection();
					 //String query="UPDATE set1speaking SET writing=? WHERE rowid=?;";
					 String updateSQL = "UPDATE set1speaking SET writing = ? WHERE rowid = ?;";
					 PreparedStatement pstmt = conn.prepareStatement(updateSQL);
					 pstmt.setString(1,textArea.getText());
					 pstmt.setInt(2,2);
					 pstmt.execute();
					 pstmt.close();
					 conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 frame.dispose();
				 Set1writing3 go=new Set1writing3();
				 go.frame.setVisible(true);
			}
		});
		button_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_2.setBounds(1175, 627, 89, 28);
		frame.getContentPane().add(button_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new MatteBorder(2, 1, 2, 1, (Color) new Color(51, 0, 153)));
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(92, 450, 1172, 131);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 11, 1152, 109);
		panel_3.add(textArea);
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
			}
		};
	}
	

}
