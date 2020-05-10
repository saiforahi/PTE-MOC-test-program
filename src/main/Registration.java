package main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

public class Registration {

	public JFrame frame;
	JLabel set1voucher,set2voucher;
	private JTextField textField;
	private JPasswordField passwordField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registration window = new Registration();
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
	public Registration() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Registration");
		frame.setIconImage(Functions.setIcon());
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBackground(Color.WHITE);
		frame.setBounds(100, 100, 745, 334);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), " Voucher ",TitledBorder.CENTER, TitledBorder.TOP, new Font("Comic Sans MS",Font.PLAIN,24), Color.black));
		panel.setBackground(Color.WHITE);
		panel.setBounds(379, 11, 325, 264);
		frame.getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("Set 1");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(38, 68, 101, 30);
		panel.add(lblNewLabel);
		
		set1voucher = new JLabel("");
		set1voucher.setBackground(Color.WHITE);
		set1voucher.setForeground(new Color(165, 42, 42));
		set1voucher.setFont(new Font("Tahoma", Font.BOLD, 15));
		set1voucher.setBorder(new MatteBorder(1, 0, 1, 0, (Color) new Color(178, 34, 34)));
		set1voucher.setOpaque(true);
		set1voucher.setHorizontalAlignment(SwingConstants.CENTER);
		set1voucher.setBounds(160, 68, 130, 30);
		panel.add(set1voucher);
		
		JLabel lblNewLabel_2 = new JLabel("Generate");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setVoucher(set1voucher);
				setVoucher(set2voucher);
			}
		});
		lblNewLabel_2.setBackground(Color.WHITE);
		lblNewLabel_2.setForeground(new Color(178, 34, 34));
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.setBorder(new MatteBorder(0, 2, 0, 2, (Color) new Color(178, 34, 34)));
		lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(108, 208, 114, 33);
		panel.add(lblNewLabel_2);
		
		JLabel lblSet = new JLabel("Set 2");
		lblSet.setHorizontalAlignment(SwingConstants.CENTER);
		lblSet.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSet.setBounds(38, 116, 101, 30);
		panel.add(lblSet);
		
		set2voucher = new JLabel("");
		set2voucher.setOpaque(true);
		set2voucher.setHorizontalAlignment(SwingConstants.CENTER);
		set2voucher.setForeground(new Color(165, 42, 42));
		set2voucher.setFont(new Font("Tahoma", Font.BOLD, 15));
		set2voucher.setBorder(new MatteBorder(1, 0, 1, 0, (Color) new Color(178, 34, 34)));
		set2voucher.setBackground(Color.WHITE);
		set2voucher.setBounds(160, 116, 130, 30);
		panel.add(set2voucher);
		
		JLabel lblRegister = new JLabel("register");
		lblRegister.setBounds(127, 196, 114, 33);
		frame.getContentPane().add(lblRegister);
		lblRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(set1voucher.getText().trim().equalsIgnoreCase(null)||set1voucher.getText().trim().equalsIgnoreCase("")||set2voucher.getText().trim().equalsIgnoreCase(null)||set1voucher.getText().trim().equalsIgnoreCase(""))
				{
					JOptionPane.showMessageDialog(frame, "No voucher has been generated");
				}
				else
				{
					if(textField.getText().trim().equalsIgnoreCase("wasi")&&new String(passwordField.getPassword()).equalsIgnoreCase("cill2018"))
					{
						try {
							File license=new File("License.ser");
							FileInputStream fis = new FileInputStream(license);
							ObjectInputStream ois=new ObjectInputStream(fis);
							License l=(License)ois.readObject();
							l.set1voucher=set1voucher.getText().trim();
							l.set2voucher=set2voucher.getText().trim();
							ois.close();
							fis.close();
							license.delete();
							FileOutputStream fos=new FileOutputStream(new File("License.ser"));
							ObjectOutputStream oos=new ObjectOutputStream(fos);
							oos.writeObject(l);
							oos.close();
							fos.close();
							frame.dispose();
							JOptionPane.showMessageDialog(frame, "Voucher code saved!");
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
		lblRegister.setOpaque(true);
		lblRegister.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegister.setForeground(new Color(178, 34, 34));
		lblRegister.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		lblRegister.setBorder(new MatteBorder(0, 2, 0, 2, (Color) new Color(178, 34, 34)));
		lblRegister.setBackground(Color.WHITE);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setColumns(10);
		textField.setBounds(183, 82, 83, 31);
		frame.getContentPane().add(textField);
		
		JLabel label = new JLabel("User Name");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setForeground(Color.DARK_GRAY);
		label.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		label.setBounds(90, 82, 83, 31);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Password");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setForeground(Color.DARK_GRAY);
		label_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		label_1.setBounds(90, 124, 83, 31);
		frame.getContentPane().add(label_1);
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("Type password here");
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordField.setBounds(183, 124, 83, 31);
		frame.getContentPane().add(passwordField);
	}
	
	public void setVoucher(JLabel label)
	{
		String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuilder builder = new StringBuilder();
		int count=5;
		while (count-- != 0) 
		{
			int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		if(label.getText().equalsIgnoreCase(builder.toString()))
		{
			while (count++ != 5) 
			{
				int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
				builder.append(ALPHA_NUMERIC_STRING.charAt(character));
			}
		}
		label.setText(builder.toString());
	}
}
