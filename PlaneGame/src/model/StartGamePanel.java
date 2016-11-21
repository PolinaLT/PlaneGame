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
import java.io.IOException;
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
	private Level level = new Level();
	private GameObjectHandler handler;
	private List<GameObject> bonusList;
	private List<GameObject> stoneList;
	private List<GameObject> lightningList;
	private TimerHandler newTimer;
	private List<GameObject> whizbangList;
	private int numberOfObjects;
	private String information;
	private Graphics g;
	private Graphics2D g2;
	private KeyHandler listener;
	
	
	public StartGamePanel() {
		image = new BufferedImage(1000, 700, BufferedImage.TYPE_4BYTE_ABGR);
		listener = new KeyHandler();
		addKeyListener(listener);
		setFocusable(true);		
	}
	
	private void information() {
		g = image.getGraphics();
		g.setColor(Color.WHITE);
		information = "Цель: " + handler.bonusReport() + "/" + level.getLevel() + " Уничтожено объектов: " + handler.removeStoneStatus();
		g.drawString(information, 0, 650);
	}
	
	public int bonusReport() {
		return handler.bonusReport();
	}
	
	public void stopGame() {
		newTimer.stop();
	}
	
	public GameStatus report() {
		return gameStatus;
	}
	
	public void start() {
        g = image.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1000, 700);
    }
	
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(10.0f));
        g2.drawImage(this.image, 0, 0, null);
    }
	
	private void drawBarrier(List<GameObject> barrierList) {
		g2 = (Graphics2D) image.getGraphics();
		for (numberOfObjects = 0; numberOfObjects < barrierList.size(); numberOfObjects++) {
			GameObject barrier = barrierList.get(numberOfObjects);
			barrierImage = barrier.getIcon().getImage();
			g2.drawImage(barrierImage, barrier.getXLocation(), barrier.getYLocation(), null);
		}
	}
	
	private void drawWhizbang() {
		g2 = (Graphics2D) image.getGraphics();
		whizbangList = handler.getWhizbangList();
		for (numberOfObjects = 0; numberOfObjects < whizbangList.size(); numberOfObjects++) {
			g2.drawRect(whizbangList.get(numberOfObjects).getXLocation(), whizbangList.get(numberOfObjects).getYLocation(), 5, 5);
		}
	}
	
	private void draw() {
		repaint();
		
		handler = new GameObjectHandler();
		gameStatus = GameStatus.PLAY;
		
		bonusList = handler.createBonusList();
		stoneList = handler.createStoneList();
		lightningList = handler.createLightningList();
	}

	public void startGame() {
		handler = new GameObjectHandler();
		draw();
		newTimer = new TimerHandler();
	}
	
	public void nextGame() {
		handler = new GameObjectHandler();
		draw();
		newTimer.restart();
	}
	
	private void drawPlane() {
		start();
		g2 = (Graphics2D) image.getGraphics();
		g2.setColor(Color.YELLOW);
			planeImage = plane.getIcon().getImage();
			g2.drawImage(planeImage, plane.getXLocation(), plane.getYLocation(), null);
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
					information();
					
					gameStatus = handler.checkStatus(plane.getXLocation(), plane.getYLocation());
				}
			});
		}
		
		public void restart() {
			timer.restart();
		}
		
		public void stop() {
			timer.stop();
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
