package game.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import game.map.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {

	private JPanel contentPane;
	MapPanel mapa;
	int charDir = -1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameFrame frame = new GameFrame();
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
	public GameFrame() {
		setTitle("Pacman");
		getContentPane().setLayout(null);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(dim.width / 2 - 400, dim.height / 2 - 400, 800, 800);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int opc = JOptionPane.showConfirmDialog(null, "¿Realmente desea salir?" ,
						"Cerrar", JOptionPane.OK_CANCEL_OPTION);
				if (opc == JOptionPane.OK_OPTION) {
					System.exit(0);
				}
			}
		});

		contentPane = new JPanel();
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent k) {

				if (k.getKeyCode() >= KeyEvent.VK_LEFT && k.getKeyCode() <= KeyEvent.VK_DOWN) {
					mapa.setStart();
					charDir = k.getKeyCode() - KeyEvent.VK_LEFT;
				}
	
				mapa.changePacmanDir(charDir);
			}
		});

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		mapa = new MapPanel();
		mapa.setLocation(150, 50);
		contentPane.add(mapa);

		Thread t = new Thread() {
			public void run() {
				while (true) {
					mapa.movimientos(charDir);
					//mapa.getPacman().changePos(charDir);
					
					repaint();
					try {
						Thread.sleep(50);
					} catch (Exception e) {
						System.out.println("Error");
					}
				}
			}
		};
		t.start();
	}
}
