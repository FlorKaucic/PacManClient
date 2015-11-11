package client.conn;

import java.io.BufferedReader;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import game.gui.GameFrame;

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

	public static void logIn(JFrame parent, String user, String pass) {
		Connection.getInstance().send("LOGIN " + user + " " + pass);

		try {
			BufferedReader in = Connection.getInstance().getBufferedReader();

			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				if (inputLine.startsWith("LOGINOK")) {
					System.out.println("ok " + inputLine);
					GameFrame gf = new GameFrame();
					gf.setVisible(true);
					parent.dispose();
					break;
				}

				if (inputLine.startsWith("LOGINFAILED")) {
					System.out.println("failed " + inputLine);
					JOptionPane.showMessageDialog(null,
							"No se puede ingresar."
									+ "\nCompruebe que el nombre de usuario y la contraseña sean correctos."
									+ "\nSi lo son, su cuenta podria estar deshabilitada por el administrador.",
							"Ingresar al juego", JOptionPane.ERROR_MESSAGE);
					break;
				}
			}
//			in.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Fallo al recibir del servidor.", "Cliente", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
		}
	}

	public static void logUp(JDialog parent, String user, String pass) {
		Connection.getInstance().send("LOGUP " + user + " " + pass);

		try {
			BufferedReader in = Connection.getInstance().getBufferedReader();
			
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				if (inputLine.startsWith("LOGUPOK")) {
					System.out.println("ok " + inputLine);
					parent.dispose();
					break;
				}
				if (inputLine.startsWith("LOGUPFAILED")) {
					System.out.println("failed " + inputLine);
					JOptionPane.showMessageDialog(null, "No se puede registrar", "Registro de usuario",
							JOptionPane.ERROR_MESSAGE);
					break;
				}
			}
//			in.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Fallo al recibir del servidor.", "Cliente", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
		}
	}

	public static int[][] readMap() {
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
					for (int i = 0; i < lines.length; i++) {
						tiles = lines[i].split(" ");
						map[i] = new int[tiles.length];
						for (int j = 0; j < tiles.length; j++) {
							map[i][j] = Integer.parseInt(tiles[j]);
						}
					}
					break;
				}
			}
//			in.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Fallo al recibir del servidor.", "Cliente", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
		}
		return map;
	}
}
