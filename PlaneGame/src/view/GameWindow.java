package view;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.omg.PortableServer.ServantRetentionPolicyValue;

import model.StartGamePanel;

public class GameWindow {
	JFrame gameFrame = new JFrame("Let's play!");
	
	public GameWindow() {
		gameFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		gameFrame.setSize(1000, 700);
		
		StartGamePanel gamePanel = new StartGamePanel();
		gameFrame.add(gamePanel);
		gamePanel.start();
		
		gameFrame.setVisible(true);
	}
}
