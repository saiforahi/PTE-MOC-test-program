package set1reading;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.TransferHandler;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import main.Functions;
import main.Menu;
import main.ReadingAnswer;
import main.sqlConnection;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.SQLException;

public class Set1reading7 {

	public JFrame frame;
	JLabel q1,q2,q3,q4,q5,q6,q7,a1,a2,a3,a4;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Set1reading7 window = new Set1reading7();
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
	public Set1reading7() {
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
		panel.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(0, 0, 0)));
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1370, 56);
		frame.getContentPane().add(panel);
		
		JLabel lblReadingItemsfill = new JLabel("Reading Items");
		lblReadingItemsfill.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblReadingItemsfill.setBounds(10, 0, 497, 56);
		panel.add(lblReadingItemsfill);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(255, 140, 0));
		panel_1.setBounds(0, 55, 1370, 33);
		frame.getContentPane().add(panel_1);
		
		JButton button_2 = new JButton("<html><u>N</u>ext</html>");
		button_2.setBackground(Color.WHITE);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					fileSaver();
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(frame, e.getMessage());
				}
				frame.dispose();
				Set1reading8 go=new Set1reading8();
				go.frame.setVisible(true);
			}
		});
		button_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_2.setBounds(1176, 639, 89, 28);
		frame.getContentPane().add(button_2);
		
		JLabel lblItemNo = new JLabel("Item no 6 of 15");
		lblItemNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemNo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblItemNo.setBounds(135, 640, 118, 24);
		frame.getContentPane().add(lblItemNo);
		
		JLabel lblNewLabel = new JLabel("<html><p align='CENTER'>In the text below some words are missing. Drag words from the text below to the appropriate place in the text. </p></html>");
		lblNewLabel.setBackground(new Color(240, 255, 240));
		lblNewLabel.setBorder(new MatteBorder(1, 0, 1, 0, (Color) new Color(0, 0, 0)));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(163, 132, 1051, 33);
		lblNewLabel.setOpaque(true);
		frame.getContentPane().add(lblNewLabel);
		
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
		panel_2.setBorder(new MatteBorder(2, 0, 2, 0, (Color) new Color(0, 0, 0)));
		panel_2.setBackground(SystemColor.text);
		panel_2.setBounds(163, 225, 1051, 124);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
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
		a1.setBounds(216, 11, 185, 29);
		panel_2.add(a1);
		
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
		a3.setBounds(469, 44, 185, 29);
		a3.setTransferHandler(new TransferHandler("text"));
		a3.setBorder(new LineBorder(new Color(128, 128, 128)));
		panel_2.add(a3);
		
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
		a4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		a4.setHorizontalAlignment(SwingConstants.CENTER);
		a4.setBounds(152, 78, 170, 29);
		a4.setTransferHandler(new TransferHandler("text"));
		a4.setBorder(new LineBorder(new Color(128, 128, 128)));
		panel_2.add(a4);
		
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
		a2.setBounds(677, 11, 185, 29);
		a2.setTransferHandler(new TransferHandler("text"));
		a2.setBorder(new LineBorder(new Color(128, 128, 128)));
		panel_2.add(a2);
		
		JLabel lblNewLabel_1 = new JLabel("Wasps, as we know, turn up");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 11, 196, 29);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblAreStillAnother = new JLabel("are still another 100,000 waiting to be discovered. One recent study");
		lblAreStillAnother.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAreStillAnother.setBounds(10, 44, 449, 29);
		panel_2.add(lblAreStillAnother);
		
		JLabel lblNewLabel_4 = new JLabel("186 new wasp species in one small corner of Costa Rican");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(664, 44, 377, 29);
		panel_2.add(lblNewLabel_4);
		
		JLabel lblOneOfThe = new JLabel("rainforest alone. In");
		lblOneOfThe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblOneOfThe.setBounds(10, 78, 132, 29);
		panel_2.add(lblOneOfThe);
		
		JLabel lblMoreThan = new JLabel("More than 110,000 species have been");
		lblMoreThan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMoreThan.setBounds(411, 11, 256, 29);
		panel_2.add(lblMoreThan);
		
		JLabel lblAndItIs = new JLabel("and it is estimated there");
		lblAndItIs.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAndItIs.setBounds(872, 11, 169, 29);
		panel_2.add(lblAndItIs);
		
		JLabel lblThereAreOnly = new JLabel("there are only around 5,400 species of mammals, and 14,000 recorded species of ant.");
		lblThereAreOnly.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblThereAreOnly.setBounds(332, 78, 709, 29);
		panel_2.add(lblThereAreOnly);
		
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
		panel_3.setBorder(new MatteBorder(2, 0, 2, 0, (Color) new Color(0, 0, 0)));
		panel_3.setBackground(new Color(204, 255, 204));
		panel_3.setBounds(163, 411, 1051, 160);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		q1 = new JLabel("described");
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
		});
		q1.setHorizontalAlignment(SwingConstants.CENTER);
		q1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		q1.setTransferHandler(new TransferHandler("text"));
		q1.setOpaque(true);
		q1.setBorder(new LineBorder(new Color(128, 128, 128)));
		q1.setBounds(64, 31, 185, 29);
		panel_3.add(q1);
		
		q2 = new JLabel("completely");
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
		});
		q2.setHorizontalAlignment(SwingConstants.CENTER);
		q2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		q2.setTransferHandler(new TransferHandler("text"));
		q2.setOpaque(true);
		q2.setBorder(new LineBorder(new Color(128, 128, 128)));
		q2.setBounds(313, 31, 185, 29);
		panel_3.add(q2);
		
		q3 = new JLabel("everywhere");
		q3.setBackground(Color.WHITE);
		q3.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(q3.getText().equals(a1.getText()))
				{
					JComponent jc=(JComponent)arg0.getComponent();
					TransferHandler th=jc.getTransferHandler();
					th.exportAsDrag(jc, arg0, TransferHandler.COPY);
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
		});
		q3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		q3.setOpaque(true);
		q3.setTransferHandler(new TransferHandler("text"));
		q3.setHorizontalAlignment(SwingConstants.CENTER);
		q3.setBorder(new LineBorder(new Color(128, 128, 128)));
		q3.setBounds(578, 31, 185, 29);
		panel_3.add(q3);
		
		q5 = new JLabel("however");
		q5.setBackground(Color.WHITE);
		q5.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(q5.getText().equals(a1.getText()))
				{
					JComponent jc=(JComponent)arg0.getComponent();
					TransferHandler th=jc.getTransferHandler();
					th.exportAsDrag(jc, arg0, TransferHandler.COPY);
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
		});
		q5.setHorizontalAlignment(SwingConstants.CENTER);
		q5.setTransferHandler(new TransferHandler("text"));
		q5.setOpaque(true);
		q5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		q5.setBorder(new LineBorder(new Color(128, 128, 128)));
		q5.setBounds(64, 100, 185, 29);
		panel_3.add(q5);
		
		q6 = new JLabel("contrast");
		q6.setBackground(Color.WHITE);
		q6.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(q6.getText().equals(a1.getText()))
				{
					JComponent jc=(JComponent)arg0.getComponent();
					TransferHandler th=jc.getTransferHandler();
					th.exportAsDrag(jc, arg0, TransferHandler.COPY);
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
		});
		q6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		q6.setTransferHandler(new TransferHandler("text"));
		q6.setOpaque(true);
		q6.setHorizontalAlignment(SwingConstants.CENTER);
		q6.setBorder(new LineBorder(new Color(128, 128, 128)));
		q6.setBounds(313, 100, 185, 29);
		panel_3.add(q6);
		
		q4 = new JLabel("eliminated");
		q4.setBackground(Color.WHITE);
		q4.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(q4.getText().equals(a1.getText()))
				{
					JComponent jc=(JComponent)arg0.getComponent();
					TransferHandler th=jc.getTransferHandler();
					th.exportAsDrag(jc, arg0, TransferHandler.COPY);
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
		});
		q4.setOpaque(true);
		q4.setHorizontalAlignment(SwingConstants.CENTER);
		q4.setTransferHandler(new TransferHandler("text"));
		q4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		q4.setBorder(new LineBorder(new Color(128, 128, 128)));
		q4.setBounds(820, 31, 185, 29);
		panel_3.add(q4);
		
		q7 = new JLabel("identified");
		q7.setBackground(Color.WHITE);
		q7.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(q7.getText().equals(a1.getText()))
				{
					JComponent jc=(JComponent)arg0.getComponent();
					TransferHandler th=jc.getTransferHandler();
					th.exportAsDrag(jc, arg0, TransferHandler.COPY);
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
		});
		q7.setOpaque(true);
		q7.setHorizontalAlignment(SwingConstants.CENTER);
		q7.setTransferHandler(new TransferHandler("text"));
		q7.setFont(new Font("Tahoma", Font.PLAIN, 15));
		q7.setBorder(new LineBorder(new Color(128, 128, 128)));
		q7.setBounds(578, 100, 185, 29);
		panel_3.add(q7);
		
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
					ra.fillBlank.add("");
				}
				else
				{
					ra.fillBlank.add(a1.getText().trim());
				}
				if(a2.getText().equals(null)||a2.getText().equals(""))
				{
					ra.fillBlank.add("");
				}
				else
				{
					ra.fillBlank.add(a2.getText().trim());
				}
				if(a3.getText().equals(null)||a3.getText().equals(""))
				{
					ra.fillBlank.add("");
				}
				else
				{
					ra.fillBlank.add(a3.getText().trim());
				}
				if(a4.getText().equals(null)||a4.getText().equals(""))
				{
					ra.fillBlank.add("");
				}
				else
				{
					ra.fillBlank.add(a4.getText().trim());
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
			else
			{
				JOptionPane.showMessageDialog(frame, "Reading answer file is missing");
			}
		}
	}
	
	public void organize()
	{
		if(a1.getText().trim().equalsIgnoreCase("described")||a2.getText().trim().equalsIgnoreCase("described")||a3.getText().trim().equalsIgnoreCase("described")||a4.getText().trim().equalsIgnoreCase("described"))
		{
			q1.setText(null);
		}
		else {
			q1.setText("described");
		}
		
		if(a1.getText().trim().equalsIgnoreCase("completely")||a2.getText().trim().equalsIgnoreCase("completely")||a3.getText().trim().equalsIgnoreCase("completely")||a4.getText().trim().equalsIgnoreCase("completely"))
		{
			q2.setText(null);
		}
		else {
			q2.setText("completely");
		}
		
		if(a1.getText().trim().equalsIgnoreCase("everywhere")||a2.getText().trim().equalsIgnoreCase("everywhere")||a3.getText().trim().equalsIgnoreCase("everywhere")||a4.getText().trim().equalsIgnoreCase("everywhere"))
		{
			q3.setText(null);
		}
		else {
			q3.setText("everywhere");
		}
		
		if(a1.getText().trim().equalsIgnoreCase("eliminated")||a2.getText().trim().equalsIgnoreCase("eliminated")||a3.getText().trim().equalsIgnoreCase("eliminated")||a4.getText().trim().equalsIgnoreCase("eliminated"))
		{
			q4.setText(null);
		}
		else {
			q4.setText("eliminated");
		}
		
//		q5
		if(a1.getText().trim().equalsIgnoreCase("however")||a2.getText().trim().equalsIgnoreCase("however")||a3.getText().trim().equalsIgnoreCase("however")||a4.getText().trim().equalsIgnoreCase("however"))
		{
			q5.setText(null);
		}
		else {
			q5.setText("however");
		}
		
//		q6
		if(a1.getText().trim().equalsIgnoreCase("contrast")||a2.getText().trim().equalsIgnoreCase("contrast")||a3.getText().trim().equalsIgnoreCase("contrast")||a4.getText().trim().equalsIgnoreCase("contrast"))
		{
			q6.setText(null);
		}
		else {
			q6.setText("contrast");
		}
		
		if(a1.getText().trim().equalsIgnoreCase("identified")||a2.getText().trim().equalsIgnoreCase("identified")||a3.getText().trim().equalsIgnoreCase("identified")||a4.getText().trim().equalsIgnoreCase("identified"))
		{
			q7.setText(null);
		}
		else {
			q7.setText("identified");
		}
	}
	
}
