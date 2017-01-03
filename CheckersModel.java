
public class CheckersModel {

	private CheckersView view;
	
	public CheckersModel(CheckersView view){
		this.view=view;
	}
	
	public void game() {
		this.view.game();
	}
	
	public void menu() {
		this.view.menu();
	}
}
