package client.gui.game;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import client.gui.game.map.*;
import client.gui.game.other.TimerPanel;
import client.logic.User;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {

	private static GameFrame INSTANCE;
	
	protected User user;
	
	protected JPanel contentPane;
	
	protected MapPanel mapa;
	protected TimerPanel timer;
	
	protected int time;
	protected boolean started;

	public static GameFrame getInstance(){
		if(INSTANCE == null)
			INSTANCE = new GameFrame();
		return INSTANCE;
	}

	/**
	 * Create the frame.
	 */
	private GameFrame() {
		setTitle("Pacman");
		getContentPane().setLayout(null);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(dim.width / 2 - 400, dim.height / 2 - 350, 800, 700);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
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
					mapa.moveCharacter(k.getKeyCode() - KeyEvent.VK_LEFT);
				}				
			}
		});

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		timer = new TimerPanel();
		timer.setLocation(this.getWidth()/2-timer.getWidth()/2,50);
		contentPane.add(timer);

		started = true;
	}
	
	public void setMap(int[][] map){
		mapa = new MapPanel(map);
		mapa.setLocation(150, 100);
		contentPane.add(mapa);

		Thread t = new Thread() {
			@Override
			public void run() {
				while (true) {
					if(started){
						timer.update(++time);
						repaint();
						mapa.update();
					}
					try {
						Thread.sleep(25);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Error Thread.", "Cliente", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		};
		t.start();
	}

	public void setUser(User user) {
		this.user = user;
		String nickname = JOptionPane.showInputDialog(null, "Ingrese su nickname:", user.getNickname());
		this.user.setNickname(nickname);
	}
	
	public void initMatch(){
		time = 0;
		
	}
}
