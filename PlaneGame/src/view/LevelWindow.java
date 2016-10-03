package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import controller.Controller;

public class LevelWindow {
	private static int level;
	private static int bonus;
	
	public LevelWindow(JFrame gameFrame) throws IOException {		
		getLevel();
	}
	
	public static void setLevelBonus(int bonusStatus) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get("C:/Users/sila-1/git/PlaneGame/PlaneGame/resources/level.txt"), Charset.defaultCharset());
		 
        int a = Integer.parseInt(lines.get(0));
 
        int c = a + 1;
        int b = bonus + bonusStatus;
 
        String text = Integer.toString(c);
        String textB = Integer.toString(b);
        PrintWriter out = new PrintWriter("C:/Users/sila-1/git/PlaneGame/PlaneGame/resources/level.txt");
        out.println(text);
        out.println(textB);
        out.close();
	}
	

	
	public static int getLevel() throws IOException {
		List<String> lines = Files.readAllLines(Paths.get("C:/Users/sila-1/git/PlaneGame/PlaneGame/resources/level.txt"), Charset.defaultCharset());
		
		level = Integer.parseInt(lines.get(0));
		bonus = Integer.parseInt(lines.get(1));
				
		return level;
	}
	
	/*public static void main(String[] args) throws IOException {
		//System.out.println(LevelWindow.getLevel());
		LevelWindow.setLevel();
	}*/

}
