package set1reading;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
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

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import main.Functions;
import main.Menu;
import main.ReadingAnswer;
import main.sqlConnection;
import set1listening.Set1listening1;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Set1reading15 {

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
					Set1reading15 window = new Set1reading15();
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
	public Set1reading15() {
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
		label_1.setBounds(163, 121, 1051, 33);
		frame.getContentPane().add(label_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new MatteBorder(3, 0, 3, 0, (Color) new Color(0, 51, 102)));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(163, 307, 1051, 209);
		frame.getContentPane().add(panel_2);
		
		JLabel lblItsTrueThat = new JLabel("It\u2019s true that cellphones ");
		lblItsTrueThat.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblItsTrueThat.setBounds(10, 39, 138, 25);
		panel_2.add(lblItsTrueThat);
		
		 comboBox1 = new JComboBox();
		comboBox1.setModel(new DefaultComboBoxModel(new String[] {"emits", "does emits", "did emit", "do emit"}));
		comboBox1.setSelectedIndex(-1);
		comboBox1.setOpaque(true);
		comboBox1.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		comboBox1.setBackground(Color.WHITE);
		comboBox1.setBounds(158, 42, 157, 21);
		panel_2.add(comboBox1);
		
		JLabel lblAccidentsAndPhotographs = new JLabel("accidents and photographs of victims of the nuclear bombs the US dropped on Japan in World War II. People hear radiation and they");
		lblAccidentsAndPhotographs.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAccidentsAndPhotographs.setBounds(10, 71, 759, 25);
		panel_2.add(lblAccidentsAndPhotographs);
		
		 comboBox2 = new JComboBox();
		comboBox2.setModel(new DefaultComboBoxModel(new String[] {"cause of", "in part of", "because of", "because to"}));
		comboBox2.setSelectedIndex(-1);
		comboBox2.setOpaque(true);
		comboBox2.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		comboBox2.setBackground(Color.WHITE);
		comboBox2.setBounds(715, 42, 157, 21);
		panel_2.add(comboBox2);
		
		JLabel lblRadiationAndThe = new JLabel("radiation and the bomb, says Geoffrey Kabat, a cancer epidemiologist at the Albert Einstein College of Medicine and author of the book Getting Risk Right. \u201CThere are all these ");
		lblRadiationAndThe.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblRadiationAndThe.setBounds(10, 102, 1031, 25);
		panel_2.add(lblRadiationAndThe);
		
		JLabel lblAssociationsAndThose = new JLabel("associations and those are deeply ingrained");
		lblAssociationsAndThose.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAssociationsAndThose.setBounds(10, 133, 256, 25);
		panel_2.add(lblAssociationsAndThose);
		
		 comboBox3 = new JComboBox();
		comboBox3.setModel(new DefaultComboBoxModel(new String[] {"add", "join", "associate", "call"}));
		comboBox3.setSelectedIndex(-1);
		comboBox3.setOpaque(true);
		comboBox3.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		comboBox3.setBackground(Color.WHITE);
		comboBox3.setBounds(779, 74, 157, 21);
		panel_2.add(comboBox3);
		
		JLabel lblButIt = new JLabel(". But it ");
		lblButIt.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblButIt.setBounds(436, 133, 42, 25);
		panel_2.add(lblButIt);
		
		 comboBox4 = new JComboBox();
		comboBox4.setModel(new DefaultComboBoxModel(new String[] {"of people", "in people", "with people", "out people"}));
		comboBox4.setSelectedIndex(-1);
		comboBox4.setOpaque(true);
		comboBox4.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		comboBox4.setBackground(Color.WHITE);
		comboBox4.setBounds(269, 135, 157, 21);
		panel_2.add(comboBox4);
		
		JLabel lblApplyHere = new JLabel("apply here.\u201D");
		lblApplyHere.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblApplyHere.setBounds(655, 133, 174, 25);
		panel_2.add(lblApplyHere);
		
		 comboBox5 = new JComboBox();
		comboBox5.setModel(new DefaultComboBoxModel(new String[] {"doesn't", "do", "don't", "done"}));
		comboBox5.setSelectedIndex(-1);
		comboBox5.setOpaque(true);
		comboBox5.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		comboBox5.setBackground(Color.WHITE);
		comboBox5.setBounds(488, 135, 157, 21);
		panel_2.add(comboBox5);
		
		JLabel lblRadiationAndRadiation = new JLabel("radiation. And radiation is a scary word for a lot of people, thanks ");
		lblRadiationAndRadiation.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblRadiationAndRadiation.setBounds(325, 39, 380, 25);
		panel_2.add(lblRadiationAndRadiation);
		
		JLabel lblTheHorrificAftermath = new JLabel("the horrific aftermath of");
		lblTheHorrificAftermath.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTheHorrificAftermath.setBounds(882, 39, 159, 25);
		panel_2.add(lblTheHorrificAftermath);
		
		JLabel lblItWithNuclear = new JLabel("it with nuclear");
		lblItWithNuclear.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblItWithNuclear.setBounds(946, 71, 95, 25);
		panel_2.add(lblItWithNuclear);
		
		JLabel lblItemNo = new JLabel("Item no 15 of 15");
		lblItemNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemNo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblItemNo.setBounds(163, 628, 118, 24);
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
					Set1listening1 go=new Set1listening1();
					go.frame.setVisible(true);
				}
			}
		});
		button_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_1.setBackground(Color.WHITE);
		button_1.setBounds(1125, 627, 89, 28);
		frame.getContentPane().add(button_1);
		
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
					ra.fillBlankCombo5.add("");
				}
				else
				{
					ra.fillBlankCombo5.add(comboBox1.getSelectedItem().toString());
				}
				
				if(comboBox2.getSelectedIndex()==-1)
				{
					ra.fillBlankCombo5.add("");
				}
				else
				{
					ra.fillBlankCombo5.add(comboBox2.getSelectedItem().toString());
				}
				if(comboBox3.getSelectedIndex()==-1)
				{
					ra.fillBlankCombo5.add("");
				}
				else
				{
					ra.fillBlankCombo5.add(comboBox3.getSelectedItem().toString());
					
				}
				if(comboBox4.getSelectedIndex()==-1)
				{
					ra.fillBlankCombo5.add("");
				}
				else
				{
					ra.fillBlankCombo5.add(comboBox4.getSelectedItem().toString());
				}
				if(comboBox5.getSelectedIndex()==-1)
				{
					ra.fillBlankCombo5.add("");
				}
				else
				{
					ra.fillBlankCombo5.add(comboBox5.getSelectedItem().toString());
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
				ra.fillBlankCombo5.add("");
			}
			else
			{
				ra.fillBlankCombo5.add(comboBox1.getSelectedItem().toString());
			}
			
			if(comboBox2.getSelectedIndex()==-1)
			{
				ra.fillBlankCombo5.add("");
			}
			else
			{
				ra.fillBlankCombo5.add(comboBox2.getSelectedItem().toString());
			}
			if(comboBox3.getSelectedIndex()==-1)
			{
				ra.fillBlankCombo5.add("");
			}
			else
			{
				ra.fillBlankCombo5.add(comboBox3.getSelectedItem().toString());
				
			}
			if(comboBox4.getSelectedIndex()==-1)
			{
				ra.fillBlankCombo5.add("");
			}
			else
			{
				ra.fillBlankCombo5.add(comboBox4.getSelectedItem().toString());
			}
			if(comboBox5.getSelectedIndex()==-1)
			{
				ra.fillBlankCombo5.add("");
			}
			else
			{
				ra.fillBlankCombo5.add(comboBox5.getSelectedItem().toString());
			}
			FileOutputStream fos=new FileOutputStream(answer,true);
			ObjectOutputStream oos=new ObjectOutputStream(new BufferedOutputStream (fos));
			oos.writeObject(ra);
			oos.close();
			fos.close();
		}
	}

}
