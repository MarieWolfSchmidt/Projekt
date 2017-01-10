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
			checkersModel.game();
		} else if (command.equals("High Score")) {
			// score();
		} else if (command.equals("Exit")) {
			System.exit(0);
		} else if (command.equals("Back To Menu")) {
			checkersModel.removePieces();
			checkersModel.menu();
		} else {
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

