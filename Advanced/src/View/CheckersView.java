package View;
import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Model.CheckersModel;



public class CheckersView extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected CheckersModel checker;
	public Menus menu;
	public int cmdLineArgument;
	
	public CheckersView(int cmdLineArgs){
		this.cmdLineArgument = cmdLineArgs;
		checker = new CheckersModel(this);
		menu = new Menus(this);
		this.setBounds(200, 60, 1280, 880);
		this.getContentPane().setBackground(Color.LIGHT_GRAY);
		this.getContentPane().setLayout(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		
		ImageIcon imgic = new ImageIcon("bakk.png");
		Image img = imgic.getImage();
		
		
		this.setContentPane(new JLabel(new ImageIcon(img)));
		menu.titlebar(this);
		
		menu.menu();
	}
	
	public void showIt(String title){
		 this.setTitle(title);
		 this.setVisible(true);
	}
	
	
		
}