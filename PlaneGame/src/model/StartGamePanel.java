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

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


public class StartGamePanel extends JPanel {
	public BufferedImage image;
	int xPosition = 10;
	int yPosition = 10;
	private final int planeLength = 15;
	private final int planeWight = 15;
	private ImageIcon planeIcon = new ImageIcon("D:/workspace/PlaneGame/resources/plane3.png");
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
		Timer timer = new Timer(5, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				start();
				Graphics2D g = (Graphics2D) image.getGraphics();
				System.out.println("@");
				g.setColor(Color.YELLOW);
				g.drawOval(cloud, 90, 50, 50);
				g.fillOval(cloud, 90, 50, 50);
				cloud -= 5;
				plane = planeIcon.getImage();
				g.drawImage(plane, xPosition, yPosition, null);
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
			
			//repaint();
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
				System.out.println("UP");
				yPosition -= 5;	
				//drawPlane();
				break;
			case KeyEvent.VK_DOWN:
				System.out.println("DOWN");
				yPosition += 5;
				//drawPlane();
				break;
			
			case KeyEvent.VK_RIGHT:
				System.out.println("RIGHT");
				xPosition = xPosition + 5;
				//start();
				//drawPlane();
				
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
