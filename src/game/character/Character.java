package game.character;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.map.MapPanel;

@SuppressWarnings("serial")
public class Character extends Component {
	private int posX;
	private int posY;
	private Image img;
	private final int ancho = 40;
	private final int largo = 40;
	private int imgX;
	private int imgY;
	private int vida;
	private final int vidatotal = 4;

	public Character(int posX, int posY, String img) {
		this.posX = posX;
		this.posY = posY;
		this.vida = 0;
		this.imgX = 0;
		this.imgY = 0;
		try {
			this.img = ImageIO.read(new File(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void paint(Graphics g) {
		g.drawImage(this.img, 
				this.posY, this.posX, this.posY + this.largo, this.posX + this.ancho,
				this.imgX + vida * this.ancho, this.imgY, this.imgX + (vida + 1) * this.ancho, this.imgY + this.largo, 
				null);
	}
	
	public void incVida(){
		vida++;
		if(vida==vidatotal)
			vida = 0;
	}
	
	public void setY(int y){
		this.imgY = this.largo * y;
	}
}
