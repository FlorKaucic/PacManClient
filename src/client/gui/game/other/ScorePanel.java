package client.gui.game.other;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import client.logic.Parser;

public class ScorePanel extends JPanel{

protected JLabel lblScore;
	
	public ScorePanel() {
		setLayout(null);
		setSize(75, 50);
		setBackground(Color.BLACK);
		lblScore = new JLabel();
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblScore.setText("---");
		lblScore.setForeground(Color.WHITE);
		lblScore.setFont(new Font("Baby Kruffy", Font.PLAIN, 24));
		lblScore.setBounds(0, 0, 75, 50);
		add(lblScore);
	}

	public void update(int time) {
		this.lblScore.setText(Parser.parseTime(time));
	}


}
