package game.map;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import game.character.Character;

@SuppressWarnings("serial")
public class MapPanel extends JPanel {
	private int width = 0;
	private int height = 0;
	public static BufferedImage MAP_IMAGE = null;
	private int[][] map;
	Character pacman;
	boolean start = false;

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for (Component c : this.getComponents())
			c.paint(g);
		if (start)
			pacman.incVida();
	}

	public void changePacmanDir(int y) {
		pacman.setY(y);
	}

	public Character getPacman() {
		return pacman;
	}

	public void setStart() {
		start = true;
	}

	public MapPanel() {
		setLayout(null);
		
		try {
			MAP_IMAGE = ImageIO.read(new File("res/img/map.png"));
			map = LectorMapa.leerMapa("res/borrar/map_0.in");
			this.height = map.length * 50;
			this.width = map[0].length * 50;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setSize(this.width, this.height);
		
		if (map != null)
			for (int i = 0; i < 11; i++)
				for (int j = 0; j < 10; j++){
					this.add(new Dibujable(j * 50, i * 50, 50, 50, MAP_IMAGE, Math.floorDiv(map[i][j], 8)));
					int bolita = Math.floorDiv(Math.floorMod(map[i][j], 8),2);
					if(bolita==1)
						this.add(new Dibujable(j * 50 + 19, i * 50 + 19, 12, 12));
					if(bolita==2)
						this.add(new Dibujable(j * 50 + 15, i * 50 + 15, 20, 20));
				}

		pacman = new Character(228, 407, "res/img/pacman.png");

		add(pacman);

	}
}
