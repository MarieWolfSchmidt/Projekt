import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;


public class CoreControl extends JPanel {

	public int n=8;
	public int r=600;
	public int m=r/n;
	public int l=2*m;

        public CoreControl() {

        }
   
        protected void paintComponent(Graphics g) {
            
        		int cellX = 0;
                int cellY = 0;
                for(int i=cellY; i<=r-m; i+=l){
             	   for(int j=cellX; j<=r-m;j+=l){
             		  g.setColor(Color.GRAY);
                      g.fillRect(j, i, m, m);
                      g.setColor(Color.WHITE);
                      g.fillRect(j+m, i, m, m); 
                      g.setColor(Color.WHITE);
                      g.fillRect(j, i+m, m, m);
                      g.setColor(Color.GRAY);
                      g.fillRect(j+m, i+m, m, m);
                      
             	   }
                }
 
 
            g.setColor(Color.BLACK);
            g.drawRect(0, 0, 600, 600);
            
            for (int i = 0; i <= 600; i += m) {
                g.drawLine(i, 0, i, 600);
            }

            for (int i = 0; i <= 600; i += m) {
                g.drawLine(0, i, 600, i);
            }
            
            /*g.setColor(Color.RED);
            g.fillOval(10, 10, 30, 30);
            g.setColor(Color.BLACK);
            g.fillOval(360, 360, 30, 30);  */  	
        }

        public void fillCell() {
            repaint();
            
        }
}