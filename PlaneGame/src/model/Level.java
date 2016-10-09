package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.swing.JFrame;

public class Level {
	private int level;
	private List<String> lines;
	
	public Level() {		
		getLevel();
	}
	
	public void setLevelBonus(int bonusStatus) {
		try {
			lines = Files.readAllLines(Paths.get("C:/Users/sila-1/git/PlaneGame/PlaneGame/resources/level.txt"), Charset.defaultCharset());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PrintWriter out;
		try {
			out = new PrintWriter("C:/Users/sila-1/git/PlaneGame/PlaneGame/resources/level.txt");
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
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	private void genLevelReport() {
		try {
			lines = Files.readAllLines(Paths.get("C:/Users/sila-1/git/PlaneGame/PlaneGame/resources/level.txt"), Charset.defaultCharset());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lines.remove(lines.size() - 1);
	}
	
	public List<String> levelReport() {
		genLevelReport();
		return lines;
	}
	
	public int getLevel() {
		level();
		return level;
	}
	
	private void level() {
		try {
			lines = Files.readAllLines(Paths.get("C:/Users/sila-1/git/PlaneGame/PlaneGame/resources/level.txt"), Charset.defaultCharset());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		level = Integer.parseInt(lines.get(lines.size() - 1));
	}
	
	public void restartGame() {
		PrintWriter out;
		try {
			out = new PrintWriter("C:/Users/sila-1/git/PlaneGame/PlaneGame/resources/level.txt");
			String text = "1";
			out.println(text);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

