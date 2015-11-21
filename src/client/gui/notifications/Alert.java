package client.gui.notifications;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.Timer;

import client.config.Config;

@SuppressWarnings("serial")
public class Alert extends JDialog {
	protected JLabel lblTime;
	
	public Alert(String msg) {
		setLayout(null);
		setSize(350, 50);
		int x = Integer.parseInt(Config.get("screen_width"))-400;
		int y = Integer.parseInt(Config.get("screen_height"))-Integer.parseInt(Config.get("taskbar_height"))-100;
		setLocation(x, y);
		this.setUndecorated(true);
		setModalityType(ModalityType.MODELESS);
		getRootPane().setBorder( BorderFactory.createLineBorder(Color.GRAY, 2, false));
		lblTime = new JLabel();
		lblTime.setBounds(20, 7, 300, 30);
		lblTime.setText(msg);
		lblTime.setForeground(Color.BLACK);
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblTime);
		
		Timer timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Alert.this.setVisible(false);
				Alert.this.dispose();
			}
		});
		timer.start();
	}

	public void setBorderColor(Color color) {
		getRootPane().setBorder( BorderFactory.createLineBorder(color, 2, false));
	}
}