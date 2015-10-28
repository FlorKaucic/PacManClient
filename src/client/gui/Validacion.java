package client.gui;

import java.util.Arrays;

public class Validacion {

	public static int isRegistValid(String usr, char [] p, char [] c) throws Exception{
		int i;
		
		if(usr.length()<1 || usr.equals(null))
			throw new Exception ("Nombre de usuario inv�lido");
		
		
		if(Arrays.equals(p, null) || p.length <1)
			throw new Exception ("Contrase�a invalida");	
		
		
		if(!Arrays.equals(p, c))
			throw new Exception ("Las contrase�as no coinciden");
		
		
		
		for(i=0; i < p.length; i++)
			if(!((p[i]>= 'a' && p[i] <= 'z') || (p[i]>= 'A' && p[i] <= 'Z') || (p[i]>= '0' && p[i] <= '9')))
				throw new Exception ("Las contrase�a contiene caracteres invalidos");
			
		if(!Arrays.equals(p, c))
			throw new Exception ("Las contrase�as no coinciden");
		
		
		return 1;
}
}
