package View;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JComponent;

import Model.Pieces;

public class Draw extends JComponent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int boardSize;
	private int squareDim;
	protected int finalBoardSize; // If the supplied commandLineArgument is an odd number then we change the boardSize so it matches.
	private int pieceSize;
	private List<Pieces> pieceList; 
	private Color squareCol;
	
	public Draw(List<Pieces> pieceList, int boardSize, int squareDim, int finalBoardSize, int pieceSize, Color squareColor){
    	this.pieceList = pieceList; 
    	this.boardSize = boardSize;
    	this.squareDim = squareDim;
    	this.finalBoardSize = finalBoardSize;
    	this.pieceSize = pieceSize; 
    	this.squareCol = squareColor;
	}
	
	
	 protected void paintComponent(Graphics g) {
		 // Override jComponent's function paintComponent to draw the board and the pieces.
	     // The method draws the board on a JPanel utilizing the structure of a coordinate system.
	     // It begins in (0,0) and draws two rows at a time to easier manipulate the colors of the board.
	     // The size of the squares are decided using the size of the board divided by the cmdlineargument. 
	        
	     for(int i = 0; i <= boardSize - squareDim; i += 2*squareDim) { 		// 2*squareDim is added to i to fill in the right color.
	        for(int j = 0; j <= boardSize - squareDim; j += 2*squareDim) {
	        	g.setColor(squareCol);
	        	g.fillRect(j, i, squareDim, squareDim);						// Gray square - first row.
	        	g.setColor(Color.WHITE);
	        	g.fillRect(j+squareDim, i, squareDim, squareDim); 			// White square - first row.
	        	g.setColor(squareCol);
	        	g.fillRect(j+squareDim, i+squareDim, squareDim, squareDim);	// Gray square - second row.
	        	g.setColor(Color.WHITE);
	        	g.fillRect(j, i+squareDim, squareDim, squareDim);			// White square - second row.
	        }
	     }
	            
	     // Draws a black rectangle to get a black square around the board. 
	     g.setColor(Color.BLACK);
	     // If the supplied cmdLineArgument is odd one will have to calculate the finalBoardSize, because of ints 
	     // way of rounding down. Otherwise one would end up with a little bit of extra board.
	     g.drawRect(0, 0, finalBoardSize, finalBoardSize); 
	            
	     // Draws a line around all the squares.
	     for (int i = 0; i < finalBoardSize; i += squareDim) {
	    	 g.drawLine(i, 0, i, finalBoardSize);
	     }
	     for (int i = 0; i < finalBoardSize; i += squareDim) {
	         g.drawLine(0, i, finalBoardSize, i);
	     }	
	            
	     // Adds pieces.
	     for(Pieces piece: pieceList) {
	    	 piece.drawPieces(g, piece.point, pieceSize);
	     }
	           	
	 }
	 
	 public void repaintBoardAndPieces() {
		 // Calls paintComponent()
		 repaint();   
	 }
	 
	 
}
