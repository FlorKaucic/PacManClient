package client.conn;

import java.io.BufferedReader;

import javax.swing.JOptionPane;

public class ClientProtocol {
	// Registrar usuario:
		// envia
		// LOGUP username password
		// recibe
		// LOGUPOK PACMAN/GHOST id nickname
		// LOGUPFAILED [...]
		
		// Ingresar usuario:
		// envia
		// LOGIN username password
		// recibe
		// LOGINOK PACMAN/GHOST id nickname
		// LOGINFAILED [...]
			
	public static int[][] readMap(){
		int[][] map = null;
		Connection.getInstance().send("GETMAP");
		try {
			BufferedReader in = Connection.getInstance().getBufferedReader();

			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				if (inputLine.startsWith("MAPOK")) {
					inputLine = inputLine.substring(6);
					String[] lines = inputLine.split("ELN");
					String[] tiles = null;
					map = new int[lines.length][];
					for(int i = 0; i < lines.length; i++){
						tiles = lines[i].split(" ");
						map[i] = new int[tiles.length];
						for(int j = 0; j < tiles.length; j++){
							map[i][j] = Integer.parseInt(tiles[j]);
						}
					}
					break;
				}
			}
			in.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Fallo al recibir del servidor.", "Cliente",
					JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
		}
		return map;
	}
}
