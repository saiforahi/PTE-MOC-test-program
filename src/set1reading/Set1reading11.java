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

public class Set1reading11 {

	public JFrame frame;
	@SuppressWarnings("rawtypes")
	JComboBox comboBox1;
	@SuppressWarnings("rawtypes")
	JComboBox comboBox2;
	@SuppressWarnings("rawtypes")
	JComboBox comboBox3;
	@SuppressWarnings("rawtypes")
	JComboBox comboBox4;
	@SuppressWarnings("rawtypes")
	JComboBox comboBox5;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Set1reading11 window = new Set1reading11();
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
	public Set1reading11() {
		initialize();
		frame.setIconImage(Functions.setIcon());
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
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
		frame.setSize(new Dimension(screenSize.width,screenSize.height));
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
		
		JLabel lblbelowIsText = new JLabel("<html><p align='CENTER'>Below is a text with blanks. Click on each blank, a list of choices will appear. Select the appropriate answer choice for each blank.</p></html>");
		lblbelowIsText.setOpaque(true);
		lblbelowIsText.setHorizontalAlignment(SwingConstants.CENTER);
		lblbelowIsText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblbelowIsText.setBorder(new MatteBorder(1, 0, 1, 0, (Color) new Color(0, 0, 0)));
		lblbelowIsText.setBackground(new Color(240, 255, 240));
		lblbelowIsText.setBounds(135, 113, 1088, 33);
		frame.getContentPane().add(lblbelowIsText);
		
		JLabel lblItemNo = new JLabel("Item no 11 of 15");
		lblItemNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemNo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblItemNo.setBounds(135, 539, 118, 24);
		frame.getContentPane().add(lblItemNo);
		
		JButton button_2 = new JButton("<html><u>N</u>ext</html>");
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
				Set1reading12 go=new Set1reading12();
				go.frame.setVisible(true);
			}
		});
		button_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_2.setBounds(1134, 538, 89, 28);
		frame.getContentPane().add(button_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(3, 0, 3, 0, (Color) new Color(0, 51, 102)));
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(135, 241, 1088, 214);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblCalledChomolungmagoddess = new JLabel("The fact that a mathematician could");
		lblCalledChomolungmagoddess.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCalledChomolungmagoddess.setBounds(10, 11, 210, 25);
		panel_2.add(lblCalledChomolungmagoddess);
		
		JLabel lblAmongWesternersThat = new JLabel("than Villani. Nonetheless, Villani, 44,");
		lblAmongWesternersThat.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAmongWesternersThat.setBounds(10, 41, 210, 25);
		panel_2.add(lblAmongWesternersThat);
		
		comboBox1 = new JComboBox();
		comboBox1.setForeground(Color.DARK_GRAY);
		comboBox1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		comboBox1.setModel(new DefaultComboBoxModel(new String[] {"is considers", "be considered", "considers", "considering"}));
		comboBox1.setBackground(new Color(255, 255, 255));
		comboBox1.setBounds(230, 14, 157, 21);
		comboBox1.setSelectedIndex(-1);
		comboBox1.setOpaque(true);
		panel_2.add(comboBox1);
		
		JLabel lblEstablishedThatIt = new JLabel("a darling of President Emmanuel Macron\u2019s young technocratic government, accompanying the president to ");
		lblEstablishedThatIt.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEstablishedThatIt.setBounds(397, 41, 670, 25);
		panel_2.add(lblEstablishedThatIt);
		
		JLabel lblSeemedLostIn = new JLabel("Ouagadougou, Burkina Faso, in November and Beijing in mid-January. The government has piled the work on his desk, which is evidence, Villani says, of the need for people with ");
		lblSeemedLostIn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSeemedLostIn.setBounds(10, 71, 1068, 25);
		panel_2.add(lblSeemedLostIn);
		
		comboBox2 = new JComboBox();
		comboBox2.setForeground(Color.DARK_GRAY);
		comboBox2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		comboBox2.setModel(new DefaultComboBoxModel(new String[] {"becomes", "is become", "has become", "becoming"}));
		comboBox2.setOpaque(true);
		comboBox2.setBackground(Color.WHITE);
		comboBox2.setSelectedIndex(-1);
		comboBox2.setBounds(230, 42, 157, 21);
		panel_2.add(comboBox2);
		
		JLabel lblInThe = new JLabel("scientific");
		lblInThe.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblInThe.setBounds(10, 100, 49, 25);
		panel_2.add(lblInThe);
		
		comboBox3 = new JComboBox();
		comboBox3.setForeground(Color.DARK_GRAY);
		comboBox3.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		comboBox3.setModel(new DefaultComboBoxModel(new String[] {"evidence", "experience", "exploration", "expertise"}));
		comboBox3.setOpaque(true);
		comboBox3.setSelectedIndex(-1);
		comboBox3.setBackground(Color.WHITE);
		comboBox3.setBounds(69, 103, 157, 21);
		panel_2.add(comboBox3);
		
		JLabel lblHeightForMore = new JLabel("in politics. But of all his projects \u2014 from math education to the future of New Caledonia to tax evasion \u2014 perhaps his most all-consuming ");
		lblHeightForMore.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblHeightForMore.setBounds(231, 100, 847, 25);
		panel_2.add(lblHeightForMore);
		
		JLabel lblTheMountainReceived = new JLabel("set the AI agenda in France and Europe for years to come.");
		lblTheMountainReceived.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTheMountainReceived.setBounds(10, 161, 1031, 25);
		panel_2.add(lblTheMountainReceived);
		
		comboBox4 = new JComboBox();
		comboBox4.setForeground(Color.DARK_GRAY);
		comboBox4.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		comboBox4.setModel(new DefaultComboBoxModel(new String[] {"hobby", "mission", "pastime", "trouble"}));
		comboBox4.setSelectedIndex(-1);
		comboBox4.setOpaque(true);
		comboBox4.setBackground(Color.WHITE);
		comboBox4.setSelectedIndex(-1);
		comboBox4.setBounds(10, 134, 157, 21);
		panel_2.add(comboBox4);
		
		comboBox5 = new JComboBox();
		comboBox5.setForeground(Color.DARK_GRAY);
		comboBox5.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		comboBox5.setModel(new DefaultComboBoxModel(new String[] {"will help", "has helped", "is helps", "is helping"}));
		comboBox5.setSelectedIndex(-1);
		comboBox5.setOpaque(true);
		comboBox5.setBackground(Color.WHITE);
		comboBox5.setSelectedIndex(-1);
		comboBox5.setBounds(910, 131, 157, 21);
		panel_2.add(comboBox5);
		
		JLabel lblAsHe = new JLabel(", as he is, a \u201Crock star\u201D \u2014 or, better yet, \u201Cthe Lady Gaga of mathematics\u201D \u2014 says perhaps more about the French ");
		lblAsHe.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAsHe.setBounds(397, 11, 681, 25);
		panel_2.add(lblAsHe);
		
		JLabel lblIsHisTask = new JLabel("is his task force on artificial intelligence and the highly anticipated report it\u2019s set to release tomorrow. If successful, the report");
		lblIsHisTask.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblIsHisTask.setBounds(177, 130, 718, 25);
		panel_2.add(lblIsHisTask);
		
		JLabel label_1 = new JLabel("\u00A9Lab Symbiotic");
		label_1.setVerticalAlignment(SwingConstants.TOP);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(Color.DARK_GRAY);
		label_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		label_1.setBounds(0, 704, 1370, 24);
		frame.getContentPane().add(label_1);
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
					ra.fillBlankCombo.add("");
				}
				else
				{
					ra.fillBlankCombo.add(comboBox1.getSelectedItem().toString());
				}
				
				if(comboBox2.getSelectedIndex()==-1)
				{
					ra.fillBlankCombo.add("");
				}
				else
				{
					ra.fillBlankCombo.add(comboBox2.getSelectedItem().toString());
				}
				if(comboBox3.getSelectedIndex()==-1)
				{
					ra.fillBlankCombo.add("");
				}
				else
				{
					ra.fillBlankCombo.add(comboBox3.getSelectedItem().toString());
					
				}
				if(comboBox4.getSelectedIndex()==-1)
				{
					ra.fillBlankCombo.add("");
				}
				else
				{
					ra.fillBlankCombo.add(comboBox4.getSelectedItem().toString());
				}
				if(comboBox5.getSelectedIndex()==-1)
				{
					ra.fillBlankCombo.add("");
				}
				else
				{
					ra.fillBlankCombo.add(comboBox5.getSelectedItem().toString());
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
				ra.fillBlankCombo.add("");
			}
			else
			{
				ra.fillBlankCombo.add(comboBox1.getSelectedItem().toString());
			}
			
			if(comboBox2.getSelectedIndex()==-1)
			{
				ra.fillBlankCombo.add("");
			}
			else
			{
				ra.fillBlankCombo.add(comboBox2.getSelectedItem().toString());
			}
			if(comboBox3.getSelectedIndex()==-1)
			{
				ra.fillBlankCombo.add("");
			}
			else
			{
				ra.fillBlankCombo.add(comboBox3.getSelectedItem().toString());
				
			}
			if(comboBox4.getSelectedIndex()==-1)
			{
				ra.fillBlankCombo.add("");
			}
			else
			{
				ra.fillBlankCombo.add(comboBox4.getSelectedItem().toString());
			}
			if(comboBox5.getSelectedIndex()==-1)
			{
				ra.fillBlankCombo.add("");
			}
			else
			{
				ra.fillBlankCombo.add(comboBox5.getSelectedItem().toString());
			}
			FileOutputStream fos=new FileOutputStream(answer,true);
			ObjectOutputStream oos=new ObjectOutputStream(new BufferedOutputStream (fos));
			oos.writeObject(ra);
			oos.close();
			fos.close();
		}
	}
}
