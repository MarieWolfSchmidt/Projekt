package Model;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import View.Draw;
import View.Menus;

public class MovePieces {
	private Point oldCoord;
	private int deltaX, deltaY;
	private boolean move = false;
	private boolean jumpRedRBlackL = false;
	private boolean jumpRedLBlackR = false;
	private boolean playerOne = true;
	private boolean playerTwo = false;
	private boolean moveAnyPiece =true;
	private Pieces piece;
	private Pieces removePiece;
	private Pieces saveRemovePiece;
	private List<Pieces> pieceList;
	private List<Pieces> jumpListRedRBlackL;
	private List<Pieces> jumpListRedLBlackR;
	private int squareDim;
	private int pieceSize;
	private int finalBoardSize;	
	
	
	public MovePieces(List<Pieces> pieceList, int squareDim, int pieceSize, int finalBoardSize) {
		this.pieceList = pieceList;
		this.jumpListRedRBlackL = new ArrayList<>();
		this.jumpListRedLBlackR = new ArrayList<>();
		this.squareDim = squareDim;
		this.pieceSize = pieceSize;
		this.finalBoardSize = finalBoardSize;
	}
	

	public void pressed(MouseEvent e) {
		// When the mouse is pressed three checks are made - one to see if anything is in the location, 
		// one to see if it is the turn of the piece pressed, and one to check for jump-piece. If everything checks out the 
		// piece is 'lifted'.

        for (Pieces piece: this.pieceList) {
            if (Pieces.contains(e.getX(), e.getY(), piece.point, pieceSize)) {
            	if (playerOne == true && piece.color == 1) {
            		if(jumpListRedRBlackL.contains(piece)){
            			jumpRedRBlackL = true;
            			this.piece = piece;
            			oldCoord = piece.point.getLocation();
            			deltaX = e.getX() - piece.point.x;
            			deltaY = e.getY() - piece.point.y;
            			move = true;	
            			playerOne = false;
            			playerTwo = true;
            		}else if(jumpListRedLBlackR.contains(piece)){
            			jumpRedLBlackR = true;
            			this.piece = piece;
            			oldCoord = piece.point.getLocation();
            			deltaX = e.getX() - piece.point.x;
            			deltaY = e.getY() - piece.point.y;
            			move = true;	
            			playerOne = false;
            			playerTwo = true;
            		}else if(moveAnyPiece == true){
            			this.piece = piece;
            			oldCoord = piece.point.getLocation();
            			deltaX = e.getX() - piece.point.x;
            			deltaY = e.getY() - piece.point.y;
            			move = true;	
            			playerOne = false;
            			playerTwo = true;
            		}
            	} else if (playerTwo == true && piece.color == 2) {
            		if(jumpListRedRBlackL.contains(piece)){
            			jumpRedRBlackL = true;
            			this.piece = piece;
            			oldCoord = piece.point.getLocation();
            			deltaX = e.getX() - piece.point.x;
            			deltaY = e.getY() - piece.point.y;
            			move = true;
            			playerOne = true;
            			playerTwo = false;
            		}else if(jumpListRedLBlackR.contains(piece)){
            			jumpRedLBlackR = true;
            			this.piece = piece;
            			oldCoord = piece.point.getLocation();
            			deltaX = e.getX() - piece.point.x;
            			deltaY = e.getY() - piece.point.y;
            			move = true;
            			playerOne = true;
            			playerTwo = false;
            		}else if(moveAnyPiece == true){
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
        
	}
	
	
	public void released(MouseEvent e, Draw draw, Menus menu) {
		
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
		this.piece.point.x = (((e.getX() - deltaX) / this.squareDim) * this.squareDim) + (this.squareDim / 2);
		this.piece.point.y = (((e.getY() - deltaY) / this.squareDim) * this.squareDim) + (this.squareDim / 2);
       
		// Difference between the 'old' location i.e. square and the 'new' one. 
		int deltaNewXOldX = this.piece.point.x - oldCoord.x;
		int deltaNewYOldY = this.piece.point.y - oldCoord.y;
		// It's preferable to calculate the conditions with positive numbers. 
		int deltaOldXNewX = this.oldCoord.x - piece.point.x;
		int deltayOldYNewY = this.oldCoord.y - piece.point.y;
 
		// This code block holds the conditions for moving the pieces. 
		
		
		//old code starts here
		if (jumpRedRBlackL == true) { 
				// First condition makes sure the piece is inside the board. 
				if (this.piece.point.x < 0 || this.piece.point.x > this.finalBoardSize || this.piece.point.y < 0 || this.piece.point.y > this.finalBoardSize) {
					this.piece.point = oldCoord;
					jumpRedRBlackL = true;	
					player();
				//The second condition makes first sure that the red piece can't move up, more than two row down and, two column to the right and that it can't move to the left.
				//than it makes sure that the pieces can't move one row down the same row, and finally it make sure that it can't move in the same column and the first column to the right.
				} else if (this.piece.color == 1 && 
						((deltayOldYNewY > (fromSquareStartToPoint + this.squareDim) || deltaNewYOldY > (fromPointToLengthOfTwoSquare + this.squareDim) || deltaNewXOldX > (fromPointToLengthOfTwoSquare + this.squareDim) || deltaOldXNewX>fromSquareStartToPoint)
						||(deltaNewYOldY<fromPointToLengthOfTwoSquare)
						||(deltaNewXOldX<fromPointToLengthOfTwoSquare))){	
					this.piece.point = oldCoord;
					jumpRedRBlackL = true;
					player();
					//The third condition makes first sure that the black piece can't move down, more than two row up and two column to the left and that it can't move to the right.
					//than it makes sure that the pieces can't move one row up or in the same row, and finally it make sure that it can't move in the same column and the first column to the left.	
				} else if (this.piece.color == 2 && 
						((deltayOldYNewY > (fromPointToEndOfOneSquareBack + this.squareDim) || deltaNewYOldY > (fromPointToLengthOfOneSquare + this.squareDim) || deltaNewXOldX > (fromPointToEndOfOneSquareBack + this.squareDim) || deltaOldXNewX > (fromPointToLengthOfTwoSquare + this.squareDim))
						||(deltayOldYNewY<fromPointToLengthOfTwoSquare)
						||(deltaOldXNewX<fromPointToLengthOfTwoSquare))) {
					this.piece.point = oldCoord;
					jumpRedRBlackL = true;
					player();
				} else if (this.piece.color == 1) {   
					pieceList.remove(removePiece);
					playerTwo = true;
					searchBoard();
				} else if (this.piece.color == 2) {	
					pieceList.remove(removePiece);
					playerOne = true;
					searchBoard();

				}
				
			}else if(jumpRedLBlackR == true){
				// First condition makes sure the piece is inside the board. 
				if (this.piece.point.x < 0 || this.piece.point.x > this.finalBoardSize || this.piece.point.y < 0 || this.piece.point.y > this.finalBoardSize) {
					this.piece.point = oldCoord;
					jumpRedLBlackR=true;
					player();
				//The second condition makes first sure that the red piece can't move up, more than two row down and, two column to the left and that it can't move to the right.
				//than it makes sure that the pieces can't move one row down or in the same row, and finally it make sure that it can't move in the same column and the first column to the left.
				} else if (this.piece.color == 1 && 
						((deltayOldYNewY > (fromSquareStartToPoint) || deltaNewYOldY > (fromPointToLengthOfTwoSquare + this.squareDim) || deltaOldXNewX>(fromPointToEndOfOneSquareBack+this.squareDim) || deltaNewXOldX > fromPointToLengthOfOneSquare)
						||(deltaNewYOldY<fromPointToLengthOfTwoSquare)
						||(deltaOldXNewX<fromPointToEndOfOneSquareBack))){	
					this.piece.point = oldCoord;
					jumpRedLBlackR=true;
					player();
				//The third condition makes first sure that the black piece can't move down, more than two row up and two column to the left and that it can't move to the right.
				//than it makes sure that the pieces can't move one row up or in the same row, and finally it make sure that it can't move in the same column and the first column to the left.	
				} else if (this.piece.color == 2 && 
						((deltayOldYNewY > (fromPointToEndOfOneSquareBack+this.squareDim) || deltaNewYOldY > (fromPointToLengthOfOneSquare) || deltaNewXOldX > (fromPointToLengthOfTwoSquare + this.squareDim) || deltaOldXNewX > (fromSquareStartToPoint))
						||(deltayOldYNewY<fromPointToLengthOfOneSquare+this.squareDim)
						||(deltaNewXOldX<fromPointToLengthOfTwoSquare))) {
					this.piece.point = oldCoord;
					jumpRedLBlackR=true;
					player();				
				} else if (this.piece.color == 1) {   
					pieceList.remove(removePiece);
					playerTwo = true;
					searchBoard();
				} else if (this.piece.color == 2) {	
					pieceList.remove(removePiece);
					playerOne = true;
					searchBoard();
				}
			}
			
			else {
				//The first condition makes sure that the piece can't move on top of another pieces.
				for (Pieces piece: pieceList) {
					if(this.piece != piece && this.piece.point.x==piece.point.x && this.piece.point.y==piece.point.y){
						this.piece.point = oldCoord;
					}
				}
				//The second condition makes sure that the piece stays inside the board.
				 if (this.piece.point.x < 0 || this.piece.point.x > this.finalBoardSize ||this.piece.point.y < 0 || this.piece.point.y > this.finalBoardSize) {
    	    		this.piece.point = oldCoord;
    	    		player();
    	    	//The third condition makes first sure that the red piece can't move up, more than one row down and a column for each side.
    	    	// then it makes sure that the piece can't move straight to the side, and finally it makes sure that the pieces don't go straight down. 
    	    	} else if (this.piece.color == 1 && 
    	    			 ((deltayOldYNewY > fromSquareStartToPoint || deltaNewYOldY>fromPointToLengthOfTwoSquare || deltaNewXOldX>fromPointToLengthOfTwoSquare || deltaOldXNewX>fromPointToEndOfOneSquareBack)
    	    			 || (this.piece.point.y==oldCoord.y && (deltaOldXNewX > fromSquareStartToPoint || deltaNewXOldX>fromPointToLengthOfOneSquare))
    	    			 || (this.piece.point.x==oldCoord.x))) {
    	    		this.piece.point = oldCoord;
    	    		player();
    	    	//The forth condition makes first sure that the black piece can't move down and more than one row up,
        	    // then it makes sure that the piece can't move straight to the side, and finally it makes sure that the pieces don't go straight down. 
    	    	} else if (this.piece.color == 2 && 
    	    			((deltayOldYNewY > fromPointToEndOfOneSquareBack || deltaNewYOldY > fromPointToLengthOfOneSquare || deltaNewXOldX>fromPointToEndOfOneSquareBack || deltaOldXNewX > fromPointToLengthOfTwoSquare)
    	    			||(this.piece.point.y==oldCoord.y && (deltaOldXNewX>fromSquareStartToPoint || deltaNewXOldX>fromPointToLengthOfOneSquare))
    	    			|| (this.piece.point.x==oldCoord.x))) {
    	    		this.piece.point = oldCoord; 
    	    		player();	
    	    	}
					searchBoard();

			}
			draw.repaintBoardAndPieces();
    	    return;  
	}
	
	private void searchBoard() {
		this.jumpListRedRBlackL.clear();
		this.jumpListRedLBlackR.clear();
		moveAnyPiece = true;
		jumpRedRBlackL = false;
		jumpRedLBlackR = false;
		// Sets jumpRed..Black.. == true if the opponents piece is ready to get slain. Loops the whole pieceList to see if 
		// any of the opponents pieces is in the correct position for a jump over it. 
		for (Pieces pieceBlack: this.pieceList) {
			for(Pieces pieceRed: this.pieceList){
				//This one check's if the a red piece can jump over a black piece to the right, and if a black piece can jump left over a red piece.
				if (pieceBlack.color == 2 && pieceRed.color==1 
				&& ((pieceBlack.point.x - squareDim) == pieceRed.point.x && (pieceBlack.point.y - squareDim) == pieceRed.point.y)){
						if(playerOne == true){
							for(Pieces piece: this.pieceList){
								//Makes sure at red piece can't jump right over a black piece if there is another piece behind it. 
								if(pieceBlack.point.x == (piece.point.x - squareDim) && pieceBlack.point.y == (piece.point.y - squareDim)){
									moveAnyPiece = true;
									jumpListRedRBlackL.clear();
									break;
								//If the isn't anything behind the black piece the red piece are going to slain that the next turn. 
								} else{
									jumpListRedRBlackL.add(pieceRed);
									removePiece=pieceBlack;
									saveRemovePiece = removePiece;
									moveAnyPiece = false;
								}
							}
						} else if (playerTwo == true){
							for(Pieces piece: this.pieceList){
								//Makes sure at black piece can't jump left over a black piece if there is another piece behind it. 
								if(pieceRed.point.x == (piece.point.x + squareDim) && pieceRed.point.y == (piece.point.y + squareDim)){
									moveAnyPiece = true;
									jumpListRedRBlackL.clear();
									System.out.println("BLACK L");
									break;
								//If the isn't anything behind the red piece the black piece are going to slain that the next turn. 
							} else{
							System.out.println("HALLO!");
							jumpListRedRBlackL.add(pieceBlack);
							removePiece=pieceRed;
							saveRemovePiece = removePiece;
							moveAnyPiece = false;
							}
						}
					}
				}
			}
		}
		
		for (Pieces pieceRed: this.pieceList) {
			for(Pieces pieceBlack: this.pieceList){
				//This one check's if the a red piece can jump over a black piece to the left, and if a black piece can jump right over a red piece.
			 	if (pieceRed.color == 1 && pieceBlack.color==2 
				&& ((pieceRed.point.x - squareDim) == pieceBlack.point.x && (pieceRed.point.y + squareDim) == pieceBlack.point.y)){
			 			if(playerOne == true){
			 				for(Pieces piece: this.pieceList){
								//Makes sure at red piece can't jump right over a black piece if there is another piece behind it. 
			 					if(pieceBlack.point.x == (piece.point.x + squareDim) && pieceBlack.point.y == (piece.point.y - squareDim)){
			 						if(jumpListRedRBlackL.size()>0){
			 							moveAnyPiece =false;
			 							removePiece =saveRemovePiece;
			 						} else {
			 						moveAnyPiece = true;
			 						}
									jumpListRedLBlackR.clear();
									break;
								//If the isn't anything behind the black piece the red piece are going to slain that the next turn. 
								} else{
									System.out.println("RedL");
									jumpListRedLBlackR.add(pieceRed);
									removePiece=pieceBlack;
									moveAnyPiece = false;
									}
			 					}
						} else if (playerTwo == true){
							for(Pieces piece: this.pieceList){
								//Makes sure at red piece can't jump left over a black piece if there is another piece behind it. 
			 					if(pieceRed.point.x == (piece.point.x - squareDim) && pieceRed.point.y == (piece.point.y + squareDim)){
			 						if(jumpListRedRBlackL.size()>0){
			 						moveAnyPiece = false;
			 						removePiece = saveRemovePiece;
			 						}else {
			 						moveAnyPiece = true;
			 						}
									jumpListRedLBlackR.clear();
									System.out.println("Black R FALSE");
									break;
								//If the isn't anything behind the black piece the red piece are going to slain that the next turn. 
								} else{
									jumpListRedLBlackR.add(pieceBlack);
									System.out.println("Black R");
									removePiece=pieceRed;
									moveAnyPiece = false;
									}
			 					}
			 				}
						}
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
