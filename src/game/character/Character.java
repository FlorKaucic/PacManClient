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
	
	public void checkPos(int path) {
		if (this.posX + this.width >= 0 && this.posX <= 500) {
			if (this.posX % 50 <= 5 && desX < 0 && path != 2 && path != 3 && path != 5 && path != 8) {
				// Si va a la izq y esta a la altura de una posible pared	
				this.desX = 0;
				this.posX = this.posX + 5 - this.posX % 50;
				this.moving = false;
				return;
			}
			if ((this.posX + this.width) % 50 >= 45 && desX > 0 && path != 1 && path != 5 && path != 6 && path != 9) {
				// Si va a la der y esta a la altura de una posible pared
				this.desX = 0;
				this.posX = this.posX - ((this.posX + this.width) % 50 - 45);
				this.moving = false;
				return;
			}
			if (this.posY % 50 <= 5 && desY < 0 && path != 2 && path != 4 && path != 6 && path != 7) {
				// Si va hacia arriba y esta a la altura de una posible pared
				this.desY = 0;
				this.posY = this.posY + 5 - this.posY % 50;
				this.moving = false;
				return;
			}
			if ((this.posY + this.height) % 50 >= 45 && desY > 0 && path != 1 && path != 3 && path != 4 && path != 10) {
				// Si va hacia abajo y esta a la altura de una posible pared
				this.desY = 0;
				this.posY = this.posY - ((this.posY + this.height) % 50 - 45);
				this.moving = false;
				return;
			}
			if (fixRoute())
				return;

			this.desX = 0;
			this.desY = 0;
			this.moving = false;
		}
		if (this.posX + this.width < 0)
			this.posX = 500;
		else
			this.posX = -this.width;
	}

	private boolean fixRoute() {
		if (this.posX % 50 <= (50 / 2) && desX != 0) {
			this.posX = (this.posX / 50) * 50 + (50 - this.width) / 2;
			return true;
		}
		if (this.posY % 50 <= (50 / 2) && desY != 0) {
			this.posY = (this.posY / 50) * 50 + (50 - this.height) / 2;
			return true;
		}
		return false;
	}
	
	public boolean canTurn(int dir, int path) {
		if (this.posX % 50 > (50 / 2) && this.posY % 50 < 11 && 
				((dir == 1 && (path == 1 || path == 3 || path == 4 || path == 10)))	|| 
				(dir == 3 && (path == 1 || path == 5 || path == 6 || path == 9)))
			return false;

		if (this.posY % 50 > (50 / 2) && this.posX % 50 < 11 && 
				((dir == 0 && (path == 2 || path == 3 || path == 5 || path == 8) ))	|| 
				(dir == 2 && (path == 2 || path == 4 || path == 6 || path == 7)))
			return false;
		return true;
	}

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
