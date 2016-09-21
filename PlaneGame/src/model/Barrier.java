package model;

import java.util.Random;


public class Barrier {
	private BarrierEnum barrierType;
	private int xLocation;
	private int yLocation;
	
	public Barrier() {
		Random randomGenerator = new Random();
		yLocation = randomGenerator.nextInt(700);
		xLocation = randomGenerator.nextInt(99001) + 1;
		int type = randomGenerator.nextInt(3);
		switch (type) {
		case 0:
			barrierType = BarrierEnum.BONUS;
			break;
		case 1:
			barrierType = BarrierEnum.PLANE;
			break;
		case 2: 
			barrierType = BarrierEnum.LIGHTNING;
			break;
		default:
			break;
		}
	}
	
	
	public BarrierEnum getType() {
		return this.barrierType;
	}
	
	public void setXLocation() {
		xLocation --;		
	}
	
	public int getXLocation() {
		return xLocation;
	}
	
	public void setYLocation() {
		yLocation --;		
	}
	
	public int getYLocation() {
		return yLocation;
	}
}
