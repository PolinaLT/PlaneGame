package view;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.omg.PortableServer.ServantRetentionPolicyValue;

import view.LevelWindow;
import controller.Controller;
import model.GameStatus;
import model.StartGamePanel;

public class GameWindow implements Runnable {
	private static JFrame gameFrame = new JFrame("Let's play!");
	private Controller controller = new Controller();
	private GameStatus gameStatus = GameStatus.PLAY;
	private Thread checkThread = new Thread(this);
	
	public GameWindow() throws IOException {
		gameFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		gameFrame.setSize(1000, 700);
		
		controller.startGame(gameFrame, LevelWindow.getLevel());
		
		checkThread.start();
		
		gameFrame.setVisible(true);
	}
	
	public static JFrame getFrame() {
		return gameFrame;
	}

	@Override
	public void run() {
		while (gameStatus == GameStatus.PLAY) {
			try {
				Thread.sleep(5);
				gameStatus = controller.report();
				if (gameStatus != GameStatus.PLAY) {
					try {
						InfoWindow info = new InfoWindow(gameStatus);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//System.out.println("INFO");
				}
				//System.out.println(gameStatus + " view");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
