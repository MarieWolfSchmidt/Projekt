import java.awt.*;
import javax.swing.*;

public class BoardArray extends JFrame {
	
	public static int rows = 8;
	public static int columns = 8;
	public static Color BLACK = Color.BLACK;
	public static Color WHITE = Color.WHITE;
	
	static JPanel[][] tiles;
	
	public static void main (String[] args){
		
		BoardArray board = new BoardArray();
		int[][] printArray = board.fillArray(8);
		board.fillBoard(printArray);

//		board.printArray(printArray);
		
	}

	public void printArray(int[][] printArray) {
		
		for (int i = 0; i < printArray.length; i++) {
			for (int j = 0; j < printArray.length; j++) {
				System.out.print(printArray[i][j]);
			}
		}

	}
	
	public int[][] fillArray(int n) {
		
		// Very simple version that puts down one piece on each site. 
		// There is either a 0 for no piece.
		// Or a 1 for a piece. 
		
		int[][] pieceArray = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == 0 && j == 0) {
					pieceArray[i][j] = 1;
				} else if (i == n-1 && j == n-1) {
					pieceArray[i][j] = 1;
				} else {
					pieceArray[i][j] = 0;
				}
			}
		}
		
		return pieceArray;
	}
	
	public void fillBoard(int[][] pieceArray) {
		
		this.setSize(372, 387);
		this.setTitle("Checkers");
		Container pane = this.getContentPane();
		pane.setLayout(new GridLayout(rows, columns));
		
		tiles = new JPanel[rows][columns];
		
		Color temp;
		for (int i = 0; i < rows; i++){
			if ( i%2 == 0){
				temp = BLACK;
			} else {
				temp = WHITE;
			}
			for(int j = 0; j < columns; j++){
				
				JPanel panel = new JPanel();
				panel.setBackground(temp);
				tiles[i][j] = panel;
				if(temp.equals(BLACK)){
					temp = WHITE;
				} else {
					temp = BLACK;
				}
				pane.add(panel);
			}
		}
		
		this.setVisible(true);		
	}

}
