package view;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.omg.PortableServer.ServantRetentionPolicyValue;

import controller.Controller;
import model.GameStatus;
import model.StartGamePanel;

public class GameWindow implements Runnable {
	private JFrame gameFrame = new JFrame("Let's play!");
	private Controller controller = new Controller();
	private GameStatus gameStatus = GameStatus.PLAY;
	private Thread checkThread = new Thread(this);
	
	public GameWindow() {
		gameFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		gameFrame.setSize(1000, 700);
		
		controller.startGame(gameFrame);
		
		checkThread.start();
		
		gameFrame.setVisible(true);
	}

	@Override
	public void run() {
		while (gameStatus == GameStatus.PLAY) {
			try {
				Thread.sleep(5);
				gameStatus = controller.report();
				if (gameStatus != GameStatus.PLAY) {
					InfoWindow info = new InfoWindow(gameStatus);
					System.out.println("INFO");
				}
				System.out.println(gameStatus + " view");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
