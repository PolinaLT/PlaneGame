package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.lang.management.PlatformLoggingMXBean;
import java.util.List;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


public class StartGamePanel extends JPanel {
	private BufferedImage image;
	private Plane plane = new Plane();
	private Image planeImage;
	private Image barrierImage;
	private GameStatus gameStatus = GameStatus.PLAY;
	private int level;
	private int speedUp = 0;
	private GameObjectHandler handler;
	private List<GameObject> bonusList;
	private List<GameObject> stoneList;
	private List<GameObject> lightningList;
	private TimerHandler newTimer;
	private List<GameObject> whizbangList;
	
	
	public StartGamePanel() {
		
		image = new BufferedImage(1000, 700, BufferedImage.TYPE_4BYTE_ABGR);
		KeyHandler listener = new KeyHandler();
		addKeyListener(listener);
		setFocusable(true);		
	}
	
	public int bonusReport() {
		return GameObjectHandler.bonusReport();
	}
	
	
	public GameStatus report() {
		
		return gameStatus;
	}
	
	public void start() {
        Graphics g = image.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1000, 700);
    }
	
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(10.0f));
        g2.drawImage(this.image, 0, 0, null);
    }
	
	private void drawBarrier(List<GameObject> barrierList) {
		Graphics2D g = (Graphics2D) image.getGraphics();
		for (int i = 0; i < barrierList.size(); i++) {
			GameObject barrier = barrierList.get(i);
			barrierImage = barrier.getIcon().getImage();
			g.drawImage(barrierImage, barrier.getXLocation() - speedUp, barrier.getYLocation(), null);
			speedUp = 0;
		}
	}
	
	private void drawWhizbang() {
		Graphics2D g = (Graphics2D) image.getGraphics();
		whizbangList = handler.getWhizbangList();
		for (int i = 0; i < whizbangList.size(); i++) {
			g.drawRect(whizbangList.get(i).getXLocation(), whizbangList.get(i).getYLocation(), 5, 5);
		}
	}
	
	private void drawCloud() {
		repaint();
		
		handler = new GameObjectHandler(level);
		gameStatus = GameStatus.PLAY;
		
		bonusList = handler.createBonusList();
		stoneList = handler.createStoneList();
		lightningList = handler.createLightningList();
	}

	public void startGame(int level) {
		this.level = level;
		drawCloud();
		newTimer = new TimerHandler();
	}
	
	public void nextGame(int level) {
		this.level = level;
		drawCloud();
		newTimer.restart();
	}
	
	private void drawPlane() {
		start();
		Graphics2D g = (Graphics2D) image.getGraphics();
		g.setColor(Color.YELLOW);
			planeImage = plane.getIcon().getImage();
			g.drawImage(planeImage, plane.getXLocation(), plane.getYLocation(), null);
			repaint();
	}
	
	class TimerHandler {
		private Timer timer;
		
		public TimerHandler() {
			startTimer();
			timer.start();
		}		
		
		private void startTimer() {
			timer = new Timer(1, new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					start();
					
					if (gameStatus == GameStatus.PLAY) {
						handler.changeLocation();
						repaint();
					}
					else {
						timer.stop();
						return;
					}
					
					drawPlane();
					drawBarrier(bonusList);
					drawBarrier(stoneList);
					drawBarrier(lightningList);
					drawWhizbang();
					
					gameStatus = handler.checkStatus(plane.getXLocation(), plane.getYLocation());
				}
			});
		}
		
		public void restart() {
			timer.restart();
		}
	}
	
	class KeyHandler implements KeyListener {

		public void keyTyped(KeyEvent e) {
			
		}
		
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			switch (keyCode) {
			case KeyEvent.VK_UP:
				plane.setYLocation(VerticalRelocation.UP);
				break;
			case KeyEvent.VK_DOWN:
				plane.setYLocation(VerticalRelocation.DOWN);
				break;
			case KeyEvent.VK_RIGHT:
				handler.addWhizbang(new Whizbang(plane.getXLocation(), plane.getYLocation()));
				break;
			default:
				break;
			}
			
		}

		public void keyReleased(KeyEvent e) {
			
		}
	}

}
