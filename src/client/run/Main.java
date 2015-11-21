package client.run;

import client.config.Config;
import client.conn.Connection;
import client.conn.ServerListener;
import client.gui.LogInFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Config.load();
		
		Thread t = new ServerListener();
		t.start();
		
		if(args!=null&&args.length>0&&args[0].equals("VIEWER")){
			Connection.getInstance().send("LOGIN VIEWER admin 1234");
		}

		LogInFrame frame = new LogInFrame();
		frame.setVisible(true);
	}

}
