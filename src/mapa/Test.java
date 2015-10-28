package mapa;

public class Test {

	public static void main(String[] args) {
/*		char c = (int) 65;
		int a = (int) c;
		if(c == a)
			System.out.println("MATCH!" + a + c);
		byte b = 65;
		c = (char) b;
		System.out.println(c);
        a/=5;
        b = (byte) a;
        System.out.println(b + a);// b+ " " + a*/
		Mapa map = null;
		GenMapa gm = new GenMapa();
		gm.escribirMapa(11, 10, "mapa.in");
		map = LectorMapa.leerMapa("mapa.in");
	    
	}

}
