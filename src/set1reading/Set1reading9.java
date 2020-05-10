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

public class Set1reading9 {

	public JFrame frame;
	JLabel a1,a2,a3,a4,q1,q2,q3,q4,q5,q6,q7;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Set1reading9 window = new Set1reading9();
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
	public Set1reading9() {
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
		panel.setBounds(155, 414, 1087, 160);
		frame.getContentPane().add(panel);
		
		 q1 = new JLabel("universities");
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
		panel.add(q1);
		
		 q2 = new JLabel("combined");
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
		q2.setTransferHandler(new TransferHandler("text"));
		q2.setBorder(new LineBorder(new Color(128, 128, 128)));
		q2.setBounds(313, 31, 185, 29);
		panel.add(q2);
		
		 q3 = new JLabel("entrusted");
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
		q3.setTransferHandler(new TransferHandler("text"));
		q3.setBorder(new LineBorder(new Color(128, 128, 128)));
		q3.setBounds(590, 31, 185, 29);
		panel.add(q3);
		
		 q5 = new JLabel("confident");
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
		panel.add(q5);
		
		 q6 = new JLabel("compacted");
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
		q6.setTransferHandler(new TransferHandler("text"));
		q6.setBorder(new LineBorder(new Color(128, 128, 128)));
		q6.setBounds(313, 100, 185, 29);
		panel.add(q6);
		
		 q4 = new JLabel("contract");
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
		q4.setBounds(851, 31, 185, 29);
		panel.add(q4);
		
		 q7 = new JLabel("introduced");
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
		q7.setTransferHandler(new TransferHandler("text"));
		q7.setBorder(new LineBorder(new Color(128, 128, 128)));
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
		panel_1.setBounds(155, 228, 1087, 153);
		frame.getContentPane().add(panel_1);
		
		JLabel lblMacronIsWorried = new JLabel("Macron is worried about unaccountable \u201Cblack box\u201D algorithms being");
		lblMacronIsWorried.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMacronIsWorried.setBounds(10, 11, 441, 29);
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
		a1.setTransferHandler(new TransferHandler("text"));
		a1.setBorder(new LineBorder(new Color(128, 128, 128)));
		a1.setBounds(461, 11, 135, 29);
		panel_1.add(a1);
		
		JLabel lblToHumansHe = new JLabel("to humans. He gave the example of an algorithm used to sort students into");
		lblToHumansHe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblToHumansHe.setBounds(10, 44, 502, 29);
		panel_1.add(lblToHumansHe);
		
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
		a2.setTransferHandler(new TransferHandler("text"));
		a2.setBorder(new LineBorder(new Color(128, 128, 128)));
		a2.setBounds(910, 11, 140, 29);
		panel_1.add(a2);
		
		JLabel lblAndSaidThat = new JLabel("and said that if its workings were not easy to understand, it could");
		lblAndSaidThat.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAndSaidThat.setBounds(653, 44, 434, 29);
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
		a3.setTransferHandler(new TransferHandler("text"));
		a3.setBorder(new LineBorder(new Color(128, 128, 128)));
		a3.setBounds(515, 44, 128, 29);
		panel_1.add(a3);
		
		JLabel lblItCouldDestroy = new JLabel("it could destroy trust and encourage people to \u201Creject\u201D innovation.it could destroy trust and encourage people to \u201Creject\u201D innovation. \u201CI have to be");
		lblItCouldDestroy.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblItCouldDestroy.setBounds(10, 78, 953, 29);
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
		a4.setTransferHandler(new TransferHandler("text"));
		a4.setBorder(new LineBorder(new Color(128, 128, 128)));
		a4.setBounds(973, 78, 104, 29);
		panel_1.add(a4);
		
		JLabel lblIntoSocietyAnd = new JLabel("into society and making decisions formerly");
		lblIntoSocietyAnd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIntoSocietyAnd.setBounds(606, 11, 294, 29);
		panel_1.add(lblIntoSocietyAnd);
		
		JLabel lblForMyPeople = new JLabel("for my people that there is no bias, at least no unfair bias, in this algorithm,\u201D he said.");
		lblForMyPeople.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblForMyPeople.setBounds(10, 113, 706, 29);
		panel_1.add(lblForMyPeople);
		
		JLabel label_18 = new JLabel("<html><p align='CENTER'>In the text below some words are missing. Drag words from the text below to the appropriate place in the text. </p></html>");
		label_18.setOpaque(true);
		label_18.setHorizontalAlignment(SwingConstants.CENTER);
		label_18.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_18.setBorder(new MatteBorder(1, 0, 1, 0, (Color) new Color(0, 0, 0)));
		label_18.setBackground(new Color(240, 255, 240));
		label_18.setBounds(155, 137, 1087, 33);
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
		
		JLabel lblItemNo = new JLabel("Item no 9 of 15");
		lblItemNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblItemNo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblItemNo.setBounds(155, 640, 118, 24);
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
				Set1reading10 go=new Set1reading10();
				go.frame.setVisible(true);
			}
		});
		button_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_1.setBackground(Color.WHITE);
		button_1.setBounds(1153, 639, 89, 28);
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
			else
				JOptionPane.showMessageDialog(frame, "Reading answer file is missing!");
		}
		
	}
	
	public void organize()
	{
		if(a1.getText().trim().equalsIgnoreCase("universities")||a2.getText().trim().equalsIgnoreCase("universities")||a3.getText().trim().equalsIgnoreCase("universities")||a4.getText().trim().equalsIgnoreCase("universities"))
		{
			q1.setText(null);
		}
		else {
			q1.setText("universities");
		}
		
		if(a1.getText().trim().equalsIgnoreCase("combined")||a2.getText().trim().equalsIgnoreCase("combined")||a3.getText().trim().equalsIgnoreCase("combined")||a4.getText().trim().equalsIgnoreCase("combined"))
		{
			q2.setText(null);
		}
		else {
			q2.setText("combined");
		}
		
		if(a1.getText().trim().equalsIgnoreCase("entrasted")||a2.getText().trim().equalsIgnoreCase("entrasted")||a3.getText().trim().equalsIgnoreCase("entrasted")||a4.getText().trim().equalsIgnoreCase("entrasted"))
		{
			q3.setText(null);
		}
		else {
			q3.setText("entrasted");
		}
		
		if(a1.getText().trim().equalsIgnoreCase("contract")||a2.getText().trim().equalsIgnoreCase("contract")||a3.getText().trim().equalsIgnoreCase("contract")||a4.getText().trim().equalsIgnoreCase("contract"))
		{
			q4.setText(null);
		}
		else {
			q4.setText("contract");
		}
		
//		q5
		if(a1.getText().trim().equalsIgnoreCase("confident")||a2.getText().trim().equalsIgnoreCase("confident")||a3.getText().trim().equalsIgnoreCase("confident")||a4.getText().trim().equalsIgnoreCase("confident"))
		{
			q5.setText(null);
		}
		else {
			q5.setText("confident");
		}
		
//		q6
		if(a1.getText().trim().equalsIgnoreCase("compacted")||a2.getText().trim().equalsIgnoreCase("compacted")||a3.getText().trim().equalsIgnoreCase("compacted")||a4.getText().trim().equalsIgnoreCase("compacted"))
		{
			q6.setText(null);
		}
		else {
			q6.setText("compacted");
		}
		
		if(a1.getText().trim().equalsIgnoreCase("introduced")||a2.getText().trim().equalsIgnoreCase("introduced")||a3.getText().trim().equalsIgnoreCase("introduced")||a4.getText().trim().equalsIgnoreCase("introduced"))
		{
			q7.setText(null);
		}
		else {
			q7.setText("introduced");
		}
	}

}
