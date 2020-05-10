package set1reading;

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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import main.Functions;
import main.Menu;
import main.ReadingAnswer;
import main.sqlConnection;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import javax.swing.border.MatteBorder;

public class Set1reading4 {

	public JFrame frame;
	public String summary;
	JRadioButton radiobtn1;
	JRadioButton radiobtn2;
	JRadioButton radiobtn3;
	JRadioButton radiobtn4;
	JRadioButton radiobtn5;
	public List <String> answers=new ArrayList <String>();;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Set1reading4 window = new Set1reading4();
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
	public Set1reading4() {
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
		panel.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 128)));
		panel.setBackground(new Color(255, 255, 255));
		panel.setLayout(null);
		panel.setBounds(758, 170, 539, 379);
		frame.getContentPane().add(panel);
		
		JLabel lblwhichOfThe = new JLabel("<html><p>Which of the following can be concluded from the passage? </p></html>");
		lblwhichOfThe.setBackground(Color.WHITE);
		lblwhichOfThe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblwhichOfThe.setBounds(46, 82, 406, 40);
		panel.add(lblwhichOfThe);
		
		radiobtn1 = new JRadioButton("<html><p>The number of earthquakes is closely related to depth. </p></html>");
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
		radiobtn1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		radiobtn1.setBounds(88, 141, 357, 30);
		//radiobtn1.setOpaque(true);
		panel.add(radiobtn1);
		
		radiobtn2 = new JRadioButton("<html><p>Roughly the same number of earthquakes occur each year. </p></html>");
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
		radiobtn2.setOpaque(true);
		radiobtn2.setBounds(88, 180, 406, 30);
		panel.add(radiobtn2);
		
		radiobtn3 = new JRadioButton("<html><p>Earthquakes are impossible at depths over 460 miles. </p></html>");
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
		radiobtn3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		radiobtn3.setBounds(88, 219, 357, 30);
		radiobtn3.setOpaque(true);
		panel.add(radiobtn3);
		
		radiobtn4 = new JRadioButton("<html><p>Earthquakes are most likely to occur near the surfaces.</p></html>");
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
		radiobtn4.setBounds(88, 257, 372, 30);
		radiobtn4.setOpaque(true);
		panel.add(radiobtn4);
		
		radiobtn5 = new JRadioButton("<html><p>The United Nations sent a team of geologist and engineers to help countries affected by earthquakes. </p></html>");
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
		radiobtn5.setOpaque(true);
		radiobtn5.setBounds(88, 297, 388, 49);
		panel.add(radiobtn5);
		
		JLabel lblreadTheText = new JLabel("<html><p align='center'>Read the text and answer the question by selecting the correct responses. More than one response is correct.</p></html>");
		lblreadTheText.setForeground(new Color(0, 0, 128));
		lblreadTheText.setBackground(Color.WHITE);
		lblreadTheText.setVerticalAlignment(SwingConstants.TOP);
		lblreadTheText.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblreadTheText.setBounds(14, 11, 515, 49);
		panel.add(lblreadTheText);
		
		JButton button = new JButton("Next");
		button.setBackground(Color.WHITE);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					fileSaver();
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				frame.dispose();
				Set1reading5 go=new Set1reading5();
				go.frame.setVisible(true);
			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 12));
		button.setBounds(1208, 640, 89, 28);
		frame.getContentPane().add(button);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 0, 1370, 56);
		frame.getContentPane().add(panel_1);
		
		JLabel lblReadingTestmultiple = new JLabel("Reading test");
		lblReadingTestmultiple.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblReadingTestmultiple.setBounds(10, 0, 497, 56);
		panel_1.add(lblReadingTestmultiple);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(255, 140, 0));
		panel_2.setBounds(0, 55, 1370, 33);
		frame.getContentPane().add(panel_2);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(255, 255, 255));
		panel_4.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 128)));
		panel_4.setBounds(86, 170, 613, 379);
		frame.getContentPane().add(panel_4);
		panel_4.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 16, 584, 358);
		panel_4.add(scrollPane);
		scrollPane.setBorder(BorderFactory.createLineBorder(Color.white));
		
		JTextPane txtpnMostEarthquakesOccur = new JTextPane();
		txtpnMostEarthquakesOccur.setEditable(false);
		txtpnMostEarthquakesOccur.setText("\r\nMost earthquakes occur within the upper 15 miles of the earth\u2019s surface. But earthquakes can and do occur at all depths to about 460 miles. Their number decreases as the depth increases. At about 460 miles one earthquake occurs only every few years. Near the surface earthquakes may run as high as 100 in a month, but the yearly average does not very much. In comparison with the total number of earthquakes each year, the number of disastrous earthquakes is very small.\r\n\r\nThe extent of the disaster in an earthquake depends on many factors. If you carefully build a toy house with an erect set, it will still stand no matter how much you shake the table. But if you build a toy house with a pack of cards, a slight shake of the table will make it fall. An earthquake in Agadir, Morocco, was not strong enough to be recorded on distant instruments, but it completely destroyed the city. Many stronger earthquakes have done comparatively little damage. If a building is well constructed and built on solid ground, it will resist an earthquake. Most deaths in earthquakes have been due to faulty building construction or poor building sites. A third and very serious factor is panic. When people rush out into narrow streets, more deaths will result.\r\n\r\nThe United Nations has played an important part in reducing the damage done by earthquakes. It has sent a team of experts to all countries known to be affected by earthquakes. Working with local geologists and engineers, the experts have studied the nature of the ground and the type of most practical building code for the local area. If followed, these suggestions will make disastrous earthquakes almost a thing of the past.\r\n");
		txtpnMostEarthquakesOccur.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtpnMostEarthquakesOccur.setBorder(BorderFactory.createLineBorder(Color.white));
		txtpnMostEarthquakesOccur.setBackground(Color.WHITE);
		scrollPane.setViewportView(txtpnMostEarthquakesOccur);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(Color.DARK_GRAY, 2));
		panel_3.setBackground(new Color(224, 255, 255));
		panel_3.setBounds(86, 640, 122, 28);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JLabel lblItemNo = new JLabel("Item no 4 of 15");
		lblItemNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemNo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_3.add(lblItemNo, BorderLayout.CENTER);
		
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
					ra.mcq4.addAll(answers);
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
				ra.mcq4.addAll(answers);
				FileOutputStream fos=new FileOutputStream(answer,true);
				ObjectOutputStream oos=new ObjectOutputStream(new BufferedOutputStream (fos));
				oos.writeObject(ra);
				oos.close();
				fos.close();
			}
		}
		
	}
}
