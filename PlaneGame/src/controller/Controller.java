package controller;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadPoolExecutor;

import javax.swing.JFrame;

import model.GameStatus;
import model.Level;
import model.StartGamePanel;

public class Controller {
	private StartGamePanel gamePanel;
	private Level level;
	
	public Controller() {
		level = new Level();
	}
	
	public int getLevel() {
		return level.getLevel();
	}
	
	public void stopGame() {
		gamePanel.stopGame();
	}
	
	public List<String> getLevelReport() {
		return level.levelReport();
	}
	
	public void startGame(JFrame gameFrame) {
		gamePanel = new StartGamePanel();
		gameFrame.add(gamePanel);
		
		gamePanel.startGame();
	}
	
	public GameStatus report() {
		 return gamePanel.report();
	}
	
	public void nextGame() {
		gamePanel.nextGame();
	}

	public void restartHistory() {
		level.restartGame();
	}
	
	public int bonusReport() {
		return gamePanel.bonusReport();
	}
}