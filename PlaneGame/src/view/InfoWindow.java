package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import model.GameStatus;

public class InfoWindow {
	private JFrame infoFrame = new JFrame();
	private JPanel infoPanel = new JPanel();
	private JLabel infoLabel;
	
	public InfoWindow(GameStatus gameStatus) {
		
		switch (gameStatus) {
		case WIN:
			infoLabel = new JLabel("WINNER!");
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
