package client.conn;

import client.config.Config;
import client.gui.LogInFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Config.load();

		Thread t = new ServerListener();
		t.start();

		LogInFrame frame = new LogInFrame();
		frame.setVisible(true);
	}

}
