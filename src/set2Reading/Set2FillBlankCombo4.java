package set2Reading;

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

public class Set2FillBlankCombo4 {

	public JFrame frame;
	@SuppressWarnings("rawtypes")
	JComboBox comboBox;
	@SuppressWarnings("rawtypes")
	JComboBox comboBox_1;
	@SuppressWarnings("rawtypes")
	JComboBox comboBox_2;
	@SuppressWarnings("rawtypes")
	JComboBox comboBox_3;
	@SuppressWarnings("rawtypes")
	JComboBox comboBox_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Set2FillBlankCombo4 window = new Set2FillBlankCombo4();
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
	public Set2FillBlankCombo4() {
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
		frame.setSize(new Dimension(screenSize.width+4, 730));
		frame.getContentPane().setPreferredSize(new Dimension(screenSize.width+4, 730));
		frame.setAlwaysOnTop(true);
		frame.setResizable(false);
		frame.setLocation(-2, -1);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1370, 56);
		frame.getContentPane().add(panel);
		
		JButton button = new JButton("Submit");
		button.setForeground(Color.DARK_GRAY);
		button.setFont(new Font("Tahoma", Font.BOLD, 12));
		button.setEnabled(false);
		button.setBackground(Color.WHITE);
		button.setBounds(1203, 14, 134, 30);
		panel.add(button);
		
		JLabel label = new JLabel("Reading Items (Fill in the blanks)");
		label.setFont(new Font("Tahoma", Font.BOLD, 24));
		label.setBounds(10, 0, 497, 56);
		panel.add(label);
		
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
		panel_2.setBorder(new MatteBorder(2, 0, 2, 0, (Color) Color.GRAY));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(148, 260, 1076, 260);
		frame.getContentPane().add(panel_2);
		
		JLabel lblIfAfterYears = new JLabel("We\u2019re continually thinking through this. As the internet gets to a broader");
		lblIfAfterYears.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblIfAfterYears.setBounds(10, 39, 413, 25);
		panel_2.add(lblIfAfterYears);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 15));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"thinking", "thing", "scale", "way"}));
		comboBox.setSelectedIndex(-1);
		comboBox.setOpaque(true);
		comboBox.setBackground(Color.WHITE);
		comboBox.setBounds(433, 40, 157, 22);
		panel_2.add(comboBox);
		
		JLabel lblAreasOfLatin = new JLabel("You");
		lblAreasOfLatin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAreasOfLatin.setBounds(10, 146, 21, 25);
		panel_2.add(lblAreasOfLatin);
		
		JLabel lblTheConfusionIs = new JLabel("responsively and make sure that we can address it so that those kinds of issues don\u2019t come up again in the future?\u201D");
		lblTheConfusionIs.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTheConfusionIs.setBounds(10, 93, 670, 25);
		panel_2.add(lblTheConfusionIs);
		
		JLabel lblBoliviaGenerallySpeak = new JLabel("it\u2019s a controlled company. We are not at the");
		lblBoliviaGenerallySpeak.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBoliviaGenerallySpeak.setBounds(10, 172, 256, 25);
		panel_2.add(lblBoliviaGenerallySpeak);
		
		comboBox_4 = new JComboBox();
		comboBox_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] {"rely", "whims", "dependant", "ask"}));
		comboBox_4.setSelectedIndex(-1);
		comboBox_4.setOpaque(true);
		comboBox_4.setBackground(Color.WHITE);
		comboBox_4.setBounds(276, 173, 157, 22);
		panel_2.add(comboBox_4);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"all day", "constantly", "every day", "all time"}));
		comboBox_1.setSelectedIndex(-1);
		comboBox_1.setOpaque(true);
		comboBox_1.setBackground(Color.WHITE);
		comboBox_1.setBounds(10, 68, 139, 22);
		panel_2.add(comboBox_1);
		
		comboBox_2 = new JComboBox();
		comboBox_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"said", "told", "talked", "mentioned"}));
		comboBox_2.setSelectedIndex(-1);
		comboBox_2.setOpaque(true);
		comboBox_2.setBackground(Color.WHITE);
		comboBox_2.setBounds(41, 147, 139, 22);
		panel_2.add(comboBox_2);
		
		JLabel lblAndHasSome = new JLabel("our governance. One of the things that I feel really lucky we have is this company structure where, at the");
		lblAndHasSome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAndHasSome.setBounds(185, 146, 607, 25);
		panel_2.add(lblAndHasSome);
		
		comboBox_3 = new JComboBox();
		comboBox_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"beginning", "final", "end", "start"}));
		comboBox_3.setSelectedIndex(-1);
		comboBox_3.setOpaque(true);
		comboBox_3.setBackground(Color.WHITE);
		comboBox_3.setBounds(796, 147, 139, 22);
		panel_2.add(comboBox_3);
		
		JLabel lblThisSpanishWas = new JLabel("best interest of the community over time.");
		lblThisSpanishWas.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblThisSpanishWas.setBounds(10, 198, 487, 25);
		panel_2.add(lblThisSpanishWas);
		
		JLabel lblEnoughAroundThe = new JLabel("and some of these services reach a bigger scale than anything has before, we\u2019re ");
		lblEnoughAroundThe.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEnoughAroundThe.setBounds(600, 39, 466, 25);
		panel_2.add(lblEnoughAroundThe);
		
		JLabel lblAGoodJob = new JLabel("confronted with new challenges. I try to judge our success not by, \u201CAre there no problems that come up?\u201D But, \u201CWhen an issue comes up, can we deal with it");
		lblAGoodJob.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAGoodJob.setBounds(159, 65, 907, 25);
		panel_2.add(lblAGoodJob);
		
		JLabel lblOfTheDay = new JLabel("of the day,");
		lblOfTheDay.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblOfTheDay.setBounds(945, 146, 121, 25);
		panel_2.add(lblOfTheDay);
		
		JLabel lblOfShorttermShareholders = new JLabel("of short-term shareholders. We can really design these products and decisions with what is going to be in the");
		lblOfShorttermShareholders.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblOfShorttermShareholders.setBounds(442, 172, 634, 25);
		panel_2.add(lblOfShorttermShareholders);
		
		JLabel lblItemNo = new JLabel("Item no 14 of 15");
		lblItemNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemNo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblItemNo.setBounds(135, 640, 118, 24);
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
				Set2FillBlankCombo5 go= new Set2FillBlankCombo5();
				go.frame.setVisible(true);
				
			}
		});
		button_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_2.setBounds(1176, 639, 89, 28);
		frame.getContentPane().add(button_2);
		
		JLabel label_17 = new JLabel("Lab Symbiotic");
		label_17.setHorizontalAlignment(SwingConstants.TRAILING);
		label_17.setForeground(Color.DARK_GRAY);
		label_17.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_17.setBounds(1204, 678, 156, 20);
		frame.getContentPane().add(label_17);
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
				/*if(radiobtn3.isSelected())
				{
					la.highlightCorrectSummary=radiobtn3.getText();
				}*/
				ra.fillBlankCombo4.add(comboBox.getSelectedItem().toString());
				ra.fillBlankCombo4.add(comboBox_1.getSelectedItem().toString());
				ra.fillBlankCombo4.add(comboBox_2.getSelectedItem().toString());
				ra.fillBlankCombo4.add(comboBox_3.getSelectedItem().toString());
				ra.fillBlankCombo4.add(comboBox_4.getSelectedItem().toString());
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
			/*if(radiobtn3.isSelected())
			{
				la.highlightCorrectSummary=radiobtn3.getText();
			}*/
			ra.fillBlankCombo4.add(comboBox.getSelectedItem().toString());
			ra.fillBlankCombo4.add(comboBox_1.getSelectedItem().toString());
			ra.fillBlankCombo4.add(comboBox_2.getSelectedItem().toString());
			ra.fillBlankCombo4.add(comboBox_3.getSelectedItem().toString());
			ra.fillBlankCombo4.add(comboBox_4.getSelectedItem().toString());
			FileOutputStream fos=new FileOutputStream(answer,true);
			ObjectOutputStream oos=new ObjectOutputStream(new BufferedOutputStream (fos));
			oos.writeObject(ra);
			oos.close();
			fos.close();
		}
	}
}
