package View;
import java.awt.Color;

import javax.swing.JFrame;


import Model.CheckersModel;



public class CheckersView extends JFrame {
	
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
		
		menu.titlebar(this);
		
		menu.menu();
	}
	
	public void showIt(String title){
		 this.setTitle(title);
		 this.setVisible(true);
	}
	
	
	
}