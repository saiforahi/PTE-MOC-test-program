package main;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Menu {

	public JFrame frame;
	public JButton btnAnswerKey;
	JLabel lblNewLabel;
	private JLabel lblNewLabel_1,lblPteAcademicCillbd;
	private JLabel lblUserManual;
	private JLabel lblTakeTests;
	private JLabel lblExit,label;
	private JPanel panel_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
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
	public Menu() {
		initialize();
		setBackground();
		logoTextAnimation();
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
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				String[] options = new String[2];
				options[0] = new String("Yes");
				options[1] = new String("No!");
				int choice=JOptionPane.showOptionDialog(frame.getContentPane(),"Are you sure to exit?!","Confirmation to proceed", JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,null,options,null);
				if(choice==0)
				{
					frame.dispose();
					//Login go=new Login();
					//go.frmLogin.setVisible(true);
					Animation go=new Animation();
					go.frame.setVisible(true);
					go.endtitleAnimation();
				}
				else frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			}
		});
		frame.setTitle("Menu");
		frame.setIconImage(Functions.setIcon());
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.getContentPane().setBackground(SystemColor.menu);
		frame.setBackground(SystemColor.menu);
		frame.setAlwaysOnTop(true);
		frame.setLocationRelativeTo(null);
		frame.setLocation(0,0);
		frame.setSize(screenSize.width, screenSize.height);
		frame.getContentPane().setPreferredSize(new Dimension(screenSize.width, screenSize.height));
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(25, 25, 112));
		panel.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		panel.setBounds(0, 0, screenSize.width, screenSize.height);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		lblPteAcademicCillbd = new JLabel("");
		lblPteAcademicCillbd.setBounds(223, 416, 552, 50);
		panel.add(lblPteAcademicCillbd);
		lblPteAcademicCillbd.setVerticalAlignment(SwingConstants.TOP);
		lblPteAcademicCillbd.setForeground(new Color(204, 255, 102));
		lblPteAcademicCillbd.setFont(new Font("Segoe Script", Font.BOLD, 24));
		
		panel_1 = new JPanel();
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				panel_1.setBorder(new TitledBorder(new LineBorder(Color.WHITE, 4, true), "Menu", TitledBorder.CENTER, TitledBorder.TOP,  new Font("Sakkal Majalla",Font.PLAIN,44), Color.WHITE));
				lblNewLabel_1.setForeground(new Color(255, 255, 255));
				lblUserManual.setForeground(new Color(255, 255, 255));
				lblTakeTests.setForeground(new Color(255, 255, 255));
				lblExit.setForeground(new Color(255, 102, 0));
				
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				panel_1.setBorder(new TitledBorder(new LineBorder(Color.DARK_GRAY, 4, true), "Menu", TitledBorder.CENTER, TitledBorder.TOP,  new Font("Sakkal Majalla",Font.PLAIN,44), Color.DARK_GRAY));
				lblNewLabel_1.setForeground(Color.DARK_GRAY);
				lblUserManual.setForeground(Color.DARK_GRAY);
				lblTakeTests.setForeground(Color.DARK_GRAY);
				lblExit.setForeground(Color.DARK_GRAY);
			}
		});
		panel_1.setForeground(Color.WHITE);
		panel_1.setBorder(new TitledBorder(new LineBorder(Color.DARK_GRAY, 4, true), "Menu", TitledBorder.CENTER, TitledBorder.TOP,  new Font("Sakkal Majalla",Font.PLAIN,44), Color.DARK_GRAY));
		panel_1.setBounds(1461, 116, 296, 333);
		panel_1.setBackground(new Color(0,0,0,0));
		panel_1.setOpaque(false);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		lblExit = new JLabel("Exit!");
		lblExit.setBounds(42, 244, 210, 47);
		panel_1.add(lblExit);
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.dispose();
				//Login go=new Login();
				//go.frmLogin.setVisible(true);
				Animation go=new Animation();
				go.frame.setVisible(true);
				go.endtitleAnimation();
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				lblExit.setFont(new Font("Sakkal Majalla", Font.BOLD, 53));
				panel_1.setBorder(new TitledBorder(new LineBorder(Color.WHITE, 4, true), "Menu", TitledBorder.CENTER, TitledBorder.TOP,  new Font("Sakkal Majalla",Font.PLAIN,44), Color.WHITE));
				lblNewLabel_1.setForeground(new Color(255, 255, 255));
				lblUserManual.setForeground(new Color(255, 255, 255));
				lblTakeTests.setForeground(new Color(255, 255, 255));
				lblExit.setForeground(new Color(255, 102, 0));
			}
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				lblExit.setFont(new Font("Sakkal Majalla", Font.BOLD, 33));
			}
		});
		lblExit.setHorizontalAlignment(SwingConstants.CENTER);
		lblExit.setForeground(Color.DARK_GRAY);
		lblExit.setFont(new Font("Sakkal Majalla", Font.BOLD, 33));
		lblExit.setBorder(null);
		
		lblUserManual = new JLabel("User Manual");
		lblUserManual.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				lblUserManual.setFont(new Font("Sakkal Majalla", Font.BOLD, 37));
				panel_1.setBorder(new TitledBorder(new LineBorder(Color.WHITE, 4, true), "Menu", TitledBorder.CENTER, TitledBorder.TOP,  new Font("Sakkal Majalla",Font.PLAIN,44), Color.WHITE));
				lblNewLabel_1.setForeground(new Color(255, 255, 255));
				lblUserManual.setForeground(new Color(255, 255, 255));
				lblTakeTests.setForeground(new Color(255, 255, 255));
				lblExit.setForeground(new Color(255, 102, 0));
			}
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				lblUserManual.setFont(new Font("Sakkal Majalla", Font.BOLD, 27));
			}
		});
		lblUserManual.setBounds(42, 131, 210, 38);
		panel_1.add(lblUserManual);
		lblUserManual.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserManual.setForeground(Color.DARK_GRAY);
		lblUserManual.setFont(new Font("Sakkal Majalla", Font.BOLD, 27));
		lblUserManual.setBorder(null);
		
		lblTakeTests = new JLabel("Take Tests");
		lblTakeTests.setBounds(42, 180, 210, 38);
		panel_1.add(lblTakeTests);
		lblTakeTests.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.dispose();
				TakeTests go= new TakeTests();
				go.frmTakeTests.setVisible(true);
				go.frmTakeTests.setExtendedState(JFrame.MAXIMIZED_BOTH);
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				lblTakeTests.setFont(new Font("Sakkal Majalla", Font.BOLD, 45));
				panel_1.setBorder(new TitledBorder(new LineBorder(Color.WHITE, 4, true), "Menu", TitledBorder.CENTER, TitledBorder.TOP,  new Font("Sakkal Majalla",Font.PLAIN,44), Color.WHITE));
				lblNewLabel_1.setForeground(new Color(255, 255, 255));
				lblUserManual.setForeground(new Color(255, 255, 255));
				lblTakeTests.setForeground(new Color(255, 255, 255));
				lblExit.setForeground(new Color(255, 102, 0));
			}
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				lblTakeTests.setFont(new Font("Sakkal Majalla", Font.BOLD, 35));
			}
		});
		lblTakeTests.setHorizontalAlignment(SwingConstants.CENTER);
		lblTakeTests.setForeground(Color.DARK_GRAY);
		lblTakeTests.setFont(new Font("Sakkal Majalla", Font.BOLD, 35));
		
		lblNewLabel_1 = new JLabel("Registration");
		lblNewLabel_1.setBounds(42, 82, 210, 38);
		panel_1.add(lblNewLabel_1);
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Registration go=new Registration();
				go.frame.setVisible(true);
				go.frame.setTitle("Voucher generator");
				go.frame.setAlwaysOnTop(true);
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				lblNewLabel_1.setFont(new Font("Sakkal Majalla", Font.BOLD, 37));
				panel_1.setBorder(new TitledBorder(new LineBorder(Color.WHITE, 4, true), "Menu", TitledBorder.CENTER, TitledBorder.TOP,  new Font("Sakkal Majalla",Font.PLAIN,44), Color.WHITE));
				lblNewLabel_1.setForeground(new Color(255, 255, 255));
				lblUserManual.setForeground(new Color(255, 255, 255));
				lblTakeTests.setForeground(new Color(255, 255, 255));
				lblExit.setForeground(new Color(255, 102, 0));
			}
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				lblNewLabel_1.setFont(new Font("Sakkal Majalla", Font.BOLD, 27));
			}
		});
		lblNewLabel_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBorder(null);
		lblNewLabel_1.setFont(new Font("Sakkal Majalla", Font.BOLD, 27));
		
		label = new JLabel("Lab Symbiotic");
		label.setHorizontalAlignment(SwingConstants.TRAILING);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
		label.setBounds(638, 347, 147, 23);
		panel.add(label);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setForeground(new Color(0, 51, 0));
		lblNewLabel.setBounds(0, 0, 1915, 1080);
		//lblNewLabel.setSize(screenSize.width,screenSize.height-10);
		panel.add(lblNewLabel);
	}
	
	public void setBackground(){
		try {
			Connection con=sqlConnection.dbConnection();
			//PreparedStatement pstmt= con.prepareStatement("SELECT describeImage FROM test1question WHERE rowid=?;");
			//pstmt.setInt(1, 1);
			ResultSet rs=con.createStatement().executeQuery("SELECT image FROM image_table WHERE rowid=4;");
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
	
	
	
	/******************************************** logo text animation ****************************/
	
	public void logoTextAnimation(){
		Runnable run=new Runnable(){
			public void run(){
				for (int i=0;i<i+1;i++)
				{
					if(i==1)
					{
						lblPteAcademicCillbd.setText("C");
						//lblTakeTests.setBorder(new LineBorder(new Color(0,0,102), 3));
					}
					else if(i==2)
					{
						lblPteAcademicCillbd.setText("Ce");
					}
					else if(i==3)
					{
						lblPteAcademicCillbd.setText("Cen");
					}
					else if(i==4)
					{
						lblPteAcademicCillbd.setText("Cent");
					}
					else if(i==5)
					{
						lblPteAcademicCillbd.setText("Cente");
						
					}
					else if(i==6)
					{
						lblPteAcademicCillbd.setText("Center");
					}
					else if(i==7)
					{
						try {
							TimeUnit.MILLISECONDS.sleep(400);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						lblPteAcademicCillbd.setText("Center F");
					}
					else if(i==8)
					{
						lblPteAcademicCillbd.setText("Center Fo");
					}
					else if(i==9)
					{
						lblPteAcademicCillbd.setText("Center For");
					}
					else if(i==10)
					{
						try {
							TimeUnit.MILLISECONDS.sleep(400);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						lblPteAcademicCillbd.setText("Center For I");
					}
					else if(i==11)
					{
						lblPteAcademicCillbd.setText("Center For In");
					}
					else if(i==12)
					{
						lblPteAcademicCillbd.setText("Center For Inn");
					}
					else if(i==13)
					{
						lblPteAcademicCillbd.setText("Center For Inno");
					}
					else if(i==14)
					{
						lblPteAcademicCillbd.setText("Center For Innov");
					}
					else if(i==15)
					{
						lblPteAcademicCillbd.setText("Center For Innova");
					}
					else if(i==16)
					{
						lblPteAcademicCillbd.setText("Center For Innovat");
					}
					else if(i==17)
					{
						lblPteAcademicCillbd.setText("Center For Innovati");
					}
					else if(i==18)
					{
						lblPteAcademicCillbd.setText("Center For Innovativ");
					}
					else if(i==19)
					{
						lblPteAcademicCillbd.setText("Center For Innovative");
					}
					else if(i==20)
					{
						try {
							TimeUnit.MILLISECONDS.sleep(400);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						lblPteAcademicCillbd.setText("Center For Innovative L");
					}
					else if(i==21)
					{
						lblPteAcademicCillbd.setText("Center For Innovative La");
					}
					else if(i==22)
					{
						lblPteAcademicCillbd.setText("Center For Innovative Lan");
					}
					else if(i==23)
					{
						lblPteAcademicCillbd.setText("Center For Innovative Lang");
						
					}
					else if(i==24)
					{
						lblPteAcademicCillbd.setText("Center For Innovative Langu");
					}
					else if(i==25)
					{
						lblPteAcademicCillbd.setText("Center For Innovative Langua");
					}
					else if(i==26)
					{
						lblPteAcademicCillbd.setText("Center For Innovative Languag");
					}
					else if(i==27)
					{
						lblPteAcademicCillbd.setText("Center For Innovative Language");
					}
					else if(i==28)
					{
						try {
							TimeUnit.MILLISECONDS.sleep(400);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						lblPteAcademicCillbd.setText("Center For Innovative Language L");
					}
					else if(i==29)
					{
						lblPteAcademicCillbd.setText("Center For Innovative Language Le");
					}
					else if(i==30)
					{
						lblPteAcademicCillbd.setText("Center For Innovative Language Lea");
					}
					else if(i==31)
					{
						lblPteAcademicCillbd.setText("Center For Innovative Language Lear");
					}
					else if(i==32)
					{
						lblPteAcademicCillbd.setText("Center For Innovative Language Learn");
					}
					else if(i==33)
					{
						lblPteAcademicCillbd.setText("Center For Innovative Language Learni");
					}
					else if(i==34)
					{
						lblPteAcademicCillbd.setText("Center For Innovative Language Learnin");
					}
					else if(i==35)
					{
						lblPteAcademicCillbd.setText("Center For Innovative Language Learning");
						try {
							TimeUnit.MILLISECONDS.sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						i=0;
						lblPteAcademicCillbd.setText(null);
					}
					
					try {
						TimeUnit.MILLISECONDS.sleep(80);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}	
			}
		};
		Thread design=new Thread(run);
		design.start();
	}
}


