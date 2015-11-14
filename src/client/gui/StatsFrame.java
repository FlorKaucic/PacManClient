package client.gui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import javax.swing.JTable;
import javax.swing.WindowConstants;

@SuppressWarnings("serial")
public class StatsFrame extends JFrame {
	private JPanel contentPane;

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
	}
	
	public void load(Object[][] data){
		String[] nombreColumnas = { "Usuario", "Partidas jugadas", "Partidas ganadas", "Partidas perdidas" };
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 422, 244);
		contentPane.add(scrollPane);
		
		JTable table = new JTable(data, nombreColumnas);
		scrollPane.setViewportView(table);
		table.setRowSelectionAllowed(false);
		table.setEnabled(false);
		table.setBackground(Color.BLACK);
		table.setForeground(Color.WHITE);
		table.setFont(new Font("Arial", Font.PLAIN, 12));
		table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
	}
}
