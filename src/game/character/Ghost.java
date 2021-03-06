package game.character;

@SuppressWarnings("serial")
public class Ghost extends Character {
	private int pacmansKilled;
	private int ghostsKilled;
	
	public Ghost(int posX, int posY, int width, int height, int vel, String img, int lifeSpan, int powerSpan) {
		super(posX, posY, width, height, vel, img, lifeSpan, powerSpan);
		this.pacmansKilled = 0;
		this.ghostsKilled = 0;
	}
	
	public void killedPacman(){
		this.pacmansKilled++;
	}
	
	public void killedGhost(){
		this.ghostsKilled++;
	}
	
	public int getPacmansKilled(){
		return this.pacmansKilled;
	}
	
	public int getGhostsKilled(){
		return this.ghostsKilled;
	}
	
}
