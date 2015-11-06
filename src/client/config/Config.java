package client.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.Properties;

import javax.swing.JOptionPane;

public class Config {
	private static Properties CONFIG;
	
	public static void load(){
		CONFIG = new Properties();
		InputStream input = null;

		try {
			input = new FileInputStream("config.properties");
			CONFIG.load(input);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se puede cargar properties.", "Cliente", JOptionPane.ERROR_MESSAGE);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "No se puede cerrar Input Properties.", "Cliente", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	
	public static String get(String key){
		return (String) CONFIG.get(key);
	}
	
	public static void set(String key, String value){
		CONFIG.put(key, value);
	}
	
}
