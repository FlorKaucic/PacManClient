package mapa;
/*Div es división entera
Mod es calcular el resto 
8 es el valor del bit 3
2 es el valor del bit 1
37 div 8 = camino 
(37 mod 8) div 2 = bolita
(37 mod 8) mod 2 = generador
*/
public class Mapa {
		private int dimF;
		private int dimC;
		private byte[][] mapa = null;
		
		public Mapa(int df,int dc){
			dimF = df;
			dimC = dc;
			mapa = new byte[df][dc];
		}
		public void agregarPos(int i, int j, int camino, int bolita, int gen){ //carga un miembro de la matriz
			mapa[i][j] = (byte)((camino*8)+((bolita*2)+gen)); 
		}
		
		public void cargarFila(int nf,String[] val){
			for(int i=0;i<val.length;i++)
			{
				mapa[nf][i] = Byte.parseByte(val[i]);
			}
		}
		
		public byte obtenerValor(int i, int j){
			return mapa[i][j];
		}
		
		public String obtenerFila(int nf){
			String fila="" + mapa[nf][0];
			for(int j=1;j<10;j++)
				fila+=" " + mapa[nf][j];
			return fila;
		}
}
