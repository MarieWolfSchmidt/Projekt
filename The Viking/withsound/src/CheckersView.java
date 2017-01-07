import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;


public class CheckersView extends JFrame {
	
	private CheckersModel checker;
	protected int cmdLineArgument;
	JPanel[][] tiles; 
	
	Clip music;
	
	public CheckersView(String args){
		
		this.cmdLineArgument = Integer.parseInt(args);
		
		checker = new CheckersModel(this);
	
		this.setBounds(100, 100, 566, 421);
		this.getContentPane().setBackground(Color.GRAY);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		menu();
	}
	
	public void showIt(String title){
		 this.setTitle(title);
		 this.setVisible(true);
	}
	
	public void menu() {
		
		this.getContentPane().setLayout(null);
		this.getContentPane().removeAll();
		this.getContentPane().repaint();
	
		// The initial menu panel initiated at startup and its label.
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(6, 6, 554, 387);
		panel.setLayout(null);
		this.getContentPane().add(panel);
		
		// Labels and buttons of the initial menu panel.
		JLabel lblNewLabel = new JLabel("Nu skal vi spille Dam!");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		JButton newGame = new JButton("New Game");
		JButton scoreButton = new JButton("High Score");
		JButton exitButton = new JButton("Exit");
		
		// Placement and bounds.
		lblNewLabel.setBounds(168, 6, 209, 25);
		newGame.setBounds(197, 69, 155, 48);
		scoreButton.setBounds(197, 168, 155, 48);
		exitButton.setBounds(197, 271, 155, 48);
		
		// Adding the label and buttons to the panel.
		panel.add(lblNewLabel);
		panel.add(newGame);
		panel.add(scoreButton);
		panel.add(exitButton);
		
		// Adding actionslisteners.
		CheckersController checkList = new CheckersController(checker);
		
		newGame.addActionListener(checkList);
		scoreButton.addActionListener(checkList);
		exitButton.addActionListener(checkList);
		
		// Adding the panel to the JFrame.
		this.getContentPane().add(panel);
	}
	
	public void game(int boardSize) {
		
		// Removing the old panels.
		this.getContentPane().setLayout(null);
		this.getContentPane().removeAll();
		this.getContentPane().repaint();
		
		// Setting color variables. 
		Color BLACK = Color.BLACK;
		Color WHITE = Color.WHITE;
		Color DARKGREY = Color.DARK_GRAY;
		
		// Setting the JFrame.
		this.setSize(850, 722);
		this.setTitle("Checkers");
	
		// Instantiating the two main panels. 
		JPanel boardPanel = new JPanel(new GridLayout(boardSize, boardSize));
		JPanel scorePanel = new JPanel();
		
		scorePanel.setLayout(null);
		
		// Adding the two main panels to the Frame. 
		this.getContentPane().add(boardPanel);
		this.getContentPane().add(scorePanel);
		
		// Placing the two main panels. 
		boardPanel.setBounds(0, 0, 700, 700);		
		scorePanel.setBounds(700, 0, 150, 700);
		
		// Adding the black and white tiles to the boardPanel.
		tiles = new JPanel[boardSize][boardSize];
		Color temp;
		for (int i = 0; i < boardSize; i++){
			if ( i%2 == 0){
				temp = DARKGREY;
			} else {
				temp = WHITE;
			}
			for(int j = 0; j < boardSize; j++){
				
				JPanel panel = new JPanel();
				panel.setBackground(temp);
				tiles[i][j] = panel;
				
				if(temp.equals(DARKGREY)){
					temp = WHITE;
				} else {
					temp = DARKGREY;
				}
				boardPanel.add(panel);
			}
		}
		
		// The second new panel - the score panel.		
		// Labels and buttons for the panel.
		JButton backButton = new JButton("Back to Menu");
		
		// Placement and bounds.
		backButton.setBounds(25, 20, 100, 50);
		
		// Adding the label and buttons to the panel.
		scorePanel.add(backButton);
			
		// Adding actionslisteners.
		CheckersController checkList = new CheckersController(checker);
		backButton.addActionListener(checkList);	
		
		
		// Make an mouse event actionlistener that is added to the Jpanel boardpanel 
		
		boardPanel.addMouseListener(checkList);
		
		
		this.setVisible(true);	
	}
	
	
	public void play(int[][] pieceArray, int cmdLineArgument) {
		
		// Need to moved out of this function and into its proper place. 
		
		ImageIcon luke = new ImageIcon("luke.png");
		Image lukePic = luke.getImage();
		Image lukePicResize = lukePic.getScaledInstance(700/cmdLineArgument, 700/cmdLineArgument, Image.SCALE_SMOOTH);
		
		ImageIcon darth = new ImageIcon("darth.png");
		Image darthPic = darth.getImage();
		Image darthPicResize = darthPic.getScaledInstance(700/cmdLineArgument, 700/cmdLineArgument, Image.SCALE_SMOOTH);
		
		ImageIcon darthSelected = new ImageIcon("darthframe.png");
		Image darthSelectedPic = darthSelected.getImage();
		Image darthSelectedPicResize = darthSelectedPic.getScaledInstance(700/cmdLineArgument, 700/cmdLineArgument, Image.SCALE_SMOOTH);
		
		ImageIcon lukeSelected = new ImageIcon("lukeframe.png");
		Image lukeSelectedPic = lukeSelected.getImage();
		Image lukeSelectedPicResize = lukeSelectedPic.getScaledInstance(700/cmdLineArgument, 700/cmdLineArgument, Image.SCALE_SMOOTH);
		
		for (int i = 0; i < cmdLineArgument; i++) {
			for (int j = 0; j < cmdLineArgument; j++) {
				if (pieceArray[i][j] == 1) {
					ImagePanel testPanel = new ImagePanel(new ImageIcon(darthPicResize).getImage());
					tiles[i][j].removeAll();
					tiles[i][j].repaint();
					tiles[i][j].add(testPanel);
				} else if (pieceArray[i][j] == -1) {
					ImagePanel testPanel = new ImagePanel(new ImageIcon(lukePicResize).getImage());
					tiles[i][j].removeAll();
					tiles[i][j].repaint();
					tiles[i][j].add(testPanel);	
				} else if (pieceArray[i][j] == 2) {
					ImagePanel testPanel = new ImagePanel(new ImageIcon(darthSelectedPicResize).getImage());
					tiles[i][j].removeAll();
					tiles[i][j].repaint();
					tiles[i][j].add(testPanel);	
				} else if (pieceArray[i][j] == -2) {
					ImagePanel testPanel = new ImagePanel(new ImageIcon(lukeSelectedPicResize).getImage());
					tiles[i][j].removeAll();
					tiles[i][j].repaint();
					tiles[i][j].add(testPanel);	
				} else if (pieceArray[i][j] == 0) {
					tiles[i][j].removeAll();
					tiles[i][j].repaint();
				} else {
					continue;
				}
			}
		}	
		
	}		
	
	 public void winBox(String infoMessage, String titleBar)
	    {
//	        int input = JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
	        int input = JOptionPane.showOptionDialog(null, infoMessage, titleBar, JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
	        if ( input == JOptionPane.OK_OPTION){
	        	menu();
	        } else {
	        	menu();
	        }
	    }
	 
	 public void activateMusic()  {
		 try {
		 File audioFile = new File("theme.wav");
		 
		 
		 
		 AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
		 AudioFormat format = audioStream.getFormat();
		 
		 DataLine.Info info = new DataLine.Info(Clip.class, format);
		 Clip audioClip = (Clip) AudioSystem.getLine(info);
		 this.music = audioClip;
		 audioClip.open(audioStream);
		 audioClip.start();
		 
		 } catch (Exception ex){
			 System.out.println("Sound file not found");
			 
		 }
		 
		 
	 }
	 
	 public void stopMusic() {
		 music.stop();
	 }
				
//				tiles[i][j].setBackground(Color.red);
				
//				JLabel testLabel = new JLabel();
//				testLabel.setLayout(null);
//				testLabel.setBackground(Color.GREEN);
////				testLabel.setBounds(0, 0, 50, 50);
////				tiles[i][j].removeAll();
////				tiles[i][j].repaint();
//				tiles[i][j].add(testLabel);
//				tiles[i][j].setVisible(true);

		
}
	
//	public void game(){
//		
//		// Removes old panel.
//		this.getContentPane().removeAll();
//		this.getContentPane().repaint();
//		
//		// some kinda explicit comment about how it works with the different panels - also how it works with panelOne and Two.
//		
//		// The first new panel - the game panel.
//		JPanel panelOne = new JPanel();
//		panelOne.setBackground(Color.WHITE);
//		panelOne.setBounds(6, 6, 372, 387);
//		panelOne.setLayout(null);
//		this.getContentPane().add(panelOne);
//		
//		// Label, bounds and position of text to the first panel.
//		JLabel lblNewLabel = new JLabel("Spilleplade");
//		lblNewLabel.setBounds(17, 39, 278, 125);
//		panelOne.add(lblNewLabel);
//		
//		// The second new panel - the score panel.
//		JPanel panelTwo = new JPanel();
//		panelTwo.setBounds(379, 6, 181, 387);
//		panelTwo.setLayout(null);
//		this.getContentPane().add(panelTwo);
//		
//		// Labels and buttons of the panel.
//		JButton backButton = new JButton("Back To Menu");
//		JLabel lblDam = new JLabel("DAM");	
//		
//		// Placement and bounds.
//		backButton.setBounds(33, 352, 117, 29);
//		lblDam.setBounds(80, 6, 30, 16);	
//		
//		// Adding the label and buttons to the panel.
//		panelTwo.add(backButton);
//		panelTwo.add(lblDam);	
//			
//		// Adding actionslisteners.
//		CheckersController checkList = new CheckersController(checker);
//		backButton.addActionListener(checkList);	
//			
//		// Adding the panel to the JFrame.
//		this.getContentPane().add(panelOne);
//		this.getContentPane().add(panelTwo);			
//	