package BaseGame;

import java.awt.Color;
//import java.awt.EventQueue;
import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.Point;
//import java.util.ArrayList;
//import java.util.List;
import javax.swing.JPanel;
//import javax.swing.UIManager;
//import javax.swing.UnsupportedLookAndFeelException;

    public class Grid extends JPanel{

        public Grid() {
           
        }
   
        
        protected void paintComponent(Graphics g) {
           
            	int cellX = 0;
                int cellY = 0;
                for(int i=cellY; i<=350; i+=100){
             	   for(int j=cellX; j<=350;j+=100){
             		  g.setColor(Color.GRAY);
                      g.fillRect(j, i, 50, 50);
             	   }
                }
                for(int i=cellY+50; i<=360; i+=100){
             	   for(int j=cellX+50; j<=360;j+=100){
             		  g.setColor(Color.GRAY);
                      g.fillRect(j, i, 50, 50);
             	   }
                }
               
               
            
            g.setColor(Color.BLACK);
            g.drawRect(0, 0, 400, 400);
            
            for (int i = 0; i <= 400; i += 50) {
                g.drawLine(i, 0, i, 400);
            }

            for (int i = 0; i <= 400; i += 50) {
                g.drawLine(0, i, 400, i);
            }
            
            g.setColor(Color.RED);
            g.fillOval(10, 10, 30, 30);
            g.setColor(Color.BLACK);
            g.fillOval(360, 360, 30, 30);     	
        }

        public void fillCell(int x, int y) {
            repaint();
            
        }

    }

   

