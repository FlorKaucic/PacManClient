package client.gui.game;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import client.config.Config;
import client.gui.game.map.*;
import client.gui.game.other.ScorePanel;
import client.gui.game.other.TimerPanel;
import client.gui.game.other.WaitingDialog;
import client.logic.User;

import game.character.Character;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {

	private static GameFrame INSTANCE;

	protected User user;

	protected JPanel contentPane;
	protected WaitingDialog waiting;

	protected MapPanel mapa;
	protected TimerPanel timer;

	protected int time;
	protected boolean playing;

	protected ScorePanel scorer1;
	protected ScorePanel scorer2;
	protected ScorePanel scorer3;
	protected ScorePanel scorer4;
	protected ScorePanel scorer5;

	private int cantPlayers;

	public static GameFrame getInstance() {
		if (INSTANCE == null)
			INSTANCE = new GameFrame();
		return INSTANCE;
	}

	/**
	 * Create the frame.
	 */
	private GameFrame() {
		setTitle("Pacman");
		getContentPane().setLayout(null);
		int x = Integer.parseInt(Config.get("screen_width")) / 2 - 400;
		int y = (Integer.parseInt(Config.get("screen_height")) - Integer.parseInt(Config.get("taskbar_height"))) / 2
				- 350;
		setBounds(x, y, 800, 700);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int opc = JOptionPane.showConfirmDialog(null, "¿Realmente desea salir?", "Cerrar",
						JOptionPane.OK_CANCEL_OPTION);
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
		contentPane.setBackground(Color.BLACK);

		timer = new TimerPanel();
		timer.setLocation(this.getWidth() / 2 - timer.getWidth() / 2, 50);
		contentPane.add(timer);	
		
	}

	public void setMap(int[][] map) {
		mapa = new MapPanel(map);
		mapa.setLocation(150, 100);
		contentPane.add(mapa);

		waiting = new WaitingDialog(this.user.getProfile());
		waiting.setVisible(true);
	}

	public void setUser(User user) {
		this.user = user;
		String nickname = JOptionPane.showInputDialog(null, "Ingrese su nickname:", user.getNickname());
		this.user.setNickname(nickname);
	}

	public int getProfile() {
		return this.user.getProfile();
	}

	public void initMatch(Character[] characters) {
		waiting.dispose();
		time = 0;
		playing = true;

		System.out.println("before added");
		mapa.addCharacters(characters);
		System.out.println("after added");
		
		setScorers(characters.length);
		
		Thread t = new Thread() {
			@Override
			public void run() {
				while (playing) {
					timer.update(++time);
					repaint();
					mapa.update();

					try {
						Thread.sleep(50);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Error Thread.", "Cliente", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		};
		t.start();
	}

//	public void initCountdown() {
//		time = 10;
//		Thread t = new Thread() {
//			@Override
//			public void run() {
//				while (true) {
//					waiting.update("La partida comienza en " + time + " segundos.");
//					time--;
//					if (time < 0)
//						break;
//					try {
//						Thread.sleep(1000);
//					} catch (Exception e) {
//						JOptionPane.showMessageDialog(null, "Error Thread.", "Cliente", JOptionPane.ERROR_MESSAGE);
//					}
//				}
//			}
//		};
//		t.start();
//	}
	
	private void setScorers(int cantPlayers){
		if(cantPlayers>=1){
			scorer1 = new ScorePanel();
			scorer1.setLocation(1*(this.getWidth()/(cantPlayers+1))-scorer1.getWidth()/2,0);
			contentPane.add(scorer1);
		}
		if(cantPlayers>=2){
			scorer2 = new ScorePanel();
			scorer2.setLocation(2*(this.getWidth()/(cantPlayers+1))-scorer2.getWidth()/2,0);
			contentPane.add(scorer2);
		}
		if(cantPlayers>=3){
			scorer3 = new ScorePanel();
			scorer3.setLocation(3*this.getWidth()/(cantPlayers+1)-scorer3.getWidth()/2,0);
			contentPane.add(scorer3);
		}
		if(cantPlayers>=4){
			scorer4 = new ScorePanel();
			scorer4.setLocation(4*this.getWidth()/(cantPlayers+1)-scorer4.getWidth()/2,0);
			contentPane.add(scorer4);
		}
		
		if(cantPlayers==5){
			scorer5 = new ScorePanel();
			scorer5.setLocation(5*this.getWidth()/(cantPlayers+1)-scorer5.getWidth()/2,0);
			contentPane.add(scorer5);
		}

	}
}
