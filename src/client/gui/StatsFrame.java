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
	 * Launch the application.
	 */

	public static void main(String[] args) {
		Config.load();

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					StatsFrame frame = new StatsFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
		contentPane.setLayout(null);
		// en algun punto del codigo previo  el table = new JTable(x,y) tendria que obtener las estadisticas de la BD

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
					Connection.getInstance().setStatus(null);
					break;
				}
			}
			
			in.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Fallo al recibir del servidor.", "Cliente", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
		}

		/*	String strIn;//la linea que recibe del server, no se como recibe así que 
						//pongo esto para despues reemplazarlo por lo que va
			String []linea;
			int i=0, j=0; 
			Object[][]data=null;
			while((status = Connection.getInstance().getStatus()).equals(null))	{
				linea = strIn.split(" ");
				if(linea[0].equals("STAT")){
		
				data[i][0]=(Integer)i;
				data[i][1]= linea[1];
				data[i][2]= new Integer(linea[2]+linea[3]);
				data[i][3]= new Integer(linea[2]);
				data[i][4]= new Integer(linea[3]);
				}
				if(linea[0].equals("ENDSTAT"))
					Connection.getInstance().setStatus(null);
					
				i++;
			}
			*/

		//		Object[][] data = { { "001", new Integer(1), new Integer(1), new Integer(0) },
		//				{ "002", new Integer(5), new Integer(2), new Integer(3) },
		//				{ "002", new Integer(5), new Integer(2), new Integer(3) },
		//				{ "002", new Integer(5), new Integer(2), new Integer(3) },
		//				{ "002", new Integer(5), new Integer(2), new Integer(3) },
		//
		//		};

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
		//linea anterior: no muestra en WindowsBuilder eso, pero si al ejecutar
	}
}
