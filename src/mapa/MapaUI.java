package mapa;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MapaUI extends JPanel {

	int x = 0;
	int y = 0;
	Mapa map;
	private BufferedImage image;

	/** * Create the panel. */
	@Override
	protected void paintComponent(Graphics g) {
//		int c;
		super.paintComponent(g);
//
//		for (int i = 0; i < 11; i++)
//			for (int j = 0; j < 10; j++) {
//				c = map.obtenerValor(i, j) / 8;
//				// g.drawImage(image.getSubimage((c*50), 0, 50, 50), (j*50)+50,
//				// (i*50)+50, null);
////				g.drawImage(image, 
////						50 + (j * 50), 50 + (i * 50), (j * 50) + 100, (i * 50) + 100,
////						c * 50, 0, (c * 50) + 50, 50, 
////						null);
//				g.drawImage(image, 
//						j * 50, i * 50, (j+1) * 50, (i+1) * 50,
//						c * 50, 0, (c * 50) + 50, 50, 
//						null);
//			}
	}

<<<<<<< HEAD
=======
	public void movePos() {

	}

>>>>>>> branch 'master' of https://github.com/FlorKaucic/PacManClient.git
	public MapaUI() {
		setLayout(null);
		setSize(500, 550);
		
		try {
			map = LectorMapa.leerMapa("mapa.in");
			image = ImageIO.read(new File("spritesheet.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}
}
