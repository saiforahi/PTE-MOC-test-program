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

public class Set2FillBlankCombo2 {

	public JFrame frame;
	@SuppressWarnings("rawtypes")
	JComboBox comboBox;
	@SuppressWarnings("rawtypes")
	JComboBox comboBox_1;
	@SuppressWarnings("rawtypes")
	JComboBox comboBox_2;
	@SuppressWarnings("rawtypes")
	JComboBox comboBox_3,comboBox_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Set2FillBlankCombo2 window = new Set2FillBlankCombo2();
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
	public Set2FillBlankCombo2() {
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
		label_1.setBounds(148, 113, 1078, 33);
		frame.getContentPane().add(label_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(2, 0, 2, 0, (Color) Color.GRAY));
		panel_2.setLayout(null);
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(148, 214, 1078, 215);
		frame.getContentPane().add(panel_2);
		
		JLabel lblTheEiffelTower = new JLabel("In view of a world where \u201Cartificial intelligence will be everywhere, like electricity,\u201D as Villani has said, becoming a leader in the field is");
		lblTheEiffelTower.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTheEiffelTower.setBounds(10, 11, 773, 25);
		panel_2.add(lblTheEiffelTower);
		
		JLabel lblThatIronCould = new JLabel("for France.");
		lblThatIronCould.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblThatIronCould.setBounds(960, 11, 108, 25);
		panel_2.add(lblThatIronCould);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"casual", "non-essential", "critical", "developed"}));
		comboBox.setSelectedIndex(-1);
		comboBox.setOpaque(true);
		comboBox.setBackground(Color.WHITE);
		comboBox.setBounds(793, 12, 157, 22);
		panel_2.add(comboBox);
		
		JLabel lblStoneWhileBeing = new JLabel("Many feel that Europe is already at an enormous");
		lblStoneWhileBeing.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblStoneWhileBeing.setBounds(10, 37, 278, 25);
		panel_2.add(lblStoneWhileBeing);
		
		JLabel lblYearsWith = new JLabel("thing, France and Europe");
		lblYearsWith.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblYearsWith.setBounds(10, 63, 144, 25);
		panel_2.add(lblYearsWith);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"concern", "disadvantage", "upper hand", "advantage"}));
		comboBox_1.setSelectedIndex(-1);
		comboBox_1.setOpaque(true);
		comboBox_1.setBackground(Color.WHITE);
		comboBox_1.setBounds(298, 38, 157, 22);
		panel_2.add(comboBox_1);
		
		JLabel lblCalledtheFather = new JLabel("GAFA (Google, Apple, Facebook, and Amazon). French bureaucracy has also historically");
		lblCalledtheFather.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCalledtheFather.setBounds(10, 93, 501, 25);
		panel_2.add(lblCalledtheFather);
		
		comboBox_2 = new JComboBox();
		comboBox_2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"doesn't have", "has", "had not", "don't have"}));
		comboBox_2.setSelectedIndex(-1);
		comboBox_2.setOpaque(true);
		comboBox_2.setBackground(Color.WHITE);
		comboBox_2.setBounds(161, 65, 157, 22);
		panel_2.add(comboBox_2);
		
		comboBox_3 = new JComboBox();
		comboBox_3.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"been", "become", "was", "is"}));
		comboBox_3.setSelectedIndex(-1);
		comboBox_3.setOpaque(true);
		comboBox_3.setBackground(Color.WHITE);
		comboBox_3.setBounds(521, 94, 157, 22);
		panel_2.add(comboBox_3);
		
		comboBox_4 = new JComboBox();
		comboBox_4.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] {"lack", "quality", "incompetence    ", "outstanding"}));
		comboBox_4.setSelectedIndex(-1);
		comboBox_4.setOpaque(true);
		comboBox_4.setBackground(Color.WHITE);
		comboBox_4.setBounds(611, 122, 157, 22);
		panel_2.add(comboBox_4);
		
		JLabel lblIsToMake = new JLabel("compared to the US and China and will need to do some Usain Bolt-style sprinting to catch up. For one");
		lblIsToMake.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblIsToMake.setBounds(461, 37, 607, 25);
		panel_2.add(lblIsToMake);
		
		JLabel lblWorldThatAssumption = new JLabel("the data-gathering platforms necessary to fuel machine learning: they lack the power of what the acronym-loving French call ");
		lblWorldThatAssumption.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblWorldThatAssumption.setBounds(328, 63, 740, 25);
		panel_2.add(lblWorldThatAssumption);
		
		JLabel lblAndInciteViolence = new JLabel("a drag on entrepreneurship and invention. Compared to the US, ");
		lblAndInciteViolence.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAndInciteViolence.setBounds(688, 93, 380, 25);
		panel_2.add(lblAndInciteViolence);
		
		JLabel lblCooperationBetweenAcademia = new JLabel("cooperation between academia and industry is much less frequent. And though France is known for the");
		lblCooperationBetweenAcademia.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCooperationBetweenAcademia.setBounds(10, 121, 591, 25);
		panel_2.add(lblCooperationBetweenAcademia);
		
		JLabel lblOfItsEngineers = new JLabel("of its engineers and scientists, much of the ");
		lblOfItsEngineers.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblOfItsEngineers.setBounds(778, 117, 300, 25);
		panel_2.add(lblOfItsEngineers);
		
		JLabel lblToplevelTalentGoes = new JLabel("top-level talent goes abroad, where there is more money and freedom to pursue research without constraints. Addressing these issues by sketching the nation\u2019s AI road map has fallen");
		lblToplevelTalentGoes.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblToplevelTalentGoes.setBounds(10, 149, 1068, 25);
		panel_2.add(lblToplevelTalentGoes);
		
		JLabel lblOnTheWelltailored = new JLabel("on the well-tailored shoulders of Villani.");
		lblOnTheWelltailored.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblOnTheWelltailored.setBounds(10, 176, 1068, 25);
		panel_2.add(lblOnTheWelltailored);
		
		JLabel lblItemNo = new JLabel("Item no 12 of 15");
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
				Set2FillBlankCombo3 go= new Set2FillBlankCombo3();
				go.frame.setVisible(true);
			}
		});
		button_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_2.setBounds(1176, 639, 89, 28);
		frame.getContentPane().add(button_2);
		
		JLabel label_19 = new JLabel("Lab Symbiotic");
		label_19.setHorizontalAlignment(SwingConstants.TRAILING);
		label_19.setForeground(Color.DARK_GRAY);
		label_19.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_19.setBounds(1204, 678, 156, 20);
		frame.getContentPane().add(label_19);
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
				ra.fillBlankCombo2.add(comboBox.getSelectedItem().toString());
				ra.fillBlankCombo2.add(comboBox_1.getSelectedItem().toString());
				ra.fillBlankCombo2.add(comboBox_2.getSelectedItem().toString());
				ra.fillBlankCombo2.add(comboBox_3.getSelectedItem().toString());
				ra.fillBlankCombo2.add(comboBox_4.getSelectedItem().toString());
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
			ra.fillBlankCombo2.add(comboBox.getSelectedItem().toString());
			ra.fillBlankCombo2.add(comboBox_1.getSelectedItem().toString());
			ra.fillBlankCombo2.add(comboBox_2.getSelectedItem().toString());
			ra.fillBlankCombo2.add(comboBox_3.getSelectedItem().toString());
			ra.fillBlankCombo2.add(comboBox_4.getSelectedItem().toString());
			FileOutputStream fos=new FileOutputStream(answer,true);
			ObjectOutputStream oos=new ObjectOutputStream(new BufferedOutputStream (fos));
			oos.writeObject(ra);
			oos.close();
			fos.close();
		}
	}

}
