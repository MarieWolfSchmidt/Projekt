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
	
	private int r=600;
	private int squaredim;
	private int l;
	private int n;
	private int str;
	private Helpcreate b;
	private Point old;
	private int deltax, deltay;
	private boolean move = false;
	private List<Helpcreate> array;
	private boolean jump = false;
	private boolean playerOne = true;
	private boolean playerTwo = false;
	

        public CoreControl(int n) {
        	this.n=n;
        	this.squaredim=r/n;   	
        	this.l=2*squaredim;
        	this.str=(squaredim/3)*2;
        	array=new ArrayList<>();
        	add(1,1,1);
            add(2,n,n);
        	addMouseListener(this);
        	addMouseMotionListener(this);
        }
        
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
        		int cellX = 0;
                int cellY = 0;
                for(int i=cellY; i<=r-squaredim; i+=l){
             	   for(int j=cellX; j<=r-squaredim;j+=l){
             		  g.setColor(Color.GRAY);
                      g.fillRect(j, i, squaredim, squaredim);
                      g.setColor(Color.WHITE);
                      g.fillRect(j+squaredim, i, squaredim, squaredim); 
                      g.setColor(Color.WHITE);
                      g.fillRect(j, i+squaredim, squaredim, squaredim);
                      g.setColor(Color.GRAY);
                      g.fillRect(j+squaredim, i+squaredim, squaredim, squaredim);
                      
             	   }
                }
 
            g.setColor(Color.BLACK);
            g.drawRect(0, 0, 600, 600);
            
            for (int i = 0; i < 600; i += squaredim) {
                g.drawLine(i, 0, i, 600);
            }

            for (int i = 0; i < 600; i += squaredim) {
                g.drawLine(0, i, 600, i);
            }	
            
           	for(Helpcreate b: array){
           		b.color.drawPieces(g,b.p,str);
           	}
           	
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
			int x = e.getX();
             int y = e.getY();

             // Locate positioned checker under mouse press.

            	for(Helpcreate b: array){
                if (CheckersPieces.contains(x, y, b.p, str)) {
                	if(playerOne){
                		if(b.i==1){
                   this.b = b;
                   old=b.p.getLocation();
                   deltax = x - b.p.x;
                   deltay = y - b.p.y;
                   move = true;
                  playerOne=false;
                  playerTwo=true;
                	}
                }else if(playerTwo){
                	if(b.i==2){
                		this.b = b;
                        old=b.p.getLocation();
                        deltax = x - b.p.x;
                        deltay = y - b.p.y;
                        move = true;
                        playerOne=true;
                        playerTwo=false;
                	}
                }
                	
			 return;
		}
     	}
                           
			
	}

		@Override
		public void mouseReleased(MouseEvent e) {
		//husk jump till false
			
           if (move)
               move = false;
            else
               return;

            // Placerer brikken i midten
    
           int delta1=squaredim*2-(str/2);
           int delta2=squaredim*3-delta1;
           int delta3=squaredim*2-delta1;
           int deltablackdown=squaredim-(str/2);
           int deltablackup=squaredim*2-deltablackdown;         
           
           b.p.x=(e.getX()-deltax)/squaredim * squaredim + squaredim / 2;
           b.p.y=(e.getY()-deltay)/squaredim * squaredim + squaredim / 2;
           
           int deltay=b.p.y-old.y;
           int deltax=b.p.x-old.x;
           int deltax2=old.x-b.p.x;
           int deltay2=old.y-b.p.y;
     
       

            for(Helpcreate b: array){
           
             if(jump==true){ 
            	 if (b != this.b && b.p.x==this.b.p.x && b.p.y==this.b.p.y){
              	   this.b.p = old;
              		if(playerOne){
            			playerOne=true;
            			playerTwo=false;
            		}else if(playerTwo){
            			playerOne=false;
            			playerTwo=true;	
            		}
              	   }
              	if(this.b.p.x<0 || this.b.p.x>600 ||this.b.p.y<0 || this.b.p.y>600){
              	   this.b.p = old;
              		if(playerOne){
            			playerOne=false;
            			playerTwo=true;
            		}else if(playerTwo){
            			playerOne=true;
            			playerTwo=false;	
            		}
                 }else if(this.b.i==1 && ((deltay>delta1+squaredim || deltax>delta1+squaredim  ||deltax2>delta2+squaredim  || deltay2>delta3+squaredim ) || ((deltay<deltablackdown+squaredim  && deltax2>squaredim-deltablackdown+squaredim )  || (deltay<deltablackdown+squaredim  && deltax>deltablackdown+squaredim ))|| ((deltax<deltablackdown+squaredim ) && (deltax2<squaredim-deltablackdown+squaredim )))){
              	   this.b.p = old; 
              		if(playerOne){
            			playerOne=false;
            			playerTwo=true;
            		}else if(playerTwo){
            			playerOne=true;
            			playerTwo=false;	
            		}
          
                 }else if(this.b.i==2 && ((deltax>delta1+squaredim|| deltax2>delta2+squaredim || deltay>deltablackdown+squaredim  || deltay2>deltablackup+squaredim  )||((deltay2<deltablackdown+squaredim  && deltax2>squaredim-deltablackdown+squaredim)  || (deltay2<deltablackdown+squaredim  && deltax>deltablackdown+squaredim )) || ((deltax2<deltablackdown+squaredim ) && (deltax<squaredim-deltablackdown+squaredim )))){
              	   this.b.p = old;
              		if(playerOne){
            			playerOne=false;
            			playerTwo=true;
            		}else if(playerTwo){
            			playerOne=true;
            			playerTwo=false;	
            		}
              	
                 }else if (this.b.i==1){
              		array.remove(b.i);
              		
              	}else if(this.b.i==2){
              		array.remove(b);
              	}
              	
              	jump=false;
              	
            	repaint();
            	
            	return;
            	 
              	
             }else {
            	if (b != this.b && b.p.x==this.b.p.x && b.p.y==this.b.p.y){
            	   this.b.p = old;
            		if(playerOne){
            			playerOne=true;
            			playerTwo=false;
            		}else if(playerTwo){
            			playerOne=false;
            			playerTwo=true;	
            		}
            	}
            	if(this.b.p.x<0 || this.b.p.x>600 ||this.b.p.y<0 || this.b.p.y>600){
            	   this.b.p = old;
            		if(playerOne){
            			playerOne=false;
            			playerTwo=true;
            		}else if(playerTwo){
            			playerOne=true;
            			playerTwo=false;	
            		}
               }else if(this.b.i==1 && ((deltay>delta1 || deltax>delta1 ||deltax2>delta2 || deltay2>delta3) || ((deltay<deltablackdown && deltax2>squaredim-deltablackdown)  || (deltay<deltablackdown && deltax>deltablackdown))|| ((deltax<deltablackdown) && (deltax2<squaredim-deltablackdown)))){
            	   this.b.p = old;
            		if(playerOne){
            			playerOne=false;
            			playerTwo=true;
            		}else if(playerTwo){
            			playerOne=true;
            			playerTwo=false;	
            		}
               }else if(this.b.i==2 && ((deltax>delta1 || deltax2>delta2 || deltay>deltablackdown || deltay2>deltablackup )||((deltay2<deltablackdown && deltax2>squaredim-deltablackdown)  || (deltay2<deltablackdown && deltax>deltablackdown)) || ((deltax2<deltablackdown) && (deltax<squaredim-deltablackdown)))){
            	   this.b.p = old; 
            		if(playerOne){
            			playerOne=false;
            			playerTwo=true;
            		}else if(playerTwo){
            			playerOne=true;
            			playerTwo=false;	
            		}
               }
            
            	
            repaint();
           
         }
             searchBoard();
             return;  
            }
            
            
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

			{
                 if (move)
                 {
                    // Update location of checker center
                    b.p = e.getPoint();
                    repaint();
                	 }
                 }
              }


		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		public void add(int i, int row, int col){
			Helpcreate pieces= new Helpcreate();
				pieces.i=i;
				pieces.color=new CheckersPieces(i);
				pieces.p=new Point((col-1)*squaredim+squaredim/2,(row-1)*squaredim+squaredim/2);
		        array.add(pieces);
		     
			}
		public void searchBoard(){
			for(Helpcreate b: array){
				
			if(this.b.i==1 && ((this.b.p.x+squaredim)== b.p.x && (this.b.p.y+squaredim)==b.p.y)){
				jump = true;
				break;
				
			}else if (this.b.i==2 && ((this.b.p.x-squaredim)==b.p.x && (this.b.p.y-squaredim)==b.p.y)){
				jump = true;
				break;
			}else{
				jump= false;
			
			}	
			
			}
			
			return;
		}
		
		public void player(){
		
			
			
			
		}
		
		private class Helpcreate{
			public int i;
			public CheckersPieces color;
			public Point p;
			
		}
		
			
}
