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

public class MainWindow extends JPanel {
	private JFrame mainFrame = new JFrame("Plane");
	private JPanel mainPanel = new JPanel();
	private JPanel menuPanel = new JPanel();
	private JLabel levelLabel;
	private JLabel bonusLabel;
	
	public MainWindow() throws IOException {
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainFrame.setSize(500, 200);
		mainFrame.setLocation(150, 0);
		
		levelLabel = new JLabel("Уровень: " + Integer.toString(LevelInfo.getLevel()));
		bonusLabel = new JLabel("Цель уровня: " + Integer.toString(LevelInfo.getLevel()));
		
		mainFrame.add(mainPanel);
		mainPanel.add(menu());
//		mainPanel.add(levelLabel);
//		mainPanel.add(bonusLabel);
		
		
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
				try {
					GameWindow gameWindow = new GameWindow();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		results.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					LevelReportWindow window = new LevelReportWindow();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		restart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					LevelInfo.restartGame();
//					try {
//						panelRepaint();
//					} catch (IOException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		return menuPanel;
	}
	
	private void panelRepaint() throws IOException {
		mainPanel.remove(levelLabel);
		mainPanel.remove(bonusLabel);
		levelLabel = new JLabel("Уровень: " + Integer.toString(LevelInfo.getLevel()));
		bonusLabel = new JLabel("Цель уровня: " + Integer.toString(LevelInfo.getLevel()));
		mainPanel.add(levelLabel);
		mainPanel.add(bonusLabel);
		repaint();
	}


	public static void main(String[] args) throws IOException {
		MainWindow mainWindow = new MainWindow();
	}
}
