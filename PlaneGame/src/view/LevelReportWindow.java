package view;

import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class LevelReportWindow {
	private JFrame reportFrame = new JFrame();
	private JPanel reportPanel;
	private JLabel reportLabel;
	private List<String> info;
	
	public LevelReportWindow() throws IOException {
		reportFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		reportFrame.setSize(150, 600);
		
		reportPanel = new JPanel();
		reportFrame.add(reportPanel);
		
		info = LevelInfo.levelReport();
		
		for (int i = 1; i < info.size(); i+=2) {
			reportLabel = new JLabel("Уровень: " + info.get(i - 1));
			reportPanel.add(reportLabel);
			reportLabel = new JLabel("Результат: " + info.get(i) + "/" + info.get(i - 1));
			reportPanel.add(reportLabel);
		}
		
		reportFrame.setVisible(true);
	}
}
