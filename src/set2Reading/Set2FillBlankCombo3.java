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


public class Set2FillBlankCombo3 {

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
					Set2FillBlankCombo3 window = new Set2FillBlankCombo3();
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
	public Set2FillBlankCombo3() {
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
		label_1.setBounds(148, 113, 1073, 33);
		frame.getContentPane().add(label_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new MatteBorder(2, 0, 2, 0, (Color) Color.GRAY));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(148, 273, 1073, 211);
		frame.getContentPane().add(panel_2);
		
		JLabel lblFoundedAfterWorld = new JLabel("I spoke with Zuckerberg on Friday about the ");
		lblFoundedAfterWorld.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFoundedAfterWorld.setBounds(10, 27, 256, 25);
		panel_2.add(lblFoundedAfterWorld);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"these kind", "this kinds", "those kinds", "that kinds"}));
		comboBox.setSelectedIndex(-1);
		comboBox.setOpaque(true);
		
		comboBox.setBackground(Color.WHITE);
		comboBox.setBounds(276, 28, 157, 22);
		panel_2.add(comboBox);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"way", "able", "cause", "order"}));
		comboBox_1.setSelectedIndex(-1);
		comboBox_1.setOpaque(true);
		comboBox_1.setBackground(Color.WHITE);
		comboBox_1.setBounds(577, 75, 157, 22);
		panel_2.add(comboBox_1);
		
		JLabel lblUnitedNationsDay = new JLabel("\u201CI think we will dig through this hole, but it will take a few years,\u201D Zuckerberg said. \u201CI wish I could ");
		lblUnitedNationsDay.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUnitedNationsDay.setBounds(10, 74, 561, 25);
		panel_2.add(lblUnitedNationsDay);
		
		JLabel lblOnOctober_1 = new JLabel("just think the reality is that solving some of these questions is just going to take a");
		lblOnOctober_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblOnOctober_1.setBounds(10, 104, 465, 25);
		panel_2.add(lblOnOctober_1);
		
		comboBox_2 = new JComboBox();
		comboBox_2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"are", "face", "fare", "do"}));
		comboBox_2.setSelectedIndex(-1);
		comboBox_2.setOpaque(true);
		comboBox_2.setBackground(Color.WHITE);
		comboBox_2.setBounds(485, 106, 157, 22);
		panel_2.add(comboBox_2);
		
		JLabel lblInPeacekeepingAnd = new JLabel("But what happens then? What has this past year");
		lblInPeacekeepingAnd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblInPeacekeepingAnd.setBounds(10, 128, 276, 25);
		panel_2.add(lblInPeacekeepingAnd);
		
		comboBox_3 = new JComboBox();
		comboBox_3.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"similar", "familiar", "different", "open"}));
		comboBox_3.setSelectedIndex(-1);
		comboBox_3.setOpaque(true);
		comboBox_3.setBackground(Color.WHITE);
		comboBox_3.setBounds(296, 129, 157, 22);
		panel_2.add(comboBox_3);
		
		JLabel lblforTheirWprk = new JLabel("take its");
		lblforTheirWprk.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblforTheirWprk.setBounds(10, 156, 48, 25);
		panel_2.add(lblforTheirWprk);
		
		comboBox_4 = new JComboBox();
		comboBox_4.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] {"through", "about", "of", "with"}));
		comboBox_4.setSelectedIndex(-1);
		comboBox_4.setOpaque(true);
		comboBox_4.setBackground(Color.WHITE);
		comboBox_4.setBounds(59, 157, 157, 22);
		panel_2.add(comboBox_4);
		
		JLabel lblOfDisputesBetween = new JLabel("of his company, the implications of its global influence, and how he sees the problems ahead of him.");
		lblOfDisputesBetween.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblOfDisputesBetween.setBounds(443, 27, 575, 25);
		panel_2.add(lblOfDisputesBetween);
		
		JLabel lblWithACommunity = new JLabel("all these issues in three months or six months, but I");
		lblWithACommunity.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblWithACommunity.setBounds(744, 74, 319, 25);
		panel_2.add(lblWithACommunity);
		
		JLabel lblSocialAndCultural = new JLabel("period of time.\u201D");
		lblSocialAndCultural.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSocialAndCultural.setBounds(652, 104, 102, 25);
		panel_2.add(lblSocialAndCultural);
		
		JLabel lblForFacebooksFuture = new JLabel("for Facebook\u2019s future? In a 2017 manifesto, Zuckerberg argued that Facebook would help humanity");
		lblForFacebooksFuture.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblForFacebooksFuture.setBounds(463, 128, 600, 25);
		panel_2.add(lblForFacebooksFuture);
		
		JLabel lblStepByBecoming = new JLabel("step\u201D by becoming \u201Cthe social infrastructure\u201D for a truly global community.");
		lblStepByBecoming.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblStepByBecoming.setBounds(226, 156, 561, 25);
		panel_2.add(lblStepByBecoming);
		
		JLabel lblItemNo = new JLabel("Item no 13 of 15");
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
				Set2FillBlankCombo4 go=new Set2FillBlankCombo4();
				go.frame.setVisible(true);
			}
		});
		button_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_2.setBounds(1176, 639, 89, 28);
		frame.getContentPane().add(button_2);
		
		JLabel label_18 = new JLabel("Lab Symbiotic");
		label_18.setHorizontalAlignment(SwingConstants.TRAILING);
		label_18.setForeground(Color.DARK_GRAY);
		label_18.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_18.setBounds(1204, 678, 156, 20);
		frame.getContentPane().add(label_18);
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
				ra.fillBlankCombo3.add(comboBox.getSelectedItem().toString());
				ra.fillBlankCombo3.add(comboBox_1.getSelectedItem().toString());
				ra.fillBlankCombo3.add(comboBox_2.getSelectedItem().toString());
				ra.fillBlankCombo3.add(comboBox_3.getSelectedItem().toString());
				ra.fillBlankCombo3.add(comboBox_4.getSelectedItem().toString());
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
			ra.fillBlankCombo3.add(comboBox.getSelectedItem().toString());
			ra.fillBlankCombo3.add(comboBox_1.getSelectedItem().toString());
			ra.fillBlankCombo3.add(comboBox_2.getSelectedItem().toString());
			ra.fillBlankCombo3.add(comboBox_3.getSelectedItem().toString());
			ra.fillBlankCombo3.add(comboBox_4.getSelectedItem().toString());
			FileOutputStream fos=new FileOutputStream(answer,true);
			ObjectOutputStream oos=new ObjectOutputStream(new BufferedOutputStream (fos));
			oos.writeObject(ra);
			oos.close();
			fos.close();
		}
	}
}
