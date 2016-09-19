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

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


public class StartGamePanel extends JPanel {
	public BufferedImage image;
	int xPosition = 500;
	int yPosition = 300;
	private final int planeLength = 15;
	private final int planeWight = 15;
	private ImageIcon planeIcon = new ImageIcon("C:/Users/sila-1/git/PlaneGame/PlaneGame/resources/plane3.png");
	private Image plane;
	int cloud = 900;
	
	public StartGamePanel() {
		image = new BufferedImage(1000, 700, BufferedImage.TYPE_4BYTE_ABGR);
		KeyHandler listener = new KeyHandler();
		addKeyListener(listener);
		setFocusable(true);
		drawCloud();
		
		
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
	
	private void drawCloud() {
		BarrierHandler handler = new BarrierHandler();
		List<Barrier> barrierList = handler.createBarrierList();
		Timer timer = new Timer(5, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
				handler.changeLocation();
				repaint();
			}
		});
		timer.start();
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
