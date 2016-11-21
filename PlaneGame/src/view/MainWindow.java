package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import controller.Controller;

public class MainWindow extends JPanel {
	private JFrame mainFrame = new JFrame("Plane");
	private JPanel mainPanel = new JPanel();
	private JPanel menuPanel = new JPanel();
	private GameWindow gameWindow;
	private StatisticsWindow window;
	private InfoWindow infoWindow;
	
	public MainWindow() {
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainFrame.setSize(500, 100);
		mainFrame.setLocationRelativeTo(null);
		
		mainFrame.add(mainPanel);
		mainPanel.add(menu());
		
		
		mainFrame.setVisible(true);
	}
	
	public JPanel menu() {
		JButton newGame = new JButton("Играть");
		JButton results = new JButton("Статистика");
		JButton restart = new JButton("Сбросить прогресс");
		
		JButton[] buttons = {newGame, results, restart};
		
		for(JButton i : buttons)  {
			menuPanel.add(i);
		}
		
		newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameWindow = new GameWindow();	
			}
		});
		
		results.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					window = new StatisticsWindow();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		restart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				infoWindow = new InfoWindow();
			}
		});
		
		return menuPanel;
	}

	public static void main(String[] args) throws IOException {
		MainWindow mainWindow = new MainWindow();
	}
}
