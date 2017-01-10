package Driver;
import View.CheckersView;

public class CheckersDriver {
	public static void main(String[] args) {
		
		// If no command line argument is supplied then 8 is called as a standard parameter.
		
		if ( args.length == 0) {
			CheckersView cfr = new CheckersView (8);
			cfr.showIt("Checkers"); 
			return;		
		}
		
		// Otherwise the view is instantiated with the command line argument as the n variable deciding the n x n of the board.
		
		if (Integer.parseInt(args[0]) < 3 || Integer.parseInt(args[0]) > 100) {
			System.out.println("Error. Please choose a number between 3 and 100");
		} else {
			CheckersView cfr = new CheckersView (Integer.parseInt(args[0]));
			cfr.showIt("Checkers"); 
		}	
	}
	
}