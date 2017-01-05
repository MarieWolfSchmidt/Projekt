

public class CheckersDriver {
	public static void main(String[] args) {

	
		if ( args.length == 0){
			CheckersView cfr = new CheckersView (8);
			cfr.showIt("Dam"); 
			return;		
		}
		
		int size = Integer.parseInt(args[0]);
		
		if (size<3||size>100) {
			System.out.println("Error. Please choose a number between 3 and 100");
			
		} else {
			CheckersView cfr = new CheckersView (size);
			cfr.showIt("Dam"); 
		}
		
	}
}