import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class CheckersController implements ActionListener, MouseListener, MouseMotionListener {

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
				// score();
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
			int[] whatever = new int[2];
//			whatever = checkersModel.rowAndColumnClicked(e);
			checkersModel.rowAndColumnClicked(e);
			
//			for (int i = 0; i < 2; i++) {
//				System.out.println(whatever[i]);
//			}
//
//			System.out.println(e.getY());
			
		}
		
		// MOUSE PRESSES
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

		// MOUSE MOVEMENT
		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		 
} 