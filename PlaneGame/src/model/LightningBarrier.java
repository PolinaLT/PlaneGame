package model;

import java.awt.Color;
import java.util.Random;

import javax.swing.ImageIcon;

public class LightningBarrier implements GameObject {
	private int xLocation;
	private int yLocation;
	private Random randomGenerator = new Random();
	private ImageIcon lightningIcon = new ImageIcon("C:/Users/sila-1/git/PlaneGame/PlaneGame/resources/lightning.png");
	
	
	public LightningBarrier(int length) {
		yLocation = randomGenerator.nextInt(700);
		xLocation = randomGenerator.nextInt(length) + 1000;
	}
	
	public void setXLocation() {
		xLocation -= 2;
	}

	public int getXLocation() {
		return xLocation;
	}

	public int getYLocation() {
		return yLocation;
	}
	
	public ImageIcon getIcon() {
		return lightningIcon;
	}

	public void setNullPosition() {
		
	}

}
