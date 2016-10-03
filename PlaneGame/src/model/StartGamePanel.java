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
	private int xPosition = 500;
	private int yPosition = 300;
	private ImageIcon planeIcon = new ImageIcon("C:/Users/sila-1/git/PlaneGame/PlaneGame/resources/plane3.png");
	private Image plane;
	private Image barrierImage;
	private GameStatus gameStatus = GameStatus.PLAY;
	private Timer timer;
	private int level;
	private int speedUp = 0;
	private BarrierHandler handler;
	private List<Barrier> bonusList;
	private List<Barrier> planeList;
	private List<Barrier> lightningList;
	
	
	public StartGamePanel() {
		
		image = new BufferedImage(1000, 700, BufferedImage.TYPE_4BYTE_ABGR);
		KeyHandler listener = new KeyHandler();
		addKeyListener(listener);
		setFocusable(true);		
	}
	
	public int bonusReport() {
		return BarrierHandler.bonusReport();
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
	
	private void drawBarrier(List<Barrier> barrierList) {
		Graphics2D g = (Graphics2D) image.getGraphics();
		for (int i = 0; i < barrierList.size(); i++) {
			Barrier barrier = barrierList.get(i);
			barrierImage = barrier.getIcon().getImage();
			g.drawImage(barrierImage, barrier.getXLocation() - speedUp, barrier.getYLocation(), null);
			speedUp = 0;
		}
	}
	
	public void drawCloud(int level) {
		repaint();
		this.level = level;
		handler = new BarrierHandler(level);
		gameStatus = GameStatus.PLAY;
		
		bonusList = handler.createBonusList();
		planeList = handler.createPlaneList();
		lightningList = handler.createLightningList();
		startTimer();
		timer.start();
		if (gameStatus != GameStatus.PLAY) {
			timer.stop();
			System.out.println("111111111111111111111111111");
		}
		
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
					stopTimer(timer);
					
					return;
				}
				
				drawPlane();
				drawBarrier(bonusList);
				drawBarrier(planeList);
				drawBarrier(lightningList);
				
				gameStatus = handler.checkStatus(xPosition, yPosition);
			}
		});
	}
	
	private void stopTimer(Timer time) {
		//System.out.println("22222222222222222222222");
		time.stop();
	}
	
	private void drawPlane() {
		start();
		Graphics2D g = (Graphics2D) image.getGraphics();
		g.setColor(Color.YELLOW);
			plane = planeIcon.getImage();
			g.drawImage(plane, xPosition, yPosition, null);
			repaint();
	}
	
	
	
	class KeyHandler implements KeyListener {

		public void keyTyped(KeyEvent e) {
			
		}
		
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			switch (keyCode) {
			case KeyEvent.VK_UP:
				yPosition -= 20;	
				break;
			case KeyEvent.VK_DOWN:
				yPosition += 20;
				break;
			case KeyEvent.VK_RIGHT:
				//speedUp = 20;
				break;
			default:
				break;
			}
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			
		}
	}

}
