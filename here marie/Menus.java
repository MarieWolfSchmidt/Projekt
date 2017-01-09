package View;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import Controller.CheckersController;
import Model.Pieces;

public class Menus  {
	
	private CheckersView view;
	public Draw draw;

	public Menus(CheckersView view){
		this.view=view;
		view.getContentPane().removeAll();
		view.getContentPane().repaint();
	} 
	
	public void titlebar(JFrame frame){
		// creating a menu bar
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		JMenu game = new JMenu("Game");
		menuBar.add(game);
		JMenuItem newGame = new JMenuItem("New Game");
		game.add(newGame);
		JMenuItem exitGame = new JMenuItem("Exit");
		game.add(exitGame);
		
		
		CheckersController titleListeners = new CheckersController(view.checker);
		newGame.addActionListener(titleListeners);
		exitGame.addActionListener(titleListeners);
		
	}
	
	public void menu() {
		// Removes old panels.
		view.getContentPane().removeAll();
		view.getContentPane().repaint();
	
		// The initial menu panel initiated at startup and its label.
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(6, 6, 1250, 800);
		panel.setLayout(null);
		view.getContentPane().add(panel);
		
		// Labels and buttons of the initial menu panel.
		JLabel lblNewLabel = new JLabel("Let's play Checkers");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 48));
		JButton newGame = new JButton("New Game");
		JButton scoreButton = new JButton("High Score");
		JButton exitButton = new JButton("Exit");
		
		// Placement and bounds.
		lblNewLabel.setBounds(425, 100, 809, 150);
		newGame.setBounds(545, 270, 209, 100);
		scoreButton.setBounds(545, 410, 209, 100);
		exitButton.setBounds(545, 560, 209, 100);
		
		// Adding the label and buttons to the panel.
		panel.add(lblNewLabel);
		panel.add(newGame);
		panel.add(scoreButton);
		panel.add(exitButton);
		
		// Adding actionslisteners.
		CheckersController checkList = new CheckersController(view.checker);
		
		newGame.addActionListener(checkList);
		scoreButton.addActionListener(checkList);
		exitButton.addActionListener(checkList);
		
		// Adding the panel to the JFrame.
		view.getContentPane().add(panel);
	}
	
	public void game(int cmdLineArgs, List<Pieces> pieceList, int boardSize, int squareDim, int finalBoardSize, int pieceSize){
		// Removes old panel.
		view.getContentPane().removeAll();
		view.getContentPane().repaint();
		
		// some kinda explicit comment about how it works with the different panels - also how it works with panelOne and Two.
		
		// The first new panel - the game panel.
		JPanel panelOne = new JPanel();
		panelOne.setBounds(6, 6, 800, 800);
		panelOne.setLayout(null);
		view.getContentPane().add(panelOne);
		
		//Adding the board and the pieces. 
		this.draw = new Draw(pieceList, boardSize, squareDim, finalBoardSize, pieceSize);
		draw.repaintBoardAndPieces();
		draw.setSize(draw.finalBoardSize,draw.finalBoardSize);
		panelOne.add(draw);
		
		//The second new panel - the score panel.
		JPanel panelTwo = new JPanel();
		panelTwo.setBounds(810, 6, 385, 666);
		panelTwo.setLayout(null);
		view.getContentPane().add(panelTwo);
		
		// Labels and buttons of the panel.
		JButton backButton = new JButton("Back To Menu");
		JLabel lblCheckers = new JLabel("Checkers");	
		JLabel beginsgame = new JLabel("The player with the red pieces starts the game");	
		
		// Placement and bounds.
		backButton.setBounds(90, 630, 200, 29);
		lblCheckers.setBounds(140, 6, 100, 16);	
		lblCheckers.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		beginsgame.setBounds(20, 40, 300, 16);	
		
		// Adding the label and buttons to the panel.
		panelTwo.add(backButton);
		panelTwo.add(lblCheckers);	
		panelTwo.add(beginsgame);
			
		//Adding actionslisteners.
		CheckersController checkList = new CheckersController(view.checker);
		backButton.addActionListener(checkList);
		panelOne.addMouseListener(checkList);
		panelOne.addMouseMotionListener(checkList);
			
		// Adding the panel to the JFrame.
		view.getContentPane().add(panelOne);
		view.getContentPane().add(panelTwo);			
	}

}