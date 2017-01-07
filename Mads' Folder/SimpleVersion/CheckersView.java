import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CheckersView extends JFrame {
	
	private CheckersModel checker;
	protected int boardSquareSize = 10;
	JPanel[][] tiles;
	int cursor = 1;
	
	// The icons used for pieces are instantiated and resized to always fit - no matter the size of boards and 
	// the size of n (commandLineArgument). 
	ImageIcon luke = new ImageIcon("luke.png");
	Image lukePic = luke.getImage();
	Image lukePicResize = lukePic.getScaledInstance(700/boardSquareSize, 700/boardSquareSize, Image.SCALE_SMOOTH);
	ImageIcon darth = new ImageIcon("darth.png");
	Image darthPic = darth.getImage();
	Image darthPicResize = darthPic.getScaledInstance(700/boardSquareSize, 700/boardSquareSize, Image.SCALE_SMOOTH);
	
	public CheckersView(String args){

		// The CheckersView class extends JFrame and therefore constructs the frames 
		// default size, color and so forth utilizing the this keyword.

		this.boardSquareSize = Integer.parseInt(args);
		checker = new CheckersModel(this);
		this.setBounds(100, 100, 566, 421);
		this.getContentPane().setBackground(Color.GRAY);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// Menu is called for the initial menu to show. 
		menu();
	}
	
	public void showIt(String title){
		
		// Sets the frame visible. 
		
		 this.setTitle(title);
		 this.setVisible(true);
	}
	
	public void menu() {
		
		// Resets the frame by clearing it of everything. 
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
		
		// Resets the frame by clearing it of everything. 
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
	
	
	public void play(int[][] pieceArray, int boardSquareSize) {
		
		// Play is called to refresh the board and redraw the pieces on the board.
		// It loops over pieceArray and if any of the values in the pieceArray[x][y] equals 1 or -1
		// either darth or luke is placed in the tiles[x][y] array which holds all the jpanels that
		// constitutes the board. If the value in the array is 0 then the panel in the tiles[][]
		// array is wiped clean. 
		
		// Luke and darth are saved as a ImagePanel in tiles[x][y] which is a custom class that extends JPanel
		// and allows for an overload of the graphics() function of JPanel. When overloaded this function allows
		// for drawing on the JPanel i.e. background drawings on a JPanel. 
		
		for (int i = 0; i < boardSquareSize; i++) {
			for (int j = 0; j < boardSquareSize; j++) {
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
				} else if (pieceArray[i][j] == 0) {
					tiles[i][j].removeAll();
					tiles[i][j].repaint();
				} else {
					continue;
				}
			}
		}		
	}		
}