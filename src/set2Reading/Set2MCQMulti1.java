package set2Reading;

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

import main.Functions;
import main.Menu;
import main.ReadingAnswer;
import main.sqlConnection;
import javax.swing.border.MatteBorder;

public class Set2MCQMulti1 {

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
					Set2MCQMulti1 window = new Set2MCQMulti1();
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
	public Set2MCQMulti1() {
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
				Set2MCQMulti2 go=new Set2MCQMulti2();
				go.frame.setVisible(true);
			}
		});
		button_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_2.setBounds(1200, 615, 89, 28);
		frame.getContentPane().add(button_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(Color.DARK_GRAY, 2));
		panel_2.setBackground(new Color(224, 255, 255));
		panel_2.setBounds(81, 615, 122, 28);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JLabel lblItemNo = new JLabel("Item no 3 of 10");
		lblItemNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemNo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_2.add(lblItemNo, BorderLayout.CENTER);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new MatteBorder(3, 1, 3, 1, (Color) new Color(51, 0, 102)));
		panel_3.setBackground(Color.WHITE);
		panel_3.setLayout(null);
		panel_3.setBounds(712, 142, 577, 424);
		frame.getContentPane().add(panel_3);
		
		JLabel lblwhichOfThe = new JLabel("<html><p>Which of the following statements correctly describe the methodology or results of the study?</p></html>");
		lblwhichOfThe.setForeground(new Color(0, 0, 205));
		lblwhichOfThe.setBackground(Color.WHITE);
		lblwhichOfThe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblwhichOfThe.setBounds(30, 30, 523, 40);
		panel_3.add(lblwhichOfThe);
		
		radiobtn1 = new JRadioButton("<html><p>Stable trade relations need to be maintained with countries at risk of facing natural disasters.</p></html>");
		radiobtn1.setBackground(Color.WHITE);
		radiobtn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(radiobtn1.isSelected())
				{
					answers.add(radiobtn1.getText().trim());
					
				}
				else
				{
					answers.remove(radiobtn1.getText().trim());
					
				}
			}
		});
		radiobtn1.setVerticalAlignment(SwingConstants.TOP);
		radiobtn1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		radiobtn1.setBounds(62, 103, 501, 40);
		panel_3.add(radiobtn1);
		
		radiobtn2 = new JRadioButton("<html><p>In the upcoming years, flood risks will remain the same and be unaffected by climate change.</p></html>");
		radiobtn2.setVerticalAlignment(SwingConstants.TOP);
		radiobtn2.setBackground(Color.WHITE);
		radiobtn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(radiobtn2.isSelected())
				{
					answers.add(radiobtn2.getText().trim());
					
				}
				else
				{
					answers.remove(radiobtn2.getText().trim());
					
				}
			}
		});
		radiobtn2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		radiobtn2.setBounds(62, 167, 488, 40);
		panel_3.add(radiobtn2);
		
		radiobtn3 = new JRadioButton("<html><p>Experts say that the US will lose 600 billion US dollars within the next 20 years because of fluvial floods in China..</p></html>");
		radiobtn3.setBackground(Color.WHITE);
		radiobtn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(radiobtn3.isSelected())
				{
					answers.add(radiobtn3.getText().trim());
					
				}
				else
				{
					answers.remove(radiobtn3.getText().trim());
					
				}
			}
		});
		radiobtn3.setVerticalAlignment(SwingConstants.TOP);
		radiobtn3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		radiobtn3.setBounds(62, 224, 488, 43);
		panel_3.add(radiobtn3);
		
		radiobtn4 = new JRadioButton("<html><p>Damages suffered in China due to flooding can have a significant impact on the global economy.</p></html>");
		radiobtn4.setVerticalAlignment(SwingConstants.TOP);
		radiobtn4.setBackground(Color.WHITE);
		radiobtn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(radiobtn4.isSelected())
				{
					answers.add(radiobtn4.getText().trim());
					
				}
				else
				{
					answers.remove(radiobtn4.getText().trim());
					
				}
			}
		});
		radiobtn4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		radiobtn4.setBounds(62, 280, 488, 43);
		panel_3.add(radiobtn4);
		
		radiobtn5 = new JRadioButton("<html><p>The US is taking the right course of action by imposing tariff sanctions due to China\u2019s economic instability. </p></html>");
		radiobtn5.setVerticalAlignment(SwingConstants.TOP);
		radiobtn5.setBackground(Color.WHITE);
		radiobtn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(radiobtn5.isSelected())
				{
					answers.add(radiobtn5.getText().trim());
					
				}
				else
				{
					answers.remove(radiobtn5.getText().trim());
					
				}
			}
		});
		radiobtn5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		radiobtn5.setBounds(62, 336, 472, 46);
		panel_3.add(radiobtn5);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new MatteBorder(3, 1, 3, 1, (Color) new Color(51, 0, 102)));
		panel_4.setLayout(null);
		panel_4.setBackground(Color.WHITE);
		panel_4.setBounds(81, 142, 585, 424);
		frame.getContentPane().add(panel_4);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 21, 539, 380);
		scrollPane.setBorder(BorderFactory.createLineBorder(Color.white));
		panel_4.add(scrollPane);
		
		JTextPane txtpnSeeminglyCountlessSelfhelp = new JTextPane();
		scrollPane.setViewportView(txtpnSeeminglyCountlessSelfhelp);
		txtpnSeeminglyCountlessSelfhelp.setBorder(BorderFactory.createLineBorder(Color.white));
		txtpnSeeminglyCountlessSelfhelp.setText("Intensifying river floods could lead to regional production losses worldwide caused by global warming. This might not only hamper local economies around the globe -- the effects might also propagate through the global network of trade and supply chains, a study now published in Nature Climate Change shows. It is the first to assess this effect for flooding on a global scale, using a newly developed dynamic economic model. It finds that economic flood damages in China, which could, without further adaption, increase by 80 percent within the next 20 years, might also affect EU and US industries. The US economy might be specifically vulnerable due to its unbalanced trade relation with China. Contrary to US president Trump's current tariff sanctions, the study suggests that building stronger and thus more balanced trade relations might be a useful strategy to mitigate economic losses caused by intensifying weather extremes.\r\n\r\n\"Climate change will increase flood risks already in the next two decades -- and this is not only a problem for millions of people but also for economies worldwide,\" says Anders Levermann, project leader from the Potsdam Institute for Climate Impact Research (PIK) in Germany and the Columbia University in New York. Without further adaption measures, climate change will likely increase economic losses worldwide due to fluvial floods by more than 15 percent accumulating to a total of about 600 billion US dollar within the next 20 years. While the bulk of this is independent of climate change, the rise is not. \"Not only local industries will be affected by these climate impacts,\" says Sven Willner, lead author of the study from PIK. \"Through supply shortages, changes in demand and associated price signals, economic losses might be down-streamed along the global trade and supply network affecting other economies on a global scale -- we were surprised about the size of this rather worrying effect.\"\r\n");
		txtpnSeeminglyCountlessSelfhelp.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtpnSeeminglyCountlessSelfhelp.setBackground(Color.WHITE);
		
		JLabel label = new JLabel("\u00A9Lab Symbiotic");
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.DARK_GRAY);
		label.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		label.setBounds(0, 698, 1370, 24);
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
				else
					JOptionPane.showMessageDialog(frame, "Reading Answer sheet is missing.");
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
