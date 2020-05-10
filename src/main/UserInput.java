package main;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import set1.MicCheck1;

public class UserInput {

	public JFrame frame;
	public static JTextField txtName;
	public static JTextField txtCell;
	public static JTextField txtEmail;
	private JLabel txtDate;
	public static String email;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInput window = new UserInput();
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
	public UserInput() {
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
		frame = new JFrame();
		frame.setResizable(false);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				frame.requestFocusInWindow();
				txtDate.setText(getCurrentDate());
			}
		});
		frame.setIconImage(Functions.setIcon());
		frame.setTitle("User Informations");
		frame.setBackground(Color.WHITE);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 613, 369);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(255, 255, 255));
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(new LineBorder(new Color(255, 127, 80), 2, true), " Information ",TitledBorder.CENTER, TitledBorder.TOP, new Font("Comic Sans MS",Font.BOLD,24), Color.DARK_GRAY));
		panel.setBackground(Color.WHITE);
		panel.setBounds(29, 11, 543, 302);
		frame.getContentPane().add(panel);
		
		txtName = new JTextField();
		txtName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(txtName.getText().equals(null)||txtName.getText().equals("")||txtName.getText().equalsIgnoreCase("name"))
				{
					frame.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
					txtName.setText(null);
					txtName.setForeground(Color.BLACK);
					txtName.requestFocus();
				}
				else
				{
					txtName.requestFocus();
				}
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				if(txtName.getText().equals(null)||txtName.getText().equals("")||txtName.getText().equalsIgnoreCase("name"))
				{
					frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					txtName.setForeground(Color.DARK_GRAY);
					txtName.setText("Name");
					frame.requestFocusInWindow();
				}
				else
				{
					frame.requestFocusInWindow();
				}
			}
		});
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtName.setText("Name");
		txtName.setForeground(Color.DARK_GRAY);
		txtName.setBorder(new LineBorder(new Color(255, 127, 80), 2));
		txtName.setHorizontalAlignment(SwingConstants.CENTER);
		txtName.setBounds(82, 98, 174, 43);
		panel.add(txtName);
		txtName.setColumns(10);
		
		txtCell = new JTextField();
		txtCell.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent arg0) {
				if(txtCell.getText().equals(null)||txtCell.getText().equals("")||txtCell.getText().equalsIgnoreCase("cell"))
				{
					txtCell.setForeground(Color.DARK_GRAY);
					txtCell.setText("Cell");
					frame.requestFocusInWindow();
				}
				else
				{
					frame.requestFocusInWindow();
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				
				if(txtCell.getText().equals(null)||txtCell.getText().equals("")||txtCell.getText().equalsIgnoreCase("cell"))
				{
					txtCell.setText(null);
					txtCell.setForeground(Color.BLACK);
					txtCell.requestFocus();
				}
				else
				{
					txtCell.requestFocus();
				}
			}
		});
		txtCell.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtCell.setForeground(Color.DARK_GRAY);
		txtCell.setText("Cell");
		txtCell.setHorizontalAlignment(SwingConstants.CENTER);
		txtCell.setBorder(new LineBorder(new Color(255, 127, 80), 2));
		txtCell.setColumns(10);
		txtCell.setBounds(295, 98, 174, 43);
		panel.add(txtCell);
		
		txtEmail = new JTextField();
		txtEmail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(txtEmail.getText().equals(null)||txtEmail.getText().equals("")||txtEmail.getText().equalsIgnoreCase("Email"))
				{
					txtEmail.setText(null);
					txtEmail.setForeground(Color.BLACK);
					txtEmail.requestFocus();
				}
				else
				{
					txtEmail.requestFocus();
				}
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				if(txtEmail.getText().equals(null)||txtEmail.getText().equals("")||txtEmail.getText().equalsIgnoreCase("Email"))
				{
					txtEmail.setForeground(Color.DARK_GRAY);
					txtEmail.setText("Email");
					frame.requestFocusInWindow();
				}
				else
				{
					frame.requestFocusInWindow();
				}
			}
		});
		txtEmail.setText("Email");
		txtEmail.setForeground(Color.DARK_GRAY);
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtEmail.setHorizontalAlignment(SwingConstants.CENTER);
		txtEmail.setBorder(new LineBorder(new Color(255, 127, 80), 2));
		txtEmail.setColumns(10);
		txtEmail.setBounds(82, 159, 387, 43);
		panel.add(txtEmail);
		
		JLabel lblNewLabel = new JLabel("Proceed!");
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(txtName.getText().trim().equals("name")||txtName.getText().trim().equals("")||txtName.getText().trim().equals(null)||txtCell.getText().trim().equals("Cell")||txtCell.getText().trim().equals("")||txtCell.getText().trim().equals(null)||txtEmail.getText().trim().equals("Email")||txtEmail.getText().trim().equals("")||txtEmail.getText().trim().equals(null))
				{
					
				}
				else
				{
					File user=new File("UserInfo.ser");
					if(user.exists())
					{
						try {
							FileInputStream fis=new FileInputStream(user);
							ObjectInputStream ois=new ObjectInputStream(fis);
							UserInfo ui=(UserInfo)ois.readObject();
							ois.close();
							fis.close();
							ui.name= txtName.getText().trim();
							ui.cell=txtCell.getText().trim();
							ui.email=txtEmail.getText().trim();
							ui.date=txtDate.getText().trim();
							ui.setNumber=TakeTests.setNumber;
							MicCheck1.setNumber=ui.setNumber;
							user.delete();
							FileOutputStream fos = new FileOutputStream(user,true);
							ObjectOutputStream oos=new ObjectOutputStream(new BufferedOutputStream (fos));
							oos.writeObject(ui);
							oos.close();
							fos.close();
							
							frame.dispose();
							MicCheck1 go=new MicCheck1();
							go.frame.setVisible(true);
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
					else
					{
						
						try {
							UserInfo ui=new UserInfo();
							ui.name= txtName.getText().trim();
							ui.cell=txtCell.getText().trim();
							ui.email=txtEmail.getText().trim();
							ui.date=txtDate.getText().trim();
							ui.setNumber=TakeTests.setNumber;
							FileOutputStream fos = new FileOutputStream(user,true);
							ObjectOutputStream oos=new ObjectOutputStream(new BufferedOutputStream (fos));
							oos.writeObject(ui);
							oos.close();
							fos.close();
							
							frame.dispose();
							MicCheck1 go=new MicCheck1();
							go.frame.setVisible(true);
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
						
					}
				}
				
			}
		});
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(201, 225, 153, 43);
		lblNewLabel.setBorder(new MatteBorder(2, 0, 2, 0, (Color) new Color(255, 99, 71)));
		lblNewLabel.setOpaque(true);
		panel.add(lblNewLabel);
		
		txtDate = new JLabel("Date");
		txtDate.setOpaque(true);
		txtDate.setBackground(Color.WHITE);
		txtDate.setBorder(new LineBorder(new Color(250, 128, 114), 2));
		txtDate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtDate.setHorizontalAlignment(SwingConstants.CENTER);
		txtDate.setBounds(185, 53, 174, 25);
		panel.add(txtDate);
	}
	public static String getCurrentDate(){
		//String time= Integer.toString(Calendar.getInstance().get(Calendar.YEAR))+"-"+Integer.toString(Calendar.getInstance().get(Calendar.MONTH))+"-"+Integer.toString(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
		Date date=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String s=sdf.format(date);
		return s;
	}
	
	/*public static int getWindowNo(){
		
		try {
			File answer=new File("Answer//UserInfo.ser");
			FileInputStream fis=new FileInputStream(answer);
			ObjectInputStream ois=new ObjectInputStream(fis);
			UserInfo ui=(UserInfo)ois.readObject();
			ois.close();
			fis.close();
			System.out.println(ui.status);
			if(ui.name.equalsIgnoreCase(txtName.getText().trim())&& ui.cell.equalsIgnoreCase(txtCell.getText().trim())&&ui.email.equalsIgnoreCase(txtEmail.getText().trim()))
			{
				return ui.status;
			}
			else return 0;
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
		return 0;
	}*/
}
