package set1listening;

import java.awt.Color;
import java.awt.Cursor;
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
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import main.Functions;
import main.ListeningAnswer;
import main.Menu;
import main.sqlConnection;
import javax.swing.JPanel;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JProgressBar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;

public class Set1listening17 {

	public JFrame frame;
	Player p;
	JLabel statusLabel,lblInevitable,lblConsistent,lblGuts,lblBath,lblDuty,lblProperly,lblBecause;
	JProgressBar progressBar;
	ArrayList<String> highlightCorrectWord=new ArrayList<String>();
	public int val=4;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Set1listening17 window = new Set1listening17();
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
	public Set1listening17() {
		initialize();
		timeCount();
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
					
					if(p!=null)
					{
						p.close();
						p=null;
					}
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
					frame.dispose();
					Menu go=new Menu();
					go.frame.setVisible(true);
					go.frame.setLocationRelativeTo(null);
				}
				else frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			}
		});
		//frame.setBounds(100, 100, 450, 300);
		frame.setIconImage(Functions.setIcon());
		frame.getContentPane().setBackground(Color.WHITE);
		frame.pack();
		frame.setBackground(Color.WHITE);
		frame.setIconImage(Functions.setIcon());
		frame.setTitle("Listening Test");
		frame.setFocusable(true);
		frame.setSize(new Dimension(screenSize.width, screenSize.height));
		frame.getContentPane().setPreferredSize(new Dimension(screenSize.width, screenSize.height));
		frame.setAlwaysOnTop(true);
		frame.setResizable(false);
		frame.setLocation(0, 0);
		frame.getContentPane().setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new LineBorder(new Color(64, 64, 64), 1, true));
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(134, 341, 1120, 263);
		frame.getContentPane().add(panel_3);
		
		JLabel lblLike = new JLabel("like");
		lblLike.setOpaque(true);
		lblLike.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		
		JLabel label_7 = new JLabel("from");
		label_7.setOpaque(true);
		label_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_7.setBackground(Color.WHITE);
		label_7.setBounds(763, 11, 34, 20);
		panel_3.add(label_7);
		
		JLabel lblSaturday = new JLabel("Saturday");
		lblSaturday.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblSaturday.setOpaque(true);
		lblSaturday.setForeground(Color.BLACK);
		lblSaturday.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSaturday.setBackground(Color.WHITE);
		lblSaturday.setBounds(799, 11, 59, 20);
		panel_3.add(lblSaturday);
		
		JLabel lblFrom = new JLabel("from");
		lblFrom.setOpaque(true);
		lblFrom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblFrom.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFrom.setBackground(Color.WHITE);
		lblFrom.setBounds(55, 11, 34, 20);
		panel_3.add(lblFrom);
		
		JLabel lblDating = new JLabel("dating.");
		lblDating.setOpaque(true);
		lblDating.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblDating.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDating.setBackground(Color.WHITE);
		lblDating.setBounds(982, 37, 44, 20);
		panel_3.add(lblDating);
		
		JLabel lblTo = new JLabel("to");
		lblTo.setBackground(Color.WHITE);
		lblTo.setOpaque(true);
		lblTo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblTo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTo.setBounds(488, 11, 15, 20);
		panel_3.add(lblTo);
		
		JLabel lblOf = new JLabel("of");
		lblOf.setBackground(Color.WHITE);
		lblOf.setOpaque(true);
		lblOf.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblOf.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblOf.setBounds(378, 11, 15, 20);
		panel_3.add(lblOf);
		
		JLabel lblCommitment = new JLabel("commitment");
		lblCommitment.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblCommitment.setOpaque(true);
		lblCommitment.setForeground(Color.BLACK);
		lblCommitment.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCommitment.setBackground(Color.WHITE);
		lblCommitment.setBounds(398, 11, 82, 20);
		panel_3.add(lblCommitment);
		
		JLabel lblPeele = new JLabel("peele,");
		lblPeele.setOpaque(true);
		lblPeele.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblPeele.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPeele.setBackground(Color.WHITE);
		lblPeele.setBounds(974, 11, 40, 20);
		panel_3.add(lblPeele);
		
		JLabel label_5 = new JLabel("Because");
		label_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_5.setOpaque(true);
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_5.setBackground(Color.WHITE);
		label_5.setBounds(600, 11, 55, 20);
		panel_3.add(label_5);
		
		JLabel lblActors = new JLabel("actors");
		lblActors.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblActors.setOpaque(true);
		lblActors.setForeground(Color.BLACK);
		lblActors.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblActors.setBackground(Color.WHITE);
		lblActors.setBounds(239, 37, 41, 20);
		panel_3.add(lblActors);
		
		JLabel lblWe = new JLabel("we");
		lblWe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblWe.setOpaque(true);
		lblWe.setForeground(Color.BLACK);
		lblWe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblWe.setBackground(Color.WHITE);
		lblWe.setBounds(141, 11, 21, 20);
		panel_3.add(lblWe);
		
		JLabel lblFinally = new JLabel("Finally");
		lblFinally.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblFinally.setOpaque(true);
		lblFinally.setForeground(Color.BLACK);
		lblFinally.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFinally.setBackground(Color.WHITE);
		lblFinally.setBounds(10, 11, 41, 20);
		panel_3.add(lblFinally);
		
		JLabel lblKind = new JLabel("kind");
		lblKind.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblKind.setOpaque(true);
		lblKind.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblKind.setBackground(Color.WHITE);
		lblKind.setBounds(900, 37, 27, 20);
		panel_3.add(lblKind);
		
		JLabel label_13 = new JLabel("Because");
		label_13.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
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
		label_13.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_13.setBackground(Color.WHITE);
		label_13.setBounds(817, 37, 55, 20);
		panel_3.add(label_13);
		
		JLabel label_14 = new JLabel("of");
		label_14.setBackground(Color.WHITE);
		label_14.setOpaque(true);
		label_14.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
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
		label_14.setBounds(931, 37, 15, 20);
		panel_3.add(label_14);
		
		JLabel label_10 = new JLabel("the");
		label_10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
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
		label_10.setBounds(212, 37, 21, 20);
		panel_3.add(label_10);
		
		JLabel lblAbout = new JLabel("about");
		lblAbout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
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
		lblAbout.setBounds(229, 11, 41, 20);
		panel_3.add(lblAbout);
		
		JLabel lblSketch = new JLabel("sketch,");
		lblSketch.setBackground(Color.WHITE);
		lblSketch.setOpaque(true);
		lblSketch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblSketch.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSketch.setBounds(91, 11, 48, 20);
		panel_3.add(lblSketch);
		
		JLabel lblSo = new JLabel("so");
		lblSo.setBackground(Color.WHITE);
		lblSo.setOpaque(true);
		lblSo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblSo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSo.setBounds(62, 37, 15, 20);
		panel_3.add(lblSo);
		
		JLabel lblIn = new JLabel("in");
		lblIn.setBackground(Color.WHITE);
		lblIn.setOpaque(true);
		lblIn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblIn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIn.setBounds(671, 37, 12, 20);
		panel_3.add(lblIn);
		
		JLabel lblLearn = new JLabel("learn");
		lblLearn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblLearn.setOpaque(true);
		lblLearn.setForeground(Color.BLACK);
		lblLearn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLearn.setBackground(Color.WHITE);
		lblLearn.setBounds(192, 11, 34, 20);
		panel_3.add(lblLearn);
		
		lblBecause = new JLabel("because");
		lblBecause.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
					highlightCorrectWord.add(lblBecause.getText().trim());
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
					highlightCorrectWord.remove(lblBecause.getText().trim());
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblBecause.setOpaque(true);
		lblBecause.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBecause.setBackground(Color.WHITE);
		lblBecause.setBounds(150, 37, 55, 20);
		panel_3.add(lblBecause);
		
		JLabel lblLive = new JLabel("live,");
		lblLive.setOpaque(true);
		lblLive.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblLive.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLive.setBackground(Color.WHITE);
		lblLive.setBounds(900, 11, 25, 20);
		panel_3.add(lblLive);
		
		JLabel lblPython = new JLabel("Python");
		lblPython.setOpaque(true);
		lblPython.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblPython.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPython.setBackground(Color.WHITE);
		lblPython.setBounds(1066, 11, 48, 20);
		panel_3.add(lblPython);
		
		JLabel lblRight = new JLabel("Right?");
		lblRight.setOpaque(true);
		lblRight.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblRight.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRight.setBackground(Color.WHITE);
		lblRight.setBounds(1037, 37, 44, 20);
		panel_3.add(lblRight);
		
		JLabel lblNight = new JLabel("night");
		lblNight.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblNight.setOpaque(true);
		lblNight.setForeground(Color.BLACK);
		lblNight.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNight.setBackground(Color.WHITE);
		lblNight.setBounds(863, 11, 31, 20);
		panel_3.add(lblNight);
		
		JLabel lblCommit = new JLabel("committed");
		lblCommit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblCommit.setOpaque(true);
		lblCommit.setForeground(Color.BLACK);
		lblCommit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCommit.setBackground(Color.WHITE);
		lblCommit.setBounds(313, 37, 70, 20);
		panel_3.add(lblCommit);
		
		JLabel label_9 = new JLabel("to");
		label_9.setBackground(Color.WHITE);
		label_9.setOpaque(true);
		label_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_9.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_9.setBounds(388, 37, 15, 20);
		panel_3.add(label_9);
		
		JLabel label_4 = new JLabel("the");
		label_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_4.setOpaque(true);
		label_4.setForeground(Color.BLACK);
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_4.setBackground(Color.WHITE);
		label_4.setBounds(272, 11, 21, 20);
		panel_3.add(label_4);
		
		JLabel lblKey = new JLabel("key");
		lblKey.setOpaque(true);
		lblKey.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblKey.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblKey.setBackground(Color.WHITE);
		lblKey.setBounds(931, 11, 23, 20);
		panel_3.add(lblKey);
		
		JLabel lblPerformance_1 = new JLabel("performance");
		lblPerformance_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblPerformance_1.setOpaque(true);
		lblPerformance_1.setForeground(Color.BLACK);
		lblPerformance_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPerformance_1.setBackground(Color.WHITE);
		lblPerformance_1.setBounds(436, 37, 84, 20);
		panel_3.add(lblPerformance_1);
		
		lblInevitable = new JLabel("inevitable");
		lblInevitable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
					highlightCorrectWord.add(lblInevitable.getText());
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
					highlightCorrectWord.remove(lblInevitable.getText());
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblInevitable.setOpaque(true);
		lblInevitable.setForeground(Color.BLACK);
		lblInevitable.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblInevitable.setBackground(Color.WHITE);
		lblInevitable.setBounds(84, 37, 59, 20);
		panel_3.add(lblInevitable);
		
		JLabel label_12 = new JLabel("they're");
		label_12.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
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
		label_12.setBounds(555, 37, 46, 20);
		panel_3.add(label_12);
		
		JLabel lblAre = new JLabel("are");
		lblAre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
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
		lblAre.setBounds(286, 37, 22, 20);
		panel_3.add(lblAre);
		
		JLabel label_6 = new JLabel("the");
		label_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
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
		label_6.setForeground(Color.BLACK);
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_6.setBackground(Color.WHITE);
		label_6.setBounds(661, 11, 21, 20);
		panel_3.add(label_6);
		
		JLabel lblTheyre = new JLabel("they're");
		lblTheyre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblTheyre.setOpaque(true);
		lblTheyre.setForeground(Color.BLACK);
		lblTheyre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTheyre.setBackground(Color.WHITE);
		lblTheyre.setBounds(10, 37, 46, 20);
		panel_3.add(lblTheyre);
		
		JLabel lblMonty = new JLabel("Monty");
		lblMonty.setOpaque(true);
		lblMonty.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblMonty.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMonty.setBackground(Color.WHITE);
		lblMonty.setBounds(1018, 11, 43, 20);
		panel_3.add(lblMonty);
		
		JLabel lblIts = new JLabel("it's");
		lblIts.setBackground(Color.WHITE);
		lblIts.setOpaque(true);
		lblIts.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblIts.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIts.setBounds(876, 37, 18, 20);
		panel_3.add(lblIts);
		
		JLabel label_8 = new JLabel("&");
		label_8.setBackground(Color.WHITE);
		label_8.setOpaque(true);
		label_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_8.setBounds(958, 11, 10, 20);
		panel_3.add(label_8);
		
		JLabel label_11 = new JLabel("the");
		label_11.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
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
		label_11.setBounds(408, 37, 21, 20);
		panel_3.add(label_11);
		
		JLabel lblCharacters = new JLabel("characters");
		lblCharacters.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblCharacters.setOpaque(true);
		lblCharacters.setForeground(Color.BLACK);
		lblCharacters.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCharacters.setBackground(Color.WHITE);
		lblCharacters.setBounds(688, 11, 70, 20);
		panel_3.add(lblCharacters);
		
		JLabel lblTheir = new JLabel("their");
		lblTheir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblTheir.setOpaque(true);
		lblTheir.setForeground(Color.BLACK);
		lblTheir.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTheir.setBackground(Color.WHITE);
		lblTheir.setBounds(688, 37, 29, 20);
		panel_3.add(lblTheir);
		
		JLabel lblImportance = new JLabel("importance");
		lblImportance.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblImportance.setOpaque(true);
		lblImportance.setForeground(Color.BLACK);
		lblImportance.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblImportance.setBackground(Color.WHITE);
		lblImportance.setBounds(299, 11, 75, 20);
		panel_3.add(lblImportance);
		
		JLabel lblCan = new JLabel("can");
		lblCan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
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
		lblCan.setBounds(164, 11, 25, 20);
		panel_3.add(lblCan);
		
		JLabel lblPerformance = new JLabel("performance.");
		lblPerformance.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblPerformance.setOpaque(true);
		lblPerformance.setForeground(Color.BLACK);
		lblPerformance.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPerformance.setBackground(Color.WHITE);
		lblPerformance.setBounds(508, 11, 88, 20);
		panel_3.add(lblPerformance);
		
		JLabel lblAnd = new JLabel("and");
		lblAnd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblAnd.setOpaque(true);
		lblAnd.setForeground(Color.BLACK);
		lblAnd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAnd.setBackground(Color.WHITE);
		lblAnd.setBounds(524, 37, 25, 20);
		panel_3.add(lblAnd);
		
		JLabel lblPresentation = new JLabel("presentation.");
		lblPresentation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblPresentation.setOpaque(true);
		lblPresentation.setForeground(Color.BLACK);
		lblPresentation.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPresentation.setBackground(Color.WHITE);
		lblPresentation.setBounds(721, 37, 85, 20);
		panel_3.add(lblPresentation);
		
		JLabel lblConfident = new JLabel("confident");
		lblConfident.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblConfident.setOpaque(true);
		lblConfident.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblConfident.setBackground(Color.WHITE);
		lblConfident.setBounds(608, 37, 59, 20);
		panel_3.add(lblConfident);
		lblLike.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLike.setBackground(Color.WHITE);
		lblLike.setBounds(955, 37, 21, 20);
		panel_3.add(lblLike);
		
		JLabel lblPeople = new JLabel("People");
		lblPeople.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblPeople.setOpaque(true);
		lblPeople.setForeground(Color.BLACK);
		lblPeople.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPeople.setBackground(Color.WHITE);
		lblPeople.setBounds(10, 63, 43, 20);
		panel_3.add(lblPeople);
		
		JLabel lblTell = new JLabel("tell");
		lblTell.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblTell.setOpaque(true);
		lblTell.setForeground(Color.BLACK);
		lblTell.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTell.setBackground(Color.WHITE);
		lblTell.setBounds(60, 63, 20, 20);
		panel_3.add(lblTell);
		
		JLabel lblThey = new JLabel("they");
		lblThey.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblThey.setOpaque(true);
		lblThey.setForeground(Color.BLACK);
		lblThey.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblThey.setBackground(Color.WHITE);
		lblThey.setBounds(145, 63, 30, 20);
		panel_3.add(lblThey);
		
		JLabel lblWho = new JLabel("who");
		lblWho.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblWho.setOpaque(true);
		lblWho.setForeground(Color.BLACK);
		lblWho.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblWho.setBackground(Color.WHITE);
		lblWho.setBounds(341, 63, 28, 20);
		panel_3.add(lblWho);
		
		lblConsistent = new JLabel("consistent.");
		lblConsistent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
					highlightCorrectWord.add(lblConsistent.getText());
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
					highlightCorrectWord.remove(lblConsistent.getText());
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblConsistent.setOpaque(true);
		lblConsistent.setForeground(Color.BLACK);
		lblConsistent.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblConsistent.setBackground(Color.WHITE);
		lblConsistent.setBounds(388, 63, 72, 20);
		panel_3.add(lblConsistent);
		
		JLabel lblACouple = new JLabel("A couple");
		lblACouple.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblACouple.setOpaque(true);
		lblACouple.setForeground(Color.BLACK);
		lblACouple.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblACouple.setBackground(Color.WHITE);
		lblACouple.setBounds(508, 63, 55, 20);
		panel_3.add(lblACouple);
		
		JLabel lblYears = new JLabel("years");
		lblYears.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblYears.setOpaque(true);
		lblYears.setForeground(Color.BLACK);
		lblYears.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblYears.setBackground(Color.WHITE);
		lblYears.setBounds(591, 63, 38, 20);
		panel_3.add(lblYears);
		
		JLabel lblHanging = new JLabel("hanging");
		lblHanging.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblHanging.setOpaque(true);
		lblHanging.setForeground(Color.BLACK);
		lblHanging.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblHanging.setBackground(Color.WHITE);
		lblHanging.setBounds(706, 63, 50, 20);
		panel_3.add(lblHanging);
		
		JLabel lblGirl = new JLabel("girl");
		lblGirl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblGirl.setOpaque(true);
		lblGirl.setForeground(Color.BLACK);
		lblGirl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGirl.setBackground(Color.WHITE);
		lblGirl.setBounds(10, 88, 17, 20);
		panel_3.add(lblGirl);
		
		JLabel lblAtThe = new JLabel("at the");
		lblAtThe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblAtThe.setOpaque(true);
		lblAtThe.setForeground(Color.BLACK);
		lblAtThe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAtThe.setBackground(Color.WHITE);
		lblAtThe.setBounds(37, 88, 41, 20);
		panel_3.add(lblAtThe);
		
		JLabel lblLike_1 = new JLabel("like");
		lblLike_1.setBackground(Color.WHITE);
		lblLike_1.setOpaque(true);
		lblLike_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblLike_1.setForeground(Color.BLACK);
		lblLike_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLike_1.setBounds(191, 88, 20, 20);
		panel_3.add(lblLike_1);
		
		JLabel lbloh = new JLabel("\u201COh, ");
		lbloh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lbloh.setOpaque(true);
		lbloh.setForeground(Color.BLACK);
		lbloh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbloh.setBackground(Color.WHITE);
		lbloh.setBounds(215, 88, 35, 20);
		panel_3.add(lbloh);
		
		JLabel lblShould = new JLabel("should");
		lblShould.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblShould.setOpaque(true);
		lblShould.setForeground(Color.BLACK);
		lblShould.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblShould.setBackground(Color.WHITE);
		lblShould.setBounds(286, 88, 41, 20);
		panel_3.add(lblShould);
		
		JLabel lblIWas = new JLabel("i was");
		lblIWas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblIWas.setOpaque(true);
		lblIWas.setForeground(Color.BLACK);
		lblIWas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIWas.setBackground(Color.WHITE);
		lblIWas.setBounds(473, 88, 34, 20);
		panel_3.add(lblIWas);
		
		JLabel lblThat_1 = new JLabel("that.\"");
		lblThat_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblThat_1.setOpaque(true);
		lblThat_1.setForeground(Color.BLACK);
		lblThat_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblThat_1.setBackground(Color.WHITE);
		lblThat_1.setBounds(624, 88, 37, 20);
		panel_3.add(lblThat_1);
		
		JLabel lblYou = new JLabel("you");
		lblYou.setOpaque(true);
		lblYou.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblYou.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblYou.setBackground(Color.WHITE);
		lblYou.setBounds(84, 63, 25, 20);
		panel_3.add(lblYou);
		
		JLabel lblThat = new JLabel("that");
		lblThat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
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
		lblThat.setBounds(113, 63, 28, 20);
		panel_3.add(lblThat);
		
		JLabel lblWant = new JLabel("want");
		lblWant.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
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
		lblWant.setBounds(179, 63, 33, 20);
		panel_3.add(lblWant);
		
		JLabel label_15 = new JLabel("to");
		label_15.setBackground(Color.WHITE);
		label_15.setOpaque(true);
		label_15.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_15.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_15.setBounds(217, 63, 15, 20);
		panel_3.add(label_15);
		
		JLabel lblDate = new JLabel("date");
		lblDate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
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
		lblDate.setBounds(237, 63, 30, 20);
		panel_3.add(lblDate);
		
		JLabel lblSomeone = new JLabel("someone");
		lblSomeone.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblSomeone.setOpaque(true);
		lblSomeone.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSomeone.setBackground(Color.WHITE);
		lblSomeone.setBounds(273, 63, 60, 20);
		panel_3.add(lblSomeone);
		
		JLabel lblIs = new JLabel("is");
		lblIs.setBackground(Color.WHITE);
		lblIs.setOpaque(true);
		lblIs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblIs.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIs.setBounds(372, 63, 10, 20);
		panel_3.add(lblIs);
		
		JLabel label_16 = new JLabel("Right?");
		label_16.setOpaque(true);
		label_16.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_16.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_16.setBackground(Color.WHITE);
		label_16.setBounds(463, 63, 44, 20);
		panel_3.add(label_16);
		
		JLabel label_17 = new JLabel("of");
		label_17.setBackground(Color.WHITE);
		label_17.setOpaque(true);
		label_17.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_17.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_17.setBounds(569, 63, 15, 20);
		panel_3.add(label_17);
		
		JLabel lblAgo = new JLabel("ago");
		lblAgo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblAgo.setOpaque(true);
		lblAgo.setForeground(Color.BLACK);
		lblAgo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAgo.setBackground(Color.WHITE);
		lblAgo.setBounds(635, 63, 25, 20);
		panel_3.add(lblAgo);
		
		JLabel lblI = new JLabel("I");
		lblI.setBackground(Color.WHITE);
		lblI.setOpaque(true);
		lblI.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblI.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblI.setBounds(664, 63, 7, 20);
		panel_3.add(lblI);
		
		JLabel lblWas = new JLabel("was");
		lblWas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblWas.setOpaque(true);
		lblWas.setForeground(Color.BLACK);
		lblWas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblWas.setBackground(Color.WHITE);
		lblWas.setBounds(674, 63, 25, 20);
		panel_3.add(lblWas);
		
		JLabel lblOut = new JLabel("out");
		lblOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblOut.setOpaque(true);
		lblOut.setForeground(Color.BLACK);
		lblOut.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblOut.setBackground(Color.WHITE);
		lblOut.setBounds(761, 63, 22, 20);
		panel_3.add(lblOut);
		
		JLabel lblWith = new JLabel("with");
		lblWith.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblWith.setOpaque(true);
		lblWith.setForeground(Color.BLACK);
		lblWith.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblWith.setBackground(Color.WHITE);
		lblWith.setBounds(788, 63, 26, 20);
		panel_3.add(lblWith);
		
		JLabel lblFriends = new JLabel("friends");
		lblFriends.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblFriends.setOpaque(true);
		lblFriends.setForeground(Color.BLACK);
		lblFriends.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFriends.setBackground(Color.WHITE);
		lblFriends.setBounds(819, 63, 44, 20);
		panel_3.add(lblFriends);
		
		JLabel lblAt = new JLabel("at");
		lblAt.setBackground(Color.WHITE);
		lblAt.setOpaque(true);
		lblAt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblAt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAt.setBounds(867, 63, 15, 20);
		panel_3.add(lblAt);
		
		JLabel label_18 = new JLabel("a");
		label_18.setBackground(Color.WHITE);
		label_18.setOpaque(true);
		label_18.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_18.setBounds(885, 63, 9, 20);
		panel_3.add(label_18);
		
		JLabel lblBar = new JLabel("bar");
		lblBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblBar.setOpaque(true);
		lblBar.setForeground(Color.BLACK);
		lblBar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBar.setBackground(Color.WHITE);
		lblBar.setBounds(900, 63, 25, 20);
		panel_3.add(lblBar);
		
		JLabel label_19 = new JLabel("and");
		label_19.setBackground(Color.WHITE);
		label_19.setOpaque(true);
		label_19.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_19.setForeground(Color.BLACK);
		label_19.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_19.setBounds(928, 63, 26, 20);
		panel_3.add(label_19);
		
		JLabel label_20 = new JLabel("I");
		label_20.setBackground(Color.WHITE);
		label_20.setOpaque(true);
		label_20.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_20.setBounds(958, 63, 7, 20);
		panel_3.add(label_20);
		
		JLabel lblSaw = new JLabel("saw");
		lblSaw.setBackground(Color.WHITE);
		lblSaw.setOpaque(true);
		lblSaw.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblSaw.setForeground(Color.BLACK);
		lblSaw.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSaw.setBounds(971, 63, 26, 20);
		panel_3.add(lblSaw);
		
		JLabel lblThis = new JLabel("this");
		lblThis.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
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
		lblThis.setBounds(1002, 63, 23, 20);
		panel_3.add(lblThis);
		
		JLabel lblBeautiful = new JLabel("beautiful");
		lblBeautiful.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblBeautiful.setOpaque(true);
		lblBeautiful.setForeground(Color.BLACK);
		lblBeautiful.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBeautiful.setBackground(Color.WHITE);
		lblBeautiful.setBounds(1031, 63, 55, 20);
		panel_3.add(lblBeautiful);
		
		JLabel label_21 = new JLabel("bar");
		label_21.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
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
		label_21.setBounds(82, 88, 25, 20);
		panel_3.add(label_21);
		
		JLabel label_22 = new JLabel("and");
		label_22.setBackground(Color.WHITE);
		label_22.setOpaque(true);
		label_22.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_22.setForeground(Color.BLACK);
		label_22.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_22.setBounds(110, 88, 26, 20);
		panel_3.add(label_22);
		
		JLabel label_23 = new JLabel("they're");
		label_23.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
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
		label_23.setBounds(141, 88, 46, 20);
		panel_3.add(label_23);
		
		JLabel label_24 = new JLabel("you");
		label_24.setOpaque(true);
		label_24.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_24.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_24.setBackground(Color.WHITE);
		label_24.setBounds(255, 88, 25, 20);
		panel_3.add(label_24);
		
		JLabel lblGo = new JLabel("go");
		lblGo.setBackground(Color.WHITE);
		lblGo.setOpaque(true);
		lblGo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblGo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGo.setBounds(333, 88, 18, 20);
		panel_3.add(lblGo);
		
		JLabel label_25 = new JLabel("and");
		label_25.setBackground(Color.WHITE);
		label_25.setOpaque(true);
		label_25.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_25.setForeground(Color.BLACK);
		label_25.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_25.setBounds(354, 88, 26, 20);
		panel_3.add(label_25);
		
		JLabel lblTalk = new JLabel("talk");
		lblTalk.setBackground(Color.WHITE);
		lblTalk.setOpaque(true);
		lblTalk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblTalk.setForeground(Color.BLACK);
		lblTalk.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTalk.setBounds(384, 88, 26, 20);
		panel_3.add(lblTalk);
		
		JLabel label_26 = new JLabel("to");
		label_26.setBackground(Color.WHITE);
		label_26.setOpaque(true);
		label_26.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_26.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_26.setBounds(414, 88, 15, 20);
		panel_3.add(label_26);
		
		JLabel lblHer = new JLabel("her.\"");
		lblHer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblHer.setOpaque(true);
		lblHer.setForeground(Color.BLACK);
		lblHer.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblHer.setBackground(Color.WHITE);
		lblHer.setBounds(433, 88, 32, 20);
		panel_3.add(lblHer);
		
		JLabel label_27 = new JLabel("like");
		label_27.setOpaque(true);
		label_27.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_27.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_27.setBackground(Color.WHITE);
		label_27.setBounds(511, 88, 21, 20);
		panel_3.add(label_27);
		
		JLabel lbli = new JLabel("\"I");
		lbli.setBackground(Color.WHITE);
		lbli.setOpaque(true);
		lbli.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lbli.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbli.setBounds(542, 88, 12, 20);
		panel_3.add(lbli);
		
		JLabel label_28 = new JLabel("can't");
		label_28.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
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
		label_28.setBounds(562, 88, 31, 20);
		panel_3.add(label_28);
		
		JLabel lblDo = new JLabel("do");
		lblDo.setBackground(Color.WHITE);
		lblDo.setOpaque(true);
		lblDo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblDo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDo.setBounds(601, 88, 17, 20);
		panel_3.add(lblDo);
		
		JLabel label_29 = new JLabel("they're");
		label_29.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
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
		label_29.setForeground(Color.BLACK);
		label_29.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_29.setBackground(Color.WHITE);
		label_29.setBounds(674, 88, 46, 20);
		panel_3.add(label_29);
		
		JLabel label_30 = new JLabel("like");
		label_30.setBackground(Color.WHITE);
		label_30.setOpaque(true);
		label_30.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_30.setForeground(Color.BLACK);
		label_30.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_30.setBounds(724, 88, 20, 20);
		panel_3.add(label_30);
		
		JLabel lblwhy = new JLabel("\"Why");
		lblwhy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblwhy.setOpaque(true);
		lblwhy.setForeground(Color.BLACK);
		lblwhy.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblwhy.setBackground(Color.WHITE);
		lblwhy.setBounds(754, 88, 36, 20);
		panel_3.add(lblwhy);
		
		JLabel lblNot = new JLabel("not?\"");
		lblNot.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
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
		lblNot.setBounds(796, 88, 36, 20);
		panel_3.add(lblNot);
		
		JLabel label_31 = new JLabel("i was");
		label_31.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
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
		label_31.setForeground(Color.BLACK);
		label_31.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_31.setBackground(Color.WHITE);
		label_31.setBounds(835, 88, 34, 20);
		panel_3.add(label_31);
		
		JLabel label_32 = new JLabel("like");
		label_32.setOpaque(true);
		label_32.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_32.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_32.setBackground(Color.WHITE);
		label_32.setBounds(873, 88, 21, 20);
		panel_3.add(label_32);
		
		JLabel label_33 = new JLabel("\"I");
		label_33.setBackground(Color.WHITE);
		label_33.setOpaque(true);
		label_33.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_33.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_33.setBounds(900, 88, 12, 20);
		panel_3.add(label_33);
		
		JLabel lblDont = new JLabel("don't");
		lblDont.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblDont.setOpaque(true);
		lblDont.setForeground(Color.BLACK);
		lblDont.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDont.setBackground(Color.WHITE);
		lblDont.setBounds(918, 88, 33, 20);
		panel_3.add(lblDont);
		
		JLabel label_34 = new JLabel("have");
		label_34.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
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
		label_34.setBounds(956, 88, 33, 20);
		panel_3.add(label_34);
		
		JLabel lblGame = new JLabel("game.\"");
		lblGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblGame.setOpaque(true);
		lblGame.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGame.setBackground(Color.WHITE);
		lblGame.setBounds(995, 88, 50, 20);
		panel_3.add(lblGame);
		
		JLabel label_35 = new JLabel("they're");
		label_35.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
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
		label_35.setForeground(Color.BLACK);
		label_35.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_35.setBackground(Color.WHITE);
		label_35.setBounds(12, 114, 46, 20);
		panel_3.add(label_35);
		
		JLabel label_36 = new JLabel("like");
		label_36.setBackground(Color.WHITE);
		label_36.setOpaque(true);
		label_36.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_36.setForeground(Color.BLACK);
		label_36.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_36.setBounds(62, 114, 20, 20);
		panel_3.add(label_36);
		
		JLabel lblyou = new JLabel("\"You");
		lblyou.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblyou.setOpaque(true);
		lblyou.setForeground(Color.BLACK);
		lblyou.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblyou.setBackground(Color.WHITE);
		lblyou.setBounds(89, 114, 36, 20);
		panel_3.add(lblyou);
		
		JLabel label_37 = new JLabel("don't");
		label_37.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
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
		label_37.setBounds(127, 114, 33, 20);
		panel_3.add(label_37);
		
		JLabel lblNeed = new JLabel("need");
		lblNeed.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblNeed.setOpaque(true);
		lblNeed.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNeed.setBackground(Color.WHITE);
		lblNeed.setBounds(166, 114, 33, 20);
		panel_3.add(lblNeed);
		
		lblGuts = new JLabel("guts.");
		lblGuts.setBackground(Color.WHITE);
		lblGuts.setOpaque(true);
		lblGuts.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
					highlightCorrectWord.add(lblGuts.getText());
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
					highlightCorrectWord.remove(lblGuts.getText());
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblGuts.setForeground(Color.BLACK);
		lblGuts.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGuts.setBounds(204, 114, 33, 20);
		panel_3.add(lblGuts);
		
		JLabel label_38 = new JLabel("\"You");
		label_38.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
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
		label_38.setBounds(244, 114, 36, 20);
		panel_3.add(label_38);
		
		JLabel lblJust = new JLabel("just");
		lblJust.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
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
		lblJust.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblJust.setBackground(Color.WHITE);
		lblJust.setBounds(284, 114, 24, 20);
		panel_3.add(lblJust);
		
		JLabel label_39 = new JLabel("need");
		label_39.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_39.setOpaque(true);
		label_39.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_39.setBackground(Color.WHITE);
		label_39.setBounds(313, 114, 33, 20);
		panel_3.add(label_39);
		
		JLabel lblConfidence = new JLabel("confidence.\"");
		lblConfidence.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblConfidence.setOpaque(true);
		lblConfidence.setForeground(Color.BLACK);
		lblConfidence.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblConfidence.setBackground(Color.WHITE);
		lblConfidence.setBounds(352, 114, 80, 20);
		panel_3.add(lblConfidence);
		
		JLabel lblBut = new JLabel("But ");
		lblBut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblBut.setOpaque(true);
		lblBut.setForeground(Color.BLACK);
		lblBut.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBut.setBackground(Color.WHITE);
		lblBut.setBounds(10, 155, 27, 20);
		panel_3.add(lblBut);
		
		JLabel lblWhat = new JLabel("what");
		lblWhat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
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
		lblWhat.setBounds(38, 155, 34, 20);
		panel_3.add(lblWhat);
		
		JLabel lblThey_1 = new JLabel("they");
		lblThey_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
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
		lblThey_1.setForeground(Color.BLACK);
		lblThey_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblThey_1.setBackground(Color.WHITE);
		lblThey_1.setBounds(75, 155, 31, 20);
		panel_3.add(lblThey_1);
		
		JLabel label_40 = new JLabel("don't");
		label_40.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_40.setOpaque(true);
		label_40.setForeground(Color.BLACK);
		label_40.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_40.setBackground(Color.WHITE);
		label_40.setBounds(110, 155, 32, 20);
		panel_3.add(label_40);
		
		JLabel lblTell_1 = new JLabel("tell");
		lblTell_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblTell_1.setOpaque(true);
		lblTell_1.setForeground(Color.BLACK);
		lblTell_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTell_1.setBackground(Color.WHITE);
		lblTell_1.setBounds(148, 155, 20, 20);
		panel_3.add(lblTell_1);
		
		JLabel lblYou_1 = new JLabel("you");
		lblYou_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
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
		lblYou_1.setBounds(172, 155, 27, 20);
		panel_3.add(lblYou_1);
		
		JLabel lblThat_2 = new JLabel("that");
		lblThat_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblThat_2.setOpaque(true);
		lblThat_2.setForeground(Color.BLACK);
		lblThat_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblThat_2.setBackground(Color.WHITE);
		lblThat_2.setBounds(217, 155, 28, 20);
		panel_3.add(lblThat_2);
		
		JLabel label_41 = new JLabel("is");
		label_41.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_41.setBackground(Color.WHITE);
		label_41.setOpaque(true);
		label_41.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_41.setBounds(202, 155, 9, 20);
		panel_3.add(label_41);
		
		JLabel label_42 = new JLabel("they");
		label_42.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_42.setOpaque(true);
		label_42.setForeground(Color.BLACK);
		label_42.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_42.setBackground(Color.WHITE);
		label_42.setBounds(249, 155, 31, 20);
		panel_3.add(label_42);
		
		JLabel label_43 = new JLabel("want");
		label_43.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_43.setOpaque(true);
		label_43.setForeground(Color.BLACK);
		label_43.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_43.setBackground(Color.WHITE);
		label_43.setBounds(284, 155, 33, 20);
		panel_3.add(label_43);
		
		JLabel lblThat_3 = new JLabel("that");
		lblThat_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblThat_3.setOpaque(true);
		lblThat_3.setForeground(Color.BLACK);
		lblThat_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblThat_3.setBackground(Color.WHITE);
		lblThat_3.setBounds(321, 155, 28, 20);
		panel_3.add(lblThat_3);
		
		JLabel lblConfidence_1 = new JLabel("confidence");
		lblConfidence_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblConfidence_1.setOpaque(true);
		lblConfidence_1.setForeground(Color.BLACK);
		lblConfidence_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblConfidence_1.setBackground(Color.WHITE);
		lblConfidence_1.setBounds(353, 155, 70, 20);
		panel_3.add(lblConfidence_1);
		
		JLabel label_44 = new JLabel("in");
		label_44.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_44.setBackground(Color.WHITE);
		label_44.setOpaque(true);
		label_44.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_44.setBounds(427, 155, 12, 20);
		panel_3.add(label_44);
		
		JLabel lblCertain = new JLabel("certain");
		lblCertain.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblCertain.setOpaque(true);
		lblCertain.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCertain.setBackground(Color.WHITE);
		lblCertain.setBounds(441, 155, 45, 20);
		panel_3.add(lblCertain);
		
		JLabel lblAreas = new JLabel("areas.");
		lblAreas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblAreas.setOpaque(true);
		lblAreas.setForeground(Color.BLACK);
		lblAreas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAreas.setBackground(Color.WHITE);
		lblAreas.setBounds(489, 155, 42, 20);
		panel_3.add(lblAreas);
		
		JLabel label_45 = new JLabel("Because");
		label_45.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_45.setOpaque(true);
		label_45.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_45.setBackground(Color.WHITE);
		label_45.setBounds(541, 155, 55, 20);
		panel_3.add(label_45);
		
		JLabel lblNo = new JLabel("no");
		lblNo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblNo.setBackground(Color.WHITE);
		lblNo.setOpaque(true);
		lblNo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNo.setBounds(602, 155, 17, 20);
		panel_3.add(lblNo);
		
		JLabel lblWoman = new JLabel("woman");
		lblWoman.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblWoman.setOpaque(true);
		lblWoman.setForeground(Color.BLACK);
		lblWoman.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblWoman.setBackground(Color.WHITE);
		lblWoman.setBounds(623, 155, 49, 20);
		panel_3.add(lblWoman);
		
		JLabel lblWants = new JLabel("wants");
		lblWants.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
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
		lblWants.setBounds(676, 155, 38, 20);
		panel_3.add(lblWants);
		
		JLabel label_46 = new JLabel("a");
		label_46.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_46.setBackground(Color.WHITE);
		label_46.setOpaque(true);
		label_46.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_46.setBounds(720, 155, 9, 20);
		panel_3.add(label_46);
		
		JLabel lblMan = new JLabel("man");
		lblMan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblMan.setOpaque(true);
		lblMan.setForeground(Color.BLACK);
		lblMan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMan.setBackground(Color.WHITE);
		lblMan.setBounds(734, 155, 30, 20);
		panel_3.add(lblMan);
		
		JLabel lblWhos = new JLabel("who's");
		lblWhos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblWhos.setOpaque(true);
		lblWhos.setForeground(Color.BLACK);
		lblWhos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblWhos.setBackground(Color.WHITE);
		lblWhos.setBounds(769, 155, 36, 20);
		panel_3.add(lblWhos);
		
		JLabel lblConfident_1 = new JLabel("confident");
		lblConfident_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblConfident_1.setOpaque(true);
		lblConfident_1.setForeground(Color.BLACK);
		lblConfident_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblConfident_1.setBackground(Color.WHITE);
		lblConfident_1.setBounds(810, 155, 60, 20);
		panel_3.add(lblConfident_1);
		
		lblBath = new JLabel("bath.");
		lblBath.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
					highlightCorrectWord.add(lblBath.getText());
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
					highlightCorrectWord.remove(lblBath.getText());
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblBath.setOpaque(true);
		lblBath.setForeground(Color.BLACK);
		lblBath.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBath.setBackground(Color.WHITE);
		lblBath.setBounds(891, 155, 38, 20);
		panel_3.add(lblBath);
		
		JLabel label_47 = new JLabel("in");
		label_47.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_47.setBackground(Color.WHITE);
		label_47.setOpaque(true);
		label_47.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_47.setBounds(876, 155, 12, 20);
		panel_3.add(label_47);
		
		JLabel lblThats = new JLabel("That's");
		lblThats.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblThats.setOpaque(true);
		lblThats.setForeground(Color.BLACK);
		lblThats.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblThats.setBackground(Color.WHITE);
		lblThats.setBounds(931, 155, 43, 20);
		panel_3.add(lblThats);
		
		JLabel label_48 = new JLabel("what");
		label_48.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_48.setOpaque(true);
		label_48.setForeground(Color.BLACK);
		label_48.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_48.setBackground(Color.WHITE);
		label_48.setBounds(978, 155, 34, 20);
		panel_3.add(label_48);
		
		JLabel label_49 = new JLabel("I");
		label_49.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_49.setBackground(Color.WHITE);
		label_49.setOpaque(true);
		label_49.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_49.setBounds(1015, 155, 7, 20);
		panel_3.add(label_49);
		
		JLabel lblGot = new JLabel("got.");
		lblGot.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblGot.setOpaque(true);
		lblGot.setForeground(Color.BLACK);
		lblGot.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGot.setBackground(Color.WHITE);
		lblGot.setBounds(1028, 155, 27, 20);
		panel_3.add(lblGot);
		
		JLabel lblSo_1 = new JLabel("So,");
		lblSo_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblSo_1.setBackground(Color.WHITE);
		lblSo_1.setOpaque(true);
		lblSo_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSo_1.setBounds(10, 178, 23, 20);
		panel_3.add(lblSo_1);
		
		JLabel lblWas_1 = new JLabel("was");
		lblWas_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblWas_1.setOpaque(true);
		lblWas_1.setForeground(Color.BLACK);
		lblWas_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblWas_1.setBackground(Color.WHITE);
		lblWas_1.setBounds(49, 178, 25, 20);
		panel_3.add(lblWas_1);
		
		JLabel label_51 = new JLabel("I");
		label_51.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_51.setBackground(Color.WHITE);
		label_51.setOpaque(true);
		label_51.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_51.setBounds(38, 178, 7, 20);
		panel_3.add(label_51);
		
		JLabel lblLike_2 = new JLabel("like,");
		lblLike_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblLike_2.setBackground(Color.WHITE);
		lblLike_2.setOpaque(true);
		lblLike_2.setForeground(Color.BLACK);
		lblLike_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLike_2.setBounds(81, 178, 26, 20);
		panel_3.add(lblLike_2);
		
		JLabel lblAlright = new JLabel("alright,");
		lblAlright.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblAlright.setBackground(Color.WHITE);
		lblAlright.setOpaque(true);
		lblAlright.setForeground(Color.BLACK);
		lblAlright.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAlright.setBounds(113, 178, 43, 20);
		panel_3.add(lblAlright);
		
		JLabel lblWell = new JLabel("well");
		lblWell.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
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
		lblWell.setBounds(164, 178, 24, 20);
		panel_3.add(lblWell);
		
		JLabel lblAm = new JLabel("I'm");
		lblAm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblAm.setBackground(Color.WHITE);
		lblAm.setOpaque(true);
		lblAm.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAm.setBounds(200, 178, 22, 20);
		panel_3.add(lblAm);
		
		JLabel lblGonna = new JLabel("gonna");
		lblGonna.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblGonna.setBackground(Color.WHITE);
		lblGonna.setOpaque(true);
		lblGonna.setForeground(Color.BLACK);
		lblGonna.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGonna.setBounds(228, 178, 41, 20);
		panel_3.add(lblGonna);
		
		JLabel lblTry = new JLabel("try");
		lblTry.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblTry.setBackground(Color.WHITE);
		lblTry.setOpaque(true);
		lblTry.setForeground(Color.BLACK);
		lblTry.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTry.setBounds(274, 178, 21, 20);
		panel_3.add(lblTry);
		
		JLabel lblIt = new JLabel("it.");
		lblIt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblIt.setBackground(Color.WHITE);
		lblIt.setOpaque(true);
		lblIt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIt.setBounds(297, 178, 13, 20);
		panel_3.add(lblIt);
		
		JLabel label_50 = new JLabel("try");
		label_50.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_50.setBackground(Color.WHITE);
		label_50.setOpaque(true);
		label_50.setForeground(Color.BLACK);
		label_50.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_50.setBounds(389, 178, 21, 20);
		panel_3.add(label_50);
		
		JLabel label_52 = new JLabel("gonna");
		label_52.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_52.setBackground(Color.WHITE);
		label_52.setOpaque(true);
		label_52.setForeground(Color.BLACK);
		label_52.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_52.setBounds(343, 178, 41, 20);
		panel_3.add(label_52);
		
		JLabel label_53 = new JLabel("I'm");
		label_53.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_53.setBackground(Color.WHITE);
		label_53.setOpaque(true);
		label_53.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_53.setBounds(315, 178, 22, 20);
		panel_3.add(label_53);
		
		JLabel lblMatch = new JLabel("math");
		lblMatch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblMatch.setOpaque(true);
		lblMatch.setForeground(Color.BLACK);
		lblMatch.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMatch.setBackground(Color.WHITE);
		lblMatch.setBounds(414, 178, 35, 20);
		panel_3.add(lblMatch);
		
		JLabel lblPickup = new JLabel("pickup");
		lblPickup.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblPickup.setOpaque(true);
		lblPickup.setForeground(Color.BLACK);
		lblPickup.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPickup.setBackground(Color.WHITE);
		lblPickup.setBounds(453, 178, 42, 20);
		panel_3.add(lblPickup);
		
		JLabel lblLine = new JLabel("line.");
		lblLine.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblLine.setOpaque(true);
		lblLine.setForeground(Color.BLACK);
		lblLine.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLine.setBackground(Color.WHITE);
		lblLine.setBounds(500, 178, 25, 20);
		panel_3.add(lblLine);
		
		JLabel label_54 = new JLabel("So,");
		label_54.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_54.setBackground(Color.WHITE);
		label_54.setOpaque(true);
		label_54.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_54.setBounds(532, 178, 23, 20);
		panel_3.add(label_54);
		
		JLabel label_55 = new JLabel("I");
		label_55.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_55.setBackground(Color.WHITE);
		label_55.setOpaque(true);
		label_55.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_55.setBounds(560, 178, 7, 20);
		panel_3.add(label_55);
		
		JLabel lblWent = new JLabel("went");
		lblWent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblWent.setOpaque(true);
		lblWent.setForeground(Color.BLACK);
		lblWent.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblWent.setBackground(Color.WHITE);
		lblWent.setBounds(571, 178, 33, 20);
		panel_3.add(lblWent);
		
		JLabel lblUp = new JLabel("up");
		lblUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblUp.setBackground(Color.WHITE);
		lblUp.setOpaque(true);
		lblUp.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUp.setBounds(609, 178, 17, 20);
		panel_3.add(lblUp);
		
		JLabel label_56 = new JLabel("to");
		label_56.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_56.setBackground(Color.WHITE);
		label_56.setOpaque(true);
		label_56.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_56.setBounds(630, 178, 15, 20);
		panel_3.add(label_56);
		
		JLabel label_63 = new JLabel("the");
		label_63.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_63.setOpaque(true);
		label_63.setForeground(Color.BLACK);
		label_63.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_63.setBackground(Color.WHITE);
		label_63.setBounds(650, 178, 21, 20);
		panel_3.add(label_63);
		
		JLabel label_64 = new JLabel("girl");
		label_64.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_64.setOpaque(true);
		label_64.setForeground(Color.BLACK);
		label_64.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_64.setBackground(Color.WHITE);
		label_64.setBounds(677, 178, 17, 20);
		panel_3.add(label_64);
		
		JLabel label_65 = new JLabel("and");
		label_65.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_65.setBackground(Color.WHITE);
		label_65.setOpaque(true);
		label_65.setForeground(Color.BLACK);
		label_65.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_65.setBounds(699, 178, 26, 20);
		panel_3.add(label_65);
		
		JLabel label_66 = new JLabel("I");
		label_66.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_66.setBackground(Color.WHITE);
		label_66.setOpaque(true);
		label_66.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_66.setBounds(729, 178, 7, 20);
		panel_3.add(label_66);
		
		JLabel label_67 = new JLabel("was");
		label_67.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_67.setOpaque(true);
		label_67.setForeground(Color.BLACK);
		label_67.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_67.setBackground(Color.WHITE);
		label_67.setBounds(741, 178, 25, 20);
		panel_3.add(label_67);
		
		JLabel lblLike_3 = new JLabel("like");
		lblLike_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblLike_3.setBackground(Color.WHITE);
		lblLike_3.setOpaque(true);
		lblLike_3.setForeground(Color.BLACK);
		lblLike_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLike_3.setBounds(771, 178, 21, 20);
		panel_3.add(lblLike_3);
		
		JLabel lblhey = new JLabel("\"Hey");
		lblhey.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblhey.setOpaque(true);
		lblhey.setForeground(Color.BLACK);
		lblhey.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblhey.setBackground(Color.WHITE);
		lblhey.setBounds(796, 178, 36, 20);
		panel_3.add(lblhey);
		
		JLabel lblGirl_1 = new JLabel("girl.");
		lblGirl_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblGirl_1.setOpaque(true);
		lblGirl_1.setForeground(Color.BLACK);
		lblGirl_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGirl_1.setBackground(Color.WHITE);
		lblGirl_1.setBounds(834, 178, 23, 20);
		panel_3.add(lblGirl_1);
		
		JLabel lblAre_1 = new JLabel("Are");
		lblAre_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblAre_1.setOpaque(true);
		lblAre_1.setForeground(Color.BLACK);
		lblAre_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAre_1.setBackground(Color.WHITE);
		lblAre_1.setBounds(863, 178, 22, 20);
		panel_3.add(lblAre_1);
		
		JLabel label_68 = new JLabel("you");
		label_68.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_68.setOpaque(true);
		label_68.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_68.setBackground(Color.WHITE);
		label_68.setBounds(890, 178, 25, 20);
		panel_3.add(label_68);
		
		JLabel label_69 = new JLabel("a");
		label_69.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_69.setBackground(Color.WHITE);
		label_69.setOpaque(true);
		label_69.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_69.setBounds(919, 178, 9, 20);
		panel_3.add(label_69);
		
		JLabel lblVertical = new JLabel("vertical");
		lblVertical.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblVertical.setOpaque(true);
		lblVertical.setForeground(Color.BLACK);
		lblVertical.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblVertical.setBackground(Color.WHITE);
		lblVertical.setBounds(933, 178, 45, 20);
		panel_3.add(lblVertical);
		
		JLabel lblAsymptote = new JLabel("asymptote?");
		lblAsymptote.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblAsymptote.setBounds(985, 178, 80, 20);
		panel_3.add(lblAsymptote);
		lblAsymptote.setOpaque(true);
		lblAsymptote.setForeground(Color.BLACK);
		lblAsymptote.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAsymptote.setBackground(Color.WHITE);
		
		JLabel lblBecause_1 = new JLabel("because");
		lblBecause_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
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
		lblBecause_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBecause_1.setBackground(Color.WHITE);
		lblBecause_1.setBounds(10, 202, 54, 20);
		panel_3.add(lblBecause_1);
		
		JLabel lblYour = new JLabel("your");
		lblYour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblYour.setOpaque(true);
		lblYour.setForeground(Color.BLACK);
		lblYour.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblYour.setBackground(Color.WHITE);
		lblYour.setBounds(69, 202, 30, 20);
		panel_3.add(lblYour);
		
		lblDuty = new JLabel("duty");
		lblDuty.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
					highlightCorrectWord.add(lblDuty.getText());
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
					highlightCorrectWord.add(lblDuty.getText());
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblDuty.setOpaque(true);
		lblDuty.setForeground(Color.BLACK);
		lblDuty.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDuty.setBackground(Color.WHITE);
		lblDuty.setBounds(102, 202, 30, 20);
		panel_3.add(lblDuty);
		
		JLabel lblHas = new JLabel("has");
		lblHas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblHas.setOpaque(true);
		lblHas.setForeground(Color.BLACK);
		lblHas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblHas.setBackground(Color.WHITE);
		lblHas.setBounds(137, 202, 24, 20);
		panel_3.add(lblHas);
		
		JLabel label_61 = new JLabel("no");
		label_61.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_61.setBackground(Color.WHITE);
		label_61.setOpaque(true);
		label_61.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_61.setBounds(166, 202, 17, 20);
		panel_3.add(label_61);
		
		JLabel lblLimits = new JLabel("limits.\"");
		lblLimits.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblLimits.setOpaque(true);
		lblLimits.setForeground(Color.BLACK);
		lblLimits.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLimits.setBackground(Color.WHITE);
		lblLimits.setBounds(188, 202, 42, 20);
		panel_3.add(lblLimits);
		
		JLabel lblShe = new JLabel("She");
		lblShe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblShe.setOpaque(true);
		lblShe.setForeground(Color.BLACK);
		lblShe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblShe.setBackground(Color.WHITE);
		lblShe.setBounds(240, 202, 25, 20);
		panel_3.add(lblShe);
		
		JLabel label_70 = new JLabel("was");
		label_70.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_70.setOpaque(true);
		label_70.setForeground(Color.BLACK);
		label_70.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_70.setBackground(Color.WHITE);
		label_70.setBounds(269, 202, 25, 20);
		panel_3.add(label_70);
		
		JLabel label_71 = new JLabel("like,");
		label_71.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_71.setBackground(Color.WHITE);
		label_71.setOpaque(true);
		label_71.setForeground(Color.BLACK);
		label_71.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_71.setBounds(301, 202, 26, 20);
		panel_3.add(label_71);
		
		JLabel lblwhat = new JLabel("\"What");
		lblwhat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblwhat.setOpaque(true);
		lblwhat.setForeground(Color.BLACK);
		lblwhat.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblwhat.setBackground(Color.WHITE);
		lblwhat.setBounds(333, 202, 43, 20);
		panel_3.add(lblwhat);
		
		JLabel lblDid = new JLabel("did");
		lblDid.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblDid.setOpaque(true);
		lblDid.setForeground(Color.BLACK);
		lblDid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDid.setBackground(Color.WHITE);
		lblDid.setBounds(381, 202, 20, 20);
		panel_3.add(lblDid);
		
		JLabel label_72 = new JLabel("you");
		label_72.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_72.setOpaque(true);
		label_72.setForeground(Color.BLACK);
		label_72.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_72.setBackground(Color.WHITE);
		label_72.setBounds(404, 202, 27, 20);
		panel_3.add(label_72);
		
		JLabel label_73 = new JLabel("just");
		label_73.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_73.setOpaque(true);
		label_73.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_73.setBackground(Color.WHITE);
		label_73.setBounds(433, 202, 24, 20);
		panel_3.add(label_73);
		
		JLabel lblSay = new JLabel("say?\"");
		lblSay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
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
		lblSay.setForeground(Color.BLACK);
		lblSay.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSay.setBackground(Color.WHITE);
		lblSay.setBounds(463, 202, 40, 20);
		panel_3.add(lblSay);
		
		JLabel label_74 = new JLabel("So,");
		label_74.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_74.setBackground(Color.WHITE);
		label_74.setOpaque(true);
		label_74.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_74.setBounds(508, 202, 23, 20);
		panel_3.add(label_74);
		
		JLabel label_75 = new JLabel("I");
		label_75.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_75.setBackground(Color.WHITE);
		label_75.setOpaque(true);
		label_75.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_75.setBounds(536, 202, 7, 20);
		panel_3.add(label_75);
		
		JLabel lblTried = new JLabel("tried");
		lblTried.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblTried.setOpaque(true);
		lblTried.setForeground(Color.BLACK);
		lblTried.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTried.setBackground(Color.WHITE);
		lblTried.setBounds(547, 202, 30, 20);
		panel_3.add(lblTried);
		
		JLabel lblAgain = new JLabel("again,");
		lblAgain.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblAgain.setOpaque(true);
		lblAgain.setForeground(Color.BLACK);
		lblAgain.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAgain.setBackground(Color.WHITE);
		lblAgain.setBounds(582, 202, 39, 20);
		panel_3.add(lblAgain);
		
		JLabel label_76 = new JLabel("\"Hey");
		label_76.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_76.setOpaque(true);
		label_76.setForeground(Color.BLACK);
		label_76.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_76.setBackground(Color.WHITE);
		label_76.setBounds(624, 202, 36, 20);
		panel_3.add(label_76);
		
		JLabel label_77 = new JLabel("girl.");
		label_77.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_77.setOpaque(true);
		label_77.setForeground(Color.BLACK);
		label_77.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_77.setBackground(Color.WHITE);
		label_77.setBounds(662, 202, 23, 20);
		panel_3.add(label_77);
		
		JLabel label_78 = new JLabel("Are");
		label_78.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_78.setOpaque(true);
		label_78.setForeground(Color.BLACK);
		label_78.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_78.setBackground(Color.WHITE);
		label_78.setBounds(691, 202, 22, 20);
		panel_3.add(label_78);
		
		JLabel label_79 = new JLabel("you");
		label_79.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_79.setOpaque(true);
		label_79.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_79.setBackground(Color.WHITE);
		label_79.setBounds(718, 202, 25, 20);
		panel_3.add(label_79);
		
		JLabel lblOpposite = new JLabel("opposite");
		lblOpposite.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblOpposite.setOpaque(true);
		lblOpposite.setForeground(Color.BLACK);
		lblOpposite.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblOpposite.setBackground(Color.WHITE);
		lblOpposite.setBounds(748, 202, 54, 20);
		panel_3.add(lblOpposite);
		
		JLabel lblOver = new JLabel("over");
		lblOver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblOver.setOpaque(true);
		lblOver.setForeground(Color.BLACK);
		lblOver.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblOver.setBackground(Color.WHITE);
		lblOver.setBounds(808, 202, 30, 20);
		panel_3.add(lblOver);
		
		JLabel lblHypotenuse = new JLabel("hypotenuse?");
		lblHypotenuse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblHypotenuse.setBounds(842, 202, 83, 20);
		panel_3.add(lblHypotenuse);
		lblHypotenuse.setOpaque(true);
		lblHypotenuse.setForeground(Color.BLACK);
		lblHypotenuse.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblHypotenuse.setBackground(Color.WHITE);
		
		JLabel label_58 = new JLabel("because");
		label_58.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_58.setOpaque(true);
		label_58.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_58.setBackground(Color.WHITE);
		label_58.setBounds(931, 202, 54, 20);
		panel_3.add(label_58);
		
		JLabel lblYou_2 = new JLabel("you're");
		lblYou_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblYou_2.setOpaque(true);
		lblYou_2.setForeground(Color.BLACK);
		lblYou_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblYou_2.setBackground(Color.WHITE);
		lblYou_2.setBounds(990, 202, 42, 20);
		panel_3.add(lblYou_2);
		
		JLabel lblMaking = new JLabel("making");
		lblMaking.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblMaking.setOpaque(true);
		lblMaking.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMaking.setBackground(Color.WHITE);
		lblMaking.setBounds(1038, 202, 47, 20);
		panel_3.add(lblMaking);
		
		JLabel lblMe = new JLabel("me");
		lblMe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblMe.setBackground(Color.WHITE);
		lblMe.setOpaque(true);
		lblMe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMe.setBounds(1089, 202, 21, 20);
		panel_3.add(lblMe);
		
		JLabel lblWant_1 = new JLabel("want");
		lblWant_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblWant_1.setOpaque(true);
		lblWant_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblWant_1.setBackground(Color.WHITE);
		lblWant_1.setBounds(10, 227, 34, 20);
		panel_3.add(lblWant_1);
		
		JLabel label_80 = new JLabel("to");
		label_80.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_80.setBackground(Color.WHITE);
		label_80.setOpaque(true);
		label_80.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_80.setBounds(47, 227, 15, 20);
		panel_3.add(label_80);
		
		JLabel lblSin = new JLabel("sin.\"");
		lblSin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblSin.setOpaque(true);
		lblSin.setForeground(Color.BLACK);
		lblSin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSin.setBackground(Color.WHITE);
		lblSin.setBounds(64, 227, 29, 20);
		panel_3.add(lblSin);
		
		JLabel label_81 = new JLabel("She");
		label_81.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_81.setOpaque(true);
		label_81.setForeground(Color.BLACK);
		label_81.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_81.setBackground(Color.WHITE);
		label_81.setBounds(100, 227, 25, 20);
		panel_3.add(label_81);
		
		JLabel label_82 = new JLabel("was");
		label_82.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_82.setOpaque(true);
		label_82.setForeground(Color.BLACK);
		label_82.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_82.setBackground(Color.WHITE);
		label_82.setBounds(129, 227, 25, 20);
		panel_3.add(label_82);
		
		JLabel label_83 = new JLabel("like,");
		label_83.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_83.setBackground(Color.WHITE);
		label_83.setOpaque(true);
		label_83.setForeground(Color.BLACK);
		label_83.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_83.setBounds(161, 227, 26, 20);
		panel_3.add(label_83);
		
		JLabel lblThink = new JLabel("think");
		lblThink.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblThink.setOpaque(true);
		lblThink.setForeground(Color.BLACK);
		lblThink.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblThink.setBackground(Color.WHITE);
		lblThink.setBounds(209, 227, 34, 20);
		panel_3.add(lblThink);
		
		JLabel label_84 = new JLabel("\"I");
		label_84.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_84.setBackground(Color.WHITE);
		label_84.setOpaque(true);
		label_84.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_84.setBounds(191, 227, 12, 20);
		panel_3.add(label_84);
		
		JLabel label_85 = new JLabel("you");
		label_85.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		label_85.setOpaque(true);
		label_85.setForeground(Color.BLACK);
		label_85.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_85.setBackground(Color.WHITE);
		label_85.setBounds(245, 227, 27, 20);
		panel_3.add(label_85);
		
		JLabel lblShould_1 = new JLabel("should");
		lblShould_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblShould_1.setOpaque(true);
		lblShould_1.setForeground(Color.BLACK);
		lblShould_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblShould_1.setBackground(Color.WHITE);
		lblShould_1.setBounds(275, 227, 41, 20);
		panel_3.add(lblShould_1);
		
		 lblProperly = new JLabel("properly");
		lblProperly.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
					highlightCorrectWord.add(lblProperly.getText());
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
					highlightCorrectWord.remove(lblProperly.getText());
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblProperly.setOpaque(true);
		lblProperly.setForeground(Color.BLACK);
		lblProperly.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblProperly.setBackground(Color.WHITE);
		lblProperly.setBounds(322, 227, 55, 20);
		panel_3.add(lblProperly);
		
		JLabel lblLeave = new JLabel("leave.\"");
		lblLeave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent().getBackground().equals(Color.white))
				{
					//arg0.getComponent().setForeground(new Color(165, 42, 42));
					arg0.getComponent().setBackground(Color.YELLOW);
				}
				else
				{
					//arg0.getComponent().setForeground(new Color(0, 0, 0));
					arg0.getComponent().setBackground(Color.white);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblLeave.setOpaque(true);
		lblLeave.setForeground(Color.BLACK);
		lblLeave.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLeave.setBackground(Color.WHITE);
		lblLeave.setBounds(380, 227, 46, 20);
		panel_3.add(lblLeave);
		
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
		panel_2.setLayout(null);
		panel_2.setBorder(new MatteBorder(2, 1, 2, 1, (Color) new Color(51, 0, 153)));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(547, 172, 277, 148);
		frame.getContentPane().add(panel_2);
		
		progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);
		progressBar.setForeground(new Color(0, 51, 102));
		progressBar.setBorder(new LineBorder(new Color(30, 144, 255), 2, true));
		progressBar.setBorderPainted(true);
		progressBar.setStringPainted(true);
		progressBar.setString("");
		progressBar.setBackground(Color.WHITE);
		progressBar.setBounds(22, 104, 234, 26);
		panel_2.add(progressBar);
		
		JLabel label_2 = new JLabel("Status");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(Color.DARK_GRAY);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_2.setBounds(93, 11, 85, 30);
		panel_2.add(label_2);
		
		statusLabel = new JLabel("beginning in 4 seconds");
		statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		statusLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		statusLabel.setBounds(61, 52, 158, 27);
		panel_2.add(statusLabel);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(new LineBorder(Color.DARK_GRAY, 2));
		panel_4.setBackground(new Color(224, 255, 255));
		panel_4.setBounds(134, 615, 154, 33);
		frame.getContentPane().add(panel_4);
		
		JLabel lblItemNo = new JLabel("Item no 14 of 17");
		lblItemNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblItemNo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblItemNo.setBounds(10, 0, 144, 33);
		panel_4.add(lblItemNo);
		
		JButton button_1 = new JButton("Next");
		button_1.addActionListener(new ActionListener() {
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
				Set1listening18 go= new Set1listening18();
				go.frame.setVisible(true);
			}
		});
		button_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_1.setEnabled(true);
		button_1.setBackground(Color.WHITE);
		button_1.setBounds(1165, 615, 89, 28);
		frame.getContentPane().add(button_1);
		
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
						pstmt.setInt(1, 2);  // 2nd listening mcq
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
					la.highlightCorrectWord2.clear();
					la.highlightCorrectWord2.addAll(highlightCorrectWord);
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
				la.highlightCorrectWord2.addAll(highlightCorrectWord);
				FileOutputStream fos=new FileOutputStream(answer,true);
				ObjectOutputStream oos=new ObjectOutputStream(new BufferedOutputStream (fos));
				oos.writeObject(la);
				oos.close();
				fos.close();
			}
		}
}
