package set1reading;

import java.awt.Color;
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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import main.Functions;
import main.Menu;
import main.ReadingAnswer;
import main.sqlConnection;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.TransferHandler;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Set1reading10 {

	public JFrame frame;
	JLabel a1,a2,a3,a4,q1,q2,q3,q4,q5,q6,q7;
	String[] options=new String[7];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Set1reading10 window = new Set1reading10();
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
	public Set1reading10() {
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
		frame = new JFrame();
		frame.getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				organize();
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				organize();
			}
		});
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
					File answer=new File("Answer//ReadingAns.ser");
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
		frame.pack();
		frame.setBackground(Color.WHITE);
		frame.setIconImage(Functions.setIcon());
		frame.setTitle("Reading Test");
		frame.setFocusable(true);
		frame.setSize(new Dimension(screenSize.width, screenSize.height));
		frame.getContentPane().setPreferredSize(new Dimension(screenSize.width, screenSize.height));
		frame.setAlwaysOnTop(true);
		frame.setResizable(false);
		frame.setLocation(0,0);
		frame.getContentPane().setLayout(null);
		
		JLabel lblItemNo = new JLabel("Item no 10 of 15");
		lblItemNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblItemNo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblItemNo.setBounds(151, 640, 118, 24);
		frame.getContentPane().add(lblItemNo);
		
		JPanel panel = new JPanel();
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				organize();
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				organize();
			}
		});
		panel.setLayout(null);
		panel.setBorder(new MatteBorder(2, 0, 2, 0, (Color) new Color(0, 0, 0)));
		panel.setBackground(new Color(204, 255, 204));
		panel.setBounds(151, 438, 1087, 160);
		frame.getContentPane().add(panel);
		
		 q1 = new JLabel("principles");
		 q1.setBackground(Color.WHITE);
		 q1.addMouseListener(new MouseAdapter() {
		 	@Override
		 	public void mousePressed(MouseEvent arg0) {
		 		if(q1.getText().equals(a1.getText()))
				{
					JComponent jc=(JComponent)arg0.getComponent();
					TransferHandler th=jc.getTransferHandler();
					th.exportAsDrag(jc, arg0, TransferHandler.COPY);
					a1.setText(null);
				}
				else if(q1.getText().equals(a2.getText())){
					a2.setText(null);
					JComponent jc=(JComponent)arg0.getComponent();
					TransferHandler th=jc.getTransferHandler();
					th.exportAsDrag(jc, arg0, TransferHandler.COPY);
				}
				else if(q1.getText().equals(a3.getText()))
				{
					a3.setText(null);
					JComponent jc=(JComponent)arg0.getComponent();
					TransferHandler th=jc.getTransferHandler();
					th.exportAsDrag(jc, arg0, TransferHandler.COPY);
				}
				else if(q1.getText().equals(a4.getText()))
				{
					a4.setText(null);
					JComponent jc=(JComponent)arg0.getComponent();
					TransferHandler th=jc.getTransferHandler();
					th.exportAsDrag(jc, arg0, TransferHandler.COPY);
				}
				else{
					JComponent jc=(JComponent)arg0.getComponent();
					TransferHandler th=jc.getTransferHandler();
					th.exportAsDrag(jc, arg0, TransferHandler.COPY);
					
				}
		 	}
		 	
		 	@Override
			public void mouseEntered(MouseEvent arg0) {
				organize();
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				organize();
			}
		 });
		q1.setOpaque(true);
		q1.setHorizontalAlignment(SwingConstants.CENTER);
		q1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		q1.setBorder(new LineBorder(new Color(128, 128, 128)));
		q1.setTransferHandler(new TransferHandler("text"));
		q1.setBounds(64, 31, 185, 29);
		panel.add(q1);
		
		 q2 = new JLabel("revolution");
		 q2.setBackground(Color.WHITE);
		 q2.addMouseListener(new MouseAdapter() {
		 	@Override
		 	public void mousePressed(MouseEvent arg0) {
		 		if(q2.getText().equals(a1.getText()))
				{
					JComponent jc=(JComponent)arg0.getComponent();
					TransferHandler th=jc.getTransferHandler();
					th.exportAsDrag(jc, arg0, TransferHandler.MOVE);
					a1.setText(null);
				}
				else if(q2.getText().equals(a2.getText())){
					a2.setText(null);
					JComponent jc=(JComponent)arg0.getComponent();
					TransferHandler th=jc.getTransferHandler();
					th.exportAsDrag(jc, arg0, TransferHandler.COPY);
				}
				else if(q2.getText().equals(a3.getText()))
				{
					a3.setText(null);
					JComponent jc=(JComponent)arg0.getComponent();
					TransferHandler th=jc.getTransferHandler();
					th.exportAsDrag(jc, arg0, TransferHandler.COPY);
				}
				else if(q2.getText().equals(a4.getText()))
				{
					a4.setText(null);
					JComponent jc=(JComponent)arg0.getComponent();
					TransferHandler th=jc.getTransferHandler();
					th.exportAsDrag(jc, arg0, TransferHandler.COPY);
				}
				else{
					JComponent jc=(JComponent)arg0.getComponent();
					TransferHandler th=jc.getTransferHandler();
					th.exportAsDrag(jc, arg0, TransferHandler.COPY);
					options();
				}
		 	}
		 	@Override
			public void mouseEntered(MouseEvent arg0) {
				organize();
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				organize();
			}
		 });
		q2.setOpaque(true);
		q2.setHorizontalAlignment(SwingConstants.CENTER);
		q2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		q2.setBorder(new LineBorder(new Color(128, 128, 128)));
		q2.setTransferHandler(new TransferHandler("text"));
		q2.setBounds(313, 31, 185, 29);
		panel.add(q2);
		
		 q3 = new JLabel("mistakes");
		 q3.setBackground(Color.WHITE);
		 q3.addMouseListener(new MouseAdapter() {
		 	@Override
		 	public void mousePressed(MouseEvent arg0) {
		 		if(q3.getText().equals(a1.getText()))
				{
					JComponent jc=(JComponent)arg0.getComponent();
					TransferHandler th=jc.getTransferHandler();
					th.exportAsDrag(jc, arg0, TransferHandler.MOVE);
					a1.setText(null);
				}
				else if(q3.getText().equals(a2.getText())){
					a2.setText(null);
					JComponent jc=(JComponent)arg0.getComponent();
					TransferHandler th=jc.getTransferHandler();
					th.exportAsDrag(jc, arg0, TransferHandler.COPY);
				}
				else if(q3.getText().equals(a3.getText()))
				{
					a3.setText(null);
					JComponent jc=(JComponent)arg0.getComponent();
					TransferHandler th=jc.getTransferHandler();
					th.exportAsDrag(jc, arg0, TransferHandler.COPY);
				}
				else if(q3.getText().equals(a4.getText()))
				{
					a4.setText(null);
					JComponent jc=(JComponent)arg0.getComponent();
					TransferHandler th=jc.getTransferHandler();
					th.exportAsDrag(jc, arg0, TransferHandler.COPY);
				}
				else{
					JComponent jc=(JComponent)arg0.getComponent();
					TransferHandler th=jc.getTransferHandler();
					th.exportAsDrag(jc, arg0, TransferHandler.COPY);
					options();
				}
		 	}
		 	@Override
			public void mouseEntered(MouseEvent arg0) {
				organize();
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				organize();
			}
		 });
		q3.setOpaque(true);
		q3.setHorizontalAlignment(SwingConstants.CENTER);
		q3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		q3.setBorder(new LineBorder(new Color(128, 128, 128)));
		q3.setTransferHandler(new TransferHandler("text"));
		q3.setBounds(590, 31, 185, 29);
		panel.add(q3);
		
		 q5 = new JLabel("creation");
		 q5.setBackground(Color.WHITE);
		 q5.addMouseListener(new MouseAdapter() {
		 	@Override
		 	public void mousePressed(MouseEvent arg0) {
		 		if(q5.getText().equals(a1.getText()))
				{
					JComponent jc=(JComponent)arg0.getComponent();
					TransferHandler th=jc.getTransferHandler();
					th.exportAsDrag(jc, arg0, TransferHandler.MOVE);
					a1.setText(null);
				}
				else if(q5.getText().equals(a2.getText())){
					a2.setText(null);
					JComponent jc=(JComponent)arg0.getComponent();
					TransferHandler th=jc.getTransferHandler();
					th.exportAsDrag(jc, arg0, TransferHandler.COPY);
				}
				else if(q5.getText().equals(a3.getText()))
				{
					a3.setText(null);
					JComponent jc=(JComponent)arg0.getComponent();
					TransferHandler th=jc.getTransferHandler();
					th.exportAsDrag(jc, arg0, TransferHandler.COPY);
				}
				else if(q5.getText().equals(a4.getText()))
				{
					a4.setText(null);
					JComponent jc=(JComponent)arg0.getComponent();
					TransferHandler th=jc.getTransferHandler();
					th.exportAsDrag(jc, arg0, TransferHandler.COPY);
				}
				else{
					JComponent jc=(JComponent)arg0.getComponent();
					TransferHandler th=jc.getTransferHandler();
					th.exportAsDrag(jc, arg0, TransferHandler.COPY);
					options();
				}
		 	}
		 	@Override
			public void mouseEntered(MouseEvent arg0) {
				organize();
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				organize();
			}
		 });
		q5.setOpaque(true);
		q5.setHorizontalAlignment(SwingConstants.CENTER);
		q5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		q5.setBorder(new LineBorder(new Color(128, 128, 128)));
		q5.setTransferHandler(new TransferHandler("text"));
		q5.setBounds(64, 100, 185, 29);
		panel.add(q5);
		
		 q6 = new JLabel("role");
		 q6.setBackground(Color.WHITE);
		 q6.addMouseListener(new MouseAdapter() {
		 	@Override
		 	public void mousePressed(MouseEvent arg0) {
		 		if(q6.getText().equals(a1.getText()))
				{
					JComponent jc=(JComponent)arg0.getComponent();
					TransferHandler th=jc.getTransferHandler();
					th.exportAsDrag(jc, arg0, TransferHandler.MOVE);
					a1.setText(null);
				}
				else if(q6.getText().equals(a2.getText())){
					a2.setText(null);
					JComponent jc=(JComponent)arg0.getComponent();
					TransferHandler th=jc.getTransferHandler();
					th.exportAsDrag(jc, arg0, TransferHandler.COPY);
				}
				else if(q6.getText().equals(a3.getText()))
				{
					a3.setText(null);
					JComponent jc=(JComponent)arg0.getComponent();
					TransferHandler th=jc.getTransferHandler();
					th.exportAsDrag(jc, arg0, TransferHandler.COPY);
				}
				else if(q6.getText().equals(a4.getText()))
				{
					a4.setText(null);
					JComponent jc=(JComponent)arg0.getComponent();
					TransferHandler th=jc.getTransferHandler();
					th.exportAsDrag(jc, arg0, TransferHandler.COPY);
				}
				else{
					JComponent jc=(JComponent)arg0.getComponent();
					TransferHandler th=jc.getTransferHandler();
					th.exportAsDrag(jc, arg0, TransferHandler.COPY);
					options();
				}
		 	}
		 	@Override
			public void mouseEntered(MouseEvent arg0) {
				organize();
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				organize();
			}
		 });
		q6.setOpaque(true);
		q6.setHorizontalAlignment(SwingConstants.CENTER);
		q6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		q6.setBorder(new LineBorder(new Color(128, 128, 128)));
		q6.setTransferHandler(new TransferHandler("text"));
		q6.setBounds(313, 100, 185, 29);
		panel.add(q6);
		
		 q4 = new JLabel("identified");
		 q4.setBackground(Color.WHITE);
		 q4.addMouseListener(new MouseAdapter() {
		 	@Override
		 	public void mousePressed(MouseEvent arg0) {
		 		if(q4.getText().equals(a1.getText()))
				{
					JComponent jc=(JComponent)arg0.getComponent();
					TransferHandler th=jc.getTransferHandler();
					th.exportAsDrag(jc, arg0, TransferHandler.MOVE);
					a1.setText(null);
				}
				else if(q4.getText().equals(a2.getText())){
					a2.setText(null);
					JComponent jc=(JComponent)arg0.getComponent();
					TransferHandler th=jc.getTransferHandler();
					th.exportAsDrag(jc, arg0, TransferHandler.COPY);
				}
				else if(q4.getText().equals(a3.getText()))
				{
					a3.setText(null);
					JComponent jc=(JComponent)arg0.getComponent();
					TransferHandler th=jc.getTransferHandler();
					th.exportAsDrag(jc, arg0, TransferHandler.COPY);
				}
				else if(q4.getText().equals(a4.getText()))
				{
					a4.setText(null);
					JComponent jc=(JComponent)arg0.getComponent();
					TransferHandler th=jc.getTransferHandler();
					th.exportAsDrag(jc, arg0, TransferHandler.COPY);
				}
				else{
					JComponent jc=(JComponent)arg0.getComponent();
					TransferHandler th=jc.getTransferHandler();
					th.exportAsDrag(jc, arg0, TransferHandler.COPY);
					options();
				}
		 	}
		 	@Override
			public void mouseEntered(MouseEvent arg0) {
				organize();
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				organize();
			}
		 });
		q4.setOpaque(true);
		q4.setHorizontalAlignment(SwingConstants.CENTER);
		q4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		q4.setBorder(new LineBorder(new Color(128, 128, 128)));
		q4.setTransferHandler(new TransferHandler("text"));
		q4.setBounds(851, 31, 185, 29);
		panel.add(q4);
		
		 q7 = new JLabel("private");
		 q7.setBackground(Color.WHITE);
		 q7.addMouseListener(new MouseAdapter() {
		 	@Override
		 	public void mousePressed(MouseEvent arg0) {
		 		if(q7.getText().equals(a1.getText()))
				{
					JComponent jc=(JComponent)arg0.getComponent();
					TransferHandler th=jc.getTransferHandler();
					th.exportAsDrag(jc, arg0, TransferHandler.MOVE);
					a1.setText(null);
				}
				else if(q7.getText().equals(a2.getText())){
					a2.setText(null);
					JComponent jc=(JComponent)arg0.getComponent();
					TransferHandler th=jc.getTransferHandler();
					th.exportAsDrag(jc, arg0, TransferHandler.COPY);
				}
				else if(q7.getText().equals(a3.getText()))
				{
					a3.setText(null);
					JComponent jc=(JComponent)arg0.getComponent();
					TransferHandler th=jc.getTransferHandler();
					th.exportAsDrag(jc, arg0, TransferHandler.COPY);
				}
				else if(q7.getText().equals(a4.getText()))
				{
					a4.setText(null);
					JComponent jc=(JComponent)arg0.getComponent();
					TransferHandler th=jc.getTransferHandler();
					th.exportAsDrag(jc, arg0, TransferHandler.COPY);
				}
				else{
					JComponent jc=(JComponent)arg0.getComponent();
					TransferHandler th=jc.getTransferHandler();
					th.exportAsDrag(jc, arg0, TransferHandler.COPY);
					options();
				}
		 	}
		 	@Override
			public void mouseEntered(MouseEvent arg0) {
				organize();
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				organize();
			}
		 });
		q7.setOpaque(true);
		q7.setHorizontalAlignment(SwingConstants.CENTER);
		q7.setFont(new Font("Tahoma", Font.PLAIN, 15));
		q7.setBorder(new LineBorder(new Color(128, 128, 128)));
		q7.setTransferHandler(new TransferHandler("text"));
		q7.setBounds(590, 100, 185, 29);
		panel.add(q7);
		
		JPanel panel_1 = new JPanel();
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				organize();
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				organize();
			}
		});
		panel_1.setLayout(null);
		panel_1.setBorder(new MatteBorder(2, 0, 2, 0, (Color) new Color(0, 0, 0)));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(151, 235, 1087, 153);
		frame.getContentPane().add(panel_1);
		
		JLabel lblInTheInterview = new JLabel("In the interview, the French president also stressed that Europe needed to play a bigger ");
		lblInTheInterview.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblInTheInterview.setBounds(10, 11, 580, 29);
		panel_1.add(lblInTheInterview);
		
		a1 = new JLabel("");
		a1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				organize();
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				organize();
			}
		});
		a1.setHorizontalAlignment(SwingConstants.CENTER);
		a1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		a1.setBorder(new LineBorder(new Color(128, 128, 128)));
		a1.setTransferHandler(new TransferHandler("text"));
		a1.setBounds(600, 11, 146, 29);
		panel_1.add(a1);
		
		JLabel lblTheUsAnd = new JLabel("the US and China as the sector\u2019s current leaders, but said neither country\u2019s values were fully aligned with those of Europe. In the US, said");
		lblTheUsAnd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTheUsAnd.setBounds(160, 44, 917, 29);
		panel_1.add(lblTheUsAnd);
		
		JLabel lblMacronWorkIs = new JLabel("Macron, work is \u201Centirely driven by the");
		lblMacronWorkIs.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMacronWorkIs.setBounds(10, 78, 250, 29);
		panel_1.add(lblMacronWorkIs);
		
		JLabel lblInShapingThe = new JLabel("in shaping the future of artificial intelligence. He");
		lblInShapingThe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblInShapingThe.setBounds(756, 11, 321, 29);
		panel_1.add(lblInShapingThe);
		
		JLabel lblAndValuesAre = new JLabel("and values are not ours.\u201D");
		lblAndValuesAre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAndValuesAre.setBounds(10, 113, 706, 29);
		panel_1.add(lblAndValuesAre);
		
		 a2 = new JLabel("");
		 a2.addMouseListener(new MouseAdapter() {
			 @Override
				public void mouseEntered(MouseEvent arg0) {
					organize();
				}
				@Override
				public void mouseExited(MouseEvent arg0) {
					organize();
				}
		 });
		a2.setBounds(10, 44, 140, 29);
		panel_1.add(a2);
		a2.setHorizontalAlignment(SwingConstants.CENTER);
		a2.setTransferHandler(new TransferHandler("text"));
		a2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		a2.setBorder(new LineBorder(new Color(128, 128, 128)));
		
		 a3 = new JLabel("");
		 a3.addMouseListener(new MouseAdapter() {
			 @Override
				public void mouseEntered(MouseEvent arg0) {
					organize();
				}
				@Override
				public void mouseExited(MouseEvent arg0) {
					organize();
				}
		 });
		a3.setBounds(270, 78, 128, 29);
		panel_1.add(a3);
		a3.setHorizontalAlignment(SwingConstants.CENTER);
		a3.setTransferHandler(new TransferHandler("text"));
		a3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		a3.setBorder(new LineBorder(new Color(128, 128, 128)));
		
		JLabel lblSectorWhileIn = new JLabel("sector,\u201D while in China, research is \u201Cdriven by a government whose");
		lblSectorWhileIn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSectorWhileIn.setBounds(408, 78, 435, 29);
		panel_1.add(lblSectorWhileIn);
		
		 a4 = new JLabel("");
		 a4.addMouseListener(new MouseAdapter() {
			 @Override
				public void mouseEntered(MouseEvent arg0) {
					organize();
				}
				@Override
				public void mouseExited(MouseEvent arg0) {
					organize();
				}
		 });
		a4.setHorizontalAlignment(SwingConstants.CENTER);
		a4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		a4.setBorder(new LineBorder(new Color(128, 128, 128)));
		a4.setTransferHandler(new TransferHandler("text"));
		a4.setBounds(853, 78, 146, 29);
		panel_1.add(a4);
		
		JLabel label_18 = new JLabel("<html><p align='CENTER'>In the text below some words are missing. Drag words from the text below to the appropriate place in the text. </p></html>");
		label_18.setOpaque(true);
		label_18.setHorizontalAlignment(SwingConstants.CENTER);
		label_18.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_18.setBorder(new MatteBorder(1, 0, 1, 0, (Color) new Color(0, 0, 0)));
		label_18.setBackground(new Color(240, 255, 240));
		label_18.setBounds(151, 127, 1087, 33);
		frame.getContentPane().add(label_18);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(255, 140, 0));
		panel_2.setBounds(0, 55, 1370, 33);
		frame.getContentPane().add(panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(0, 0, 0)));
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(0, 0, 1370, 56);
		frame.getContentPane().add(panel_3);
		
		JLabel lblReadingItems = new JLabel("Reading Items ");
		lblReadingItems.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblReadingItems.setBounds(10, 0, 497, 56);
		panel_3.add(lblReadingItems);
		
		JButton button_1 = new JButton("<html><u>N</u>ext</html>");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					fileSaver();
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				frame.dispose();
				Set1reading11 go=new Set1reading11();
				go.frame.setVisible(true);
			}
		});
		button_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_1.setBackground(Color.WHITE);
		button_1.setBounds(1149, 639, 89, 28);
		frame.getContentPane().add(button_1);
		
		JLabel label = new JLabel("\u00A9Lab Symbiotic");
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.DARK_GRAY);
		label.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		label.setBounds(0, 704, 1370, 24);
		frame.getContentPane().add(label);
	}
	
	public void fileSaver() throws IOException, ClassNotFoundException{
		File answerFolder = new File("Answer");
		if(answerFolder.exists())
		{
			File answer=new File("Answer//ReadingAns.ser");
			if(answer.exists())
			{
				FileInputStream fis=new FileInputStream(answer);
				ObjectInputStream ois=new ObjectInputStream(fis);
				ReadingAnswer ra=(ReadingAnswer)ois.readObject();
				if(a1.getText().equals(null)||a1.getText().equals(""))
				{
					ra.fillBlank4.add("");
				}
				else
				{
					ra.fillBlank4.add(a1.getText().trim());
				}
				if(a2.getText().equals(null)||a2.getText().equals(""))
				{
					ra.fillBlank4.add("");
				}
				else
				{
					ra.fillBlank4.add(a2.getText().trim());
				}
				if(a3.getText().equals(null)||a3.getText().equals(""))
				{
					ra.fillBlank4.add("");
				}
				else
				{
					ra.fillBlank4.add(a3.getText().trim());
				}
				if(a4.getText().equals(null)||a4.getText().equals(""))
				{
					ra.fillBlank4.add("");
				}
				else
				{
					ra.fillBlank4.add(a4.getText().trim());
				}
				ois.close();
				fis.close();
				answer.delete();
				FileOutputStream fos=new FileOutputStream(new File("Answer//ReadingAns.ser"),true);
				ObjectOutputStream oos=new ObjectOutputStream(new BufferedOutputStream (fos));
				oos.writeObject(ra);
				oos.close();
				fos.close();
			}
		}
		else
		{
			answerFolder.mkdirs();
			File answer=new File("Answer//ReadingAns.ser");
			ReadingAnswer ra=new ReadingAnswer();
			/*if(radiobtn3.isSelected())
			{
				la.highlightCorrectSummary=radiobtn3.getText();
			}*/
			if(a1.getText().equals(null)||a1.getText().equals(""))
			{
				ra.fillBlank4.add("");
			}
			else
			{
				ra.fillBlank4.add(a1.getText().trim());
			}
			if(a2.getText().equals(null)||a2.getText().equals(""))
			{
				ra.fillBlank4.add("");
			}
			else
			{
				ra.fillBlank4.add(a2.getText().trim());
			}
			if(a3.getText().equals(null)||a3.getText().equals(""))
			{
				ra.fillBlank4.add("");
			}
			else
			{
				ra.fillBlank4.add(a3.getText().trim());
			}
			if(a4.getText().equals(null)||a4.getText().equals(""))
			{
				ra.fillBlank4.add("");
			}
			else
			{
				ra.fillBlank4.add(a4.getText().trim());
			}
			FileOutputStream fos=new FileOutputStream(answer,true);
			ObjectOutputStream oos=new ObjectOutputStream(new BufferedOutputStream (fos));
			oos.writeObject(ra);
			oos.close();
			fos.close();
		}
	}
	
	public void options()
	{
		String[] arr=new String[4];
		if(!a1.getText().equals(null))
		{
			arr[0]=a1.getText().trim();
		}
		else
			arr[0]="";
		
		if(!a2.getText().equals(null))
		{
			arr[1]=a2.getText().trim();
		}
		else
			arr[1]="";
		
		if(!a3.getText().equals(null))
		{
			arr[2]=a3.getText().trim();
		}
		else
			arr[2]="";
		if(!a4.getText().equals(null))
		{
			arr[3]=a4.getText().trim();
		}
		else
			arr[3]="";
		
		
		for(int i=0;i<arr.length;i++)
		{
			if(arr[i]=="principles")
			{
				q1.setText(null);
			}
			else if(arr[i]=="revolution")
			{
				q2.setText(null);
			}
			else if(arr[i]=="mistakes")
			{
				q3.setText(null);
			}
			else if(arr[i]=="identified")
			{
				q4.setText(null);
			}
			else if(arr[i]=="creation")
			{
				q5.setText(null);
			}
			else if(arr[i]=="role")
			{
				q6.setText(null);
			}
			else if(arr[i]=="private")
			{
				q7.setText(null);
			}
		}
	}
	
	public void organize()
	{
		if(a1.getText().trim().equalsIgnoreCase("principles")||a2.getText().trim().equalsIgnoreCase("principles")||a3.getText().trim().equalsIgnoreCase("principles")||a4.getText().trim().equalsIgnoreCase("principles"))
		{
			q1.setText(null);
		}
		else {
			q1.setText("principles");
		}
		
		if(a1.getText().trim().equalsIgnoreCase("revolution")||a2.getText().trim().equalsIgnoreCase("revolution")||a3.getText().trim().equalsIgnoreCase("revolution")||a4.getText().trim().equalsIgnoreCase("revolution"))
		{
			q2.setText(null);
		}
		else {
			q2.setText("revolution");
		}
		
		if(a1.getText().trim().equalsIgnoreCase("mistakes")||a2.getText().trim().equalsIgnoreCase("mistakes")||a3.getText().trim().equalsIgnoreCase("mistakes")||a4.getText().trim().equalsIgnoreCase("mistakes"))
		{
			q3.setText(null);
		}
		else {
			q3.setText("mistakes");
		}
		
		if(a1.getText().trim().equalsIgnoreCase("identified")||a2.getText().trim().equalsIgnoreCase("identified")||a3.getText().trim().equalsIgnoreCase("identified")||a4.getText().trim().equalsIgnoreCase("identified"))
		{
			q4.setText(null);
		}
		else {
			q4.setText("identified");
		}
		
//		q5
		if(a1.getText().trim().equalsIgnoreCase("creation")||a2.getText().trim().equalsIgnoreCase("creation")||a3.getText().trim().equalsIgnoreCase("creation")||a4.getText().trim().equalsIgnoreCase("creation"))
		{
			q5.setText(null);
		}
		else {
			q5.setText("creation");
		}
		
//		q6
		if(a1.getText().trim().equalsIgnoreCase("role")||a2.getText().trim().equalsIgnoreCase("role")||a3.getText().trim().equalsIgnoreCase("role")||a4.getText().trim().equalsIgnoreCase("role"))
		{
			q6.setText(null);
		}
		else {
			q6.setText("role");
		}
		
		if(a1.getText().trim().equalsIgnoreCase("private")||a2.getText().trim().equalsIgnoreCase("private")||a3.getText().trim().equalsIgnoreCase("private")||a4.getText().trim().equalsIgnoreCase("private"))
		{
			q7.setText(null);
		}
		else {
			q7.setText("private");
		}
	}
}