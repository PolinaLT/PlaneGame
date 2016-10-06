package model;

import java.awt.Color;
import java.util.Random;

import javax.swing.ImageIcon;

public class LightningBarrier implements Barrier {
	private int xLocation;
	private int yLocation;
	private Random randomGenerator = new Random();
	private Color color;
	private ImageIcon lightningIcon = new ImageIcon("C:/Users/sila-1/git/PlaneGame/PlaneGame/resources/lightning.png");
	
	
	public LightningBarrier(int length) {
		yLocation = randomGenerator.nextInt(700);
		xLocation = randomGenerator.nextInt(length) + 1000;
		color = Color.YELLOW;
	}
	
	public void setXLocation() {
		xLocation -= 5;
	}

	public int getXLocation() {
		return xLocation;
	}

	public int getYLocation() {
		return yLocation;
	}
	
	public Color getColor() {
		return color;
	}
	
	public ImageIcon getIcon() {
		return lightningIcon;
	}

}
