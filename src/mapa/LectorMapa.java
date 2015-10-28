package mapa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class LectorMapa {

	public static Mapa leerMapa(String ruta){
		Mapa map = null;
	    File file= null;
	    FileReader fr= null;
	    BufferedReader br= null;
	    try {
	    	String linea;
			String[] data;
			int nf=0; 
	    	
			file = new File(ruta);
			fr = new FileReader(file);
			br = new BufferedReader(fr); 
			linea = br.readLine();
			while(linea != null){	 
				data = linea.split(" ");
			  	if(map == null){
			  		map = new Mapa(Integer.parseInt(data[0]),Integer.parseInt(data[1]));
			  	}
			  	else
			  	{
			  		map.cargarFila(nf,data);
			  		System.out.println(map.obtenerFila(nf));
			  		nf++;
			  	} 
			  	
			  	linea = br.readLine();
			}		 
		} catch (Exception e) {
			e.printStackTrace();
		}
	    finally{
	    	try {
				fr.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
	    }
		return map;
	}
}
