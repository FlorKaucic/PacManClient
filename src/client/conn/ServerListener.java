package client.conn;

import java.io.BufferedReader;

import javax.swing.JOptionPane;

public class ServerListener extends Thread {
	public void run() {
		try {
			BufferedReader in = Connection.getInstance().getBufferedReader();

			String input;
			while ((input = in.readLine()) != null) {
				System.out.println("Se leyo una linea "+input);
				ClientProtocol.processInput(input);
			}

			System.out.println("Se leyo una linea "+input);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Fallo al recibir del servidor.", "Cliente",
					JOptionPane.ERROR_MESSAGE);
			// CHANGE
		}
	}
}
