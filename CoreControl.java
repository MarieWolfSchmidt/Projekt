import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class CoreControl {

    public static class Grid extends JPanel {

        private List<Point> fillCells;
  

        public Grid() {
            fillCells= new ArrayList<>(50);
        }
   
        
        protected void paintComponent(Graphics g) {
            for (Point fillCell : fillCells) {
            	int cellX = 10 + (fillCell.x);
                int cellY = 10 + (fillCell.y);
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
               
               
            }
            g.setColor(Color.BLACK);
            g.drawRect(10, 10, 400, 400);
            
            for (int i = 10; i <= 410; i += 50) {
                g.drawLine(i, 10, i, 410);
            }

            for (int i = 10; i <= 410; i += 50) {
                g.drawLine(10, i, 410, i);
            }
            
            g.setColor(Color.RED);
            g.fillOval(20, 20, 30, 30);
            g.setColor(Color.BLACK);
            g.fillOval(370, 370, 30, 30);     	
        }

        public void fillCell(int x, int y) {
            fillCells.add(new Point(x, y));
            repaint();
            
        }

    }

    public static void main(String[] a) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                Grid grid = new Grid();
                JFrame window = new JFrame();
                window.setSize(450, 450);
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.add(grid);
                window.setVisible(true);
               
                grid.fillCell(0, 0);
               
             

            }
        });
    }
}