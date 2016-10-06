package model;

public class Whizbang {
	private int xPosition;
	private int yPosition;
	
	public Whizbang(int xPosition, int yPosition) {
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}
	
	public void setXPosition() {
		xPosition += 5;
	}
	
	public int getXPosition() {
		return xPosition;
	}
	
	public int getYPosition() {
		return yPosition;
	}
}
