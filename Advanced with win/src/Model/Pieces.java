package Model;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;



public class Pieces  {

	protected int color;
	public Point point;
	
	
	
	public Color playerOne;
	public Color playerTwo;
	
	public Pieces(int color, Point point, Color p1, Color p2) {
		this.color = color;
		this.point = point;
		this.playerOne = p1;
		this.playerTwo = p2;
	}
	
	public void drawPieces(Graphics g, Point point, int squareSize) {
		int x = (point.x - squareSize / 2);
		int y = (point.y - squareSize / 2);
		if(color == 1){
		g.setColor(playerOne);
        g.fillOval(x, y, squareSize, squareSize); 
		}
		else if(color == 2){
        g.setColor(playerTwo);
        g.fillOval(x, y, squareSize, squareSize);
		}
	}

	public static boolean contains(int x, int y, Point point, int squareSize) {
	      return (point.x - x) * (point.x - x) + (point.y - y) * (point.y - y) < squareSize / 2 * 
	    		  squareSize / 2;
	   }
	
	

}
