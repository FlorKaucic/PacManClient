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
	
	public void movimientos(int dir){
		int resX1=0, resY1=0, resX2, resY2, camino;
		

		pacman.changePos(dir);
		
		resX1 = pacman.getPosX()%50;
		resX2 = (pacman.getPosX()+pacman.getAncho())%50;
		resY1 = pacman.getPosY()%50;
		resY2 = (pacman.getPosY()+pacman.getLargo())%50;
		
		
		if(pacman.getPosX()+pacman.getAncho()>=0 && pacman.getPosX() <=500){
			if (resX1<=5 && dir ==0){
				camino = map[pacman.getPosY()/50][pacman.getPosX()/50]/8;	
				if((pacman.getPosY()/50 != (pacman.getPosY()+pacman.getLargo())/50) ||
				   (!puedeSeguir(dir, camino))){
					pacman.setVel(0);
					pacman.setPosX(pacman.getPosX()+5-resX1);
					return;
				}
			}
			else
			if(resX2>= 45 && dir==2){
				camino = map[pacman.getPosY()/50][pacman.getPosX()/50]/8;	
				if((pacman.getPosY()/50 != (pacman.getPosY()+pacman.getLargo())/50) ||
				   (!puedeSeguir(dir, camino))){
					pacman.setVel(0);
					pacman.setPosX(pacman.getPosX()-(resX2-45));
					return;
				}
			}
			else
			if(resY1 <= 5 && dir==1){
				camino = map[pacman.getPosY()/50][pacman.getPosX()/50]/8;	
				if((pacman.getPosX()/50 != (pacman.getPosX()+pacman.getAncho())/50) ||
				   (!puedeSeguir(dir, camino))){
					pacman.setVel(0);
					pacman.setPosY(pacman.getPosY()+5-resY1);
					return;
				}
			}else
			if(resY2 >= 45 && dir==3){
				camino = map[pacman.getPosY()/50][pacman.getPosX()/50]/8;	
				if((pacman.getPosX()/50 != (pacman.getPosX()+pacman.getAncho())/50) ||
				   (!puedeSeguir(dir, camino))){
					pacman.setVel(0);
					pacman.setPosY(pacman.getPosY()-(resY2-45));
					System.out.println("resX1: " + resX1 + " - resX2: " + resX2+
							" - resY1: " + resY1 + " - resY2: " + resY2+ " - camino: " + camino +
							" - difY1: " + (pacman.getPosY()/50) + " - difY2: " + ((pacman.getPosY()+pacman.getLargo())/50) +
							" - difX1: " + (pacman.getPosX()/50) + " - difX2: " +((pacman.getPosX()+pacman.getLargo())/50));
					return;
				}		
			}
			else{
				pacman.setVel(4);
				
				
			}
		}
		else if(pacman.getPosX()+pacman.getAncho()<0)
			pacman.setPosX(500);
			else
				pacman.setPosX(-pacman.getAncho());
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
						this.add(new Dibujable(j * 50 + 19, i * 50 + 19, 8, 8));
					if(bolita==2)
						this.add(new Dibujable(j * 50 + 15, i * 50 + 15, 15, 15));
				}

		pacman = new Character(228, 407, "res/img/pacman.png");

		add(pacman);
		
	}
	public boolean puedeSeguir(int dir, int cam){
		if(dir==0)
			if(cam==2 || cam==3 || cam==5 || cam==8)
			return false;
		if(dir==3) 
			if(cam==1 || cam==5 || cam==6 || cam==9)
			return false;
		if(dir==2) 
			if(cam==2 || cam==4 || cam==6 || cam==7)
			return false;
		if(dir==1)
			if(cam==1 || cam==3 || cam==4 || cam==10)
			return false;
		
		
		return true;
	}
	
}
