package game.gui;
import mapa.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//import client.gui.MapaUI;

@SuppressWarnings("serial")
public class GameUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameUI frame = new GameUI();
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
	public GameUI() {
		setTitle("Pacman");
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/*JPanel panelmapa = new JPanel();
		panelmapa.setBounds(39, 34, 532, 343);
		contentPane.add(panelmapa);
		*/
		setBounds(0,0,800,700);
		MapaUI mapa = new MapaUI();
		mapa.setBounds(0, 0, 550, 500);
		//panelmapa.add(mapa);
		getContentPane().add(mapa);
				
	}
}
