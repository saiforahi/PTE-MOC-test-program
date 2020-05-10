package set2Writing;

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
import set2Reading.Set2MCQSingle1;

public class Set2Writing3 {

	public JFrame frame;
	JTextPane textPane;
	JTextArea textArea;
	JLabel countLabel;
	JLabel minLabel;
	JLabel secLabel;
	JButton button;
	Thread timer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Set2Writing3 window = new Set2Writing3();
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
	public Set2Writing3() {
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
			pstmt.setInt(1, 3);
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
						conn.createStatement().executeUpdate("DELETE FROM set2speaking;");
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
		
		JTextPane textPane = new JTextPane();
		textPane.setText((String) null);
		textPane.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 16));
		textPane.setEditable(false);
		textPane.setBackground(Color.WHITE);
		textPane.setText(getText());
		textPane.setBounds(94, 164, 1166, 136);
		frame.getContentPane().add(textPane);
		
		countLabel = new JLabel("Total word count: 0");
		countLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		countLabel.setBounds(92, 592, 153, 24);
		frame.getContentPane().add(countLabel);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new LineBorder(Color.DARK_GRAY, 2));
		panel.setBackground(new Color(224, 255, 255));
		panel.setBounds(92, 627, 154, 33);
		frame.getContentPane().add(panel);
		
		JLabel lblItemNo = new JLabel("Item no 3 of 3");
		lblItemNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemNo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblItemNo.setBounds(0, 0, 154, 33);
		panel.add(lblItemNo);
		
		JLabel label_2 = new JLabel("Timer");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_2.setBounds(1162, 592, 42, 24);
		frame.getContentPane().add(label_2);
		
		minLabel = new JLabel("19");
		minLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		minLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		minLabel.setBounds(1203, 592, 24, 24);
		frame.getContentPane().add(minLabel);
		
		JLabel label_4 = new JLabel(":");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_4.setBounds(1228, 592, 10, 24);
		frame.getContentPane().add(label_4);
		
		secLabel = new JLabel("60");
		secLabel.setHorizontalAlignment(SwingConstants.LEFT);
		secLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		secLabel.setBounds(1240, 592, 24, 24);
		frame.getContentPane().add(secLabel);
		
		button = new JButton("Next");
		button.setBackground(Color.WHITE);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					 Connection conn=sqlConnection.answerDBConnection();
					 //String query="UPDATE set1speaking SET writing=? WHERE rowid=?;";
					 String updateSQL = "UPDATE set2speaking SET writing = ? WHERE rowid = ?;";
					 PreparedStatement pstmt = conn.prepareStatement(updateSQL);
					 pstmt.setString(1,textArea.getText());
					 pstmt.setInt(2,3);
					 pstmt.execute();
					 pstmt.close();
					 conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String[] options = new String[2];
				options[0] = new String("yes");
				options[1] = new String("not now");
				int choice=JOptionPane.showOptionDialog(frame.getContentPane(),"Reading part is going to start.....","Confirmation to proceed", JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,null,options,null);
				if(choice==0)
				{
					frame.dispose();
					Set2MCQSingle1 go=new Set2MCQSingle1();
					go.frame.setVisible(true);
					go.frame.setAlwaysOnTop(true);
				}
			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 12));
		button.setBounds(1175, 627, 89, 28);
		frame.getContentPane().add(button);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(255, 140, 0));
		panel_1.setBounds(0, 55, 1370, 33);
		frame.getContentPane().add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(0, 0, 1370, 56);
		frame.getContentPane().add(panel_2);
		
		JLabel lblPracticeSet = new JLabel("Writing Test");
		lblPracticeSet.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblPracticeSet.setBounds(10, 0, 671, 56);
		panel_2.add(lblPracticeSet);
		
		JLabel lblNewLabel = new JLabel("<html><p align='CENTER'>You will have 20 minutes to plan, write and revise an essay about the topic below. Your response will be judged on how well you develop a position, organize your ideas, present supporting details and control the elements of standard written englsih. You should write 200-300 words.</p></html>");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(204, 97, 1012, 39);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(Color.LIGHT_GRAY, 3, true));
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(94, 353, 1166, 217);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 11, 1146, 195);
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
				int min=19;
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
