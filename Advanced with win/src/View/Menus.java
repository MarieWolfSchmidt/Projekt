package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import Controller.CheckersController;
import Model.Pieces;

public class Menus  {
	
	private CheckersView view;
	public Draw draw;
	
	
	private JLabel gameTimer;
	private ActionListener timePerformer;
	private Timer time;
	private int sec = 0;
	private int min = 0;
	private int hour = 0;
	private String seconds = "";
	private String minutes = "";
	private String hours = "";
	
	

	public Menus(CheckersView view){
		this.view = view;
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
		newGame.setToolTipText("Starts a new game");
		game.add(newGame);
		JMenuItem exitGame = new JMenuItem("Exit");
		game.add(exitGame);
		
		JMenu settings = new JMenu("Settings");
		menuBar.add(settings);
		JMenuItem musicButton = new JMenuItem("Music On/Off");
		settings.add(musicButton);
		JMenuItem defaultTheme = new JMenuItem("Default theme");
		defaultTheme.setToolTipText("Theme does not change until a new game is started");
		settings.add(defaultTheme);
		JMenuItem pinkTheme = new JMenuItem("Pink theme");
		pinkTheme.setToolTipText("Theme does not change until a new game is started");
		settings.add(pinkTheme);
		
		
		CheckersController titleListeners = new CheckersController(view.checker);
		newGame.addActionListener(titleListeners);
		exitGame.addActionListener(titleListeners);
		musicButton.addActionListener(titleListeners);
		defaultTheme.addActionListener(titleListeners);
		pinkTheme.addActionListener(titleListeners);
		
	}
	
	public void menu() {
		// Removes old panels.
		view.getContentPane().removeAll();
		view.getContentPane().repaint();
		
		// The initial menu panel initiated at startup and its label.
		
		
		
		JPanel panel = new JPanel();
//		panel.setBackground(Color.WHITE);
		panel.setOpaque(false);
		panel.setBounds(6, 6, 1250, 800);
		panel.setLayout(null);
		view.getContentPane().add(panel);
		
		// Labels and buttons of the initial menu panel.
		JLabel checkersLabel = new JLabel("Let's play Checkers");
		checkersLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 48));
		checkersLabel.setForeground(Color.WHITE);
		
		JButton newGame = new JButton("New Game");
		newGame.setBackground(Color.DARK_GRAY);
		newGame.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		newGame.setForeground(Color.WHITE);
		
		JButton scoreButton = new JButton("High Score");
		scoreButton.setBackground(Color.DARK_GRAY);
		scoreButton.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		scoreButton.setForeground(Color.WHITE);
		
		JButton exitButton = new JButton("Exit");
		exitButton.setBackground(Color.DARK_GRAY);
		exitButton.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		exitButton.setForeground(Color.WHITE);
		
		JButton rules = new JButton("Rules");
		rules.setBackground(Color.DARK_GRAY);
		rules.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		rules.setForeground(Color.WHITE);
		
		JButton settings = new JButton("Settings");
		settings.setBackground(Color.DARK_GRAY);
		settings.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		settings.setForeground(Color.WHITE);
		
		
		
		
		// Placement and bounds.
		checkersLabel.setBounds(425, 100, 809, 150);
		newGame.setBounds(405, 270, 458, 100);
		scoreButton.setBounds(405, 410, 209, 100);
		exitButton.setBounds(654, 550, 209, 100);
		rules.setBounds(405, 550, 209, 100);
		settings.setBounds(654, 410, 209, 100);
		
		// Adding the label and buttons to the panel.
		panel.add(checkersLabel);
		panel.add(newGame);
		panel.add(scoreButton);
		panel.add(exitButton);
		panel.add(rules);
		panel.add(settings);
		
		// Adding actionslisteners.
		CheckersController checkList = new CheckersController(view.checker);
		
		newGame.addActionListener(checkList);
		scoreButton.addActionListener(checkList);
		exitButton.addActionListener(checkList);
		rules.addActionListener(checkList);
		settings.addActionListener(checkList);
		
		// Adding the panel to the JFrame.
		view.getContentPane().add(panel);
	}
	
	public void settings(){
		view.getContentPane().removeAll();
		view.getContentPane().repaint();
		
		JPanel settingsPanel = new JPanel();
		settingsPanel.setBackground(Color.WHITE);
		settingsPanel.setBounds(6, 6, 1250, 800);
		settingsPanel.setLayout(null);
		settingsPanel.setOpaque(false);
		view.getContentPane().add(settingsPanel);
		
		JLabel settingsLabel = new JLabel("Settings", SwingConstants.CENTER);
		settingsLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 48));
		settingsLabel.setBounds(425, 100, 400, 150);
		settingsLabel.setForeground(Color.WHITE);
		settingsPanel.add(settingsLabel);
		
		
		JButton normalTheme = new JButton("Default theme");
		normalTheme.setBounds(530, 390, 209, 50);
		normalTheme.setBackground(Color.DARK_GRAY);
		normalTheme.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		normalTheme.setForeground(Color.WHITE);
		settingsPanel.add(normalTheme);
		
		JButton pinkTheme = new JButton("Pink theme");
		pinkTheme.setBounds(530, 480, 209, 50);
		pinkTheme.setBackground(Color.DARK_GRAY);
		pinkTheme.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		pinkTheme.setForeground(Color.WHITE);
		settingsPanel.add(pinkTheme);
		
		JButton toggleMusic = new JButton("Music On/Off");
		toggleMusic.setBounds(530, 570, 209, 50);
		toggleMusic.setBackground(Color.DARK_GRAY);
		toggleMusic.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		toggleMusic.setForeground(Color.WHITE);
		settingsPanel.add(toggleMusic);
		
		JButton backButton = new JButton("Back To Menu");
		backButton.setBounds(530, 660, 209, 30);
		backButton.setBackground(Color.DARK_GRAY);
		backButton.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		backButton.setForeground(Color.WHITE);
		settingsPanel.add(backButton);
		
		//actionlisteners for settingspanel
		CheckersController settingsList = new CheckersController(view.checker);
		toggleMusic.addActionListener(settingsList);
		backButton.addActionListener(settingsList);
		normalTheme.addActionListener(settingsList);
		pinkTheme.addActionListener(settingsList);
		
	}
	
	public void rules(){
		
		view.getContentPane().removeAll();
		view.getContentPane().repaint();
		
		JPanel rulePanel = new JPanel();
		rulePanel.setBounds(6, 6, 1250, 800);
		rulePanel.setLayout(null);
		rulePanel.setOpaque(false);
		view.getContentPane().add(rulePanel);
		
		JLabel lblRules = new JLabel("RULES", SwingConstants.CENTER);
		lblRules.setBounds(425, 100, 400, 150);
		lblRules.setFont(new Font("Lucida Grande", Font.PLAIN, 48));
		lblRules.setForeground(Color.WHITE);
		
		JButton howToButton = new JButton("How To Play");
		howToButton.setBounds(1035, 550, 209, 100);
		howToButton.setBackground(Color.DARK_GRAY);
		howToButton.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		howToButton.setForeground(Color.WHITE);
		
		JButton rulesBackButton = new JButton("Back To Menu");
		rulesBackButton.setBounds(1035, 690, 209, 100);
		rulesBackButton.setBackground(Color.DARK_GRAY);
		rulesBackButton.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		rulesBackButton.setForeground(Color.WHITE);
		
		JTextArea txtRules = new JTextArea();
		txtRules.setBounds(400, 250, 525, 280);
		txtRules.setFont(new Font("Calibri", Font.PLAIN, 20));
		
		
		
		rulePanel.add(lblRules);
		rulePanel.add(howToButton);
		rulePanel.add(rulesBackButton);
		rulePanel.add(txtRules);
		
		
		txtRules.setText(" \r\n - Player at top starts the game."
				+ "\r\n - Each player take turns moving their piece.\r\n - When a piece reaches the opponent's piece it can \r\n    jump over it,"
				+ " which removes the opponent's piece. \r\n - The game is over when there's only one piece left."
				+ "\r\n - The player with the last piece wins the game. \r\n - When a piece reaches the opposite site of the board, "
				+ "\r\n   it becomes \"Dam\", and is now able to move backwards.\r\n - The piece becomes \"Dam\" in the next turn. \r\n");
		txtRules.setBackground(Color.LIGHT_GRAY);
		
		
		CheckersController rulesList = new CheckersController(view.checker);
		rulesBackButton.addActionListener(rulesList);
		howToButton.addActionListener(rulesList);
		
		view.getContentPane().add(rulePanel);
	}
	
	public void howTo(){
		
		view.getContentPane().removeAll();
		view.getContentPane().repaint();
		
		JPanel howToPanel = new JPanel();
		howToPanel.setBounds(6, 6, 1250, 800);
		howToPanel.setLayout(null);
		howToPanel.setOpaque(false);
		view.getContentPane().add(howToPanel);
		
		JLabel lblHowTo = new JLabel("How To Play", SwingConstants.CENTER);
		lblHowTo.setBounds(425, 100, 400, 150);
		lblHowTo.setFont(new Font("Lucida Grande", Font.PLAIN, 48));
		lblHowTo.setForeground(Color.WHITE);
		
		JTextArea txtHowTo = new JTextArea();
		txtHowTo.setBounds(400, 250, 525, 280);
		txtHowTo.setFont(new Font("Calibri", Font.PLAIN, 20));
		
		JButton ruleButton = new JButton("Rules");
		ruleButton.setBounds(1035, 550, 209, 100);
		ruleButton.setBackground(Color.DARK_GRAY);
		ruleButton.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		ruleButton.setForeground(Color.WHITE);
		
		JButton howToBackButton = new JButton("Back To Menu");
		howToBackButton.setBounds(1035, 690, 209, 100);
		howToBackButton.setBackground(Color.DARK_GRAY);
		howToBackButton.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		howToBackButton.setForeground(Color.WHITE);
		
		
		
		howToPanel.add(lblHowTo);
		howToPanel.add(txtHowTo);
		howToPanel.add(ruleButton);
		howToPanel.add(howToBackButton);
		
		txtHowTo.setText("\r\n \r\n - To move the pieces click the piece and drag it to \r\n   the desired desination. "
				+ "\r\n - You can only move the piece to a legal Checkers move."
				+ "\r\n - The piece will go back to the old position, \r\n   if it is moved to an illegal destination, "
				+ "\r\n    and the player has to move their piece again. \r\n");
		txtHowTo.setBackground(Color.LIGHT_GRAY);
		
		CheckersController howToList = new CheckersController(view.checker);
		ruleButton.addActionListener(howToList);
		howToBackButton.addActionListener(howToList);
		
		view.getContentPane().add(howToPanel);

	}
	
	
	public void highScore(){
		view.getContentPane().removeAll();
		view.getContentPane().repaint();
		
		JPanel highScorePanel = new JPanel();
		view.getContentPane().add(highScorePanel);
		highScorePanel.setBounds(6, 6, 1250, 800);
		highScorePanel.setBackground(Color.WHITE);
		highScorePanel.setOpaque(false);
		
		JButton toMenu = new JButton("Back To Menu");
		toMenu.setBounds(1035, 690, 209, 100);
		toMenu.setBackground(Color.DARK_GRAY);
		toMenu.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		toMenu.setForeground(Color.WHITE);
		highScorePanel.add(toMenu);
		
		CheckersController hsList = new CheckersController(view.checker);
		toMenu.addActionListener(hsList);
		
		JLabel hsTitle = new JLabel("High scores", SwingConstants.CENTER);
		hsTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 48));
		hsTitle.setBounds(425, 100, 400, 150);
		hsTitle.setForeground(Color.WHITE);
		highScorePanel.add(hsTitle);
		
		JLabel name = new JLabel("Player Name");
		name.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		name.setBounds(475, 200, 200, 100);
		name.setForeground(Color.RED);
		highScorePanel.add(name);
		
		JLabel amountOfMoves = new JLabel("Moves");
		amountOfMoves.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		amountOfMoves.setBounds(725, 200, 200, 100);
		amountOfMoves.setForeground(Color.WHITE);
		highScorePanel.add(amountOfMoves);
		
		JLabel firstPlace = new JLabel("1.");
		firstPlace.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		firstPlace.setBounds(425, 235, 100, 100);
		firstPlace.setForeground(Color.WHITE);
		highScorePanel.add(firstPlace);
		
		JLabel topPlayer = new JLabel("Ulfur");
		topPlayer.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		topPlayer.setBounds(475, 235, 100, 100);
		topPlayer.setForeground(Color.WHITE);
		highScorePanel.add(topPlayer);
		
		JLabel topMoves = new JLabel("1");
		topMoves.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		topMoves.setBounds(725, 235, 100, 100);
		topMoves.setForeground(Color.WHITE);
		highScorePanel.add(topMoves);
		
		JLabel secondPlace = new JLabel("2.");
		secondPlace.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		secondPlace.setBounds(425, 270, 100, 100);
		secondPlace.setForeground(Color.WHITE);
		highScorePanel.add(secondPlace);
		
		JLabel secondPlayer = new JLabel("Ulfur");
		secondPlayer.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		secondPlayer.setBounds(475, 270, 100, 100);
		secondPlayer.setForeground(Color.WHITE);
		highScorePanel.add(secondPlayer);
		
		JLabel secondMoves = new JLabel("3");
		secondMoves.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		secondMoves.setBounds(725, 270, 100, 100);
		secondMoves.setForeground(Color.WHITE);
		highScorePanel.add(secondMoves);
		
		JLabel thirdPlace = new JLabel("3.");
		thirdPlace.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		thirdPlace.setBounds(425, 305, 100, 100);
		thirdPlace.setForeground(Color.WHITE);
		highScorePanel.add(thirdPlace);
		
		JLabel thirdPlayer = new JLabel("Ulfur");
		thirdPlayer.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		thirdPlayer.setBounds(475, 305, 100, 100);
		thirdPlayer.setForeground(Color.WHITE);
		highScorePanel.add(thirdPlayer);
		
		JLabel thirdMoves = new JLabel("6");
		thirdMoves.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		thirdMoves.setBounds(725, 305, 100, 100);
		thirdMoves.setForeground(Color.WHITE);
		highScorePanel.add(thirdMoves);
		
		JLabel fourthPlace = new JLabel("4.");
		fourthPlace.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		fourthPlace.setBounds(425, 340, 100, 100);
		fourthPlace.setForeground(Color.WHITE);
		highScorePanel.add(fourthPlace);
		
		JLabel fourthPlayer = new JLabel("Mads");
		fourthPlayer.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		fourthPlayer.setBounds(475, 340, 100, 100);
		fourthPlayer.setForeground(Color.WHITE);
		highScorePanel.add(fourthPlayer);
		
		JLabel fourthMoves = new JLabel("did not finish");
		fourthMoves.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		fourthMoves.setBounds(725, 340, 100, 100);
		fourthMoves.setForeground(Color.WHITE);
		highScorePanel.add(fourthMoves);
		
	}
	
	public void game(int cmdLineArgs, List<Pieces> pieceList, int boardSize, int squareDim, int finalBoardSize, int pieceSize, Color squareCol){
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
		this.draw = new Draw(pieceList, boardSize, squareDim, finalBoardSize, pieceSize, squareCol);
		draw.repaintBoardAndPieces();
		draw.setSize(draw.finalBoardSize,draw.finalBoardSize);
		panelOne.add(draw);
		
		//The second new panel - the score panel.
		JPanel panelTwo = new JPanel();
		panelTwo.setBounds(916, 6, 334, 800);
		panelTwo.setLayout(null);
		view.getContentPane().add(panelTwo);
		
		// Labels and buttons of panelTwo.
		JButton backButton = new JButton("Back To Menu");
		JLabel lblCheckers = new JLabel("Checkers");	
		JLabel beginsgame = new JLabel("The player with the red pieces starts the game");
		JButton toggleMusic = new JButton("Music On/Off");
		
		//turn marker
		JLabel turn = new JLabel("TURN", SwingConstants.CENTER);
		turn.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		turn.setBounds(100, 350, 150, 30);
		turn.setOpaque(true);
		turn.setBackground(Color.RED);
		turn.setForeground(Color.CYAN);
		
		// Placement and bounds.
		toggleMusic.setBounds(70, 710, 200, 29);
		backButton.setBounds(70, 760, 200, 29);
		lblCheckers.setBounds(140, 6, 100, 16);	
		lblCheckers.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		beginsgame.setBounds(20, 40, 300, 16);
		
		//adding timers for both players
		gameTimer = new JLabel("00:00:00");
		gameTimer.setBounds(140, 250, 150, 30);
		gameTimer.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		//if a timer is already running this has to run so the new timer wont go twice as fast as the old one
		if (time != null){
			stopTimer();
		}
		
		resetTimeLabel();
		
		// Adding the label and buttons to the panel.
		panelTwo.add(toggleMusic);
		panelTwo.add(backButton);
		panelTwo.add(lblCheckers);	
		panelTwo.add(beginsgame);
		panelTwo.add(turn);
		panelTwo.add(gameTimer);
		
			
		//Adding actionslisteners.
		CheckersController checkList = new CheckersController(view.checker);
		backButton.addActionListener(checkList);
		panelOne.addMouseListener(checkList);
		panelOne.addMouseMotionListener(checkList);
		toggleMusic.addActionListener(checkList);
		
		//the third panel - slain pieces
				JPanel panelThree = new JPanel();
				panelThree.setBounds(810, 6, 102, 800);
				panelThree.setLayout(null);
				view.getContentPane().add(panelThree);
				
		//stuff is to put here for the third panel
				
			
		// Adding the panel to the JFrame.
		view.getContentPane().add(panelOne);
		view.getContentPane().add(panelTwo);			
	}
	
	// a function that creates the timer for the game and makes sure it is in the right format
	public void resetTimeLabel(){
		this.sec = 0;
		this.min = 0;
		this.hour = 0;
		
		// the timer needs a actionlistener to be able to count
		timePerformer = new ActionListener() {
	          public void actionPerformed(ActionEvent evt) {
	        	  seconds = Integer.toString(sec);
	        	  minutes = Integer.toString(min);
	        	  hours = Integer.toString(hour);
	        	  
	        	  if (sec < 59){
	        		  sec++; 
	        	  } else if (sec == 59){
	        		  sec = 0;
	        		  min++;
	        		  
	        		  if (min == 60){
	        			  hour++;
	        			  min = 0;
	        		  }
	        	  }
	        	  //following if sentences make sure that if the numbers are less than 10 they get a extra 0 in front of them.
	        	  if (seconds.length()<2){
        			  seconds = "0" + seconds;
        		  } 
	        	  if (minutes.length()<2){
        			  minutes = "0" + minutes;
        		  }
	        	  if(hours.length() <2){
    				  hours = "0" + hours;
    			  }
	        	  gameTimer.setText(""+ hours + ":" + minutes + ":" + seconds);
	          }
	      };
	      //the timer counts in secconds. So the timePerformer actionlistener is activated every 1000 milliseconds or every second.
	      time = new Timer(1000, timePerformer);
	      time.start();
	}
	
	// a function that only stops the timer so it can reset and wont count in the next game
	public void stopTimer(){
		time.stop();
	}
	
	public void gameOver( String message){
		ImageIcon icon = new ImageIcon("Smiley.svg.png");
		
		int input = JOptionPane.showOptionDialog(null, message, "Game over!", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, icon, null, null);
		if ( input == JOptionPane.OK_OPTION ){
			view.checker.removePieces();
			view.checker.game();
		} else {
			view.checker.removePieces();
			view.menu.menu();
		}
	}
	

}