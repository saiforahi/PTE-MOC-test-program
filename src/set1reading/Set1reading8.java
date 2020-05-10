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

public class Set1reading8 {

	public JFrame frame;
	JLabel a1,a2,a3,a4,q1,q3,q4,q5,q6,q7,q2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Set1reading8 window = new Set1reading8();
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
	public Set1reading8() {
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
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1370, 56);
		frame.getContentPane().add(panel);
		
		JLabel lblReadingItems = new JLabel("Reading Items");
		lblReadingItems.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblReadingItems.setBounds(10, 0, 497, 56);
		panel.add(lblReadingItems);
		
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
		label_1.setBounds(157, 130, 1067, 33);
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
		panel_2.setBounds(157, 223, 1067, 153);
		frame.getContentPane().add(panel_2);
		
		JLabel lblBothParasiticAnd = new JLabel("Both parasitic and predatory wasps have a");
		lblBothParasiticAnd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBothParasiticAnd.setBounds(10, 11, 279, 29);
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
		a1.setBounds(311, 11, 185, 29);
		panel_2.add(a1);
		
		JLabel lblWhichIncludesSpiders = new JLabel("which includes spiders, mites, insects and centipedes. They are right at the ");
		lblWhichIncludesSpiders.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblWhichIncludesSpiders.setBounds(10, 44, 494, 29);
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
		a2.setBounds(514, 44, 185, 29);
		panel_2.add(a2);
		
		JLabel lblOfTheInvertebrate = new JLabel("of the invertebrate food chain. Through the");
		lblOfTheInvertebrate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblOfTheInvertebrate.setBounds(709, 44, 291, 29);
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
		a3.setBounds(10, 78, 185, 29);
		panel_2.add(a3);
		
		JLabel lblOfBothCarnivorous = new JLabel("of both carnivorous and plant-feeding arthropod populations, wasps protect lower invertebrate species and plants. This regulation ");
		lblOfBothCarnivorous.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblOfBothCarnivorous.setBounds(205, 78, 852, 29);
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
		a4.setBounds(255, 113, 170, 29);
		panel_2.add(a4);
		
		JLabel lblImpactOnThe = new JLabel("impact on the abundance of arthropods, the largest phylum in the animal kingdom");
		lblImpactOnThe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblImpactOnThe.setBounds(513, 11, 544, 29);
		panel_2.add(lblImpactOnThe);
		
		JLabel lblOfPopulationsIs = new JLabel("of populations is arguably their most");
		lblOfPopulationsIs.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblOfPopulationsIs.setBounds(10, 113, 235, 29);
		panel_2.add(lblOfPopulationsIs);
		
		JLabel lblRoleBothEcologically = new JLabel("role, both ecologically and economically.");
		lblRoleBothEcologically.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRoleBothEcologically.setBounds(435, 113, 622, 29);
		panel_2.add(lblRoleBothEcologically);
		
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
		panel_3.setBackground(new Color(204, 255, 204));
		panel_3.setBounds(157, 409, 1067, 160);
		frame.getContentPane().add(panel_3);
		
		 q1 = new JLabel("stop");
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
		q1.setTransferHandler(new TransferHandler("text"));
		q1.setBorder(new LineBorder(new Color(128, 128, 128)));
		q1.setBounds(64, 31, 185, 29);
		panel_3.add(q1);
		
		
		
		 q3 = new JLabel("important");
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
		
		 q5 = new JLabel("massive");
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
		
		 q6 = new JLabel("range");
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
		
		 q4 = new JLabel("top");
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
		
		 q7 = new JLabel("regulation");
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
		
		q2 = new JLabel("reap");
		q2.setBackground(Color.WHITE);
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
		lblItemNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblItemNo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblItemNo.setBounds(157, 640, 118, 24);
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
				Set1reading9 go=new Set1reading9();
				go.frame.setVisible(true);
			}
		});
		button_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_1.setBackground(Color.WHITE);
		button_1.setBounds(1135, 639, 89, 28);
		frame.getContentPane().add(button_1);
		
		JLabel label_2 = new JLabel("\u00A9Lab Symbiotic");
		label_2.setVerticalAlignment(SwingConstants.TOP);
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(Color.DARK_GRAY);
		label_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		label_2.setBounds(0, 704, 1370, 24);
		frame.getContentPane().add(label_2);
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
		if(a1.getText().trim().equalsIgnoreCase("stop")||a2.getText().trim().equalsIgnoreCase("stop")||a3.getText().trim().equalsIgnoreCase("stop")||a4.getText().trim().equalsIgnoreCase("stop"))
		{
			q1.setText(null);
		}
		else {
			q1.setText("stop");
		}
		
		if(a1.getText().trim().equalsIgnoreCase("reap")||a2.getText().trim().equalsIgnoreCase("reap")||a3.getText().trim().equalsIgnoreCase("reap")||a4.getText().trim().equalsIgnoreCase("reap"))
		{
			q2.setText(null);
		}
		else {
			q2.setText("reap");
		}
		
		if(a1.getText().trim().equalsIgnoreCase("important")||a2.getText().trim().equalsIgnoreCase("important")||a3.getText().trim().equalsIgnoreCase("important")||a4.getText().trim().equalsIgnoreCase("important"))
		{
			q3.setText(null);
		}
		else {
			q3.setText("important");
		}
		
		if(a1.getText().trim().equalsIgnoreCase("top")||a2.getText().trim().equalsIgnoreCase("top")||a3.getText().trim().equalsIgnoreCase("top")||a4.getText().trim().equalsIgnoreCase("top"))
		{
			q4.setText(null);
		}
		else {
			q4.setText("top");
		}
		
//		q5
		if(a1.getText().trim().equalsIgnoreCase("massive")||a2.getText().trim().equalsIgnoreCase("massive")||a3.getText().trim().equalsIgnoreCase("massive")||a4.getText().trim().equalsIgnoreCase("massive"))
		{
			q5.setText(null);
		}
		else {
			q5.setText("massive");
		}
		
//		q6
		if(a1.getText().trim().equalsIgnoreCase("range")||a2.getText().trim().equalsIgnoreCase("range")||a3.getText().trim().equalsIgnoreCase("range")||a4.getText().trim().equalsIgnoreCase("range"))
		{
			q6.setText(null);
		}
		else {
			q6.setText("range");
		}
		
		if(a1.getText().trim().equalsIgnoreCase("regulation")||a2.getText().trim().equalsIgnoreCase("regulation")||a3.getText().trim().equalsIgnoreCase("regulation")||a4.getText().trim().equalsIgnoreCase("regulation"))
		{
			q7.setText(null);
		}
		else {
			q7.setText("regulation");
		}
	}
}
