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
	private final int planeLength = 15;
	private final int planeWight = 15;
	private ImageIcon planeIcon = new ImageIcon("C:/Users/sila-1/git/PlaneGame/PlaneGame/resources/plane3.png");
	private Image plane;
	private int cloud = 900;
	private GameStatus gameStatus = GameStatus.PLAY;
	private Timer timer;
	private GameStatus status;
	
	public StartGamePanel() {
		image = new BufferedImage(1000, 700, BufferedImage.TYPE_4BYTE_ABGR);
		KeyHandler listener = new KeyHandler();
		addKeyListener(listener);
		setFocusable(true);
		
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

	private void gameOver() {
		System.out.println("Game Over");
	}
	
	
	
	public void drawCloud() {
		BarrierHandler handler = new BarrierHandler();
		List<Barrier> barrierList = handler.createBarrierList();
		java.util.Timer utilTimer = new java.util.Timer();
		utilTimer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				start();
				Graphics2D g = (Graphics2D) image.getGraphics();
				
				drawPlane();
				
				for (int i = 0; i < barrierList.size(); i++) {
					Barrier barrier = barrierList.get(i);
					BarrierEnum barrierEnum = barrier.getType();
					
					switch (barrierEnum) {
					case BONUS:
						g.setColor(Color.WHITE);
						break;
					case LIGHTNING:
						g.setColor(Color.YELLOW);
						break;
					case PLANE:
						g.setColor(Color.BLUE);
						break;
					}
					
					g.drawRect(barrier.getXLocation(), barrier.getYLocation(), 20, 20);
					g.fillRect(barrier.getXLocation(), barrier.getYLocation(), 20, 20);
				}
				
				//System.out.println(gameStatus);
				
				//System.out.println(gameStatus);
				if (gameStatus == GameStatus.PLAY) {
					handler.changeLocation();
					repaint();
					gameStatus = handler.checkStatus(xPosition, yPosition);
				}
				else {
					utilTimer.cancel();
				}
				
			}
		}, 0, 15);
		
	}
	
	private void stopTimer(Timer time) {
		time.stop();
	}
	
	private void drawPlane() {
		start();
		Graphics2D g = (Graphics2D) image.getGraphics();
		g.setColor(Color.YELLOW);
		if (xPosition < 0 || xPosition > 1000 || yPosition < 0 || yPosition > 700) {
			gameOver();
		}
		else {
			//g.drawRect(xPosition, yPosition, planeLength, planeWight);
			//g.fillRect(xPosition, yPosition, planeLength, planeWight);
			//g.drawLine(xPosition, yPosition, xPosition + planeLength, yPosition);
			plane = planeIcon.getImage();
			g.drawImage(plane, xPosition, yPosition, null);
			repaint();
		}
	}
	
	
	
	class KeyHandler implements KeyListener {

		
		
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			switch (keyCode) {
			case KeyEvent.VK_UP:
				yPosition -= 10;	
				break;
			case KeyEvent.VK_DOWN:
				yPosition += 10;
				break;
			default:
				break;
			}
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	}

}
