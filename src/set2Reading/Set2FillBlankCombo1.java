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

public class Set2FillBlankCombo1 {

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
					Set2FillBlankCombo1 window = new Set2FillBlankCombo1();
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
	public Set2FillBlankCombo1() {
		initialize();
		frame.setIconImage(Functions.setIcon());
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
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
		lblItemNo.setBounds(135, 640, 118, 24);
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
				Set2FillBlankCombo2 go=new Set2FillBlankCombo2();
				go.frame.setVisible(true);
			}
		});
		button_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_2.setBounds(1176, 639, 89, 28);
		frame.getContentPane().add(button_2);
		
		JLabel label_3 = new JLabel("Lab Symbiotic");
		label_3.setHorizontalAlignment(SwingConstants.TRAILING);
		label_3.setForeground(Color.DARK_GRAY);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_3.setBounds(1204, 678, 156, 20);
		frame.getContentPane().add(label_3);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(135, 289, 1113, 169);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblCalledChomolungmagoddess = new JLabel("The voeux, or \u201Cnew year\u2019s wishes,\u201D are a standard exercise of French politicians from the president on down, in which they");
		lblCalledChomolungmagoddess.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCalledChomolungmagoddess.setBounds(10, 11, 705, 25);
		panel_2.add(lblCalledChomolungmagoddess);
		
		JLabel lblAmongWesternersThat = new JLabel(" lay out projects for the year to come. Villani, a mathematician and Fields Medal winner (often shorthanded as the equivalent of the Nobel Prize in mathematics), was");
		lblAmongWesternersThat.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAmongWesternersThat.setBounds(10, 41, 947, 25);
		panel_2.add(lblAmongWesternersThat);
		
		comboBox1 = new JComboBox();
		comboBox1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		comboBox1.setModel(new DefaultComboBoxModel(new String[] {"repeat", "forget", "review", "forgive"}));
		comboBox1.setBackground(new Color(255, 255, 255));
		comboBox1.setBounds(725, 14, 157, 22);
		comboBox1.setSelectedIndex(-1);
		comboBox1.setOpaque(true);
		panel_2.add(comboBox1);
		
		JLabel lblSeemedLostIn = new JLabel("the practice; only six months earlier, he was still an academic. He was dressed");
		lblSeemedLostIn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSeemedLostIn.setBounds(10, 71, 452, 25);
		panel_2.add(lblSeemedLostIn);
		
		comboBox2 = new JComboBox();
		comboBox2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		comboBox2.setModel(new DefaultComboBoxModel(new String[] {"new by", "new to", "new of", "new from"}));
		comboBox2.setOpaque(true);
		comboBox2.setBackground(Color.WHITE);
		comboBox2.setSelectedIndex(-1);
		comboBox2.setBounds(967, 42, 135, 22);
		panel_2.add(comboBox2);
		
		JLabel lblInThe = new JLabel("\u2014 winter or summer \u2014 in a black three-piece suit, a shirt with cufflinks, a");
		lblInThe.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblInThe.setBounds(639, 71, 453, 25);
		panel_2.add(lblInThe);
		
		comboBox3 = new JComboBox();
		comboBox3.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		comboBox3.setModel(new DefaultComboBoxModel(new String[] {"informally", "casually", "like never before", "as always"}));
		comboBox3.setOpaque(true);
		comboBox3.setSelectedIndex(-1);
		comboBox3.setBackground(Color.WHITE);
		comboBox3.setBounds(472, 72, 157, 22);
		panel_2.add(comboBox3);
		
		JLabel lblHeightForMore = new JLabel("spider brooch on his lapel, and a large, floppy tie called a lavalli\u00E8re (today\u2019s version in purple). He cut an unmistakable figure, sporting a three-day beard, his dark hair");
		lblHeightForMore.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblHeightForMore.setBounds(10, 100, 954, 25);
		panel_2.add(lblHeightForMore);
		
		JLabel lblTheMountainReceived = new JLabel("selfies before taking the stage.");
		lblTheMountainReceived.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTheMountainReceived.setBounds(496, 129, 336, 25);
		panel_2.add(lblTheMountainReceived);
		
		comboBox4 = new JComboBox();
		comboBox4.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		comboBox4.setModel(new DefaultComboBoxModel(new String[] {"styled", "positioned", "motioned", "created"}));
		comboBox4.setSelectedIndex(-1);
		comboBox4.setOpaque(true);
		comboBox4.setBackground(Color.WHITE);
		comboBox4.setSelectedIndex(-1);
		comboBox4.setBounds(971, 101, 131, 22);
		panel_2.add(comboBox4);
		
		comboBox5 = new JComboBox();
		comboBox5.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		comboBox5.setModel(new DefaultComboBoxModel(new String[] {"posed with", "posed by", "posed for", "posed into"}));
		comboBox5.setSelectedIndex(-1);
		comboBox5.setOpaque(true);
		comboBox5.setBackground(Color.WHITE);
		comboBox5.setSelectedIndex(-1);
		comboBox5.setBounds(330, 130, 157, 22);
		panel_2.add(comboBox5);
		
		JLabel lblIsHisTask = new JLabel("in a pageboy. He mingled, smiling with attendees, and");
		lblIsHisTask.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblIsHisTask.setBounds(10, 130, 310, 25);
		panel_2.add(lblIsHisTask);
		
		JLabel lblActivitiesOfThe = new JLabel("activities of the past year and");
		lblActivitiesOfThe.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblActivitiesOfThe.setBounds(892, 11, 186, 25);
		panel_2.add(lblActivitiesOfThe);
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
				ra.fillBlankCombo.add(comboBox1.getSelectedItem().toString());
				ra.fillBlankCombo.add(comboBox2.getSelectedItem().toString());
				ra.fillBlankCombo.add(comboBox3.getSelectedItem().toString());
				ra.fillBlankCombo.add(comboBox4.getSelectedItem().toString());
				ra.fillBlankCombo.add(comboBox5.getSelectedItem().toString());
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
			ra.fillBlankCombo.add(comboBox1.getSelectedItem().toString());
			ra.fillBlankCombo.add(comboBox2.getSelectedItem().toString());
			ra.fillBlankCombo.add(comboBox3.getSelectedItem().toString());
			ra.fillBlankCombo.add(comboBox4.getSelectedItem().toString());
			ra.fillBlankCombo.add(comboBox5.getSelectedItem().toString());
			FileOutputStream fos=new FileOutputStream(answer,true);
			ObjectOutputStream oos=new ObjectOutputStream(new BufferedOutputStream (fos));
			oos.writeObject(ra);
			oos.close();
			fos.close();
		}
	}
}
