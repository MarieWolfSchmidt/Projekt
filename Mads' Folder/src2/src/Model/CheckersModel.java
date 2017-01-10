package Model;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import View.CheckersView;

public class CheckersModel {

	private CheckersView view;
	private List<Pieces> pieceList;
	private MovePieces move;
	private CheckersMusic music;
	
	private int boardSize = 800;
	private int squareDim;
	protected int finalBoardSize; // If the supplied commandLineArgument is an odd number then we change the boardSize so it matches.
	private int pieceSize;
	
	public boolean isMusicOn = false;
	
	
	public CheckersModel(CheckersView view) {
		this.view = view;
    	this.squareDim = boardSize/view.cmdLineArgument; 
    	this.finalBoardSize = squareDim*view.cmdLineArgument;
    	this.pieceSize = (squareDim/3)*2;
    	this.pieceList = new ArrayList<>();
    	this.move = new MovePieces(pieceList, squareDim, pieceSize, finalBoardSize);
	}
	
	public void game() {
    	add(1, 1, 1, squareDim, pieceList);
    	add(2, view.cmdLineArgument, view.cmdLineArgument, squareDim, pieceList);
		this.view.menu.game(view.cmdLineArgument, pieceList, boardSize, squareDim, finalBoardSize, pieceSize);
	}
	
	public void menu() {
		this.view.menu.menu();
	}
	
	public void mousePressed(MouseEvent e) {
		move.pressed(e);
	}
	
	public void mouseDragged(MouseEvent e) {
		move.mouseDragged(e, view.menu.draw);
	}
	
	public void mouseReleased(MouseEvent e) {
		move.released(e, view.menu.draw);
	}
	
	public void add(int i, int row, int col, int squareDim, List<Pieces> pieceList) {
		
		// Instantiates a piece and adds it to the pieceList. 
		
		Point p = new Point((col-1)*squareDim+squareDim/2,(row-1)*squareDim+squareDim/2); 
		Pieces piece = new Pieces(i,p);
	    pieceList.add(piece);     
	}
	
	public void removePieces() {
		
		// Removes all pieces from the pieceList when the user presses the back to menu button in the game() menu.
			this.pieceList.clear();
	}
	
	public void toggleMusic() {
		
		if (isMusicOn == false ){
			music.activateMusic();
			this.isMusicOn = true;
		} else {
			music.stopMusic();
			this.isMusicOn = false;
		}
	}
	
}
