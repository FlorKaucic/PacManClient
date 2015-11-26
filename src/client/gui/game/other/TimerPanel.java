package client.gui.game.other;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import client.logic.Parser;

@SuppressWarnings("serial")
public class TimerPanel extends JPanel {
	protected JLabel lblTime;
	
	public TimerPanel() {
		setLayout(null);
		setSize(75, 50);
		setBackground(Color.BLACK);
		lblTime = new JLabel();
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setText("--:--");
		lblTime.setForeground(Color.WHITE);
		lblTime.setFont(new Font("Kristen ITC", Font.BOLD, 24));
		lblTime.setBounds(0, 0, 75, 50);
		add(lblTime);
	}

	public void update(int time) {
		if(time <= 2400)
			this.lblTime.setText(Parser.parseTime(time));
	}

}