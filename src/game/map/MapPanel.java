package game.map;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MapPanel extends JPanel {
	private final int width = 500;
	private final int height = 550;
	public static BufferedImage IMAGE;
	private int[][] map;
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		for (Component c : this.getComponents())
			c.paint(g);
	}

	public void movePos() {

	}

	public MapPanel() {
		setLayout(null);
		setSize(this.width, this.height);
		
		try {
			IMAGE = ImageIO.read(new File("map.png"));
			map = LectorMapa.leerMapa("map_0.in");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(map!=null)
			for (int i = 0; i < 11; i++)
				for (int j = 0; j < 10; j++)
					this.add(new Tile(i*50,j*50, map[i][j]));

	}
}
