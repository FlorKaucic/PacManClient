package game.character;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

@SuppressWarnings("serial")
public class Character extends Component {
	private int posX;
	private int posY;
	private Image img;
	private final int ancho = 30;
	private final int largo = 30;
	private int imgX;
	private int imgY;
	private int vida;
	private final int vidatotal = 4;
	private int dir;
	private int vel; //esto iria en la clase de cada personaje
	
	public Character(int posX, int posY, String img) {
		this.posX = posX;
		this.posY = posY;
		this.vida = 1;
		this.imgX = 0;
		this.imgY = 0;
		vel = 4;
		try {
			this.img = ImageIO.read(new File(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}
	
	public int getDir(){
		return dir;
	}

	public void setPosX(int i) {
		posX = i;
	}

	public void setPosY(int i) {
		posY = i;
	}
	
	public void setVel(int v){
		vel = v;
	}
	
	public int getLargo(){
		return largo;
	}
	public int getAncho(){
		return ancho;
	}

	public void changePos(int dir) {
		if (dir == 0)
			this.setPosX(this.getPosX() - vel);

		if (dir == 1)
			this.setPosY(this.getPosY() - vel);

		if (dir == 2)
			this.setPosX(this.getPosX() + vel);

		if (dir == 3)
			this.setPosY(this.getPosY() + vel);
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(this.img, this.posX, this.posY, this.posX + this.largo, this.posY + this.ancho,
				this.imgX + vida * this.ancho, this.imgY, this.imgX + (vida + 1) * this.ancho, this.imgY + this.largo,
				null);
	}

	public void incVida() {
		vida++;
		if (vida == vidatotal)
			vida = 0;
	}

	public void setY(int y) {
		this.imgY = this.largo * y;
		dir = y;
	}
}
