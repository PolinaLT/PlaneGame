package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class MainWindow extends JPanel {
	JFrame mainFrame = new JFrame("Plane");
	JPanel mainPanel = new JPanel();
	JPanel menuPanel = new JPanel();
	
	public MainWindow() {
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainFrame.setSize(300, 200);
		mainFrame.setLocation(150, 0);
		
		mainFrame.add(mainPanel);
		mainFrame.add(menu());
		
		
		mainFrame.setVisible(true);
	}
	
	public JPanel menu() {
		JButton newGame = new JButton("Играть");
		JButton results = new JButton("Статистика");
		JButton exit = new JButton("Выход");
		
		JButton[] buttons = {newGame, results, exit};
		
		for(JButton i : buttons)  {
			menuPanel.add(i);
		}
		
		newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.setVisible(false);
				GameWindow gameWindow = new GameWindow();
				
			}
		});
		
		return menuPanel;
	}


	public static void main(String[] args) {
		MainWindow mainWindow = new MainWindow();
	}
}
