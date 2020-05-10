package set2Reading;

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

public class Set2FillBlank4 {

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
					Set2FillBlank4 window = new Set2FillBlank4();
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
	public Set2FillBlank4() {
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
						conn.createStatement().executeUpdate("DELETE FROM set2speaking;");
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
		lblItemNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemNo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblItemNo.setBounds(135, 640, 118, 24);
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
		panel.setBackground(new Color(211, 211, 211));
		panel.setBounds(135, 424, 1087, 160);
		frame.getContentPane().add(panel);
		
		 q1 = new JLabel("private");
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
		
		 q2 = new JLabel("guarantee ");
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
		
		 q3 = new JLabel("plenty");
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
		
		 q5 = new JLabel("worse");
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
		
		 q6 = new JLabel("consumers");
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
		
		 q4 = new JLabel("transparent");
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
		
		 q7 = new JLabel("guard");
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
		panel_1.setBounds(135, 221, 1087, 125);
		frame.getContentPane().add(panel_1);
		
		JLabel lblInTheInterview = new JLabel("In order to");
		lblInTheInterview.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblInTheInterview.setBounds(10, 11, 71, 29);
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
		a1.setBounds(91, 10, 146, 29);
		panel_1.add(a1);
		
		JLabel lblTheUsAnd = new JLabel("companies to do the same. \"Obviously some of them will say, there is a commercial value in my algorithm, I don\u2019t want to make it");
		lblTheUsAnd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTheUsAnd.setBounds(127, 44, 853, 29);
		panel_1.add(lblTheUsAnd);
		
		JLabel lblMacronWorkIs = new JLabel("But I think we need a fair discussion between service providers and");
		lblMacronWorkIs.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMacronWorkIs.setBounds(10, 78, 438, 29);
		panel_1.add(lblMacronWorkIs);
		
		JLabel lblInShapingThe = new JLabel("against this threat, Macron said the French government will do its best to open up data used by AI systems and pressure");
		lblInShapingThe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblInShapingThe.setBounds(247, 11, 830, 29);
		panel_1.add(lblInShapingThe);
		
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
		a2.setBounds(10, 44, 112, 29);
		panel_1.add(a2);
		a2.setHorizontalAlignment(SwingConstants.CENTER);
		a2.setTransferHandler(new TransferHandler("text"));
		a2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		a2.setBorder(new LineBorder(new Color(128, 128, 128)));
		
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
		a4.setBounds(453, 78, 146, 29);
		panel_1.add(a4);
		
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
		 a3.setBounds(985, 44, 102, 29);
		 panel_1.add(a3);
		 a3.setHorizontalAlignment(SwingConstants.CENTER);
		 a3.setTransferHandler(new TransferHandler("text"));
		 a3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		 a3.setBorder(new LineBorder(new Color(128, 128, 128)));
		 
		 JLabel lblWhoAreAlso = new JLabel("who are also citizens,\" said Macron.");
		 lblWhoAreAlso.setFont(new Font("Tahoma", Font.PLAIN, 15));
		 lblWhoAreAlso.setBounds(609, 78, 478, 29);
		 panel_1.add(lblWhoAreAlso);
		
		JLabel label_18 = new JLabel("<html><p align='CENTER'>In the text below some words are missing. Drag words from the text below to the appropriate place in the text. </p></html>");
		label_18.setOpaque(true);
		label_18.setHorizontalAlignment(SwingConstants.CENTER);
		label_18.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_18.setBorder(new MatteBorder(1, 0, 1, 0, (Color) new Color(0, 0, 0)));
		label_18.setBackground(new Color(240, 255, 240));
		label_18.setBounds(135, 113, 1087, 33);
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
		
		JButton button = new JButton("Submit");
		button.setForeground(Color.DARK_GRAY);
		button.setFont(new Font("Tahoma", Font.BOLD, 12));
		button.setEnabled(false);
		button.setBackground(Color.WHITE);
		button.setBounds(1203, 14, 134, 30);
		panel_3.add(button);
		
		JLabel label_19 = new JLabel("Reading Items (Fill in the blanks)");
		label_19.setFont(new Font("Tahoma", Font.BOLD, 24));
		label_19.setBounds(10, 0, 497, 56);
		panel_3.add(label_19);
		
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
				Set2FillBlankCombo1 go=new Set2FillBlankCombo1();
				go.frame.setVisible(true);
			}
		});
		button_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_1.setBackground(Color.WHITE);
		button_1.setBounds(1176, 639, 89, 28);
		frame.getContentPane().add(button_1);
		
		JLabel label_20 = new JLabel("Lab Symbiotic");
		label_20.setHorizontalAlignment(SwingConstants.TRAILING);
		label_20.setForeground(Color.DARK_GRAY);
		label_20.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_20.setBounds(1204, 678, 156, 20);
		frame.getContentPane().add(label_20);
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
	
	public void organize()
	{
		if(a1.getText().trim().equalsIgnoreCase("private")||a2.getText().trim().equalsIgnoreCase("private")||a3.getText().trim().equalsIgnoreCase("private")||a4.getText().trim().equalsIgnoreCase("private"))
		{
			q1.setText(null);
		}
		else {
			q1.setText("private");
		}
		
		if(a1.getText().trim().equalsIgnoreCase("guarantee")||a2.getText().trim().equalsIgnoreCase("guarantee")||a3.getText().trim().equalsIgnoreCase("guarantee")||a4.getText().trim().equalsIgnoreCase("guarantee"))
		{
			q2.setText(null);
		}
		else {
			q2.setText("guarantee");
		}
		
		if(a1.getText().trim().equalsIgnoreCase("plenty")||a2.getText().trim().equalsIgnoreCase("plenty")||a3.getText().trim().equalsIgnoreCase("plenty")||a4.getText().trim().equalsIgnoreCase("plenty"))
		{
			q3.setText(null);
		}
		else {
			q3.setText("plenty");
		}
		
		if(a1.getText().trim().equalsIgnoreCase("transparent")||a2.getText().trim().equalsIgnoreCase("transparent")||a3.getText().trim().equalsIgnoreCase("transparent")||a4.getText().trim().equalsIgnoreCase("transparent"))
		{
			q4.setText(null);
		}
		else {
			q4.setText("transparent");
		}
		
//		q5
		if(a1.getText().trim().equalsIgnoreCase("worse")||a2.getText().trim().equalsIgnoreCase("worse")||a3.getText().trim().equalsIgnoreCase("worse")||a4.getText().trim().equalsIgnoreCase("worse"))
		{
			q5.setText(null);
		}
		else {
			q5.setText("worse");
		}
		
//		q6
		if(a1.getText().trim().equalsIgnoreCase("consumers")||a2.getText().trim().equalsIgnoreCase("consumers")||a3.getText().trim().equalsIgnoreCase("consumers")||a4.getText().trim().equalsIgnoreCase("consumers"))
		{
			q6.setText(null);
		}
		else {
			q6.setText("consumers");
		}
		
		if(a1.getText().trim().equalsIgnoreCase("guard")||a2.getText().trim().equalsIgnoreCase("guard")||a3.getText().trim().equalsIgnoreCase("guard")||a4.getText().trim().equalsIgnoreCase("guard"))
		{
			q7.setText(null);
		}
		else {
			q7.setText("guard");
		}
		
	}
}
