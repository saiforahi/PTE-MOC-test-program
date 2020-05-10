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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
import main.Functions;
import main.Menu;
import main.ReadingAnswer;
import main.sqlConnection;
import javax.swing.border.MatteBorder;

public class Set1reading2 {

	public JFrame frame;
	JRadioButton radiobtn1;
	JRadioButton radiobtn2;
	JRadioButton radiobtn3;
	JRadioButton radiobtn4;
	public String summary;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Set1reading2 window = new Set1reading2();
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
	public Set1reading2() {
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
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(2, 0, 2, 0, (Color) new Color(0, 51, 102)));
		panel_2.setBackground(Color.WHITE);
		panel_2.setLayout(null);
		panel_2.setBounds(134, 175, 539, 381);
		frame.getContentPane().add(panel_2);
		
		JTextPane txtpnOnOctoberthe = new JTextPane();
		txtpnOnOctoberthe.setEditable(false);
		txtpnOnOctoberthe.setText("The University of Toronto\u2019s very useful website lets visitors burrow deep into the data gathered by a team of undergraduates tasked with tracking down each of the 10,886 people who earned Ph.D.s between 2000 and 2015. \r\nThe student sleuths succeeded admirably, locating 9583 individuals and documenting each one\u2019s first or current employment status from \u201Ctwo or more reliable Internet sources,\u201D according to an overview of the project.");
		txtpnOnOctoberthe.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtpnOnOctoberthe.setBackground(Color.WHITE);
		txtpnOnOctoberthe.setBounds(10, 75, 519, 210);
		panel_2.add(txtpnOnOctoberthe);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new LineBorder(Color.DARK_GRAY, 2));
		panel_3.setBackground(new Color(224, 255, 255));
		panel_3.setBounds(134, 615, 140, 33);
		frame.getContentPane().add(panel_3);
		
		JLabel lblItemNo = new JLabel("Item no 2 of 15");
		lblItemNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblItemNo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblItemNo.setBounds(10, 0, 130, 33);
		panel_3.add(lblItemNo);
		
		JButton button_2 = new JButton("Next");
		button_2.setBackground(Color.WHITE);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				try {
					fileSaver();
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Set1reading3 go=new Set1reading3();
				go.frame.setVisible(true);
			}
		});
		button_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_2.setBounds(1165, 615, 89, 28);
		frame.getContentPane().add(button_2);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new MatteBorder(2, 0, 2, 0, (Color) new Color(0, 51, 102)));
		panel_4.setBackground(Color.WHITE);
		panel_4.setLayout(null);
		panel_4.setBounds(695, 175, 558, 381);
		frame.getContentPane().add(panel_4);
		
		JTextPane txtpnReadTheText = new JTextPane();
		txtpnReadTheText.setForeground(new Color(51, 102, 153));
		txtpnReadTheText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtpnReadTheText.setEditable(false);
		txtpnReadTheText.setText("Read the text and answer the multipile-choice question by selecting the correct response. Only one response is correct.");
		txtpnReadTheText.setBounds(47, 43, 519, 40);
		panel_4.add(txtpnReadTheText);
		
		radiobtn1 = new JRadioButton("<html><p>A team of undergraduates were tasked with tracking down how many people earned Ph.D.s between 2000 and 2015.</p></html>");
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
		radiobtn1.setVerticalAlignment(SwingConstants.TOP);
		radiobtn1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		radiobtn1.setBounds(52, 111, 501, 40);
		panel_4.add(radiobtn1);
		
		radiobtn2 = new JRadioButton("<html><p>The employment status of each undergraduate on the team is documented on the University of Toronto\u2019s website.</p></html>");
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
		radiobtn2.setVerticalAlignment(SwingConstants.TOP);
		radiobtn2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		radiobtn2.setBounds(52, 163, 488, 40);
		panel_4.add(radiobtn2);
		
		radiobtn3 = new JRadioButton("<html><p>The students successfully found and recorded the first or current employment status of the majority of Ph.D.s earners between 2000 and 2015.</p></html>");
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
		radiobtn3.setVerticalAlignment(SwingConstants.TOP);
		radiobtn3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		radiobtn3.setBounds(52, 217, 488, 66);
		panel_4.add(radiobtn3);
		
		radiobtn4 = new JRadioButton("<html><p>The student sleuths succeeded in documenting each one\u2019s first or current employment status from at least one reliable Internet source.</p></html>");
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
		radiobtn4.setVerticalAlignment(SwingConstants.TOP);
		radiobtn4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		radiobtn4.setBounds(52, 286, 488, 43);
		panel_4.add(radiobtn4);
		
		JLabel label = new JLabel("\u00A9Lab Symbiotic");
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.DARK_GRAY);
		label.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		label.setBounds(0, 704, 1370, 24);
		frame.getContentPane().add(label);
	}
	
	public void fileSaver() throws IOException, ClassNotFoundException{
		if(!radiobtn1.isSelected()&&!radiobtn2.isSelected()&&!radiobtn3.isSelected()&&!radiobtn4.isSelected())
		{
			String[] options = new String[2];
			options[0] = new String("Agree");
			options[1] = new String("Disagree");
			int choice=JOptionPane.showOptionDialog(frame.getContentPane(),"Are you sure to save and step ahead?!","Confirmation to proceed", JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,null,options,null);
			if(choice==0)
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
						ra.mcq2="";
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
					ra.mcq2="";
					FileOutputStream fos=new FileOutputStream(answer,true);
					ObjectOutputStream oos=new ObjectOutputStream(new BufferedOutputStream (fos));
					oos.writeObject(ra);
					oos.close();
					fos.close();
				}
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
					ra.mcq2=summary;
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
				ra.mcq2=summary;
				FileOutputStream fos=new FileOutputStream(answer,true);
				ObjectOutputStream oos=new ObjectOutputStream(new BufferedOutputStream (fos));
				oos.writeObject(ra);
				oos.close();
				fos.close();
			}
		}
		
	}
}
