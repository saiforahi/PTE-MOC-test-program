package set1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import main.Functions;
import main.Menu;
import main.TakeTests;
import set2.Set2readAloud1;

public class MicCheck1 {
	public static int setNumber;
	public JFrame frame;
	public AudioFormat af;
	public TargetDataLine target;
	public Clip clip;
	public Mixer mixer;
	public JButton recButton;
	public JProgressBar progressBar;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MicCheck1 window = new MicCheck1();
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
	public MicCheck1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setNumber=TakeTests.setNumber;
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame = new JFrame();
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
					frame.dispose();
					Menu go=new Menu();
					go.frame.setVisible(true);
					go.frame.setLocationRelativeTo(null);
				}
				else frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			}
		});
		frame.setIconImage(Functions.setIcon());
		frame.pack();
		frame.setResizable(false);
		frame.setTitle("Microphone check");
		frame.setSize(new Dimension(screenSize.width, screenSize.height));
		frame.getContentPane().setPreferredSize(new Dimension(screenSize.width, screenSize.height));
		frame.setAlwaysOnTop(true);
		frame.setLocationRelativeTo(null);
		frame.setLocation(0,0);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(128, 0, 0));
		panel.setBounds(0, 35, 1364, 18);
		frame.getContentPane().add(panel);
		
		JLabel label = new JLabel("Microphone check");
		label.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 22));
		label.setBounds(81, 71, 306, 37);
		frame.getContentPane().add(label);
		
		JTextPane textPane = new JTextPane();
		textPane.setText("This is an oportunity to check that your microphone is functioning correctly.\r\n\r\n1. Make sure your headset is on and the microphone is in the downword position near your mouth.\r\n2. When you are read, click on the RECORD button and say \"TESTING, TESTING, ONE, TWO, THREE\" into the microphone.\r\n3. After you have spoken, click on the STOP button. Your recording is now complete.\r\n4. Now click on the PLAYBACK button. You should clearly hear your self speaking.\r\n5. If you can not hear your voice clearly, please check the following:\r\n        --- Headphone and Microphone jacks are plugged into the computer completely and correctly\r\n        --- Volume control on the headset or headset cord.\r\n        --- Volume control settings on the computer.\r\n\r\nIf you have checked all of those items and are still having trouble, your headset or computer system may not be operating correctly.\r\nPlease contact the appropriate customer service support.");
		textPane.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		textPane.setEditable(false);
		textPane.setBounds(81, 114, 1001, 266);
		frame.getContentPane().add(textPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(224, 255, 255));
		panel_1.setBounds(81, 390, 464, 206);
		frame.getContentPane().add(panel_1);
		
		JLabel label_1 = new JLabel("Recorded Answer");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		label_1.setBounds(37, 11, 409, 44);
		panel_1.add(label_1);
		
		JLabel label_2 = new JLabel("Current status");
		label_2.setHorizontalAlignment(SwingConstants.LEFT);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_2.setBounds(26, 56, 185, 25);
		panel_1.add(label_2);
		
		JLabel label_3 = new JLabel("Click record to begin");
		label_3.setHorizontalAlignment(SwingConstants.LEFT);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_3.setBounds(26, 78, 185, 25);
		panel_1.add(label_3);
		
		progressBar = new JProgressBar();
		progressBar.setMinimum(0);
		progressBar.setMaximum(100);
		progressBar.setBounds(26, 112, 413, 25);
		panel_1.add(progressBar);
		
		recButton = new JButton("Record");
		recButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Runnable r1 = new Runnable() {
					  public void run() {
						  recButton.setEnabled(false);
						 try {
					      do {
					    	  progressBar.setValue(progressBar.getValue()+1);
					        Thread.sleep(50);
					      }while(progressBar.getValue()<=100);
					    } catch (InterruptedException iex) {}
					 }
				};
				Runnable r2 = new Runnable() {
					  public void run() {
					    try {
					    	af=new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,44100,16, 2, 4, 44100, false);
							DataLine.Info info= new DataLine.Info(TargetDataLine.class, af);
							if(!AudioSystem.isLineSupported(info)){
								System.err.println("Line isn't supported!");
							}
							target= (TargetDataLine)AudioSystem.getLine(info);
							target.open();
							System.out.println("Starting recording....");
							target.start();
							System.out.println("Recording started....");
							Thread thread= new Thread()
							{
								@Override public void run(){
									AudioInputStream audioStream=new AudioInputStream(target);
									File f=new File("audio.wav");
									try {
										AudioSystem.write(audioStream, AudioFileFormat.Type.WAVE, f);
									} catch (IOException e) {
										e.printStackTrace();
									}
									System.out.println("Recording stopped....");
								}
							};
							thread.start();
							Thread.sleep(5000);
							target.stop();
							target.close();
							System.out.println("Ended....");
							Thread.sleep(5000L);
					   } catch (InterruptedException iex) {} catch (LineUnavailableException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					 }
				};
				Thread thread = new Thread(r1);
				Thread thread1 = new Thread(r2);
				thread.start();
				thread1.start();
			}
			
		});
		recButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		recButton.setBounds(26, 160, 89, 27);
		panel_1.add(recButton);
		
		JButton playButton = new JButton("Playback");
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mixer.Info[] mixInfo= AudioSystem.getMixerInfo();
				for(Mixer.Info info:mixInfo){
					System.out.println(info.getName()+"....."+info.getDescription());
				}
				mixer=AudioSystem.getMixer(mixInfo[1]);
				DataLine.Info dataInfo=new DataLine.Info(Clip.class, null);
				try {
					clip=(Clip)mixer.getLine(dataInfo);
				} catch (LineUnavailableException e) {
					e.printStackTrace();
				}
				
				try {
					AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File("audio.wav"));
					clip.open(audioStream);
					clip.start();
				} catch (UnsupportedAudioFileException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		playButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		playButton.setBounds(192, 160, 89, 27);
		panel_1.add(playButton);
		
		JButton stopButton = new JButton("Stop");
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(clip.isRunning())
				{
					clip.stop();
				}
			}
		});
		stopButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		stopButton.setBounds(346, 160, 89, 27);
		panel_1.add(stopButton);
		
		JLabel label_4 = new JLabel("Note: During the practice you will not have record, playback and stop buttons. The voice recording will start automatically.");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_4.setBounds(81, 606, 902, 25);
		frame.getContentPane().add(label_4);
		
		JButton button_3 = new JButton("Next");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(setNumber==1)
				{
					Set1speaking1 go=new Set1speaking1();
					go.frame.setVisible(true);
					frame.dispose();
				}
				else if(setNumber==2)
				{
					Set2readAloud1 go=new Set2readAloud1();
					go.frame.setVisible(true);
					frame.dispose();
				}
				
				
			}
		});
		button_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		button_3.setBounds(1177, 606, 89, 27);
		frame.getContentPane().add(button_3);
		
		JLabel lblNewLabel = new JLabel("    Set 1");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(0, 1, 547, 33);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel label_5 = new JLabel("\u00A9Lab Symbiotic");
		label_5.setVerticalAlignment(SwingConstants.TOP);
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setForeground(Color.DARK_GRAY);
		label_5.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		label_5.setBounds(0, 704, 1370, 24);
		frame.getContentPane().add(label_5);
	}
}
