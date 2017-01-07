import java.awt.event.MouseEvent;

public class CheckersModel {

	private CheckersView view;
	private int cmdLineArgument;
	private int pieceArray[][];
	private boolean pieceLiftet = false;
	private int[] coordsLiftetPiece;
	int[] rowAndColumn = new int[2];
	int[] theMove = new int[2];
	int[][] legalMoves = new int[3][2];
	int lukeOrDarthClicked;
	int rowLiftedFrom, colLiftedFrom;
	int removePieceRow, removePieceCol;
	
	
	public CheckersModel(CheckersView view){
		this.view = view;
		this.cmdLineArgument = view.cmdLineArgument;
	}
	
	public void menu() {
		this.view.menu();
	}
	
	public void game() {
		pieceArray = fillArray(cmdLineArgument);
		this.view.game(cmdLineArgument);
		this.play(pieceArray);
	}
	
	public void play(int[][] pieceArray) {
		this.view.play(pieceArray, cmdLineArgument);
	}
	
	public int[][] fillArray(int n) {
		
		// Very simple version that puts down one piece on each site. 
		// There is either a 0 for no piece.
		// Or a 1 or -1 for a piece. 
		
		// Consider making a version with a class called pieces - because pieces can hold information about where they were before.
		// What side they are on and what not. 
		
		int[][] pieceArray = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == 0 && j == 1) {
					pieceArray[i][j] = 1;
				} else if (i == n-1 && j == n-2) {
					pieceArray[i][j] = -1;
				} else {
					pieceArray[i][j] = 0;
				}
			}
		}
		return pieceArray;
	}
	
	public void rowAndColumnClicked(MouseEvent e) {
		
		// Comment on why the x and y coords are the opposit way. 
		
		int xCount = 0;
		int sizeOfPanel = 700/cmdLineArgument;
		int xValueClicked = sizeOfPanel;
		for (int i = 0; i < cmdLineArgument; i++) {			
			if (e.getX() <= xValueClicked) {
				rowAndColumn[1] = xCount;
				break;
			} else {
				xValueClicked += sizeOfPanel;
			}
			xCount += 1;
		}
		
		int yCount = 0;
		int yValueClicked = sizeOfPanel;
		for (int j = 0; j < cmdLineArgument; j++) {
			if (e.getY() <= yValueClicked) {
				rowAndColumn[0] = yCount;
				break;
			} else {
				yValueClicked += sizeOfPanel;
			}
			yCount += 1;
		}
		
		if (pieceLiftet == false) {
			liftPiece(pieceArray, rowAndColumn);
		} else {
			setPiece(pieceArray, rowAndColumn);
		}
	}
	
	public void liftPiece(int[][] pieceArray, int[] rowAndColumn) {
		
		if ((pieceLiftet == false) && (pieceArray[rowAndColumn[0]][rowAndColumn[1]] != 0)) {
			pieceLiftet = true;
			lukeOrDarthClicked = pieceArray[rowAndColumn[0]][rowAndColumn[1]];
			
			if (pieceArray[rowAndColumn[0]][rowAndColumn[1]] == 1){
					pieceArray[rowAndColumn[0]][rowAndColumn[1]] = 2;
			} 
			if (pieceArray[rowAndColumn[0]][rowAndColumn[1]] == -1){
				pieceArray[rowAndColumn[0]][rowAndColumn[1]] = -2;
			} 
			coordsLiftetPiece = rowAndColumn; 
			
			rowLiftedFrom = rowAndColumn[0];
			colLiftedFrom =rowAndColumn[1];
			
			legalMoves(lukeOrDarthClicked);
		} else {
			System.out.println("ERROR: Piece already lifted or there is nothing to lift");
		}
		
		this.play(pieceArray);
	}
	
	public void setPiece(int[][] pieceArray, int[] rowAndColumn) {
		
		if (pieceLiftet == true) {
			
			if (isMoveIn(rowAndColumn, legalMoves) == true) {
				pieceLiftet = false;
				
				// SOMETHING HERE ABOUT MOVING THE ONE THAT DIES
				if (lukeOrDarthClicked == 1) {
					pieceArray[theMove[0]][theMove[1]] = 1;
					pieceArray[rowLiftedFrom][colLiftedFrom] = 0;
					pieceJumped(removePieceRow, removePieceCol);
				} else {
					pieceArray[theMove[0]][theMove[1]] = -1;
					pieceArray[rowLiftedFrom][colLiftedFrom] = 0;
					pieceJumped(removePieceRow, removePieceCol);
				}
				
				
//				for (int i = 0; i < cmdLineArgument; i++) {
//					System.out.println();
//					for (int j = 0; j < cmdLineArgument; j++) {
//						System.out.print(pieceArray[i][j]);
//					}
//				}
			}
			
		} else {
			System.out.println("ERROR: Wrong placement of piece - try another place.");
		}
		
		winCheck();
		
		
		this.play(pieceArray);
		
	}
	
	public void legalMoves(int lukeOrDarthClicked) {
		
		
		// Left move up for luke - only a legal move if its inside the board.
		if ((lukeOrDarthClicked == -1) && (coordsLiftetPiece[0] - 1 >= 0) && (coordsLiftetPiece[1] - 1 >= 0)) {
			if (pieceArray[coordsLiftetPiece[0] - 1][coordsLiftetPiece[1] - 1] == 0) {
				legalMoves[0][0] = coordsLiftetPiece[0] - 1;
				legalMoves[0][1] = coordsLiftetPiece[1] - 1;	
			}
			if (pieceArray[coordsLiftetPiece[0] - 1][coordsLiftetPiece[1] - 1] == 1) {
				legalMoves[0][0] = coordsLiftetPiece[0] - 2;
				legalMoves[0][1] = coordsLiftetPiece[1] - 2;	
				
//				pieceArray[coordsLiftetPiece[0] - 1][coordsLiftetPiece[1] - 1] = 0;
			}
		}
		// Right move up for luke - only a legal move if its inside the board. 	
		if ((lukeOrDarthClicked == -1) && (coordsLiftetPiece[0] - 1 >= 0) && (coordsLiftetPiece[1] + 1 < cmdLineArgument)) {
			if (pieceArray[coordsLiftetPiece[0] - 1][coordsLiftetPiece[1] + 1] == 0) {
				
				// needs some kind of bool check for pieceArray[i][j] + 1 
				// in the last array piecearray[i][j+1] <- the +1 has to be checked against 
				// coordsliftetpiece + 1 and to see if its > than the array. 
				
				legalMoves[1][0] = coordsLiftetPiece[0] - 1;
				legalMoves[1][1] = coordsLiftetPiece[1] + 1;
			}
			// Jump check for luke starts
			if (pieceArray[coordsLiftetPiece[0] - 1][coordsLiftetPiece[1] + 1] == 1) {
				legalMoves[1][0] = coordsLiftetPiece[0] - 2;
				legalMoves[1][1] = coordsLiftetPiece[1] + 2;
				
				
				removePieceRow = coordsLiftetPiece[0] - 1;
				removePieceCol = coordsLiftetPiece[1] + 1;
				
//				pieceArray[coordsLiftetPiece[0] - 1][coordsLiftetPiece[1] + 1] = 0;  // makes jumped piece disappear
			}
			if (pieceArray[coordsLiftetPiece[0] - 1][coordsLiftetPiece[1] - 1] == 1) {
				legalMoves[1][0] = coordsLiftetPiece[0] - 2;
				legalMoves[1][1] = coordsLiftetPiece[1] + 2;
				
				removePieceRow = coordsLiftetPiece[0] - 1;
				removePieceCol = coordsLiftetPiece[1] - 1;
				
//				pieceArray[coordsLiftetPiece[0] - 1][coordsLiftetPiece[1] - 1] = 0;  // makes jumped piece disappear
			}
			// Jump check for luke ends
			
 		}
		// Left move down for darth - only a legal move if its inside the board.
		if ((lukeOrDarthClicked == 1) && (coordsLiftetPiece[0] + 1 < cmdLineArgument) && (coordsLiftetPiece[1] - 1 >= 0)) {
			if (pieceArray[coordsLiftetPiece[0] + 1][coordsLiftetPiece[1] - 1] == 0) {
				legalMoves[0][0] = coordsLiftetPiece[0] + 1;
				legalMoves[0][1] = coordsLiftetPiece[1] - 1;	
			}
			
			if (pieceArray[coordsLiftetPiece[0] + 1][coordsLiftetPiece[1] - 1] == -1) {
				legalMoves[0][0] = coordsLiftetPiece[0] + 2;
				legalMoves[0][1] = coordsLiftetPiece[1] - 2;	
				
				removePieceRow = coordsLiftetPiece[0] + 1;
				removePieceCol = coordsLiftetPiece[1] - 1;
			}
		}
		
		// Right move down for luke - only a legal move if its inside the board. 	
		if ((lukeOrDarthClicked == 1) && (coordsLiftetPiece[0] + 1 < cmdLineArgument) && (coordsLiftetPiece[1] + 1 < cmdLineArgument )) {
			if (pieceArray[coordsLiftetPiece[0] + 1][coordsLiftetPiece[1] + 1] == 0) {
				
				// needs some kind of bool check for pieceArray[i][j] + 1 
				// in the last array piecearray[i][j+1] <- the +1 has to be checked against 
				// coordsliftetpiece + 1 and to see if its > than the array. 
				
				legalMoves[1][0] = coordsLiftetPiece[0] + 1;
				legalMoves[1][1] = coordsLiftetPiece[1] + 1;
			}
			
			if (pieceArray[coordsLiftetPiece[0] + 1][coordsLiftetPiece[1] + 1] == -1) {
				
				// needs some kind of bool check for pieceArray[i][j] + 1 
				// in the last array piecearray[i][j+1] <- the +1 has to be checked against 
				// coordsliftetpiece + 1 and to see if its > than the array. 
				
				legalMoves[1][0] = coordsLiftetPiece[0] + 2;
				legalMoves[1][1] = coordsLiftetPiece[1] + 2;
				
				removePieceRow = coordsLiftetPiece[0] + 1;
				removePieceCol = coordsLiftetPiece[1] + 1;
			}
 		}

		// Where you come from is always a legal move.
		
		legalMoves[2][0] = coordsLiftetPiece[0];
		legalMoves[2][1] = coordsLiftetPiece[1];
		
	}
	
	public boolean isMoveIn(int[] rowAndColumn ,int[][] legalMoves) {
		
		boolean isIn = false;
		
		outerloop:
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 2; j++) {
				if ((legalMoves[i][j] == rowAndColumn[0]) && (legalMoves[i][j+1] == rowAndColumn[1])) {
					theMove[0] = rowAndColumn[0];
					theMove[1] = rowAndColumn[1];
					isIn = true;
					break outerloop;
				} else {
					j = 2;
				}
			}
		}
		return isIn;
	}
	
	public void pieceJumped (int removePieceRow, int removePieceCol){
		pieceArray[removePieceRow][removePieceCol] = 0;
	}
	
	public void winCheck(){
		int lukeCounter = 0, darthCounter = 0;
			for ( int i = 0; i < cmdLineArgument; i++){
				for ( int j = 0; j < cmdLineArgument; j++){
					if (pieceArray[i][j] == -1){
						lukeCounter++;
					} else if (pieceArray[i][j] == 1){
						darthCounter++;
					}
				}
			}
			if (lukeCounter == 0){
//				System.out.println("The dark side has won");
				view.winBox("Darth has won!", "Game over!");
				
			} else if ( darthCounter == 0){
//				System.out.println("Luke will live to see another day");
				view.winBox("Luke has won!", "Game over!");
			}
	}
	
	
	
}
