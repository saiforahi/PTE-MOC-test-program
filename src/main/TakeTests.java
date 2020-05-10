package main;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;

import set1.MicCheck1;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class TakeTests {

	public JFrame frmTakeTests;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	public static int setNumber;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TakeTests window = new TakeTests();
					window.frmTakeTests.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TakeTests() {
		initialize();
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
		frmTakeTests = new JFrame();
		frmTakeTests.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				Menu go=new Menu();
				go.frame.setVisible(true);
			}
		});
		frmTakeTests.getContentPane().setBackground(Color.WHITE);
		frmTakeTests.setTitle("Take Tests");
		frmTakeTests.setIconImage(Functions.setIcon());
		frmTakeTests.pack();
		frmTakeTests.setSize(new Dimension(screenSize.width,screenSize.height));
		frmTakeTests.getContentPane().setPreferredSize(new Dimension(screenSize.width,screenSize.height));
		frmTakeTests.getContentPane().setLayout(null);
		frmTakeTests.setLocationRelativeTo(null);
		frmTakeTests.setAlwaysOnTop(true);
		frmTakeTests.setLocation(0,0);
		frmTakeTests.setResizable(false);
		
		JLabel label = new JLabel("\u00A9Lab Symbiotic");
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.DARK_GRAY);
		label.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		label.setBounds(0, 704, 1370, 24);
		frmTakeTests.getContentPane().add(label);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(64, 64, 64), 1, true));
		panel_2.setBackground(Color.DARK_GRAY);
		panel_2.setBounds(1021, 597, 186, 36);
		frmTakeTests.getContentPane().add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton = new JButton("Back to main menu");
		btnNewButton.setToolTipText("Clict to go backward");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmTakeTests.dispose();
				Menu go=new Menu();
				go.frame.setVisible(true);
				go.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			}
		});
		btnNewButton.setBackground(Color.GRAY);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_2.add(btnNewButton, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(245, 245, 220));
		panel_1.setBorder(new LineBorder(new Color(64, 64, 64), 5, true));
		panel_1.setBounds(149, 171, 319, 339);
		frmTakeTests.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Test 1");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(33, 11, 252, 40);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("Speaking Items");
		lblNewLabel_3.setForeground(new Color(139, 0, 0));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Malgun Gothic Semilight", Font.BOLD, 22));
		lblNewLabel_3.setBounds(33, 62, 252, 48);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblWritingItems = new JLabel("Writing Items");
		lblWritingItems.setForeground(new Color(255, 140, 0));
		lblWritingItems.setHorizontalAlignment(SwingConstants.CENTER);
		lblWritingItems.setFont(new Font("Malgun Gothic Semilight", Font.BOLD, 22));
		lblWritingItems.setBounds(33, 126, 252, 48);
		panel_1.add(lblWritingItems);
		
		JLabel lblReadingItems = new JLabel("Reading Items");
		lblReadingItems.setForeground(new Color(128, 0, 128));
		lblReadingItems.setHorizontalAlignment(SwingConstants.CENTER);
		lblReadingItems.setFont(new Font("Malgun Gothic Semilight", Font.BOLD, 22));
		lblReadingItems.setBounds(33, 190, 252, 48);
		panel_1.add(lblReadingItems);
		
		JLabel lblListeningItems = new JLabel("Listening Items");
		lblListeningItems.setForeground(new Color(25, 25, 112));
		lblListeningItems.setHorizontalAlignment(SwingConstants.CENTER);
		lblListeningItems.setFont(new Font("Malgun Gothic Semilight", Font.BOLD, 22));
		lblListeningItems.setBounds(33, 254, 252, 48);
		panel_1.add(lblListeningItems);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setForeground(Color.WHITE);
		panel.setBackground(new Color(255, 165, 0));
		panel.setBounds(0, 36, 1366, 42);
		frmTakeTests.getContentPane().add(panel);
		
		JLabel lblPteAcademic = new JLabel("PTE Academic MOC tests");
		lblPteAcademic.setForeground(Color.DARK_GRAY);
		lblPteAcademic.setFont(new Font("Cambria Math", Font.BOLD, 34));
		lblPteAcademic.setBounds(152, 0, 942, 42);
		panel.add(lblPteAcademic);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(253, 245, 230));
		panel_3.setBorder(new LineBorder(new Color(64, 64, 64), 5, true));
		panel_3.setBounds(520, 171, 319, 339);
		frmTakeTests.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblTest = new JLabel("Test 2");
		lblTest.setHorizontalAlignment(SwingConstants.CENTER);
		lblTest.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTest.setBounds(33, 11, 252, 40);
		panel_3.add(lblTest);
		
		JLabel label_4 = new JLabel("Writing Items");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setForeground(new Color(255, 140, 0));
		label_4.setFont(new Font("Malgun Gothic Semilight", Font.BOLD, 22));
		label_4.setBounds(33, 126, 252, 48);
		panel_3.add(label_4);
		
		JLabel label_5 = new JLabel("Speaking Items");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setForeground(new Color(139, 0, 0));
		label_5.setFont(new Font("Malgun Gothic Semilight", Font.BOLD, 22));
		label_5.setBounds(33, 62, 252, 48);
		panel_3.add(label_5);
		
		JLabel label_6 = new JLabel("Reading Items");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setForeground(new Color(128, 0, 128));
		label_6.setFont(new Font("Malgun Gothic Semilight", Font.BOLD, 22));
		label_6.setBounds(33, 190, 252, 48);
		panel_3.add(label_6);
		
		JLabel label_7 = new JLabel("Listening Items");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setForeground(new Color(25, 25, 112));
		label_7.setFont(new Font("Malgun Gothic Semilight", Font.BOLD, 22));
		label_7.setBounds(33, 254, 252, 48);
		panel_3.add(label_7);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(230, 230, 250));
		panel_4.setBorder(new LineBorder(new Color(64, 64, 64), 5, true));
		panel_4.setBounds(888, 171, 319, 339);
		frmTakeTests.getContentPane().add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblTest_1 = new JLabel("Test 3");
		lblTest_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTest_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTest_1.setBounds(34, 11, 252, 40);
		panel_4.add(lblTest_1);
		
		JLabel label_8 = new JLabel("Writing Items");
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setForeground(new Color(255, 140, 0));
		label_8.setFont(new Font("Malgun Gothic Semilight", Font.BOLD, 22));
		label_8.setBounds(34, 126, 252, 48);
		panel_4.add(label_8);
		
		JLabel label_9 = new JLabel("Speaking Items");
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setForeground(new Color(139, 0, 0));
		label_9.setFont(new Font("Malgun Gothic Semilight", Font.BOLD, 22));
		label_9.setBounds(34, 62, 252, 48);
		panel_4.add(label_9);
		
		JLabel label_10 = new JLabel("Reading Items");
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		label_10.setForeground(new Color(128, 0, 128));
		label_10.setFont(new Font("Malgun Gothic Semilight", Font.BOLD, 22));
		label_10.setBounds(34, 190, 252, 48);
		panel_4.add(label_10);
		
		JLabel label_11 = new JLabel("Listening Items");
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		label_11.setForeground(new Color(25, 25, 112));
		label_11.setFont(new Font("Malgun Gothic Semilight", Font.BOLD, 22));
		label_11.setBounds(34, 254, 252, 48);
		panel_4.add(label_11);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(Color.DARK_GRAY, 2));
		panel_5.setBounds(149, 521, 319, 50);
		frmTakeTests.getContentPane().add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Enter Code");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2.setBounds(0, 0, 92, 50);
		panel_5.add(lblNewLabel_2);
		
		JButton btnGo = new JButton("Go!");
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textField.getText().equals(null)||textField.getText().trim().equals(""))
					{
						JOptionPane.showMessageDialog(frmTakeTests, "Enter Code first....");
					}
					else
					{
						boolean match=false;
						File license=new File("License.ser");
						
						try {
							FileInputStream fis = new FileInputStream(license);
							ObjectInputStream ois=new ObjectInputStream(fis);
							License l=(License)ois.readObject();
							if(l.set1voucher.equalsIgnoreCase(textField.getText()))
								{
									setNumber=1;
									frmTakeTests.dispose();
									UserInput go=new UserInput();
									go.frame.setVisible(true);
									go.frame.setAlwaysOnTop(true);
									go.frame.setLocationRelativeTo(null);
									match=true;
							}
							
							if(match==false)
							{
								JOptionPane.showMessageDialog(frmTakeTests, "Wrong voucher code!");
							}
							ois.close();
							fis.close();
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}	
				}
			}
		});
		btnGo.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnGo.setBounds(243, 7, 70, 35);
		panel_5.add(btnGo);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER)
				{
					if(textField.getText().equals(null)||textField.getText().trim().equals(""))
					{
						JOptionPane.showMessageDialog(frmTakeTests, "Enter Code first....");
					}
					else
					{
						boolean match=false;
						File license=new File("License.ser");
						
						try {
							FileInputStream fis = new FileInputStream(license);
							ObjectInputStream ois=new ObjectInputStream(fis);
							License l=(License)ois.readObject();
							if(l.set1voucher.equalsIgnoreCase(textField.getText()))
								{
									setNumber=1;
									frmTakeTests.dispose();
									UserInput go=new UserInput();
									go.frame.setVisible(true);
									go.frame.setAlwaysOnTop(true);
									go.frame.setLocationRelativeTo(null);
									match=true;
							}
							
							if(match==false)
							{
								JOptionPane.showMessageDialog(frmTakeTests, "Wrong voucher code!");
							}
							ois.close();
							fis.close();
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				}
			}
		});
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(100, 10, 134, 28);
		panel_5.add(textField);
		textField.setColumns(10);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new LineBorder(Color.DARK_GRAY, 2));
		panel_6.setBounds(520, 521, 319, 50);
		frmTakeTests.getContentPane().add(panel_6);
		panel_6.setLayout(null);
		
		JLabel label_2 = new JLabel("Enter Code");
		label_2.setHorizontalAlignment(SwingConstants.TRAILING);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_2.setBounds(0, 0, 92, 50);
		panel_6.add(label_2);
		
		textField_1 = new JTextField();
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER)
				{
					if(textField_1.getText().equals(null)||textField_1.getText().trim().equals(""))
					{
						JOptionPane.showMessageDialog(frmTakeTests, "Enter Code first....");
					}
					else
					{
						boolean match=false;
						File license=new File("License.ser");
						
						try {
							FileInputStream fis = new FileInputStream(license);
							ObjectInputStream ois=new ObjectInputStream(fis);
							License l=(License)ois.readObject();
							if(l.set2voucher.equalsIgnoreCase(textField_1.getText()))
								{
									setNumber=2;
									MicCheck1.setNumber=2;
									frmTakeTests.dispose();
									UserInput go=new UserInput();
									go.frame.setVisible(true);
									go.frame.setAlwaysOnTop(true);
									go.frame.setLocationRelativeTo(null);
									match=true;
							}
							
							if(match==false)
							{
								JOptionPane.showMessageDialog(frmTakeTests, "Wrong voucher code!");
							}
							ois.close();
							fis.close();
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}	
				}
				}
			}
		});
		/*textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER){
					 if(textField_1.getText().equals(null)||textField_1.getText().trim().equals(""))
						{
							JOptionPane.showMessageDialog(frmTakeTests, "Enter Code first....");
						}
						else
						{
							boolean match=false;
							try {
								Connection conn=sqlConnection.dbConnection();
								ResultSet rs=conn.createStatement().executeQuery("SELECT test2 FROM voucher;");
								while(rs.next())
								{
									if(rs.getString("test2").equals(textField_1.getText()))
									{
										frmTakeTests.dispose();
										MicCheck1 go=new MicCheck1();
										MicCheck.setNumber=2;
										go.frmMicrophoneCheck.setVisible(true);
										go.frmMicrophoneCheck.setLocation(-1, -1);
										match=true;
										break;
									}
								}
								if(match==false)
								{
									JOptionPane.showMessageDialog(frmTakeTests, "Wrong voucher code!");
								}
								rs.close();
								conn.close();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
				 }
			}
		});*/
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setColumns(10);
		textField_1.setBounds(100, 10, 134, 28);
		panel_6.add(textField_1);
		
		JButton button = new JButton("Go!");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textField_1.getText().equals(null)||textField_1.getText().trim().equals(""))
				{
					JOptionPane.showMessageDialog(frmTakeTests, "Enter Code first....");
				}
				else
				{
					boolean match=false;
					File license=new File("License.ser");
					
					try {
						FileInputStream fis = new FileInputStream(license);
						ObjectInputStream ois=new ObjectInputStream(fis);
						License l=(License)ois.readObject();
						if(l.set2voucher.equalsIgnoreCase(textField_1.getText()))
							{
								setNumber=2;
								MicCheck1.setNumber=2;
								frmTakeTests.dispose();
								UserInput go=new UserInput();
								go.frame.setVisible(true);
								go.frame.setAlwaysOnTop(true);
								go.frame.setLocationRelativeTo(null);
								match=true;
						}
						
						if(match==false)
						{
							JOptionPane.showMessageDialog(frmTakeTests, "Wrong voucher code!");
						}
						ois.close();
						fis.close();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
			}
			}
		});
		/*button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textField_1.getText().equals(null)||textField_1.getText().trim().equals(""))
				{
					JOptionPane.showMessageDialog(frmTakeTests, "Enter Code first....");
				}
				else
				{
					boolean match=false;
					try {
						Connection conn=sqlConnection.dbConnection();
						ResultSet rs=conn.createStatement().executeQuery("SELECT test2 FROM voucher;");
						while(rs.next())
						{
							if(rs.getString("test2").equals(textField_1.getText()))
							{
								frmTakeTests.dispose();
								MicCheck go=new MicCheck();
								MicCheck.setNumber=2;
								go.frmMicrophoneCheck.setVisible(true);
								go.frmMicrophoneCheck.setLocation(-1, -1);
								match=true;
								break;
							}
						}
						if(match==false)
						{
							JOptionPane.showMessageDialog(frmTakeTests, "Wrong voucher code!");
						}
						rs.close();
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});*/
		button.setFont(new Font("Tahoma", Font.BOLD, 18));
		button.setBounds(243, 7, 70, 35);
		panel_6.add(button);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new LineBorder(Color.DARK_GRAY, 2));
		panel_7.setBounds(888, 521, 319, 50);
		frmTakeTests.getContentPane().add(panel_7);
		panel_7.setLayout(null);
		
		JLabel label_3 = new JLabel("Enter Code");
		label_3.setHorizontalAlignment(SwingConstants.TRAILING);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_3.setBounds(0, 0, 92, 50);
		panel_7.add(label_3);
		
		textField_2 = new JTextField();
		textField_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER)
				{
					JOptionPane.showMessageDialog(frmTakeTests, "Under Development!");
				}
			}
		});
		/*textField_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER){
					 if(textField_2.getText().equals(null)||textField_2.getText().trim().equals(""))
						{
							JOptionPane.showMessageDialog(frmTakeTests, "Enter Code first....");
						}
						else
						{
							boolean match=false;
							try {
								Connection conn=sqlConnection.dbConnection();
								ResultSet rs=conn.createStatement().executeQuery("SELECT test3 FROM voucher;");
								while(rs.next())
								{
									if(rs.getString("test3").equals(textField_2.getText()))
									{
										frmTakeTests.dispose();
										MicCheck go=new MicCheck();
										MicCheck.setNumber=3;
										go.frmMicrophoneCheck.setVisible(true);
										go.frmMicrophoneCheck.setLocation(-1, -1);
										match=true;
										break;
									}
								}
								if(match==false)
								{
									JOptionPane.showMessageDialog(frmTakeTests, "Wrong voucher code!");
								}
								rs.close();
								conn.close();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
				 }
			}
		});*/
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setColumns(10);
		textField_2.setBounds(102, 10, 134, 28);
		panel_7.add(textField_2);
		
		JButton button_1 = new JButton("Go!");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(frmTakeTests, "Under Development!");
			}
		});
		/*button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textField_2.getText().equals(null)||textField_2.getText().trim().equals(""))
				{
					JOptionPane.showMessageDialog(frmTakeTests, "Enter Code first....");
				}
				else
				{
					boolean match=false;
					try {
						Connection conn=sqlConnection.dbConnection();
						ResultSet rs=conn.createStatement().executeQuery("SELECT test3 FROM voucher;");
						while(rs.next())
						{
							if(rs.getString("test3").equals(textField_2.getText()))
							{
								frmTakeTests.dispose();
								MicCheck go=new MicCheck();
								MicCheck.setNumber=3;
								go.frmMicrophoneCheck.setVisible(true);
								go.frmMicrophoneCheck.setLocation(-1, -1);
								match=true;
								break;
							}
						}
						if(match==false)
						{
							JOptionPane.showMessageDialog(frmTakeTests, "Wrong voucher code!");
						}
						rs.close();
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});*/
		button_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		button_1.setBounds(243, 7, 70, 35);
		panel_7.add(button_1);
		
		JLabel lblNewLabel_4 = new JLabel("Take Tests");
		lblNewLabel_4.setForeground(new Color(25, 25, 112));
		lblNewLabel_4.setFont(new Font("Sitka Banner", Font.BOLD, 40));
		lblNewLabel_4.setBounds(149, 104, 257, 56);
		frmTakeTests.getContentPane().add(lblNewLabel_4);
		frmTakeTests.setResizable(false);
		
	}
}
