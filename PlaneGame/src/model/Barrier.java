package model;

public class Barrier {
	private BarrierEnum type;
	private int xLocation;
	private int yLocation;
	
	public Barrier() {
		//xLoacation = 1000;
		//yLocation = 
		//RANDOOOOOOOOOOOOM
	}
	
	public void setType(BarrierEnum type) {
		this.type = type;
	}
	
	public BarrierEnum getType() {
		return this.type;
	}
	
	public void setXLocation(int x) {
		xLocation -= x;		
	}
	
	public int getXLocation() {
		return xLocation;
	}
	
	public void setYLocation(int y) {
		yLocation -= y;		
	}
	
	public int getYLocation() {
		return yLocation;
	}
}
