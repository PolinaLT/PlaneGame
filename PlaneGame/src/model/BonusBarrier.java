package model;

import java.util.Random;

public class BonusBarrier implements Barrier {
	private int yLocation;
	private int xLocation;
	private Random randomGenerator = new Random();
	
	public BonusBarrier() {
		yLocation = randomGenerator.nextInt(700);
		xLocation = randomGenerator.nextInt(99001) + 1;
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

	
	
	
}
