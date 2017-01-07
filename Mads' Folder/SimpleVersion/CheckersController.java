import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CheckersController implements ActionListener, MouseListener {

	private CheckersModel checkersModel;
	
	public CheckersController(CheckersModel checkerModel){
		this.checkersModel = checkerModel;
	}
	
		 public void actionPerformed(ActionEvent e) {
			 
			 String command = e.getActionCommand();
			 if (command.equals("New Game")) {
				 checkersModel.game();
			 }
			 else if(command.equals("High Score")){
				
			 }
			 else if(command.equals("Exit")){
				 System.exit(0);
			 }
			 else if(command.equals("Back To Menu")){
				 checkersModel.menu();
			 }
			
			 else{
				 System.out.println("ERROR: Unexpected ActionCommand");
			 }
		 }


		@Override
		public void mouseClicked(MouseEvent e) {
			
			// The mouse controls the game.
			
			checkersModel.rowAndColumnClicked(e);
			
		}
		
		// Not used.
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		 
} 