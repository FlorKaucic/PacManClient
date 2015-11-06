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
				System.out.println(inputLine);
				if (inputLine.equals("Se recibio: fin")){
					System.out.println("Fin.");
					break;
				}
			}
			in.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Fallo al recibir del servidor.", "Cliente", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
}
