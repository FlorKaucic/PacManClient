package game.character;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class Drawable extends Component {
	protected Image img;
	protected int posX;
	protected int posY;
	protected Integer imgX;
	protected Integer imgY;
	protected int width;
	protected int height;

	public Drawable(int posX, int posY, int width, int height) {
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
		this.img = null;
		this.imgX = null;
		this.imgY = null;
	}
	
	public Drawable(int posX, int posY, int width, int height, String img) {
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
		this.imgX = 0;
		this.imgY = 0;
		try {
			this.img = ImageIO.read(new File(img));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "No se pudo cargar la imagen.", "Error de Juego", JOptionPane.ERROR_MESSAGE);
		};
	}

	public Drawable(int posX, int posY, int width, int height, String img, int value) {
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
		this.imgX = value * width;
		this.imgY = 0;
		try {
			this.img = ImageIO.read(new File(img));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "No se pudo cargar la imagen.", "Error de Juego", JOptionPane.ERROR_MESSAGE);
		};
	}
	
	public void paint(Graphics g) {
		if(this.img != null){			
			g.drawImage(this.img, 
				this.posX, this.posY, this.posX + this.width, this.posY + this.height,
				this.imgX, this.imgY, this.imgX + this.width, this.imgY + this.height,
				null);
			return;
		}
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//		if(efec>0){		
			g.setColor(Color.ORANGE);
//		}
//		else{
//			g.setColor(Color.WHITE);
//		}
//		efec++;
//		if(efec==15)
//			efec = -15;
		
		g.fillOval(posX, posY, width, height);
	}
	

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
}
