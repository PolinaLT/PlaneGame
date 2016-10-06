package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.RepaintManager;
import javax.swing.Timer;
import javax.swing.WindowConstants;

import org.omg.PortableServer.ServantRetentionPolicyValue;

import view.LevelInfo;
import controller.Controller;
import model.GameStatus;
import model.StartGamePanel;

public class GameWindow extends JFrame {
	private static JFrame gameFrame = new JFrame("Let's play!");
	private Controller controller = new Controller();
	private GameStatus gameStatus = GameStatus.PLAY;
	private int bonusStatus;
	private JLabel infoLabel;
	private JPanel infoPanel = new JPanel();
	private JFrame infoFrame = new JFrame("Info");
	private JButton nextButton = new JButton("Играть дальше");
	private TimerHandler newTimer;
	private JLabel levelLabel = new JLabel();
	
	public GameWindow() throws IOException {
		gameFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		gameFrame.setSize(1000, 700);
		
		controller.startGame(gameFrame, LevelInfo.getLevel());
		
		newTimer = new TimerHandler();
		gameFrame.setVisible(true);
	}
	
	public static JFrame getFrame() {
		return gameFrame;
	}


	private void newGame() throws IOException {
		gameStatus = GameStatus.PLAY;
		controller.nextGame(LevelInfo.getLevel());
		newTimer.restart();
	}
	
	
	class TimerHandler {
		private Timer timer;
		
		public TimerHandler() {
			timer();
			timer.start();
		}
		
		private void timer() {
			timer = new Timer(1, new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gameStatus = controller.report();
					bonusStatus = controller.bonusReport();
					if (gameStatus != GameStatus.PLAY) {
						try {
							Info info = new Info();
							timer.stop();
							return;
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			});
		}
		
		public void restart() {
			timer.restart();
		}
	}
	
	class Info {
		public Info() throws IOException {
			switch (gameStatus) {
			case WIN:
				infoLabel = new JLabel("Победа!");
				levelLabel = new JLabel("Цель:" + Integer.toString(bonusStatus) + "/" + Integer.toString(LevelInfo.getLevel()));
				LevelInfo.setLevelBonus(bonusStatus);
				//setBonus
				System.out.println(bonusStatus);
				break;
			case LOSS:
				infoLabel = new JLabel("LOSER!");
				levelLabel = new JLabel(" ");
				break;
			default:
				break;
			}
			
			
			infoFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			infoFrame.setSize(300, 100);
			
			infoPanel.removeAll();
			infoPanel.repaint();
			
			infoFrame.add(infoPanel);
			infoPanel.add(infoLabel, BorderLayout.NORTH);
			infoPanel.add(levelLabel);
			
			
			infoPanel.add(nextButton, BorderLayout.SOUTH);
			
			nextButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						newGame();
						infoFrame.setVisible(false);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			
			infoFrame.setVisible(true);
		}
	}
}
