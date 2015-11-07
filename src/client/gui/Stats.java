package client.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
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
				{"002",new Integer(5),new Integer(2),new Integer(3)},
		};
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 422, 244);
		contentPane.add(scrollPane);
		
		table = new JTable(data,nombreColumnas);
		scrollPane.setViewportView(table);
		table.setRowSelectionAllowed(false);
		table.setEnabled(false);
	}
}
