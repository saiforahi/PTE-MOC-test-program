package set1reading;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JTextPane;
import javax.swing.JRadioButton;

import main.Functions;
import main.Menu;
import main.ReadingAnswer;
import main.sqlConnection;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.border.MatteBorder;

public class Set1reading1 {

	public JFrame frame;
	JRadioButton radiobtn1;
	JRadioButton radiobtn2;
	JRadioButton radiobtn3;
	JRadioButton radiobtn4;
	JButton nextButton;
	public String summary;
	public static JPanel panel_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Set1reading1 window = new Set1reading1();
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
	public Set1reading1() {
		initialize();
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
					File answer=new File("Answer//ReadingAns.ser");
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
		frame.setTitle("Reading Test");
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
		
		JLabel lblReadingTestmultiple = new JLabel("Reading Test");
		lblReadingTestmultiple.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblReadingTestmultiple.setBounds(10, 0, 497, 56);
		panel.add(lblReadingTestmultiple);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(255, 140, 0));
		panel_1.setBounds(0, 55, 1370, 33);
		frame.getContentPane().add(panel_1);
		
		nextButton = new JButton("Next");
		nextButton.setBackground(Color.WHITE);
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				try {
					fileSaver();
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Set1reading2 go=new Set1reading2();
				go.frame.setVisible(true);
				
			}
		});
		nextButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		nextButton.setBounds(1165, 615, 89, 28);
		frame.getContentPane().add(nextButton);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new LineBorder(Color.DARK_GRAY, 2));
		panel_2.setBackground(new Color(224, 255, 255));
		panel_2.setBounds(134, 615, 154, 33);
		frame.getContentPane().add(panel_2);
		
		JLabel lblItemNo = new JLabel("Item no 1 of 15");
		lblItemNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblItemNo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblItemNo.setBounds(10, 0, 144, 33);
		panel_2.add(lblItemNo);
		
		panel_3 = new JPanel();
		panel_3.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 128)));
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(52, 116, 539, 435);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("<html><p>For decades, blue ribbon reports, studies, panels, and commissions have bemoaned universities\u2019 lack of transparency about the career prospects of their Ph.D. recipients and postdocs. In particular, experts have criticized institutions\u2019 failure to track and report how their doctoral and postdoctoral alumni fare in the labor market.\r\n<br><br>As administrators at 10 U.S. research institutions warned in a Science article this past December, without knowing what kinds of jobs graduates can expect after graduation, students can\u2019t \u201Cmake informed choices about their pre- and postdoctoral training activities.\u201D</p></html>");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel.setBounds(23, 28, 494, 378);
		panel_3.add(lblNewLabel);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(25, 25, 112)));
		panel_4.setBackground(Color.WHITE);
		panel_4.setLayout(null);
		panel_4.setBounds(675, 138, 539, 390);
		frame.getContentPane().add(panel_4);
		
		radiobtn3 = new JRadioButton("<html><p>Experts have criticized institutions\u2019 failure to hire their doctoral and postdoctoral alumni as professors at their universities.</p></html>");
		radiobtn3.setVerticalAlignment(SwingConstants.TOP);
		radiobtn3.setBackground(Color.WHITE);
		radiobtn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(radiobtn3.isSelected())
				{
					summary=radiobtn3.getText();
					radiobtn3.setBackground(Color.YELLOW);
					radiobtn1.setSelected(false);
					radiobtn1.setBackground(Color.white);
					radiobtn2.setSelected(false);
					radiobtn2.setBackground(Color.white);
					radiobtn4.setSelected(false);
					radiobtn4.setBackground(Color.white);
				}
				else
				{
					summary=radiobtn3.getText();
					radiobtn3.setSelected(true);
					radiobtn3.setBackground(Color.YELLOW);
					radiobtn1.setSelected(false);
					radiobtn1.setBackground(Color.white);
					radiobtn2.setSelected(false);
					radiobtn2.setBackground(Color.white);
					radiobtn4.setSelected(false);
					radiobtn4.setBackground(Color.white);
				}
			}
		});
		radiobtn3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		radiobtn3.setBounds(41, 229, 488, 43);
		panel_4.add(radiobtn3);
		
		radiobtn1 = new JRadioButton("<html><p>Universities are praised for clearly indicating the career outcomes of their Ph.D and postdoc programs.</p></html>");
		radiobtn1.setVerticalAlignment(SwingConstants.TOP);
		radiobtn1.setBackground(Color.WHITE);
		radiobtn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(radiobtn1.isSelected())
				{
					summary=radiobtn1.getText();
					radiobtn1.setBackground(Color.YELLOW);
					radiobtn2.setSelected(false);
					radiobtn2.setBackground(Color.white);
					radiobtn3.setSelected(false);
					radiobtn3.setBackground(Color.white);
					radiobtn4.setSelected(false);
					radiobtn4.setBackground(Color.white);
				}
				else
				{
					summary=radiobtn1.getText();
					radiobtn1.setSelected(true);
					radiobtn1.setBackground(Color.YELLOW);
					radiobtn2.setBackground(Color.white);
					radiobtn3.setSelected(false);
					radiobtn3.setBackground(Color.white);
					radiobtn4.setSelected(false);
					radiobtn4.setBackground(Color.white);
				}
			}
		});
		radiobtn1.setOpaque(true);
		radiobtn1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		radiobtn1.setBounds(41, 98, 488, 43);
		panel_4.add(radiobtn1);
		
		radiobtn2 = new JRadioButton("<html><p>Universities are criticized by experts for not being able to track and report how well their Ph.D and postdoc alumni do in the job market.</p></html>");
		radiobtn2.setVerticalAlignment(SwingConstants.TOP);
		radiobtn2.setBackground(Color.WHITE);
		radiobtn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(radiobtn2.isSelected())
				{
					summary=radiobtn2.getText();
					radiobtn2.setBackground(Color.YELLOW);
					radiobtn1.setSelected(false);
					radiobtn1.setBackground(Color.WHITE);
					radiobtn3.setSelected(false);
					radiobtn3.setBackground(Color.WHITE);
					radiobtn4.setSelected(false);
					radiobtn4.setBackground(Color.WHITE);
				}
				else
				{
					summary=radiobtn2.getText();
					radiobtn2.setSelected(true);
					radiobtn2.setBackground(Color.YELLOW);
					radiobtn1.setSelected(false);
					radiobtn1.setBackground(Color.WHITE);
					radiobtn3.setSelected(false);
					radiobtn3.setBackground(Color.WHITE);
					radiobtn4.setSelected(false);
					radiobtn4.setBackground(Color.WHITE);
				}
			}
		});
		radiobtn2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		radiobtn2.setBounds(41, 162, 488, 40);
		panel_4.add(radiobtn2);
		
		radiobtn4 = new JRadioButton("<html><p>Research shows that most graduates are certain of what kinds of jobs they can expect.</p></html>");
		radiobtn4.setVerticalAlignment(SwingConstants.TOP);
		radiobtn4.setBackground(Color.WHITE);
		radiobtn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(radiobtn4.isSelected())
				{
					summary=radiobtn4.getText();
					radiobtn4.setBackground(Color.YELLOW);
					radiobtn2.setSelected(false);
					radiobtn2.setBackground(Color.WHITE);
					radiobtn3.setSelected(false);
					radiobtn3.setBackground(Color.WHITE);
					radiobtn1.setSelected(false);
					radiobtn1.setBackground(Color.WHITE);
				}
				else
				{
					summary=radiobtn4.getText();
					radiobtn4.setSelected(true);
					radiobtn4.setBackground(Color.YELLOW);
					radiobtn2.setSelected(false);
					radiobtn2.setBackground(Color.WHITE);
					radiobtn3.setSelected(false);
					radiobtn3.setBackground(Color.WHITE);
					radiobtn1.setSelected(false);
					radiobtn1.setBackground(Color.WHITE);
				}
			}
		});
		radiobtn4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		radiobtn4.setBounds(41, 298, 488, 40);
		panel_4.add(radiobtn4);
		
		JTextPane textPane = new JTextPane();
		textPane.setForeground(new Color(0, 0, 128));
		textPane.setText("Read the text and answer the multipile-choice question by selecting the correct response. Only one response is correct.");
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textPane.setEditable(false);
		textPane.setBounds(10, 41, 519, 40);
		panel_4.add(textPane);
		
		JLabel label = new JLabel("\u00A9Lab Symbiotic");
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.DARK_GRAY);
		label.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		label.setBounds(0, 704, 1370, 24);
		frame.getContentPane().add(label);
	}
	
	/******************************** file saver **************************************/
	
	public void fileSaver() throws IOException, ClassNotFoundException{
		if(!radiobtn1.isSelected()&&!radiobtn2.isSelected()&&!radiobtn3.isSelected()&&!radiobtn4.isSelected())
		{
			String[] options = new String[2];
			options[0] = new String("Agree");
			options[1] = new String("Disagree");
			int choice=JOptionPane.showOptionDialog(frame.getContentPane(),"Are you sure to save and step ahead?!","Confirmation to proceed", JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,null,options,null);
			if(choice==0)
			{
				File answer=new File("Answer//ReadingAns.ser");
					if(answer.exists())
					{
						FileInputStream fis=new FileInputStream(answer);
						ObjectInputStream ois=new ObjectInputStream(fis);
						ReadingAnswer ra=(ReadingAnswer)ois.readObject();
						ra.mcq1="";
						ois.close();
						fis.close();
						answer.delete();
						FileOutputStream fos=new FileOutputStream(new File("Answer//ReadingAns.ser"),true);
						ObjectOutputStream oos=new ObjectOutputStream(new BufferedOutputStream (fos));
						oos.writeObject(ra);
						oos.close();
						fos.close();
					}
					else
					{
						File answer1=new File("Answer//ReadingAns.ser");
						ReadingAnswer ra=new ReadingAnswer();
						ra.mcq1="";
						FileOutputStream fos=new FileOutputStream(answer1,true);
						ObjectOutputStream oos=new ObjectOutputStream(new BufferedOutputStream (fos));
						oos.writeObject(ra);
						oos.close();
						fos.close();
					}
				
			}
		}
		else
		{
			File answer=new File("Answer//ReadingAns.ser");
				if(answer.exists())
				{
					FileInputStream fis=new FileInputStream(answer);
					ObjectInputStream ois=new ObjectInputStream(fis);
					ReadingAnswer ra=(ReadingAnswer)ois.readObject();
					ra.mcq1=summary;
					ois.close();
					fis.close();
					answer.delete();
					FileOutputStream fos=new FileOutputStream(new File("Answer//ReadingAns.ser"),true);
					ObjectOutputStream oos=new ObjectOutputStream(new BufferedOutputStream (fos));
					oos.writeObject(ra);
					oos.close();
					fos.close();
				}
				else
				{
					File answer1=new File("Answer//ReadingAns.ser");
					ReadingAnswer ra=new ReadingAnswer();
					ra.mcq1=summary;
					FileOutputStream fos=new FileOutputStream(answer1,true);
					ObjectOutputStream oos=new ObjectOutputStream(new BufferedOutputStream (fos));
					oos.writeObject(ra);
					oos.close();
					fos.close();
				}
			
		}
		
	}
}
