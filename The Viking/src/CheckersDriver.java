
public class CheckersDriver {
	public static void main(String[] args) {
		
//		if ((Integer.parseInt(args[0]) < 3) || (Integer.parseInt(args[0]) > 100)) {
//			System.out.println("Start value has the wrong input - n >= 3 && n <=100");
//		} else {
//			CheckersView cfr = new CheckersView (args[0]);
//			cfr.showIt("Dam"); 
//		}

		CheckersView cfr = new CheckersView("10");
		cfr.showIt("Dam");
		
	}
}