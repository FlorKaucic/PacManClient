package game.character;

import java.awt.Graphics;

@SuppressWarnings("serial")
public class Character extends Drawable {
	protected int desX;
	protected int desY;
	protected int vel;
	protected int power;
	protected int powerSpan;
	protected int life;
	protected int lifeSpan;
	protected boolean moving;

	public Character(int posX, int posY, int width, int height, int vel, String img, int lifeSpan, int powerSpan) {
		super(posX, posY, width, height, img);
		this.vel = vel;
		this.moving = false;
		this.life = 1;
		this.power = -1;
		this.lifeSpan = lifeSpan;
		this.powerSpan = powerSpan;
	}

	public void update() {
		this.posX += this.vel * this.desX;
		this.posY += this.vel * this.desY;
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(this.img, this.posX, this.posY, this.posX + this.width, this.posY + this.height,
				this.imgX + life * this.width, this.imgY, this.imgX + (life + 1) * this.width, this.imgY + this.height,
				null);
	}
	
//	public void checkPos(int path) {
//		if (this.posX + this.width < 0){
//			this.posX = 500;
//			return;
//		}
//		if (this.posX > 500){
//			this.posX = -this.width;
//			return;
//		}
//		
//		int x, y;
//		x = this.posX % 50; // posicion x dentro del cuadrado
//		y = this.posY % 50; // posicion y dentro del cuadrado
//		
//		if (desX < 0 && x < 5 && !canGoLeft(path)) {
//			// Si va a la izq y esta a la altura de una posible pared	
//			this.desX = 0;
//			this.posX = this.posX - x + 5;
//			return;
//		}
//		if (desX > 0 && x + this.width > 45 && !canGoRigth(path)) {
//			// Si va a la izq y esta a la altura de una posible pared	
//			this.desX = 0;
//			this.posX = this.posX - (x + this.width) + 45;
//			return;
//		}
//		if (desY < 0 && y < 5 && !canGoUp(path)) {
//			// Si va a la izq y esta a la altura de una posible pared	
//			this.desY = 0;
//			this.posY = this.posY - y + 5;
//			return;
//		}
//		if (desY > 0 && y + this.width > 45 && !canGoDown(path)) {
//			// Si va a la izq y esta a la altura de una posible pared	
//			this.desY = 0;
//			this.posY = this.posY - (y + this.height) + 45;
//			return;
//		}
//		
//		if (desY != 0 && x <= (this.width / 2)) {
//			this.posX = (this.posX / 50) * 50 + (50 - this.width) / 2;
//			return;
//		}
//		if (desX != 0 && y <= (this.height / 2)) {
//			this.posY = (this.posY / 50) * 50 + (50 - this.height) / 2;
//			return;
//		}
//		
//		this.desX = 0;
//		this.desY = 0;
//		
//	}
//	
//	private boolean canGoLeft(int path){
//		return !(path == 2 || path == 3 || path == 5 || path == 8);
//	}
//	
//	private boolean canGoRigth(int path){
//		return !(path == 2 || path == 4 || path == 6 || path == 7);
//	}
//	
//	private boolean canGoUp(int path){
//		return !(path == 1 || path == 3 || path == 4 || path == 10);
//	}
//	
//	private boolean canGoDown(int path){
//		return !(path == 1 || path == 5 || path == 6 || path == 9);
//	}
	
//	public boolean canTurn(int dir, int path) {
//		if (this.posX % 50 > (50 / 2) && this.posY % 50 < 11 && 
//				(dir == 1 && canGoUp(path)) || (dir == 3 && canGoDown(path)))
//			return true;
//
//		if (this.posY % 50 > (50 / 2) && this.posX % 50 < 11 &&
//				(dir == 0 && canGoLeft(path)) || (dir == 2 && canGoRigth(path)))
//			return true;
//		
//		return false;
//	}

	public void respawn() {
		this.posX = 50;
		this.posY = 50;
	}

	public void setDesX(int desX) {
		this.desX = desX;
	}

	public void setDesY(int desY) {
		this.desY = desY;
	}

	public void incLife() {
		life++;
		if (life == lifeSpan)
			life = 0;
	}

	public void setImgY(int y) {
		this.imgY = this.width * y;
	}
}
