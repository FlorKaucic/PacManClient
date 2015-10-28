package mapa;

import java.awt.Image;
import java.io.FileWriter;
import java.io.PrintWriter;

public class GenMapa {
	Mapa map = null;
	
    private void crearMapa(int f, int c){ //carga toda la matriz
    	map = new Mapa(f,c);
    	map.agregarPos(0,0,3,1,1);
    	map.agregarPos(0,1,1,1,0);
    	map.agregarPos(0,2,10,1,0);
    	map.agregarPos(0,3,1,1,0);
    	map.agregarPos(0,4,4,1,0);
    	map.agregarPos(0,5,3,1,0);
    	map.agregarPos(0,6,1,1,0);
    	map.agregarPos(0,7,10,1,0);
    	map.agregarPos(0,8,1,1,0);
    	map.agregarPos(0,9,4,1,1);
    		
    	map.agregarPos(1,0,2,2,0);
    	map.agregarPos(1,1,0,0,0);
    	map.agregarPos(1,2,2,1,0);
    	map.agregarPos(1,3,0,0,0);
    	map.agregarPos(1,4,2,1,0);
    	map.agregarPos(1,5,2,1,0);
    	map.agregarPos(1,6,0,0,0);
    	map.agregarPos(1,7,2,1,0);
    	map.agregarPos(1,8,0,0,0);
    	map.agregarPos(1,9,2,2,0);

    	map.agregarPos(2,0,8,1,0);
    	map.agregarPos(2,1,1,1,0);
    	map.agregarPos(2,2,11,1,0);
    	map.agregarPos(2,3,10,1,0);
    	map.agregarPos(2,4,9,1,0);
    	map.agregarPos(2,5,9,1,0);
    	map.agregarPos(2,6,10,1,0);
    	map.agregarPos(2,7,11,1,0);
    	map.agregarPos(2,8,1,1,0);
    	map.agregarPos(2,9,7,1,0);

    	map.agregarPos(3,0,5,1,0);
    	map.agregarPos(3,1,1,1,0);
    	map.agregarPos(3,2,7,1,0);
    	map.agregarPos(3,3,5,1,0);
    	map.agregarPos(3,4,4,1,0);
    	map.agregarPos(3,5,3,1,0);
    	map.agregarPos(3,6,6,1,0);
    	map.agregarPos(3,7,8,1,0);
    	map.agregarPos(3,8,1,1,0);
    	map.agregarPos(3,9,6,1,0);

    	map.agregarPos(4,0,0,0,0);
    	map.agregarPos(4,1,0,0,0);
    	map.agregarPos(4,2,2,1,0);
    	map.agregarPos(4,3,3,0,0);
    	map.agregarPos(4,4,9,0,0);
    	map.agregarPos(4,5,9,0,0);
    	map.agregarPos(4,6,4,0,0);
    	map.agregarPos(4,7,2,1,0);
    	map.agregarPos(4,8,0,0,0);
    	map.agregarPos(4,9,0,0,0);

    	map.agregarPos(5,0,12,0,0);
    	map.agregarPos(5,1,1,0,0);
    	map.agregarPos(5,2,11,1,0);
    	map.agregarPos(5,3,7,0,0);
    	map.agregarPos(5,4,13,0,0);
    	map.agregarPos(5,5,14,0,0);
    	map.agregarPos(5,6,8,0,0);
    	map.agregarPos(5,7,11,1,0);
    	map.agregarPos(5,8,1,0,0);
    	map.agregarPos(5,9,12,0,0);

    	map.agregarPos(6,0,0,0,0);
    	map.agregarPos(6,1,0,0,0);
    	map.agregarPos(6,2,2,1,0);
    	map.agregarPos(6,3,8,0,0);
    	map.agregarPos(6,4,1,0,0);
    	map.agregarPos(6,5,1,0,0);
    	map.agregarPos(6,6,7,0,0);
    	map.agregarPos(6,7,2,1,0);
    	map.agregarPos(6,8,0,0,0);
    	map.agregarPos(6,9,0,0,0);

    	map.agregarPos(7,0,3,1,0);
    	map.agregarPos(7,1,1,1,0);
    	map.agregarPos(7,2,11,1,0);
    	map.agregarPos(7,3,9,1,0);
    	map.agregarPos(7,4,4,1,0);
    	map.agregarPos(7,5,3,1,0);
    	map.agregarPos(7,6,9,1,0);
    	map.agregarPos(7,7,11,1,0);
    	map.agregarPos(7,8,1,1,0);
    	map.agregarPos(7,9,4,1,0);

    	map.agregarPos(8,0,5,2,0);
    	map.agregarPos(8,1,4,1,0);
    	map.agregarPos(8,2,8,1,0);
    	map.agregarPos(8,3,10,1,0);
    	map.agregarPos(8,4,9,1,0);
    	map.agregarPos(8,5,9,1,0);
    	map.agregarPos(8,6,10,1,0);
    	map.agregarPos(8,7,7,1,0);
    	map.agregarPos(8,8,3,1,0);
    	map.agregarPos(8,9,6,2,0);

    	map.agregarPos(9,0,3,1,0);
    	map.agregarPos(9,1,9,1,0);
    	map.agregarPos(9,2,6,1,0);
    	map.agregarPos(9,3,5,1,0);
    	map.agregarPos(9,4,4,1,0);
    	map.agregarPos(9,5,3,1,0);
    	map.agregarPos(9,6,6,1,0);
    	map.agregarPos(9,7,5,1,0);
    	map.agregarPos(9,8,9,1,0);
    	map.agregarPos(9,9,4,1,0);

    	map.agregarPos(10,0,5,1,1);
    	map.agregarPos(10,1,1,1,0);
    	map.agregarPos(10,2,1,1,0);
    	map.agregarPos(10,3,1,1,0);
    	map.agregarPos(10,4,9,1,0);
    	map.agregarPos(10,5,9,1,0);
    	map.agregarPos(10,6,1,1,0);
    	map.agregarPos(10,7,1,1,0);
    	map.agregarPos(10,8,1,1,0);
    	map.agregarPos(10,9,6,1,0);


	}
	public void escribirMapa(int nf, int nc, String ruta){ //guarda la matriz, creandola previamente con los metodos privados.
		FileWriter fw = null;
		PrintWriter pw = null;
		try {
			String linea;
			fw = new FileWriter(ruta);
		    pw = new PrintWriter(fw);
		    
		    this.crearMapa(nf, nc); // 11 x 10, nf=11, nc=10
		    /*for(int i=0;i<11;i++)
		    	System.out.println(map.obtenerFila(i));*/
		    pw.println(nf+" "+nc);
		    for(int i=0;i<nf;i++)
		    	pw.println(map.obtenerFila(i));
		    		
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			try {
				fw.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
}
