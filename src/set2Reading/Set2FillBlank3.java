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
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.TransferHandler;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Set2FillBlank3 {

	public JFrame frame;
	JLabel a1,a2,a3,a4,q1,q2,q3,q4,q5,q6,q7;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Set2FillBlank3 window = new Set2FillBlank3();
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
	public Set2FillBlank3() {
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
		panel.setBounds(135, 392, 1087, 160);
		frame.getContentPane().add(panel);
		
		 q1 = new JLabel("nests");
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
		
		 q2 = new JLabel("without");
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
		
		 q3 = new JLabel("location");
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
		
		 q5 = new JLabel("stipulation");
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
		
		 q6 = new JLabel("impression");
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
		
		 q4 = new JLabel("killing");
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
		q4.setBounds(851, 31, 185, 29);
		q4.setTransferHandler(new TransferHandler("text"));
		panel.add(q4);
		
		 q7 = new JLabel("majority");
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
		panel_1.setBounds(135, 206, 1087, 153);
		frame.getContentPane().add(panel_1);
		
		JLabel lblMacronIsWorried = new JLabel("Although the ");
		lblMacronIsWorried.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMacronIsWorried.setBounds(10, 11, 87, 29);
		panel_1.add(lblMacronIsWorried);
		
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
		a1.setBounds(100, 11, 135, 29);
		panel_1.add(a1);
		
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
		a2.setHorizontalAlignment(SwingConstants.CENTER);
		a2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		a2.setBorder(new LineBorder(new Color(128, 128, 128)));
		a2.setTransferHandler(new TransferHandler("text"));
		a2.setBounds(875, 11, 140, 29);
		panel_1.add(a2);
		
		JLabel lblAndSaidThat = new JLabel("on insect populations. Social wasp queens share their");
		lblAndSaidThat.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAndSaidThat.setBounds(10, 44, 349, 29);
		panel_1.add(lblAndSaidThat);
		
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
		a3.setHorizontalAlignment(SwingConstants.CENTER);
		a3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		a3.setBorder(new LineBorder(new Color(128, 128, 128)));
		a3.setTransferHandler(new TransferHandler("text"));
		a3.setBounds(363, 44, 128, 29);
		panel_1.add(a3);
		
		JLabel lblItCouldDestroy = new JLabel("the colony cycle. This means a single nest provides a whopping bang for buck in terms of ecosystem services,");
		lblItCouldDestroy.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblItCouldDestroy.setBounds(10, 78, 722, 29);
		panel_1.add(lblItCouldDestroy);
		
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
		a4.setBounds(742, 78, 104, 29);
		panel_1.add(a4);
		
		JLabel lblIntoSocietyAnd = new JLabel("of wasps lead solitary lives, it is the 1,000 or so species of social wasps which make the biggest");
		lblIntoSocietyAnd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIntoSocietyAnd.setBounds(245, 11, 620, 29);
		panel_1.add(lblIntoSocietyAnd);
		
		JLabel lblForMyPeople = new JLabel("millipedes and crop-devouring insects.");
		lblForMyPeople.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblForMyPeople.setBounds(10, 113, 706, 29);
		panel_1.add(lblForMyPeople);
		
		JLabel lblWithThousandsOf = new JLabel("with thousands of offspring workers, who raise upwards of 10,000 sibling larvae during");
		lblWithThousandsOf.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblWithThousandsOf.setBounds(501, 44, 586, 29);
		panel_1.add(lblWithThousandsOf);
		
		JLabel lblVastNumbersOf = new JLabel("vast numbers of spiders,");
		lblVastNumbersOf.setBounds(856, 78, 221, 29);
		panel_1.add(lblVastNumbersOf);
		lblVastNumbersOf.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
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
		
		JLabel lblItemNo = new JLabel("Item no 9 of 15");
		lblItemNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemNo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblItemNo.setBounds(135, 640, 118, 24);
		frame.getContentPane().add(lblItemNo);
		
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
				Set2FillBlank4 go=new Set2FillBlank4();
				go.frame.setVisible(true);
			}
		});
		button_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_1.setBackground(Color.WHITE);
		button_1.setBounds(1176, 639, 89, 28);
		frame.getContentPane().add(button_1);
		
		JLabel label_21 = new JLabel("Lab Symbiotic");
		label_21.setHorizontalAlignment(SwingConstants.TRAILING);
		label_21.setForeground(Color.DARK_GRAY);
		label_21.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_21.setBounds(1204, 678, 156, 20);
		frame.getContentPane().add(label_21);
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
					ra.fillBlank3.add("");
				}
				else
				{
					ra.fillBlank3.add(a1.getText().trim());
				}
				if(a2.getText().equals(null)||a2.getText().equals(""))
				{
					ra.fillBlank3.add("");
				}
				else
				{
					ra.fillBlank3.add(a2.getText().trim());
				}
				if(a3.getText().equals(null)||a3.getText().equals(""))
				{
					ra.fillBlank3.add("");
				}
				else
				{
					ra.fillBlank3.add(a3.getText().trim());
				}
				if(a4.getText().equals(null)||a4.getText().equals(""))
				{
					ra.fillBlank3.add("");
				}
				else
				{
					ra.fillBlank3.add(a4.getText().trim());
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
				ra.fillBlank3.add("");
			}
			else
			{
				ra.fillBlank3.add(a1.getText().trim());
			}
			if(a2.getText().equals(null)||a2.getText().equals(""))
			{
				ra.fillBlank3.add("");
			}
			else
			{
				ra.fillBlank3.add(a2.getText().trim());
			}
			if(a3.getText().equals(null)||a3.getText().equals(""))
			{
				ra.fillBlank3.add("");
			}
			else
			{
				ra.fillBlank3.add(a3.getText().trim());
			}
			if(a4.getText().equals(null)||a4.getText().equals(""))
			{
				ra.fillBlank3.add("");
			}
			else
			{
				ra.fillBlank3.add(a4.getText().trim());
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
		if(a1.getText().trim().equalsIgnoreCase("nests")||a2.getText().trim().equalsIgnoreCase("nests")||a3.getText().trim().equalsIgnoreCase("nests")||a4.getText().trim().equalsIgnoreCase("nests"))
		{
			q1.setText(null);
		}
		else {
			q1.setText("nests");
		}
		
		if(a1.getText().trim().equalsIgnoreCase("without")||a2.getText().trim().equalsIgnoreCase("without")||a3.getText().trim().equalsIgnoreCase("without")||a4.getText().trim().equalsIgnoreCase("without"))
		{
			q2.setText(null);
		}
		else {
			q2.setText("without");
		}
		
		if(a1.getText().trim().equalsIgnoreCase("location")||a2.getText().trim().equalsIgnoreCase("location")||a3.getText().trim().equalsIgnoreCase("location")||a4.getText().trim().equalsIgnoreCase("location"))
		{
			q3.setText(null);
		}
		else {
			q3.setText("location");
		}
		
		if(a1.getText().trim().equalsIgnoreCase("killing")||a2.getText().trim().equalsIgnoreCase("killing")||a3.getText().trim().equalsIgnoreCase("killing")||a4.getText().trim().equalsIgnoreCase("killing"))
		{
			q4.setText(null);
		}
		else {
			q4.setText("killing");
		}
		
//		q5
		if(a1.getText().trim().equalsIgnoreCase("stipulation")||a2.getText().trim().equalsIgnoreCase("stipulation")||a3.getText().trim().equalsIgnoreCase("stipulation")||a4.getText().trim().equalsIgnoreCase("stipulation"))
		{
			q5.setText(null);
		}
		else {
			q5.setText("stipulation");
		}
		
//		q6
		if(a1.getText().trim().equalsIgnoreCase("impression")||a2.getText().trim().equalsIgnoreCase("impression")||a3.getText().trim().equalsIgnoreCase("impression")||a4.getText().trim().equalsIgnoreCase("impression"))
		{
			q6.setText(null);
		}
		else {
			q6.setText("impression");
		}
		
		if(a1.getText().trim().equalsIgnoreCase("majority")||a2.getText().trim().equalsIgnoreCase("majority")||a3.getText().trim().equalsIgnoreCase("majority")||a4.getText().trim().equalsIgnoreCase("majority"))
		{
			q7.setText(null);
		}
		else {
			q7.setText("majority");
		}
		
	}

}
