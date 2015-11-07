package client.conn;

import java.io.BufferedReader;

import javax.swing.JOptionPane;

public class ServerListener extends Thread {
	BufferedReader in = null;
	
	public ServerListener(BufferedReader in){
		this.in = in;
	}

	public void run() {
		try {
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				ClientProtocol.processInput(inputLine);
			}
			in.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Fallo al recibir del servidor.", "Cliente", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
}
