package client.logic;

import client.config.Config;
import client.logic.builder.UserBuilder;
import game.character.Character;
import game.character.Ghost;
import game.character.Pacman;

public class Parser {
	
	public static int[][] parseMap(String input){
		String[] lines = input.split("ELN");
		String[] tiles = null;
		int[][] map = new int[lines.length][];
		for (int i = 0; i < lines.length; i++) {
			tiles = lines[i].split(" ");
			map[i] = new int[tiles.length];
			for (int j = 0; j < tiles.length; j++) {
				map[i][j] = Integer.parseInt(tiles[j]);
			}
		}
		return map;
	}
	
	public static Object[][] parseStats(String input){
		String[] lines = input.split(" ");
		Object[][] data = new Object[lines.length / 3][4];
		for (int i = 0; i < lines.length - 3; i += 3) {
			data[i / 3][0] = lines[i + 1];
			data[i / 3][1] = new Integer(Integer.parseInt(lines[i + 2]) + Integer.parseInt(lines[i + 3]));
			data[i / 3][2] = new Integer(lines[i + 2]);
			data[i / 3][3] = new Integer(lines[i + 3]);
		}
		return data;
	}
	
	public static User parseUser(String input){
		String[] data = input.split(" ");
		return new UserBuilder(Integer.parseInt(data[1])).withNickname(data[2]).withProfile(Integer.parseInt(data[0])).build();
	}

	public static String parseTime(int time) {		
		int realtime = 2400 - time; // 2400 es la cantidad de repaints que entran en un minuto
		realtime = Math.floorDiv(realtime, 40); // 40 es la cantidad de repaints que entran en un segundo
		String parsedtime = (realtime>=60)?"01:00":"00:"+((realtime<10)?"0"+realtime:realtime);
		return parsedtime;
	}

	public static String parseProfile(int profile) {
		if(profile>=0&&profile<=4)
			return Config.get("name"+profile);
		return "ESPECTADOR";
	}

	public static Character[] parseCharacters(String input) {
		String[] lines = input.split("ELN");
		String[] c = null;
		Character[] characters = new Character[lines.length];
		for (int i = 0; i < lines.length; i++) {
			c = lines[i].split(" ");
			System.out.println("adding char");
			if(i == 0)
				characters[i] = new Pacman(Integer.parseInt(c[0]),Integer.parseInt(c[1]),
						Integer.parseInt(c[2]),Integer.parseInt(c[3]),
						Integer.parseInt(c[4]),Config.get("img_path")+Parser.parseProfile(i).toLowerCase()+".png",
						Integer.parseInt(c[5]),Integer.parseInt(c[6]));
			else
				characters[i] = new Ghost(Integer.parseInt(c[0]),Integer.parseInt(c[1]),
						Integer.parseInt(c[2]),Integer.parseInt(c[3]),
						Integer.parseInt(c[4]),Config.get("img_path")+Parser.parseProfile(i).toLowerCase()+".png",
						Integer.parseInt(c[5]),Integer.parseInt(c[6]));
		}

		System.out.println("before return");
		return characters;
	}
	
}
