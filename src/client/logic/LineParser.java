package client.logic;

import client.logic.builder.UserBuilder;

public class LineParser {
	
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
		return new UserBuilder(Integer.parseInt(data[1])).withNickname(data[2]).withProfile(data[0]).build();
	}
	
}
