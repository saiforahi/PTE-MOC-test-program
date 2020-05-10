package main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import main.Functions;
import main.Menu;
import main.UserInfo;
import main.sqlConnection;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CompleteWindow {

	public JFrame frame;
	private JLabel lblNewLabel_1;
	private JTextPane textPane;
	public JLabel lblNewLabel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CompleteWindow window = new CompleteWindow();
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
	public CompleteWindow() {
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
		frame.getContentPane().setBackground(Color.WHITE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				userObjectStore("UserInfo.ser");
				frame.dispose();
				Menu go=new Menu();
				go.frame.setVisible(true);
			}
		});
		frame.setBounds(100, 100, 582, 461);
		frame.setIconImage(Functions.setIcon());
		frame.setIconImage(Functions.setIcon());
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(83, 30, 407, 101);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setForeground(new Color(0, 128, 128));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 31));
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(83, 142, 407, 174);
		panel.setBorder(new TitledBorder(new LineBorder(Color.gray, 2, true), "Leave Your comment here", TitledBorder.CENTER, TitledBorder.TOP,  new Font("Sakkal Majalla",Font.PLAIN,24), Color.DARK_GRAY));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		textPane = new JTextPane();
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textPane.setBounds(21, 33, 364, 123);
		panel.add(textPane);
		
		lblNewLabel_1 = new JLabel("Home!");
		lblNewLabel_1.setToolTipText("press to go menu page");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblNewLabel_1.setFont(new Font("Sakkal Majalla", Font.BOLD, 40));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				lblNewLabel_1.setFont(new Font("Sakkal Majalla", Font.BOLD, 26));
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				userObjectStore("UserInfo.ser");
				frame.dispose();
				Menu go=new Menu();
				go.frame.setVisible(true);
			}
		});
		lblNewLabel_1.setFont(new Font("Sakkal Majalla", Font.BOLD, 26));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(227, 340, 127, 42);
		lblNewLabel_1.setBorder(new LineBorder(Color.WHITE));
		frame.getContentPane().add(lblNewLabel_1);
	}
	
	 public void userObjectStore(String filename)
	 {
		 Connection conn;
		 FileInputStream fis;
		try {
			conn=sqlConnection.answerDBConnection();
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO set1SerFiles (files) VALUES(?)");
			fis = new FileInputStream(new File(filename));
			ObjectInputStream ois=new ObjectInputStream(fis);
			UserInfo l=(UserInfo)ois.readObject();
			l.comment=textPane.getText().trim();
			ois.close();
			fis.close();
		    ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    ObjectOutputStream oos = new ObjectOutputStream(baos);
		    oos.writeObject(l);
		    
		    byte[] fileAsBytes = baos.toByteArray();
		    ByteArrayInputStream bais = new ByteArrayInputStream(fileAsBytes);
		    pstmt.setBinaryStream(1, bais, fileAsBytes.length);
		    pstmt.executeUpdate();
		    pstmt.close();
		    conn.close();
		    new File(filename).delete();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	 }
}
