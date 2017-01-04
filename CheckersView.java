import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CheckersView extends JFrame {
	
	private CheckersModel checker;
	 private List<Point> fillCells;
	 
	
	public CheckersView(){

		checker = new CheckersModel(this);
	
		this.setBounds(100, 100, 650, 500);
		this.getContentPane().setBackground(Color.GRAY);
		this.getContentPane().setLayout(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);		
		menu();
	}
	

	
	public void showIt(String title){
		 this.setTitle(title);
		 this.setVisible(true);
	}
	
	public void menu() {
		
		this.getContentPane().removeAll();
		this.getContentPane().repaint();

	
		// The initial menu panel initiated at startup and its label.
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(6, 6, 638, 466);
		panel.setLayout(null);
		this.getContentPane().add(panel);
		
		// Labels and buttons of the initial menu panel.
		JLabel lblNewLabel = new JLabel("Nu skal vi spille Dam!");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		JButton newGame = new JButton("New Game");
		JButton scoreButton = new JButton("High Score");
		JButton exitButton = new JButton("Exit");
		
		// Placement and bounds.
		lblNewLabel.setBounds(220, 6, 209, 25);
		newGame.setBounds(250, 69, 155, 48);
		scoreButton.setBounds(250, 168, 155, 48);
		exitButton.setBounds(250, 271, 155, 48);
		
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

	
	
	public void game(){
		
		
		// Removes old panel.
		this.getContentPane().removeAll();
		this.getContentPane().repaint();
		
		// some kinda explicit comment about how it works with the different panels - also how it works with panelOne and Two.
		
		// The first new panel - the game panel.
		JPanel panelOne = new JPanel();
		panelOne.setBackground(Color.WHITE);
		panelOne.setBounds(6, 72, 400, 400);
		panelOne.setLayout(null);
		this.getContentPane().add(panelOne);
		
		// Label, bounds and position of text to the first panel.
		JLabel lblNewLabel = new JLabel("Spilleplade");
		lblNewLabel.setBounds(17, 39, 278, 125);

		
		// The second new panel - the score panel.
		JPanel panelTwo = new JPanel();
		panelTwo.setBounds(409, 6, 235, 466);
		panelTwo.setLayout(null);
		this.getContentPane().add(panelTwo);
		
		// Labels and buttons of the panel.
		JButton backButton = new JButton("Back To Menu");
		JLabel lblDam = new JLabel("DAM");	
		
		// Placement and bounds.
		backButton.setBounds(46, 414, 146, 29);
		lblDam.setBounds(106, 6, 61, 16);	
		
		// Adding the label and buttons to the panel.
		panelTwo.add(backButton);
		panelTwo.add(lblDam);	
			
		// Adding actionslisteners.
		CheckersController checkList = new CheckersController(checker);
		backButton.addActionListener(checkList);	
			
		// Adding the panel to the JFrame.
		this.getContentPane().add(panelOne);
		this.getContentPane().add(panelTwo);			
	}
	  
}
