import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;


public class CoreControl extends JComponent implements MouseListener, MouseMotionListener {

	public int n=8;
	public int r=600;
	public int m=r/n;
	public int l=2*m;
	public int str=(m/3)*2;
	public int p1=m/2-(str/2);
	public int p11=m/2-(str/2);
	public int p2=600-m/2-(str/2);

        public CoreControl() {
        	//addMouseMotionListener(this);
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
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
            
           cirkelred(g);
           cirkelblack(g);
           
            
        }
        
        public void cirkelred(Graphics c){
        	c.setColor(Color.RED);
            c.fillOval(p1, p11, str, str);
            addMouseMotionListener(this);
        }
        public void cirkelblack(Graphics c){
        	 c.setColor(Color.BLACK);
             c.fillOval(p2, p2, str, str);	
             addMouseMotionListener(this);
        }
       
        
        
        public void fillCell() {
            repaint();
            
        }

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			p2 = e.getX();
		    p2 = e.getY();		
		    repaint();
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
}