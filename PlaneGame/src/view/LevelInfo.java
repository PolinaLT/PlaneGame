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

public class LevelInfo {
	private static int level;
	private static List<String> lines;
	
	public LevelInfo(JFrame gameFrame) throws IOException {		
		getLevel();
	}
	
	public static void setLevelBonus(int bonusStatus) throws IOException {
		lines = Files.readAllLines(Paths.get("C:/Users/sila-1/git/PlaneGame/PlaneGame/resources/level.txt"), Charset.defaultCharset());
		
		PrintWriter out = new PrintWriter("C:/Users/sila-1/git/PlaneGame/PlaneGame/resources/level.txt");
		
		for (int i = 0; i < lines.size(); i++) {
			out.println(lines.get(i));
		}
		
        int a = Integer.parseInt(lines.get(lines.size() - 1));
 
        int c = a + 1;
        
        String text = Integer.toString(c);
        String textB = Integer.toString(bonusStatus);
       
        out.println(textB);
        out.println(text);
        out.close();
	}
	
	public static List<String> levelReport() throws IOException {
		lines = Files.readAllLines(Paths.get("C:/Users/sila-1/git/PlaneGame/PlaneGame/resources/level.txt"), Charset.defaultCharset());
		lines.remove(lines.size() - 1);
		return lines;
	}
	

	
	public static int getLevel() throws IOException {
		lines = Files.readAllLines(Paths.get("C:/Users/sila-1/git/PlaneGame/PlaneGame/resources/level.txt"), Charset.defaultCharset());
		
		level = Integer.parseInt(lines.get(lines.size() - 1));
		
		return level;
	}
	
	public static void restartGame() throws FileNotFoundException {
		PrintWriter out = new PrintWriter("C:/Users/sila-1/git/PlaneGame/PlaneGame/resources/level.txt");
		String text = "1";
		out.println(text);
		out.close();
	}
}
