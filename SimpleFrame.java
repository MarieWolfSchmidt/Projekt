package ccs.SimpleFrame;
import javax.swing.JFrame;

public class SimpleFrame extends JFrame {
	
	public SimpleFrame(){
		
		this.setSize(300,300);
		this.setLocation(100,100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void showIt(){
		this.setVisible(true);
	}
	
	public void showIt(String title){
		this.setTitle(title);
		this.setVisible(true);
	}
	
	public void showIt(String title, int x, int y){
		this.setTitle(title);
		this.setSize(x,y);
		this.setVisible(true);
	}
	
	public void hideIt(){
		this.setVisible(false);
	}
	

}
