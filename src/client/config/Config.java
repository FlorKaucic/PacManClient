package client.config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
	
	public static void save(){
		OutputStream output = null;
		
		try {
			output = new FileOutputStream("config.properties");
			CONFIG.store(output, null);
		} catch (IOException io) {
			JOptionPane.showMessageDialog(null, "No se pudieron guardar los cambios en la configuracion.", "Error", JOptionPane.ERROR_MESSAGE);
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
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
