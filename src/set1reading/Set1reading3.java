package set1reading;

import java.awt.BorderLayout;
import java.awt.Color;
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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import main.Functions;
import main.Menu;
import main.ReadingAnswer;
import main.sqlConnection;

public class Set1reading3 {

	public JFrame frame;
	JRadioButton radiobtn1;
	JRadioButton radiobtn2;
	JRadioButton radiobtn3;
	JRadioButton radiobtn4;
	JRadioButton radiobtn5;
	public String summary;
	public List <String> answers=new ArrayList <String>();;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Set1reading3 window = new Set1reading3();
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
	public Set1reading3() {
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
		
		JLabel lblReadingTestmultiple = new JLabel("Reading test");
		lblReadingTestmultiple.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblReadingTestmultiple.setBounds(10, 0, 497, 56);
		panel.add(lblReadingTestmultiple);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(255, 140, 0));
		panel_1.setBounds(0, 55, 1370, 33);
		frame.getContentPane().add(panel_1);
		
		JButton button_2 = new JButton("Next");
		button_2.setBackground(Color.WHITE);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					fileSaver();
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				frame.dispose();
				Set1reading4 go=new Set1reading4();
				go.frame.setVisible(true);
			}
		});
		button_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_2.setBounds(1200, 625, 89, 28);
		frame.getContentPane().add(button_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(Color.DARK_GRAY, 2));
		panel_2.setBackground(new Color(224, 255, 255));
		panel_2.setBounds(81, 615, 122, 28);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JLabel lblItemNo = new JLabel("Item no 3 of 15");
		lblItemNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemNo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_2.add(lblItemNo, BorderLayout.CENTER);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 128)));
		panel_3.setBackground(Color.WHITE);
		panel_3.setLayout(null);
		panel_3.setBounds(676, 142, 613, 424);
		frame.getContentPane().add(panel_3);
		
		JLabel lblwhichOfThe = new JLabel("<html><p>Which of the following statements correctly describe the methodology or results of the study?</p></html>");
		lblwhichOfThe.setForeground(new Color(0, 0, 205));
		lblwhichOfThe.setBackground(Color.WHITE);
		lblwhichOfThe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblwhichOfThe.setBounds(26, 28, 559, 40);
		panel_3.add(lblwhichOfThe);
		
		radiobtn1 = new JRadioButton("<html><p>A.\tThe study did not really give a significant result as they found no statistical differences in connectivity within hemispheres, or between men and women.</p></html>");
		radiobtn1.setBackground(Color.WHITE);
		radiobtn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(radiobtn1.isSelected())
				{
					answers.add(radiobtn1.getText().trim());
					radiobtn1.setBackground(Color.YELLOW);
				}
				else
				{
					answers.remove(radiobtn1.getText().trim());
					radiobtn1.setBackground(Color.WHITE);
				}
			}
		});
		radiobtn1.setVerticalAlignment(SwingConstants.TOP);
		radiobtn1.setOpaque(true);
		radiobtn1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		radiobtn1.setBounds(69, 90, 501, 40);
		panel_3.add(radiobtn1);
		
		radiobtn2 = new JRadioButton("<html><p>The study found that the main difference between participants assessed to be the most and least creative was in the frontal lobe of their brains.</p></html>");
		radiobtn2.setVerticalAlignment(SwingConstants.TOP);
		radiobtn2.setBackground(Color.WHITE);
		radiobtn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(radiobtn2.isSelected())
				{
					answers.add(radiobtn2.getText().trim());
					radiobtn2.setBackground(Color.YELLOW);
				}
				else
				{
					answers.remove(radiobtn2.getText().trim());
					radiobtn2.setBackground(Color.WHITE);
				}
			}
		});
		radiobtn2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		radiobtn2.setBounds(69, 150, 488, 40);
		radiobtn2.setOpaque(true);
		panel_3.add(radiobtn2);
		
		radiobtn3 = new JRadioButton("<html><p>The results of the study seemed inconclusive until the assessments of the top and bottom 15% of the participants were compared.</p></html>");
		radiobtn3.setBackground(Color.WHITE);
		radiobtn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(radiobtn3.isSelected())
				{
					answers.add(radiobtn3.getText().trim());
					radiobtn3.setBackground(Color.YELLOW);
				}
				else
				{
					answers.remove(radiobtn3.getText().trim());
					radiobtn3.setBackground(Color.WHITE);
				}
			}
		});
		radiobtn3.setVerticalAlignment(SwingConstants.TOP);
		radiobtn3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		radiobtn3.setOpaque(true);
		radiobtn3.setBounds(69, 208, 488, 43);
		panel_3.add(radiobtn3);
		
		radiobtn4 = new JRadioButton("<html><p>Though the study did not show any statistical differences in connectivity within hemispheres, it was found that the percentage of creative men were 15% higher than women.</p></html>");
		radiobtn4.setVerticalAlignment(SwingConstants.TOP);
		radiobtn4.setBackground(Color.WHITE);
		radiobtn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(radiobtn4.isSelected())
				{
					answers.add(radiobtn4.getText().trim());
					radiobtn4.setBackground(Color.YELLOW);
				}
				else
				{
					answers.remove(radiobtn4.getText().trim());
					radiobtn4.setBackground(Color.WHITE);
				}
			}
		});
		radiobtn4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		radiobtn4.setBounds(69, 267, 488, 56);
		radiobtn4.setOpaque(true);
		panel_3.add(radiobtn4);
		
		radiobtn5 = new JRadioButton("<html><p>The participants\u2019 creativity was tested by assessing how many different answers they could provide for one question.</p></html>");
		radiobtn5.setVerticalAlignment(SwingConstants.TOP);
		radiobtn5.setBackground(Color.WHITE);
		radiobtn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(radiobtn5.isSelected())
				{
					answers.add(radiobtn5.getText().trim());
					radiobtn5.setBackground(Color.YELLOW);
				}
				else
				{
					answers.remove(radiobtn5.getText().trim());
					radiobtn5.setBackground(Color.WHITE);
				}
			}
		});
		radiobtn5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		radiobtn5.setBounds(69, 335, 472, 46);
		radiobtn5.setOpaque(true);
		panel_3.add(radiobtn5);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 128)));
		panel_4.setLayout(null);
		panel_4.setBackground(Color.WHITE);
		panel_4.setBounds(81, 142, 585, 424);
		frame.getContentPane().add(panel_4);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 21, 539, 380);
		scrollPane.setBorder(BorderFactory.createLineBorder(Color.white));
		panel_4.add(scrollPane);
		
		JTextPane txtpnSeeminglyCountlessSelfhelp = new JTextPane();
		txtpnSeeminglyCountlessSelfhelp.setEditable(false);
		txtpnSeeminglyCountlessSelfhelp.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtpnSeeminglyCountlessSelfhelp.setText("Seemingly countless self-help books and seminars tell you to tap into the right side of your brain to stimulate creativity. But forget the \"right-brain\" myth -- a new study suggests it's how well the two brain hemispheres communicate that sets highly creative people apart.\r\nFor the study, statisticians David Dunson of Duke University and Daniele Durante of the University of Padova analyzed the network of white matter connections among 68 separate brain regions in healthy college-age volunteers.\r\nA team led by neuroscientist Rex Jung of the University of New Mexico collected the data using an MRI technique called diffusion tensor imaging. Jung's team used a combination of tests to assess creativity. Some were measures of a type of problem-solving called \"divergent thinking,\" or the ability to come up with many answers to a question. They asked people to draw as many geometric designs as they could in five minutes. They also asked people to list as many new uses as they could for everyday objects, such as a brick or a paper clip. The participants also filled out a questionnaire about their achievements in ten areas, including the visual arts, music, creative writing, dance, cooking and science.\r\nThe responses were used to calculate a composite creativity score for each person.\r\nDunson and Durante trained computers to sift through the data and identify differences in brain structure.\r\nThey found no statistical differences in connectivity within hemispheres, or between men and women. But when they compared people who scored in the top 15 percent on the creativity tests with those in the bottom 15 percent, high-scoring people had significantly more connections between the right and left hemispheres.\r\nThe differences were mainly in the brain's frontal lobe.");
		scrollPane.setViewportView(txtpnSeeminglyCountlessSelfhelp);
		
		JLabel label = new JLabel("\u00A9Lab Symbiotic");
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.DARK_GRAY);
		label.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		label.setBounds(0, 704, 1370, 24);
		frame.getContentPane().add(label);
	}
	
	
	public void fileSaver() throws IOException, ClassNotFoundException{
		if(!radiobtn1.isSelected()&&!radiobtn2.isSelected()&&!radiobtn3.isSelected()&&!radiobtn4.isSelected()&&!radiobtn5.isSelected())
		{
			String[] options = new String[2];
			options[0] = new String("Agree");
			options[1] = new String("Disagree");
			int choice=JOptionPane.showOptionDialog(frame.getContentPane(),"Are you sure to save and step ahead?!","Confirmation to proceed", JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,null,options,null);
			if(choice==0)
			{
				JOptionPane.showMessageDialog(frame, "No answer has been ");
			}
		}
		else
		{
			File answerFolder = new File("Answer");
			if(answerFolder.exists())
			{
				File answer=new File("Answer//ReadingAns.ser");
				if(answer.exists())
				{
					FileInputStream fis=new FileInputStream(answer);
					ObjectInputStream ois=new ObjectInputStream(fis);
					ReadingAnswer ra=(ReadingAnswer)ois.readObject();
					ra.mcq3.addAll(answers);
					ois.close();
					fis.close();
					answer.delete();
					FileOutputStream fos=new FileOutputStream(new File("Answer//ReadingAns.ser"),true);
					ObjectOutputStream oos=new ObjectOutputStream(new BufferedOutputStream (fos));
					oos.writeObject(ra);
					oos.close();
					fos.close();
				}
			}
			else
			{
				answerFolder.mkdirs();
				File answer=new File("Answer//ReadingAns.ser");
				ReadingAnswer ra=new ReadingAnswer();
				ra.mcq3.addAll(answers);
				FileOutputStream fos=new FileOutputStream(answer,true);
				ObjectOutputStream oos=new ObjectOutputStream(new BufferedOutputStream (fos));
				oos.writeObject(ra);
				oos.close();
				fos.close();
			}
		}
		
	}
}
