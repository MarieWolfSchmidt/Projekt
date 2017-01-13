package Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import Model.CheckersModel;

public class CheckersController implements ActionListener, MouseListener, MouseMotionListener {

	private CheckersModel checkersModel;
	
	public CheckersController(CheckersModel checkerModel){
		this.checkersModel = checkerModel;
		
	}
	
	public void actionPerformed(ActionEvent e) {
			 
	String command = e.getActionCommand();
		if (command.equals("New Game")) {
			checkersModel.removePieces();
			checkersModel.turnReset();
			checkersModel.game();
		} else if (command.equals("High Score")) {
			checkersModel.highScore();
		} else if (command.equals("Exit")) {
			System.exit(0);
		} else if (command.equals("Back To Menu")) {
			checkersModel.menu();
		} else if(command.equals("Music On/Off")){
			checkersModel.toggleMusic();
		} else if (command.equals("Settings")){
			checkersModel.settings();
		} else if (command.equals("Pink theme")){
			checkersModel.setTheme("Pink theme");
		} else if (command.equals("Default theme")){
			checkersModel.setTheme("Default theme");
		} else if (command.equals("Rules")){
			checkersModel.rules();
		} else if (command.equals("How To Play")){
			checkersModel.howTo();
		}
		else {
			System.out.println("ERROR: Unexpected ActionCommand");
		}
			 	 
	}
		 
	@Override
	public void mousePressed(MouseEvent e) {
		checkersModel.mousePressed(e);
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		checkersModel.mouseDragged(e);
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		checkersModel.mouseReleased(e);			
	}

	public void mouseMoved(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}		
		
}

