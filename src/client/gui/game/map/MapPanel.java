package client.gui.game.map;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import client.config.Config;
import client.conn.Connection;
import game.character.Character;
import game.character.Drawable;

@SuppressWarnings("serial")
public class MapPanel extends JPanel {
	private int width = 0;
	private int height = 0;
	public static BufferedImage MAP_IMAGE = null;
	private int[][] map;
	LinkedList <Dibujable> balls = new LinkedList<Dibujable>();
	LinkedList <Dibujable> sballs = new LinkedList<Dibujable>();
	Character[] characters;
	boolean start = false;
	int me;
	int dir;
	int count = 0;

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for (Component c : this.getComponents())
			c.paint(g);
		if (start) {
			if (count == 10) {
				count = 0;
				for (Character c : characters)
					c.incLife();
			}
			count++;
		}
	}

	public MapPanel(int[][] map) {
		this.map = map;

		setLayout(null);

		try {
			MAP_IMAGE = ImageIO.read(new File(Config.get("tile_img")));
			this.height = map.length * Integer.parseInt(Config.get("tile_heigth"));
			this.width = map[0].length * Integer.parseInt(Config.get("tile_width"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setSize(this.width, this.height);

		if (map != null)
			for (int i = 0; i < 11; i++)
				for (int j = 0; j < 10; j++) {
					this.add(new Dibujable(j * 50, i * 50, 50, 50, MAP_IMAGE, Math.floorDiv(map[i][j], 8)));
					int bolita = Math.floorDiv(Math.floorMod(map[i][j], 8), 2);
					if (bolita == 1){
						balls.add(new Dibujable(j * 50 + 19, i * 50 + 19, 8, 8));
						this.add(balls.getLast());
					}
					if (bolita == 2){
						sballs.add(new Dibujable(j * 50 + 15, i * 50 + 15, 15, 15));
						this.add(sballs.getLast());
					}
				}
		this.characters = null;

		// characters = new ArrayList<Character>();
		// characters.add(new Pacman(235, 410, 30, 30, 2, "res/img/pacman.png",
		// 4, 20));
		// for (Character c : characters)
		// add(c);
		
	}

	public void addCharacters(Character[] characters) {
		this.characters = characters;
		for (Character c : this.characters)
			add(c);
	}

	public void update() {
		if (characters != null)
			for (Character c : characters) {
				c.update();
				// int i = c.getPosY() / 50;
				// i = (i >= map.length) ? map.length - 1 : ((i <= 0) ? 0 : i);
				// int j = c.getPosX() / 50;
				// j = (j >= map[0].length) ? map[0].length - 1 : ((j <= 0) ? 0
				// : j);
				// int path = map[i][j] / 8;
				// c.update(path,dir);
				c.incLife();
			}
	}

	public void setMe(int me) {
		this.me = me;
	}

	public void moveCharacter(int dir) {
		Connection.getInstance().send("MOVE " + me + " " + dir);
	}

	public void setMovement(int i, int x, int y, int dx, int dy) {
		this.characters[i].setPosX(x);
		this.characters[i].setPosY(y);
		this.characters[i].setDesX(dx);
		this.characters[i].setDesY(dy);
		if (dx != 0 || dy != 0) {
			this.characters[i].setImgY();
			this.characters[i].setMoving(true);
		} else if(dx==0 && dy==0)
			this.characters[i].setMoving(false);
	}

	public void setDir(int i) {
		this.dir = i;
	}

	public void removeBall(int i) {
		this.remove(balls.get(i));
	}
	public void removeSBall(int i) {
		this.remove(sballs.get(i));
	}

	public void setRespawn(int i, int x, int y, int dx, int dy) {
		this.characters[i].setPosX(x);
		this.characters[i].setPosY(y);
		this.characters[i].setDesX(dx);
		this.characters[i].setDesY(dy);
//		if (dx != 0 || dy != 0) {
//			this.characters[i].setImgY();
//			this.characters[i].setMoving(true);
//		} else
		this.characters[i].setMoving(false);
		
	}

	// public void rotateCharacter(int dir) {
	// Character mypj = characters.get(me);
	// if (mypj.canTurn(dir, map[mypj.getPosY() / 50][mypj.getPosX() / 50] / 8))
	// {
	// if (dir == 0 || dir == 2) {
	// mypj.setDesX(dir - 1);
	// mypj.setDesY(0);
	// }
	// if (dir == 1 || dir == 3) {
	// mypj.setDesY(dir - 2);
	// mypj.setDesX(0);
	// }
	// mypj.setImgY(dir);
	// }
	// }

}
