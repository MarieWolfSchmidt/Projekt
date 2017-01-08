import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;


public class CoreControl extends JComponent implements MouseListener, MouseMotionListener {
	
	
	private int cmdLineArgument;
	private int boardSize = 600;
	public int finalBoardSize; // If the supplied commandLineArgument is an odd number then we change the boardSize so it matches.
	private int squareDim;
	private int pieceSize;
	private Point oldCoord;
	private int deltaX, deltaY;
	private CheckersPieces piece;
	private List<CheckersPieces> pieceList;
	private boolean move = false;
	private boolean jump = false;
	private boolean playerOne = true;
	private boolean playerTwo = false;
	
	

        public CoreControl(int cmdLineArgument) {
        	this.cmdLineArgument = cmdLineArgument;
        	this.squareDim = boardSize/cmdLineArgument;  
        	this.finalBoardSize = squareDim*cmdLineArgument;
        	this.pieceSize = (squareDim/3)*2;
        	pieceList = new ArrayList<>();
        	add(1,1,1);
            add(2,cmdLineArgument,cmdLineArgument);
        	addMouseListener(this);
        	addMouseMotionListener(this);
        }
        
        
        @Override 
        protected void paintComponent(Graphics g) {
        // Override jComponent's function paintComponent to draw the board and the pieces.
        // The method draws the board on a JPanel utilizing the structure of a coordinate system.
        // It begins in (0,0) and draws two rows at a time to easier manipulate the colors of the board.
        // The size of the squares are decided using the size of the board divided by the cmdlineargument. 
        
        	for(int i = 0; i <= boardSize - squareDim; i += 2*squareDim){ 		// 2*squareDim is added to i to fill in the right color.
        		for(int j = 0; j <= boardSize - squareDim; j += 2*squareDim){
        			g.setColor(Color.GRAY);
        			g.fillRect(j, i, squareDim, squareDim);						// Gray square - first row.
        			g.setColor(Color.WHITE);
        			g.fillRect(j+squareDim, i, squareDim, squareDim); 			// White square - first row.
        			g.setColor(Color.GRAY);
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
           	for(CheckersPieces piece: pieceList){
           		piece.drawPieces(g, piece.point, pieceSize);
           	}
           	
        } 
        
        public void fillCell() {
            repaint();   
        }

		@Override
		public void mousePressed(MouseEvent e) {

			// When the mouse is pressed two checks are made - one to see if anything is in the location 
			// and one to see if it is the turn of the piece pressed. If everything checks out the 
			// piece is 'lifted'.

            for (CheckersPieces piece: pieceList) {
                if (CheckersPieces.contains(e.getX(), e.getY(), piece.point, pieceSize)) {
                	if (playerOne == true && piece.color == 1) {
                		this.piece = piece;
                		oldCoord = piece.point.getLocation();
                		deltaX = e.getX() - piece.point.x;
                		deltaY = e.getY() - piece.point.y;
                		move = true;	
                        playerOne=false;
                        playerTwo=true;
                	} else if (playerTwo == true && piece.color == 2) {
                		this.piece = piece;
                		oldCoord = piece.point.getLocation();
                		deltaX = e.getX() - piece.point.x;
                		deltaY = e.getY() - piece.point.y;
                		move = true;
                        playerOne=true;
                        playerTwo=false;
                	}
                }
            }		
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		
			// When the mouse is released the piece is placed. 
			
			if (move == true) {
				move = false;
			} else {
				return;
			}
            
			 int delta1 = squareDim*2 - (pieceSize/2);
			 int delta2 = squareDim*3 - delta1;
			 int delta3 = squareDim*2 - delta1;
			 int deltaBlackDown = squareDim - (pieceSize/2);  
			
			// Centers the piece in the given square in the location where the mouse is released.
			piece.point.x = (((e.getX() - deltaX) / squareDim) * squareDim) + (squareDim / 2);
			piece.point.y = (((e.getY() - deltaY) / squareDim) * squareDim) + (squareDim / 2);
           
			// Difference between the 'old' location i.e. square and the 'new' one. 
			int deltay = piece.point.y - oldCoord.y;
			int deltax = piece.point.x - oldCoord.x;
			// It's preferable to calculate the conditions with positive numbers. 
			int deltax2 = oldCoord.x - piece.point.x;
			int deltay2 = oldCoord.y - piece.point.y;
     
			// This code block holds the conditions for moving the pieces. The whole list of pieces if looped over
			// to check if the move is legal. 
			for (CheckersPieces piece: pieceList) {
				if (jump == true) { 
					// This condition makes sure that no pieces can be on top of each other when releasing the mouse button.
					if (piece != this.piece && piece.point.x == this.piece.point.x && piece.point.y == this.piece.point.y) {
						this.piece.point = oldCoord;
						player();
					}
					// First condition makes sure the piece is inside the board. 
					if (this.piece.point.x < 0 || this.piece.point.x > finalBoardSize || this.piece.point.y < 0 || this.piece.point.y > finalBoardSize) {
						this.piece.point = oldCoord;
						player();
					// Second condition makes sure the piece can only move one down to the left and one down to the right.	
					} else if (this.piece.color == 1 && 
							((deltay > (delta1 + squareDim) ||deltax > (delta1 + squareDim) || deltax2 > (delta2 + squareDim) || deltay2 > (delta3 + squareDim)) 
							|| ((deltay < (deltaBlackDown + squareDim) && deltax2 > (squareDim - deltaBlackDown + squareDim))
							|| (deltay < (deltaBlackDown + squareDim) && deltax > (deltaBlackDown + squareDim)))
							|| ((deltax < (deltaBlackDown + squareDim)) && (deltax2 < (squareDim - deltaBlackDown + squareDim))))) {
						this.piece.point = oldCoord; 
						player();
					} else if(this.piece.color == 2 && 
							((deltax > (delta1 + squareDim) || deltax2 > (delta2 + squareDim) || deltay > (deltaBlackDown + squareDim) || deltay2 > (delta2 + squareDim)) 
							|| ((deltay2 < (deltaBlackDown + squareDim) && deltax2 > (squareDim - deltaBlackDown + squareDim)) 
							|| (deltay2 < (deltaBlackDown + squareDim) && deltax > (deltaBlackDown + squareDim))) 
							|| ((deltax2 < (deltaBlackDown + squareDim)) && (deltax < (squareDim - deltaBlackDown + squareDim))))) {
						this.piece.point = oldCoord;
						player();
					} else if (this.piece.color == 1) {  // WHY DOES THIS WORK ??? 
						pieceList.remove(piece.color); 
					} else if(this.piece.color == 2) {	// WHY DOES THIS WORK ???
						pieceList.remove(piece);
					}
					jump = false;
					repaint();
					return;
        		
				} else {
					if (piece != this.piece && piece.point.x == this.piece.point.x && piece.point.y == this.piece.point.y) {
						this.piece.point = oldCoord;
        	    		player();
        	    	}
        	 
        	    	if(this.piece.point.x < 0 || this.piece.point.x > 600 ||this.piece.point.y < 0 || this.piece.point.y > 600) {
        	    		this.piece.point = oldCoord;
        	    		player();
        	    	} else if (this.piece.color == 1 && ((deltay > delta1 || deltax > delta1 ||deltax2 > delta2 || deltay2 > delta3) || ((deltay < deltaBlackDown && deltax2 > (squareDim - deltaBlackDown))  || (deltay < deltaBlackDown && deltax > deltaBlackDown))|| ((deltax < deltaBlackDown) && (deltax2 < (squareDim - deltaBlackDown))))){
        	    		this.piece.point = oldCoord;
        	    		player();
        	    	} else if (this.piece.color == 2 && ((deltax > delta1 || deltax2 > delta2 || deltay > deltaBlackDown || deltay2 > delta2 )||((deltay2 < deltaBlackDown && deltax2 > (squareDim - deltaBlackDown))  || (deltay2 < deltaBlackDown && deltax > deltaBlackDown)) || ((deltax2 < deltaBlackDown) && (deltax < (squareDim - deltaBlackDown))))){
        	    		this.piece.point = oldCoord; 
        	    		player();	
        	    	}
        	    	repaint();
        	    }
        	    searchBoard();
        	    return;  
            }
		}

		@Override
		public void mouseDragged(MouseEvent e) {
				
				// Allows the piece to follow the mouse when clicked. 
			
				if (move) {
                    piece.point = e.getPoint(); // Update location of piece center.
                    repaint();
                 }
        }
		
		// Unused mouse events. 
		public void mouseClicked(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mouseMoved(MouseEvent e) {}
		
		public void add(int i, int row, int col) {
			
			// Instantiates a piece and adds it to the pieceList. 
			
			Point p = new Point((col-1)*squareDim+squareDim/2,(row-1)*squareDim+squareDim/2); 
			CheckersPieces piece = new CheckersPieces(i,p);
		    pieceList.add(piece);
		     
			}
		
		private void searchBoard() {
			
			// Sets jump == true if the opponents piece is ready to get slain. Loops the whole pieceList to see if 
			// any of the opponents pieces is in the correct position for a jump over it. 
			
			for (CheckersPieces piece: pieceList) {
				
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
			return;
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