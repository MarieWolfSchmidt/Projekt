package board;

import java.awt.*;
import javax.swing.*;

public class BoardArray extends JFrame {
	
	public static int rows = 8;
	public static int columns = 8;
	public static Color col1 = Color.BLACK;
	public static Color col2 = Color.WHITE;
	
	static JPanel[][] tiles;
	
	//private JFrame frmCheckers;
	
	public static void main (String[] args){
		
		BoardArray board = new BoardArray();
		board.fillArray();
		
	}

	public void fillArray() {
		
//		JFrame checkerBoard = new JFrame();
		this.setSize(1107, 945);
		this.setTitle("Checkers");
		Container pane = this.getContentPane();
		pane.setLayout(new GridLayout(rows, columns));
		
		tiles = new JPanel[rows][columns];
		
		Color temp;
		for (int i = 0; i < rows; i++){
			if ( i%2 == 0){
				temp = col1;
			} else {
				temp = col2;
			}
			for(int j = 0; j < columns; j++){
				
				JPanel panel = new JPanel();
				panel.setBackground(temp);
				tiles[i][j] = panel;
				if(temp.equals(col1)){
					temp = col2;
				} else {
					temp = col1;
				}
				pane.add(panel);
			}
		}
		
		this.setVisible(true);		
	}
	
//	public void Checkersprog() {
//		initialize();
//	}
	
//	private void initialize() {
//		frmCheckers = new JFrame();
//		frmCheckers.setTitle("Checkers");
//		frmCheckers.setBounds(100, 100, 800, 800);
//		frmCheckers.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frmCheckers.getContentPane().setLayout(null);
//	}

}
