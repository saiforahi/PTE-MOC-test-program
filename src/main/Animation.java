package main;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class Animation {

	public JFrame frame;
	JLabel label1,label4,label5,lblNewLabel;
	private JLabel label2;
	private JLabel label3;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panel4;
	private JPanel panel5;
	License l;
	private JLabel label;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Animation window = new Animation();
					window.frame.setVisible(true);
					window.titleAnimation();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Animation() {
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
		frame.getContentPane().setBackground(new Color(0,0,0,0));
		frame.setResizable(false);
		frame.setSize(632, 417);
		frame.setLocationRelativeTo(null);
		frame.setIconImage(Functions.setIcon());
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		 label = new JLabel("");
		 label.setBounds(386, 370, 108, 32);
		 label.setHorizontalAlignment(SwingConstants.CENTER);
		 label.setForeground(Color.DARK_GRAY);
		 label.setFont(new Font("Dialog", Font.BOLD, 15));
		
		panel3 = new JPanel();
		panel3.setBounds(218, 0, 138, 417);
		panel3.setBorder(new LineBorder(SystemColor.controlDkShadow, 2, true));
		
		label3 = new JLabel("");
		label3.setForeground(Color.DARK_GRAY);
		label3.setHorizontalAlignment(SwingConstants.LEFT);
		label3.setFont(new Font("Dialog", Font.BOLD, 24));
		
		panel1 = new JPanel();
		panel1.setBounds(0, 0, 138, 417);
		panel1.setBorder(new LineBorder(SystemColor.controlDkShadow, 2, true));
		
		label1 = new JLabel("");
		label1.setForeground(Color.DARK_GRAY);
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		label1.setFont(new Font("Dialog", Font.BOLD, 24));
		
		panel4 = new JPanel();
		panel4.setBounds(356, 0, 138, 417);
		panel4.setBorder(new LineBorder(SystemColor.controlDkShadow, 2, true));
		
		label4 = new JLabel("");
		label4.setForeground(Color.DARK_GRAY);
		label4.setHorizontalAlignment(SwingConstants.LEFT);
		label4.setFont(new Font("Dialog", Font.BOLD, 24));
		
		panel2 = new JPanel();
		panel2.setBounds(138, 0, 80, 417);
		panel2.setBorder(new LineBorder(SystemColor.controlDkShadow, 2, true));
		
		label2 = new JLabel("");
		label2.setForeground(Color.DARK_GRAY);
		label2.setHorizontalAlignment(SwingConstants.LEFT);
		label2.setFont(new Font("Dialog", Font.BOLD, 24));
		
		panel5 = new JPanel();
		panel5.setBounds(494, 0, 138, 417);
		panel5.setBorder(new LineBorder(SystemColor.controlDkShadow, 2, true));
		
		label5 = new JLabel("");
		label5.setForeground(Color.DARK_GRAY);
		label5.setHorizontalAlignment(SwingConstants.LEFT);
		label5.setFont(new Font("Dialog", Font.BOLD, 24));
		GroupLayout gl_panel5 = new GroupLayout(panel5);
		gl_panel5.setHorizontalGroup(
			gl_panel5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel5.createSequentialGroup()
					.addGap(8)
					.addComponent(label5, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
					.addGap(8))
		);
		gl_panel5.setVerticalGroup(
			gl_panel5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel5.createSequentialGroup()
					.addGap(185)
					.addComponent(label5, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
		);
		panel5.setLayout(gl_panel5);
		GroupLayout gl_panel2 = new GroupLayout(panel2);
		gl_panel2.setHorizontalGroup(
			gl_panel2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel2.createSequentialGroup()
					.addGap(8)
					.addComponent(label2, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
					.addGap(8))
		);
		gl_panel2.setVerticalGroup(
			gl_panel2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel2.createSequentialGroup()
					.addGap(185)
					.addComponent(label2, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
		);
		panel2.setLayout(gl_panel2);
		GroupLayout gl_panel4 = new GroupLayout(panel4);
		gl_panel4.setHorizontalGroup(
			gl_panel4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel4.createSequentialGroup()
					.addGap(8)
					.addComponent(label4, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
					.addGap(8))
		);
		gl_panel4.setVerticalGroup(
			gl_panel4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel4.createSequentialGroup()
					.addGap(185)
					.addComponent(label4, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
		);
		panel4.setLayout(gl_panel4);
		GroupLayout gl_panel1 = new GroupLayout(panel1);
		gl_panel1.setHorizontalGroup(
			gl_panel1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel1.createSequentialGroup()
					.addGap(8)
					.addComponent(label1, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
					.addGap(8))
		);
		gl_panel1.setVerticalGroup(
			gl_panel1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel1.createSequentialGroup()
					.addGap(185)
					.addComponent(label1, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
		);
		panel1.setLayout(gl_panel1);
		GroupLayout gl_panel3 = new GroupLayout(panel3);
		gl_panel3.setHorizontalGroup(
			gl_panel3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel3.createSequentialGroup()
					.addGap(8)
					.addComponent(label3, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
					.addGap(8))
		);
		gl_panel3.setVerticalGroup(
			gl_panel3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel3.createSequentialGroup()
					.addGap(185)
					.addComponent(label3, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
		);
		panel3.setLayout(gl_panel3);
		frame.getContentPane().setLayout(null);
		
		 lblNewLabel = new JLabel("");
		 lblNewLabel.setBounds(494, 351, 115, 51);
		 lblNewLabel.setForeground(Color.DARK_GRAY);
		 lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		 lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		 frame.getContentPane().add(lblNewLabel);
		frame.getContentPane().add(panel1);
		frame.getContentPane().add(panel2);
		frame.getContentPane().add(panel3);
		frame.getContentPane().add(label);
		frame.getContentPane().add(panel4);
		frame.getContentPane().add(panel5);
	}
	
	//************************************************************** Method of animation in starting*********************************************************
	
			public void titleAnimation(){
				Runnable run=new Runnable(){
					public void run(){
						/*frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
						try {
							TimeUnit.MILLISECONDS.sleep(100);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						for (int i=0;i<5;i++)
						{
							if(i==0)
							{
								label1.setText("Center");
								panel1.setBackground(new Color(51, 153, 255));
							}
							else if(i==1)
							{
								label2.setText("For");
								panel2.setBackground(new Color(255, 102, 102));
							}
							else if(i==2)
							{
								label3.setText("Innovative");
								panel3.setBackground(new Color(255, 255, 153));
							}
							else if(i==3)
							{
								label4.setText("Language");
								panel4.setBackground(new Color(102, 204, 102));
							}
							else if(i==4)
							{
								label5.setText("Learning");
								panel5.setBackground(new Color(204, 102, 153));
							}
							try {
								TimeUnit.MILLISECONDS.sleep(500);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						try {
							lblNewLabel.setText("Powered by      LAB SYMBIOTIC");
							TimeUnit.SECONDS.sleep(3);
							frame.dispose();
							//direct login
							directLogin();
							//Login go=new Login();
							//go.frmLogin.setVisible(true);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}*/
						
						frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
						try {
							TimeUnit.MILLISECONDS.sleep(100);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						for (int i=0;i<5;i++)
						{
							if(i==0)
							{
								label1.setText("Center");
								panel1.setBackground(SystemColor.info);
							}
							else if(i==1)
							{
								label2.setForeground(Color.WHITE);
								label2.setText("For");
								panel2.setBackground(SystemColor.inactiveCaptionText);
							}
							else if(i==2)
							{
								label3.setText("Innovative");
								panel3.setBackground(SystemColor.info);
							}
							else if(i==3)
							{
								label4.setForeground(Color.WHITE);
								label4.setText("Language");
								panel4.setBackground(SystemColor.inactiveCaptionText);
							}
							else if(i==4)
							{
								label5.setText("Learning");
								panel5.setBackground(SystemColor.info);
							}
							try {
								TimeUnit.MILLISECONDS.sleep(500);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						try {
							label.setForeground(Color.WHITE);
							label.setText("Powered by");
							lblNewLabel.setText("LAB SYMBIOTIC");
							TimeUnit.SECONDS.sleep(3);
							frame.dispose();
							//direct login
							directLogin();
							//Login go=new Login();
							//go.frmLogin.setVisible(true);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				};
				Thread tr=new Thread(run);
				tr.start();
			}
			
			//************************************************************** Method of animation in ending*********************************************************
			
			public void endtitleAnimation(){
				Runnable run=new Runnable(){
					public void run(){
						/*frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
						try {
							TimeUnit.MILLISECONDS.sleep(100);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						for (int i=0;i<5;i++)
						{
							if(i==0)
							{
								label1.setText("Center");
								panel1.setBackground(new Color(51, 153, 255));
							}
							else if(i==1)
							{
								label2.setText("For");
								panel2.setBackground(new Color(255, 102, 102));
							}
							else if(i==2)
							{
								label3.setText("Innovative");
								panel3.setBackground(new Color(255, 255, 153));
							}
							else if(i==3)
							{
								label4.setText("Language");
								panel4.setBackground(new Color(102, 204, 102));
							}
							else if(i==4)
							{
								label5.setText("Learning");
								panel5.setBackground(new Color(204, 102, 153));
							}
							try {
								TimeUnit.MILLISECONDS.sleep(500);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						try {
							label.setForeground(Color.WHITE);
							label.setText("Powered by");
							lblNewLabel.setText("LAB SYMBIOTIC");
							TimeUnit.MILLISECONDS.sleep(250);
							System.exit(0);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}*/
						
						frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
						try {
							TimeUnit.MILLISECONDS.sleep(100);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						for (int i=0;i<5;i++)
						{
							if(i==0)
							{
								label1.setText("Center");
								panel1.setBackground(SystemColor.info);
							}
							else if(i==1)
							{
								label2.setForeground(Color.WHITE);
								label2.setText("For");
								panel2.setBackground(SystemColor.inactiveCaptionText);
							}
							else if(i==2)
							{
								label3.setText("Innovative");
								panel3.setBackground(SystemColor.info);
							}
							else if(i==3)
							{
								label4.setForeground(Color.WHITE);
								label4.setText("Language");
								panel4.setBackground(SystemColor.inactiveCaptionText);
							}
							else if(i==4)
							{
								label5.setText("Learning");
								panel5.setBackground(SystemColor.info);
							}
							try {
								TimeUnit.MILLISECONDS.sleep(500);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						try {
							label.setForeground(Color.WHITE);
							label.setText("Powered by");
							lblNewLabel.setText("<html><p>LAB SYMBIOTIC</p></html>");
							TimeUnit.MILLISECONDS.sleep(250);
							System.exit(0);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				};
				Thread tr=new Thread(run);
				tr.start();
			}
			
			public void directLogin() {
				boolean log=false;
				File f=new File("License.ser");
				if(f.exists())
				{
					try {
						FileInputStream fis = new FileInputStream(f);
						ObjectInputStream ois=new ObjectInputStream(fis);
						l=(License)ois.readObject();
						if(l.times<=10)
						{
							
								l.times++;
								log=true;
								frame.dispose();
								Menu goToMenu= new Menu();
								goToMenu.frame.setVisible(true);
								goToMenu.frame.setLocationRelativeTo(null);
							
						}
						else
						{
							JOptionPane.showMessageDialog(frame, "Limit Expired");
						}
						ois.close();
						fis.close();
					} catch (FileNotFoundException e) {
						JOptionPane.showMessageDialog(frame, "Limit Expired");
						e.printStackTrace();
					} catch (IOException e) {
						JOptionPane.showMessageDialog(frame,e.getMessage());
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						JOptionPane.showMessageDialog(frame,e.getMessage());
						e.printStackTrace();
					}
					
				}
				else
				{
					JOptionPane.showMessageDialog(frame, "Status not valid");
					System.exit(0);
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
						JOptionPane.showMessageDialog(frame,e.getMessage());
						e.printStackTrace();
					} catch (IOException e) {
						JOptionPane.showMessageDialog(frame,e.getMessage());
						e.printStackTrace();
					}
				}
				if(l.times>=10)
				{
					f.delete();
				}
			}
}
