package game.map;

import java.awt.Component;
import java.awt.Graphics;

@SuppressWarnings("serial")
public class Tile extends Component {
	private int valor = 0;
	private int x = 0;
	private int y = 0;

	public Tile(int x, int y, int valor) {
		this.x = x;
		this.y = y;
		this.valor = valor;
	}
	
	public void paint(Graphics g) {
		g.drawImage(MapPanel.IMAGE, 
				this.y, this.x, this.y+50, this.x+50,
				this.valor * 50, 0, (this.valor + 1) * 50, 50, 
				null);
	}

}
