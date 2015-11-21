package client.conn;

import java.io.BufferedReader;

import javax.swing.JOptionPane;

public class ServerListener extends Thread {
	public void run() {
		try {
			BufferedReader in = Connection.getInstance().getBufferedReader();

			String input;
			while ((input = in.readLine()) != null) {
				ClientProtocol.processInput(input);
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Fallo al recibir del servidor.\n"+ex, "Cliente",
					JOptionPane.ERROR_MESSAGE);
			// CHANGE
		}
	}
}
