package game.gui.map;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import client.config.Config;
import client.conn.ClientProtocol;
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

	public void setStart(boolean b) {
		start = b;
	}
	
	public void movimientos(int dir){
		int resX1=0, resY1=0, resX2, resY2, camino;
				
		resX1 = pacman.getPosX()%50;
		resX2 = (pacman.getPosX()+pacman.getAncho())%50;
		resY1 = pacman.getPosY()%50;
		resY2 = (pacman.getPosY()+pacman.getLargo())%50;
		

		
		if(pacman.getPosX()+pacman.getAncho()>=0 && pacman.getPosX() <=500){
			if (resX1<=5 && dir ==0){
				camino = map[pacman.getPosY()/50][pacman.getPosX()/50]/8;	
				if(/*(pacman.getPosY()/50 != (pacman.getPosY()+pacman.getLargo())/50) ||*/
				   (!puedeSeguir(dir, camino))){
					pacman.setVel(0);
					pacman.setPosX(pacman.getPosX()+5-resX1);
					this.setStart(false);
					return;
				}
			}
			else
			if(resX2>= 45 && dir==2){
				camino = map[pacman.getPosY()/50][pacman.getPosX()/50]/8;	
				if(/*(pacman.getPosY()/50 != (pacman.getPosY()+pacman.getLargo())/50) ||*/
				   (!puedeSeguir(dir, camino))){
					pacman.setVel(0);
					pacman.setPosX(pacman.getPosX()-(resX2-45));
					this.setStart(false);
					return;
				}
			}
			else
			if(resY1 <= 5 && dir==1){
				camino = map[pacman.getPosY()/50][pacman.getPosX()/50]/8;	
				if(/*(pacman.getPosX()/50 != (pacman.getPosX()+pacman.getAncho())/50) ||*/
				   (!puedeSeguir(dir, camino))){
					pacman.setVel(0);
					pacman.setPosY(pacman.getPosY()+5-resY1);
					this.setStart(false);
					return;
				}
			}else
			if(resY2 >= 45 && dir==3){
				camino = map[pacman.getPosY()/50][pacman.getPosX()/50]/8;	
				if(/*(pacman.getPosX()/50 != (pacman.getPosX()+pacman.getAncho())/50) ||*/
				   (!puedeSeguir(dir, camino))){
					pacman.setVel(0);
					pacman.setPosY(pacman.getPosY()-(resY2-45));
					this.setStart(false);
					return;
				}		
			}
			else{
				
				if(this.corregirCurso(dir)){
					pacman.setVel(4);
					
				}
					else{
					pacman.setVel(0);
					this.setStart(false);
					}
			}
				
			
		}
		else if(pacman.getPosX()+pacman.getAncho()<0)
			pacman.setPosX(500);
			else
				pacman.setPosX(-pacman.getAncho());
		
		
		pacman.changePos(dir);	
	}
	
	private boolean corregirCurso(int dir){
		
		if(pacman.getPosX()%50<=(50/2) && (dir==1 || dir==3)){
			pacman.setPosX((pacman.getPosX()/50)*50+(50-pacman.getAncho())/2);
			return true;
		}
		if(pacman.getPosY()%50<=(50/2) && (dir==0 || dir==2)){
			pacman.setPosY((pacman.getPosY()/50)*50+(50-pacman.getLargo())/2);
			return true;
		}

		return false;
	}

	public MapPanel() {
		setLayout(null);
		
		try {
			MAP_IMAGE = ImageIO.read(new File(Config.get("tile_img")));
			map = ClientProtocol.readMap();
			this.height = map.length * Integer.parseInt(Config.get("tile_heigth"));
			this.width = map[0].length * Integer.parseInt(Config.get("tile_width"));
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

		pacman = new Character(228, 407, Config.get("pacman_img"));

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
	
	public boolean puedeGirar(int dir){
		int cam = map[pacman.getPosY()/50][pacman.getPosX()/50]/8; 
		
		if(pacman.getPosX()%50>(50/2) && (dir==1 || dir==3))
			return false;
		
		if(pacman.getPosY()%50>(50/2) && (dir==0 || dir==2))
			return false;
		
		if(dir==0)
			if((cam==2 || cam==3 || cam==5 || cam==8) && pacman.getPosX()%50<11)  
			return false;
		if(dir==3) 
			if(cam==1 || cam==5 || cam==6 || cam==9 && pacman.getPosY()%50<11)
			return false;
		if(dir==2) 
			if(cam==2 || cam==4 || cam==6 || cam==7 && pacman.getPosX()%50<11)
			return false;
		if(dir==1)
			if(cam==1 || cam==3 || cam==4 || cam==10 && pacman.getPosY()%50<11)
			return false;
		

		return true;
	
		
	}
}
