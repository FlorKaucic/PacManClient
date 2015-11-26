package client.gui.game;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import client.config.Config;
import client.conn.Connection;
import client.gui.game.map.*;
import client.gui.game.other.ScorePanel;
import client.gui.game.other.TimerPanel;
import client.gui.game.other.WaitingDialog;
import client.gui.game.other.WinnerDialog;
import client.gui.notifications.Alert;
import client.logic.User;

import game.character.Character;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

	protected ScorePanel[] scorers;

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
					Connection.getInstance().send("MOVE "+(k.getKeyCode() - KeyEvent.VK_LEFT));
//					mapa.setDir(k.getKeyCode() - KeyEvent.VK_LEFT);
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
		Connection.getInstance().send("READY");
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
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (GameFrame.this.user.getProfile() != -1) {
					Alert dialog = new Alert("Usa las flechas del teclado para moverte.");
					dialog.setBorderColor(Color.YELLOW);
					dialog.setVisible(true);
				} else {
					Alert dialog = new Alert("Solo podes ver la partida.");
					dialog.setBorderColor(Color.YELLOW);
					dialog.setVisible(true);
				}
			}
		});

		waiting.dispose();
		setScorers(characters);
		time = 0;
		playing = true;

		System.out.println("before added");
		mapa.addCharacters(characters);
		System.out.println("after added");


		mapa.addCharacters(characters);
		
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
	
	public void setScorers(Character [] lines){
		int cantPlayers = lines.length;
		scorers = new ScorePanel[lines.length];
		for(int i=0; i<lines.length;i++){
			scorers[i] = new ScorePanel(i);
			scorers[i].setLocation((i+1)*(this.getWidth()/(cantPlayers+1))-scorers[i].getWidth()/2,0);
			contentPane.add(scorers[i]);
		}

	}
	
	public void setScore(int caracter, int score){
		scorers[caracter].setScore(score);
	}

	

	public void showWaiting() {
		waiting = new WaitingDialog(this.user.getProfile());
		waiting.setVisible(true);
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				waiting.requestFocus();
			}
		});
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

	public void setMovement(int i, int x, int y, int dx, int dy) {
		mapa.setMovement(i, x, y, dx, dy);
	}

	public void ballDown(int i) {
		mapa.removeBall(i);
	}

	public void sBallDown(int i) {
		mapa.removeSBall(i);
	}
	
	public void setWinner(int profile) {
		WinnerDialog winner = new WinnerDialog(profile);
		winner.setVisible(true);
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				winner.requestFocus();
			}
		});
	}
	
	public void setOffPlaying(){
		playing = false;
	}

}
