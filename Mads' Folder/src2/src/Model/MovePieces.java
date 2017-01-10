package Model;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.List;

import View.Draw;

public class MovePieces {
	private Point oldCoord;
	private int deltaX, deltaY;
	private boolean move = false;
	private boolean jump = false;
	private boolean playerOne = true;
	private boolean playerTwo = false;
	private Pieces piece;
	private List<Pieces> pieceList;
	private int squareDim;
	private int pieceSize;
	private int finalBoardSize;
	
	public MovePieces(List<Pieces> pieceList, int squareDim, int pieceSize, int finalBoardSize) {
		this.pieceList = pieceList;
		this.squareDim = squareDim;
		this.pieceSize = pieceSize;
		this.finalBoardSize = finalBoardSize;
	}
	

	public void pressed(MouseEvent e) {
		// When the mouse is pressed two checks are made - one to see if anything is in the location 
		// and one to see if it is the turn of the piece pressed. If everything checks out the 
		// piece is 'lifted'.

        for (Pieces piece: this.pieceList) {
            if (Pieces.contains(e.getX(), e.getY(), piece.point, pieceSize)) {
            	if (playerOne == true && piece.color == 1) {
            		this.piece = piece;
            		oldCoord = piece.point.getLocation();
            		deltaX = e.getX() - piece.point.x;
            		deltaY = e.getY() - piece.point.y;
            		move = true;	
                    playerOne = false;
                    playerTwo = true;
            	} else if (playerTwo == true && piece.color == 2) {
            		this.piece = piece;
            		oldCoord = piece.point.getLocation();
            		deltaX = e.getX() - piece.point.x;
            		deltaY = e.getY() - piece.point.y;
            		move = true;
                    playerOne = true;
                    playerTwo = false;
            	}
            }
        }
	}
	
	
	public void released(MouseEvent e, Draw draw) {
		
		// When the mouse is released the piece is placed. 
		
		if (move == true) {
			move = false;
		} else {
			return;
		}
        
		 int fromPointToLengthOfTwoSquare = this.squareDim*2 - (this.pieceSize/2);
		 int fromPointToEndOfOneSquareBack = this.squareDim*3 - fromPointToLengthOfTwoSquare;
		 int fromSquareStartToPoint = this.squareDim*2 - fromPointToLengthOfTwoSquare;
		 int fromPointToLengthOfOneSquare = this.squareDim - (this.pieceSize/2);  
		
		// Centers the piece in the given square in the location where the mouse is released.
		piece.point.x = (((e.getX() - deltaX) / this.squareDim) * this.squareDim) + (this.squareDim / 2);
		piece.point.y = (((e.getY() - deltaY) / this.squareDim) * this.squareDim) + (this.squareDim / 2);
       
		// Difference between the 'old' location i.e. square and the 'new' one. 
		int deltaNewXOldX = piece.point.x - oldCoord.x;
		int deltaNewYOldY = piece.point.y - oldCoord.y;
		// It's preferable to calculate the conditions with positive numbers. 
		int deltaOldXNewX = oldCoord.x - piece.point.x;
		int deltayOldYNewY = oldCoord.y - piece.point.y;
 
		// This code block holds the conditions for moving the pieces. The whole list of pieces if looped over
		// to check if the move is legal. 
		for (Pieces piece: this.pieceList) {
			if (jump == true) { 
				// First condition makes sure the piece is inside the board. 
				if (this.piece.point.x < 0 || this.piece.point.x > this.finalBoardSize || this.piece.point.y < 0 || this.piece.point.y > this.finalBoardSize) {
					this.piece.point = oldCoord;
					player();
				//The second condition makes first sure that the red piece can't move up, more than two row down and, two column to the right and that it can't move to the left.
				//than it makes sure that the pieces can't move one row down, and finally it make sure that it can't move in the same column and the first column to the right.
				} else if (this.piece.color == 1 && 
						((deltayOldYNewY > (fromSquareStartToPoint + this.squareDim) || deltaNewYOldY > (fromPointToLengthOfTwoSquare + this.squareDim) || deltaNewXOldX > (fromPointToLengthOfTwoSquare + this.squareDim) || deltaOldXNewX>fromSquareStartToPoint)
						||(deltaNewYOldY<fromPointToLengthOfTwoSquare)
						||(deltaNewXOldX<fromPointToLengthOfTwoSquare))){	
					this.piece.point = oldCoord; 
					player();
					//The third condition makes first sure that the black piece can't move down, more than two row up and two column to the left and that it can't move to the right.
					//than it makes sure that the pieces can't move one row up, and finally it make sure that it can't move in the same column and the first column to the left.	
				} else if (this.piece.color == 2 && 
						((deltayOldYNewY > (fromPointToEndOfOneSquareBack + this.squareDim) || deltaNewYOldY > (fromPointToLengthOfOneSquare + this.squareDim) || deltaNewXOldX > (fromPointToEndOfOneSquareBack + this.squareDim) || deltaOldXNewX > (fromPointToLengthOfTwoSquare + this.squareDim))
						||(deltayOldYNewY<fromPointToLengthOfTwoSquare)
						||(deltaOldXNewX<fromPointToLengthOfTwoSquare))) {
					this.piece.point = oldCoord;
					player();
				} else if (this.piece.color == 1) {   
					pieceList.remove(piece.color); 
				} else if (this.piece.color == 2) {	
					pieceList.remove(piece);
				}
				jump = false;
		
			} else {
				//The first condition makes sure that the piece stays inside the board.
				if (this.piece.point.x < 0 || this.piece.point.x > this.finalBoardSize ||this.piece.point.y < 0 || this.piece.point.y > this.finalBoardSize) {
    	    		this.piece.point = oldCoord;
    	    		player();
    	    	//The second condition makes first sure that the red piece can't move up, more than one row down and a column for each side.
    	    	// then it makes sure that the piece can't move straight to the side, and finally it makes sure that the pieces don't go straight down. 
    	    	} else if (this.piece.color == 1 && 
    	    			 ((deltayOldYNewY > fromSquareStartToPoint || deltaNewYOldY>fromPointToLengthOfTwoSquare || deltaNewXOldX>fromPointToLengthOfTwoSquare || deltaOldXNewX>fromPointToEndOfOneSquareBack)
    	    			 || (this.piece.point.y==oldCoord.y && (deltaOldXNewX > fromSquareStartToPoint || deltaNewXOldX>fromPointToLengthOfOneSquare))
    	    			 || (this.piece.point.x==oldCoord.x))) {
    	    		this.piece.point = oldCoord;
    	    		player();
    	    	//The third condition makes first sure that the black piece can't move down and more than one row up,
        	    // then it makes sure that the piece can't move straight to the side, and finally it makes sure that the pieces don't go straight down. 
    	    	} else if (this.piece.color == 2 && 
    	    			((deltayOldYNewY > fromPointToEndOfOneSquareBack || deltaNewYOldY > fromPointToLengthOfOneSquare || deltaNewXOldX>fromPointToEndOfOneSquareBack || deltaOldXNewX > fromPointToLengthOfTwoSquare)
    	    			||(this.piece.point.y==oldCoord.y && (deltaOldXNewX>fromSquareStartToPoint || deltaNewXOldX>fromPointToLengthOfOneSquare))
    	    			|| (this.piece.point.x==oldCoord.x))) {
    	    		this.piece.point = oldCoord; 
    	    		player();	
    	    	}
    	    	
    	    }
			draw.repaintBoardAndPieces();
			searchBoard();
    	    return;  
        }
	}
	
	private void searchBoard() {
		
		// Sets jump == true if the opponents piece is ready to get slain. Loops the whole pieceList to see if 
		// any of the opponents pieces is in the correct position for a jump over it. 
		
		for (Pieces piece: pieceList) {
			if (this.piece.color == 1 && ((this.piece.point.x + squareDim) == piece.point.x && (this.piece.point.y + squareDim) == piece.point.y)){
				jump = true;
				break;
			} else if (this.piece.color == 2 && ((this.piece.point.x - squareDim) == piece.point.x && (this.piece.point.y - squareDim) == piece.point.y)){
				jump = true;
				break;
			} else {
				jump= false;
			}	
		}
	}
	
	public void mouseDragged(MouseEvent e, Draw draw) {
		
		// Allows the piece to follow the mouse when clicked. 
	
		if (move) {
            piece.point = e.getPoint(); // Update location of piece center.
            draw.repaintBoardAndPieces();
		}
}
	
	private void player() {
		
		// Sets the player booleans true or false according to who's turn it is. 
		
		if (playerOne == true) {
			playerOne=false;
			playerTwo=true;
		} else if (playerTwo == true) {
			playerOne=true;
			playerTwo=false;	
		}
	}
	
}
