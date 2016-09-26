package model;

import java.awt.Color;
import java.util.Random;

import javax.swing.ImageIcon;

public class PlaneBarrier implements Barrier {
	private int xLocation;
	private int yLocation;
	private Random randomGenerator = new Random();
	private Color color;
	private ImageIcon planeIcon = new ImageIcon("C:/Users/sila-1/git/PlaneGame/PlaneGame/resources/planeB.png");
		
	public PlaneBarrier(int length) {
		yLocation = randomGenerator.nextInt(700);
		xLocation = randomGenerator.nextInt(length) + 1;
		color = Color.RED;
	}	
	
	public void setXLocation() {
		xLocation -= 6;
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
		return planeIcon;
	}

}
