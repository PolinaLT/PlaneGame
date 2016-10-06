package controller;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadPoolExecutor;

import javax.swing.JFrame;

import model.GameStatus;
import model.StartGamePanel;

public class Controller {
	private StartGamePanel gamePanel;
	
	public Controller() {
		
	}
	
	public void startGame(JFrame gameFrame, int level) {
		gamePanel = new StartGamePanel();
		gameFrame.add(gamePanel);
		
		gamePanel.startGame(level);
	}
	
	public GameStatus report() {
		 return gamePanel.report();
	}
	
	public void nextGame(int level) {
		gamePanel.nextGame(level);
	}

	public int bonusReport() {
		return gamePanel.bonusReport();
	}
}