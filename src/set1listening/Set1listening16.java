package set1listening;

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
import javax.swing.border.MatteBorder;

public class Set1listening16 {

	public JFrame frame;
	JLabel lblOrganized;
	JLabel lblBy;
	JLabel label1;
	JLabel lblDecohered;
	ArrayList<String> highlightCorrectWord=new ArrayList<String>();
	JLabel lblDevice;
	JLabel lblPlanes;
	JLabel lblBasically;
	JLabel lblWay;
	JLabel statusLabel;
	JProgressBar progressBar;
	Player p;
	public int val=4;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Set1listening16 window = new Set1listening16();
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
	public Set1listening16() {
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
						conn.createStatement().executeUpdate("DELETE FROM set1speaking;");
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
		panel_2.setBorder(new MatteBorder(2, 1, 2, 1, (Color) new Color(51, 0, 153)));
		panel_2.setBounds(542, 217, 277, 148);
		frame.getContentPane().add(panel_2);
		
		progressBar = new JProgressBar();
		progressBar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		progressBar.setForeground(new Color(0, 51, 102));
		progressBar.setBorder(new LineBorder(new Color(30, 144, 255), 2, true));
		progressBar.setBorderPainted(true);
		progressBar.setStringPainted(true);
		progressBar.setString("");
		progressBar.setIndeterminate(true);
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
		panel_3.setBackground(new Color(224, 255, 255));
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
				Set1listening17 go= new Set1listening17();
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
		panel_4.setBounds(134, 434, 1120, 148);
		frame.getContentPane().add(panel_4);
		panel_4.setLayout(null);
		
		lblOrganized = new JLabel("So why ");
		lblOrganized.setBounds(10, 11, 52, 20);
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
		
		lblBy = new JLabel("is it that ");
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
		lblBy.setBounds(64, 11, 57, 20);
		panel_4.add(lblBy);
		lblBy.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblYourRadioCannot = new JLabel("your radio cannot listen to Radio Moscow? Why isn't it possible for your");
		lblYourRadioCannot.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblYourRadioCannot.setBounds(123, 11, 462, 20);
		panel_4.add(lblYourRadioCannot);
		
		JLabel lblRadioTo = new JLabel("radio to");
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
		lblRadioTo.setBounds(591, 11, 52, 20);
		panel_4.add(lblRadioTo);
		
		JLabel lblListenTo = new JLabel("listen to");
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
		lblListenTo.setBounds(645, 11, 52, 20);
		panel_4.add(lblListenTo);
		
		JLabel lblAllFrequencies = new JLabel("all frequencies?");
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
		lblAllFrequencies.setBounds(701, 11, 98, 20);
		panel_4.add(lblAllFrequencies);
		
		JLabel lblBecause = new JLabel("Because");
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
		lblBecause.setBounds(809, 11, 55, 20);
		panel_4.add(lblBecause);
		
		JLabel lblYour = new JLabel("your");
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
		lblYour.setBounds(871, 11, 29, 20);
		panel_4.add(lblYour);
		
		lblDevice = new JLabel("device");
		lblDevice.setOpaque(true);
		lblDevice.setBackground(Color.white);
		lblDevice.addMouseListener(new MouseAdapter() {
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
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
					highlightCorrectWord.add(lblDevice.getText());
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
					highlightCorrectWord.remove(lblDevice.getText());
				}
			}
		});
		lblDevice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDevice.setBounds(906, 11, 41, 20);
		panel_4.add(lblDevice);
		
		label1 = new JLabel("decoherent.");
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
		label1.setBounds(968, 11, 78, 20);
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
		label_4.setBounds(953, 11, 9, 20);
		panel_4.add(label_4);
		
		JLabel lblItIsNo = new JLabel("It is no");
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
		lblItIsNo.setBounds(1054, 11, 56, 20);
		panel_4.add(lblItIsNo);
		
		JLabel lblLonger = new JLabel("longer");
		lblLonger.addMouseListener(new MouseAdapter() {
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
		lblLonger.setForeground(Color.BLACK);
		lblLonger.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLonger.setOpaque(true);
		lblLonger.setBackground(Color.white);
		lblLonger.setBounds(10, 37, 41, 20);
		panel_4.add(lblLonger);
		
		JLabel lblVibrating = new JLabel("vibrating");
		lblVibrating.addMouseListener(new MouseAdapter() {
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
		lblVibrating.setForeground(Color.BLACK);
		lblVibrating.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblVibrating.setOpaque(true);
		lblVibrating.setBackground(Color.white);
		lblVibrating.setBounds(55, 37, 59, 20);
		panel_4.add(lblVibrating);
		
		JLabel lblInUnison = new JLabel("in unison");
		lblInUnison.addMouseListener(new MouseAdapter() {
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
		lblInUnison.setForeground(Color.BLACK);
		lblInUnison.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblInUnison.setOpaque(true);
		lblInUnison.setBackground(Color.white);
		lblInUnison.setBounds(115, 37, 59, 20);
		panel_4.add(lblInUnison);
		
		JLabel lblWith = new JLabel("with");
		lblWith.addMouseListener(new MouseAdapter() {
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
		lblWith.setForeground(Color.BLACK);
		lblWith.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblWith.setOpaque(true);
		lblWith.setBackground(Color.white);
		lblWith.setBounds(178, 37, 29, 20);
		panel_4.add(lblWith);
		
		JLabel lblThese = new JLabel("these");
		lblThese.addMouseListener(new MouseAdapter() {
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
		lblThese.setForeground(Color.BLACK);
		lblThese.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblThese.setOpaque(true);
		lblThese.setBackground(Color.white);
		lblThese.setBounds(209, 37, 41, 20);
		panel_4.add(lblThese);
		
		JLabel lblOther = new JLabel("other");
		lblOther.addMouseListener(new MouseAdapter() {
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
		lblOther.setForeground(Color.BLACK);
		lblOther.setOpaque(true);
		lblOther.setBackground(Color.white);
		lblOther.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblOther.setBounds(250, 37, 34, 20);
		panel_4.add(lblOther);
		
		JLabel lblFrequencies = new JLabel("frequencies.");
		lblFrequencies.addMouseListener(new MouseAdapter() {
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
		lblFrequencies.setForeground(Color.BLACK);
		lblFrequencies.setOpaque(true);
		lblFrequencies.setBackground(Color.white);
		lblFrequencies.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFrequencies.setBounds(291, 37, 85, 20);
		panel_4.add(lblFrequencies);
		
		JLabel lblAnd = new JLabel("And");
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
		lblAnd.setBounds(378, 37, 29, 20);
		panel_4.add(lblAnd);
		
		JLabel lblThe = new JLabel("the");
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
		lblThe.setBounds(411, 37, 21, 20);
		panel_4.add(lblThe);
		
		JLabel lblSame = new JLabel("same");
		lblSame.addMouseListener(new MouseAdapter() {
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
		lblSame.setForeground(Color.BLACK);
		lblSame.setOpaque(true);
		lblSame.setBackground(Color.white);
		lblSame.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSame.setBounds(438, 37, 41, 20);
		panel_4.add(lblSame);
		
		JLabel lblThing = new JLabel("thing");
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
		lblThing.setBounds(480, 37, 34, 20);
		panel_4.add(lblThing);
		
		JLabel lblInQuantum = new JLabel("in quantum");
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
		lblInQuantum.setBounds(517, 37, 78, 20);
		panel_4.add(lblInQuantum);
		
		JLabel lblPhysics = new JLabel("physics;");
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
		lblPhysics.setBounds(596, 37, 52, 20);
		panel_4.add(lblPhysics);
		
		JLabel lblWe = new JLabel("we");
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
		lblWe.setBounds(655, 37, 21, 20);
		panel_4.add(lblWe);
		
		JLabel lblConsist = new JLabel("consist");
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
		lblConsist.setBounds(678, 37, 44, 20);
		panel_4.add(lblConsist);
		
		JLabel lblOfAtoms = new JLabel("of atoms.");
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
		lblOfAtoms.setBounds(727, 37, 64, 20);
		panel_4.add(lblOfAtoms);
		
		JLabel lblOurAtoms = new JLabel("Our atoms");
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
		lblOurAtoms.setBounds(801, 37, 70, 20);
		panel_4.add(lblOurAtoms);
		
		JLabel lblVibrate = new JLabel("vibrate");
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
		lblVibrate.setBounds(877, 37, 44, 20);
		panel_4.add(lblVibrate);
		
		JLabel lblBut = new JLabel("but");
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
		lblBut.setBounds(926, 37, 21, 20);
		panel_4.add(lblBut);
		
		JLabel lblThey = new JLabel("they");
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
		lblThey.setBounds(952, 37, 34, 20);
		panel_4.add(lblThey);
		
		JLabel lblNo = new JLabel("no");
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
		lblNo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNo.setOpaque(true);
		lblNo.setBackground(Color.white);
		lblNo.setBounds(987, 37, 21, 20);
		panel_4.add(lblNo);
		
		JLabel label_6 = new JLabel("longer");
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
		label_6.setForeground(Color.BLACK);
		label_6.setOpaque(true);
		label_6.setBackground(Color.white);
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_6.setBounds(1009, 37, 41, 20);
		panel_4.add(label_6);
		
		JLabel label_7 = new JLabel("vibrate");
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
		label_7.setForeground(Color.BLACK);
		label_7.setOpaque(true);
		label_7.setBackground(Color.white);
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_7.setBounds(1054, 37, 44, 20);
		panel_4.add(label_7);
		
		JLabel label_8 = new JLabel("in unison");
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
		label_8.setForeground(Color.BLACK);
		label_8.setOpaque(true);
		label_8.setBackground(Color.white);
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_8.setBounds(10, 63, 59, 20);
		panel_4.add(label_8);
		
		JLabel label_9 = new JLabel("with");
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
		label_9.setForeground(Color.BLACK);
		label_9.setOpaque(true);
		label_9.setBackground(Color.white);
		label_9.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_9.setBounds(73, 63, 29, 20);
		panel_4.add(label_9);
		
		JLabel label_10 = new JLabel("these");
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
		label_10.setForeground(Color.BLACK);
		label_10.setOpaque(true);
		label_10.setBackground(Color.white);
		label_10.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_10.setBounds(104, 63, 41, 20);
		panel_4.add(label_10);
		
		JLabel label_11 = new JLabel("other");
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
		label_11.setForeground(Color.BLACK);
		label_11.setOpaque(true);
		label_11.setBackground(Color.white);
		label_11.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_11.setBounds(146, 63, 34, 20);
		panel_4.add(label_11);
		
		lblPlanes = new JLabel("planes.");
		lblPlanes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
					highlightCorrectWord.add(lblPlanes.getText());
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
					highlightCorrectWord.remove(lblPlanes.getText());
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
		lblPlanes.setBounds(186, 63, 47, 20);
		panel_4.add(lblPlanes);
		
		JLabel label_12 = new JLabel("we");
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
		label_12.setForeground(Color.BLACK);
		label_12.setOpaque(true);
		label_12.setBackground(Color.white);
		label_12.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_12.setBounds(240, 63, 19, 20);
		panel_4.add(label_12);
		
		JLabel lblHave = new JLabel("have");
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
		lblHave.setBounds(264, 63, 35, 20);
		panel_4.add(lblHave);
		
		JLabel lblDecoupled = new JLabel("decoupled ");
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
		lblDecoupled.setBounds(303, 63, 70, 20);
		panel_4.add(lblDecoupled);
		
		JLabel lblFrom = new JLabel("from");
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
		lblFrom.setBounds(376, 63, 34, 20);
		panel_4.add(lblFrom);
		
		JLabel lblThem = new JLabel("them,");
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
		lblThem.setBounds(414, 63, 39, 20);
		panel_4.add(lblThem);
		
		JLabel label_13 = new JLabel("we");
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
		label_13.setForeground(Color.BLACK);
		label_13.setBackground(Color.white);
		label_13.setOpaque(true);
		label_13.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_13.setBounds(460, 63, 19, 20);
		panel_4.add(label_13);
		
		JLabel label_14 = new JLabel("have");
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
		label_14.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_14.setBackground(Color.white);
		label_14.setOpaque(true);
		label_14.setBounds(485, 63, 35, 20);
		panel_4.add(label_14);
		
		lblDecohered = new JLabel("decohered");
		lblDecohered.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
					highlightCorrectWord.add(lblDecohered.getText().trim());
					System.out.println(highlightCorrectWord.size());
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
					highlightCorrectWord.remove(lblDecohered.getText().trim());
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
		lblDecohered.setForeground(Color.BLACK);
		lblDecohered.setBackground(Color.white);
		lblDecohered.setOpaque(true);
		lblDecohered.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDecohered.setBounds(524, 63, 70, 20);
		panel_4.add(lblDecohered);
		
		JLabel label_15 = new JLabel("from");
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
		label_15.setForeground(Color.BLACK);
		label_15.setBackground(Color.white);
		label_15.setOpaque(true);
		label_15.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_15.setBounds(599, 63, 34, 20);
		panel_4.add(label_15);
		
		JLabel lblThem_1 = new JLabel("them.");
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
		lblThem_1.setBounds(637, 63, 39, 20);
		panel_4.add(lblThem_1);
		
		JLabel lblSoIn = new JLabel("So in");
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
		lblSoIn.setBounds(682, 63, 34, 20);
		panel_4.add(lblSoIn);
		
		JLabel label_16 = new JLabel("other");
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
		label_16.setForeground(Color.BLACK);
		label_16.setBackground(Color.white);
		label_16.setOpaque(true);
		label_16.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_16.setBounds(717, 63, 34, 20);
		panel_4.add(label_16);
		
		JLabel lblWords = new JLabel("words,");
		lblWords.addMouseListener(new MouseAdapter() {
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
		lblWords.setForeground(Color.BLACK);
		lblWords.setBackground(Color.white);
		lblWords.setOpaque(true);
		lblWords.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblWords.setBounds(757, 63, 43, 20);
		panel_4.add(lblWords);
		
		JLabel lblDjVu = new JLabel("D\u00E9j\u00E0 Vu");
		lblDjVu.addMouseListener(new MouseAdapter() {
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
		lblDjVu.setForeground(Color.BLACK);
		lblDjVu.setOpaque(true);
		lblDjVu.setBackground(Color.white);
		lblDjVu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDjVu.setBounds(805, 63, 52, 20);
		panel_4.add(lblDjVu);
		
		JLabel lblProbably = new JLabel("probably");
		lblProbably.addMouseListener(new MouseAdapter() {
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
		lblProbably.setForeground(Color.BLACK);
		lblProbably.setOpaque(true);
		lblProbably.setBackground(Color.white);
		lblProbably.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblProbably.setBounds(878, 63, 58, 20);
		panel_4.add(lblProbably);
		
		JLabel label_17 = new JLabel("is");
		label_17.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_17.setBounds(863, 63, 9, 20);
		panel_4.add(label_17);
		
		lblBasically = new JLabel("basically");
		lblBasically.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
					highlightCorrectWord.add(lblBasically.getText());
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
					highlightCorrectWord.remove(lblBasically.getText());
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
		lblBasically.setBounds(940, 63, 52, 20);
		panel_4.add(lblBasically);
		
		JLabel lblFragment = new JLabel("fragment ");
		lblFragment.addMouseListener(new MouseAdapter() {
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
		lblFragment.setForeground(Color.BLACK);
		lblFragment.setOpaque(true);
		lblFragment.setBackground(Color.white);
		lblFragment.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFragment.setBounds(1010, 63, 65, 20);
		panel_4.add(lblFragment);
		
		JLabel lblA = new JLabel("a");
		lblA.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblA.setBounds(997, 63, 9, 20);
		panel_4.add(lblA);
		
		JLabel lblOfOur = new JLabel("of our");
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
		lblOfOur.setBounds(10, 88, 41, 20);
		panel_4.add(lblOfOur);
		
		JLabel lblBrain = new JLabel("brain");
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
		lblBrain.setBounds(55, 88, 32, 20);
		panel_4.add(lblBrain);
		
		JLabel lblEliciting = new JLabel("eliciting");
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
		lblEliciting.setBounds(91, 88, 45, 20);
		panel_4.add(lblEliciting);
		
		JLabel lblMemories = new JLabel("memories");
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
		lblMemories.setBounds(141, 88, 66, 20);
		panel_4.add(lblMemories);
		
		JLabel lblAnd_1 = new JLabel("and");
		lblAnd_1.setForeground(Color.BLACK);
		lblAnd_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAnd_1.setBounds(211, 88, 26, 20);
		panel_4.add(lblAnd_1);
		
		JLabel lblFragments = new JLabel("fragments");
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
		lblFragments.setBounds(241, 88, 70, 20);
		panel_4.add(lblFragments);
		
		JLabel lblOfPrevious = new JLabel("of previous");
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
		lblOfPrevious.setBounds(313, 88, 73, 20);
		panel_4.add(lblOfPrevious);
		
		JLabel lblSituations = new JLabel("situations.");
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
		lblSituations.setBounds(391, 88, 65, 20);
		panel_4.add(lblSituations);
		
		JLabel lblHowever = new JLabel("However,");
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
		lblHowever.setBounds(460, 88, 62, 20);
		panel_4.add(lblHowever);
		
		JLabel lblInQuantum_1 = new JLabel("in quantum");
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
		lblInQuantum_1.setBounds(528, 88, 78, 20);
		panel_4.add(lblInQuantum_1);
		
		JLabel lblPhysics_1 = new JLabel("physics");
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
		lblPhysics_1.setBounds(607, 88, 52, 20);
		panel_4.add(lblPhysics_1);
		
		JLabel label_18 = new JLabel("they");
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
		label_18.setForeground(Color.BLACK);
		label_18.setOpaque(true);
		label_18.setBackground(Color.white);
		label_18.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_18.setBounds(660, 88, 31, 20);
		panel_4.add(label_18);
		
		JLabel lblReally = new JLabel("really");
		lblReally.addMouseListener(new MouseAdapter() {
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
		lblReally.setForeground(Color.BLACK);
		lblReally.setOpaque(true);
		lblReally.setBackground(Color.white);
		lblReally.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblReally.setBounds(696, 88, 35, 20);
		panel_4.add(lblReally);
		
		JLabel lblAre = new JLabel("are,");
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
		lblAre.setForeground(Color.BLACK);
		lblAre.setOpaque(true);
		lblAre.setBackground(Color.white);
		lblAre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAre.setBounds(736, 88, 27, 20);
		panel_4.add(lblAre);
		
		JLabel lblInSome = new JLabel("in some");
		lblInSome.setForeground(Color.BLACK);
		lblInSome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblInSome.setBounds(767, 88, 52, 20);
		panel_4.add(lblInSome);
		
		lblWay = new JLabel("way,");
		lblWay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
					highlightCorrectWord.add(lblWay.getText());
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
					highlightCorrectWord.remove(lblWay.getText());
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
		lblWay.setBounds(824, 88, 32, 20);
		panel_4.add(lblWay);
		
		JLabel lblParallel = new JLabel("parallel");
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
		lblParallel.setForeground(Color.BLACK);
		lblParallel.setOpaque(true);
		lblParallel.setBackground(Color.white);
		lblParallel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblParallel.setBounds(859, 88, 45, 20);
		panel_4.add(lblParallel);
		
		JLabel lblUniverses = new JLabel("universes");
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
		lblUniverses.setBounds(909, 88, 62, 20);
		panel_4.add(lblUniverses);
		
		JLabel lblSurroundingUs = new JLabel("surrounding us.");
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
		lblSurroundingUs.setBounds(977, 88, 108, 20);
		panel_4.add(lblSurroundingUs);
		
		JLabel lblThe_1 = new JLabel("The");
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
		lblThe_1.setBounds(10, 114, 27, 20);
		panel_4.add(lblThe_1);
		
		JLabel lblProblem = new JLabel("problem");
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
		lblProblem.setBounds(41, 114, 55, 20);
		panel_4.add(lblProblem);
		
		JLabel label_19 = new JLabel("is");
		label_19.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_19.setBounds(98, 114, 9, 20);
		panel_4.add(label_19);
		
		JLabel label_20 = new JLabel("we");
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
		label_20.setBackground(Color.white);
		label_20.setForeground(Color.BLACK);
		label_20.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_20.setBounds(113, 114, 19, 20);
		panel_4.add(label_20);
		
		JLabel lblCant = new JLabel("can't");
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
		lblCant.setBounds(137, 114, 34, 20);
		panel_4.add(lblCant);
		
		JLabel lblEnter = new JLabel("enter");
		lblEnter.addMouseListener(new MouseAdapter() {
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
		lblEnter.setOpaque(true);
		lblEnter.setBackground(Color.white);
		lblEnter.setForeground(Color.BLACK);
		lblEnter.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEnter.setBounds(175, 114, 34, 20);
		panel_4.add(lblEnter);
		
		JLabel lblThem_2 = new JLabel("them");
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
		lblThem_2.setBounds(215, 114, 35, 20);
		panel_4.add(lblThem_2);
		
		JLabel lblBecause_1 = new JLabel("because");
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
		lblBecause_1.setBounds(255, 114, 55, 20);
		panel_4.add(lblBecause_1);
		
		JLabel label_21 = new JLabel("we");
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
		label_21.setBackground(Color.white);
		label_21.setForeground(Color.BLACK);
		label_21.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_21.setBounds(317, 114, 19, 20);
		panel_4.add(label_21);
		
		JLabel label_22 = new JLabel("have");
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
		label_22.setBackground(Color.white);
		label_22.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_22.setBounds(341, 114, 35, 20);
		panel_4.add(label_22);
		
		JLabel lblDecohered_1 = new JLabel("decohered");
		lblDecohered_1.addMouseListener(new MouseAdapter() {
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
		lblDecohered_1.setForeground(Color.BLACK);
		lblDecohered_1.setOpaque(true);
		lblDecohered_1.setBackground(Color.white);
		lblDecohered_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDecohered_1.setBounds(380, 114, 70, 20);
		panel_4.add(lblDecohered_1);
		
		JLabel label_23 = new JLabel("from");
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
		label_23.setBackground(Color.white);
		label_23.setForeground(Color.BLACK);
		label_23.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_23.setBounds(456, 114, 34, 20);
		panel_4.add(label_23);
		
		JLabel label_24 = new JLabel("them.");
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
		label_24.setBackground(Color.white);
		label_24.setForeground(Color.BLACK);
		label_24.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_24.setBounds(494, 114, 39, 20);
		panel_4.add(label_24);
		
		JLabel label = new JLabel("\u00A9Lab Symbiotic");
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.DARK_GRAY);
		label.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		label.setBounds(0, 704, 1370, 24);
		frame.getContentPane().add(label);
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
					PreparedStatement pstmt=conn.prepareStatement("SELECT listeningHighlightWord FROM test1question WHERE rowid=?;");
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
				ois.close();
				fis.close();
				answer.delete();
				la.highlightCorrectWord.clear();
				la.highlightCorrectWord.addAll(highlightCorrectWord);
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
