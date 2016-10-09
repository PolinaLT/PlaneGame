package model;

import javax.swing.ImageIcon;

public class Whizbang implements GameObject {
	private int xPosition;
	private int yPosition;
	
	public Whizbang(int xPosition, int yPosition) {
		this.xPosition = xPosition + 15;
		this.yPosition = yPosition + 7;
	}

	public int getYPosition() {
		return yPosition;
	}

	public void setXLocation() {
		xPosition += 5;
	}

	public void setYLocation(VerticalRelocation relocation) {
		
	}

	public int getXLocation() {
		return xPosition;
	}

	public int getYLocation() {
		return yPosition;
	}

	public ImageIcon getIcon() {
		return null;
	}
	
	public void setNullPosition() {
		xPosition = 0;
	}
	
}
