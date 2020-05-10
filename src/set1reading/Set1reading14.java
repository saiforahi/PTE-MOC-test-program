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

public class Set1reading14 {

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
					Set1reading14 window = new Set1reading14();
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
	public Set1reading14() {
		initialize();
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
		frame.getContentPane().setPreferredSize(new Dimension(screenSize.width,screenSize.height));
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
		label_1.setBounds(148, 113, 1076, 33);
		frame.getContentPane().add(label_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new MatteBorder(3, 0, 3, 0, (Color) new Color(0, 51, 102)));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(148, 249, 1076, 260);
		frame.getContentPane().add(panel_2);
		
		JLabel lblIfAfterYears = new JLabel("Here are a few of the principles. One is transparency. Right now, I don\u2019t think we are");
		lblIfAfterYears.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblIfAfterYears.setBounds(10, 39, 487, 25);
		panel_2.add(lblIfAfterYears);
		
		comboBox1 = new JComboBox();
		comboBox1.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		comboBox1.setModel(new DefaultComboBoxModel(new String[] {"transparency", "transparent", "transparence", "translucent"}));
		comboBox1.setSelectedIndex(-1);
		comboBox1.setOpaque(true);
		comboBox1.setBackground(Color.WHITE);
		comboBox1.setBounds(507, 41, 157, 21);
		panel_2.add(comboBox1);
		
		JLabel lblMeanTheLessons = new JLabel("We haven\u2019t ");
		lblMeanTheLessons.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMeanTheLessons.setBounds(10, 65, 68, 25);
		panel_2.add(lblMeanTheLessons);
		
		JLabel lblAreasOfLatin = new JLabel("A second is some sort of independent appeal ");
		lblAreasOfLatin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAreasOfLatin.setBounds(10, 146, 261, 25);
		panel_2.add(lblAreasOfLatin);
		
		JLabel lblTheConfusionIs = new JLabel("how we\u2019re driving those things down over time. ");
		lblTheConfusionIs.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTheConfusionIs.setBounds(10, 93, 1031, 25);
		panel_2.add(lblTheConfusionIs);
		
		JLabel lblBoliviaGenerallySpeak = new JLabel("operations and review team looks at it and decides that it needs to get taken down, there\u2019s not really a way to appeal that. I think in any kind of good-functioning democratic system,");
		lblBoliviaGenerallySpeak.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBoliviaGenerallySpeak.setBounds(10, 172, 1056, 25);
		panel_2.add(lblBoliviaGenerallySpeak);
		
		comboBox5 = new JComboBox();
		comboBox5.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		comboBox5.setModel(new DefaultComboBoxModel(new String[] {"start", "talk", "call", "step"}));
		comboBox5.setSelectedIndex(-1);
		comboBox5.setOpaque(true);
		comboBox5.setBackground(Color.WHITE);
		comboBox5.setBounds(507, 201, 157, 21);
		panel_2.add(comboBox5);
		
		comboBox2 = new JComboBox();
		comboBox2.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		comboBox2.setModel(new DefaultComboBoxModel(new String[] {"do", "did", "doing", "done"}));
		comboBox2.setSelectedIndex(-1);
		comboBox2.setOpaque(true);
		comboBox2.setBackground(Color.WHITE);
		comboBox2.setBounds(88, 68, 139, 21);
		panel_2.add(comboBox2);
		
		comboBox3 = new JComboBox();
		comboBox3.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		comboBox3.setModel(new DefaultComboBoxModel(new String[] {"process", "request", "wish", "problem"}));
		comboBox3.setSelectedIndex(-1);
		comboBox3.setOpaque(true);
		comboBox3.setBackground(Color.WHITE);
		comboBox3.setBounds(281, 149, 139, 21);
		panel_2.add(comboBox3);
		
		JLabel lblAndHasSome = new JLabel("Right now, if you post something on Facebook and someone");
		lblAndHasSome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAndHasSome.setBounds(430, 146, 344, 25);
		panel_2.add(lblAndHasSome);
		
		comboBox4 = new JComboBox();
		comboBox4.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		comboBox4.setModel(new DefaultComboBoxModel(new String[] {"likes", "complains", "comments", "reports"}));
		comboBox4.setSelectedIndex(-1);
		comboBox4.setOpaque(true);
		comboBox4.setBackground(Color.WHITE);
		comboBox4.setBounds(790, 149, 139, 21);
		panel_2.add(comboBox4);
		
		JLabel lblThisSpanishWas = new JLabel("there needs to be a way to appeal. And I think we can build that internally as a first ");
		lblThisSpanishWas.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblThisSpanishWas.setBounds(10, 198, 487, 25);
		panel_2.add(lblThisSpanishWas);
		
		JLabel lblEnoughAroundThe = new JLabel("enough around the prevalence of different issues on the platform.");
		lblEnoughAroundThe.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEnoughAroundThe.setBounds(674, 39, 392, 25);
		panel_2.add(lblEnoughAroundThe);
		
		JLabel lblAGoodJob = new JLabel("a good job of publishing and being transparent about the prevalence of those kinds of issues, and the work that we\u2019re doing and the trends of ");
		lblAGoodJob.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAGoodJob.setBounds(237, 65, 829, 25);
		panel_2.add(lblAGoodJob);
		
		JLabel lblItAndOur = new JLabel("it and our community");
		lblItAndOur.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblItAndOur.setBounds(939, 146, 127, 25);
		panel_2.add(lblItAndOur);
		
		JLabel lblItemNo = new JLabel("Item no 10 of 15");
		lblItemNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemNo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblItemNo.setBounds(148, 596, 118, 24);
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
				Set1reading15 go= new Set1reading15();
				go.frame.setVisible(true);
				
			}
		});
		button_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_2.setBounds(1135, 595, 89, 28);
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
					ra.fillBlankCombo4.add("");
				}
				else
				{
					ra.fillBlankCombo4.add(comboBox1.getSelectedItem().toString());
				}
				
				if(comboBox2.getSelectedIndex()==-1)
				{
					ra.fillBlankCombo4.add("");
				}
				else
				{
					ra.fillBlankCombo4.add(comboBox2.getSelectedItem().toString());
				}
				if(comboBox3.getSelectedIndex()==-1)
				{
					ra.fillBlankCombo4.add("");
				}
				else
				{
					ra.fillBlankCombo4.add(comboBox3.getSelectedItem().toString());
					
				}
				if(comboBox4.getSelectedIndex()==-1)
				{
					ra.fillBlankCombo4.add("");
				}
				else
				{
					ra.fillBlankCombo4.add(comboBox4.getSelectedItem().toString());
				}
				if(comboBox5.getSelectedIndex()==-1)
				{
					ra.fillBlankCombo4.add("");
				}
				else
				{
					ra.fillBlankCombo4.add(comboBox5.getSelectedItem().toString());
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
				ra.fillBlankCombo4.add("");
			}
			else
			{
				ra.fillBlankCombo4.add(comboBox1.getSelectedItem().toString());
			}
			
			if(comboBox2.getSelectedIndex()==-1)
			{
				ra.fillBlankCombo4.add("");
			}
			else
			{
				ra.fillBlankCombo4.add(comboBox2.getSelectedItem().toString());
			}
			if(comboBox3.getSelectedIndex()==-1)
			{
				ra.fillBlankCombo4.add("");
			}
			else
			{
				ra.fillBlankCombo4.add(comboBox3.getSelectedItem().toString());
				
			}
			if(comboBox4.getSelectedIndex()==-1)
			{
				ra.fillBlankCombo4.add("");
			}
			else
			{
				ra.fillBlankCombo4.add(comboBox4.getSelectedItem().toString());
			}
			if(comboBox5.getSelectedIndex()==-1)
			{
				ra.fillBlankCombo4.add("");
			}
			else
			{
				ra.fillBlankCombo4.add(comboBox5.getSelectedItem().toString());
			}
			FileOutputStream fos=new FileOutputStream(answer,true);
			ObjectOutputStream oos=new ObjectOutputStream(new BufferedOutputStream (fos));
			oos.writeObject(ra);
			oos.close();
			fos.close();
		}
	}
}
