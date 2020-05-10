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
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.TransferHandler;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Set2FillBlank2 {

	public JFrame frame;
	JLabel a1,a2,a3,a4,q1,q3,q4,q5,q6,q7,q2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Set2FillBlank2 window = new Set2FillBlank2();
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
	public Set2FillBlank2() {
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
		panel.setLayout(null);
		panel.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1370, 56);
		frame.getContentPane().add(panel);
		
		JButton button = new JButton("Submit");
		button.setForeground(Color.DARK_GRAY);
		button.setFont(new Font("Tahoma", Font.BOLD, 12));
		button.setEnabled(false);
		button.setBackground(Color.WHITE);
		button.setBounds(1203, 14, 134, 30);
		panel.add(button);
		
		JLabel label = new JLabel("Reading Items (Fill in the blanks)");
		label.setFont(new Font("Tahoma", Font.BOLD, 24));
		label.setBounds(10, 0, 497, 56);
		panel.add(label);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(255, 140, 0));
		panel_1.setBounds(0, 55, 1370, 33);
		frame.getContentPane().add(panel_1);
		
		JLabel label_1 = new JLabel("<html><p align='CENTER'>In the text below some words are missing. Drag words from the text below to the appropriate place in the text. </p></html>");
		label_1.setOpaque(true);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_1.setBorder(new MatteBorder(1, 0, 1, 0, (Color) new Color(0, 0, 0)));
		label_1.setBackground(new Color(240, 255, 240));
		label_1.setBounds(135, 113, 1067, 33);
		frame.getContentPane().add(label_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.addMouseListener(new MouseAdapter() {
			@Override
		 	public void mouseEntered(MouseEvent arg0) {
		 		organize();
		 	}
		 	@Override
		 	public void mouseExited(MouseEvent arg0) {
		 		organize();
		 	}
		});
		
		panel_2.setLayout(null);
		panel_2.setBorder(new MatteBorder(2, 0, 2, 0, (Color) new Color(0, 0, 0)));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(135, 199, 1067, 160);
		frame.getContentPane().add(panel_2);
		
		JLabel lblBothParasiticAnd = new JLabel("This huge and diverse assemblage belongs to the order Hymenoptera and is ");
		lblBothParasiticAnd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBothParasiticAnd.setBounds(10, 11, 505, 29);
		panel_2.add(lblBothParasiticAnd);
		
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
		a1.setBounds(525, 11, 185, 29);
		panel_2.add(a1);
		
		JLabel lblWhichIncludesSpiders = new JLabel("Almost 80,000 species of wasps");
		lblWhichIncludesSpiders.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblWhichIncludesSpiders.setBounds(10, 44, 209, 29);
		panel_2.add(lblWhichIncludesSpiders);
		
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
		a2.setBounds(229, 44, 185, 29);
		panel_2.add(a2);
		
		JLabel lblOfTheInvertebrate = new JLabel("to the Parasitica group, which lay their eggs in or on their prey or plants using elongated tubular");
		lblOfTheInvertebrate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblOfTheInvertebrate.setBounds(424, 44, 643, 29);
		panel_2.add(lblOfTheInvertebrate);
		
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
		a3.setTransferHandler(new TransferHandler("text"));
		a3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		a3.setBorder(new LineBorder(new Color(128, 128, 128)));
		a3.setBounds(477, 78, 185, 29);
		panel_2.add(a3);
		
		JLabel lblOfBothCarnivorous = new JLabel("organs called ovipositors. The remaining 33,000 species are Aculeates,");
		lblOfBothCarnivorous.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblOfBothCarnivorous.setBounds(10, 78, 460, 29);
		panel_2.add(lblOfBothCarnivorous);
		
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
		a4.setBounds(205, 113, 170, 29);
		panel_2.add(a4);
		
		JLabel lblImpactOnThe = new JLabel("into two groups, the Parasitica and the Aculeata. ");
		lblImpactOnThe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblImpactOnThe.setBounds(720, 11, 337, 29);
		panel_2.add(lblImpactOnThe);
		
		JLabel lblOfPopulationsIs = new JLabel("have been modified through");
		lblOfPopulationsIs.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblOfPopulationsIs.setBounds(10, 113, 185, 29);
		panel_2.add(lblOfPopulationsIs);
		
		JLabel lblRoleBothEcologically = new JLabel("to form a sting.");
		lblRoleBothEcologically.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRoleBothEcologically.setBounds(381, 113, 317, 29);
		panel_2.add(lblRoleBothEcologically);
		
		JLabel lblOfWhichAre = new JLabel("of which are predators, and the ones whose ovipositors ");
		lblOfWhichAre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblOfWhichAre.setBounds(667, 78, 400, 29);
		panel_2.add(lblOfWhichAre);
		
		JPanel panel_3 = new JPanel();
		panel_3.addMouseListener(new MouseAdapter() {
			@Override
		 	public void mouseEntered(MouseEvent arg0) {
		 		organize();
		 	}
		 	@Override
		 	public void mouseExited(MouseEvent arg0) {
		 		organize();
		 	}
		});
		panel_3.setLayout(null);
		panel_3.setBorder(new MatteBorder(2, 0, 2, 0, (Color) new Color(0, 0, 0)));
		panel_3.setBackground(new Color(211, 211, 211));
		panel_3.setBounds(135, 392, 1067, 160);
		frame.getContentPane().add(panel_3);
		
		 q1 = new JLabel("divided");
		 q1.addMouseListener(new MouseAdapter() {
		 	@Override
		 	public void mousePressed(MouseEvent arg0) {
		 		if(q1.getText().equals(a1.getText().toString()))
				{
		 			a1.setText(null);
					JComponent jc=(JComponent)arg0.getComponent();
					TransferHandler th=jc.getTransferHandler();
					th.exportAsDrag(jc, arg0, TransferHandler.COPY);
					
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
		q1.setTransferHandler(new TransferHandler("text"));
		q1.setBorder(new LineBorder(new Color(128, 128, 128)));
		q1.setBounds(64, 31, 185, 29);
		panel_3.add(q1);
		
		
		
		 q3 = new JLabel("belong");
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
		q3.setBounds(578, 31, 185, 29);
		panel_3.add(q3);
		
		 q5 = new JLabel("open");
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
		q5.setTransferHandler(new TransferHandler("text"));
		q5.setBorder(new LineBorder(new Color(128, 128, 128)));
		q5.setBounds(64, 100, 185, 29);
		panel_3.add(q5);
		
		 q6 = new JLabel("most");
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
		panel_3.add(q6);
		
		 q4 = new JLabel("empty");
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
		q4.setTransferHandler(new TransferHandler("text"));
		q4.setBorder(new LineBorder(new Color(128, 128, 128)));
		q4.setBounds(820, 31, 185, 29);
		panel_3.add(q4);
		
		 q7 = new JLabel("evolution");
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
		q7.setBounds(578, 100, 185, 29);
		panel_3.add(q7);
		
		q2 = new JLabel("try");
		q2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(q2.getText().equals(a1.getText()))
				{
					JComponent jc=(JComponent)arg0.getComponent();
					TransferHandler th=jc.getTransferHandler();
					th.exportAsDrag(jc, arg0, TransferHandler.COPY);
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
		panel_3.add(q2);
		
		JLabel lblItemNo = new JLabel("Item no 8 of 15");
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
				Set2FillBlank3 go=new Set2FillBlank3();
				go.frame.setVisible(true);
			}
		});
		button_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_1.setBackground(Color.WHITE);
		button_1.setBounds(1176, 639, 89, 28);
		frame.getContentPane().add(button_1);
		
		JLabel label_18 = new JLabel("Lab Symbiotic");
		label_18.setHorizontalAlignment(SwingConstants.TRAILING);
		label_18.setForeground(Color.DARK_GRAY);
		label_18.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_18.setBounds(1204, 678, 156, 20);
		frame.getContentPane().add(label_18);
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
					ra.fillBlank2.add("");
				}
				else
				{
					ra.fillBlank2.add(a1.getText().trim());
				}
				if(a2.getText().equals(null)||a2.getText().equals(""))
				{
					ra.fillBlank2.add("");
				}
				else
				{
					ra.fillBlank2.add(a2.getText().trim());
				}
				if(a3.getText().equals(null)||a3.getText().equals(""))
				{
					ra.fillBlank2.add("");
				}
				else
				{
					ra.fillBlank2.add(a3.getText().trim());
				}
				if(a4.getText().equals(null)||a4.getText().equals(""))
				{
					ra.fillBlank2.add("");
				}
				else
				{
					ra.fillBlank2.add(a4.getText().trim());
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
				ra.fillBlank2.add("");
			}
			else
			{
				ra.fillBlank2.add(a1.getText().trim());
			}
			if(a2.getText().equals(null)||a2.getText().equals(""))
			{
				ra.fillBlank2.add("");
			}
			else
			{
				ra.fillBlank2.add(a2.getText().trim());
			}
			if(a3.getText().equals(null)||a3.getText().equals(""))
			{
				ra.fillBlank2.add("");
			}
			else
			{
				ra.fillBlank2.add(a3.getText().trim());
			}
			if(a4.getText().equals(null)||a4.getText().equals(""))
			{
				ra.fillBlank2.add("");
			}
			else
			{
				ra.fillBlank2.add(a4.getText().trim());
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
		if(a1.getText().trim().equalsIgnoreCase("divided")||a2.getText().trim().equalsIgnoreCase("divided")||a3.getText().trim().equalsIgnoreCase("divided")||a4.getText().trim().equalsIgnoreCase("divided"))
		{
			q1.setText(null);
		}
		else {
			q1.setText("divided");
		}
		
		if(a1.getText().trim().equalsIgnoreCase("try")||a2.getText().trim().equalsIgnoreCase("try")||a3.getText().trim().equalsIgnoreCase("try")||a4.getText().trim().equalsIgnoreCase("try"))
		{
			q2.setText(null);
		}
		else {
			q2.setText("try");
		}
		
		if(a1.getText().trim().equalsIgnoreCase("belong")||a2.getText().trim().equalsIgnoreCase("belong")||a3.getText().trim().equalsIgnoreCase("belong")||a4.getText().trim().equalsIgnoreCase("belong"))
		{
			q3.setText(null);
		}
		else {
			q3.setText("belong");
		}
		
		if(a1.getText().trim().equalsIgnoreCase("empty")||a2.getText().trim().equalsIgnoreCase("empty")||a3.getText().trim().equalsIgnoreCase("empty")||a4.getText().trim().equalsIgnoreCase("empty"))
		{
			q4.setText(null);
		}
		else {
			q4.setText("empty");
		}
		
//		q5
		if(a1.getText().trim().equalsIgnoreCase("open")||a2.getText().trim().equalsIgnoreCase("open")||a3.getText().trim().equalsIgnoreCase("open")||a4.getText().trim().equalsIgnoreCase("open"))
		{
			q5.setText(null);
		}
		else {
			q5.setText("open");
		}
		
//		q6
		if(a1.getText().trim().equalsIgnoreCase("most")||a2.getText().trim().equalsIgnoreCase("most")||a3.getText().trim().equalsIgnoreCase("most")||a4.getText().trim().equalsIgnoreCase("most"))
		{
			q6.setText(null);
		}
		else {
			q6.setText("most");
		}
		
		if(a1.getText().trim().equalsIgnoreCase("evolution")||a2.getText().trim().equalsIgnoreCase("evolution")||a3.getText().trim().equalsIgnoreCase("evolution")||a4.getText().trim().equalsIgnoreCase("evolution"))
		{
			q7.setText(null);
		}
		else {
			q7.setText("evolution");
		}
		
	}
	
}
