package client.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import javax.swing.JTable;
import javax.swing.WindowConstants;

@SuppressWarnings("serial")
public class Stats extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Stats frame = new Stats();
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
	public Stats() {
		setTitle("Pacoman - Estadisticas");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		// en algun punto del codigo previo  el table = new JTable(x,y) tendria que obtener las estadisticas de la BD
		String[] nombreColumnas = {"Usuario","Partidas jugadas", "Partidas ganadas","Partidas perdidas"};
		Object[][] data = {
				{"001",new Integer(1),new Integer(1),new Integer(0)},
				{"002",new Integer(5),new Integer(2),new Integer(3)},
				{"002",new Integer(5),new Integer(2),new Integer(3)},
				{"002",new Integer(5),new Integer(2),new Integer(3)},
				{"002",new Integer(5),new Integer(2),new Integer(3)},
				{"002",new Integer(5),new Integer(2),new Integer(3)},
				{"002",new Integer(5),new Integer(2),new Integer(3)},
				{"002",new Integer(5),new Integer(2),new Integer(3)},
				{"002",new Integer(5),new Integer(2),new Integer(3)},
				{"002",new Integer(5),new Integer(2),new Integer(3)},
				{"002",new Integer(5),new Integer(2),new Integer(3)},
				{"002",new Integer(5),new Integer(2),new Integer(3)},
				{"002",new Integer(5),new Integer(2),new Integer(3)},
				{"002",new Integer(5),new Integer(2),new Integer(3)},
				{"002",new Integer(5),new Integer(2),new Integer(3)},
				{"002",new Integer(5),new Integer(2),new Integer(3)},
				{"002",new Integer(5),new Integer(2),new Integer(3)},
				{"002",new Integer(5),new Integer(2),new Integer(3)},
				{"002",new Integer(5),new Integer(2),new Integer(3)},
				{"002",new Integer(5),new Integer(2),new Integer(3)},
				{"002",new Integer(5),new Integer(2),new Integer(3)},
				{"999",new Integer(66464),new Integer(4443),new Integer(3897897)},
		};
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 422, 244);
		contentPane.add(scrollPane);
		
		table = new JTable(data,nombreColumnas);
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
