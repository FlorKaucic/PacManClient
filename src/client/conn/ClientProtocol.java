package client.conn;

import javax.swing.JOptionPane;

import client.gui.LogInFrame;
import client.gui.LogUpDialog;
import client.gui.StatsFrame;
import client.gui.game.GameFrame;
import client.logic.Parser;

public class ClientProtocol {

	public static void processInput(String input) {
		System.out.println(input);
		if (input.startsWith("LOGIN")) {
			System.out.println("LOGIN");
			processLogin(input.substring(5));
		}
		if (input.startsWith("LOGUP")) {
			System.out.println("LOGUP");
			processLogup(input.substring(5));
		}
		if (input.startsWith("MAP")) {
			System.out.println("MAP");
			processMap(input.substring(3));
			System.out.println("MapReady");
		}
//		if (input.startsWith("COUNTDOWN")){
//			System.out.println("COUNTDOWN");
//			processCountdown();
//		}
		if (input.startsWith("START")) {
			processStart(input.substring(6));
		}
		if (input.startsWith("STATS")) {
			System.out.println("STATS");
			input = input.substring(5);
		}
		if (input.startsWith("READYOK")){
			System.out.println("READYOK");
			processReady();
		}
		if (input.startsWith("MOVE")){
			processMovement(input.substring(5));
		}
//		if (input.startsWith("PING"))
//			Connection.getInstance().send("PONG");
		
		if (input.startsWith("BALLDOWN"))
			processBallDown(input);
		
		if (input.startsWith("SBALLDOWN"))
			processSBallDown(input);
		
		if (input.startsWith("SCORES"))
			processScores(input.substring(6));
		if(input.startsWith("WINNER"))
			processWinner(input.substring(7));
	}

	private static void processWinner(String substring) {
		String[] lines = substring.split(" ");
		int profile = Integer.parseInt(lines[0]);
		String[] aux = new String[lines.length-1]; 
		for(int i = 1; i < lines.length; i++)
			aux[i-1] = lines[i];
		GameFrame frame = GameFrame.getInstance();
		//frame.setScorers(aux);
		frame.setWinner(profile);
	}

	private static void processScores(String substring) {
		String[] lines = substring.split(" ");
		GameFrame frame = GameFrame.getInstance();
		//frame.setScorers(lines);
	}

	// StringBuffer str = new StringBuffer("SCORES ");
	// str.append((this.totalballs - this.balls.size()) + "/" +
	// this.totalballs);
	// for (int i = 1; i < characters.size(); i++) {
	// Ghost g = (Ghost) characters.get(i);
	// str.append(" " + g.getPacmansKilled() + "-" + g.getGhostsKilled());
	// }
	// return str.toString();

	// SCORES cant_comida/cant_total pacman_muertos-fantasmas_muertos

	private static void processBallDown(String in) {
		GameFrame frame = GameFrame.getInstance();
		String[] ind = in.split(" ");
		frame.ballDown(Integer.parseInt(ind[1]));
	}
	
	private static void processSBallDown(String in) {
		GameFrame frame = GameFrame.getInstance();
		String[] ind = in.split(" ");
		frame.sBallDown(Integer.parseInt(ind[1]));
	}
	
	// private static void processCountdown() {
	// GameFrame frame = GameFrame.getInstance();
	// frame.initCountdown();
	// }

	private static void processMovement(String input) {
		String[] num = input.split(" ");
		GameFrame frame = GameFrame.getInstance();
		frame.setMovement(Integer.parseInt(num[0]), Integer.parseInt(num[1]), Integer.parseInt(num[2]),
				Integer.parseInt(num[3]), Integer.parseInt(num[4]));
	}

	private static void processReady() {
		GameFrame frame = GameFrame.getInstance();
		frame.showWaiting();
	}

	private static void processStart(String input) {
		GameFrame frame = GameFrame.getInstance();
		frame.initMatch(Parser.parseCharacters(input));
	}

	private static void processLogin(String input) {
		if (input.startsWith("OK")) {
			GameFrame frame = GameFrame.getInstance();
			frame.setUser(Parser.parseUser(input.substring(3)));
			Connection.getInstance().send("GETMAP");
			return;
		}
		JOptionPane.showMessageDialog(null,
				"No se puede ingresar." + "\nCompruebe que el nombre de usuario y la contraseña sean correctos."
						+ "\nSi lo son, su cuenta podria estar deshabilitada por el administrador.",
				"Ingresar al juego", JOptionPane.ERROR_MESSAGE);
		LogInFrame frame = new LogInFrame();
		frame.setVisible(true);
	}

	private static void processLogup(String input) {
		if (input.startsWith("OK")) {
			JOptionPane.showMessageDialog(null, "Cuenta creada con exito", "Nuevo usuario", JOptionPane.PLAIN_MESSAGE);
			return;
		}
		JOptionPane.showMessageDialog(null, "No se puede registrar.", "Nuevo usuario", JOptionPane.ERROR_MESSAGE);
		LogUpDialog frame = new LogUpDialog();
		frame.setVisible(true);
	}

	private static void processMap(String input) {
		if (input.startsWith("OK")) {
			input = input.substring(3);
			GameFrame frame = GameFrame.getInstance();
			frame.setVisible(true);
			frame.setMap(Parser.parseMap(input));
		}
	}

	public static void processStats(String input) {
		if (input.startsWith("OK")) {
			Object[][] data = Parser.parseStats(input.substring(3));
			StatsFrame frame = new StatsFrame();
			frame.load(data);
			frame.setVisible(true);
			return;
		}
		JOptionPane.showMessageDialog(null, "No se pudieron obtener las estadísticas", "Estadísticas",
				JOptionPane.ERROR_MESSAGE);
		LogInFrame frame = new LogInFrame();
		frame.setVisible(true);
	}

}
