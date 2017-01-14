package Model;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import View.DrawOnBoardPanel;
import View.Menus;

public class MovePieces {
	private Menus menu;
	private Point oldCoord;
	private int deltaX, deltaY;
	private boolean move = false;
	private boolean jumpRedRBlackL = false;
	private boolean jumpRedLBlackR = false;
	public boolean playerOne = true;
	private boolean playerTwo = false;
	private boolean moveAnyPiece =true;
	private Pieces piece;
	private Pieces jumpingPiece;
	private Pieces removePiece;
	private List<Pieces> removeList;
	private List<Pieces> pieceList;
	private List<Pieces> jumpListRedRBlackL;
	private List<Pieces> jumpListRedLBlackR;
	private int squareDim;
	private int pieceSize;
	private int finalBoardSize;	
	private int playerOnePieceCount, playerTwoPieceCount;
	private Color pOneColor, pTwoColor;
	
	public MovePieces(List<Pieces> pieceList, int squareDim, int pieceSize, int finalBoardSize, Color playerOne, Color playerTwo) {
		this.pieceList = pieceList;
		this.jumpListRedRBlackL = new ArrayList<>();
		this.jumpListRedLBlackR = new ArrayList<>();
		this.squareDim = squareDim;
		this.pieceSize = pieceSize;
		this.finalBoardSize = finalBoardSize;
		this.pOneColor = playerOne;
		this.pTwoColor = playerTwo;
		this.removeList = new ArrayList<>();
	}
	

	public void pressed(MouseEvent e) {
		// When the mouse is pressed three checks are made - one to see if anything is in the location, 
		// one to see if it is the turn of the piece pressed, and one to check for jump-piece. If everything checks out the 
		// piece is 'lifted'.

        for (Pieces piece: this.pieceList) {
            if (Pieces.contains(e.getX(), e.getY(), piece.getPoint(), pieceSize)) {
            	if (playerOne == true && piece.getColor().equals(this.pOneColor)) {
            		if(jumpListRedRBlackL.contains(piece)){
            			jumpRedRBlackL = true;
            			this.piece = piece;
            			oldCoord = piece.getPoint().getLocation();
            			deltaX = e.getX() - piece.getPoint().x;
            			deltaY = e.getY() - piece.getPoint().y;
            			move = true;	
            			playerOne = false;
            			playerTwo = true;
            		}else if(jumpListRedLBlackR.contains(piece)){
            			jumpRedLBlackR = true;
            			this.piece = piece;
            			oldCoord = piece.getPoint().getLocation();
            			deltaX = e.getX() - piece.getPoint().x;
            			deltaY = e.getY() - piece.getPoint().y;
            			move = true;	
            			playerOne = false;
            			playerTwo = true;
            		}else if(moveAnyPiece == true){
            			this.piece = piece;
            			oldCoord = piece.getPoint().getLocation();
            			deltaX = e.getX() - piece.getPoint().x;
            			deltaY = e.getY() - piece.getPoint().y;
            			move = true;	
            			playerOne = false;
            			playerTwo = true;
            		}
            	} else if (playerTwo == true && piece.getColor().equals(this.pTwoColor)) {
            		if(jumpListRedRBlackL.contains(piece)){
            			jumpRedRBlackL = true;
            			this.piece = piece;
            			oldCoord = piece.getPoint().getLocation();
            			deltaX = e.getX() - piece.getPoint().x;
            			deltaY = e.getY() - piece.getPoint().y;
            			move = true;
            			playerOne = true;
            			playerTwo = false;
            		}else if(jumpListRedLBlackR.contains(piece)){
            			jumpRedLBlackR = true;
            			this.piece = piece;
            			oldCoord = piece.getPoint().getLocation();
            			deltaX = e.getX() - piece.getPoint().x;
            			deltaY = e.getY() - piece.getPoint().y;
            			move = true;
            			playerOne = true;
            			playerTwo = false;
            		}else if(moveAnyPiece == true){
            			this.piece = piece;
            			oldCoord = piece.getPoint().getLocation();
            			deltaX = e.getX() - piece.getPoint().x;
            			deltaY = e.getY() - piece.getPoint().y;
            			move = true;
            			playerOne = true;
            			playerTwo = false;
            		}
            	}
            }
        }
        
	}
	
	
	public void released(MouseEvent e, DrawOnBoardPanel draw, Menus menu) {
		this.menu = menu;
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
		this.piece.getPoint().x = (((e.getX() - deltaX) / this.squareDim) * this.squareDim) + (this.squareDim / 2);
		this.piece.getPoint().y = (((e.getY() - deltaY) / this.squareDim) * this.squareDim) + (this.squareDim / 2);
       
		jumpingPiece = this.piece;
		// Difference between the 'old' location i.e. square and the 'new' one. 
		int deltaNewXOldX = this.piece.getPoint().x - oldCoord.x;
		int deltaNewYOldY = this.piece.getPoint().y - oldCoord.y;
		// It's preferable to calculate the conditions with positive numbers. 
		int deltaOldXNewX = this.oldCoord.x - piece.getPoint().x;
		int deltayOldYNewY = this.oldCoord.y - piece.getPoint().y;
 
		// This code block holds the conditions for moving the pieces. 
		
		
		
		if (jumpRedRBlackL == true) { 
				// First condition makes sure the piece is inside the board. 
				if (this.piece.getPoint().x < 0 || this.piece.getPoint().x > this.finalBoardSize || this.piece.getPoint().y < 0 || this.piece.getPoint().y > this.finalBoardSize) {
					this.piece.setPoint(oldCoord);
					jumpRedRBlackL = false;	
					player();
				//The second condition makes first sure that the red piece can't move up, more than two row down and, two column to the right and that it can't move to the left.
				//than it makes sure that the pieces can't move one row down the same row, and finally it make sure that it can't move in the same column and the first column to the right.
				} else if (this.piece.getColor().equals(pOneColor) && 
						((deltayOldYNewY > (fromSquareStartToPoint + this.squareDim) || deltaNewYOldY > (fromPointToLengthOfTwoSquare + this.squareDim) || deltaNewXOldX > (fromPointToLengthOfTwoSquare + this.squareDim) || deltaOldXNewX>fromSquareStartToPoint)
						||(deltaNewYOldY<fromPointToLengthOfTwoSquare)
						||(deltaNewXOldX<fromPointToLengthOfTwoSquare))){	
					this.piece.setPoint(oldCoord);
					jumpRedRBlackL = false;
					player();
					//The third condition makes first sure that the black piece can't move down, more than two row up and two column to the left and that it can't move to the right.
					//than it makes sure that the pieces can't move one row up or in the same row, and finally it make sure that it can't move in the same column and the first column to the left.	
				} else if (this.piece.getColor().equals(pTwoColor) && 
						((deltayOldYNewY > (fromPointToEndOfOneSquareBack + this.squareDim) || deltaNewYOldY > (fromPointToLengthOfOneSquare + this.squareDim) || deltaNewXOldX > (fromPointToEndOfOneSquareBack + this.squareDim) || deltaOldXNewX > (fromPointToLengthOfTwoSquare + this.squareDim))
						||(deltayOldYNewY<fromPointToLengthOfTwoSquare)
						||(deltaOldXNewX<fromPointToLengthOfTwoSquare))) {
					this.piece.setPoint(oldCoord);
					jumpRedRBlackL = false;
					player();
				} else if (this.piece.getColor().equals(pOneColor)) {  
					for (Pieces removePiece : removeList){
						if ( removePiece.getPoint().x + squareDim == this.piece.getPoint().x && removePiece.getPoint().y + squareDim == this.piece.getPoint().y){
							this.removePiece = removePiece;
						}
					}
					pieceList.remove(this.removePiece);
					pieceCount();
					jumpAgain();
					
				} else if (this.piece.getColor().equals(pTwoColor)) {	
					for (Pieces removePiece : removeList){
						if ( removePiece.getPoint().x - squareDim == this.piece.getPoint().x && removePiece.getPoint().y - squareDim == this.piece.getPoint().y){
							this.removePiece = removePiece;
						}
					}
					pieceList.remove(this.removePiece);
					pieceCount();
					jumpAgain();
					
				}
				
			}else if(jumpRedLBlackR == true){
				// First condition makes sure the piece is inside the board. 
				if (this.piece.getPoint().x < 0 || this.piece.getPoint().x > this.finalBoardSize || this.piece.getPoint().y < 0 || this.piece.getPoint().y > this.finalBoardSize) {
					this.piece.setPoint(oldCoord);
					jumpRedLBlackR = false;
					player();
				//The second condition makes first sure that the red piece can't move up, more than two row down and, two column to the left and that it can't move to the right.
				//than it makes sure that the pieces can't move one row down or in the same row, and finally it make sure that it can't move in the same column and the first column to the left.
				} else if (this.piece.getColor().equals(pOneColor) && 
						((deltayOldYNewY > (fromSquareStartToPoint) || deltaNewYOldY > (fromPointToLengthOfTwoSquare + this.squareDim) || deltaOldXNewX>(fromPointToEndOfOneSquareBack+this.squareDim) || deltaNewXOldX > fromPointToLengthOfOneSquare)
						||(deltaNewYOldY<fromPointToLengthOfTwoSquare)
						||(deltaOldXNewX<fromPointToEndOfOneSquareBack))){	
					this.piece.setPoint(oldCoord);
					jumpRedLBlackR = false;
					player();
				//The third condition makes first sure that the black piece can't move down, more than two row up and two column to the left and that it can't move to the right.
				//than it makes sure that the pieces can't move one row up or in the same row, and finally it make sure that it can't move in the same column and the first column to the left.	
				} else if (this.piece.getColor().equals(pTwoColor) && 
						((deltayOldYNewY > (fromPointToEndOfOneSquareBack+this.squareDim) || deltaNewYOldY > (fromPointToLengthOfOneSquare) || deltaNewXOldX > (fromPointToLengthOfTwoSquare + this.squareDim) || deltaOldXNewX > (fromSquareStartToPoint))
						||(deltayOldYNewY<fromPointToLengthOfOneSquare+this.squareDim)
						||(deltaNewXOldX<fromPointToLengthOfTwoSquare))) {
					this.piece.setPoint(oldCoord);
					jumpRedLBlackR = false;
					player();				
				} else if (this.piece.getColor().equals(pOneColor)) {  
					for (Pieces removePiece : removeList){
						if ( removePiece.getPoint().x - squareDim == this.piece.getPoint().x && removePiece.getPoint().y + squareDim == this.piece.getPoint().y){
							this.removePiece = removePiece;
						}
					}
					pieceList.remove(this.removePiece);
					pieceCount();
					jumpAgain();
				} else if (this.piece.getColor().equals(pTwoColor)) {
					for (Pieces removePiece : removeList){
						if ( removePiece.getPoint().x + squareDim == this.piece.getPoint().x && removePiece.getPoint().y - squareDim == this.piece.getPoint().y){
							this.removePiece = removePiece;
						}
					}
					pieceList.remove(this.removePiece);
					pieceCount();
					jumpAgain();
				}
			} else if (this.piece.isKing == true){
				
				for (Pieces piece: pieceList) {
					if(this.piece != piece && this.piece.getPoint().x==piece.getPoint().x && this.piece.getPoint().y==piece.getPoint().y){
						this.piece.setPoint(oldCoord);
					}
				}
	
				if (this.piece.getPoint().x < 0 || this.piece.getPoint().x  > this.finalBoardSize || this.piece.getPoint().y < 0 || this.piece.getPoint().y > this.finalBoardSize) {
					this.piece.setPoint(oldCoord);
					jumpRedRBlackL = false;	
					player();	   
				}else if (this.piece.getColor().equals(pOneColor) && 
		    			((deltayOldYNewY > fromPointToEndOfOneSquareBack || deltaNewYOldY > fromPointToLengthOfTwoSquare 
		    			|| deltaNewXOldX>fromPointToEndOfOneSquareBack || deltaOldXNewX > fromPointToLengthOfTwoSquare)
	        	    	|| (this.piece.getPoint().y==oldCoord.y && (deltaOldXNewX>fromSquareStartToPoint || deltaNewXOldX>fromPointToLengthOfOneSquare))
	        	    	|| (this.piece.getPoint().x==oldCoord.x))){
		    		
					this.piece.setPoint(oldCoord);
		    		player();
		    		
		    		// Uses the conditions from the regular red piece to make the red piece move in the other direction.
		    	}else if (this.piece.getColor().equals(pTwoColor)
		    			&& 
		    			((deltayOldYNewY > fromPointToLengthOfTwoSquare || deltaNewYOldY>fromPointToLengthOfTwoSquare 
		    			|| deltaNewXOldX>fromPointToLengthOfTwoSquare || deltaOldXNewX>fromPointToEndOfOneSquareBack)
	       	    		|| (this.piece.getPoint().y==oldCoord.y && (deltaOldXNewX > fromSquareStartToPoint || deltaNewXOldX>fromPointToLengthOfOneSquare))
	       	    		|| (this.piece.getPoint().x==oldCoord.x))){
		    		
		    		this.piece.setPoint(oldCoord);
	   	    		player();
		    	}
				searchBoard();
				
			}
			
			else {
				//The first condition makes sure that the piece can't move on top of another pieces.
				for (Pieces piece: pieceList) {
					if(this.piece != piece && this.piece.getPoint().x==piece.getPoint().x && this.piece.getPoint().y==piece.getPoint().y){
						this.piece.setPoint(oldCoord);
					}
				}
				//The second condition makes sure that the piece stays inside the board.
				 if (this.piece.getPoint().x < 0 || this.piece.getPoint().x > this.finalBoardSize ||this.piece.getPoint().y < 0 || this.piece.getPoint().y > this.finalBoardSize) {
    	    		this.piece.setPoint(oldCoord);
    	    		player();
    	    	//The third condition makes first sure that the red piece can't move up, more than one row down and a column for each side.
    	    	// then it makes sure that the piece can't move straight to the side, and finally it makes sure that the pieces don't go straight down. 
    	    	} else if (this.piece.getColor().equals(pOneColor) && 
    	    			 ((deltayOldYNewY > fromSquareStartToPoint || deltaNewYOldY>fromPointToLengthOfTwoSquare || deltaNewXOldX>fromPointToLengthOfTwoSquare || deltaOldXNewX>fromPointToEndOfOneSquareBack)
    	    			 || (this.piece.getPoint().y==oldCoord.y && (deltaOldXNewX > fromSquareStartToPoint || deltaNewXOldX>fromPointToLengthOfOneSquare))
    	    			 || (this.piece.getPoint().x==oldCoord.x))) {
    	    		this.piece.setPoint(oldCoord);
    	    		player();
    	    	//The forth condition makes first sure that the black piece can't move down and more than one row up,
        	    // then it makes sure that the piece can't move straight to the side, and finally it makes sure that the pieces don't go straight down. 
    	    	} else if ((this.piece.getColor().equals(pTwoColor) || this.piece.isKing == true) && 
    	    			((deltayOldYNewY > fromPointToEndOfOneSquareBack || deltaNewYOldY > fromPointToLengthOfOneSquare || deltaNewXOldX>fromPointToEndOfOneSquareBack || deltaOldXNewX > fromPointToLengthOfTwoSquare)
    	    			||(this.piece.getPoint().y==oldCoord.y && (deltaOldXNewX>fromSquareStartToPoint || deltaNewXOldX>fromPointToLengthOfOneSquare))
    	    			|| (this.piece.getPoint().x==oldCoord.x))) {
    	    		this.piece.setPoint(oldCoord); 
    	    		player();	
    	    	}
					searchBoard();

			}
		
			if(this.piece.getColor().equals(pOneColor) 
				&& this.piece.getPoint().y > finalBoardSize-squareDim){
				this.piece.isKing = true;
			}else if(this.piece.getColor().equals(pTwoColor)
					&& this.piece.getPoint().y < squareDim){
				this.piece.isKing = true;	
			}
			draw.repaintBoardPanel();
    	    return;  
	}
	
	private void searchBoard() {
		this.removeList.clear();
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
				if (pieceBlack.getColor().equals(pTwoColor) && pieceRed.getColor().equals(pOneColor) 
				&& ((pieceBlack.getPoint().x - squareDim) == pieceRed.getPoint().x && (pieceBlack.getPoint().y - squareDim) == pieceRed.getPoint().y)){
						if(playerOne == true){
							for(Pieces piece: this.pieceList){
								//Makes sure at red piece can't jump right over a black piece if there is another piece behind it. 
								if(pieceBlack.getPoint().x == (piece.getPoint().x - squareDim) && pieceBlack.getPoint().y == (piece.getPoint().y - squareDim)
										|| ((pieceBlack.getPoint().x + squareDim) > finalBoardSize) || ((pieceBlack.getPoint().y + squareDim) > finalBoardSize)){
									// the OR parts of the sentence make sure that piece cant jump of board
									moveAnyPiece = true;
									jumpListRedRBlackL.remove(pieceRed);
									break;
								//If the isn't anything behind the black piece the red piece are going to slain that the next turn. 
								} else{
									if (jumpListRedRBlackL.contains(pieceRed)){
										continue;
									}
									jumpListRedRBlackL.add(pieceRed);
									removeList.add(pieceBlack);
									moveAnyPiece = false;
								}
							}
						} else if (playerTwo == true){
							for(Pieces piece: this.pieceList){
								//Makes sure at black piece can't jump right over a red piece if there is another piece behind it. 
								if(pieceRed.getPoint().x == (piece.getPoint().x + squareDim) && pieceRed.getPoint().y == (piece.getPoint().y + squareDim)
										|| ((pieceRed.getPoint().x - squareDim) < 0) || ((pieceRed.getPoint().y - squareDim) < 0)){
									// the OR parts of the sentence make sure that piece can not jump of board 
									moveAnyPiece = true;
									jumpListRedRBlackL.remove(pieceBlack);
									break;
								//If the isn't anything behind the red piece the black piece is going to get slain next turn. 
							} else{
								if (jumpListRedRBlackL.contains(pieceBlack)){
									continue;
								}
							jumpListRedRBlackL.add(pieceBlack);
							removeList.add(pieceRed);
							moveAnyPiece = false;
							}
						}
					}
				} 
			}
		}
		
		for (Pieces pieceRed: this.pieceList) {
			for(Pieces pieceBlack: this.pieceList){
				//This one checks if the a red piece can jump over a black piece to the left, and if a black piece can jump right over a red piece.
			 	if (pieceRed.getColor().equals(pOneColor) && pieceBlack.getColor().equals(pTwoColor) 
				&& ((pieceRed.getPoint().x - squareDim) == pieceBlack.getPoint().x && (pieceRed.getPoint().y + squareDim) == pieceBlack.getPoint().y)
				){
			 			if(playerOne == true){
			 				for(Pieces piece: this.pieceList){
								//Makes sure at red piece can't jump left over a black piece if there is another piece behind it. 
			 					if(pieceBlack.getPoint().x == (piece.getPoint().x + squareDim) && (pieceBlack.getPoint().y == (piece.getPoint().y - squareDim)) 
			 							|| ((pieceBlack.getPoint().x - squareDim) < 0 ) || ((pieceBlack.getPoint().y + squareDim) > finalBoardSize)){
			 					// the OR parts of the sentence make sure that piece cant jump of board
			 						jumpListRedLBlackR.remove(pieceRed);
			 						//It needs to check if it is possible to jump to the other side
			 						if(jumpListRedRBlackL.size()>0){
			 							moveAnyPiece = false;
			 							
			 						} else if(jumpListRedLBlackR.size() > 0){
			 							moveAnyPiece = false;
			 						}
			 						else {
			 						moveAnyPiece = true;
			 						}
			 						
									break;
			 						
								//If the isn't anything behind the black piece the red piece are going to slain that the next turn. 
								} else{
									if ( jumpListRedLBlackR.contains(pieceRed)){
										continue;
									}
										
									jumpListRedLBlackR.add(pieceRed);
									removeList.add(pieceBlack);
									moveAnyPiece = false;
									}
			 					}
						} else if (playerTwo == true){
							for(Pieces piece: this.pieceList){
								//Makes sure at black piece can't jump right over a red piece if there is another piece behind it. 
			 					if(pieceRed.getPoint().x == (piece.getPoint().x - squareDim) && (pieceRed.getPoint().y == (piece.getPoint().y + squareDim)) 
			 							|| ((pieceRed.getPoint().x + squareDim) > finalBoardSize) || ((pieceRed.getPoint().y - squareDim) < 0)){
			 					// the OR parts of the sentence make sure that piece can't jump of board
			 						jumpListRedLBlackR.remove(pieceBlack);
			 						//But it needs to check if it is possible to jump to the other side
			 						if(jumpListRedRBlackL.size()>0){
			 						moveAnyPiece = false;
			 						
			 						} else if ( jumpListRedLBlackR.size() > 0 ){
			 							moveAnyPiece = false;
			 						} else {
			 							moveAnyPiece = true;
			 							
			 						}
			 							
			 							break;
			 						
								//If the isn't anything behind the black piece the red piece are going to slain that the next turn. 
								} else{
									if ( jumpListRedLBlackR.contains(pieceBlack)){
										continue;
									} else {
									jumpListRedLBlackR.add(pieceBlack);
									removeList.add(pieceRed);
									moveAnyPiece = false;
									}
									}
			 					}
			 			}
					}
			}
		}
	
	}
	
	
	public void mouseDragged(MouseEvent e, DrawOnBoardPanel draw) {
		
		// Allows the piece to follow the mouse when clicked. 
	
		if (move) {
            piece.setPoint(e.getPoint()); // Update location of piece center.
            draw.repaintBoardPanel();
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
	
	
	public void jumpAgain(){
		jumpListRedLBlackR.clear();
		jumpListRedRBlackL.clear();
		if (playerTwo) {
			playerOne = true;
			playerTwo = false;
			searchBoard();
			if (jumpListRedLBlackR.contains(jumpingPiece)){
				jumpListRedLBlackR.clear();
			} else if( jumpListRedRBlackL.contains(jumpingPiece)) {
				jumpListRedRBlackL.clear();
				jumpListRedRBlackL.add(jumpingPiece);
			} else {
				playerOne = false;
				playerTwo = true;
				}
			}
		if (playerOne) {
			playerOne = false;
			playerTwo = true;
			searchBoard();
			if (jumpListRedLBlackR.contains(jumpingPiece)){
				jumpListRedLBlackR.clear();
				jumpListRedLBlackR.add(jumpingPiece);
			} else if( jumpListRedRBlackL.contains(jumpingPiece)) {
				jumpListRedRBlackL.clear();
				jumpListRedRBlackL.add(jumpingPiece);
			} else {
				playerOne = true;
				playerTwo = false;
				searchBoard();
				}
		}
		searchBoard();
	}
	
	// resets the booleans for a completely new game
	public void resetTurn () {
		playerOne = true;
		playerTwo = false;
		jumpRedLBlackR = false;
		jumpRedRBlackL = false;
		moveAnyPiece = true;
	}
	
	public void winCheck (String message) {
		menu.gameOver(message);
	}
	
	public void pieceCount (){
		if (playerOne){
			playerOnePieceCount--;
		} else if ( playerTwo ) {
			playerTwoPieceCount--;
		}
		if ( playerOnePieceCount == 0 ){
			winCheck("Player Two Won!\n" + "Do you want to play again?");
		}
		if ( playerTwoPieceCount == 0 ) {
			winCheck("Player One Won!\n" + "Do you want to play again?");
		}
	}
	
	public void setAmountOfPieces (int amount){
		playerOnePieceCount = amount / 2;
		playerTwoPieceCount = amount / 2;
	}
	
	public int getPlayerOnePieceCount() {
		return this.playerOnePieceCount;
	}
	
	public int getPlayerTwoPieceCount() {
		return this.playerTwoPieceCount;
	}
}