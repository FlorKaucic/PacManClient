package client.conn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	public static void main(String[] args) {
		Socket socket = null;
		PrintWriter out = null;
		BufferedReader in = null;

		try {
			socket = new Socket("localhost", 4444);
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

//			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
//			String fromServer;
//			String fromUser;
			
//			while ((fromServer = in.readLine()) != null) {
//				System.out.println("Server: " + fromServer);
//				if (fromServer.equals("Se recibio: fin"))
//					break;
//
//				fromUser = stdIn.readLine();
//				if (fromUser != null) {
//					System.out.println("Client: " + fromUser);
//					out.println(fromUser);
//				}
//			}
			
			out.close();
			in.close();
//			stdIn.close();
			socket.close();
		}catch (UnknownHostException e){
			System.err.println("Host desconocido.\n"+e);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Error al conectar con el host solicitado.\n"+e);
			System.exit(1);
		}
	}
}
