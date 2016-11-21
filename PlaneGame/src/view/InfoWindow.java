package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import controller.Controller;

public class InfoWindow {
	private JFrame infoFrame = new JFrame();
	private JPanel infoPanel = new JPanel();
	private JButton agree = new JButton("ДА");
	private JButton disagree = new JButton("НЕТ");
	private Controller controller = new Controller();
	
	public InfoWindow() {
		infoFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		infoFrame.setSize(300, 80);
		infoFrame.setLocationRelativeTo(null);
		
		infoPanel.add(new JLabel("Вы уверены?"));
		infoPanel.add(agree);
		infoPanel.add(disagree);
		
		agree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.restartHistory();
				addInfo();
			}
		});
		
		disagree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				infoFrame.setVisible(false);
			}
		});
		
		
	}
	
	private void addInfo() {
		infoPanel.removeAll();
		infoPanel.add(new JLabel("Прогресс был успешно сброшен"));
		infoPanel.repaint();
		infoFrame.repaint();
		infoFrame.revalidate();
		infoFrame.add(infoPanel);
		infoFrame.setVisible(true);
	}
}
