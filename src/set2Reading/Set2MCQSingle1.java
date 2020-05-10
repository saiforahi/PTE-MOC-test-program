package set2Reading;

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

public class Set2MCQSingle1 {

	public JFrame frame;
	JRadioButton radiobtn1;
	JRadioButton radiobtn2;
	JRadioButton radiobtn3;
	JRadioButton radiobtn4;
	JButton nextButton;
	public static JTextPane txtpnIndigenousamericansTheSioux;
	public String summary;
	public static JPanel panel_3;
	private JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Set2MCQSingle1 window = new Set2MCQSingle1();
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
	public Set2MCQSingle1() {
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
		
		JLabel lblReadingTestmultiple = new JLabel("Reading Test (multiple choice)");
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
				Set2MCQSingle2 go=new Set2MCQSingle2();
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
		panel_3.setBorder(new MatteBorder(3, 1, 3, 1, (Color) new Color(51, 0, 102)));
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(134, 138, 539, 435);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		txtpnIndigenousamericansTheSioux = new JTextPane();
		txtpnIndigenousamericansTheSioux.setEditable(false);
		txtpnIndigenousamericansTheSioux.setBackground(Color.WHITE);
		txtpnIndigenousamericansTheSioux.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtpnIndigenousamericansTheSioux.setText("\r\n\r\n\r\n\r\nAuthors of depression studies encourage Ph.D. students to appreciate how important it is to take care of themselves. \u201CMental health problems can develop into serious threats to one\u2019s well-being and career, and can have detrimental consequences in the long-term,\u201D they write. So, if you\u2019re struggling, it\u2019s important to \u201Cseek professional help or seek help in your personal environment, even if you think it\u2019s probably a temporary thing.\u201D The greatest predictor for experiencing mental health challenges was having difficulty taking care of family needs due to conflicting work commitments.");
		txtpnIndigenousamericansTheSioux.setBounds(23, 21, 494, 392);
		panel_3.add(txtpnIndigenousamericansTheSioux);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new MatteBorder(3, 1, 3, 1, (Color) new Color(51, 0, 102)));
		panel_4.setBackground(Color.WHITE);
		panel_4.setLayout(null);
		panel_4.setBounds(715, 138, 539, 435);
		frame.getContentPane().add(panel_4);
		
		JTextPane textPane = new JTextPane();
		textPane.setText("Read the text and answer the multipile-choice question by selecting the correct response. Only one response is correct.");
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textPane.setEditable(false);
		textPane.setBounds(41, 60, 461, 40);
		panel_4.add(textPane);
		
		radiobtn1 = new JRadioButton("<html><p>Authors of depression studies say mental health problems of Ph.D students are probably a temporary thing.</p></html>");
		radiobtn1.setVerticalAlignment(SwingConstants.TOP);
		radiobtn1.setBackground(Color.WHITE);
		radiobtn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(radiobtn1.isSelected())
				{
					summary=radiobtn1.getText();
					radiobtn2.setSelected(false);
					radiobtn3.setSelected(false);
					radiobtn4.setSelected(false);
				}
				else
				{
					summary=radiobtn1.getText();
					radiobtn1.setSelected(true);
					radiobtn2.setSelected(false);
					radiobtn3.setSelected(false);
					radiobtn4.setSelected(false);
				}
			}
		});
		radiobtn1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		radiobtn1.setBounds(41, 134, 488, 43);
		panel_4.add(radiobtn1);
		
		radiobtn2 = new JRadioButton("<html><p>Authors of depression studies tell Ph.D. students to combat depression by prioritising family needs over taking care of themselves.</p></html>");
		radiobtn2.setVerticalAlignment(SwingConstants.TOP);
		radiobtn2.setBackground(Color.WHITE);
		radiobtn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(radiobtn2.isSelected())
				{
					summary=radiobtn2.getText();
					radiobtn1.setSelected(false);
					radiobtn3.setSelected(false);
					radiobtn4.setSelected(false);
				}
				else
				{
					summary=radiobtn2.getText();
					radiobtn2.setSelected(true);
					radiobtn1.setSelected(false);
					radiobtn3.setSelected(false);
					radiobtn4.setSelected(false);
				}
			}
		});
		radiobtn2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		radiobtn2.setBounds(41, 198, 488, 40);
		panel_4.add(radiobtn2);
		
		radiobtn3 = new JRadioButton("<html><p>Mental health problems only have detrimental consequences in the short-term.</p></html>");
		radiobtn3.setVerticalAlignment(SwingConstants.TOP);
		radiobtn3.setBackground(Color.WHITE);
		radiobtn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(radiobtn3.isSelected())
				{
					summary=radiobtn3.getText();
					radiobtn1.setSelected(false);
					radiobtn2.setSelected(false);
					radiobtn4.setSelected(false);
				}
				else
				{
					summary=radiobtn3.getText();
					radiobtn3.setSelected(true);
					radiobtn2.setSelected(false);
					radiobtn1.setSelected(false);
					radiobtn4.setSelected(false);
				}
			}
		});
		radiobtn3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		radiobtn3.setBounds(41, 265, 488, 43);
		panel_4.add(radiobtn3);
		
		radiobtn4 = new JRadioButton("<html><p>People who have conflicting work commitments that make it difficult for them to take care of family needs are most likely to experience mental health challenges.</p></html>");
		radiobtn4.setVerticalAlignment(SwingConstants.TOP);
		radiobtn4.setBackground(Color.WHITE);
		radiobtn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(radiobtn4.isSelected())
				{
					summary=radiobtn4.getText();
					radiobtn2.setSelected(false);
					radiobtn3.setSelected(false);
					radiobtn1.setSelected(false);
				}
				else
				{
					summary=radiobtn4.getText();
					radiobtn4.setSelected(true);
					radiobtn2.setSelected(false);
					radiobtn3.setSelected(false);
					radiobtn1.setSelected(false);
				}
			}
		});
		radiobtn4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		radiobtn4.setBounds(41, 334, 488, 40);
		panel_4.add(radiobtn4);
		
		label = new JLabel("\u00A9Lab Symbiotic");
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
