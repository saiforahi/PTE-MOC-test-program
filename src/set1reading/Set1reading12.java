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

public class Set1reading12 {

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
					Set1reading12 window = new Set1reading12();
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
	public Set1reading12() {
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
		label_1.setBounds(148, 113, 1078, 33);
		frame.getContentPane().add(label_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(3, 0, 3, 0, (Color) new Color(0, 51, 102)));
		panel_2.setLayout(null);
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(148, 278, 1078, 157);
		frame.getContentPane().add(panel_2);
		
		JLabel lblTheEiffelTower = new JLabel("Behind Facebook\u2019s hard year is a");
		lblTheEiffelTower.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTheEiffelTower.setBounds(10, 25, 193, 25);
		panel_2.add(lblTheEiffelTower);
		
		JLabel lblThatIronCould = new JLabel("between the company\u2019s values, ambitions, business model, and mind-boggling scale. Mark Zuckerberg, the founder of");
		lblThatIronCould.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblThatIronCould.setBounds(380, 25, 688, 25);
		panel_2.add(lblThatIronCould);
		
		comboBox1 = new JComboBox<String>();
		comboBox1.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		comboBox1.setModel(new DefaultComboBoxModel<String>(new String[] {"corruption", "collision", "collaboration", "coalition"}));
		comboBox1.setSelectedIndex(-1);
		comboBox1.setOpaque(true);
		comboBox1.setBackground(Color.WHITE);
		comboBox1.setBounds(213, 28, 157, 21);
		panel_2.add(comboBox1);
		
		JLabel lblStoneWhileBeing = new JLabel("Facebook, has long held that the company\u2019s");
		lblStoneWhileBeing.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblStoneWhileBeing.setBounds(10, 51, 250, 25);
		panel_2.add(lblStoneWhileBeing);
		
		JLabel lblYearsWith = new JLabel("world is a");
		lblYearsWith.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblYearsWith.setBounds(10, 77, 56, 25);
		panel_2.add(lblYearsWith);
		
		comboBox2 = new JComboBox<String>();
		comboBox2.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		comboBox2.setModel(new DefaultComboBoxModel<String>(new String[] {"loss", "opportunity", "mistake", "mission"}));
		comboBox2.setSelectedIndex(-1);
		comboBox2.setOpaque(true);
		comboBox2.setBackground(Color.WHITE);
		comboBox2.setBounds(270, 54, 157, 21);
		panel_2.add(comboBox2);
		
		JLabel lblCalledtheFather = new JLabel("easier for governments to undermine each others\u2019 elections from afar; a more connected world can make it easier to spread");
		lblCalledtheFather.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCalledtheFather.setBounds(10, 107, 719, 25);
		panel_2.add(lblCalledtheFather);
		
		comboBox3 = new JComboBox<String>();
		comboBox3.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		comboBox3.setModel(new DefaultComboBoxModel<String>(new String[] {"happiness", "better", "worst", "best"}));
		comboBox3.setSelectedIndex(-1);
		comboBox3.setOpaque(true);
		comboBox3.setBackground(Color.WHITE);
		comboBox3.setBounds(76, 80, 157, 21);
		panel_2.add(comboBox3);
		
		comboBox4 = new JComboBox<String>();
		comboBox4.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		comboBox4.setModel(new DefaultComboBoxModel<String>(new String[] {"coming", "this", "past", "passed"}));
		comboBox4.setSelectedIndex(-1);
		comboBox4.setOpaque(true);
		comboBox4.setBackground(Color.WHITE);
		comboBox4.setBounds(571, 80, 157, 21);
		panel_2.add(comboBox4);
		
		comboBox5 = new JComboBox<String>();
		comboBox5.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		comboBox5.setModel(new DefaultComboBoxModel<String>(new String[] {"openness", "hatred", "love", "affection"}));
		comboBox5.setSelectedIndex(-1);
		comboBox5.setOpaque(true);
		comboBox5.setBackground(Color.WHITE);
		comboBox5.setBounds(734, 110, 157, 21);
		panel_2.add(comboBox5);
		
		JLabel lblIsToMake = new JLabel("is to make the world more open and connected \u2014 with the assumption being that a more open and connected");
		lblIsToMake.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblIsToMake.setBounds(437, 51, 631, 25);
		panel_2.add(lblIsToMake);
		
		JLabel lblWorldThatAssumption = new JLabel("world. That assumption has been sorely tested over the");
		lblWorldThatAssumption.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblWorldThatAssumption.setBounds(243, 77, 319, 25);
		panel_2.add(lblWorldThatAssumption);
		
		JLabel lblYearAsWeve = new JLabel("year. As we\u2019ve seen, a more open world can make it ");
		lblYearAsWeve.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblYearAsWeve.setBounds(738, 77, 319, 25);
		panel_2.add(lblYearAsWeve);
		
		JLabel lblAndInciteViolence = new JLabel("and incite violence.");
		lblAndInciteViolence.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAndInciteViolence.setBounds(899, 107, 169, 25);
		panel_2.add(lblAndInciteViolence);
		
		JLabel lblItemNo = new JLabel("Item no 12 of 15");
		lblItemNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemNo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblItemNo.setBounds(148, 546, 118, 24);
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
				Set1reading13 go= new Set1reading13();
				go.frame.setVisible(true);
			}
		});
		button_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_2.setBounds(1137, 545, 89, 28);
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
					ra.fillBlankCombo2.add("");
				}
				else
				{
					ra.fillBlankCombo2.add(comboBox1.getSelectedItem().toString());
				}
				
				if(comboBox2.getSelectedIndex()==-1)
				{
					ra.fillBlankCombo2.add("");
				}
				else
				{
					ra.fillBlankCombo2.add(comboBox2.getSelectedItem().toString());
				}
				if(comboBox3.getSelectedIndex()==-1)
				{
					ra.fillBlankCombo2.add("");
				}
				else
				{
					ra.fillBlankCombo2.add(comboBox3.getSelectedItem().toString());
					
				}
				if(comboBox4.getSelectedIndex()==-1)
				{
					ra.fillBlankCombo2.add("");
				}
				else
				{
					ra.fillBlankCombo2.add(comboBox4.getSelectedItem().toString());
				}
				if(comboBox5.getSelectedIndex()==-1)
				{
					ra.fillBlankCombo2.add("");
				}
				else
				{
					ra.fillBlankCombo2.add(comboBox5.getSelectedItem().toString());
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
				ra.fillBlankCombo2.add("");
			}
			else
			{
				ra.fillBlankCombo2.add(comboBox1.getSelectedItem().toString());
			}
			
			if(comboBox2.getSelectedIndex()==-1)
			{
				ra.fillBlankCombo2.add("");
			}
			else
			{
				ra.fillBlankCombo2.add(comboBox2.getSelectedItem().toString());
			}
			if(comboBox3.getSelectedIndex()==-1)
			{
				ra.fillBlankCombo2.add("");
			}
			else
			{
				ra.fillBlankCombo2.add(comboBox3.getSelectedItem().toString());
				
			}
			if(comboBox4.getSelectedIndex()==-1)
			{
				ra.fillBlankCombo2.add("");
			}
			else
			{
				ra.fillBlankCombo2.add(comboBox4.getSelectedItem().toString());
			}
			if(comboBox5.getSelectedIndex()==-1)
			{
				ra.fillBlankCombo2.add("");
			}
			else
			{
				ra.fillBlankCombo2.add(comboBox5.getSelectedItem().toString());
			}
			FileOutputStream fos=new FileOutputStream(answer,true);
			ObjectOutputStream oos=new ObjectOutputStream(new BufferedOutputStream (fos));
			oos.writeObject(ra);
			oos.close();
			fos.close();
		}
	}

}
