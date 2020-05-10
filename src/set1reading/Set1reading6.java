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

public class Set1reading6 {

	public JFrame frame;
	String[] question=new String[5];
	JLabel a1;
	JLabel a2;
	JLabel a3;
	JLabel a4;
	JLabel a5;
	JLabel q1,q2,q3,q4,q5;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Set1reading6 window = new Set1reading6();
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
	public Set1reading6() {
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
		question[0]="<html><p align='CENTER'>New research shows that playful boys are viewed as rebellious and disruptive by their 1st, 2nd, and 3rd grade teachers whereas playful girls are not. </p></html>";
		question[1]="<html><p align='CENTER'>Due to observing teachers' attempts to discourage the expression of playfulness, the boys' classmates changed their view of these \"class clowns\" from initially positive to increasingly negative. </p></html>";
		question[2]="<html><p align='CENTER'>Children regularly observe playful boys, or 'class clowns', being treated negatively by their teachers, and over time come to change their view of them as desirable playmates in 1st and 2nd grades to being seen as boys who should be avoided or spurned in 3rd grade.</p></html>";
		question[3]="<html><p align='CENTER'>The teachers' disapproving view of playful boys was in contrast to the children's self-perception and how they were regarded by their peers -- who at the outset viewed playful boys as appealing and desired playmates.</p></html>";
		question[4]="<html><p align='CENTER'>The results confirmed dominant gender differences found by previous research in the field. Whereas teachers regarded playful boys as distinct from less playful boys, no such discrepancy was detected for girls.</p></html>";
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
		
		JLabel lbllabSymbiotic = new JLabel("\u00A9Lab Symbiotic");
		lbllabSymbiotic.setVerticalAlignment(SwingConstants.TOP);
		lbllabSymbiotic.setHorizontalAlignment(SwingConstants.CENTER);
		lbllabSymbiotic.setForeground(Color.DARK_GRAY);
		lbllabSymbiotic.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		lbllabSymbiotic.setBounds(0, 704, 1370, 24);
		frame.getContentPane().add(lbllabSymbiotic);
		
		JButton button = new JButton("<html><u>N</u>ext</html>");
		button.setBackground(Color.WHITE);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					fileSaver();
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				frame.dispose();
				Set1reading7 go=new Set1reading7();
				go.frame.setVisible(true);
			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 12));
		button.setBounds(1115, 645, 89, 28);
		frame.getContentPane().add(button);
		
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
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		panel.setBorder(new MatteBorder(2, 0, 2, 0, (Color) new Color(0, 0, 153)));
		panel.setBounds(712, 114, 492, 509);
		frame.getContentPane().add(panel);
		
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
		a1.setOpaque(true);
		a1.setHorizontalAlignment(SwingConstants.CENTER);
		a1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		a1.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(153, 51, 51)));
		a1.setBackground(Color.WHITE);
		a1.setFocusable(true);
		a1.setBounds(10, 11, 472, 87);
		a1.setTransferHandler(new TransferHandler("text"));
		panel.add(a1);
		
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
		a2.setBackground(Color.WHITE);
		a2.setHorizontalAlignment(SwingConstants.CENTER);
		a2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		a2.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(153, 51, 51)));
		a2.setOpaque(true);
		a2.setBounds(10, 112, 472, 87);
		a2.setTransferHandler(new TransferHandler("text"));
		panel.add(a2);
		
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
		a3.setBackground(Color.WHITE);
		a3.setHorizontalAlignment(SwingConstants.CENTER);
		a3.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		a3.setOpaque(true);
		a3.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(153, 51, 51)));
		a3.setTransferHandler(new TransferHandler("text"));
		a3.setBounds(10, 214, 472, 87);
		panel.add(a3);
		
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
		a4.setBackground(Color.WHITE);
		a4.setHorizontalAlignment(SwingConstants.CENTER);
		a4.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		a4.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(153, 51, 51)));
		a4.setOpaque(true);
		a4.setBounds(10, 315, 472, 87);
		a4.setTransferHandler(new TransferHandler("text"));
		panel.add(a4);
		
		a5 = new JLabel("");
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
		a5.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(153, 51, 51)));
		a5.setOpaque(true);
		a5.setBounds(10, 413, 472, 87);
		a5.setTransferHandler(new TransferHandler("text"));
		panel.add(a5);
		
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
		panel_1.setBackground(Color.WHITE);
		panel_1.setLayout(null);
		panel_1.setBorder(new MatteBorder(2, 0, 2, 0, (Color) new Color(0, 0, 153)));
		panel_1.setBounds(161, 114, 492, 509);
		frame.getContentPane().add(panel_1);
		
		q1 = new JLabel("<html><p align='CENTER'>New research shows that playful boys are viewed as rebellious and disruptive by their 1st, 2nd, and 3rd grade teachers whereas playful girls are not. </p></html>");
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
		q1.setOpaque(true);
		q1.setHorizontalAlignment(SwingConstants.CENTER);
		q1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		q1.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 153, 204)));
		q1.setBackground(Color.WHITE);
		q1.setTransferHandler(new TransferHandler("text"));
		q1.setBounds(10, 11, 472, 87);
		panel_1.add(q1);
		
		q2 = new JLabel("<html><p align='CENTER'>Due to observing teachers' attempts to discourage the expression of playfulness, the boys' classmates changed their view of these \"class clowns\" from initially positive to increasingly negative. </p></html>");
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
		q2.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 153, 204)));
		q2.setTransferHandler(new TransferHandler("text"));
		q2.setOpaque(true);
		q2.setBounds(10, 112, 472, 87);
		panel_1.add(q2);
		
		q3 = new JLabel("<html><p align='CENTER'>Children regularly observe playful boys, or 'class clowns', being treated negatively by their teachers, and over time come to change their view of them as desirable playmates in 1st and 2nd grades to being seen as boys who should be avoided or spurned in 3rd grade.</p></html>");
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
		q3.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 153, 204)));
		q3.setOpaque(true);
		q3.setTransferHandler(new TransferHandler("text"));
		q3.setBounds(10, 214, 472, 87);
		panel_1.add(q3);
		
		q4 = new JLabel("<html><p align='CENTER'>The teachers' disapproving view of playful boys was in contrast to the children's self-perception and how they were regarded by their peers -- who at the outset viewed playful boys as appealing and desired playmates.</p></html>");
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
		q4.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 153, 204)));
		q4.setOpaque(true);
		q4.setTransferHandler(new TransferHandler("text"));
		q4.setBounds(10, 315, 472, 87);
		panel_1.add(q4);
		
		q5 = new JLabel("<html><p align='CENTER'>The results confirmed dominant gender differences found by previous research in the field. Whereas teachers regarded playful boys as distinct from less playful boys, no such discrepancy was detected for girls.</p></html>");
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
		q5.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 153, 204)));
		q5.setOpaque(true);
		q5.setTransferHandler(new TransferHandler("text"));
		q5.setBounds(10, 413, 472, 87);
		panel_1.add(q5);
		
		JLabel lblItemNo = new JLabel("Item no 6 of 15");
		lblItemNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemNo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblItemNo.setBounds(161, 646, 118, 24);
		frame.getContentPane().add(lblItemNo);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(255, 140, 0));
		panel_2.setBounds(0, 55, 1370, 33);
		frame.getContentPane().add(panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(0, 0, 1370, 56);
		frame.getContentPane().add(panel_3);
		
		JLabel lblSpeakingTestreorder = new JLabel("Speaking Test");
		lblSpeakingTestreorder.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblSpeakingTestreorder.setBounds(10, 0, 497, 56);
		panel_3.add(lblSpeakingTestreorder);
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
					ra.reArrange2.add("");
				}
				else
				{
					ra.reArrange2.add(a1.getText().trim());
				}
				if(a2.getText().equals(null)||a2.getText().equals(""))
				{
					ra.reArrange2.add("");
				}
				else
				{
					ra.reArrange2.add(a2.getText().trim());
				}
				if(a3.getText().equals(null)||a3.getText().equals(""))
				{
					ra.reArrange2.add("");
				}
				else
				{
					ra.reArrange2.add(a3.getText().trim());
				}
				if(a4.getText().equals(null)||a4.getText().equals(""))
				{
					ra.reArrange2.add("");
				}
				else
				{
					ra.reArrange2.add(a4.getText().trim());
				}
				if(a5.getText().equals(null)||a5.getText().equals(""))
				{
					ra.reArrange2.add("");
				}
				else
				{
					ra.reArrange2.add(a5.getText().trim());
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
				JOptionPane.showMessageDialog(frame, "Reading answer file is missing");
		}
		
		
	}
	
	public void organize()
	{
		if(a1.getText().trim().equalsIgnoreCase(question[0])||a2.getText().trim().equalsIgnoreCase(question[0])||a3.getText().trim().equalsIgnoreCase(question[0])||a4.getText().trim().equalsIgnoreCase(question[0])||a5.getText().trim().equalsIgnoreCase(question[0]))
		{
			q1.setText(null);
		}
		else {
			q1.setText(question[0]);
		}
		
		if(a1.getText().trim().equalsIgnoreCase(question[1])||a2.getText().trim().equalsIgnoreCase(question[1])||a3.getText().trim().equalsIgnoreCase(question[1])||a4.getText().trim().equalsIgnoreCase(question[1])||a5.getText().trim().equalsIgnoreCase(question[1]))
		{
			q2.setText(null);
		}
		else {
			q2.setText(question[1]);
		}
		
		if(a1.getText().trim().equalsIgnoreCase(question[2])||a2.getText().trim().equalsIgnoreCase(question[2])||a3.getText().trim().equalsIgnoreCase(question[2])||a4.getText().trim().equalsIgnoreCase(question[2])||a5.getText().trim().equalsIgnoreCase(question[2]))
		{
			q3.setText(null);
		}
		else {
			q3.setText(question[2]);
		}
		
		if(a1.getText().trim().equalsIgnoreCase(question[3])||a2.getText().trim().equalsIgnoreCase(question[3])||a3.getText().trim().equalsIgnoreCase(question[3])||a4.getText().trim().equalsIgnoreCase(question[3])||a5.getText().trim().equalsIgnoreCase(question[3]))
		{
			q4.setText(null);
		}
		else {
			q4.setText(question[3]);
		}
		
//		q5
		if(a1.getText().trim().equalsIgnoreCase(question[4])||a2.getText().trim().equalsIgnoreCase(question[4])||a3.getText().trim().equalsIgnoreCase(question[4])||a4.getText().trim().equalsIgnoreCase(question[4])||a5.getText().trim().equalsIgnoreCase(question[4]))
		{
			q5.setText(null);
		}
		else {
			q5.setText(question[4]);
		}
	}
}
