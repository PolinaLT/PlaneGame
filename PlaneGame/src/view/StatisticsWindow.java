package view;

import java.awt.Label;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle.Control;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import controller.Controller;

public class StatisticsWindow {
	private JFrame reportFrame = new JFrame();
	private JPanel reportPanel;
	private JLabel reportLabel;
	private List<String> info;
	private Controller controller = new Controller();
	
	public StatisticsWindow() throws IOException {
		reportFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		info = controller.getLevelReport();
		
		reportFrame.setSize(200, 60 + info.size() * 10);
		reportFrame.setLocationRelativeTo(null);
		
		reportPanel = new JPanel();
		reportFrame.add(reportPanel);
		
		genStatistics();
		reportFrame.setVisible(true);
	}
	
	private void genStatistics() {
		for (int i = 1; i < info.size(); i+=2) {
			reportLabel = new JLabel("Уровень: " + info.get(i - 1));
			reportPanel.add(reportLabel);
			reportLabel = new JLabel("Результат: " + info.get(i) + "/" + info.get(i - 1));
			reportPanel.add(reportLabel);
		}
		
		if (info.size() == 0) {
			reportLabel = new JLabel("Ни один уровень не пройден :(");
			reportPanel.add(reportLabel);
		}
		
	}
}
