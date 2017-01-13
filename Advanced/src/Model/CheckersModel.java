package Model;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import View.CheckersView;

public class CheckersModel {

	private CheckersView view;
	private List<Pieces> pieceList;
	private MovePieces move;
	
		
	private int boardSize = 800;
	private int squareDim;
	protected int finalBoardSize; // If the supplied commandLineArgument is an odd number then we change the boardSize so it matches.
	private int pieceSize;
	private int rowsWithPieces;
	
	public boolean isMusicOn = false;
	Clip soundtrack;
	
	private Color p1color = Color.RED;
	private Color p2color = Color.BLACK;
	private Color squareColor = Color.GRAY;
	
	public CheckersModel(CheckersView view) {
		this.view = view;
    	this.squareDim = boardSize/view.cmdLineArgument; 
    	this.finalBoardSize = squareDim*view.cmdLineArgument;
    	this.pieceSize = (squareDim/3)*2;
    	this.pieceList = new ArrayList<>();
    	this.move = new MovePieces(pieceList, squareDim, pieceSize, finalBoardSize);
    	this.rowsWithPieces = view.cmdLineArgument / 2 - 1;    	
	}
	
	public void game() {
		//Makes sure that there are only 3 rows with pieces per team
		if(rowsWithPieces > 3) {
			rowsWithPieces = 3;
		} else if (rowsWithPieces <= 0){
			rowsWithPieces = 1;
		}
		//Adds the pieces, one red row and one black row each time.
		for (int i = 0; i < rowsWithPieces; i++){
			//To make sure the pieces are only placed on the gray squares.
			int nextColumRed, nextColumBlack;
			if (i%2 == 0){
				nextColumRed = 1;
				nextColumBlack = 0;
			} else {
				nextColumRed = 2;
				nextColumBlack = 1;
			}
			for (int j = 0; j < view.cmdLineArgument; j += 2){
				add(1, i + 1, j + nextColumRed, squareDim, pieceList);
				add(2, view.cmdLineArgument-i, view.cmdLineArgument - j - nextColumBlack, squareDim, pieceList);
			}
		}
		this.view.menu.game(view.cmdLineArgument, pieceList, boardSize, squareDim, finalBoardSize, pieceSize, squareColor);
//    	add(1, 1, 1, squareDim, pieceList);
//    	add(2, view.cmdLineArgument, view.cmdLineArgument, squareDim, pieceList);
//		this.view.menu.game(view.cmdLineArgument, pieceList, boardSize, squareDim, finalBoardSize, pieceSize, squareColor);
	}
	
	public void menu() {
		this.view.menu.menu();
	}
	
	public void settings() {
		this.view.menu.settings();
	}
	
	public void highScore() {
		this.view.menu.highScore();
	}
	
	public void rules() {
		this.view.menu.rules();
	}
	
	public void howTo() {
		this.view.menu.howTo();
	}
	
	public void mousePressed(MouseEvent e) {
		move.pressed(e);
	}
	
	public void mouseDragged(MouseEvent e) {
		move.mouseDragged(e, view.menu.draw);
	}
	
	public void mouseReleased(MouseEvent e) {
		move.released(e, view.menu.draw, view.menu);
	}
	
	public void add(int i, int row, int col, int squareDim, List<Pieces> pieceList) {
		
		// Instantiates a piece and adds it to the pieceList. 
		
		Point p = new Point((col-1)*squareDim+squareDim/2,(row-1)*squareDim+squareDim/2); 
		Pieces piece = new Pieces(i,p, p1color, p2color);
	    pieceList.add(piece);     
	}
	
	public void removePieces() {
		
		// Removes all pieces from the pieceList when the user presses the back to menu button in the game() menu.
			this.pieceList.clear();
	}
	
	public void toggleMusic() {

		if (isMusicOn == false ){
			activateMusic();
			this.isMusicOn = true;
		} else {
			stopMusic();
			this.isMusicOn = false;
		}
		
	}
	
	public void activateMusic(){
		try {
			 InputStream in = new FileInputStream("eye.wav");
			 InputStream bufferedIn = new BufferedInputStream(in);
			 soundtrack = AudioSystem.getClip();
			 AudioInputStream ais = AudioSystem.getAudioInputStream(bufferedIn);
			 
			 soundtrack.open(ais);
			 soundtrack.loop(Clip.LOOP_CONTINUOUSLY);
			 

		 
		 } catch (Exception ex){
			 System.out.println("Sound file not found");
			 
		 }
	}
	
	public void stopMusic(){
		soundtrack.stop();
	}
	
	public void setTheme(String theme){
		if ( theme == "Default theme"){
			this.p1color = Color.RED;
			this.p2color = Color.BLACK;
			this.squareColor = Color.GRAY;
					
		} else if ( theme == "Pink theme"){
			this.p1color = Color.MAGENTA;
			this.p2color = Color.GREEN;
			this.squareColor = Color.PINK;
		}
	}
	
	public void turnReset(){
		move.resetTurn();
	}
	
	
	
}
