package client.gui.game.other;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import client.logic.Parser;

@SuppressWarnings("serial")
public class TimerPanel extends JPanel {
	protected JLabel lblTime;
	
	public TimerPanel() {
		setLayout(null);
		setSize(100, 100);
		setBackground(Color.BLACK);
		lblTime = new JLabel();
		lblTime.setText("--:--");
		lblTime.setForeground(Color.WHITE);
		lblTime.setFont(new Font("Monospaced", Font.PLAIN, 14));
		lblTime.setBounds(0, 0, 100, 100);
		add(lblTime);
	}

	public void update(int time) {
		this.lblTime.setText(Parser.parseTime(time));
	}

}