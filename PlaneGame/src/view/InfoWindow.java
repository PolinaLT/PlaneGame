package view;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import controller.Controller;
import model.GameStatus;

public class InfoWindow {
	private JFrame infoFrame = new JFrame();
	private JPanel infoPanel = new JPanel();
	private JLabel infoLabel;
	
	public InfoWindow(GameStatus gameStatus, int bonusStatus) throws IOException {
		
		switch (gameStatus) {
		case WIN:
			infoLabel = new JLabel("WINNER!");
			LevelWindow.setLevelBonus(bonusStatus);
			//setBonus
			System.out.println(bonusStatus);
			break;
		case LOSS:
			infoLabel = new JLabel("LOSER!");
			break;
		default:
			break;
		}
		
		infoFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		infoFrame.setSize(300, 100);
				
		infoFrame.add(infoPanel);
		infoPanel.add(infoLabel, BorderLayout.CENTER);
		
		infoFrame.setVisible(true);
	}
	
}
