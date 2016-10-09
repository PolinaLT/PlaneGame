package model;

import java.awt.Color;

import javax.swing.ImageIcon;


public interface GameObject {
		
	void setXLocation();
	
	void setYLocation(VerticalRelocation relocation);
	
	int getXLocation();
		
	int getYLocation();
	
	void setNullPosition();
		
	ImageIcon getIcon();
}
