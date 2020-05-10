package set1reading;

import java.awt.Color;
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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.SQLException;

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
import javax.swing.border.MatteBorder;

import main.Functions;
import main.Menu;
import main.ReadingAnswer;
import main.sqlConnection;

public class Set1reading5 {

	public JFrame frame;
	String[] question=new String[5];
	JLabel a1;
	JLabel q2;
	JLabel q1;
	JLabel a5;
	JLabel a2;
	JLabel a3;
	JLabel a4;
	JLabel q3;
	JLabel q4;
	JLabel q5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Set1reading5 window = new Set1reading5();
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
	public Set1reading5() {
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
		question[0]="Researchers from Case Western Reserve University School of Medicine and colleagues have discovered how two brain regions work together to maintain attention, and how discordance between the regions could lead to attention deficit disorders, including schizophrenia, bipolar disorder, and major depression.";
		question[1]="People with attention deficits have difficulty focusing and often display compulsive behavior. The new study suggests these symptoms could be due to dysfunction in a gene -- ErbB4 -- that helps different brain regions communicate.";
		question[2]="In a study published in the current issue of Neuron, researchers showed mice lacking ErbB4 activity in specific brain regions performed poorly on timed attention tasks.";
		question[3]="The mice struggled to pay attention and remember visual cues associated with food. Neuroscientists describe the kind of thought-driven attention required for the tasks as \\\"top-down attention.";
		question[4]="People who lack efficient top-down attention are at a higher risk for attention deficit hyperactivity disorder (ADHD). The study is the first to connect ErbB4 to top-down attention.";
		
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
		frame.setSize(new Dimension(screenSize.width,screenSize.height));
		frame.getContentPane().setPreferredSize(new Dimension(screenSize.width,screenSize.height));
		frame.setAlwaysOnTop(true);
		frame.setResizable(false);
		frame.setLocation(0,0);
		frame.getContentPane().setLayout(null);
		
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
		panel_3.setBackground(Color.WHITE);
		panel_3.setLayout(null);
		panel_3.setBounds(724, 99, 492, 509);
		panel_3.setBorder(new MatteBorder(2, 0, 2, 0, (Color) new Color(0, 0, 153)));
		frame.getContentPane().add(panel_3);
		
		a1 = new JLabel("");
		a1.setForeground(new Color(0, 0, 51));
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
		a1.setBackground(Color.WHITE);
		a1.setHorizontalAlignment(SwingConstants.CENTER);
		a1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		a1.setOpaque(true);
		a1.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(153, 51, 51)));
		a1.setTransferHandler(new TransferHandler("text"));
		a1.setBounds(10, 11, 472, 87);
		panel_3.add(a1);
		
		a2 = new JLabel("");
		a2.setForeground(new Color(0, 0, 51));
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
		a2.setBackground(Color.WHITE);
		a2.setHorizontalAlignment(SwingConstants.CENTER);
		a2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		a2.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(153, 51, 51)));
		a2.setBounds(10, 112, 472, 87);
		a2.setOpaque(true);
		a2.setTransferHandler(new TransferHandler("text"));
		panel_3.add(a2);
		
		a3 = new JLabel("");
		a3.setForeground(new Color(0, 0, 51));
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
		a3.setBackground(Color.WHITE);
		a3.setHorizontalAlignment(SwingConstants.CENTER);
		a3.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		a3.setOpaque(true);
		a3.setBounds(10, 214, 472, 87);
		a3.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(153, 51, 51)));
		a3.setTransferHandler(new TransferHandler("text"));
		panel_3.add(a3);
		
		a4 = new JLabel("");
		a4.setForeground(new Color(0, 0, 51));
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
		a4.setBackground(Color.WHITE);
		a4.setHorizontalAlignment(SwingConstants.CENTER);
		a4.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		a4.setBounds(10, 315, 472, 87);
		a4.setOpaque(true);
		a4.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(153, 51, 51)));
		a4.setTransferHandler(new TransferHandler("text"));
		panel_3.add(a4);
		
		a5 = new JLabel("");
		a5.setForeground(new Color(0, 0, 51));
		a5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				organize();
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				organize();
			}
		});
		a5.setBackground(Color.WHITE);
		a5.setHorizontalAlignment(SwingConstants.CENTER);
		a5.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		a5.setOpaque(true);
		a5.setBounds(10, 413, 472, 87);
		a5.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(153, 51, 51)));
		a5.setTransferHandler(new TransferHandler("text"));
		panel_3.add(a5);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1370, 56);
		frame.getContentPane().add(panel);
		
		JLabel lblReadingTestrearrange = new JLabel("Reading test ");
		lblReadingTestrearrange.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblReadingTestrearrange.setBounds(10, 0, 497, 56);
		panel.add(lblReadingTestrearrange);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(255, 140, 0));
		panel_1.setBounds(0, 55, 1370, 33);
		frame.getContentPane().add(panel_1);
		
		JLabel lblItemNo = new JLabel("Item no 5 of 15");
		lblItemNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemNo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblItemNo.setBounds(173, 634, 118, 24);
		frame.getContentPane().add(lblItemNo);
		
		JButton button_2 = new JButton("<html><u>N</u>ext</html>");
		button_2.setBackground(Color.WHITE);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					fileSaver();
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				frame.dispose();
				Set1reading6 go=new Set1reading6();
				go.frame.setVisible(true);
			}
		});
		button_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_2.setBounds(1127, 633, 89, 28);
		frame.getContentPane().add(button_2);
		
		JLabel lbllabSymbiotic = new JLabel("\u00A9Lab Symbiotic");
		lbllabSymbiotic.setVerticalAlignment(SwingConstants.TOP);
		lbllabSymbiotic.setHorizontalAlignment(SwingConstants.CENTER);
		lbllabSymbiotic.setForeground(Color.DARK_GRAY);
		lbllabSymbiotic.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		lbllabSymbiotic.setBounds(0, 704, 1370, 24);
		frame.getContentPane().add(lbllabSymbiotic);
		
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
		panel_2.setBackground(Color.WHITE);
		panel_2.setBorder(new MatteBorder(2, 0, 2, 0, (Color) new Color(0, 0, 51)));
		panel_2.setBounds(173, 99, 492, 509);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		q1 = new JLabel("<html><p align='CENTER'>Researchers from Case Western Reserve University School of Medicine and colleagues have discovered how two brain regions work together to maintain attention, and how discordance between the regions could lead to attention deficit disorders, including schizophrenia, bipolar disorder, and major depression.</p></html>");
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
				else if(q1.getText().equals(a5.getText()))
				{
					a5.setText(null);
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
		q1.setHorizontalAlignment(SwingConstants.CENTER);
		q1.setTransferHandler(new TransferHandler("text"));
		q1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		q1.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 153, 204)));
		q1.setBounds(10, 11, 472, 87);
		q1.setOpaque(true);
		panel_2.add(q1);
		
		q2 = new JLabel("<html><p align='CENTER'>People with attention deficits have difficulty focusing and often display compulsive behavior. The new study suggests these symptoms could be due to dysfunction in a gene -- ErbB4 -- that helps different brain regions communicate. </p></html>");
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
				else if(q2.getText().equals(a5.getText()))
				{
					a5.setText(null);
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
		q2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		q2.setBounds(10, 112, 472, 87);
		q2.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 153, 204)));
		q2.setTransferHandler(new TransferHandler("text"));
		q2.setOpaque(true);
		panel_2.add(q2);
		
		q3 = new JLabel("<html><p align='CENTER'>In a study published in the current issue of Neuron, researchers showed mice lacking ErbB4 activity in specific brain regions performed poorly on timed attention tasks.</p></html>");
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
				else if(q3.getText().equals(a5.getText()))
				{
					a5.setText(null);
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
		q3.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		q3.setTransferHandler(new TransferHandler("text"));
		q3.setBounds(10, 214, 472, 87);
		q3.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 153, 204)));
		q3.setOpaque(true);
		panel_2.add(q3);
		
		q4 = new JLabel("<html><p align='CENTER'>The mice struggled to pay attention and remember visual cues associated with food. Neuroscientists describe the kind of thought-driven attention required for the tasks as \"top-down attention.\"</p></html>");
		q4.setBackground(Color.WHITE);
		q4.setOpaque(true);
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
				else if(q4.getText().equals(a5.getText()))
				{
					a5.setText(null);
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
		q4.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		q4.setTransferHandler(new TransferHandler("text"));
		q4.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 153, 204)));
		q4.setBounds(10, 315, 472, 87);
		panel_2.add(q4);
		
		q5 = new JLabel("<html><p align='CENTER'>People who lack efficient top-down attention are at a higher risk for attention deficit hyperactivity disorder (ADHD). The study is the first to connect ErbB4 to top-down attention.</p></html>");
		q5.setBackground(Color.WHITE);
		q5.setOpaque(true);
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
				else if(q5.getText().equals(a5.getText()))
				{
					a5.setText(null);
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
		q5.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		q5.setTransferHandler(new TransferHandler("text"));
		q5.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 153, 204)));
		q5.setBounds(10, 413, 472, 87);
		panel_2.add(q5);
	}
	
/******************************** file saver **************************************/
	
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
				/*if(radiobtn3.isSelected())
				{
					la.highlightCorrectSummary=radiobtn3.getText();
				}*/
				if(a1.getText().equals(null)||a1.getText().equals(""))
				{
					ra.reArrange1.add("");
				}
				else
				{
					ra.reArrange1.add(a1.getText().trim());
				}
				if(a2.getText().equals(null)||a2.getText().equals(""))
				{
					ra.reArrange1.add("");
				}
				else
				{
					ra.reArrange1.add(a2.getText().trim());
				}
				if(a3.getText().equals(null)||a3.getText().equals(""))
				{
					ra.reArrange1.add("");
				}
				else
				{
					ra.reArrange1.add(a3.getText().trim());
				}
				if(a4.getText().equals(null)||a4.getText().equals(""))
				{
					ra.reArrange1.add("");
				}
				else
				{
					ra.reArrange1.add(a4.getText().trim());
				}
				if(a5.getText().equals(null)||a5.getText().equals(""))
				{
					ra.reArrange1.add("");
				}
				else
				{
					ra.reArrange1.add(a5.getText().trim());
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
				ra.reArrange1.add("");
			}
			else
			{
				ra.reArrange1.add(a1.getText().trim());
			}
			if(a2.getText().equals(null)||a2.getText().equals(""))
			{
				ra.reArrange1.add("");
			}
			else
			{
				ra.reArrange1.add(a2.getText().trim());
			}
			if(a3.getText().equals(null)||a3.getText().equals(""))
			{
				ra.reArrange1.add("");
			}
			else
			{
				ra.reArrange1.add(a3.getText().trim());
			}
			if(a4.getText().equals(null)||a4.getText().equals(""))
			{
				ra.reArrange1.add("");
			}
			else
			{
				ra.reArrange1.add(a4.getText().trim());
			}
			if(a5.getText().equals(null)||a5.getText().equals(""))
			{
				ra.reArrange1.add("");
			}
			else
			{
				ra.reArrange1.add(a5.getText().trim());
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
		if(a1.getText().trim().equalsIgnoreCase("<html><p align='CENTER'>"+question[0]+"</p></html>")||a2.getText().trim().equalsIgnoreCase("<html><p align='CENTER'>"+question[0]+"</p></html>")||a3.getText().trim().equalsIgnoreCase("<html><p align='CENTER'>"+question[0]+"</p></html>")||a4.getText().trim().equalsIgnoreCase("<html><p align='CENTER'>"+question[0]+"</p></html>")||a5.getText().trim().equalsIgnoreCase("<html><p align='CENTER'>"+question[0]+"</p></html>"))
		{
			q1.setText(null);
		}
		else {
			q1.setText("<html><p align='CENTER'>"+question[0]+"</p></html>");
		}
		
		if(a1.getText().trim().equalsIgnoreCase("<html><p align='CENTER'>"+question[1]+"</p></html>")||a2.getText().trim().equalsIgnoreCase("<html><p align='CENTER'>"+question[1]+"</p></html>")||a3.getText().trim().equalsIgnoreCase("<html><p align='CENTER'>"+question[1]+"</p></html>")||a4.getText().trim().equalsIgnoreCase("<html><p align='CENTER'>"+question[1]+"</p></html>")||a5.getText().trim().equalsIgnoreCase("<html><p align='CENTER'>"+question[1]+"</p></html>"))
		{
			q2.setText(null);
		}
		else {
			q2.setText("<html><p align='CENTER'>"+question[1]+"</p></html>");
		}
		
		if(a1.getText().trim().equalsIgnoreCase("<html><p align='CENTER'>"+question[2]+"</p></html>")||a2.getText().trim().equalsIgnoreCase("<html><p align='CENTER'>"+question[2]+"</p></html>")||a3.getText().trim().equalsIgnoreCase("<html><p align='CENTER'>"+question[2]+"</p></html>")||a4.getText().trim().equalsIgnoreCase("<html><p align='CENTER'>"+question[2]+"</p></html>")||a5.getText().trim().equalsIgnoreCase("<html><p align='CENTER'>"+question[2]+"</p></html>"))
		{
			q3.setText(null);
		}
		else {
			q3.setText("<html><p align='CENTER'>"+question[2]+"</p></html>");
		}
		
		if(a1.getText().trim().equalsIgnoreCase("<html><p align='CENTER'>"+question[3]+"</p></html>")||a2.getText().trim().equalsIgnoreCase("<html><p align='CENTER'>"+question[3]+"</p></html>")||a3.getText().trim().equalsIgnoreCase("<html><p align='CENTER'>"+question[3]+"</p></html>")||a4.getText().trim().equalsIgnoreCase("<html><p align='CENTER'>"+question[3]+"</p></html>")||a5.getText().trim().equalsIgnoreCase("<html><p align='CENTER'>"+question[3]+"</p></html>"))
		{
			q4.setText(null);
		}
		else {
			q4.setText("<html><p align='CENTER'>"+question[3]+"</p></html>");
		}
		
//		q5
		if(a1.getText().trim().equalsIgnoreCase("<html><p align='CENTER'>"+question[4]+"</p></html>")||a2.getText().trim().equalsIgnoreCase("<html><p align='CENTER'>"+question[4]+"</p></html>")||a3.getText().trim().equalsIgnoreCase("<html><p align='CENTER'>"+question[4]+"</p></html>")||a4.getText().trim().equalsIgnoreCase("<html><p align='CENTER'>"+question[4]+"</p></html>")||a5.getText().trim().equalsIgnoreCase("<html><p align='CENTER'>"+question[4]+"</p></html>"))
		{
			q5.setText(null);
		}
		else {
			q5.setText("<html><p align='CENTER'>"+question[4]+"</p></html>");
		}
	}
}

