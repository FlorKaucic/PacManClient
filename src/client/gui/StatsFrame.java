package client.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.BufferedReader;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import client.config.Config;
import client.conn.Connection;

import javax.swing.JTable;
import javax.swing.WindowConstants;

@SuppressWarnings("serial")
public class StatsFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	Object[][] data = null;

	/**
	 * Create the frame.
	 */
	public StatsFrame() {
		setTitle("PacMan - Estadisticas");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		String[] nombreColumnas = { "Usuario", "Partidas jugadas", "Partidas ganadas", "Partidas perdidas" };

		Connection.getInstance().send("GETALLSTATS");

		try {
			BufferedReader in = Connection.getInstance().getBufferedReader();
			String input;
			
			while ((input = in.readLine()) != null) {
				if (input.startsWith("STATSOK")) {
					String[] linea = input.substring(6).split(" ");
					data = new Object[linea.length/3][4];
					for (int i = 0; i < linea.length - 3; i += 3) {
						data[i/3][0] = linea[i+1];
						data[i/3][1] = new Integer(Integer.parseInt(linea[i + 2]) + Integer.parseInt(linea[i + 3]));
						data[i/3][2] = new Integer(linea[i + 2]);
						data[i/3][3] = new Integer(linea[i + 3]);
					}
					break;
				}
				if (input.startsWith("STATSFAILED")) {
					JOptionPane.showMessageDialog(null, "No se pudieron obtener las estadísticas", "Estadísticas",
							JOptionPane.ERROR_MESSAGE);
					break;
				}
			}
			
			in.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Fallo al recibir del servidor.", "Cliente", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
		}

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 422, 244);
		contentPane.add(scrollPane);

		table = new JTable(data, nombreColumnas);
		scrollPane.setViewportView(table);
		table.setRowSelectionAllowed(false);
		table.setEnabled(false);
		table.setBackground(Color.BLACK);
		table.setForeground(Color.WHITE);
		table.setFont(new Font("Arial", Font.PLAIN, 12));
		table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
	}
}
