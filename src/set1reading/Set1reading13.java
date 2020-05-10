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

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.MatteBorder;

import main.Functions;
import main.Menu;
import main.ReadingAnswer;
import main.sqlConnection;


public class Set1reading13 {

	public JFrame frame;
	JComboBox<String> comboBox1;
	JComboBox<String> comboBox2;
	JComboBox<String> comboBox3;
	JComboBox<String> comboBox4;
	JComboBox<String> comboBox5;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Set1reading13 window = new Set1reading13();
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
	public Set1reading13() {
		initialize();
		frame.setIconImage(Functions.setIcon());
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
		panel.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1370, 56);
		frame.getContentPane().add(panel);
		
		JLabel lblReadingItems = new JLabel("Reading Items");
		lblReadingItems.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblReadingItems.setBounds(10, 0, 497, 56);
		panel.add(lblReadingItems);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(255, 140, 0));
		panel_1.setBounds(0, 55, 1370, 33);
		frame.getContentPane().add(panel_1);
		
		JLabel label_1 = new JLabel("<html><p align='CENTER'>Below is a text with blanks. Click on each blank, a list of choices will appear. Select the appropriate answer choice for each blank.</p></html>");
		label_1.setOpaque(true);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_1.setBorder(new MatteBorder(1, 0, 1, 0, (Color) new Color(0, 0, 0)));
		label_1.setBackground(new Color(240, 255, 240));
		label_1.setBounds(148, 113, 1073, 33);
		frame.getContentPane().add(label_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new MatteBorder(3, 0, 3, 0, (Color) new Color(0, 51, 102)));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(148, 255, 1073, 258);
		frame.getContentPane().add(panel_2);
		
		JLabel lblFoundedAfterWorld = new JLabel("I think more than a lot of other companies, we\u2019re in a position where we have to adjudicate ");
		lblFoundedAfterWorld.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFoundedAfterWorld.setBounds(10, 34, 528, 25);
		panel_2.add(lblFoundedAfterWorld);
		
		comboBox1 = new JComboBox<String>();
		comboBox1.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		comboBox1.setModel(new DefaultComboBoxModel<String>(new String[] {"these kind", "this kinds", "those kinds", "that kinds"}));
		comboBox1.setSelectedIndex(-1);
		comboBox1.setOpaque(true);
		
		comboBox1.setBackground(Color.WHITE);
		comboBox1.setBounds(548, 36, 157, 21);
		panel_2.add(comboBox1);
		
		JLabel lblItsNewestMembers = new JLabel("And in");
		lblItsNewestMembers.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblItsNewestMembers.setBounds(10, 60, 42, 25);
		panel_2.add(lblItsNewestMembers);
		
		comboBox2 = new JComboBox<String>();
		comboBox2.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		comboBox2.setModel(new DefaultComboBoxModel<String>(new String[] {"way", "able", "cause", "order"}));
		comboBox2.setSelectedIndex(-1);
		comboBox2.setOpaque(true);
		comboBox2.setBackground(Color.WHITE);
		comboBox2.setBounds(62, 63, 157, 21);
		panel_2.add(comboBox2);
		
		JLabel lblUnitedNationsDay = new JLabel("But I think it is actually one of the most interesting philosophical questions that we");
		lblUnitedNationsDay.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUnitedNationsDay.setBounds(10, 111, 473, 25);
		panel_2.add(lblUnitedNationsDay);
		
		JLabel lblOnOctober_1 = new JLabel("in every different country, where there are wildly ");
		lblOnOctober_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblOnOctober_1.setBounds(10, 137, 284, 25);
		panel_2.add(lblOnOctober_1);
		
		comboBox3 = new JComboBox<String>();
		comboBox3.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		comboBox3.setModel(new DefaultComboBoxModel<String>(new String[] {"are", "face", "fare", "do"}));
		comboBox3.setSelectedIndex(-1);
		comboBox3.setOpaque(true);
		comboBox3.setBackground(Color.WHITE);
		comboBox3.setBounds(493, 114, 157, 21);
		panel_2.add(comboBox3);
		
		JLabel lblInPeacekeepingAnd = new JLabel("placed to always determine what the policies should be for people all around the world. And I have been working on and thinking");
		lblInPeacekeepingAnd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblInPeacekeepingAnd.setBounds(10, 165, 738, 25);
		panel_2.add(lblInPeacekeepingAnd);
		
		comboBox4 = new JComboBox<String>();
		comboBox4.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		comboBox4.setModel(new DefaultComboBoxModel<String>(new String[] {"similar", "familiar", "different", "open"}));
		comboBox4.setSelectedIndex(-1);
		comboBox4.setOpaque(true);
		comboBox4.setBackground(Color.WHITE);
		comboBox4.setBounds(304, 140, 157, 21);
		panel_2.add(comboBox4);
		
		JLabel lblforTheirWprk = new JLabel("more democratic or community-oriented process that reflects the values of people around the world?");
		lblforTheirWprk.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblforTheirWprk.setBounds(10, 193, 640, 25);
		panel_2.add(lblforTheirWprk);
		
		comboBox5 = new JComboBox<String>();
		comboBox5.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		comboBox5.setModel(new DefaultComboBoxModel<String>(new String[] {"through", "about", "of", "with"}));
		comboBox5.setSelectedIndex(-1);
		comboBox5.setOpaque(true);
		comboBox5.setBackground(Color.WHITE);
		comboBox5.setBounds(763, 168, 157, 21);
		panel_2.add(comboBox5);
		
		JLabel lblOfDisputesBetween = new JLabel("of disputes between different members of our community.");
		lblOfDisputesBetween.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblOfDisputesBetween.setBounds(715, 34, 348, 25);
		panel_2.add(lblOfDisputesBetween);
		
		JLabel lblToDoThat = new JLabel("to do that, we\u2019ve had to build out a whole set of policies and governance around how that works.");
		lblToDoThat.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblToDoThat.setBounds(229, 60, 691, 25);
		panel_2.add(lblToDoThat);
		
		JLabel lblWithACommunity = new JLabel("With a community of more than 2 billion people all around the world,");
		lblWithACommunity.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblWithACommunity.setBounds(660, 111, 403, 25);
		panel_2.add(lblWithACommunity);
		
		JLabel lblSocialAndCultural = new JLabel("social and cultural norms, it is just not clear to me that us sitting in an office here in California are best");
		lblSocialAndCultural.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSocialAndCultural.setBounds(471, 137, 592, 25);
		panel_2.add(lblSocialAndCultural);
		
		JLabel lblHowCanYou = new JLabel("How can you set up a ");
		lblHowCanYou.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblHowCanYou.setBounds(930, 165, 133, 25);
		panel_2.add(lblHowCanYou);
		
		JLabel lblItemNo = new JLabel("Item no 13 of 15");
		lblItemNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemNo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblItemNo.setBounds(148, 581, 118, 24);
		frame.getContentPane().add(lblItemNo);
		
		JButton button_2 = new JButton("<html><u>N</u>ext</html>");
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
				Set1reading14 go=new Set1reading14();
				go.frame.setVisible(true);
			}
		});
		button_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_2.setBounds(1132, 580, 89, 28);
		frame.getContentPane().add(button_2);
		
		JLabel label_2 = new JLabel("\u00A9Lab Symbiotic");
		label_2.setVerticalAlignment(SwingConstants.TOP);
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(Color.DARK_GRAY);
		label_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		label_2.setBounds(0, 704, 1370, 24);
		frame.getContentPane().add(label_2);
	}
	
	public void fileSaver() throws IOException, ClassNotFoundException{
		File answerFolder = new File("Answer");
		if(answerFolder.exists())
		{
			File answer=new File("Answer//ReadingAns.ser");
			if(answer.exists())
			{
				FileInputStream fis=new FileInputStream(answer);
				ObjectInputStream ois=new ObjectInputStream(fis);
				ReadingAnswer ra=(ReadingAnswer)ois.readObject();
				if(comboBox1.getSelectedIndex()==-1)
				{
					ra.fillBlankCombo3.add("");
				}
				else
				{
					ra.fillBlankCombo3.add(comboBox1.getSelectedItem().toString());
				}
				
				if(comboBox2.getSelectedIndex()==-1)
				{
					ra.fillBlankCombo3.add("");
				}
				else
				{
					ra.fillBlankCombo3.add(comboBox2.getSelectedItem().toString());
				}
				if(comboBox3.getSelectedIndex()==-1)
				{
					ra.fillBlankCombo3.add("");
				}
				else
				{
					ra.fillBlankCombo3.add(comboBox3.getSelectedItem().toString());
					
				}
				if(comboBox4.getSelectedIndex()==-1)
				{
					ra.fillBlankCombo3.add("");
				}
				else
				{
					ra.fillBlankCombo3.add(comboBox4.getSelectedItem().toString());
				}
				if(comboBox5.getSelectedIndex()==-1)
				{
					ra.fillBlankCombo3.add("");
				}
				else
				{
					ra.fillBlankCombo3.add(comboBox5.getSelectedItem().toString());
				}
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
			if(comboBox1.getSelectedIndex()==-1)
			{
				ra.fillBlankCombo3.add("");
			}
			else
			{
				ra.fillBlankCombo3.add(comboBox1.getSelectedItem().toString());
			}
			
			if(comboBox2.getSelectedIndex()==-1)
			{
				ra.fillBlankCombo3.add("");
			}
			else
			{
				ra.fillBlankCombo3.add(comboBox2.getSelectedItem().toString());
			}
			if(comboBox3.getSelectedIndex()==-1)
			{
				ra.fillBlankCombo3.add("");
			}
			else
			{
				ra.fillBlankCombo3.add(comboBox3.getSelectedItem().toString());
				
			}
			if(comboBox4.getSelectedIndex()==-1)
			{
				ra.fillBlankCombo3.add("");
			}
			else
			{
				ra.fillBlankCombo3.add(comboBox4.getSelectedItem().toString());
			}
			if(comboBox5.getSelectedIndex()==-1)
			{
				ra.fillBlankCombo3.add("");
			}
			else
			{
				ra.fillBlankCombo3.add(comboBox5.getSelectedItem().toString());
			}
			FileOutputStream fos=new FileOutputStream(answer,true);
			ObjectOutputStream oos=new ObjectOutputStream(new BufferedOutputStream (fos));
			oos.writeObject(ra);
			oos.close();
			fos.close();
		}
	}
}
