package Menu;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.BorderLayout;

public class MenuGUI{

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuGUI window = new MenuGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MenuGUI() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(500, 500, 400, 527);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblMenu = new JLabel("DAM");
		lblMenu.setBounds(148, 28, 171, 33);
		frame.getContentPane().add(lblMenu);
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.setBounds(99, 99, 171, 41);
		frame.getContentPane().add(btnNewGame);
		
		JButton btnSettings = new JButton("Settings");
		btnSettings.setBounds(99, 168, 171, 41);
		frame.getContentPane().add(btnSettings);
		
		JButton btnExitGame = new JButton("Exit Game");
		btnExitGame.setBounds(99, 237, 171, 41);
		frame.getContentPane().add(btnExitGame);
		
		JLabel lblAf = new JLabel("Af ...");
		lblAf.setBounds(15, 341, 115, 33);
		frame.getContentPane().add(lblAf);
	}
}
