package model;

import java.awt.Color;
import java.util.Random;

import javax.swing.ImageIcon;

public class BonusBarrier implements GameObject {
	private int yLocation;
	private int xLocation;
	private Random randomGenerator = new Random();
	private ImageIcon bonusIcon = new ImageIcon("C:/Users/sila-1/git/PlaneGame/PlaneGame/resources/bonus.png");
	
	public BonusBarrier(int length) {
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
		return bonusIcon;
	}

	public void setNullPosition() {
		xLocation = 0;
	}	
	
}
