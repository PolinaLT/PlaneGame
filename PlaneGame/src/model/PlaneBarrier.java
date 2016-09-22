package model;

import java.util.Random;

public class PlaneBarrier implements Barrier {
	private int xLocation;
	private int yLocation;
	private Random randomGenerator = new Random();
		
	public PlaneBarrier() {
		yLocation = randomGenerator.nextInt(700);
		xLocation = randomGenerator.nextInt(99001) + 1;
	}	
	
	public void setXLocation() {
		xLocation -= 3;
	}

	public int getXLocation() {
		return xLocation;
	}

	@Override
	public int getYLocation() {
		return yLocation;
	}

}
