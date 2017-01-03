
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
//import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
//import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
//import javax.swing.Action;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

public class menu {

	private JFrame frame;
	private JPanel panel;


	public static void main(String[] args) {
		menu window = new menu();
		window.frame.setVisible(true);
	}
	

	public menu() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.GRAY);
		frame.getContentPane().setLayout(null);
		initialize();
frame.setBounds(100, 100, 566, 421);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private void initialize() {
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(6, 6, 554, 387);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("New Game");
		btnNewButton.setBounds(197, 69, 155, 48);
		panel.add(btnNewButton);
		btnNewButton.addActionListener(new ButtonListener());
		
		JLabel lblNewLabel = new JLabel("Nu skal vi spille Dam!");
		lblNewLabel.setBounds(168, 6, 209, 25);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		panel.add(lblNewLabel);
		
		JButton settingButton = new JButton("Settings");
		settingButton.setBounds(197, 168, 155, 48);
		panel.add(settingButton);
		settingButton.addActionListener(new ButtonListener());
		
		
		JButton exitButton = new JButton("Exit");
		exitButton.setBounds(197, 271, 155, 48);
		panel.add(exitButton);
		exitButton.addActionListener(new ButtonListener());
		
		
		frame.getContentPane().add(panel);
	}
	
	private class ButtonListener implements ActionListener{
		 public void actionPerformed(ActionEvent e) {
			 String command = e.getActionCommand();
			 if ("Settings".equals(command)) {
				 setting();
			 }
			 else if("Exit".equals(command)){
				 System.exit(0);
			 }
			 else if("New Game".equals(command)){
				 game();
			 }
		 }
	}
	
	private void game(){
		frame.getContentPane().removeAll();
		 frame.getContentPane().repaint();
			JPanel panel2 = new JPanel();
			panel2.setBackground(Color.WHITE);
			panel2.setBounds(6, 6, 372, 387);
			frame.getContentPane().add(panel2);
			panel2.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Spilleplade");
			lblNewLabel.setBounds(17, 39, 278, 125);
			panel2.add(lblNewLabel);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBounds(379, 6, 181, 387);
			frame.getContentPane().add(panel_1);
			panel_1.setLayout(null);
			
			JButton backButton = new JButton("Back To Menu");
			backButton.setBounds(33, 352, 117, 29);
			panel_1.add(backButton);
			backButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.getContentPane().removeAll();
					 frame.getContentPane().repaint();
					 initialize();
				}
			});
			
			JLabel lblDam = new JLabel("DAM");
			lblDam.setBounds(80, 6, 30, 16);
			panel_1.add(lblDam);
	}
	
	
	private void setting(){
		 frame.getContentPane().removeAll();
		 frame.getContentPane().repaint();
		 JPanel panel1 = new JPanel();
		 panel1.setBackground(Color.WHITE);
		 panel1.setBounds(6, 6, 554, 387);	
			frame.getContentPane().add(panel1);
			panel1.setLayout(null);
			
			JLabel lblNewLabel1 = new JLabel("Settings");
			lblNewLabel1.setBounds(233, 6, 76, 25);
			lblNewLabel1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
			panel1.add(lblNewLabel1);
			
		
			JTextField textField = new JTextField();
			textField.setText("8");
			textField.setBounds(356, 80, 151, 35);
			panel1.add(textField);
			textField.setColumns(10);
			
			JLabel lblNewLabel = new JLabel("Select the size of the game board");
			lblNewLabel.setBounds(22, 84, 262, 26);
			panel1.add(lblNewLabel);
			
			JButton backButton = new JButton("Back to menu");
			backButton.setBounds(22, 153, 175, 51);
			panel1.add(backButton);
			backButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.getContentPane().removeAll();
					 frame.getContentPane().repaint();
					 initialize();
				}
			});
			
			frame.getContentPane().add(panel1);
		
	}
	
}
