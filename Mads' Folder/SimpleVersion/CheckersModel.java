import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;

public class CheckersModel {

	// The model class does all the manipulation of the data and holds all the information on the board, 
	// the pieces and everything related to moving them around. 
	
	private CheckersView view;
	private int boardSquareSize;
	private int BOARDSIZE = 700;
	private int pieceArray[][];
	private boolean pieceLiftet = false;
	private int[] coordsLiftetPiece;
	private int[] rowAndColumn = new int[2];
	private int[] theMove = new int[2];
	private int[][] legalMoves = new int[3][2];
	private int lukeOrDarthClicked;
	private boolean doubleJump = false;
	private int[] doubleJumpCoords = new int[2];
	private int whosTurn = -1; 
	
	// Constructor holds the view for access here off. 
	// Also instantiates the boardSize through the command line argument supplied when running the game.
	public CheckersModel(CheckersView view) {
		this.view = view;
		this.boardSquareSize = view.boardSquareSize;
	}
	
	// Calls menu() from view - shows the initial menu.
	public void menu() {
		this.view.menu();
	}
	
	// Calls game() from view - shows the game board.
	// Also calls play() from view - sets the initial pieces. 
	public void game() {
		pieceArray = fillPieceArray(boardSquareSize);
		this.view.game(boardSquareSize);
		this.play(pieceArray);
	}
	
	private void play(int[][] pieceArray) {
		this.view.play(pieceArray, boardSquareSize);
	}
	
	
	/////////////////////////////////////////////////////////////////////
	// CONSIDER MOVING EVERYTHING FROM THIS POINT TO A SEPERATE CLASS. //
    /////////////////////////////////////////////////////////////////////
	
	
	private int[][] fillPieceArray(int boardSize) {
		
		// Very simple version that puts down one piece on each site. 
		// There is either a 0 for no piece or a 1 (darth) or -1 (luke) for a piece. 
				
		int[][] pieceArray = new int[boardSize][boardSize];
		
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				if (i == 0 && j == 1) {
					pieceArray[i][j] = 1;
				} else if (i == boardSize-1 && j == boardSize-2) {
					pieceArray[i][j] = -1;
				} else {
					pieceArray[i][j] = 0;
				}
			}
		}
		return pieceArray;
	}
	
	protected void rowAndColumnClicked(MouseEvent e) {
		
		// Function that translate the click into what row and column was clicked.
		// Column is saved in rowAndColumn[1] and row in rowAndColumn[0]
		
		// Divides the boardSize (700px) into the size of each square. This value is saved in 
		// sizeOfPanel - in the first round of the loop the e.getX() (the x value of the mouse click) 
		// is check against the xValueClicked (sizeOfPanel) if it's less or the same the click
		// happened in the first column - if not - sizeOfPanel is added to xValueClicked and the check
		// is done again. 
		
		int colCount = 0;
		int sizeOfPanel = BOARDSIZE/boardSquareSize;
		int xValueClicked = sizeOfPanel;
		
		for (int i = 0; i < boardSquareSize; i++) {			
			if (e.getX() <= xValueClicked) {
				rowAndColumn[1] = colCount;
				break;
			} else {
				xValueClicked += sizeOfPanel;
			}
			colCount += 1;
		}
		
		// Same principle as above just for the rows instead of columns i.e. e.getY() instead of e.getX().
		int rowCount = 0;
		int yValueClicked = sizeOfPanel;
		for (int j = 0; j < boardSquareSize; j++) {
			if (e.getY() <= yValueClicked) {
				rowAndColumn[0] = rowCount;
				break;
			} else {
				yValueClicked += sizeOfPanel;
			}
			rowCount += 1;
		}
		
		// Move piece is called to initiate a move. 
		movePiece(e);
	}
	
	private void movePiece(MouseEvent e) {
		
		// Initiates a move - if the pieceLiftet boolean is false it allows for liftPiece() to be called
		// and vice versa. 
		
		if (pieceLiftet == false) {
			// This extra piece of logic makes sure that only the one who's turn it is can actually move.
			if (pieceArray[rowAndColumn[0]][rowAndColumn[1]] == whosTurn) {
				liftPiece(pieceArray, rowAndColumn);
				changeCursor(e, lukeOrDarthClicked);
			} else {
				System.out.println("ERROR: Wrong side clicked - its not his/hers turn.");
			}
		} else {
			setPiece(pieceArray, rowAndColumn);
			changeCursor(e, lukeOrDarthClicked);
			// Swaps the turn for the next click.
			if (whosTurn == 1) {
				whosTurn = -1;
			} else {
				whosTurn = 1;
			}
		}
	}
	
	private void liftPiece(int[][] pieceArray, int[] rowAndColumn) {
		
		// Lifts the piece that is clicked if pieceLiftet is false and the field clicked is not empty.
		// If either luke or darth is clicked it is saved in lukeOrDarthClicked.
		// Whatever field what clicked is set to 0 in the pieceArray, because it moves the piece being clicked. 
		// Hereafter legalMoves() is called with lukeOrDarthClicked. 
		
		if ((pieceLiftet == false) && (pieceArray[rowAndColumn[0]][rowAndColumn[1]] != 0)) {
			pieceLiftet = true;
			lukeOrDarthClicked = pieceArray[rowAndColumn[0]][rowAndColumn[1]];
			pieceArray[rowAndColumn[0]][rowAndColumn[1]] = 0;
			coordsLiftetPiece = rowAndColumn; 
			legalMoves(lukeOrDarthClicked);
		} else {
			System.out.println("ERROR: Piece already lifted or there is nothing to lift");
		}
		
		// Play() is called to update the board after lifting a piece.  
		this.play(pieceArray);
	}
	
	private void setPiece(int[][] pieceArray, int[] rowAndColumn) {
		
		// Sets the piece on the board in the field that is clicked if that field is 
		// one of the legal moves. isMoveIn() is called to make sure that the field 
		// pressed is one of the legal moves.
		
		if (pieceLiftet == true) {
			if (isMoveIn(rowAndColumn, legalMoves) == true) {
				pieceLiftet = false;
				if (lukeOrDarthClicked == 1) {
					pieceArray[theMove[0]][theMove[1]] = 1;
					lukeOrDarthClicked = 0; // to make the cursor swap work - see changeCursor().
				} else {
					pieceArray[theMove[0]][theMove[1]] = -1;
					lukeOrDarthClicked = 0; // to make the cursor swap work - see changeCursor().
				}

				// Only called if the opponent is to be jumped over to 'kill' his piece.
				// It remove the picture of either darth or luke if they are 'killed'.
				// It does so by setting it's coordintates to 0 in the piece array before play is called.
				if (doubleJump == true) {
					pieceArray[doubleJumpCoords[0]][doubleJumpCoords[1]] = 0;
					doubleJump = false;
				}
			}
		} else {
			System.out.println("ERROR: Wrong placement of piece - try another place.");
		}
		this.play(pieceArray);
	}
	
	private void legalMoves(int lukeOrDarthClicked) {

		// Checks for legal moves after a piece has been liftet off the board.
		// For luke it checks left up; which is essentially a 'pieceArray[x-1][y-1] = -1'. 
		// Same goes for right up; which is essentially a 'pieceArray[x-1][y+1] = -1'.
		// And same logic goes for darth with [+1][-1] and [+1][+1].
		// The checks make sure that no one can jump out of the board and no one can stand on top of each other.
		// If the opponents piece is in the way it allows for a jump over it.
		
		// Left move up for luke - only a legal move if its inside the board.
		if ((lukeOrDarthClicked == -1) && (coordsLiftetPiece[0] - 1 >= 0) && (coordsLiftetPiece[1] - 1 >= 0)) {
			// Just a normal one step jump - checks for anything else on the board if nothing it allows the move.
			if (pieceArray[coordsLiftetPiece[0] - 1][coordsLiftetPiece[1] - 1] == 0) {
				legalMoves[0][0] = coordsLiftetPiece[0] - 1;
				legalMoves[0][1] = coordsLiftetPiece[1] - 1;	
			}
			// This is a jump over "two step" jump - if the opponents piece is in the way it allows for a double jump over move.
			if (pieceArray[coordsLiftetPiece[0] - 1][coordsLiftetPiece[1] - 1] == 1) {
				legalMoves[0][0] = coordsLiftetPiece[0] - 2;
				legalMoves[0][1] = coordsLiftetPiece[1] - 2;	
				// Saves the coords of the one being jumped - used to remove the .png later. 	
				doubleJump = true;
				doubleJumpCoords[0] = coordsLiftetPiece[0] - 1;
				doubleJumpCoords[1] = coordsLiftetPiece[1] - 1;
			}
		}
		// Right move up for luke - only a legal move if its inside the board. 	
		if ((lukeOrDarthClicked == -1) && (coordsLiftetPiece[0] - 1 >= 0) && (coordsLiftetPiece[1] + 1 < boardSquareSize)) {
			if (pieceArray[coordsLiftetPiece[0] - 1][coordsLiftetPiece[1] + 1] == 0) {
				legalMoves[1][0] = coordsLiftetPiece[0] - 1;
				legalMoves[1][1] = coordsLiftetPiece[1] + 1;
			}
			// This is a jump over "two step" jump - if the opponents piece is in the way it allows for a double jump over move.
			if (pieceArray[coordsLiftetPiece[0] - 1][coordsLiftetPiece[1] + 1] == 1) {
				legalMoves[1][0] = coordsLiftetPiece[0] - 2;
				legalMoves[1][1] = coordsLiftetPiece[1] + 2;
				// Saves the coords of the one being jumped - used to remove the .png later. 
				doubleJump = true;
				doubleJumpCoords[0] = coordsLiftetPiece[0] - 1;
				doubleJumpCoords[1] = coordsLiftetPiece[1] + 1;
			}
 		}
		// Left move down for darth - only a legal move if its inside the board.
		if ((lukeOrDarthClicked == 1) && (coordsLiftetPiece[0] + 1 < boardSquareSize) && (coordsLiftetPiece[1] - 1 >= 0)) {
			if (pieceArray[coordsLiftetPiece[0] + 1][coordsLiftetPiece[1] - 1] == 0) {
				legalMoves[0][0] = coordsLiftetPiece[0] + 1;
				legalMoves[0][1] = coordsLiftetPiece[1] - 1;	
			}
			// This is a jump over "two step" jump - if the opponents piece is in the way it allows for a double jump over move.
			if (pieceArray[coordsLiftetPiece[0] + 1][coordsLiftetPiece[1] - 1] == -1) {
				legalMoves[0][0] = coordsLiftetPiece[0] + 2;
				legalMoves[0][1] = coordsLiftetPiece[1] - 2;	
				// Saves the coords of the one being jumped - used to remove the .png later. 
				doubleJump = true;
				doubleJumpCoords[0] = coordsLiftetPiece[0] + 1;
				doubleJumpCoords[1] = coordsLiftetPiece[1] - 1;
			}
		}
		// Right move down for darth - only a legal move if its inside the board. 	
		if ((lukeOrDarthClicked == 1) && (coordsLiftetPiece[0] + 1 < boardSquareSize) && (coordsLiftetPiece[1] + 1 < boardSquareSize )) {
			if (pieceArray[coordsLiftetPiece[0] + 1][coordsLiftetPiece[1] + 1] == 0) {
				legalMoves[1][0] = coordsLiftetPiece[0] + 1;
				legalMoves[1][1] = coordsLiftetPiece[1] + 1;
			}
			// This is a jump over "two step" jump - if the opponents piece is in the way it allows for a double jump over move.
			if (pieceArray[coordsLiftetPiece[0] + 1][coordsLiftetPiece[1] + 1] == -1) {
				legalMoves[1][0] = coordsLiftetPiece[0] + 2;
				legalMoves[1][1] = coordsLiftetPiece[1] + 2;
				// Saves the coords of the one being jumped - used to remove the .png later. 
				doubleJump = true;
				doubleJumpCoords[0] = coordsLiftetPiece[0] + 1;
				doubleJumpCoords[1] = coordsLiftetPiece[1] + 1;
			}
 		}
		// Where you come from is always a legal move - so you can always place the piece here again.
		legalMoves[2][0] = coordsLiftetPiece[0];
		legalMoves[2][1] = coordsLiftetPiece[1];
	}
	
	private boolean isMoveIn(int[] rowAndColumn ,int[][] legalMoves) {
		
		// Makes a check to see if the clicked rowAndColumn is one of the legal moves.
		// Checks a row at a time in legalMoves - if a legal move is found true is returned. 
		
		boolean isIn = false;
		
		outerloop:
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 2; j++) {
				// If the row in legalMoves doesn't match the one in rowAndColumn it moves to else. 
				if ((legalMoves[i][j] == rowAndColumn[0]) && (legalMoves[i][j+1] == rowAndColumn[1])) {
					theMove[0] = rowAndColumn[0];
					theMove[1] = rowAndColumn[1];
					isIn = true;
					break outerloop;
				} else {
					j = 2; // Checks a row at a time - i++; 
				}
			}
		}
		return isIn;
	}
	
	private void changeCursor(MouseEvent e, int lukeOrDarthClicked) {
		
		// Changes the cursor when one of the pieces are clicked.
		// Utilizes two custom cursors - one for luke and one for darth. 
		
		Point whatever = new Point(35,35);
		Cursor lukeCursor = Toolkit.getDefaultToolkit().createCustomCursor(view.lukePicResize, whatever, "lukeCursor");
		Cursor darthCursor = Toolkit.getDefaultToolkit().createCustomCursor(view.darthPicResize, whatever, "darthCursor");
		
		// Chooses curser based on what is clicked i.e. the lukeOrDarthClicked variable. 
		if (lukeOrDarthClicked == -1) {
			e.getComponent().setCursor(lukeCursor);
		} else if (lukeOrDarthClicked == 1) {
			e.getComponent().setCursor(darthCursor);
		} else {
			e.getComponent().setCursor(Cursor.getDefaultCursor());
		}

	}
	
}
