package model;

import java.awt.Color;

import javax.swing.ImageIcon;


public interface Barrier {
		
	void setXLocation();
	
	int getXLocation();
		
	int getYLocation();
	
	Color getColor();
	
	ImageIcon getIcon();
}
