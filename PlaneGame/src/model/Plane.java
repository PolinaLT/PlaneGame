package model;

import java.awt.Color;

import javax.swing.ImageIcon;

public class Plane implements GameObject {
	private int xPosition;
	private int yPosition;
	private final ImageIcon planeIcon = new ImageIcon("C:/Users/sila-1/git/PlaneGame/PlaneGame/resources/plane3.png");
	
	public Plane() {
		xPosition = 500;
		yPosition = 300;
	}
	
	public void setXLocation() {
		
	}
	
	public int getXLocation() {
		return xPosition;
	}
	
	public int getYLocation() {
		return yPosition;
	}
	
	public ImageIcon getIcon() {
		return planeIcon;
	}

	public void setYLocation(VerticalRelocation relocation) {
		switch (relocation) {
		case UP:
			yPosition -= 20;
			break;
		case DOWN:
			yPosition += 20;
			break;
		default:
			break;
		}
	}
	
	public void setNullPosition() {
		
	}
	
}
