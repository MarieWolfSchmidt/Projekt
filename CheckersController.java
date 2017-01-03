import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckersController implements ActionListener {

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
	}

