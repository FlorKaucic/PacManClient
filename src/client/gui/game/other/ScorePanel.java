package client.gui.game.other;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import client.config.Config;
import client.logic.Parser;

@SuppressWarnings("serial")
public class ScorePanel extends JPanel{

protected JLabel lblScore;
protected Image img = null;

	public ScorePanel(int c) {
		setLayout(null);
		setSize(105, 30);
		setBackground(Color.BLACK);
		lblScore = new JLabel();
		try {
			this.img = ImageIO.read(new File(Config.get("img_path")+Parser.parseProfile(c).toLowerCase()+".png"));
			lblScore.setHorizontalAlignment(SwingConstants.CENTER);
			lblScore.setText("---");
			lblScore.setForeground(Color.WHITE);
			lblScore.setFont(new Font("Baby Kruffy", Font.PLAIN, 24));
			lblScore.setBounds(30, 0, 75, 30);
			add(lblScore);
		} catch (IOException e) {
			JLabel error = new JLabel("No se pudo cargar la imagen.");
			error.setBounds(30, 0, 75, 30);
			error.setForeground(Color.WHITE);
			error.setFont(new Font("Arial", Font.BOLD, 14));
			error.setVisible(true);
			this.add(error);
		}
		
		
	}

	public void update(int time) {
		this.lblScore.setText(Parser.parseTime(time));
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if(img != null)
			g.drawImage(this.img, 0, 0, 30, 30, 0, 0, 30, 30, null);
	}

}
