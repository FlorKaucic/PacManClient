package client.conn;

public class ClientProtocol {
	// Registrar usuario:
		// envia
		// LOGUP username password
		// recibe
		// LOGUPOK PACMAN/GHOST id nickname
		// LOGUPFAILED [...]
		
		// Ingresar usuario:
		// envia
		// LOGIN username password
		// recibe
		// LOGINOK PACMAN/GHOST id nickname
		// LOGINFAILED [...]
			
		
		public static void processInput(String input){
			if(input.startsWith("LOGUPOK") || input.startsWith("LOGINOK")){
				Connection.getInstance().setStatus("LOGGED");
			}
			Connection.getInstance().setStatus("FAILED");
		}
}
