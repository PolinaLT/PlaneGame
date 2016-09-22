package model;

import java.util.Random;

public class LightningBarrier implements Barrier {
	private int xLocation;
	private int yLocation;
	private Random randomGenerator = new Random();
	
	public LightningBarrier() {
		yLocation = randomGenerator.nextInt(700);
		xLocation = randomGenerator.nextInt(99001) + 1;
	}
	
	public void setXLocation() {
		xLocation -= 1;
	}

	public int getXLocation() {
		return xLocation;
	}

	public int getYLocation() {
		return yLocation;
	}

}
