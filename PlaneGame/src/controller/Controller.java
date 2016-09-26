package controller;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadPoolExecutor;

import javax.swing.JFrame;

import model.GameStatus;
import model.StartGamePanel;

public class Controller {
	private StartGamePanel gamePanel;
	private GameStatus status = GameStatus.PLAY;
	
	public Controller() {
		
	}
	
	public void startGame(JFrame gameFrame, int level) {
		gamePanel = new StartGamePanel(level);
		gameFrame.add(gamePanel);
		gamePanel.drawCloud();
	}
	
	public GameStatus report() {
		 return gamePanel.report();
	}

	
}
