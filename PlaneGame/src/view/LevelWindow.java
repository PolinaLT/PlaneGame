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
	private String file = "C:/Users/sila-1/git/PlaneGame/PlaneGame/resources/level.txt";
	private static int level;
	private static String levelString;
	private JFrame levelFrame = new JFrame("Level");
	private JPanel levelPanel = new JPanel();
	private Controller controller = new Controller();
	
	public LevelWindow(JFrame gameFrame) throws IOException {
		levelFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		levelFrame.setSize(100, 500);
		
		getLevel();
		for (int i = 0; i < level; i++) {
			JButton button = new JButton(Integer.toString(i+1));
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controller.startGame(gameFrame, level);
				}
			});
			levelPanel.add(button);
		}
		
		
		levelFrame.add(levelPanel);
		levelFrame.setVisible(true);
	}
	
	public static void setLevel() throws IOException {
		List<String> lines = Files.readAllLines(Paths.get("C:/Users/sila-1/git/PlaneGame/PlaneGame/resources/level.txt"), Charset.defaultCharset());
		 
        int a = Integer.parseInt(lines.get(0));
 
        int c = a + 1;
 
        String text = Integer.toString(c);
        PrintWriter out = new PrintWriter("C:/Users/sila-1/git/PlaneGame/PlaneGame/resources/level.txt");
        out.println(text);
        out.close();
	}
	
	public static int getLevel() throws IOException {
		List<String> lines = Files.readAllLines(Paths.get("C:/Users/sila-1/git/PlaneGame/PlaneGame/resources/level.txt"), Charset.defaultCharset());
		
		level = Integer.parseInt(lines.get(0));
				
		return level;
	}
	
	/*public static void main(String[] args) throws IOException {
		//System.out.println(LevelWindow.getLevel());
		LevelWindow.setLevel();
	}*/

}
