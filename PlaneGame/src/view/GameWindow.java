package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.RepaintManager;
import javax.swing.Timer;
import javax.swing.WindowConstants;

import org.omg.PortableServer.ServantRetentionPolicyValue;

import controller.Controller;
import model.GameStatus;
import model.StartGamePanel;

public class GameWindow extends JFrame {
	private JFrame gameFrame = new JFrame("Let's play!");
	private Controller controller = new Controller();
	private GameStatus gameStatus = GameStatus.PLAY;
	private int bonusStatus;
	private JLabel infoLabel;
	private JPanel infoPanel = new JPanel();
	private JFrame infoFrame = new JFrame("Info");
	private JButton nextButton = new JButton("Играть дальше");
	private TimerHandler newTimer;
	private JLabel levelLabel = new JLabel();
	private JButton close = new JButton("Меню");
	private Info info;
	
	public GameWindow() {
		gameFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		gameFrame.setSize(1000, 700);
		gameFrame.setLocationRelativeTo(null);
		controller.startGame(gameFrame);
		
		newTimer = new TimerHandler();
		
		gameFrame.addWindowListener(new WindowListener() {
			public void windowOpened(WindowEvent e) {
			}
			
			public void windowIconified(WindowEvent e) {
			}
			
			public void windowDeiconified(WindowEvent e) {
			}
			
			public void windowDeactivated(WindowEvent e) {				
			}
			
			public void windowClosing(WindowEvent e) {
			}
			
			public void windowClosed(WindowEvent e) {
				controller.stopGame();
			}
			
			public void windowActivated(WindowEvent e) {
			}
		});
		
		gameFrame.setVisible(true);
	}
	
	private void newGame() {
		gameStatus = GameStatus.PLAY;
		controller.nextGame();
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
							info = new Info();
							timer.stop();
							return;
						} catch (IOException e1) {
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
				levelLabel = new JLabel("Цель:" + Integer.toString(bonusStatus) + "/" + Integer.toString(controller.getLevel()));
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
			infoFrame.setSize(400, 100);
			infoFrame.setLocationRelativeTo(null);
			
			infoPanel.removeAll();
			infoPanel.repaint();
			
			infoFrame.add(infoPanel);
			infoPanel.add(infoLabel, BorderLayout.NORTH);
			infoPanel.add(levelLabel);
			
			
			infoPanel.add(nextButton, BorderLayout.SOUTH);
			infoPanel.add(close, BorderLayout.SOUTH);
			
			nextButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
						newGame();
						infoFrame.setVisible(false);
				}
			});
			
			close.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					infoFrame.setVisible(false);
					gameFrame.setVisible(false);
				}
			});
			
			infoFrame.setVisible(true);
		}
	}
}
