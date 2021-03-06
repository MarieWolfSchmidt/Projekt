import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;



public class CheckersPieces  {

	protected int color;
	public Point point;
	
	public CheckersPieces(int color, Point p){
		this.color=color;
		this.point=p;
	}
	
	public void drawPieces(Graphics g, Point p, int str){
		int x=(p.x-str/2);
		int y=(p.y-str/2);
		if(color==1){
		g.setColor(Color.RED);
        g.fillOval(x, y, str, str); 
		}
		else if(color==2){
        g.setColor(Color.BLACK);
        g.fillOval(x, y, str, str);
		}
	}

	public static boolean contains(int x, int y, Point p, int str)
	   {
	      return (p.x - x) * (p.x - x) + (p.y - y) * (p.y - y) < str / 2 * 
	             str / 2;
	   }
	
	
	
}
