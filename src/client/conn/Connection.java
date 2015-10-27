package client.conn;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Properties;


public class Connection {
	public static int PORT = 0;
	public static InetAddress IP = null;
	
	private static Connection INSTANCE = null;
	
	private Socket socket = null;
	private PrintWriter out = null;
	private BufferedReader in = null;

	private Connection() throws IOException {
		configurate();
		System.out.println("Cargado "+Connection.IP+":"+Connection.PORT);
		socket = new Socket(Connection.IP, Connection.PORT);
		out = new PrintWriter(socket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		createListener();
	}
	
	public static Connection getInstance() {
		if (INSTANCE == null) {
			try{
				INSTANCE = new Connection();
			}catch(IOException e){
				System.out.println("No se puede establecer la conexion con "+Connection.IP+":"+Connection.PORT);
				e.printStackTrace();
			}
		}
		return INSTANCE;
	}
	
	public void send(String message){
		out.println(message);
	}

	public void createListener(){
		Thread t = new ServerListener(this.in);
		t.start();
	}

	private void configurate() throws IOException {
		Properties prop = new Properties();
		InputStream input = null;

		input = new FileInputStream("config.properties");
		prop.load(input);
		Connection.IP = InetAddress.getByName(prop.getProperty("ip"));
		Connection.PORT = Integer.parseInt(prop.getProperty("port"));
	}
	
	
}
