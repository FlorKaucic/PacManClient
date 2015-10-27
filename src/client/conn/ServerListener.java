package client.conn;

import java.io.BufferedReader;

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
			System.err.println("Fallo.");
			e.printStackTrace();
		}
	}
}
