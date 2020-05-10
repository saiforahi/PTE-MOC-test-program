package set2Listening;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import main.Functions;
import main.ListeningAnswer;
import main.Menu;
import main.sqlConnection;

public class Set2HighIncrrctWord1 {

	public JFrame frame;
	JLabel lblOrganized;
	JLabel lblBy;
	JLabel label1;
	ArrayList<String> highlightCorrectWord=new ArrayList<String>();
	JLabel lblDevice;
	JLabel lblPlanes;
	JLabel lblBasically;
	JLabel lblWay;
	JLabel statusLabel;
	JProgressBar progressBar;
	public JLabel lblWords,lblEnter,lblChairs, lblForming,lblPlay,lblNose;
	Player p;
	public int val=4;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Set2HighIncrrctWord1 window = new Set2HighIncrrctWord1();
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
	public Set2HighIncrrctWord1() {
		initialize();
		timeCount();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.put("progressBar.foreground", Color.ORANGE);
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
					File answer=new File("Answer//ListeningAns.ser");
					answer.delete();
					Connection conn=sqlConnection.answerDBConnection();
					try {
						conn.createStatement().executeUpdate("DELETE FROM set2speaking;");
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(p!=null)
					{
						p.close();
						p=null;
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
		frame.setBackground(Color.DARK_GRAY);
		frame.setIconImage(Functions.setIcon());
		frame.setTitle("Listening Test");
		frame.setFocusable(true);
		frame.setSize(new Dimension(screenSize.width, screenSize.height));
		frame.getContentPane().setPreferredSize(new Dimension(screenSize.width, screenSize.height));
		frame.setAlwaysOnTop(true);
		frame.setResizable(false);
		frame.setLocation(0,0);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1370, 56);
		frame.getContentPane().add(panel);
		
		JLabel lblListeningTest = new JLabel("Listening Test");
		lblListeningTest.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblListeningTest.setBounds(10, 0, 506, 56);
		panel.add(lblListeningTest);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(255, 140, 0));
		panel_1.setBounds(0, 55, 1370, 33);
		frame.getContentPane().add(panel_1);
		
		JLabel label_1 = new JLabel("You will hear a recording. Below is the transcription of recording. Some words in the transcription differ from what the speaker(s) said. Please click on the words that are different.");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_1.setBounds(86, 111, 1188, 33);
		frame.getContentPane().add(label_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setLayout(null);
		panel_2.setBorder(new LineBorder(new Color(255, 228, 181), 2, true));
		panel_2.setBounds(542, 186, 277, 148);
		frame.getContentPane().add(panel_2);
		
		progressBar = new JProgressBar();
		progressBar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		progressBar.setStringPainted(true);
		progressBar.setBorder(new LineBorder(new Color(244, 164, 96), 1, true));
		progressBar.setIndeterminate(true);
		progressBar.setString("");
		progressBar.setForeground(new Color(244, 164, 96));
		progressBar.setBackground(Color.WHITE);
		progressBar.setBounds(22, 104, 234, 26);
		panel_2.add(progressBar);
		
		JLabel label_2 = new JLabel("Status");
		label_2.setForeground(Color.DARK_GRAY);
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_2.setBounds(93, 11, 85, 30);
		panel_2.add(label_2);
		
		statusLabel = new JLabel("beginning in 4 seconds");
		statusLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		statusLabel.setBounds(67, 52, 152, 27);
		panel_2.add(statusLabel);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new LineBorder(Color.DARK_GRAY, 2));
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(134, 615, 154, 33);
		frame.getContentPane().add(panel_3);
		
		JLabel lblItemNo = new JLabel("Item no 13 of 17");
		lblItemNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblItemNo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblItemNo.setBounds(10, 0, 144, 33);
		panel_3.add(lblItemNo);
		
		JButton button_2 = new JButton("Next");
		button_2.setBackground(Color.WHITE);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					fileSaver();
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(p!=null)
				{
					p.close();
					p=null;
				}
				frame.dispose();
				Set2Dictation1 go= new Set2Dictation1();
				go.frame.setVisible(true);
			}
		});
		button_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_2.setEnabled(true);
		button_2.setBounds(1165, 615, 89, 28);
		frame.getContentPane().add(button_2);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setBorder(new LineBorder(new Color(64, 64, 64), 1, true));
		panel_4.setBounds(134, 364, 1120, 213);
		frame.getContentPane().add(panel_4);
		panel_4.setLayout(null);
		
		lblOrganized = new JLabel("Okay.");
		lblOrganized.setBounds(19, 11, 42, 20);
		lblOrganized.setOpaque(true);
		panel_4.add(lblOrganized);
		lblOrganized.setForeground(new Color(0, 0, 0));
		lblOrganized.setBackground(Color.white);
		lblOrganized.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblOrganized.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		lblBy = new JLabel("Now I");
		lblBy.setBackground(Color.white);
		lblBy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblBy.setBounds(64, 11, 41, 20);
		panel_4.add(lblBy);
		lblBy.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblYourRadioCannot = new JLabel("don't");
		lblYourRadioCannot.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblYourRadioCannot.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblYourRadioCannot.setBounds(111, 11, 34, 20);
		panel_4.add(lblYourRadioCannot);
		
		JLabel lblRadioTo = new JLabel("want");
		lblRadioTo.setOpaque(true);
		lblRadioTo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblRadioTo.setBackground(Color.white);
		lblRadioTo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRadioTo.setBounds(152, 11, 34, 20);
		panel_4.add(lblRadioTo);
		
		JLabel lblListenTo = new JLabel("attention");
		lblListenTo.setBackground(Color.white);
		lblListenTo.setOpaque(true);
		lblListenTo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblListenTo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblListenTo.setBounds(570, 11, 59, 20);
		panel_4.add(lblListenTo);
		
		JLabel lblAllFrequencies = new JLabel("that");
		lblAllFrequencies.setOpaque(true);
		lblAllFrequencies.setBackground(Color.white);
		lblAllFrequencies.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblAllFrequencies.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAllFrequencies.setBounds(635, 11, 29, 20);
		panel_4.add(lblAllFrequencies);
		
		JLabel lblBecause = new JLabel("the");
		lblBecause.setOpaque(true);
		lblBecause.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblBecause.setBackground(Color.white);
		lblBecause.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBecause.setBounds(670, 11, 21, 20);
		panel_4.add(lblBecause);
		
		JLabel lblYour = new JLabel("person");
		lblYour.setBackground(Color.white);
		lblYour.setOpaque(true);
		lblYour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblYour.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblYour.setBounds(701, 11, 44, 20);
		panel_4.add(lblYour);
		
		lblDevice = new JLabel("your");
		lblDevice.setOpaque(true);
		lblDevice.setBackground(Color.white);
		lblDevice.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
					highlightCorrectWord.add(lblDevice.getText().trim());
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
					highlightCorrectWord.remove(lblDevice.getText().trim());
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblDevice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDevice.setBounds(771, 11, 29, 20);
		panel_4.add(lblDevice);
		
		label1 = new JLabel("a liar.");
		label1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
					highlightCorrectWord.add(label1.getText().trim());
					System.out.println(highlightCorrectWord.size());
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
					highlightCorrectWord.remove(label1.getText().trim());
					System.out.println(highlightCorrectWord.size());
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label1.setOpaque(true);
		label1.setBackground(Color.white);
		label1.setBounds(858, 11, 35, 20);
		panel_4.add(label1);
		
		JLabel label_4 = new JLabel("is");
		label_4.setOpaque(true);
		label_4.setBackground(Color.white);
		label_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_4.setBounds(842, 11, 9, 20);
		panel_4.add(label_4);
		
		JLabel lblItIsNo = new JLabel("Also,");
		lblItIsNo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblItIsNo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblItIsNo.setOpaque(true);
		lblItIsNo.setBackground(Color.white);
		lblItIsNo.setBounds(903, 11, 32, 20);
		panel_4.add(lblItIsNo);
		
		JLabel lblAnd = new JLabel("sitting");
		lblAnd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblAnd.setForeground(Color.BLACK);
		lblAnd.setOpaque(true);
		lblAnd.setBackground(Color.white);
		lblAnd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAnd.setBounds(194, 37, 38, 20);
		panel_4.add(lblAnd);
		
		JLabel lblThe = new JLabel("your");
		lblThe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblThe.setForeground(Color.BLACK);
		lblThe.setOpaque(true);
		lblThe.setBackground(Color.white);
		lblThe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblThe.setBounds(255, 37, 29, 20);
		panel_4.add(lblThe);
		
		JLabel lblThing = new JLabel("We're");
		lblThing.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblThing.setForeground(Color.BLACK);
		lblThing.setOpaque(true);
		lblThing.setBackground(Color.white);
		lblThing.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblThing.setBounds(430, 37, 38, 20);
		panel_4.add(lblThing);
		
		JLabel lblInQuantum = new JLabel("all liars.");
		lblInQuantum.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblInQuantum.setForeground(Color.BLACK);
		lblInQuantum.setOpaque(true);
		lblInQuantum.setBackground(Color.white);
		lblInQuantum.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblInQuantum.setBounds(475, 37, 47, 20);
		panel_4.add(lblInQuantum);
		
		JLabel lblPhysics = new JLabel("What");
		lblPhysics.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblPhysics.setForeground(Color.BLACK);
		lblPhysics.setOpaque(true);
		lblPhysics.setBackground(Color.white);
		lblPhysics.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPhysics.setBounds(529, 37, 38, 20);
		panel_4.add(lblPhysics);
		
		JLabel lblWe = new JLabel("I'm");
		lblWe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblWe.setForeground(Color.BLACK);
		lblWe.setOpaque(true);
		lblWe.setBackground(Color.white);
		lblWe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblWe.setBounds(570, 37, 25, 20);
		panel_4.add(lblWe);
		
		JLabel lblConsist = new JLabel("gonna");
		lblConsist.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblConsist.setForeground(Color.BLACK);
		lblConsist.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblConsist.setOpaque(true);
		lblConsist.setBackground(Color.white);
		lblConsist.setBounds(598, 37, 42, 20);
		panel_4.add(lblConsist);
		
		JLabel lblOfAtoms = new JLabel("today");
		lblOfAtoms.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblOfAtoms.setForeground(Color.BLACK);
		lblOfAtoms.setBackground(Color.white);
		lblOfAtoms.setOpaque(true);
		lblOfAtoms.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblOfAtoms.setBounds(668, 37, 38, 20);
		panel_4.add(lblOfAtoms);
		
		JLabel lblOurAtoms = new JLabel("is");
		lblOurAtoms.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblOurAtoms.setForeground(Color.BLACK);
		lblOurAtoms.setOpaque(true);
		lblOurAtoms.setBackground(Color.white);
		lblOurAtoms.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblOurAtoms.setBounds(711, 37, 9, 20);
		panel_4.add(lblOurAtoms);
		
		JLabel lblVibrate = new JLabel("show");
		lblVibrate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblVibrate.setForeground(Color.BLACK);
		lblVibrate.setOpaque(true);
		lblVibrate.setBackground(Color.white);
		lblVibrate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblVibrate.setBounds(803, 37, 34, 20);
		panel_4.add(lblVibrate);
		
		JLabel lblBut = new JLabel("you");
		lblBut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblBut.setForeground(Color.BLACK);
		lblBut.setOpaque(true);
		lblBut.setBackground(Color.white);
		lblBut.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBut.setBounds(843, 37, 24, 20);
		panel_4.add(lblBut);
		
		JLabel lblThey = new JLabel("the");
		lblThey.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblThey.setForeground(Color.BLACK);
		lblThey.setOpaque(true);
		lblThey.setBackground(Color.white);
		lblThey.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblThey.setBounds(911, 37, 24, 20);
		panel_4.add(lblThey);
		
		JLabel lblResearch = new JLabel("research");
		lblResearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblResearch.setForeground(Color.BLACK);
		lblResearch.setOpaque(true);
		lblResearch.setBackground(Color.white);
		lblResearch.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblResearch.setBounds(938, 37, 56, 20);
		panel_4.add(lblResearch);
		
		JLabel lblSays = new JLabel("says");
		lblSays.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblSays.setForeground(Color.BLACK);
		lblSays.setOpaque(true);
		lblSays.setBackground(Color.white);
		lblSays.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSays.setBounds(1000, 37, 31, 20);
		panel_4.add(lblSays);
		
		JLabel lblHow = new JLabel("how");
		lblHow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblHow.setForeground(Color.BLACK);
		lblHow.setOpaque(true);
		lblHow.setBackground(Color.white);
		lblHow.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblHow.setBounds(101, 63, 28, 20);
		panel_4.add(lblHow);
		
		lblPlanes = new JLabel("you");
		lblPlanes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
					highlightCorrectWord.add(lblPlanes.getText().trim());
				
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
					highlightCorrectWord.remove(lblPlanes.getText().trim());
					
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblPlanes.setForeground(Color.BLACK);
		lblPlanes.setOpaque(true);
		lblPlanes.setBackground(Color.white);
		lblPlanes.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPlanes.setBounds(136, 63, 24, 20);
		panel_4.add(lblPlanes);
		
		JLabel lblHave = new JLabel("a lie");
		lblHave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblHave.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblHave.setOpaque(true);
		lblHave.setBackground(Color.white);
		lblHave.setBounds(252, 63, 25, 20);
		panel_4.add(lblHave);
		
		JLabel lblDecoupled = new JLabel("become");
		lblDecoupled.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblDecoupled.setForeground(Color.BLACK);
		lblDecoupled.setOpaque(true);
		lblDecoupled.setBackground(Color.white);
		lblDecoupled.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDecoupled.setBounds(194, 63, 54, 20);
		panel_4.add(lblDecoupled);
		
		JLabel lblFrom = new JLabel("spotter");
		lblFrom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblFrom.setForeground(Color.BLACK);
		lblFrom.setOpaque(true);
		lblFrom.setBackground(Color.white);
		lblFrom.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFrom.setBounds(284, 63, 50, 20);
		panel_4.add(lblFrom);
		
		JLabel lblThem = new JLabel("and");
		lblThem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblThem.setForeground(Color.BLACK);
		lblThem.setOpaque(true);
		lblThem.setBackground(Color.white);
		lblThem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblThem.setBounds(337, 63, 25, 20);
		panel_4.add(lblThem);
		
		JLabel lblThem_1 = new JLabel("might");
		lblThem_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblThem_1.setForeground(Color.BLACK);
		lblThem_1.setBackground(Color.white);
		lblThem_1.setOpaque(true);
		lblThem_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblThem_1.setBounds(428, 63, 39, 20);
		panel_4.add(lblThem_1);
		
		JLabel lblSoIn = new JLabel("to go");
		lblSoIn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblSoIn.setForeground(Color.BLACK);
		lblSoIn.setOpaque(true);
		lblSoIn.setBackground(Color.white);
		lblSoIn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSoIn.setBounds(505, 63, 34, 20);
		panel_4.add(lblSoIn);
		
		JLabel lblExtra = new JLabel("extra");
		lblExtra.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblExtra.setForeground(Color.BLACK);
		lblExtra.setBackground(Color.white);
		lblExtra.setOpaque(true);
		lblExtra.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblExtra.setBounds(570, 63, 34, 20);
		panel_4.add(lblExtra);
		
		lblWords = new JLabel("distance");
		lblWords.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
					highlightCorrectWord.add(lblWords.getText().trim());
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
					highlightCorrectWord.remove(lblWords.getText().trim());
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblWords.setForeground(Color.BLACK);
		lblWords.setBackground(Color.white);
		lblWords.setOpaque(true);
		lblWords.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblWords.setBounds(610, 63, 53, 20);
		panel_4.add(lblWords);
		
		lblBasically = new JLabel("seeking,");
		lblBasically.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
					highlightCorrectWord.add(lblBasically.getText().trim());
					
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
					highlightCorrectWord.remove(lblBasically.getText().trim());
					
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblBasically.setForeground(Color.BLACK);
		lblBasically.setOpaque(true);
		lblBasically.setBackground(Color.white);
		lblBasically.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBasically.setBounds(894, 63, 53, 20);
		panel_4.add(lblBasically);
		
		JLabel lblOfOur = new JLabel("Now,");
		lblOfOur.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblOfOur.setForeground(Color.BLACK);
		lblOfOur.setOpaque(true);
		lblOfOur.setBackground(Color.white);
		lblOfOur.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblOfOur.setBounds(10, 95, 33, 20);
		panel_4.add(lblOfOur);
		
		JLabel lblBrain = new JLabel("speaking");
		lblBrain.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblBrain.setForeground(Color.BLACK);
		lblBrain.setOpaque(true);
		lblBrain.setBackground(Color.white);
		lblBrain.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBrain.setBounds(49, 95, 56, 20);
		panel_4.add(lblBrain);
		
		JLabel lblEliciting = new JLabel("trust,");
		lblEliciting.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblEliciting.setForeground(Color.BLACK);
		lblEliciting.setBackground(Color.white);
		lblEliciting.setOpaque(true);
		lblEliciting.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEliciting.setBounds(133, 95, 35, 20);
		panel_4.add(lblEliciting);
		
		JLabel lblMemories = new JLabel("ever");
		lblMemories.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblMemories.setForeground(Color.BLACK);
		lblMemories.setBackground(Color.white);
		lblMemories.setOpaque(true);
		lblMemories.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMemories.setBounds(174, 95, 32, 20);
		panel_4.add(lblMemories);
		
		JLabel lblAnd_1 = new JLabel("since");
		lblAnd_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblAnd_1.setForeground(Color.BLACK);
		lblAnd_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAnd_1.setBounds(208, 95, 33, 20);
		panel_4.add(lblAnd_1);
		
		JLabel lblFragments = new JLabel("I wrote");
		lblFragments.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblFragments.setForeground(Color.BLACK);
		lblFragments.setBackground(Color.white);
		lblFragments.setOpaque(true);
		lblFragments.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFragments.setBounds(247, 95, 47, 20);
		panel_4.add(lblFragments);
		
		JLabel lblOfPrevious = new JLabel("this");
		lblOfPrevious.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblOfPrevious.setForeground(Color.BLACK);
		lblOfPrevious.setBackground(Color.white);
		lblOfPrevious.setOpaque(true);
		lblOfPrevious.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblOfPrevious.setBounds(299, 95, 23, 20);
		panel_4.add(lblOfPrevious);
		
		JLabel lblSituations = new JLabel("spotting\"");
		lblSituations.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblSituations.setForeground(Color.BLACK);
		lblSituations.setBackground(Color.white);
		lblSituations.setOpaque(true);
		lblSituations.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSituations.setBounds(399, 95, 58, 20);
		panel_4.add(lblSituations);
		
		JLabel lblHowever = new JLabel(", no");
		lblHowever.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblHowever.setForeground(Color.BLACK);
		lblHowever.setBackground(Color.white);
		lblHowever.setOpaque(true);
		lblHowever.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblHowever.setBounds(460, 95, 29, 20);
		panel_4.add(lblHowever);
		
		JLabel lblInQuantum_1 = new JLabel("one");
		lblInQuantum_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblInQuantum_1.setForeground(Color.BLACK);
		lblInQuantum_1.setOpaque(true);
		lblInQuantum_1.setBackground(Color.white);
		lblInQuantum_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblInQuantum_1.setBounds(492, 95, 25, 20);
		panel_4.add(lblInQuantum_1);
		
		JLabel lblPhysics_1 = new JLabel("meet");
		lblPhysics_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblPhysics_1.setForeground(Color.BLACK);
		lblPhysics_1.setBackground(Color.white);
		lblPhysics_1.setOpaque(true);
		lblPhysics_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPhysics_1.setBounds(585, 95, 34, 20);
		panel_4.add(lblPhysics_1);
		
		JLabel lblLike = new JLabel("like,");
		lblLike.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblLike.setForeground(Color.BLACK);
		lblLike.setOpaque(true);
		lblLike.setBackground(Color.white);
		lblLike.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLike.setBounds(456, 118, 24, 20);
		panel_4.add(lblLike);
		
		JLabel lblInSome = new JLabel("have");
		lblInSome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblInSome.setForeground(Color.BLACK);
		lblInSome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblInSome.setBounds(737, 118, 32, 20);
		panel_4.add(lblInSome);
		
		lblWay = new JLabel("on");
		lblWay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
					highlightCorrectWord.add(lblWay.getText().trim());
					System.out.println(highlightCorrectWord.size());
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
					highlightCorrectWord.remove(lblWay.getText().trim());
					System.out.println(highlightCorrectWord.size());
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblWay.setForeground(Color.BLACK);
		lblWay.setOpaque(true);
		lblWay.setBackground(Color.white);
		lblWay.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblWay.setBounds(831, 118, 18, 20);
		panel_4.add(lblWay);
		
		JLabel lblUniverses = new JLabel("focused");
		lblUniverses.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblUniverses.setForeground(Color.BLACK);
		lblUniverses.setOpaque(true);
		lblUniverses.setBackground(Color.white);
		lblUniverses.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUniverses.setBounds(775, 118, 52, 20);
		panel_4.add(lblUniverses);
		
		JLabel lblSurroundingUs = new JLabel("cooking.");
		lblSurroundingUs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblSurroundingUs.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSurroundingUs.setOpaque(true);
		lblSurroundingUs.setBackground(Color.white);
		lblSurroundingUs.setBounds(852, 118, 53, 20);
		panel_4.add(lblSurroundingUs);
		
		JLabel lblThe_1 = new JLabel("So,");
		lblThe_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblThe_1.setOpaque(true);
		lblThe_1.setBackground(Color.white);
		lblThe_1.setForeground(Color.BLACK);
		lblThe_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblThe_1.setBounds(10, 157, 22, 20);
		panel_4.add(lblThe_1);
		
		JLabel lblProblem = new JLabel("My");
		lblProblem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblProblem.setOpaque(true);
		lblProblem.setBackground(Color.white);
		lblProblem.setForeground(Color.BLACK);
		lblProblem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblProblem.setBounds(357, 118, 23, 20);
		panel_4.add(lblProblem);
		
		JLabel lblWe_1 = new JLabel("we");
		lblWe_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblWe_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblWe_1.setBounds(85, 157, 19, 20);
		panel_4.add(lblWe_1);
		
		JLabel lblOf = new JLabel("of");
		lblOf.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblOf.setOpaque(true);
		lblOf.setBackground(Color.white);
		lblOf.setForeground(Color.BLACK);
		lblOf.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblOf.setBounds(112, 95, 15, 20);
		panel_4.add(lblOf);
		
		JLabel lblCant = new JLabel("before");
		lblCant.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblCant.setOpaque(true);
		lblCant.setBackground(Color.white);
		lblCant.setForeground(Color.BLACK);
		lblCant.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCant.setBounds(37, 157, 43, 20);
		panel_4.add(lblCant);
		
		 lblEnter = new JLabel("going,");
		lblEnter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
					highlightCorrectWord.add(lblEnter.getText().trim());
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
					highlightCorrectWord.remove(lblEnter.getText().trim());
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblEnter.setOpaque(true);
		lblEnter.setBackground(Color.white);
		lblEnter.setForeground(Color.BLACK);
		lblEnter.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEnter.setBounds(138, 157, 41, 20);
		panel_4.add(lblEnter);
		
		JLabel lblThem_2 = new JLabel("what");
		lblThem_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblThem_2.setOpaque(true);
		lblThem_2.setBackground(Color.white);
		lblThem_2.setForeground(Color.BLACK);
		lblThem_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblThem_2.setBounds(182, 157, 35, 20);
		panel_4.add(lblThem_2);
		
		JLabel lblBecause_1 = new JLabel("husband's");
		lblBecause_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblBecause_1.setOpaque(true);
		lblBecause_1.setBackground(Color.white);
		lblBecause_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBecause_1.setBounds(384, 118, 66, 20);
		panel_4.add(lblBecause_1);
		
		JLabel lblGet_1 = new JLabel("get");
		lblGet_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblGet_1.setOpaque(true);
		lblGet_1.setBackground(Color.white);
		lblGet_1.setForeground(Color.BLACK);
		lblGet_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGet_1.setBounds(111, 157, 23, 20);
		panel_4.add(lblGet_1);
		
		JLabel lblIm = new JLabel("I'm");
		lblIm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblIm.setOpaque(true);
		lblIm.setBackground(Color.white);
		lblIm.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIm.setBounds(220, 157, 22, 20);
		panel_4.add(lblIm);
		
		JLabel lblIs_1 = new JLabel("I'm");
		lblIs_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblIs_1.setOpaque(true);
		lblIs_1.setBackground(Color.white);
		lblIs_1.setForeground(Color.BLACK);
		lblIs_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIs_1.setBounds(332, 157, 24, 20);
		panel_4.add(lblIs_1);
		
		JLabel lblMy_1 = new JLabel("my");
		lblMy_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblMy_1.setOpaque(true);
		lblMy_1.setBackground(Color.white);
		lblMy_1.setForeground(Color.BLACK);
		lblMy_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMy_1.setBounds(448, 157, 22, 20);
		panel_4.add(lblMy_1);
		
		JLabel lblTo = new JLabel("to");
		lblTo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblTo.setOpaque(true);
		lblTo.setForeground(Color.BLACK);
		lblTo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTo.setBackground(Color.WHITE);
		lblTo.setBounds(190, 11, 13, 20);
		panel_4.add(lblTo);
		
		JLabel lblAlarm = new JLabel("alarm");
		lblAlarm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblAlarm.setOpaque(true);
		lblAlarm.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAlarm.setBackground(Color.WHITE);
		lblAlarm.setBounds(209, 11, 38, 20);
		panel_4.add(lblAlarm);
		
		JLabel lblAnybody = new JLabel("anybody");
		lblAnybody.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblAnybody.setOpaque(true);
		lblAnybody.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAnybody.setBackground(Color.WHITE);
		lblAnybody.setBounds(250, 11, 57, 20);
		panel_4.add(lblAnybody);
		
		JLabel lblIn = new JLabel("in");
		lblIn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblIn.setOpaque(true);
		lblIn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIn.setBackground(Color.WHITE);
		lblIn.setBounds(313, 11, 12, 20);
		panel_4.add(lblIn);
		
		JLabel lblThis = new JLabel("this");
		lblThis.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblThis.setOpaque(true);
		lblThis.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblThis.setBackground(Color.WHITE);
		lblThis.setBounds(329, 11, 24, 20);
		panel_4.add(lblThis);
		
		JLabel lblRoom = new JLabel("room");
		lblRoom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblRoom.setOpaque(true);
		lblRoom.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRoom.setBackground(Color.WHITE);
		lblRoom.setBounds(357, 11, 34, 20);
		panel_4.add(lblRoom);
		
		JLabel lblBut_1 = new JLabel("but");
		lblBut_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblBut_1.setOpaque(true);
		lblBut_1.setForeground(Color.BLACK);
		lblBut_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBut_1.setBackground(Color.WHITE);
		lblBut_1.setBounds(398, 11, 24, 20);
		panel_4.add(lblBut_1);
		
		JLabel lblIts = new JLabel("it's");
		lblIts.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblIts.setOpaque(true);
		lblIts.setForeground(Color.BLACK);
		lblIts.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIts.setBackground(Color.WHITE);
		lblIts.setBounds(426, 11, 21, 20);
		panel_4.add(lblIts);
		
		JLabel lblJust = new JLabel("just");
		lblJust.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblJust.setOpaque(true);
		lblJust.setForeground(Color.BLACK);
		lblJust.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblJust.setBackground(Color.WHITE);
		lblJust.setBounds(450, 11, 24, 20);
		panel_4.add(lblJust);
		
		JLabel lblCome = new JLabel("come");
		lblCome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblCome.setOpaque(true);
		lblCome.setForeground(Color.BLACK);
		lblCome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCome.setBackground(Color.WHITE);
		lblCome.setBounds(480, 11, 36, 20);
		panel_4.add(lblCome);
		
		JLabel label_3 = new JLabel("to");
		label_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_3.setOpaque(true);
		label_3.setForeground(Color.BLACK);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_3.setBackground(Color.WHITE);
		label_3.setBounds(522, 11, 13, 20);
		panel_4.add(label_3);
		
		JLabel lblMy = new JLabel("my");
		lblMy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblMy.setOpaque(true);
		lblMy.setForeground(Color.BLACK);
		lblMy.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMy.setBackground(Color.WHITE);
		lblMy.setBounds(542, 11, 22, 20);
		panel_4.add(lblMy);
		
		JLabel label_25 = new JLabel("to");
		label_25.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_25.setOpaque(true);
		label_25.setForeground(Color.BLACK);
		label_25.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_25.setBackground(Color.WHITE);
		label_25.setBounds(753, 11, 13, 20);
		panel_4.add(label_25);
		
		JLabel lblRight = new JLabel("right");
		lblRight.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblRight.setOpaque(true);
		lblRight.setForeground(Color.BLACK);
		lblRight.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRight.setBackground(Color.WHITE);
		lblRight.setBounds(807, 11, 30, 20);
		panel_4.add(lblRight);
		
		JLabel label_26 = new JLabel("the");
		label_26.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_26.setOpaque(true);
		label_26.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_26.setBackground(Color.WHITE);
		label_26.setBounds(945, 11, 21, 20);
		panel_4.add(label_26);
		
		JLabel label_27 = new JLabel("person");
		label_27.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_27.setOpaque(true);
		label_27.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_27.setBackground(Color.WHITE);
		label_27.setBounds(972, 11, 44, 20);
		panel_4.add(label_27);
		
		JLabel label_28 = new JLabel("to");
		label_28.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_28.setOpaque(true);
		label_28.setForeground(Color.BLACK);
		label_28.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_28.setBackground(Color.WHITE);
		label_28.setBounds(1022, 11, 13, 20);
		panel_4.add(label_28);
		
		JLabel label_29 = new JLabel("your");
		label_29.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_29.setOpaque(true);
		label_29.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_29.setBackground(Color.WHITE);
		label_29.setBounds(1042, 11, 29, 20);
		panel_4.add(label_29);
		
		JLabel lblLeft = new JLabel("left");
		lblLeft.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblLeft.setOpaque(true);
		lblLeft.setForeground(Color.BLACK);
		lblLeft.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLeft.setBackground(Color.WHITE);
		lblLeft.setBounds(1080, 11, 21, 20);
		panel_4.add(lblLeft);
		
		JLabel label_30 = new JLabel("a liar.");
		label_30.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_30.setOpaque(true);
		label_30.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_30.setBackground(Color.WHITE);
		label_30.setBounds(26, 37, 35, 20);
		panel_4.add(label_30);
		
		JLabel label_31 = new JLabel("is");
		label_31.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_31.setOpaque(true);
		label_31.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_31.setBackground(Color.WHITE);
		label_31.setBounds(10, 37, 9, 20);
		panel_4.add(label_31);
		
		JLabel label_32 = new JLabel("Also,");
		label_32.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_32.setOpaque(true);
		label_32.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_32.setBackground(Color.WHITE);
		label_32.setBounds(74, 37, 32, 20);
		panel_4.add(label_32);
		
		JLabel label_33 = new JLabel("the");
		label_33.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_33.setOpaque(true);
		label_33.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_33.setBackground(Color.WHITE);
		label_33.setBounds(116, 37, 21, 20);
		panel_4.add(label_33);
		
		JLabel label_34 = new JLabel("person");
		label_34.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_34.setOpaque(true);
		label_34.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_34.setBackground(Color.WHITE);
		label_34.setBounds(143, 37, 44, 20);
		panel_4.add(label_34);
		
		JLabel lblIn_1 = new JLabel("in");
		lblIn_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblIn_1.setOpaque(true);
		lblIn_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIn_1.setBackground(Color.WHITE);
		lblIn_1.setBounds(238, 37, 11, 20);
		panel_4.add(lblIn_1);
		
		JLabel lblVery = new JLabel("very");
		lblVery.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblVery.setOpaque(true);
		lblVery.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblVery.setBackground(Color.WHITE);
		lblVery.setBounds(292, 37, 29, 20);
		panel_4.add(lblVery);
		
		lblChairs = new JLabel("chairs");
		lblChairs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
					highlightCorrectWord.add(lblChairs.getText().trim());
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
					highlightCorrectWord.remove(lblChairs.getText().trim());
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblChairs.setOpaque(true);
		lblChairs.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblChairs.setBackground(Color.WHITE);
		lblChairs.setBounds(329, 37, 38, 20);
		panel_4.add(lblChairs);
		
		JLabel label_35 = new JLabel("is");
		label_35.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_35.setOpaque(true);
		label_35.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_35.setBackground(Color.WHITE);
		label_35.setBounds(372, 37, 9, 20);
		panel_4.add(label_35);
		
		JLabel label_36 = new JLabel("a liar.");
		label_36.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_36.setOpaque(true);
		label_36.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_36.setBackground(Color.WHITE);
		label_36.setBounds(388, 37, 35, 20);
		panel_4.add(label_36);
		
		JLabel lblDo = new JLabel("do");
		lblDo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblDo.setOpaque(true);
		lblDo.setForeground(Color.BLACK);
		lblDo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDo.setBackground(Color.WHITE);
		lblDo.setBounds(645, 37, 17, 20);
		panel_4.add(lblDo);
		
		JLabel label_37 = new JLabel("I'm");
		label_37.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_37.setOpaque(true);
		label_37.setForeground(Color.BLACK);
		label_37.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_37.setBackground(Color.WHITE);
		label_37.setBounds(726, 37, 25, 20);
		panel_4.add(label_37);
		
		JLabel label_38 = new JLabel("gonna");
		label_38.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_38.setOpaque(true);
		label_38.setForeground(Color.BLACK);
		label_38.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_38.setBackground(Color.WHITE);
		label_38.setBounds(756, 37, 42, 20);
		panel_4.add(label_38);
		
		JLabel lblWhat = new JLabel("what");
		lblWhat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblWhat.setOpaque(true);
		lblWhat.setForeground(Color.BLACK);
		lblWhat.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblWhat.setBackground(Color.WHITE);
		lblWhat.setBounds(873, 37, 32, 20);
		panel_4.add(lblWhat);
		
		JLabel lblAbout = new JLabel("about");
		lblAbout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblAbout.setOpaque(true);
		lblAbout.setForeground(Color.BLACK);
		lblAbout.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAbout.setBackground(Color.WHITE);
		lblAbout.setBounds(1037, 37, 38, 20);
		panel_4.add(lblAbout);
		
		JLabel lblWhy = new JLabel("why");
		lblWhy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblWhy.setOpaque(true);
		lblWhy.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblWhy.setBackground(Color.WHITE);
		lblWhy.setBounds(1080, 37, 28, 20);
		panel_4.add(lblWhy);
		
		JLabel lblWere = new JLabel("we're");
		lblWere.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblWere.setOpaque(true);
		lblWere.setForeground(Color.BLACK);
		lblWere.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblWere.setBackground(Color.WHITE);
		lblWere.setBounds(10, 63, 34, 20);
		panel_4.add(lblWere);
		
		JLabel lblAllLiars = new JLabel("all liars,");
		lblAllLiars.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblAllLiars.setOpaque(true);
		lblAllLiars.setForeground(Color.BLACK);
		lblAllLiars.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAllLiars.setBackground(Color.WHITE);
		lblAllLiars.setBounds(50, 63, 47, 20);
		panel_4.add(lblAllLiars);
		
		JLabel lblCan = new JLabel("can");
		lblCan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblCan.setOpaque(true);
		lblCan.setForeground(Color.BLACK);
		lblCan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCan.setBackground(Color.WHITE);
		lblCan.setBounds(166, 63, 24, 20);
		panel_4.add(lblCan);
		
		JLabel label_6 = new JLabel("why");
		label_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_6.setOpaque(true);
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_6.setBackground(Color.WHITE);
		label_6.setBounds(367, 63, 28, 20);
		panel_4.add(label_6);
		
		JLabel label_7 = new JLabel("you");
		label_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_7.setOpaque(true);
		label_7.setForeground(Color.BLACK);
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_7.setBackground(Color.WHITE);
		label_7.setBounds(398, 63, 24, 20);
		panel_4.add(label_7);
		
		JLabel lblWant = new JLabel("want");
		lblWant.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblWant.setOpaque(true);
		lblWant.setForeground(Color.BLACK);
		lblWant.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblWant.setBackground(Color.WHITE);
		lblWant.setBounds(469, 63, 33, 20);
		panel_4.add(lblWant);
		
		JLabel label_8 = new JLabel("the");
		label_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_8.setOpaque(true);
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_8.setBackground(Color.WHITE);
		label_8.setBounds(544, 63, 21, 20);
		panel_4.add(label_8);
		
		JLabel label_9 = new JLabel("and");
		label_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_9.setOpaque(true);
		label_9.setForeground(Color.BLACK);
		label_9.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_9.setBackground(Color.WHITE);
		label_9.setBounds(670, 63, 25, 20);
		panel_4.add(label_9);
		
		JLabel lblGo = new JLabel("go");
		lblGo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblGo.setOpaque(true);
		lblGo.setForeground(Color.BLACK);
		lblGo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGo.setBackground(Color.WHITE);
		lblGo.setBounds(701, 63, 17, 20);
		panel_4.add(lblGo);
		
		JLabel lblFrom_1 = new JLabel("from");
		lblFrom_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblFrom_1.setOpaque(true);
		lblFrom_1.setForeground(Color.BLACK);
		lblFrom_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFrom_1.setBackground(Color.WHITE);
		lblFrom_1.setBounds(723, 63, 31, 20);
		panel_4.add(lblFrom_1);
		
		JLabel lblLie = new JLabel("lie");
		lblLie.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblLie.setOpaque(true);
		lblLie.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLie.setBackground(Color.WHITE);
		lblLie.setBounds(761, 63, 12, 20);
		panel_4.add(lblLie);
		
		JLabel lblSpotting = new JLabel("spotting");
		lblSpotting.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblSpotting.setOpaque(true);
		lblSpotting.setForeground(Color.BLACK);
		lblSpotting.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSpotting.setBackground(Color.WHITE);
		lblSpotting.setBounds(778, 63, 51, 20);
		panel_4.add(lblSpotting);
		
		JLabel label_10 = new JLabel("to");
		label_10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_10.setOpaque(true);
		label_10.setForeground(Color.BLACK);
		label_10.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_10.setBackground(Color.WHITE);
		label_10.setBounds(837, 63, 13, 20);
		panel_4.add(label_10);
		
		JLabel label_11 = new JLabel("truth");
		label_11.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_11.setOpaque(true);
		label_11.setForeground(Color.BLACK);
		label_11.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_11.setBackground(Color.WHITE);
		label_11.setBounds(856, 63, 32, 20);
		panel_4.add(label_11);
		
		JLabel label_12 = new JLabel("and");
		label_12.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_12.setOpaque(true);
		label_12.setForeground(Color.BLACK);
		label_12.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_12.setBackground(Color.WHITE);
		label_12.setBounds(953, 63, 25, 20);
		panel_4.add(label_12);
		
		JLabel label_13 = new JLabel("to");
		label_13.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_13.setOpaque(true);
		label_13.setForeground(Color.BLACK);
		label_13.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_13.setBackground(Color.WHITE);
		label_13.setBounds(984, 63, 13, 20);
		panel_4.add(label_13);
		
		JLabel lblTrust = new JLabel("trust");
		lblTrust.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblTrust.setOpaque(true);
		lblTrust.setForeground(Color.BLACK);
		lblTrust.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTrust.setBackground(Color.WHITE);
		lblTrust.setBounds(1003, 63, 32, 20);
		panel_4.add(lblTrust);
		
		lblForming = new JLabel("forming.");
		lblForming.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
					highlightCorrectWord.add(lblForming.getText().trim());
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
					highlightCorrectWord.remove(lblForming.getText().trim());
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblForming.setOpaque(true);
		lblForming.setForeground(Color.BLACK);
		lblForming.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblForming.setBackground(Color.WHITE);
		lblForming.setBounds(1042, 63, 62, 20);
		panel_4.add(lblForming);
		
		JLabel lblBook = new JLabel("book,");
		lblBook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblBook.setOpaque(true);
		lblBook.setForeground(Color.BLACK);
		lblBook.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBook.setBackground(Color.WHITE);
		lblBook.setBounds(327, 95, 37, 20);
		panel_4.add(lblBook);
		
		JLabel lbllie = new JLabel("\u201CLie");
		lbllie.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lbllie.setOpaque(true);
		lbllie.setForeground(Color.BLACK);
		lbllie.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbllie.setBackground(Color.WHITE);
		lbllie.setBounds(369, 95, 27, 20);
		panel_4.add(lbllie);
		
		JLabel lblWants = new JLabel("wants");
		lblWants.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblWants.setOpaque(true);
		lblWants.setForeground(Color.BLACK);
		lblWants.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblWants.setBackground(Color.WHITE);
		lblWants.setBounds(522, 95, 41, 20);
		panel_4.add(lblWants);
		
		JLabel lblTo_1 = new JLabel("to");
		lblTo_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblTo_1.setOpaque(true);
		lblTo_1.setForeground(Color.BLACK);
		lblTo_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTo_1.setBackground(Color.WHITE);
		lblTo_1.setBounds(566, 95, 14, 20);
		panel_4.add(lblTo_1);
		
		JLabel lblAPerson = new JLabel("a person");
		lblAPerson.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblAPerson.setOpaque(true);
		lblAPerson.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAPerson.setBackground(Color.WHITE);
		lblAPerson.setBounds(624, 95, 60, 20);
		panel_4.add(lblAPerson);
		
		JLabel lblAnymore = new JLabel("anymore");
		lblAnymore.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblAnymore.setOpaque(true);
		lblAnymore.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAnymore.setBackground(Color.WHITE);
		lblAnymore.setBounds(687, 95, 60, 20);
		panel_4.add(lblAnymore);
		
		JLabel lblNo = new JLabel("no,");
		lblNo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblNo.setOpaque(true);
		lblNo.setForeground(Color.BLACK);
		lblNo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNo.setBackground(Color.WHITE);
		lblNo.setBounds(753, 95, 23, 20);
		panel_4.add(lblNo);
		
		JLabel label_14 = new JLabel("no,");
		label_14.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_14.setOpaque(true);
		label_14.setForeground(Color.BLACK);
		label_14.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_14.setBackground(Color.WHITE);
		label_14.setBounds(777, 95, 23, 20);
		panel_4.add(label_14);
		
		JLabel label_15 = new JLabel("no,");
		label_15.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_15.setOpaque(true);
		label_15.setForeground(Color.BLACK);
		label_15.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_15.setBackground(Color.WHITE);
		label_15.setBounds(827, 95, 23, 20);
		panel_4.add(label_15);
		
		JLabel label_16 = new JLabel("no,");
		label_16.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_16.setOpaque(true);
		label_16.setForeground(Color.BLACK);
		label_16.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_16.setBackground(Color.WHITE);
		label_16.setBounds(803, 95, 23, 20);
		panel_4.add(label_16);
		
		JLabel lblNo_1 = new JLabel("no.");
		lblNo_1.setOpaque(true);
		lblNo_1.setForeground(Color.BLACK);
		lblNo_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNo_1.setBackground(Color.WHITE);
		lblNo_1.setBounds(853, 95, 23, 20);
		panel_4.add(lblNo_1);
		
		JLabel lblThey_1 = new JLabel("They");
		lblThey_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblThey_1.setOpaque(true);
		lblThey_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblThey_1.setBackground(Color.WHITE);
		lblThey_1.setBounds(882, 95, 35, 20);
		panel_4.add(lblThey_1);
		
		JLabel lblSay = new JLabel("say,");
		lblSay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblSay.setOpaque(true);
		lblSay.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSay.setBackground(Color.WHITE);
		lblSay.setBounds(922, 95, 30, 20);
		panel_4.add(lblSay);
		
		JLabel lblits = new JLabel("\"It's");
		lblits.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblits.setOpaque(true);
		lblits.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblits.setBackground(Color.WHITE);
		lblits.setBounds(954, 95, 30, 20);
		panel_4.add(lblits);
		
		JLabel lblOkay = new JLabel("okay,");
		lblOkay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblOkay.setOpaque(true);
		lblOkay.setForeground(Color.BLACK);
		lblOkay.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblOkay.setBackground(Color.WHITE);
		lblOkay.setBounds(988, 95, 38, 20);
		panel_4.add(lblOkay);
		
		JLabel lblWell = new JLabel("we'll");
		lblWell.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblWell.setOpaque(true);
		lblWell.setForeground(Color.BLACK);
		lblWell.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblWell.setBackground(Color.WHITE);
		lblWell.setBounds(1030, 95, 28, 20);
		panel_4.add(lblWell);
		
		JLabel lblEmail = new JLabel("e-mail");
		lblEmail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblEmail.setOpaque(true);
		lblEmail.setForeground(Color.BLACK);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmail.setBackground(Color.WHITE);
		lblEmail.setBounds(1062, 95, 41, 20);
		panel_4.add(lblEmail);
		
		JLabel lblYou = new JLabel("you.\"");
		lblYou.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblYou.setOpaque(true);
		lblYou.setForeground(Color.BLACK);
		lblYou.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblYou.setBackground(Color.WHITE);
		lblYou.setBounds(10, 118, 35, 20);
		panel_4.add(lblYou);
		
		JLabel lblICant = new JLabel("I can't");
		lblICant.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblICant.setOpaque(true);
		lblICant.setForeground(Color.BLACK);
		lblICant.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblICant.setBackground(Color.WHITE);
		lblICant.setBounds(53, 118, 42, 20);
		panel_4.add(lblICant);
		
		JLabel lblEven = new JLabel("even");
		lblEven.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblEven.setOpaque(true);
		lblEven.setForeground(Color.BLACK);
		lblEven.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEven.setBackground(Color.WHITE);
		lblEven.setBounds(103, 118, 32, 20);
		panel_4.add(lblEven);
		
		JLabel lblGet = new JLabel("get");
		lblGet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblGet.setOpaque(true);
		lblGet.setForeground(Color.BLACK);
		lblGet.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGet.setBackground(Color.WHITE);
		lblGet.setBounds(143, 118, 21, 20);
		panel_4.add(lblGet);
		
		JLabel lblA = new JLabel("a");
		lblA.setOpaque(true);
		lblA.setForeground(Color.BLACK);
		lblA.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblA.setBackground(Color.WHITE);
		lblA.setBounds(169, 118, 8, 20);
		panel_4.add(lblA);
		
		JLabel lblCoffee = new JLabel("coffee");
		lblCoffee.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblCoffee.setOpaque(true);
		lblCoffee.setForeground(Color.BLACK);
		lblCoffee.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCoffee.setBackground(Color.WHITE);
		lblCoffee.setBounds(184, 118, 42, 20);
		panel_4.add(lblCoffee);
		
		JLabel lblDate = new JLabel("date");
		lblDate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblDate.setOpaque(true);
		lblDate.setForeground(Color.BLACK);
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDate.setBackground(Color.WHITE);
		lblDate.setBounds(232, 118, 31, 20);
		panel_4.add(lblDate);
		
		JLabel lblAt = new JLabel("at");
		lblAt.setOpaque(true);
		lblAt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAt.setBackground(Color.WHITE);
		lblAt.setBounds(266, 118, 14, 20);
		panel_4.add(lblAt);
		
		JLabel lblStarbucks = new JLabel("starbucks.");
		lblStarbucks.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblStarbucks.setOpaque(true);
		lblStarbucks.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStarbucks.setBackground(Color.WHITE);
		lblStarbucks.setBounds(284, 118, 70, 20);
		panel_4.add(lblStarbucks);
		
		JLabel lblhoney = new JLabel("\"Honey,");
		lblhoney.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblhoney.setOpaque(true);
		lblhoney.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblhoney.setBackground(Color.WHITE);
		lblhoney.setBounds(485, 118, 53, 20);
		panel_4.add(lblhoney);
		
		JLabel lblDeception = new JLabel("deception?");
		lblDeception.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblDeception.setOpaque(true);
		lblDeception.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDeception.setBackground(Color.WHITE);
		lblDeception.setBounds(542, 118, 72, 20);
		panel_4.add(lblDeception);
		
		JLabel lblMaybe = new JLabel("Maybe");
		lblMaybe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblMaybe.setOpaque(true);
		lblMaybe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMaybe.setBackground(Color.WHITE);
		lblMaybe.setBounds(617, 118, 45, 20);
		panel_4.add(lblMaybe);
		
		JLabel lblYou_1 = new JLabel("you");
		lblYou_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblYou_1.setOpaque(true);
		lblYou_1.setForeground(Color.BLACK);
		lblYou_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblYou_1.setBackground(Color.WHITE);
		lblYou_1.setBounds(668, 118, 25, 20);
		panel_4.add(lblYou_1);
		
		JLabel lblCould = new JLabel("could");
		lblCould.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblCould.setOpaque(true);
		lblCould.setForeground(Color.BLACK);
		lblCould.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCould.setBackground(Color.WHITE);
		lblCould.setBounds(698, 118, 35, 20);
		panel_4.add(lblCould);
		
		JLabel lblHow_1 = new JLabel("How");
		lblHow_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblHow_1.setForeground(Color.BLACK);
		lblHow_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblHow_1.setBounds(911, 118, 30, 20);
		panel_4.add(lblHow_1);
		
		JLabel lblAbout_1 = new JLabel("about");
		lblAbout_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblAbout_1.setOpaque(true);
		lblAbout_1.setForeground(Color.BLACK);
		lblAbout_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAbout_1.setBackground(Color.WHITE);
		lblAbout_1.setBounds(945, 118, 39, 20);
		panel_4.add(lblAbout_1);
		
		JLabel lblCooking = new JLabel("cooking?");
		lblCooking.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblCooking.setOpaque(true);
		lblCooking.setForeground(Color.BLACK);
		lblCooking.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCooking.setBackground(Color.WHITE);
		lblCooking.setBounds(1038, 118, 57, 20);
		panel_4.add(lblCooking);
		
		JLabel lblFrench = new JLabel("french");
		lblFrench.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblFrench.setOpaque(true);
		lblFrench.setForeground(Color.BLACK);
		lblFrench.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFrench.setBackground(Color.WHITE);
		lblFrench.setBounds(990, 118, 41, 20);
		panel_4.add(lblFrench);
		
		JLabel lblGonna = new JLabel("gonna");
		lblGonna.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblGonna.setOpaque(true);
		lblGonna.setForeground(Color.BLACK);
		lblGonna.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGonna.setBackground(Color.WHITE);
		lblGonna.setBounds(248, 157, 41, 20);
		panel_4.add(lblGonna);
		
		JLabel lblDo_1 = new JLabel("do");
		lblDo_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblDo_1.setOpaque(true);
		lblDo_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDo_1.setBackground(Color.WHITE);
		lblDo_1.setBounds(295, 157, 17, 20);
		panel_4.add(lblDo_1);
		
		JLabel lblIs = new JLabel("is");
		lblIs.setOpaque(true);
		lblIs.setForeground(Color.BLACK);
		lblIs.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIs.setBackground(Color.WHITE);
		lblIs.setBounds(317, 157, 9, 20);
		panel_4.add(lblIs);
		
		JLabel label_17 = new JLabel("gonna");
		label_17.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_17.setOpaque(true);
		label_17.setForeground(Color.BLACK);
		label_17.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_17.setBackground(Color.WHITE);
		label_17.setBounds(359, 157, 41, 20);
		panel_4.add(label_17);
		
		JLabel lblClarify = new JLabel("clarify");
		lblClarify.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblClarify.setOpaque(true);
		lblClarify.setForeground(Color.BLACK);
		lblClarify.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblClarify.setBackground(Color.WHITE);
		lblClarify.setBounds(405, 157, 39, 20);
		panel_4.add(lblClarify);
		
		JLabel lblGoal = new JLabel("goal");
		lblGoal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblGoal.setOpaque(true);
		lblGoal.setForeground(Color.BLACK);
		lblGoal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGoal.setBackground(Color.WHITE);
		lblGoal.setBounds(475, 157, 28, 20);
		panel_4.add(lblGoal);
		
		JLabel lblFor = new JLabel("for");
		lblFor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblFor.setOpaque(true);
		lblFor.setForeground(Color.BLACK);
		lblFor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFor.setBackground(Color.WHITE);
		lblFor.setBounds(508, 157, 23, 20);
		panel_4.add(lblFor);
		
		JLabel label_18 = new JLabel("you");
		label_18.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_18.setOpaque(true);
		label_18.setForeground(Color.BLACK);
		label_18.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_18.setBackground(Color.WHITE);
		label_18.setBounds(532, 157, 25, 20);
		panel_4.add(label_18);
		
		JLabel lblWhich = new JLabel("which");
		lblWhich.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblWhich.setOpaque(true);
		lblWhich.setForeground(Color.BLACK);
		lblWhich.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblWhich.setBackground(Color.WHITE);
		lblWhich.setBounds(562, 157, 35, 20);
		panel_4.add(lblWhich);
		
		JLabel label_19 = new JLabel("is");
		label_19.setOpaque(true);
		label_19.setForeground(Color.BLACK);
		label_19.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_19.setBackground(Color.WHITE);
		label_19.setBounds(602, 157, 9, 20);
		panel_4.add(label_19);
		
		JLabel lblNot = new JLabel("not");
		lblNot.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblNot.setOpaque(true);
		lblNot.setForeground(Color.BLACK);
		lblNot.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNot.setBackground(Color.WHITE);
		lblNot.setBounds(617, 157, 23, 20);
		panel_4.add(lblNot);
		
		JLabel lblTo_2 = new JLabel("to");
		lblTo_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblTo_2.setOpaque(true);
		lblTo_2.setForeground(Color.BLACK);
		lblTo_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTo_2.setBackground(Color.WHITE);
		lblTo_2.setBounds(643, 157, 15, 20);
		panel_4.add(lblTo_2);
		
		lblPlay = new JLabel("play");
		lblPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
					highlightCorrectWord.add(lblPlay.getText().trim());
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
					highlightCorrectWord.remove(lblPlay.getText().trim());
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblPlay.setOpaque(true);
		lblPlay.setForeground(Color.BLACK);
		lblPlay.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPlay.setBackground(Color.WHITE);
		lblPlay.setBounds(660, 157, 29, 20);
		panel_4.add(lblPlay);
		
		JLabel lblAGame = new JLabel("a game");
		lblAGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblAGame.setOpaque(true);
		lblAGame.setForeground(Color.BLACK);
		lblAGame.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAGame.setBackground(Color.WHITE);
		lblAGame.setBounds(690, 157, 52, 20);
		panel_4.add(lblAGame);
		
		JLabel lblOf_1 = new JLabel("of");
		lblOf_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblOf_1.setOpaque(true);
		lblOf_1.setForeground(Color.BLACK);
		lblOf_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblOf_1.setBackground(Color.WHITE);
		lblOf_1.setBounds(747, 157, 15, 20);
		panel_4.add(lblOf_1);
		
		JLabel lblGotcha = new JLabel("gotcha");
		lblGotcha.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblGotcha.setOpaque(true);
		lblGotcha.setForeground(Color.BLACK);
		lblGotcha.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGotcha.setBackground(Color.WHITE);
		lblGotcha.setBounds(766, 157, 45, 20);
		panel_4.add(lblGotcha);
		
		JLabel lblParallel = new JLabel("parallel.");
		lblParallel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblParallel.setOpaque(true);
		lblParallel.setForeground(Color.BLACK);
		lblParallel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblParallel.setBackground(Color.WHITE);
		lblParallel.setBounds(816, 157, 50, 20);
		panel_4.add(lblParallel);
		
		JLabel lblLie_1 = new JLabel("Lie");
		lblLie_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblLie_1.setOpaque(true);
		lblLie_1.setForeground(Color.BLACK);
		lblLie_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLie_1.setBackground(Color.WHITE);
		lblLie_1.setBounds(869, 157, 20, 20);
		panel_4.add(lblLie_1);
		
		JLabel lblSpotters = new JLabel("spotters");
		lblSpotters.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblSpotters.setOpaque(true);
		lblSpotters.setForeground(Color.BLACK);
		lblSpotters.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSpotters.setBackground(Color.WHITE);
		lblSpotters.setBounds(892, 157, 54, 20);
		panel_4.add(lblSpotters);
		
		JLabel lblArent = new JLabel("aren't");
		lblArent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblArent.setOpaque(true);
		lblArent.setForeground(Color.BLACK);
		lblArent.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblArent.setBackground(Color.WHITE);
		lblArent.setBounds(950, 157, 39, 20);
		panel_4.add(lblArent);
		
		JLabel lblThose = new JLabel("those");
		lblThose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblThose.setOpaque(true);
		lblThose.setForeground(Color.BLACK);
		lblThose.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblThose.setBackground(Color.WHITE);
		lblThose.setBounds(994, 157, 37, 20);
		panel_4.add(lblThose);
		
		JLabel lblNutpicky = new JLabel("nitpicky");
		lblNutpicky.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblNutpicky.setOpaque(true);
		lblNutpicky.setForeground(Color.BLACK);
		lblNutpicky.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNutpicky.setBackground(Color.WHITE);
		lblNutpicky.setBounds(1035, 157, 49, 20);
		panel_4.add(lblNutpicky);
		
		JLabel lblKids = new JLabel("kids,");
		lblKids.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblKids.setOpaque(true);
		lblKids.setForeground(Color.BLACK);
		lblKids.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblKids.setBackground(Color.WHITE);
		lblKids.setBounds(10, 180, 29, 20);
		panel_4.add(lblKids);
		
		JLabel lblIn_2 = new JLabel("in");
		lblIn_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblIn_2.setOpaque(true);
		lblIn_2.setForeground(Color.BLACK);
		lblIn_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIn_2.setBackground(Color.WHITE);
		lblIn_2.setBounds(45, 180, 10, 20);
		panel_4.add(lblIn_2);
		
		JLabel lblThe_2 = new JLabel("the");
		lblThe_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblThe_2.setOpaque(true);
		lblThe_2.setForeground(Color.BLACK);
		lblThe_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblThe_2.setBackground(Color.WHITE);
		lblThe_2.setBounds(60, 180, 23, 20);
		panel_4.add(lblThe_2);
		
		JLabel lblBack = new JLabel("back");
		lblBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblBack.setOpaque(true);
		lblBack.setForeground(Color.BLACK);
		lblBack.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBack.setBackground(Color.WHITE);
		lblBack.setBounds(87, 180, 33, 20);
		panel_4.add(lblBack);
		
		JLabel lblOf_2 = new JLabel("of");
		lblOf_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblOf_2.setOpaque(true);
		lblOf_2.setForeground(Color.BLACK);
		lblOf_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblOf_2.setBackground(Color.WHITE);
		lblOf_2.setBounds(123, 180, 16, 20);
		panel_4.add(lblOf_2);
		
		JLabel label_20 = new JLabel("the");
		label_20.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_20.setOpaque(true);
		label_20.setForeground(Color.BLACK);
		label_20.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_20.setBackground(Color.WHITE);
		label_20.setBounds(143, 180, 23, 20);
		panel_4.add(label_20);
		
		JLabel lblRoom_1 = new JLabel("room");
		lblRoom_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblRoom_1.setOpaque(true);
		lblRoom_1.setForeground(Color.BLACK);
		lblRoom_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRoom_1.setBackground(Color.WHITE);
		lblRoom_1.setBounds(170, 180, 36, 20);
		panel_4.add(lblRoom_1);
		
		JLabel lblThat = new JLabel("that");
		lblThat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblThat.setOpaque(true);
		lblThat.setForeground(Color.BLACK);
		lblThat.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblThat.setBackground(Color.WHITE);
		lblThat.setBounds(210, 180, 29, 20);
		panel_4.add(lblThat);
		
		JLabel lblAre = new JLabel("are");
		lblAre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblAre.setOpaque(true);
		lblAre.setForeground(Color.BLACK);
		lblAre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAre.setBackground(Color.WHITE);
		lblAre.setBounds(242, 180, 23, 20);
		panel_4.add(lblAre);
		
		JLabel lblShouting = new JLabel("shouting");
		lblShouting.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblShouting.setOpaque(true);
		lblShouting.setForeground(Color.BLACK);
		lblShouting.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblShouting.setBackground(Color.WHITE);
		lblShouting.setBounds(269, 180, 56, 20);
		panel_4.add(lblShouting);
		
		JLabel label_21 = new JLabel("gotcha");
		label_21.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_21.setOpaque(true);
		label_21.setForeground(Color.BLACK);
		label_21.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_21.setBackground(Color.WHITE);
		label_21.setBounds(329, 180, 45, 20);
		panel_4.add(label_21);
		
		JLabel lblGotcha_1 = new JLabel("gotcha,");
		lblGotcha_1.setOpaque(true);
		lblGotcha_1.setForeground(Color.BLACK);
		lblGotcha_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGotcha_1.setBackground(Color.WHITE);
		lblGotcha_1.setBounds(377, 180, 49, 20);
		panel_4.add(lblGotcha_1);
		
		JLabel lblYour_1 = new JLabel("your");
		lblYour_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblYour_1.setOpaque(true);
		lblYour_1.setForeground(Color.BLACK);
		lblYour_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblYour_1.setBackground(Color.WHITE);
		lblYour_1.setBounds(430, 180, 29, 20);
		panel_4.add(lblYour_1);
		
		JLabel lblEyebrow = new JLabel("eyebrow");
		lblEyebrow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblEyebrow.setOpaque(true);
		lblEyebrow.setForeground(Color.BLACK);
		lblEyebrow.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEyebrow.setBackground(Color.WHITE);
		lblEyebrow.setBounds(464, 180, 56, 20);
		panel_4.add(lblEyebrow);
		
		JLabel lblTwitched = new JLabel("twitched,");
		lblTwitched.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblTwitched.setOpaque(true);
		lblTwitched.setForeground(Color.BLACK);
		lblTwitched.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTwitched.setBackground(Color.WHITE);
		lblTwitched.setBounds(524, 180, 60, 20);
		panel_4.add(lblTwitched);
		
		JLabel label_22 = new JLabel("you");
		label_22.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_22.setOpaque(true);
		label_22.setForeground(Color.BLACK);
		label_22.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_22.setBackground(Color.WHITE);
		label_22.setBounds(585, 180, 25, 20);
		panel_4.add(label_22);
		
		JLabel lblFlared = new JLabel("flared");
		lblFlared.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblFlared.setOpaque(true);
		lblFlared.setForeground(Color.BLACK);
		lblFlared.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFlared.setBackground(Color.WHITE);
		lblFlared.setBounds(615, 180, 37, 20);
		panel_4.add(lblFlared);
		
		JLabel label_23 = new JLabel("your");
		label_23.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_23.setOpaque(true);
		label_23.setForeground(Color.BLACK);
		label_23.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_23.setBackground(Color.WHITE);
		label_23.setBounds(655, 180, 29, 20);
		panel_4.add(label_23);
		
		lblNose = new JLabel("nose.");
		lblNose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
					highlightCorrectWord.add(lblNose.getText().trim());
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
					highlightCorrectWord.remove(lblNose.getText().trim());
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblNose.setOpaque(true);
		lblNose.setForeground(Color.BLACK);
		lblNose.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNose.setBackground(Color.WHITE);
		lblNose.setBounds(689, 180, 37, 20);
		panel_4.add(lblNose);
		
		JLabel lblI = new JLabel("I");
		lblI.setOpaque(true);
		lblI.setForeground(Color.BLACK);
		lblI.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblI.setBackground(Color.WHITE);
		lblI.setBounds(732, 180, 9, 20);
		panel_4.add(lblI);
		
		JLabel lblWatched = new JLabel("watched");
		lblWatched.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblWatched.setOpaque(true);
		lblWatched.setForeground(Color.BLACK);
		lblWatched.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblWatched.setBackground(Color.WHITE);
		lblWatched.setBounds(743, 180, 55, 20);
		panel_4.add(lblWatched);
		
		JLabel label_24 = new JLabel("that");
		label_24.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_24.setOpaque(true);
		label_24.setForeground(Color.BLACK);
		label_24.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_24.setBackground(Color.WHITE);
		label_24.setBounds(803, 180, 29, 20);
		panel_4.add(label_24);
		
		JLabel lblTv = new JLabel("TV");
		lblTv.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblTv.setOpaque(true);
		lblTv.setForeground(Color.BLACK);
		lblTv.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTv.setBackground(Color.WHITE);
		lblTv.setBounds(835, 180, 21, 20);
		panel_4.add(lblTv);
		
		JLabel lblShow = new JLabel("show");
		lblShow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblShow.setOpaque(true);
		lblShow.setForeground(Color.BLACK);
		lblShow.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblShow.setBackground(Color.WHITE);
		lblShow.setBounds(860, 180, 33, 20);
		panel_4.add(lblShow);
		
		JLabel lblLie_2 = new JLabel("lie");
		lblLie_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblLie_2.setOpaque(true);
		lblLie_2.setForeground(Color.BLACK);
		lblLie_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLie_2.setBackground(Color.WHITE);
		lblLie_2.setBounds(900, 180, 13, 20);
		panel_4.add(lblLie_2);
		
		JLabel lblTo_3 = new JLabel("to");
		lblTo_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblTo_3.setOpaque(true);
		lblTo_3.setForeground(Color.BLACK);
		lblTo_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTo_3.setBackground(Color.WHITE);
		lblTo_3.setBounds(919, 180, 13, 20);
		panel_4.add(lblTo_3);
		
		JLabel lblMe = new JLabel("me,");
		lblMe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblMe.setOpaque(true);
		lblMe.setForeground(Color.BLACK);
		lblMe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMe.setBackground(Color.WHITE);
		lblMe.setBounds(938, 180, 28, 20);
		panel_4.add(lblMe);
		
		JLabel label_39 = new JLabel("I");
		label_39.setOpaque(true);
		label_39.setForeground(Color.BLACK);
		label_39.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_39.setBackground(Color.WHITE);
		label_39.setBounds(969, 180, 9, 20);
		panel_4.add(label_39);
		
		JLabel lblKnow = new JLabel("know");
		lblKnow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblKnow.setOpaque(true);
		lblKnow.setForeground(Color.BLACK);
		lblKnow.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblKnow.setBackground(Color.WHITE);
		lblKnow.setBounds(980, 180, 33, 20);
		panel_4.add(lblKnow);
		
		JLabel lblYoure = new JLabel("you're");
		lblYoure.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblYoure.setOpaque(true);
		lblYoure.setForeground(Color.BLACK);
		lblYoure.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblYoure.setBackground(Color.WHITE);
		lblYoure.setBounds(1019, 180, 41, 20);
		panel_4.add(lblYoure);
		
		JLabel lblLying = new JLabel("lying.");
		lblLying.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblLying.setOpaque(true);
		lblLying.setForeground(Color.BLACK);
		lblLying.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLying.setBackground(Color.WHITE);
		lblLying.setBounds(1065, 180, 39, 20);
		panel_4.add(lblLying);
	}
	//************************************************************** Method of countdown*********************************************************
	
		public void timeCount(){
			Runnable run=new Runnable(){
				public void run(){
					frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					while(val>=0)
			    	{
						statusLabel.setText("starting in "+val+" seconds");
						val--;
			    		try {
							TimeUnit.SECONDS.sleep(1);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			    	}
					try {
						player();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (LineUnavailableException e) {
						e.printStackTrace();
					}
				}
			};
			Thread tr=new Thread(run);
			tr.start();
			
		}
/************************************************** player **************************************************************/
	
	public void player() throws InterruptedException, LineUnavailableException{
		Runnable run1=new Runnable(){
			public void run(){
				statusLabel.setText("Playing");
				progressBar.setIndeterminate(false);
				progressBar.setMaximum(220);
				do{
					progressBar.setValue(progressBar.getValue()+1);
					try {
						TimeUnit.MILLISECONDS.sleep(250);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}while(progressBar.getValue()<220);
			}
		};
		Runnable run=new Runnable(){
			public void run(){
				try {
					Connection conn= sqlConnection.dbConnection();
					PreparedStatement pstmt=conn.prepareStatement("SELECT listeningHighlightWord FROM test2question WHERE rowid=?;");
					pstmt.setInt(1, 1);  // 2nd listening mcq
					ResultSet rs=pstmt.executeQuery();
					InputStream input = rs.getBinaryStream("listeningHighlightWord");
					//FileInputStream fis=new FileInputStream("sounds\\S1.mp3");
					p=new Player(input);
					
					p.play();
					rs.close();
					pstmt.close();
					conn.close();
				} catch (JavaLayerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				statusLabel.setText("playback stopped");
				
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		};
		Thread tr1=new Thread(run1);
		Thread tr=new Thread(run);
		tr1.start();
		tr.start();
		
		//Thread.sleep(5000);
		//recorder();
	}
	public void fileSaver() throws IOException, ClassNotFoundException{
		File answerFolder = new File("Answer");
		if(answerFolder.exists())
		{
			File answer=new File("Answer//ListeningAns.ser");
			if(answer.exists())
			{
				FileInputStream fis=new FileInputStream(answer);
				ObjectInputStream ois=new ObjectInputStream(fis);
				ListeningAnswer la=(ListeningAnswer)ois.readObject();
				/*if(radiobtn3.isSelected())
				{
					la.highlightCorrectSummary=radiobtn3.getText();
				}*/
				la.highlightCorrectWord.addAll(highlightCorrectWord);
				ois.close();
				fis.close();
				answer.delete();
				FileOutputStream fos=new FileOutputStream(new File("Answer//ListeningAns.ser"),true);
				ObjectOutputStream oos=new ObjectOutputStream(new BufferedOutputStream (fos));
				oos.writeObject(la);
				oos.close();
				fos.close();
			}
		}
		else
		{
			answerFolder.mkdirs();
			File answer=new File("Answer//ListeningAns.ser");
			ListeningAnswer la=new ListeningAnswer();
			/*if(radiobtn3.isSelected())
			{
				la.highlightCorrectSummary=radiobtn3.getText();
			}*/
			la.highlightCorrectWord.addAll(highlightCorrectWord);
			FileOutputStream fos=new FileOutputStream(answer,true);
			ObjectOutputStream oos=new ObjectOutputStream(new BufferedOutputStream (fos));
			oos.writeObject(la);
			oos.close();
			fos.close();
		}
	}
}
