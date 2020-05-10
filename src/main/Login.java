package main;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.MatteBorder;


public class Login implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JFrame frmLogin;
	private JTextField textField;
	int pass=1122;
	boolean log=false;
	License l;
	JCheckBox chckbxNewCheckBox ;
	JLabel lblNewLabel,label_1;
	
	private JLabel label;
	JLabel logoText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					Date date=new SimpleDateFormat("dd/MM/yyyy").parse("14/10/2018");
					if(date.compareTo(new SimpleDateFormat("dd/MM/yyyy").parse(getCurrentDate()))>=0)
					{
						Login window = new Login();
						window.frmLogin.setVisible(true);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "DataSource interrupted!");
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
		setButtonImage();
		frmLogin.setIconImage(setIcon());
		setLogo();
		logoTextAnimation();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
		frmLogin = new JFrame();
		frmLogin.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				Animation go=new Animation();
				go.frame.setVisible(true);
				go.endtitleAnimation();
			}
		});
		
		frmLogin.setTitle("Login");
		frmLogin.getContentPane().setBackground(SystemColor.menu);
		frmLogin.setBackground(SystemColor.menu);
		frmLogin.setBounds(100,100, 604, 457);
		frmLogin.setAlwaysOnTop(true);
		frmLogin.setResizable(false);
		frmLogin.setLocationRelativeTo(null);
		frmLogin.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBounds(0, 0, 599, 427);
		panel.setBackground(new Color(255, 255, 255));
		panel.setOpaque(true);
		frmLogin.getContentPane().add(panel);
		panel.setLayout(null);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				File f=new File("License.ser");
				if(f.exists())
				{
					try {
						FileInputStream fis = new FileInputStream(f);
						ObjectInputStream ois=new ObjectInputStream(fis);
						l=(License)ois.readObject();
						if(l.times<=10)
						{
							System.out.println(l.times);
							if(textField.getText().toString().equals(l.code))
							{
								l.times++;
								log=true;
								frmLogin.dispose();
								Menu goToMenu= new Menu();
								goToMenu.frame.setVisible(true);
								goToMenu.frame.setLocationRelativeTo(null);
								if(chckbxNewCheckBox.isSelected())
								{
									goToMenu.btnAnswerKey.setEnabled(false);
								}
								JOptionPane.showMessageDialog(goToMenu.frame, "You have successfully entered!!");
							}
							else
							{
								JOptionPane.showMessageDialog(frmLogin, "Wrong code");
							}
						}
						else
						{
							JOptionPane.showMessageDialog(frmLogin, "Limit Expired");
						}
						ois.close();
						fis.close();
					} catch (FileNotFoundException e) {
						JOptionPane.showMessageDialog(frmLogin, "Limit Expired");
						e.printStackTrace();
					} catch (IOException e) {
						JOptionPane.showMessageDialog(frmLogin,e.getMessage());
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						JOptionPane.showMessageDialog(frmLogin,e.getMessage());
						e.printStackTrace();
					}
					
				}
				else
				{
					JOptionPane.showMessageDialog(frmLogin, "Status not valid");
				}
				if(log==true)
				{
					try {
						FileOutputStream fos=new FileOutputStream(f);
						ObjectOutputStream oos=new ObjectOutputStream(fos);
						oos.writeObject(l);
						oos.close();
						fos.close();
					} catch (FileNotFoundException e) {
						JOptionPane.showMessageDialog(frmLogin,e.getMessage());
						e.printStackTrace();
					} catch (IOException e) {
						JOptionPane.showMessageDialog(frmLogin,e.getMessage());
						e.printStackTrace();
					}
				}
				if(l.times>=10)
				{
					f.delete();
				}
			}
		});
		lblNewLabel.setIcon(new ImageIcon("arrow.png"));
		lblNewLabel.setBounds(454, 243, 44, 37);
		panel.add(lblNewLabel);
		
		JLabel lblEnterCode = new JLabel("Enter Code");
		lblEnterCode.setForeground(new Color(0, 0, 153));
		lblEnterCode.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterCode.setFont(new Font("Sakkal Majalla", Font.BOLD, 33));
		lblEnterCode.setBounds(105, 243, 147, 37);
		panel.add(lblEnterCode);
		
		logoText = new JLabel("Center for Innovative Language Learning");
		logoText.setForeground(new Color(0, 0, 153));
		logoText.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		logoText.setHorizontalAlignment(SwingConstants.CENTER);
		logoText.setBounds(69, 153, 473, 30);
		panel.add(logoText);
		
		textField = new JTextField();
		textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frmLogin.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
				textField.requestFocus();
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frmLogin.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				frmLogin.requestFocusInWindow();
			}
		});
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER)
				{
					File f=new File("License.ser");
					if(f.exists())
					{
						try {
							FileInputStream fis = new FileInputStream(f);
							ObjectInputStream ois=new ObjectInputStream(fis);
							l=(License)ois.readObject();
							ois.close();
							fis.close();
							if(l.times<=10)
							{
								System.out.println(l.times);
								if(textField.getText().toString().equals(l.code))
								{
									l.times++;
									f.delete();
									FileOutputStream fos = new FileOutputStream(new File("License.ser"));
									ObjectOutputStream oos=new ObjectOutputStream(fos);
									oos.writeObject(l);
									oos.close();
									fos.close();
									log=true;
									frmLogin.dispose();
									Menu goToMenu= new Menu();
									goToMenu.frame.setVisible(true);
									
									if(chckbxNewCheckBox.isSelected())
									{
										goToMenu.btnAnswerKey.setEnabled(false);
									}
									//JOptionPane.showMessageDialog(goToMenu.frame, "You have successfully entered!!");
								}
								else
								{
									JOptionPane.showMessageDialog(frmLogin, "Wrong code");
								}
							}
							else
							{
								JOptionPane.showMessageDialog(frmLogin, "Expired");
							}
							
						} catch (FileNotFoundException e) {
							JOptionPane.showMessageDialog(frmLogin, "Expired");
							System.exit(0);
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(frmLogin, e.getMessage());
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							JOptionPane.showMessageDialog(frmLogin, e.getMessage());
							e.printStackTrace();
						}
						
					}
					else
					{
						JOptionPane.showMessageDialog(frmLogin, "Status not valid");
					}
					if(log==true)
					{
						//l.times++;
						try {
							FileOutputStream fos=new FileOutputStream(f);
							ObjectOutputStream oos=new ObjectOutputStream(fos);
							oos.writeObject(l);
							oos.close();
							fos.close();
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if(l.times>=10)
					{
						f.delete();
					}
				}
			}
		});
		textField.setToolTipText("Type here");
		textField.setOpaque(false);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setForeground(Color.DARK_GRAY);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField.setBorder(new MatteBorder(1, 0, 1, 0, (Color) new Color(0, 0, 102)));
		textField.setColumns(10);
		textField.setBackground(new Color(0, 0, 0, 0));
		textField.setBounds(262, 243, 169, 37);
		panel.add(textField);
		
		chckbxNewCheckBox = new JCheckBox("Examinee");
		chckbxNewCheckBox.setBackground(Color.WHITE);
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chckbxNewCheckBox.setBounds(262, 298, 80, 23);
		panel.add(chckbxNewCheckBox);
		
		label_1 = new JLabel("");
		label_1.setBounds(206, 45, 195, 83);
		panel.add(label_1);
		
		label = new JLabel("\u00A9Lab Symbiotic");
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(51, 102, 102));
		label.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		label.setBounds(0, 382, 599, 24);
		panel.add(label);
	}
	public void setButtonImage(){
		try {
			Connection con=sqlConnection.dbConnection();
			ResultSet rs=con.createStatement().executeQuery("SELECT image FROM image_table WHERE rowid=6;");
			if (rs.next())
			{
				byte[] blob=rs.getBytes("image");
				ImageIcon image= new ImageIcon(blob);
				Image im=image.getImage();
				Image myimg=im.getScaledInstance(lblNewLabel.getWidth(),lblNewLabel.getHeight(),Image.SCALE_SMOOTH);
				ImageIcon icon= new ImageIcon(myimg);
				lblNewLabel.setIcon(icon);
			}
			rs.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void setLogo(){
		try {
			Connection con=sqlConnection.dbConnection();
			//PreparedStatement pstmt= con.prepareStatement("SELECT describeImage FROM test1question WHERE rowid=?;");
			//pstmt.setInt(1, 1);
			ResultSet rs=con.createStatement().executeQuery("SELECT image FROM image_table WHERE rowid=2;");
			
			if (rs.next())
			{
				byte[] blob=rs.getBytes("image");
				ImageIcon image= new ImageIcon(blob);
				Image im=image.getImage();
				Image myimg=im.getScaledInstance(label_1.getWidth(),label_1.getHeight(),Image.SCALE_SMOOTH);
				ImageIcon icon= new ImageIcon(myimg);
				label_1.setIcon(icon);
			}
			rs.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public Image setIcon(){
		try {
			Connection con=sqlConnection.dbConnection();
			//PreparedStatement pstmt= con.prepareStatement("SELECT describeImage FROM test1question WHERE rowid=?;");
			//pstmt.setInt(1, 1);
			ResultSet rs=con.createStatement().executeQuery("SELECT image FROM image_table WHERE rowid=5;");
			
			if (rs.next())
			{
				byte[] blob=rs.getBytes("image");
				ImageIcon image= new ImageIcon(blob);
				Image im=image.getImage();
				return im;
			}
			
			rs.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public static String getCurrentDate(){
		//String time= Integer.toString(Calendar.getInstance().get(Calendar.YEAR))+"-"+Integer.toString(Calendar.getInstance().get(Calendar.MONTH))+"-"+Integer.toString(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
		Date date=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String s=sdf.format(date);
		return s;
	}
	
	public void logoTextAnimation(){
		Runnable run=new Runnable(){
			public void run(){
				for (int i=0;i<i+1;i++)
				{
					try {
						TimeUnit.MILLISECONDS.sleep(250);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(i==1)
					{
						label.setForeground(new Color(25,25,112));
					}
					else if(i==2)
					{
						label.setForeground(new Color(0,0,128));
					}
					else if(i==3)
					{
						label.setForeground(new Color(0,0,205));
					}
					else if(i==4)
					{
						label.setForeground(new Color(65,105,225));
					}
					else if(i==5)
					{
						label.setForeground(new Color(138,43,226));
					}
					else if(i==6)
					{
						label.setForeground(new Color(75,0,130));
					}
					else if(i==7)
					{
						label.setForeground(new Color(72,61,139));
					}
					else if(i==8)
					{
						label.setForeground(new Color(106,90,205));
					}
					else if(i==9)
					{
						label.setForeground(new Color(147,112,219));
					}
					else if(i==10)
					{
						label.setForeground(new Color(139,0,139));
					}
					else if(i==11)
					{
						label.setForeground(new Color(148,0,211));
					}
					else if(i==12)
					{
						label.setForeground(new Color(186,85,211));
					}
					else if(i==13)
					{
						label.setForeground(new Color(128,0,128));
					}
					else if(i==14)
					{
						label.setForeground(new Color(72,61,139));
						i=0;
					}
					
				}
					
			}
		};
		Thread tr=new Thread(run);
		tr.start();
	}
}
