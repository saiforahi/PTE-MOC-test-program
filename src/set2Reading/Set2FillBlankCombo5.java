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
import set2Listening.Set2SummarySpokenTxt1;

public class Set2FillBlankCombo5 {

	public JFrame frame;
	@SuppressWarnings("rawtypes")
	JComboBox comboBox1,comboBox2,comboBox3,comboBox4,comboBox5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Set2FillBlankCombo5 window = new Set2FillBlankCombo5();
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
	public Set2FillBlankCombo5() {
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
		frame.setSize(new Dimension(screenSize.width, screenSize.height));
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
		label_1.setBounds(135, 113, 1051, 33);
		frame.getContentPane().add(label_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new MatteBorder(2, 0, 2, 0, (Color) Color.GRAY));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(135, 243, 1051, 300);
		frame.getContentPane().add(panel_2);
		
		JLabel lblItsTrueThat = new JLabel("There\u2019s an extremely creepy \u201Cghost radio station\u201D broadcasting out of somewhere in Russia (once again) ever");
		lblItsTrueThat.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblItsTrueThat.setBounds(10, 39, 634, 25);
		panel_2.add(lblItsTrueThat);
		
		 comboBox1 = new JComboBox();
		comboBox1.setModel(new DefaultComboBoxModel(new String[] {"while", "all", "time", "since"}));
		comboBox1.setSelectedIndex(-1);
		comboBox1.setOpaque(true);
		comboBox1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		comboBox1.setBackground(Color.WHITE);
		comboBox1.setBounds(645, 41, 157, 22);
		panel_2.add(comboBox1);
		
		JLabel lblAccidentsAndPhotographs = new JLabel("actually increased since the fall of the Cold War.");
		lblAccidentsAndPhotographs.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAccidentsAndPhotographs.setBounds(10, 71, 276, 25);
		panel_2.add(lblAccidentsAndPhotographs);
		
		 comboBox2 = new JComboBox();
		comboBox2.setModel(new DefaultComboBoxModel(new String[] {"combined", "consists", "make", "made"}));
		comboBox2.setSelectedIndex(-1);
		comboBox2.setOpaque(true);
		comboBox2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		comboBox2.setBackground(Color.WHITE);
		comboBox2.setBounds(584, 127, 157, 22);
		panel_2.add(comboBox2);
		
		JLabel lblRadiationAndThe = new JLabel("UVB-76, aka \u201CThe Buzzer\u201D, has occupied 4625 kHz frequency ever since the early 1970s. Its content");
		lblRadiationAndThe.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblRadiationAndThe.setBounds(10, 126, 568, 25);
		panel_2.add(lblRadiationAndThe);
		
		JLabel lblAssociationsAndThose = new JLabel("with the occasional message spoken in Russian or some kind of unknown code. It also faintly broadcast a song from Swan Lake in 2010.");
		lblAssociationsAndThose.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAssociationsAndThose.setBounds(10, 157, 779, 25);
		panel_2.add(lblAssociationsAndThose);
		
		 comboBox3 = new JComboBox();
		comboBox3.setModel(new DefaultComboBoxModel(new String[] {"imagine", "think", "wish", "decide"}));
		comboBox3.setSelectedIndex(-1);
		comboBox3.setOpaque(true);
		comboBox3.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		comboBox3.setBackground(Color.WHITE);
		comboBox3.setBounds(869, 158, 157, 22);
		panel_2.add(comboBox3);
		
		JLabel lblButIt = new JLabel("As you can ");
		lblButIt.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblButIt.setBounds(799, 157, 66, 25);
		panel_2.add(lblButIt);
		
		 comboBox4 = new JComboBox();
		comboBox4.setModel(new DefaultComboBoxModel(new String[] {"requested", "serious", "likely", "talked"}));
		comboBox4.setSelectedIndex(-1);
		comboBox4.setOpaque(true);
		comboBox4.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		comboBox4.setBackground(Color.WHITE);
		comboBox4.setBounds(632, 193, 157, 22);
		panel_2.add(comboBox4);
		
		JLabel lblApplyHere = new JLabel(", you can find a bunch of theories about the buzzer, especially among Internet discussion boards. The most");
		lblApplyHere.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblApplyHere.setBounds(10, 190, 613, 25);
		panel_2.add(lblApplyHere);
		
		 comboBox5 = new JComboBox();
		comboBox5.setModel(new DefaultComboBoxModel(new String[] {"agreed", "talked", "confirmed", "told"}));
		comboBox5.setSelectedIndex(-1);
		comboBox5.setOpaque(true);
		comboBox5.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		comboBox5.setBackground(Color.WHITE);
		comboBox5.setBounds(565, 227, 157, 22);
		panel_2.add(comboBox5);
		
		JLabel lblTheHorrificAftermath = new JLabel("the Cold War, although its activity has ");
		lblTheHorrificAftermath.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTheHorrificAftermath.setBounds(812, 39, 229, 25);
		panel_2.add(lblTheHorrificAftermath);
		
		JLabel lblItWithNuclear = new JLabel("of a strange repeated buzzing noise, interjected ");
		lblItWithNuclear.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblItWithNuclear.setBounds(751, 126, 290, 25);
		panel_2.add(lblItWithNuclear);
		
		JLabel lblExplanationIsThat = new JLabel("explanation is that it\u2019s used by the ");
		lblExplanationIsThat.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblExplanationIsThat.setBounds(799, 190, 227, 25);
		panel_2.add(lblExplanationIsThat);
		
		JLabel lblRussianGovernmentTo = new JLabel("Russian government to instruct spies all over the world. The government, of course, have never");
		lblRussianGovernmentTo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblRussianGovernmentTo.setBounds(10, 226, 548, 25);
		panel_2.add(lblRussianGovernmentTo);
		
		JLabel lblThis = new JLabel("this.");
		lblThis.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblThis.setBounds(732, 224, 227, 25);
		panel_2.add(lblThis);
		
		JLabel lblItemNo = new JLabel("Item no 15 of 15");
		lblItemNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemNo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblItemNo.setBounds(135, 640, 118, 24);
		frame.getContentPane().add(lblItemNo);
		
		JButton button_1 = new JButton("<html><u>N</u>ext</html>");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] options = new String[2];
				options[0] = new String("yes");
				options[1] = new String("not now");
				int choice=JOptionPane.showOptionDialog(frame.getContentPane(),"Listening part is going to start.....","Confirmation to proceed", JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,null,options,null);
				if(choice==0)
				{
					
					try {
						fileSaver();
					} catch (ClassNotFoundException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					frame.dispose();
					Set2SummarySpokenTxt1 go=new Set2SummarySpokenTxt1();
					go.frame.setVisible(true);
				}
			}
		});
		button_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_1.setBackground(Color.WHITE);
		button_1.setBounds(1176, 639, 89, 28);
		frame.getContentPane().add(button_1);
		
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
				ra.fillBlankCombo5.add(comboBox1.getSelectedItem().toString());
				ra.fillBlankCombo5.add(comboBox2.getSelectedItem().toString());
				ra.fillBlankCombo5.add(comboBox3.getSelectedItem().toString());
				ra.fillBlankCombo5.add(comboBox4.getSelectedItem().toString());
				ra.fillBlankCombo5.add(comboBox5.getSelectedItem().toString());
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
			ra.fillBlankCombo5.add(comboBox1.getSelectedItem().toString());
			ra.fillBlankCombo5.add(comboBox2.getSelectedItem().toString());
			ra.fillBlankCombo5.add(comboBox3.getSelectedItem().toString());
			ra.fillBlankCombo5.add(comboBox4.getSelectedItem().toString());
			ra.fillBlankCombo5.add(comboBox5.getSelectedItem().toString());
			FileOutputStream fos=new FileOutputStream(answer,true);
			ObjectOutputStream oos=new ObjectOutputStream(new BufferedOutputStream (fos));
			oos.writeObject(ra);
			oos.close();
			fos.close();
		}
	}

}
